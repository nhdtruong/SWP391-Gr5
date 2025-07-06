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
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
            System.out.println(e);
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

    public List<Doctor> getAllDoctorBySearchNameOrUsername(String name) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
                + "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, "
                + "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE d.doctor_name LIKE ? or d.username LiKE ? ";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("username"),
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

    public List<Doctor> getAllDoctorHaveScheduleBySearchNameOrUsername(String name) {
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.deparment_id from doctors d join doctor_schedule ds\n"
                + "on d.doctor_id = ds.doctor_id \n"
                + "group by d.doctor_id,d.username,d.doctor_name,d.deparment_id \n"
                + "having d.doctor_name LIKE ? or d.username LiKE ?  ";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setString(2, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), getDepartmentByDoctor_department_id(rs.getInt(4)));
                list.add(d);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Doctor> getAllDoctorBySearchName(String name) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
                + "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, "
                + "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE d.doctor_name LIKE ?";

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

    public List<Doctor> getAllDoctorByFilterDepartment(String department) {
        List<Doctor> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
                + "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, "
                + "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE 1 = 1");

        List<Object> params = new ArrayList<>();

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

    public List<Doctor> getAllDoctorHaveScheduleFilterDepartment(String department) {
        List<Doctor> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select d.doctor_id,d.username,d.doctor_name,d.deparment_id from doctors d join doctor_schedule ds\n"
                + "on d.doctor_id = ds.doctor_id \n"
                + "group by d.doctor_id,d.username,d.doctor_name,d.deparment_id \n"
                + "having 1 = 1 ");

        List<Object> params = new ArrayList<>();

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
                        rs.getString("username"),
                        rs.getString("doctor_name"),
                        getDepartmentByDoctor_department_id(rs.getInt("deparment_id"))
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
        StringBuilder sql = new StringBuilder("SELECT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
                + "d.deparment_id, d.address, d.img, d.description, d.position_id, d.AcademicTitle_id, "
                + "d.AcademicDegree_id, d.status,d.specialized,d.EducationHistory FROM doctors d WHERE 1 = 1");

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

    public List<Doctor> getDoctorsByServiceCategory(int categoryServiceId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT DISTINCT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
                + "d.deparment_id, d.img, d.position_id, d.AcademicTitle_id, "
                + "d.AcademicDegree_id, d.specialized, d.EducationHistory, s.fee "
                + "FROM doctors d "
                + "JOIN doctor_service ds ON d.doctor_id = ds.doctor_id "
                + "JOIN service s ON ds.service_id = s.service_id "
                + "JOIN category_service cs ON s.category_service_id = cs.category_service_id "
                + "WHERE cs.category_service_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryServiceId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Doctor d = new Doctor();

                    d.setDoctor_id(rs.getInt("doctor_id"));
                    d.setUsername(rs.getString("username"));
                    d.setDoctor_name(rs.getString("doctor_name"));
                    d.setGender(rs.getString("gender"));
                    d.setDOB(rs.getDate("dob"));
                    d.setPhone(rs.getString("phone"));
                    d.setDepartment(getDepartmentByDoctor_department_id(rs.getInt("deparment_id")));
                    d.setImg(rs.getString("img"));
                    d.setPosition(getPositionByDoctor_position_id(rs.getInt("position_id")));
                    d.setAcademicTitle(getAcademictitleByDoctor_Academictile_id(rs.getInt("AcademicTitle_id")));
                    d.setAcademicDegree(getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt("AcademicDegree_id")));
                    d.setSpecialized(rs.getString("specialized"));
                    d.setEducationHistory(rs.getString("EducationHistory"));
                    d.setEmail(getEmailDotorByUsernae(rs.getString("username")));
                    d.setFee(rs.getDouble("fee")); 

                    list.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Doctor> getDoctorsByServiceCategoryFilter(int categoryServiceId, String gender, String departmentId) {
    List<Doctor> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT DISTINCT d.doctor_id, d.username, d.doctor_name, d.gender, d.dob, d.phone, "
            + "d.deparment_id, d.img, d.position_id, d.AcademicTitle_id, "
            + "d.AcademicDegree_id, d.specialized, d.EducationHistory, s.fee "
            + "FROM doctors d "
            + "JOIN doctor_service ds ON d.doctor_id = ds.doctor_id "
            + "JOIN service s ON ds.service_id = s.service_id "
            + "JOIN category_service cs ON s.category_service_id = cs.category_service_id "
            + "WHERE cs.category_service_id = ?");

    if (!"all".equalsIgnoreCase(gender)) {
        sql.append(" AND d.gender = ?");
    }

    if (!"all".equalsIgnoreCase(departmentId)) {
        sql.append(" AND d.deparment_id = ?");
    }

    try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
        int index = 1;
        ps.setInt(index++, categoryServiceId);

        if (!"all".equalsIgnoreCase(gender)) {
            ps.setString(index++, gender);
        }

        if (!"all".equalsIgnoreCase(departmentId)) {
            ps.setInt(index++, Integer.parseInt(departmentId));
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Doctor d = new Doctor();

                d.setDoctor_id(rs.getInt("doctor_id"));
                d.setUsername(rs.getString("username"));
                d.setDoctor_name(rs.getString("doctor_name"));
                d.setGender(rs.getString("gender"));
                d.setDOB(rs.getDate("dob"));
                d.setPhone(rs.getString("phone"));
                d.setDepartment(getDepartmentByDoctor_department_id(rs.getInt("deparment_id")));
                d.setImg(rs.getString("img"));
                d.setPosition(getPositionByDoctor_position_id(rs.getInt("position_id")));
                d.setAcademicTitle(getAcademictitleByDoctor_Academictile_id(rs.getInt("AcademicTitle_id")));
                d.setAcademicDegree(getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt("AcademicDegree_id")));
                d.setSpecialized(rs.getString("specialized"));
                d.setEducationHistory(rs.getString("EducationHistory"));
                d.setEmail(getEmailDotorByUsernae(rs.getString("username")));
                d.setFee(rs.getDouble("fee")); 

                list.add(d);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
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
                        rs.getString(2),
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

    public List<Doctor> getAllDoctorInDepartmanent(int departmentId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.gender,d.dob,d.phone,d.deparment_id,d.address,d.img,d.description,d.position_id,d.AcademicTitle_id,d.AcademicDegree_id,d.status ,d.specialized,d.EducationHistory from doctors d "
                + "where d.deparment_id = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departmentId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1),
                        rs.getString(2),
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

    public Set<Integer> getWeekdayNumbersOfDoctor(int doctorId) {
        Set<Integer> weekdays = new TreeSet<>();
        String sql = "SELECT TOP 10 working_date FROM doctor_schedule "
                + "WHERE doctor_id = ? AND working_date >= CAST(GETDATE() AS DATE) "
                + "ORDER BY working_date";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date sqlDate = rs.getDate("working_date");
                LocalDate localDate = sqlDate.toLocalDate();
                int dayOfWeek = localDate.getDayOfWeek().getValue();
                weekdays.add(dayOfWeek);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekdays;
    }

    public List<Doctor> getAllDoctorInDepartmanentHaveSchedule(int departmentId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.doctor_name, d.gender, "
                + "d.deparment_id, d.description, d.img, d.position_id, "
                + "d.AcademicTitle_id, d.AcademicDegree_id, d.EducationHistory "
                + "FROM doctors d "
                + "WHERE d.deparment_id = ? and d.status = 1 "
                + "AND EXISTS ("
                + "    SELECT 1 FROM doctor_schedule ds "
                + "    WHERE ds.doctor_id = d.doctor_id "
                + "    AND ds.working_date >= CAST(GETDATE() AS DATE)"
                + ")";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departmentId);
            rs = ps.executeQuery();

            while (rs.next()) {
                int doctorId = rs.getInt("doctor_id");
                String doctorName = rs.getString("doctor_name");
                String gender = rs.getString("gender");
                int departmentIdFromDb = rs.getInt("deparment_id");
                String description = rs.getString("description");
                String img = rs.getString("img");
                int positionId = rs.getInt("position_id");
                int academicTitleId = rs.getInt("AcademicTitle_id");
                int academicDegreeId = rs.getInt("AcademicDegree_id");
                String educationHistory = rs.getString("EducationHistory");

                Deparment department = getDepartmentByDoctor_department_id(departmentIdFromDb);
                AcademicTitle academicTitle = getAcademictitleByDoctor_Academictile_id(academicTitleId);
                AcademicDegree academicDegree = getAcademicDegreeByDoctor_AcademicDegre_id(academicDegreeId);
                Position position = getPositionByDoctor_position_id(positionId);

                Set<Integer> workingWeekdays = getWeekdayNumbersOfDoctor(doctorId);

                Doctor d = new Doctor(
                        doctorId,
                        doctorName,
                        gender,
                        department,
                        description,
                        img,
                        academicDegree,
                        academicTitle,
                        position,
                        educationHistory,
                        workingWeekdays
                );

                list.add(d);
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Doctor> getAllDoctorHaveSchedule() {
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.deparment_id from doctors d join doctor_schedule ds\n"
                + "on d.doctor_id = ds.doctor_id \n"
                + "group by d.doctor_id,d.username,d.doctor_name,d.deparment_id ";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), getDepartmentByDoctor_department_id(rs.getInt(4)));
                list.add(d);
            }
            return list;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Doctor> getDoctorsWithoutSchedule() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.username, d.doctor_name, d.deparment_id "
                + "FROM doctors d "
                + "LEFT JOIN doctor_schedule ds ON d.doctor_id = ds.doctor_id "
                + "WHERE ds.doctor_id IS NULL";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("doctor_id");
                String username = rs.getString("username");
                String name = rs.getString("doctor_name");
                int departmentId = rs.getInt("deparment_id");

                Doctor d = new Doctor(id, username, name, getDepartmentByDoctor_department_id(departmentId));
                list.add(d);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy bác sĩ không có lịch: " + e.getMessage());
        }

        return list;
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

    public Doctor getDoctorByDoctorUsername(String username) {
        String sql = "select d.doctor_id,d.username,d.doctor_name,d.gender,d.dob,d.phone,d.deparment_id,d.address,d.img,d.description,d.position_id,d.AcademicTitle_id,d.AcademicDegree_id,d.status,d.specialized,d.EducationHistory from doctors d where d.username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
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

    public Doctor getDoctorByDoctorUsername1(String username) {
        String sql = "select d.doctor_id,d.doctor_name from doctors d where d.username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1),
                        rs.getString(2)
                );
                return d;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getDoctorIdByDoctorUsername(String username) {
        String sql = "select d.doctor_id from doctors d where d.username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public Doctor getDoctorByDoctorId1(String id) {
        String sql = "select d.doctor_id,d.doctor_name from doctors d where d.doctor_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Doctor d = new Doctor(rs.getInt(1),
                        rs.getString(2)
                );
                return d;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Doctor getDoctorByDoctorId(String id) {
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

    public void updateDoctorStatusRole(String username, int status) {
        String sql = "UPDATE doctors SET  status = ?  WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(2, username);
            ps.setInt(1, status);

            int rows = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Doctor> getDoctorsNotInService(int departmentId, int serviceId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT doctor_id, doctor_name "
                + "FROM doctors "
                + "WHERE deparment_id = ? "
                + "AND doctor_id NOT IN (SELECT doctor_id FROM doctor_service WHERE service_id = ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.setInt(2, serviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDoctor_id(rs.getInt("doctor_id"));
                d.setDoctor_name(rs.getString("doctor_name"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Doctor> getDoctorsNotInCategory(int categoryServiceId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT doctor_id, doctor_name ,deparment_id "
                + "FROM doctors "
                + "WHERE doctor_id NOT IN ("
                + "  SELECT ds.doctor_id "
                + "  FROM doctor_service ds "
                + "  JOIN service s ON ds.service_id = s.service_id "
                + "  WHERE s.category_service_id = ?"
                + ")";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, categoryServiceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDoctor_id(rs.getInt("doctor_id"));
                d.setDoctor_name(rs.getString("doctor_name"));
                d.setDepartment(getDepartmentByDoctor_department_id(rs.getInt("deparment_id")));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateDoctor(int doctorId, String doctorName, String gender, String phone, String dob,
            String description, int departmentId, int status, String specialized, String EducationHistory, int positionId, int academicDegreeId, int academicTitleId, String img) {
        String sql = "UPDATE doctors SET doctor_name = ?, gender = ?, phone = ?, dob = ?, "
                + "description = ?, deparment_id = ?,  status = ? ,specialized =?, EducationHistory =? ,position_id= ?,AcademicDegree_id=? ,AcademicTitle_id= ? ,img = ? WHERE doctor_id = ?";

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
            ps.setInt(10, positionId);
            ps.setInt(11, academicDegreeId);
            ps.setInt(12, academicTitleId);
            ps.setString(13, img);
            ps.setInt(14, doctorId);
            int rows = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void InsertDoctorByAdmin(String username, String doctor_name, String gender, int department_id, int position_id, int AcademicT_id, int AcademicDgre_id, int status, String img) {
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

    public String sang = "";

    public List<Doctor> GetListDoctor(String gender, int[] departmentIds, String sortType, int pageIndex, int pageSize) {
        List<Doctor> doctors = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (");
        sql.append("SELECT d.doctor_id, d.doctor_name, d.gender, d.dob, d.phone, d.img, d.address, d.description, COUNT(rs.ratestar_id) AS total_reviews,");
        sql.append("dp.department_name, ");
        sql.append("AVG(CAST(rs.star AS FLOAT)) AS average_rating, ");
        sql.append("ROW_NUMBER() OVER (ORDER BY ");

        switch (sortType) {
            case "rating":
                sql.append("AVG(CAST(rs.star AS FLOAT)) DESC ");
                break;
            case "popular":
                sql.append("COUNT(rs.ratestar_id) DESC ");
                break;
            case "latest":
                sql.append("d.doctor_id DESC ");
                break;
            default:
                sql.append("d.doctor_id ASC ");
                break;
        }

        sql.append(") AS RowNum ");
        sql.append("FROM doctors d ");
        sql.append("LEFT JOIN department dp ON d.deparment_id = dp.department_id ");
        sql.append("LEFT JOIN ratestar rs ON d.doctor_id = rs.doctor_id ");
        sql.append("WHERE 1=1 ");

        // Gender filter
        if (gender != null && !gender.isEmpty()) {
            sql.append("AND d.gender = ? ");
        }

        // Department filter (multiple values)
        if (departmentIds != null && departmentIds.length > 0) {
            sql.append("AND d.deparment_id IN (");
            for (int i = 0; i < departmentIds.length; i++) {
                sql.append("?");
                if (i < departmentIds.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        sql.append("GROUP BY d.doctor_id, d.doctor_name, d.gender, d.dob, d.phone, d.img, d.address, d.description, dp.department_name ");
        sql.append(") AS paged_result ");
        sql.append("WHERE RowNum BETWEEN ? AND ?");

        System.out.println("sql: " + sql.toString());
        sang = sql.toString();
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (gender != null && !gender.isEmpty()) {
                ps.setString(paramIndex++, gender);
            }

            if (departmentIds != null && departmentIds.length > 0) {
                for (int deptId : departmentIds) {
                    ps.setInt(paramIndex++, deptId);
                }
            }

            int startRow = (pageIndex - 1) * pageSize + 1;
            int endRow = pageIndex * pageSize;

            ps.setInt(paramIndex++, startRow);
            ps.setInt(paramIndex++, endRow);

            ResultSet rs = ps.executeQuery();
            PositionDAO positionDAO = new PositionDAO();
            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctor_id(rs.getInt("doctor_id"));
                //doctor.setUsername(rs.getString(2));
                doctor.setDoctor_name(rs.getString("doctor_name"));
                doctor.setGender(rs.getString("gender"));
                //doctor.setDob(rs.getDate("dob"));
                doctor.setPhone(rs.getString("phone"));
                doctor.setImg(rs.getString("img"));
                doctor.setAdress(rs.getString("address"));
                //doctor.setDescription(rs.getString("description"));
                doctor.setDepartment_name(rs.getString("department_name"));
                doctor.setAverageRateStar(rs.getFloat("average_rating"));
                doctor.setNumber_rate_star(rs.getInt("total_reviews"));
                doctor.setPosition(positionDAO.GetPositionName(doctor.getDoctor_id()));
                doctors.add(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctors;
    }

    public int GetListDoctorNumber(String gender, int[] departmentIds, String sortType) {
        //List<Doctor> doctors = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (");
        sql.append("SELECT d.doctor_id, d.doctor_name, d.gender, d.dob, d.phone, d.img, d.address, d.description, COUNT(rs.ratestar_id) AS total_reviews,");
        sql.append("dp.department_name, ");
        sql.append("AVG(CAST(rs.star AS FLOAT)) AS average_rating, ");
        sql.append("ROW_NUMBER() OVER (ORDER BY ");

        switch (sortType) {
            case "rating":
                sql.append("AVG(CAST(rs.star AS FLOAT)) DESC ");
                break;
            case "popular":
                sql.append("COUNT(rs.ratestar_id) DESC ");
                break;
            case "latest":
                sql.append("d.doctor_id DESC ");
                break;
            default:
                sql.append("d.doctor_id ASC ");
                break;
        }

        sql.append(") AS RowNum ");
        sql.append("FROM doctors d ");
        sql.append("LEFT JOIN department dp ON d.deparment_id = dp.department_id ");
        sql.append("LEFT JOIN ratestar rs ON d.doctor_id = rs.doctor_id ");
        sql.append("WHERE 1=1 ");

        // Gender filter
        if (gender != null && !gender.isEmpty()) {
            sql.append("AND d.gender = ? ");
        }

        // Department filter (multiple values)
        if (departmentIds != null && departmentIds.length > 0) {
            sql.append("AND d.deparment_id IN (");
            for (int i = 0; i < departmentIds.length; i++) {
                sql.append("?");
                if (i < departmentIds.length - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        sql.append("GROUP BY d.doctor_id, d.doctor_name, d.gender, d.dob, d.phone, d.img, d.address, d.description, dp.department_name ");
        sql.append(") AS paged_result ");
        //sql.append("WHERE RowNum BETWEEN ? AND ?");

        System.out.println("sql: " + sql.toString());
        sang = sql.toString();
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            if (gender != null && !gender.isEmpty()) {
                ps.setString(paramIndex++, gender);
            }

            if (departmentIds != null && departmentIds.length > 0) {
                for (int deptId : departmentIds) {
                    ps.setInt(paramIndex++, deptId);
                }
            }

//            int startRow = (pageIndex - 1) * pageSize + 1;
//            int endRow = pageIndex * pageSize;
//            ps.setInt(paramIndex++, startRow);
//            ps.setInt(paramIndex++, endRow);
            ResultSet rs = ps.executeQuery();
            PositionDAO positionDAO = new PositionDAO();
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

    public static void main(String[] args) {
        DoctorDAO d = new DoctorDAO();
       System.out.println(d.getDoctorsByServiceCategoryFilter(2,"Nam","all"));
        
        System.out.println(d.getDoctorsByServiceCategory(2));
//        List<Doctor> l = d.getAllDoctorInDepartmanentHaveSchedule(1);
//        for (Doctor doctor : l) {
//            System.out.println(doctor.getWorkingWeekdays().size());
//        }
//        System.out.println(d.getAllDoctorInDepartmanentHaveSchedule(1));

        //    System.out.println("scc " + d.getDoctorsNotInCategory(2));
        //   System.out.println("dsc " + d.getDoctorsNotInService(6, 12));
//        int[] department = new int[0];
//        List<Doctor> l = d.GetListDoctor("", department, "rating", 1, 2);
//        for (Doctor doctor : l) {
//            System.out.println(doctor.getDoctor_id());
//        }
    }
}
