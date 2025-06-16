/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.AcademicDegree;
import model.AcademicTitle;
import model.Deparment;
import model.Doctor;
import model.Position;

/**
 *
 * @author DELL
 */
public class DoctorDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Doctor> getTop6Doctor() {
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_name,d.deparment_id,d.img,d.AcademicDegree_id ,d.AcademicTitle_id ,d.position_id\n"
                + "from doctors d ";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getString(1),
                        getDepartmentByDoctor_department_id(rs.getInt(2)),
                        rs.getString(3), getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt(4)),
                        getAcademictitleByDoctor_Academictile_id(rs.getInt(5)),
                        getPositionByDoctor_position_id(rs.getInt(6)));

                list.add(d);
            }
            return list;

        } catch (SQLException e) {
        }

        return null;
    }

    public Position getPositionByDoctor_position_id(int id) {

        String sql = "select * from position p where p.position_id = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Position(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public Deparment getDepartmentByDoctor_department_id(int id) {

        String sql = "select d.department_id, d.department_name from department d where d.department_id = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Deparment(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public AcademicDegree getAcademicDegreeByDoctor_AcademicDegre_id(int id) {

        String sql = "select * from AcademicDegree a where a.AcademicDegree_id =?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AcademicDegree(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public AcademicTitle getAcademictitleByDoctor_Academictile_id(int id) {

        String sql = "select * from AcademicTitle a where a.AcademicTitle_id = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new AcademicTitle(rs.getInt(1), rs.getString(2));
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public String getEmailDotorByUsernae(String username) {
        String sql = "select u.email from users u where u.username = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }
    
public List<Doctor> getAllDoctorBySearchName(String name) {
    List<Doctor> list = new ArrayList<>();
    String sql = "SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, " +
            "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, " +
            "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE d.doctor_name LIKE ?";

    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Doctor d = new Doctor(
                    rs.getInt("doctor_id"),
                    rs.getString("doctor_name"),
                    rs.getString("gender"),
                    rs.getDate("dob"),
                    rs.getString("phone"),
                    getDepartmentByDoctor_department_id(rs.getInt("deparment_id")),
                    rs.getString("address"),
                    rs.getString("img"),
                    rs.getString("description"),
                    getPositionByDoctor_position_id(rs.getInt("position_id")),
                    getAcademictitleByDoctor_Academictile_id(rs.getInt("AcademicTitle_id")),
                    getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt("AcademicDegree_id")),
                    rs.getInt("status"),
                    rs.getString("specialized"),
                    rs.getString("EducationHistory"),
                    getEmailDotorByUsernae(rs.getString("username"))
            );
            list.add(d);
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return list;
}



    public List<Doctor> getAllDoctorByFilter(String gender, String position, String department) {
    List<Doctor> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, " +
            "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, " +
            "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE 1 = 1");

    List<Object> params = new ArrayList<>();

    if (!gender.equalsIgnoreCase("all")) {
        sql.append(" and d.gender = ?");
        params.add(gender);
    }
    if (!position.equalsIgnoreCase("all")) {
        sql.append(" and d.position_id = ?");
        params.add(position);
    }
    if (!department.equalsIgnoreCase("all")) {
        sql.append(" and d.deparment_id = ?");
        params.add(department);
    }

    try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Doctor d = new Doctor(
                    rs.getInt("doctor_id"),
                    rs.getString("doctor_name"),
                    rs.getString("gender"),
                    rs.getDate("dob"),
                    rs.getString("phone"),
                    getDepartmentByDoctor_department_id(rs.getInt("deparment_id")),
                    rs.getString("address"),
                    rs.getString("img"),
                    rs.getString("description"),
                    getPositionByDoctor_position_id(rs.getInt("position_id")),
                    getAcademictitleByDoctor_Academictile_id(rs.getInt("AcademicTitle_id")),
                    getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt("AcademicDegree_id")),
                    rs.getInt("status"),
                    rs.getString("specialized"),
                    rs.getString("EducationHistory"),
                    getEmailDotorByUsernae(rs.getString("username"))
            );
            list.add(d);
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return list;
}

    public List<Doctor> getAllDoctorByAdmin() {
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.gender,d.dob,d.phone,d.deparment_id,d.address,d.img,d.description,d.position_id,d.AcademicTitle_id,d.AcademicDegree_id,d.status ,d.specialized,d.EducationHistory from doctors d";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        getDepartmentByDoctor_department_id(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        getPositionByDoctor_position_id(rs.getInt(11)),
                        getAcademictitleByDoctor_Academictile_id(rs.getInt(12)),
                        getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt(13)),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        
                        getEmailDotorByUsernae(rs.getString(2)));
                list.add(d);
            }
            return list;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

  
                 public void deleteDoctorByAdmin(String username) {
        String sql = "DELETE FROM doctors WHERE username = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
                 
      public String getUsernameByDoctorId(String id) {
        String sql = "select d.username from doctors d where d.doctor_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;

    }
    public Doctor getDoctorByDoctorId(String id){
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.gender,d.dob,d.phone,d.deparment_id,d.address,d.img,d.description,d.position_id,d.AcademicTitle_id,d.AcademicDegree_id,d.status,d.specialized,d.EducationHistory from doctors d where d.doctor_id = ?";
        try {
             PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
          if (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getString(6),
                        getDepartmentByDoctor_department_id(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        getPositionByDoctor_position_id(rs.getInt(11)),
                        getAcademictitleByDoctor_Academictile_id(rs.getInt(12)),
                        getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt(13)),
                        rs.getInt(14),
                        rs.getString(15),
                        rs.getString(16),
                        getEmailDotorByUsernae(rs.getString(2)));
               return d;
            }
           
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
      public void updateDoctorStatusRole( String username, int status) {
    String sql = "UPDATE doctors SET  status = ?  WHERE username = ?";
    
    try (  PreparedStatement ps = connection.prepareStatement(sql)) {

         
        ps.setString(2, username);
        ps.setInt(1, status);

        int rows = ps.executeUpdate();
        

    } catch (Exception e) {
        e.printStackTrace();
    }

    
}
    public void updateDoctor(int doctorId, String doctorName, String gender, String phone, String dob,
                            String description, int departmentId, int status,String specialized,String EducationHistory ,int positionId,int academicDegreeId , int academicTitleId) {
    String sql = "UPDATE doctors SET doctor_name = ?, gender = ?, phone = ?, dob = ?, " +
                 "description = ?, deparment_id = ?,  status = ? ,specialized =?, EducationHistory =? ,position_id= ?,AcademicDegree_id=? ,AcademicTitle_id= ? WHERE doctor_id = ?";
    
    try ( 
         PreparedStatement ps = connection.prepareStatement(sql)) {
        
        ps.setString(1, doctorName);
        ps.setString(2, gender);
        ps.setString(3, phone);
        ps.setDate(4, Date.valueOf(dob)); 
        ps.setString(5, description);
        ps.setInt(6, departmentId);
        ps.setInt(7, status);
        ps.setString(8, specialized);
        ps.setString(9, EducationHistory);
        ps.setInt(10,positionId );
        ps.setInt(11, academicDegreeId);
        ps.setInt(12, academicTitleId);
        ps.setInt(13, doctorId);
        int rows = ps.executeUpdate();
        

    } catch (Exception e) {
        e.printStackTrace();
    }

    
}

    public void InsertDoctorByAdmin(String username, String doctor_name, String gender, int department_id, int position_id, int AcademicT_id, int AcademicDgre_id, int status,String img) {
        String sql = "INSERT INTO doctors (\n"
                + "    username,\n"
                + "    doctor_name,\n"
                + "    gender,\n"
                + "    deparment_id,\n"
                + "    position_id,\n"
                + "    AcademicTitle_id,\n"
                + "    AcademicDegree_id,\n"
                + "    status,\n"
                + "    img\n"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, doctor_name);
            ps.setString(3, gender);
            ps.setInt(4, department_id);
            ps.setInt(5, position_id);
            ps.setInt(6, AcademicT_id);
            ps.setInt(7, AcademicDgre_id);
            ps.setInt(8, status);
            ps.setString(9, img);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void RecoverStatusForDoctor(String username) {
        String sql = "UPDATE doctors\n"
                + "SET status = 1\n"
                + "WHERE username = ?;";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        DoctorDAO d = new DoctorDAO();
        List<Doctor> l = d.getAllDoctorByAdmin();
        System.out.println(l);
        System.out.println(d.getDoctorByDoctorId("1").toString());

    }
}
