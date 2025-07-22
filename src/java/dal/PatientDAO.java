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
public class PatientDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public int CountPatient(){
        String sql = "SELECT * FROM patients";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            int count  = 0;
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
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

        String sql = "INSERT INTO patients (username, patient_name, gender, dob, job, phone, email, bhyt, nation, cccd, address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, gender);
            ps.setDate(4, dob);  // java.sql.Date
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
    public static String sql;

    public List<Patient> searchPatientsByNameWithPaging(String keyword, int page, int pageSize) {
        List<Patient> patients = new ArrayList<>();

        String sql = """
        SELECT 
            p.patient_id,
            p.username,
            p.patient_name,
            p.gender,
            p.dob,
            p.job,
            p.phone,
            p.email,
            p.bhyt,
            p.nation,
            p.cccd,
            p.address,
            u.img AS user_img
        FROM patients p
        JOIN users u ON p.username = u.username
        WHERE p.patient_name LIKE ?
        ORDER BY p.patient_id
        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            int offset = (page - 1) * pageSize;
            stmt.setInt(2, offset);
            stmt.setInt(3, pageSize);

            ResultSet rs = stmt.executeQuery();

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
                p.setImg(rs.getString("user_img")); // lấy ảnh từ bảng users

                patients.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }

    public int searchPatientsByNameWithPagingNumber(String keyword) {

        String sql = """
        SELECT 
            p.patient_id
        FROM patients p
        JOIN users u ON p.username = u.username
        WHERE p.patient_name LIKE ?
    """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = count + 1;
            }
            return count;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String getImgByPatientid(int id) {
        String sql = "	select users.img from patients join users on patients.username=users.username where patient_id = " + id;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                String img = rs.getString(1);
                return img;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "default";
    }

    public static void main(String[] args) {
        PatientDAO pdao = new PatientDAO();
        System.out.println(pdao.getPatientById(1));

    }
}
