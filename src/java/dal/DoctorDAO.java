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
public class DoctorDAO extends DBContext{
    
     PreparedStatement ps = null;
     ResultSet rs = null;
    
    public List<Doctor> getTop6Doctor(){
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_name,d.deparment_id,d.img,d.AcademicDegree_id ,d.AcademicTitle_id ,d.position_id\n" +
"from doctors d " ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Doctor d = new Doctor(rs.getString(1), 
                        getDepartmentByDoctor_department_id(rs.getInt(2)), 
                       rs.getString(3), getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt(4)), 
                        getAcademictitleByDoctor_Academictile_id(rs.getInt(5)), 
                        getPositionByDoctor_position_id(rs.getInt(6)));
              
                list.add(d);
            }
            return list;
            
        } catch (Exception e) {
        }
        
        return null;
    } 
    
    public Position getPositionByDoctor_position_id(int id){
        
        String sql = "select * from position p where p.position_id = ?;";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Position(rs.getInt(1),rs.getString(2));
            }
            
            
        } catch (Exception e) {
        }
        
        return null;
    } 
    
        public Deparment getDepartmentByDoctor_department_id(int id){
        
        String sql = "select d.department_id, d.department_name from department d where d.department_id = ?;";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Deparment(rs.getInt(1),rs.getString(2));
            }
            
            
        } catch (Exception e) {
        }
        
        return null;
    } 
       public  AcademicDegree getAcademicDegreeByDoctor_AcademicDegre_id(int id){
        
        String sql = "select * from AcademicDegree a where a.AcademicDegree_id =?;";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new AcademicDegree(rs.getInt(1),rs.getString(2));
            }
            
            
        } catch (Exception e) {
        }
        
        return null;
    } 
       public AcademicTitle  getAcademictitleByDoctor_Academictile_id(int id){
        
        String sql = "select * from AcademicTitle a where a.AcademicTitle_id = ?;";
        try {
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new AcademicTitle(rs.getInt(1),rs.getString(2));
            }
            
            
        } catch (Exception e) {
        }
        
        return null;
    } 
    
        
        
        
    public List<Doctor> getAllDoctorByAdmin(){
        List<Doctor> list = new ArrayList<>();
        String sql = "select d.doctor_id,d.doctor_name,d.gender,d.deparment_id,d.dob,d.phone,d.email,d.description,d.status,d.img,d.AcademicDegree_id,d.AcademicTitle_id,d.position_id,d.address from doctors d" ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
               Doctor d = new Doctor(rs.getInt(1),
                       rs.getString(2),
                       rs.getString(3),
                       getDepartmentByDoctor_department_id(rs.getInt(4)), 
                       rs.getDate(5), 
                       rs.getString(6),
                       rs.getString(7),
                       rs.getString(8), 
                       rs.getInt(9), 
                       rs.getString(10), 
                       getAcademicDegreeByDoctor_AcademicDegre_id(rs.getInt(11)),
                       getAcademictitleByDoctor_Academictile_id(rs.getInt(12)),
                       getPositionByDoctor_position_id(rs.getInt(13)), 
                       rs.getString(14));
               list.add(d);
            }
            return list;
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return null;
    } 
    
    public static void main(String[] args) {
        DoctorDAO d = new DoctorDAO();
        List<Doctor> l = d.getAllDoctorByAdmin();
        System.out.println(l);
        
    }
}


