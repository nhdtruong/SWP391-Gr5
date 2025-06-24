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
import model.Patient;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author DELL
 */
public class PatientDAO extends DBContext{
   PreparedStatement ps = null;
    ResultSet rs = null;
    public List<Patient> getPatientByUsername(String username) {
         List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE username = ? and status = 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
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
                list.add(p);
            }
             return list;
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
    
    public void updatePatient(Patient p) {
    String sql = "UPDATE patients SET "
               + "patient_name = ?, gender = ?, dob = ?, job = ?, phone = ?, email = ?, "
               + "bhyt = ?, nation = ?, cccd = ?, address = ? "
               + "WHERE patient_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setNString(1, p.getPatientName());
        ps.setNString(2, p.getGender());
        ps.setDate(3, p.getDob());
        ps.setNString(4, p.getJob());
        ps.setString(5, p.getPhone());
        ps.setNString(6, p.getEmail());
        ps.setString(7, p.getBhyt());
        ps.setNString(8, p.getNation());
        ps.setString(9, p.getCccd());
        ps.setNString(10, p.getAddress());
        ps.setInt(11, p.getPatientId()); 

        ps.executeUpdate();
       
    } catch (Exception e) {
        e.printStackTrace();
    }

}
public void deletePatientById(int id) {
    String sql = "UPDATE patients SET status = 0 WHERE patient_id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace(); 
    }
}
    public void insertPatient(String username, String name, String gender, Date dob, String job,
                          String phone, String email, String bhyt, String nation,
                          String cccd, String address) {

    String sql = "INSERT INTO patients (username, patient_name, gender, dob, job, phone, email, bhyt, nation, cccd, address) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setString(2, name);
        ps.setString(3, gender);
        ps.setDate(4, dob);  
        ps.setString(5, job);
        ps.setString(6, phone);
        ps.setString(7, email);
        ps.setString(8, bhyt);
        ps.setString(9, nation);
        ps.setString(10, cccd);
        ps.setString(11, address);

        ps.executeUpdate();
        System.out.println("Insert thành công!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    public boolean isBHYTExists(String bhyt) {
    String sql = "SELECT 1 FROM patients WHERE bhyt = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, bhyt);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
    public boolean isCCCDExists(String cccd) {
    String sql = "SELECT 1 FROM patients WHERE cccd = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, cccd);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); 
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public static void main(String[] args) {
        PatientDAO pdao = new PatientDAO();
        System.out.println(pdao.getPatientByUsername("user10"));
//        System.out.println(pdao.getPatientByUsername("admin"));
//        pdao.deletePatientById(6);
//        Patient p = new Patient();
//        p.setPatientId(1); // ID của bệnh nhân cần cập nhật (phải tồn tại trong DB)
//        p.setPatientName("Nguyễn Văn A");
//        p.setGender("Nam");
//        p.setDob(Date.valueOf(LocalDate.of(1995, 5, 15)));
//        p.setJob("Kỹ sư phần mềm");
//        p.setPhone("0901234567");
//        p.setEmail("nguyenvana@example.com");
//        p.setBhyt("BHYT123456");
//        p.setNation("Việt Nam");
//        p.setCccd("012345678901");
//        p.setAddress("123 Đường ABC, Quận 1, TP.HCM");
//        pdao.updatePatient(p);
    }
}
