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
import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Slot;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
public class DoctorScheduleDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;
    
       public boolean checkDoctorHasSchedule(int doctorId) {
        String sql = "SELECT 1 FROM doctor_schedule WHERE doctor_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; 
    }


    public int insertDoctorSchedule(int doctorId, Date date, int status) {
        String sql = "INSERT INTO doctor_schedule(doctor_id,working_date,status) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
            ps.setInt(3, status);
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

    public WorkingDateSchedule getWorkingDayScheduleOfDoctor(int doctorId, Date workingdate) {
        String sql = "SELECT ds.working_date, ds.status, dss.slot_start, dss.slot_end "
                + "FROM doctor_schedule ds "
                + "JOIN doctor_schedule_slot dss ON ds.schedule_id = dss.schedule_id "
                + "WHERE ds.doctor_id = ? and ds.working_date = ? "
                + "ORDER BY ds.working_date, dss.slot_start";
        WorkingDateSchedule w = new WorkingDateSchedule();
        try (PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, workingdate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Date day = rs.getDate("working_date");
                int status = rs.getInt("status");
                w.setWorkingDate(day);
                w.setStatus(status);
                rs.beforeFirst();
                List<Slot> list = new ArrayList<>();
                while (rs.next()) {
                    Time start = rs.getTime("slot_start");
                    Time end = rs.getTime("slot_end");
                    Slot slot = new Slot();
                    slot.setSlotStart(start);
                    slot.setSlotEnd(end);
                    list.add(slot);
                }
                w.setSlots(list);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return w;
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

    public int deleteDoctorScheduleByDoctorId(int doctorId) {

        String deleteSlots = "DELETE FROM doctor_schedule_slot WHERE schedule_id IN (SELECT schedule_id FROM doctor_schedule WHERE doctor_id = ?)";
        String deleteDoctorSchedule = "DELETE FROM doctor_schedule WHERE doctor_id = ?";

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

    public int deleteDayScheduleOfDoctor(int doctorId, Date date) {

        String deleteSlots = "DELETE FROM doctor_schedule_slot WHERE schedule_id IN (SELECT schedule_id FROM doctor_schedule WHERE doctor_id = ? and working_date = ?)";
        String deleteDoctorSchedule = "DELETE FROM doctor_schedule WHERE doctor_id = ? and working_date = ?";

        try (PreparedStatement ps1 = connection.prepareStatement(deleteSlots); PreparedStatement ps2 = connection.prepareStatement(deleteDoctorSchedule)) {

            ps1.setInt(1, doctorId);
            ps1.setDate(2, date);
            ps1.executeUpdate();

            ps2.setInt(1, doctorId);
            ps2.setDate(2, date);
            ps2.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return 0;
    }

    public List<WorkingDateSchedule> getWorkingScheduleOfDoctor(int doctorId) {
        String sql = "SELECT ds.working_date, ds.status, dss.slot_start, dss.slot_end "
                + "FROM doctor_schedule ds "
                + "JOIN doctor_schedule_slot dss ON ds.schedule_id = dss.schedule_id "
                + "WHERE ds.doctor_id = ? "
                + "ORDER BY ds.working_date, dss.slot_start";
        Map<Date, WorkingDateSchedule> workingMap = new LinkedHashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("working_date");
                int status = rs.getInt("status");
                Time start = rs.getTime("slot_start");
                Time end = rs.getTime("slot_end");

                WorkingDateSchedule day = workingMap.computeIfAbsent(date, d -> {// sửa lại ở đây , khó kiểu chỗ này
                    WorkingDateSchedule w = new WorkingDateSchedule();
                    w.setWorkingDate(d);
                    w.setStatus(status);
                    w.setSlots(new ArrayList<>());
                    return w;
                });

                Slot slot = new Slot();
                slot.setSlotStart(start);
                slot.setSlotEnd(end);
                day.getSlots().add(slot);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>(workingMap.values());
    }

    public List<Date> getAllDaysWorkingInscheduleOfDoctor(int doctorId) {
        String sql = "SELECT DISTINCT working_date FROM doctor_schedule WHERE doctor_id = ? ORDER BY working_date";
        List<Date> dates = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("working_date");
                dates.add(date);
            }

        } catch (Exception e) {
            System.out.println("Error fetching working dates: " + e.getMessage());
        }

        return dates;
    }

    public int getStatusSlotBasedOnAppointment(int slotId) {
        String sql = "select ";
        return 0;
    }

    public List<WorkingDateSchedule> getWorkingScheduleOfDoctor10Day(int doctorId) {
        String sql = "SELECT \n"
                + "    ds.doctor_id,\n"
                + "    ds.working_date, \n"
                + "    ds.status, \n"
                + "    dss.slot_id, \n"
                + "    dss.slot_start, \n"
                + "    dss.slot_end,\n"
                + "    COUNT(a.appointment_id) AS appointment_count\n"
                + "FROM doctor_schedule ds\n"
                + "JOIN doctor_schedule_slot dss ON ds.schedule_id = dss.schedule_id\n"
                + "LEFT JOIN appointment a ON dss.slot_id = a.slot_id AND a.status != 0\n"
                + "WHERE ds.status = 1 \n"
                + "  AND ds.doctor_id = ?\n"
                + "  AND ds.working_date IN (\n"
                + "      SELECT TOP 10 working_date \n"
                + "      FROM (\n"
                + "          SELECT DISTINCT working_date \n"
                + "          FROM doctor_schedule \n"
                + "          WHERE working_date >= CAST(GETDATE() AS DATE)\n"
                + "            AND doctor_id = ?\n"
                + "      ) AS temp \n"
                + "      ORDER BY working_date\n"
                + "  )\n"
                + "GROUP BY ds.doctor_id, ds.working_date, ds.status, dss.slot_id, dss.slot_start, dss.slot_end\n"
                + "ORDER BY ds.working_date, dss.slot_start;";

        Map<Date, WorkingDateSchedule> workingMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, doctorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date sqlDate = rs.getDate("working_date");
                LocalDate workingDate = sqlDate.toLocalDate();
                int status = rs.getInt("status");
                int slotId = rs.getInt("slot_id");
                Time start = rs.getTime("slot_start");
                Time end = rs.getTime("slot_end");
                int appointmentCount = rs.getInt("appointment_count");

                WorkingDateSchedule day = workingMap.computeIfAbsent(sqlDate, d -> {
                    WorkingDateSchedule w = new WorkingDateSchedule();
                    w.setWorkingDate(d);
                    w.setStatus(status);
                    w.setSlots(new ArrayList<>());
                    return w;
                });

                Slot slot = new Slot();
                slot.setSlotId(slotId);
                slot.setSlotStart(start);
                slot.setSlotEnd(end);

                if (workingDate.isEqual(today)) {
                    LocalDateTime slotStartTime = LocalDateTime.of(workingDate, start.toLocalTime());
                    Duration duration = Duration.between(now, slotStartTime);
                    if (duration.toHours() < 2) {
                        slot.setStatus(-1); // quá gần
                    } else if (appointmentCount >= 4) {
                        slot.setStatus(0); // full
                    } else {
                        slot.setStatus(1); // còn chỗ
                    }
                } else {
                    slot.setStatus(appointmentCount >= 4 ? 0 : 1);
                }

                day.getSlots().add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // bỏ ngày hôm nay nếu tất cả slot đều -1
        Iterator<Map.Entry<Date, WorkingDateSchedule>> iterator = workingMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Date, WorkingDateSchedule> entry = iterator.next();
            LocalDate d = entry.getKey().toLocalDate();
            if (d.isEqual(today)) {
                List<Slot> slots = entry.getValue().getSlots();
                if (slots.stream().allMatch(s -> s.getStatus() == -1)) {
                    iterator.remove(); // xóa hôm nay
                }
                break; // đã xử lý hôm nay, không cần duyệt tiếp
            }
        }

        return new ArrayList<>(workingMap.values());
    }

    public static void main(String[] args) {
        DoctorScheduleDAO d = new DoctorScheduleDAO();
        int doctorId = 1; // ID bác sĩ cần test
        //  Date date = Date.valueOf("2025-06-23");
        //     d.deleteDayScheduleOfDoctor(doctorId, date);
        //List<WorkingDateSchedule> schedules = d.getWorkingScheduleOfDoctor(1);
        /*    
        WorkingDateSchedule w = d.getWorkingDayScheduleOfDoctor(1, date);

        System.out.println("Ngày: " + w.getWorkingDate() + " | Trạng thái: " + w.getStatus());

            for (Slot slot : w.getSlots()) {
                System.out.println("    Slot: " + slot.getSlotStart() + " - " + slot.getSlotEnd());
            
        }
         */
 /*
        List<WorkingDateSchedule> schedules = d.getWorkingScheduleOfDoctor10Day(6);
        for (WorkingDateSchedule day : schedules) {
            System.out.println("Ngày: " + day.getWorkingDate() + " | Trạng thái: " + day.getStatus());


                for (Slot slot : day.getSlots()) {
                    System.out.println("    Slot: " + slot.getSlotStart() + " - " + slot.getSlotEnd());
                }
            

            System.out.println(); // dòng trống giữa các ngày
        }
         */
        System.out.println(d.getWorkingScheduleOfDoctor10Day(1));

    }

}
