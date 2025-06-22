/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DoctorScheduleSlots;

/**
 *
 * @author DELL
 */
public class DoctorScheduleSlotsDAO extends DBContext{
    
    PreparedStatement ps = null;
    ResultSet rs = null;

    
    
    public Map<Integer, List<DoctorScheduleSlots>> getTemplateScheduleByDoctorId(int doctorId) {
        Map<Integer, List<DoctorScheduleSlots>> result = new HashMap<>();

        String sql = "SELECT d.day_of_week, s.shift, s.slot_start, s.slot_end "
                + "FROM weekly_schedule_doctor d JOIN weekly_schedule_slot s ON d.template_id = s.template_id "
                + "WHERE d.doctor_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int day = rs.getInt("day_of_week");
                DoctorScheduleSlots slot = new DoctorScheduleSlots( rs.getTime("slot_start"), rs.getTime("slot_end"));
                List<DoctorScheduleSlots> slots = result.get(day);
                if (slots == null) {
                    slots = new ArrayList<>();
                    result.put(day, slots);
                }
                slots.add(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
        public void insertScheduleSlot(int schedule_id, Time start, Time end) {
        String sql = "INSERT INTO doctor_schedule_slot (schedule_id, slot_start, slot_end) VALUES ( ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, schedule_id);
            ps.setTime(2, start);
            ps.setTime(3, end);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
