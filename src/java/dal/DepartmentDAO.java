/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DTOStatic.StaticDepartment;
import context.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.Deparment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class DepartmentDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getDepartmentIdByDoctorId(int doctorId) {
    String sql = "SELECT deparment_id FROM doctors WHERE doctor_id = ?";
    
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, doctorId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("deparment_id");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return -1;
}
    
    public List<Deparment> getAllDeparment() {
        List<Deparment> list = new ArrayList<>();
        String sql = "SELECT de.department_id ,de.department_name ,de.img\n"
                + "FROM department de\n"
                + "WHERE department_name <> N'Đa Khoa';";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Deparment de = new Deparment(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(de);
            }
            return list;

        } catch (Exception e) {
        }

        return null;
    }
    
    
    public int CountDoctorInDepartment(int depId){
        String sql = "  select Count (*) from doctors where deparment_id ="+ depId;
        
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            return 0;

        } catch (Exception e) {
        }

        return 0;
    }

    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<StaticDepartment> staticDepartments = new ArrayList<>();
        var listDep = departmentDAO.getAllDeparment();
        for (Deparment dep : listDep) {
            StaticDepartment sd = new StaticDepartment();
            sd.setId(dep.getId());
            sd.setDepartment_name(dep.getDepartment_name());
            sd.setNumber(departmentDAO.CountDoctorInDepartment(dep.getId()));
            
            staticDepartments.add(sd);
        }
        for (var deparment : staticDepartments) {
            System.out.println(deparment.getId());
            System.out.println(deparment.getDepartment_name());
            System.out.println(deparment.getNumber());
        }
    }

}
