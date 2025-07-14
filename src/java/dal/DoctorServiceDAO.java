/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Deparment;

/**
 *
 * @author DELL
 */
public class DoctorServiceDAO extends DBContext {

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

    public List<Doctor> getDoctorsByService( int serviceId) {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.doctor_id, d.doctor_name ,d.deparment_id FROM doctor_service ds "
                + "JOIN doctors d ON ds.doctor_id = d.doctor_id "
                + "WHERE ds.service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, serviceId);
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
    
    
    public Doctor getDoctorByService( int serviceId) {
        
        String sql = "SELECT d.doctor_id, d.doctor_name ,d.deparment_id FROM doctor_service ds "
                + "JOIN doctors d ON ds.doctor_id = d.doctor_id "
                + "WHERE ds.service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, serviceId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDoctor_id(rs.getInt("doctor_id"));
                d.setDoctor_name(rs.getString("doctor_name"));
                d.setDepartment(getDepartmentByDoctor_department_id(rs.getInt("deparment_id")));
                return  d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public boolean removeDoctorFromService(int doctorId, int serviceId) {
        String sql = "DELETE FROM doctor_service WHERE doctor_id = ? AND service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, serviceId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     public boolean removeDoctorServiceByServiceId( int serviceId) {
        String sql = "DELETE FROM doctor_service WHERE  service_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, serviceId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addDoctorToService(int doctorId, int serviceId) {
        String sql = "INSERT INTO doctor_service (doctor_id, service_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            ps.setInt(2, serviceId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        DoctorServiceDAO d = new DoctorServiceDAO();

    }

}
