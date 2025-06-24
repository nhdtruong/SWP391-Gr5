/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AppointmentView;

/**
 *
 * @author DELL
 */
public class AppointmentDAO extends DBContext {

    public void insertAppointment(int patientId, int slotId, int serviceId, String note) {
        String sql = "INSERT INTO appointment (patient_id, slot_id, service_id, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, slotId);
            ps.setInt(3, serviceId);
            ps.setString(4, note);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public static void main(String[] args) {
        AppointmentDAO a = new AppointmentDAO();
        System.out.println(a.getAllAppointmentsFilterStatus("0"));
    }

}
