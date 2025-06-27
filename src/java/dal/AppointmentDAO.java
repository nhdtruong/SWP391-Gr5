/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.AppointmentView;
import java.sql.Time;
import java.sql.SQLException;

import java.sql.Statement;
/**
 *
 * @author DELL
 */
public class AppointmentDAO extends DBContext {

    public int insertAppointment(String appointmentCode, int patientId, int slotId, int serviceId, String note) {
        String sql = "INSERT INTO appointment (appointment_code, patient_id, slot_id, service_id, note) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, appointmentCode);
            ps.setInt(2, patientId);
            ps.setInt(3, slotId);
            ps.setInt(4, serviceId);
            ps.setString(5, note);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating appointment failed, no rows affected.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                } else {
                    throw new SQLException("Creating appointment failed, no ID obtained.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // Nếu có lỗi
    }

    public List<AppointmentView> getAllAppointmentsSearchDoctor(String text) {
        List<AppointmentView> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.slot_id, -- slot_id từ bảng appointment\n"
                + "    p.patient_name ,\n"
                + "    d.doctor_name ,\n"
                + "    s.[service_name],\n"
                + "    ds.working_date,\n"
                + "    sl.slot_start,\n"
                + "    sl.slot_end,\n"
                + "    a.status,\n"
                + "    a.note\n"
                + "FROM appointment a\n"
                + "JOIN patients p ON a.patient_id = p.patient_id\n"
                + "JOIN service s ON a.service_id = s.service_id\n"
                + "JOIN doctor_schedule_slot sl ON a.slot_id = sl.slot_id\n"
                + "JOIN doctor_schedule ds ON sl.schedule_id = ds.schedule_id\n"
                + "JOIN doctors d ON ds.doctor_id = d.doctor_id \n"
                + "where d.doctor_name Like ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentView a = new AppointmentView();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setSlotId(rs.getInt("slot_id"));
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setServiceName(rs.getString("service_name"));
                a.setWorkingDate(rs.getDate("working_date"));
                a.setSlotStart(rs.getTime("slot_start"));
                a.setSlotEnd(rs.getTime("slot_end"));
                a.setStatus(rs.getInt("status"));
                a.setNote(rs.getString("note"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AppointmentView> getAllAppointmentsFilterStatus(String status) {
        List<AppointmentView> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.slot_id, -- slot_id từ bảng appointment\n"
                + "    p.patient_name ,\n"
                + "    d.doctor_name ,\n"
                + "    s.[service_name],\n"
                + "    ds.working_date,\n"
                + "    sl.slot_start,\n"
                + "    sl.slot_end,\n"
                + "    a.status,\n"
                + "    a.note\n"
                + "FROM appointment a\n"
                + "JOIN patients p ON a.patient_id = p.patient_id\n"
                + "JOIN service s ON a.service_id = s.service_id\n"
                + "JOIN doctor_schedule_slot sl ON a.slot_id = sl.slot_id\n"
                + "JOIN doctor_schedule ds ON sl.schedule_id = ds.schedule_id\n"
                + "JOIN doctors d ON ds.doctor_id = d.doctor_id \n"
                + "where 1 = 1 ");

        List<Object> params = new ArrayList<>();

        if (!status.equalsIgnoreCase("all")) {
            sql.append(" and a.status = ?");
            params.add(status);
        }
        try (PreparedStatement ps = connection.prepareStatement(sql.toString());) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentView a = new AppointmentView();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setSlotId(rs.getInt("slot_id"));
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setServiceName(rs.getString("service_name"));
                a.setWorkingDate(rs.getDate("working_date"));
                a.setSlotStart(rs.getTime("slot_start"));
                a.setSlotEnd(rs.getTime("slot_end"));
                a.setStatus(rs.getInt("status"));
                a.setNote(rs.getString("note"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<AppointmentView> getAllAppointments() {
        List<AppointmentView> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.slot_id, -- slot_id từ bảng appointment\n"
                + "    p.patient_name ,\n"
                + "    d.doctor_name ,\n"
                + "    s.[service_name],\n"
                + "    ds.working_date,\n"
                + "    sl.slot_start,\n"
                + "    sl.slot_end,\n"
                + "    a.status,\n"
                + "    a.note\n"
                + "FROM appointment a\n"
                + "JOIN patients p ON a.patient_id = p.patient_id\n"
                + "JOIN service s ON a.service_id = s.service_id\n"
                + "JOIN doctor_schedule_slot sl ON a.slot_id = sl.slot_id\n"
                + "JOIN doctor_schedule ds ON sl.schedule_id = ds.schedule_id\n"
                + "JOIN doctors d ON ds.doctor_id = d.doctor_id;";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AppointmentView a = new AppointmentView();
                a.setAppointmentId(rs.getInt("appointment_id"));
                a.setSlotId(rs.getInt("slot_id"));
                a.setPatientName(rs.getString("patient_name"));
                a.setDoctorName(rs.getString("doctor_name"));
                a.setServiceName(rs.getString("service_name"));
                a.setWorkingDate(rs.getDate("working_date"));
                a.setSlotStart(rs.getTime("slot_start"));
                a.setSlotEnd(rs.getTime("slot_end"));
                a.setStatus(rs.getInt("status"));
                a.setNote(rs.getString("note"));
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int checkPatientBookingConflictDetailed(int patientId, int departmentId, Date dateBooking, Time slotStart, Time slotEnd) {

        String sqlDept = "SELECT COUNT(*) FROM appointment a " //đã đặt cùng chuyên khoa trong ngày
                + "JOIN [service] s ON a.service_id = s.service_id "
                + "JOIN doctor_schedule_slot dss ON a.slot_id = dss.slot_id "
                + "JOIN doctor_schedule ds ON dss.schedule_id = ds.schedule_id "
                + "WHERE a.patient_id = ? "
                + "AND ds.working_date = ? "
                + "AND s.department_id = ? "
                + "AND a.status != 0";

        String sqlTime = "SELECT COUNT(*) FROM appointment a "
                + "JOIN [service] s ON a.service_id = s.service_id "
                + "JOIN doctor_schedule_slot dss ON a.slot_id = dss.slot_id "
                + "JOIN doctor_schedule ds ON dss.schedule_id = ds.schedule_id "
                + "WHERE a.patient_id = ? "
                + "AND ds.working_date = ? "
                + "AND s.department_id != ? "
                + "AND CONVERT(varchar, dss.slot_start, 108) = CONVERT(varchar, ?, 108) "
                + "AND CONVERT(varchar, dss.slot_end, 108) = CONVERT(varchar, ?, 108) "
                + "AND a.status != 0";

        try {
            //  lỗi 1
            try (PreparedStatement ps = connection.prepareStatement(sqlDept)) {
                ps.setInt(1, patientId);
                ps.setDate(2, dateBooking);
                ps.setInt(3, departmentId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return 1;
                }
            }

            // lỗi 2
            try (PreparedStatement ps = connection.prepareStatement(sqlTime)) {
                ps.setInt(1, patientId);
                ps.setDate(2, dateBooking);
                ps.setInt(3, departmentId);
                ps.setTime(4, slotStart);
                ps.setTime(5, slotEnd);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return 2;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0; // Không lỗi
    }

    public static void main(String[] args) {
        AppointmentDAO a = new AppointmentDAO();
        System.out.println(a.getAllAppointmentsFilterStatus("0"));
    }

}
