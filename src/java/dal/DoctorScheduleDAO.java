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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Shifts;
import model.Slot;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
public class DoctorScheduleDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public int insertDoctorSchedule(int doctorId, Date date) {
        String sql = "INSERT INTO doctor_schedule(doctor_id,working_date) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, doctorId);
            ps.setDate(2, date);
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
        String sql = "SELECT ds.working_date, ds.status, dss.shift, dss.slot_start, dss.slot_end "
                + "FROM doctor_schedule ds "
                + "JOIN doctor_schedule_slot dss ON ds.schedule_id = dss.schedule_id "
                + "WHERE ds.doctor_id = ? and ds.working_date = ? "
                + "ORDER BY ds.working_date, dss.shift, dss.slot_start";
        WorkingDateSchedule w = new WorkingDateSchedule();
        try (PreparedStatement ps = connection.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, workingdate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Date day = rs.getDate("working_date");
                int status = rs.getInt("status");
                w.setWorkingDate(day);
                w.setStatus(status);
                rs.beforeFirst();
                while (rs.next()) {

                    int shift = rs.getInt("shift");
                    Time start = rs.getTime("slot_start");
                    Time end = rs.getTime("slot_end");

                    Shifts targetShift = null;
                    for (Shifts s : w.getShifts()) {
                        if (s.getShift() == shift) {
                            targetShift = s;
                            break;
                        }
                    }
                    if (targetShift == null) {
                        targetShift = new Shifts();
                        targetShift.setShift(shift);
                        w.getShifts().add(targetShift);
                    }

                    Slot slot = new Slot();
                    slot.setSlotStart(start);
                    slot.setSlotEnd(end);
                    targetShift.getSlots().add(slot);
                }
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
    
     public int deleteDayScheduleOfDoctor(int doctorId ,Date date) {

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
        String sql = "SELECT ds.working_date, ds.status, dss.shift, dss.slot_start, dss.slot_end "
                + "FROM doctor_schedule ds "
                + "JOIN doctor_schedule_slot dss ON ds.schedule_id = dss.schedule_id "
                + "WHERE ds.doctor_id = ? "
                + "ORDER BY ds.working_date, dss.shift, dss.slot_start";
        Map<Date, WorkingDateSchedule> workingMap = new LinkedHashMap<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("working_date");
                int status = rs.getInt("status");
                int shift = rs.getInt("shift");
                Time start = rs.getTime("slot_start");
                Time end = rs.getTime("slot_end");

                WorkingDateSchedule day = workingMap.computeIfAbsent(date, d -> {// sửa lại ở đây , khó kiểu chỗ này
                    WorkingDateSchedule w = new WorkingDateSchedule();
                    w.setWorkingDate(d);
                    w.setStatus(status);
                    return w;
                });

                Shifts targetShift = null;
                for (Shifts s : day.getShifts()) {
                    if (s.getShift() == shift) {
                        targetShift = s;
                        break;
                    }
                }
                if (targetShift == null) {
                    targetShift = new Shifts();
                    targetShift.setShift(shift);
                    day.getShifts().add(targetShift);
                }

                // Thêm Slot vào Shift
                Slot slot = new Slot();
                slot.setSlotStart(start);
                slot.setSlotEnd(end);
                targetShift.getSlots().add(slot);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<>(workingMap.values());
    }

    public static void main(String[] args) {
        DoctorScheduleDAO d = new DoctorScheduleDAO();
        int doctorId = 1; // ID bác sĩ cần test
          Date date = Date.valueOf("2025-06-23");
       d.deleteDayScheduleOfDoctor(doctorId, date);
        List<WorkingDateSchedule> schedules = d.getWorkingScheduleOfDoctor(doctorId);
      
        WorkingDateSchedule w = d.getWorkingDayScheduleOfDoctor(1, date);

        System.out.println("Ngày: " + w.getWorkingDate() + " | Trạng thái: " + w.getStatus());
        for (Shifts s : w.getShifts()) {
            String ca = (s.getShift() == 1) ? "sáng" : "chiều";
            System.out.println("ca" + ca);
            for (Slot slot : s.getSlots()) {
                System.out.println("    Slot: " + slot.getSlotStart() + " - " + slot.getSlotEnd());
            }
        }

       

        System.out.println(); // dòng trống giữa các ngày

    }

//        for (WorkingDateSchedule day : schedules) {
//            System.out.println("Ngày: " + day.getWorkingDate() + " | Trạng thái: " + day.getStatus());
//
//            for (Shifts shift : day.getShifts()) {
//                String ca = (shift.getShift() == 1) ? "Sáng" : "Chiều";
//                System.out.println("  Ca: " + ca);
//
//                for (Slot slot : shift.getSlots()) {
//                    System.out.println("    Slot: " + slot.getSlotStart() + " - " + slot.getSlotEnd());
//                }
//            }
//
//            System.out.println(); // dòng trống giữa các ngày
//        }
    // d.insertDoctorSchedule(1, date);
}
