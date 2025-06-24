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
public class WeeklyScheduleSlotDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    
    
   public Map<Integer, List<DoctorScheduleSlots>> getTemplateScheduleByDoctorId(int doctorId) {
    Map<Integer, List<DoctorScheduleSlots>> result = new HashMap<>();

    String sql = """
        SELECT d.day_of_week, s.slot_start, s.slot_end
        FROM weekly_schedule_doctor d
        JOIN weekly_schedule_slot s ON d.template_id = s.template_id
        WHERE d.doctor_id = ?
    """;

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, doctorId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int dayOfWeek = rs.getInt("day_of_week");
            Time start = rs.getTime("slot_start");
            Time end = rs.getTime("slot_end");

            DoctorScheduleSlots slot = new DoctorScheduleSlots(start, end);

            // Nếu chưa có danh sách cho ngày này thì tạo mới
            result.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(slot);
        }

    } catch (Exception e) {
        e.printStackTrace(); // hoặc dùng Logger
    }

    return result;
}

        public void insertSlot(int templateId, Time start, Time end) {
        String sql = "INSERT INTO weekly_schedule_slot (template_id, slot_start, slot_end) VALUES ( ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, templateId);
            ps.setTime(2, start);
            ps.setTime(3, end);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        WeeklyScheduleSlotDAO d = new WeeklyScheduleSlotDAO();
        System.out.println(d.getTemplateScheduleByDoctorId(1));
    }

}
