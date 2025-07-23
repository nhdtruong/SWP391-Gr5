/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class WeeklyDoctorScheduleDAO extends DBContext {

  
    public int insertTemplateSchdule(int doctorId, int dayOfWeek) {
        String sql = "INSERT INTO weekly_schedule_doctor (doctor_id, day_of_week) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, doctorId);
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

    public void insertSlot(int templateId, int shift, Time start, Time end) {
        String sql = "INSERT INTO weekly_schedule_slot (template_id, shift, slot_start, slot_end) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, templateId);
            ps.setInt(2, shift);
            ps.setTime(3, start);
            ps.setTime(4, end);
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

    public static void main(String[] args) {
        WeeklyDoctorScheduleDAO d = new WeeklyDoctorScheduleDAO();
        System.out.println(d.deleteWeeklyScheduleByDoctorId(2));
        //truong yh alsdja
    }

}
