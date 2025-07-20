/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.WorkingDateSchedule;
import java.sql.Date;
import model.Slot;

/**
 *
 * @author DELL
 *
 */
public class WeeklyPackageServiceScheduleDAO extends DBContext {

    public int insertTemplateSchdule(int service_Id, int dayOfWeek) {
        String sql = "INSERT INTO weekly_schedule_packageService (service_id, day_of_week) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, service_Id);
            ps.setInt(2, dayOfWeek);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public void insertSlot(int week_pakageService_id, Time start, Time end) {
        String sql = "INSERT INTO weekly_schedule_slot_pakageService (week_pakageService_id, slot_start, slot_end) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, week_pakageService_id);

            ps.setTime(2, start);
            ps.setTime(3, end);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int deleteWeeklyScheduleByDoctorId(int doctorId) {

        String deleteSlots = "DELETE FROM weekly_schedule_slot WHERE template_id IN (SELECT template_id FROM weekly_schedule_doctor WHERE doctor_id = ?)";
        String deleteDoctorSchedule = "DELETE FROM weekly_schedule_doctor WHERE doctor_id = ?";

        try (PreparedStatement ps1 = connection.prepareStatement(deleteSlots); PreparedStatement ps2 = connection.prepareStatement(deleteDoctorSchedule)) {

            ps1.setInt(1, doctorId);
            ps1.executeUpdate();

            ps2.setInt(1, doctorId);
            ps2.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return 0;
    }

    public List<WorkingDateSchedule> getPackageServiceSchedule7Days(int serviceId) {
        Map<Integer, List<Slot>> dayOfWeekSlotMap = getWeeklySlotsByServiceId(serviceId);

        return generateScheduleFromWeeklyMap(dayOfWeekSlotMap, serviceId);
    }

    private Map<Integer, List<Slot>> getWeeklySlotsByServiceId(int serviceId) {
        Map<Integer, List<Slot>> map = new HashMap<>();

        String sql = "SELECT wsp.day_of_week, wsps.slot_start, wsps.slot_end "
                + "FROM weekly_schedule_packageService wsp "
                + "JOIN weekly_schedule_slot_pakageService wsps ON wsp.week_pakageService_id = wsps.week_pakageService_id "
                + "WHERE wsp.service_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, serviceId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int day = rs.getInt("day_of_week");
                Time start = rs.getTime("slot_start");
                Time end = rs.getTime("slot_end");

                Slot slot = new Slot();
                slot.setSlotStart(start);
                slot.setSlotEnd(end);
                

                map.computeIfAbsent(day, k -> new ArrayList<>()).add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    private List<WorkingDateSchedule> generateScheduleFromWeeklyMap(Map<Integer, List<Slot>> dayOfWeekSlotMap, int serviceId) {

        List<WorkingDateSchedule> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.plusDays(i);
            int dow = date.getDayOfWeek().getValue();
            int dbDay = dow + 1;

            if (dayOfWeekSlotMap.containsKey(dbDay)) {
                WorkingDateSchedule wds = new WorkingDateSchedule();
                wds.setWorkingDate(Date.valueOf(date));
                wds.setStatus(1);

                List<Slot> slotsForDay = new ArrayList<>();
                for (Slot s : dayOfWeekSlotMap.get(dbDay)) {
                    Slot newSlot = new Slot();
                    newSlot.setSlotStart(s.getSlotStart());
                    newSlot.setSlotEnd(s.getSlotEnd());
                    newSlot.setSlotId(1);
                    int count = getBookedAppointmentsCount(serviceId, date, s.getSlotStart(), s.getSlotEnd());
                    newSlot.setStatus(count >= 3 ? 0 : 1);

                    slotsForDay.add(newSlot);
                }

                wds.setSlots(slotsForDay);
                result.add(wds);
            }
        }

        return result;
    }

    private int getBookedAppointmentsCount(int serviceId, LocalDate date, Time start, Time end) {
       String sql = "SELECT COUNT(*) FROM appointment "
           + "WHERE service_id = ? AND booking_date = ? "
           + "AND CAST(slot_start AS TIME) = CAST(? AS TIME) "
           + "AND CAST(slot_end AS TIME) = CAST(? AS TIME)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, serviceId);
            ps.setDate(2, Date.valueOf(date));
            ps.setTime(3, start);
            ps.setTime(4, end);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args) {
        WeeklyPackageServiceScheduleDAO w = new WeeklyPackageServiceScheduleDAO();
        System.out.println(w.getPackageServiceSchedule7Days(36));
    }

}
