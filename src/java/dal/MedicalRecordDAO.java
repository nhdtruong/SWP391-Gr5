/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import model.Medicine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.MedicalRecord;
import model.Patient;

/**
 *
 * @author DELL
 */
public class MedicalRecordDAO extends DBContext {

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
                p.setEmail(rs.getNString("email"));
                p.setBhyt(rs.getString("bhyt"));
                p.setNation(rs.getNString("nation"));
                p.setCccd(rs.getString("cccd"));
                p.setAddress(rs.getNString("address"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MedicalRecord> getMedicalRecordsWithMedicinesByUsername(String username) {
        String sql = """
        SELECT 
            mr.*, 
            m.medicine_id, m.medicine_name, m.unit, m.usage, m.image,
            d.doctor_name,
            dp.department_name,
            p.patient_id
        FROM patients p
        JOIN medical_record mr ON p.patient_id = mr.patient_id
        JOIN doctors d ON mr.doctor_id = d.doctor_id
        JOIN department dp ON d.deparment_id = dp.department_id
        LEFT JOIN prescription_medicine pm ON mr.record_id = pm.record_id
        LEFT JOIN medicine m ON pm.medicine_id = m.medicine_id
        WHERE p.username = ?
        ORDER BY mr.visit_date DESC
    """;

        Map<Integer, MedicalRecord> recordMap = new LinkedHashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int recordId = rs.getInt("record_id");
                    MedicalRecord record = recordMap.get(recordId);

                    if (record == null) {
                        record = new MedicalRecord();
                        record.setRecordId(recordId);
                        int pid = rs.getInt("patient_id");
                        record.setPatientId(pid);
                        record.setDoctorId(rs.getInt("doctor_id"));
                        record.setSymptoms(rs.getNString("symptoms"));
                        record.setDiagnosis(rs.getNString("diagnosis"));
                        record.setConclusion(rs.getNString("conclusion"));
                        record.setInstruction(rs.getNString("instruction"));
                        record.setNote(rs.getNString("note"));
                        record.setVisitDate(rs.getDate("visit_date"));
                        record.setMedicines(new ArrayList<>());

                        Patient patient = getPatientById(pid);
                        record.setPatient(patient);

                        record.setDoctorName(rs.getNString("doctor_name"));
                        record.setDepartmentName(rs.getNString("department_name"));

                        recordMap.put(recordId, record);
                    }

                    int medId = rs.getInt("medicine_id");
                    if (!rs.wasNull()) {
                        Medicine med = new Medicine();
                        med.setMedicineId(medId);
                        med.setMedicineName(rs.getNString("medicine_name"));
                        med.setUnit(rs.getNString("unit"));
                        med.setUsage(rs.getNString("usage"));
                        med.setImage(rs.getString("image"));
                        record.getMedicines().add(med);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(recordMap.values());
    }

    public List<MedicalRecord> getMedicalRecordsWithMedicinesByPatient(int patientId) {
        String sql = "SELECT mr.*, m.medicine_id, m.medicine_name, m.unit, m.usage, m.image "
                + "FROM medical_record mr "
                + "LEFT JOIN prescription_medicine pm ON mr.record_id = pm.record_id "
                + "LEFT JOIN medicine m ON pm.medicine_id = m.medicine_id "
                + "WHERE mr.patient_id = ? "
                + "ORDER BY mr.visit_date DESC";

        Map<Integer, MedicalRecord> recordMap = new LinkedHashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int recordId = rs.getInt("record_id");

                    MedicalRecord record = recordMap.get(recordId);
                    if (record == null) {
                        record = new MedicalRecord();
                        record.setRecordId(recordId);
                        record.setPatientId(rs.getInt("patient_id"));
                        record.setDoctorId(rs.getInt("doctor_id"));
                        record.setSymptoms(rs.getString("symptoms"));
                        record.setDiagnosis(rs.getString("diagnosis"));
                        record.setConclusion(rs.getString("conclusion"));
                        record.setInstruction(rs.getString("instruction"));
                        record.setNote(rs.getString("note"));
                        record.setVisitDate(rs.getDate("visit_date"));
                        record.setMedicines(new ArrayList<>());

                        recordMap.put(recordId, record);
                    }

                    int medId = rs.getInt("medicine_id");
                    if (medId > 0) {
                        Medicine med = new Medicine();
                        med.setMedicineId(medId);
                        med.setMedicineName(rs.getString("medicine_name"));
                        med.setUnit(rs.getString("unit"));
                        med.setUsage(rs.getString("usage"));
                        med.setImage(rs.getString("image"));
                        record.getMedicines().add(med);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>(recordMap.values());
    }

    public List<Patient> getPatientsByDoctor(int doctorId) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT DISTINCT p.patient_id, p.username, p.patient_name, p.gender, p.dob, "
                + "p.job, p.phone, p.email, p.bhyt, p.nation, p.cccd, p.address "
                + "FROM medical_record mr "
                + "JOIN patients p ON mr.patient_id = p.patient_id "
                + "WHERE mr.doctor_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Patient p = new Patient();

                    p.setPatientId(rs.getInt("patient_id"));
                    p.setUsername(rs.getString("username"));
                    p.setPatientName(rs.getString("patient_name"));
                    p.setGender(rs.getString("gender"));
                    p.setDob(rs.getDate("dob"));
                    p.setJob(rs.getString("job"));
                    p.setPhone(rs.getString("phone"));
                    p.setEmail(rs.getString("email"));
                    p.setBhyt(rs.getString("bhyt"));
                    p.setNation(rs.getString("nation"));
                    p.setCccd(rs.getString("cccd"));
                    p.setAddress(rs.getString("address"));

                    list.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<MedicalRecord> getMedicalRecordsByDoctorAndPatient(int doctorId, int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT [record_id], [patient_id], [doctor_id], [symptoms], [diagnosis], "
                + "[conclusion], [instruction], [note], [visit_date] "
                + "FROM [medical_record] "
                + "WHERE doctor_id = ? AND patient_id = ?";
        MedicineDAO medicineDAO = new MedicineDAO();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setInt(2, patientId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MedicalRecord record = new MedicalRecord();
                    record.setRecordId(rs.getInt("record_id"));
                    record.setPatientId(rs.getInt("patient_id"));
                    record.setDoctorId(rs.getInt("doctor_id"));
                    record.setSymptoms(rs.getString("symptoms"));
                    record.setDiagnosis(rs.getString("diagnosis"));
                    record.setConclusion(rs.getString("conclusion"));
                    record.setInstruction(rs.getString("instruction"));
                    record.setNote(rs.getString("note"));
                    record.setVisitDate(rs.getDate("visit_date"));
                    record.setMedicines(medicineDAO.GetMedicineByRecord_id(record.getRecordId()));
                    
                    records.add(record);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    public boolean insertMedicalRecord(
            int patientId,
            int doctorId,
            String symptoms,
            String diagnosis,
            String conclusion,
            String instruction,
            String note,
            String[] selectedMedicineIds
    ) {
        String sqlInsertRecord = "INSERT INTO medical_record (patient_id, doctor_id, symptoms, diagnosis, conclusion, instruction, note) "
                + "OUTPUT INSERTED.record_id VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlInsertMedicine = "INSERT INTO prescription_medicine (record_id, medicine_id) VALUES (?, ?)";

        try {
            int recordId = -1;

            // 1. Insert medical_record và lấy record_id
            try (PreparedStatement psRecord = connection.prepareStatement(sqlInsertRecord)) {
                psRecord.setInt(1, patientId);
                psRecord.setInt(2, doctorId);
                psRecord.setString(3, symptoms);
                psRecord.setString(4, diagnosis);
                psRecord.setString(5, conclusion);
                psRecord.setString(6, instruction);
                psRecord.setString(7, note);

                try (ResultSet rs = psRecord.executeQuery()) {
                    if (rs.next()) {
                        recordId = rs.getInt("record_id");
                    }
                }
            }

            // 2. Thêm thuốc nếu có
            if (selectedMedicineIds != null && recordId != -1) {
                try (PreparedStatement psMedicine = connection.prepareStatement(sqlInsertMedicine)) {
                    for (String idStr : selectedMedicineIds) {
                        int medId = Integer.parseInt(idStr);
                        psMedicine.setInt(1, recordId);
                        psMedicine.setInt(2, medId);
                        psMedicine.executeUpdate();
                    }
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
        System.out.println(medicalRecordDAO.getMedicalRecordsWithMedicinesByUsername("user10"));
    }

}
