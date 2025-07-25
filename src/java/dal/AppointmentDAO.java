/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DTOStatic.AppointmentStat;
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
import java.util.HashMap;
import java.util.Map;
import model.Patient;

/**
 *
 * @author DELL
 */
public class AppointmentDAO extends DBContext {

    public List<Map<String, Object>> getAppointmentsLast5Days() {
        List<Map<String, Object>> list = new ArrayList<>();

        String sql = "SELECT CONVERT(date, created_at) AS day, COUNT(*) AS total_appointments "
                + "FROM appointment "
                + "WHERE created_at >= DATEADD(day, -4, CAST(GETDATE() AS date)) "
                + "GROUP BY CONVERT(date, created_at) "
                + "ORDER BY day";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("day", rs.getDate("day")); // java.sql.Date
                item.put("total", rs.getInt("total_appointments"));
                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int CountAppointment() {
        String sql = "select count(*) from appointment";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Creating appointment failed, no ID obtained.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int insertAppointment(
            String appointmentCode,
            int patientId,
            int doctorId,
            int serviceId,
            String note) {

        String sql = "INSERT INTO appointment "
                + "(appointment_code, patient_id, doctor_id,service_id, note) "
                + "VALUES ( ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, appointmentCode);
            ps.setInt(2, patientId);
            ps.setInt(3, doctorId);

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

        return -1;
    }

    public int insertAppointment(
            String appointmentCode,
            int patientId,
            int doctorId,
            int slotId,
            int serviceId,
            Date booking_date,
            Time slot_start,
            Time slot_end,
            String note) {

        String sql = "INSERT INTO appointment "
                + "(appointment_code, patient_id, doctor_id, slot_id, service_id, booking_date, slot_start, slot_end, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, appointmentCode);
            ps.setInt(2, patientId);
            ps.setInt(3, doctorId);
            ps.setInt(4, slotId);
            ps.setInt(5, serviceId);
            ps.setDate(6, booking_date);
            ps.setTime(7, slot_start);
            ps.setTime(8, slot_end);
            ps.setString(9, note);

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

        return -1;
    }

    public int insertAppointment(
            String appointmentCode,
            int patientId,
            int serviceId,
            Date booking_date,
            Time slot_start,
            Time slot_end,
            String note) {

        String sql = "INSERT INTO appointment "
                + "(appointment_code, patient_id, service_id, booking_date, slot_start, slot_end, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, appointmentCode);
            ps.setInt(2, patientId);

            ps.setInt(3, serviceId);
            ps.setDate(4, booking_date);
            ps.setTime(5, slot_start);
            ps.setTime(6, slot_end);
            ps.setString(7, note);

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

        return -1;
    }

    public boolean rescheduleAppointment(int appointmentId, int doctorId, int slotId, Date dateBooking, Time slotStart, Time slotEnd) {
        String sql = "UPDATE appointment SET doctor_id = ?, slot_id = ?, booking_date = ?, slot_start = ?, slot_end = ?,slot_id_request_change=null,status=1 WHERE appointment_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, slotId);
            ps.setDate(3, dateBooking);
            ps.setTime(4, slotStart);
            ps.setTime(5, slotEnd);
            ps.setInt(6, appointmentId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean reqChangeAppointmentByDoctor(int appointmentId, int doctorId, int slot_id_request_change, Date dateBooking, Time slotStart, Time slotEnd) {
        String sql = "UPDATE appointment SET doctor_id = ?, slot_id_request_change = ?, booking_date = ?, slot_start = ?, slot_end = ?,status = 4  WHERE appointment_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, slot_id_request_change);
            ps.setDate(3, dateBooking);
            ps.setTime(4, slotStart);
            ps.setTime(5, slotEnd);
            ps.setInt(6, appointmentId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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

    public List<AppointmentView> getAppointmentsByFilter(String status, String paymentStatus) {
        List<AppointmentView> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    a.slot_id,\n"
                + "    p.patient_name,\n"
                + "    d.doctor_name,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    ds.working_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    sl.slot_start,\n"
                + "    sl.slot_end,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "JOIN patients p ON a.patient_id = p.patient_id\n"
                + "JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "JOIN service s ON a.service_id = s.service_id\n"
                + "JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "JOIN doctor_schedule_slot sl ON a.slot_id = sl.slot_id\n"
                + "JOIN doctor_schedule ds ON sl.schedule_id = ds.schedule_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")\n"
                + "WHERE 1 = 1");

        List<Object> params = new ArrayList<>();

        if (status != null && !status.equalsIgnoreCase("all")) {
            sql.append(" AND a.status = ?");
            params.add(Integer.parseInt(status));
        }

        if (paymentStatus != null && !paymentStatus.equalsIgnoreCase("all")) {
            sql.append(" AND ISNULL(pm.status, 'Chưa thanh toán') = ?");
            params.add(paymentStatus);
        }

        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentView a = new AppointmentView();

                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setSlotId(rs.getInt("slot_id"));
                    a.setPatientName(rs.getString("patient_name"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setWorkingDate(rs.getDate("working_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<AppointmentView> getAppointmentsByUsername(String username) {
        List<AppointmentView> list = new ArrayList<>();

        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    a.slot_id,\n"
                + "    p.patient_name,\n"
                + "    d.doctor_name,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    a.booking_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    a.slot_start,\n"
                + "    a.slot_end,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "left JOIN patients p ON a.patient_id = p.patient_id\n"
                + "left JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "left JOIN service s ON a.service_id = s.service_id\n"
                + "left JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")\n"
                + "WHERE p.username = ?";
        System.out.println(sql);
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentView a = new AppointmentView();

                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setSlotId(rs.getInt("slot_id"));
                    a.setPatientName(rs.getString("patient_name"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setDateBooking(rs.getDate("booking_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));

                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public int countRefundRequestedAppointments() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM appointment WHERE status = 3";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<AppointmentView> getAllAppointments() {
        List<AppointmentView> list = new ArrayList<>();

        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    a.slot_id,\n"
                + "    a.doctor_id,\n"
                + "    p.patient_name,\n"
                + "    d.doctor_name,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    a.booking_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    a.slot_start,\n"
                + "    a.slot_end,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "left JOIN patients p ON a.patient_id = p.patient_id\n"
                + "left JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "left JOIN service s ON a.service_id = s.service_id\n"
                + "left JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "left JOIN doctor_schedule_slot sl ON a.slot_id = sl.slot_id\n"
                + "left JOIN doctor_schedule ds ON sl.schedule_id = ds.schedule_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentView a = new AppointmentView();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setSlotId(rs.getInt("slot_id"));
                    a.setDoctorId(rs.getInt("doctor_id"));
                    a.setPatientName(rs.getString("patient_name"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setDateBooking(rs.getDate("booking_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public AppointmentView getBillstByCode(String appointmentCode) {
        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    p.patient_name,\n"
                + "    d.doctor_name,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    a.booking_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    a.slot_start,\n"
                + "    a.slot_end,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "left JOIN patients p ON a.patient_id = p.patient_id\n"
                + "left JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "left JOIN service s ON a.service_id = s.service_id\n"
                + "left JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")\n"
                + "WHERE a.appointment_code = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, appointmentCode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AppointmentView a = new AppointmentView();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setPatientName(rs.getString("patient_name"));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setDateBooking(rs.getDate("booking_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public AppointmentView getAppointmentsByAppointmentId(int appointmentID) {

        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    a.patient_id,\n"
                + "    d.doctor_name,\n"
                + "    a.doctor_id,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    a.booking_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    a.slot_start,\n"
                + "    a.slot_end,\n"
                + "    a.slot_id,\n"
                + "    a.slot_id_request_change,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "left JOIN patients p ON a.patient_id = p.patient_id\n"
                + "left JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "left JOIN service s ON a.service_id = s.service_id\n"
                + "left JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")\n"
                + "WHERE a.appointment_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, appointmentID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AppointmentView a = new AppointmentView();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setPatient(getPatientById(rs.getInt("patient_id")));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setDoctorId(rs.getInt("doctor_id"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setDateBooking(rs.getDate("booking_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setSlotIdReqChange(rs.getInt("slot_id_request_change"));
                    a.setSlotId(rs.getInt("slot_id"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));
                    return a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM patients WHERE patient_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Patient p = new Patient();
                p.setPatientId(rs.getInt("patient_id"));
                p.setPatientName(rs.getNString("patient_name"));
                p.setGender(rs.getNString("gender"));
                p.setDob(rs.getDate("dob"));
                p.setJob(rs.getNString("job"));
                p.setPhone(rs.getString("phone"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<AppointmentView> getAppointmentsByDoctorId(int doctorId) {
        List<AppointmentView> list = new ArrayList<>();

        String sql = "SELECT \n"
                + "    a.appointment_id,\n"
                + "    a.appointment_code,\n"
                + "    a.patient_id,\n"
                + "    d.doctor_name,\n"
                + "    a.doctor_id,\n"
                + "    s.service_name,\n"
                + "    dpt.department_name,\n"
                + "    a.booking_date,\n"
                + "    a.created_at,\n"
                + "    a.is_refunded,\n"
                + "    a.slot_start,\n"
                + "    a.slot_end,\n"
                + "    a.slot_id,\n"
                + "    a.slot_id_request_change,\n"
                + "    a.status,\n"
                + "    a.note,\n"
                + "    ISNULL(pm.amount, 0) AS amount,\n"
                + "    ISNULL(pm.status, 'Chưa thanh toán') AS payment_status\n"
                + "FROM appointment a\n"
                + "left JOIN patients p ON a.patient_id = p.patient_id\n"
                + "left JOIN doctors d ON a.doctor_id = d.doctor_id\n"
                + "left JOIN service s ON a.service_id = s.service_id\n"
                + "left JOIN department dpt ON s.department_id = dpt.department_id\n"
                + "LEFT JOIN payment pm ON pm.payment_id = (\n"
                + "    SELECT TOP 1 payment_id FROM payment\n"
                + "    WHERE appointment_id = a.appointment_id\n"
                + "    ORDER BY pay_date DESC\n"
                + ")\n"
                + "WHERE a.doctor_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AppointmentView a = new AppointmentView();
                    a.setAppointmentId(rs.getInt("appointment_id"));
                    a.setAppointment_code(rs.getString("appointment_code"));
                    a.setSlotId(rs.getInt("slot_id"));
                    a.setSlotIdReqChange(rs.getInt("slot_id_request_change"));
                    a.setDoctorId(rs.getInt("doctor_id"));
                    a.setPatient(getPatientById(rs.getInt("patient_id")));
                    a.setDoctorName(rs.getString("doctor_name"));
                    a.setServiceName(rs.getString("service_name"));
                    a.setDepartmentName(rs.getString("department_name"));
                    a.setDateBooking(rs.getDate("booking_date"));
                    a.setCreated_at(rs.getDate("created_at"));
                    a.setIs_refunded(rs.getBoolean("is_refunded"));
                    a.setSlotStart(rs.getTime("slot_start"));
                    a.setSlotEnd(rs.getTime("slot_end"));
                    a.setStatus(rs.getInt("status"));
                    a.setNote(rs.getString("note"));
                    a.setAmount(rs.getDouble("amount"));
                    a.setPaymentStatus(rs.getString("payment_status"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean cancelAppointmentById(String appointment_code) {
        String sql = "UPDATE appointment SET status = 0 WHERE appointment_code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, appointment_code);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean requestRefoundAppointmentById(String appointment_code) {
        String sql = "UPDATE appointment SET status = 3 WHERE appointment_code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, appointment_code);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countAppointmentsByMonthYear(int year, int month) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM [clinic_db4].[dbo].[appointment]
            WHERE YEAR([created_at]) = ? AND MONTH([created_at]) = ?
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // mặc định nếu không có kết quả
    }

    public boolean updateStatusToZero(int appointmentId) {
        String sql = "UPDATE appointment SET status = 0 WHERE appointment_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, appointmentId);

            int rows = ps.executeUpdate();
            return rows > 0; // nếu có ít nhất 1 dòng được update
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int checkPatientBookingConflictDetailed(int patientId, int departmentId, Date dateBooking, Time slotStart, Time slotEnd) {

        String sqlDept = "SELECT COUNT(*) FROM appointment a " //đã đặt cùng chuyên khoa trong ngày
                + "JOIN [service] s ON a.service_id = s.service_id "
                + "JOIN doctor_schedule_slot dss ON a.slot_id = dss.slot_id "
                + "JOIN doctor_schedule ds ON dss.schedule_id = ds.schedule_id "
                + "WHERE a.patient_id = ? "
                + "AND ds.working_date = ? "
                + "AND s.department_id = ? "
                + "AND a.status != 0 ";

        String sqlTime = "SELECT COUNT(*) FROM appointment a " // đặt cùng thời gian,khác chuyên khoa 
                + "JOIN [service] s ON a.service_id = s.service_id "
                + "JOIN doctor_schedule_slot dss ON a.slot_id = dss.slot_id "
                + "JOIN doctor_schedule ds ON dss.schedule_id = ds.schedule_id "
                + "WHERE a.patient_id = ? "
                + "AND ds.working_date = ? "
                + "AND s.department_id != ? "
                + "AND CONVERT(varchar, dss.slot_start, 108) = CONVERT(varchar, ?, 108) "
                + "AND CONVERT(varchar, dss.slot_end, 108) = CONVERT(varchar, ?, 108) "
                + "AND a.status != 0 ";

        try {

            try (PreparedStatement ps = connection.prepareStatement(sqlDept)) {
                ps.setInt(1, patientId);
                ps.setDate(2, dateBooking);
                ps.setInt(3, departmentId);
                ResultSet rs = ps.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    return 1;
                }
            }

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

        // System.out.println(a.getBillstByCode("T250721LUUTKJ"));
        // System.out.println(a.getAppointmentsByAppointmentId(1));
        //   System.out.println(a.getAllAppointments());
        //  System.out.println(a.getAppointmentsByUsername("user10"));
        //System.out.println(a.getAllAppointments());
        //   System.out.println(a.getAppointmentsByAppointmentId(19));
        System.out.println(a.getAppointmentsByDoctorId(70));
    }

}
