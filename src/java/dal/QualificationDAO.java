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
import model.Position;
import model.AcademicDegree;
import model.AcademicTitle;


/**
 *
 * @author DELL
 */
public class QualificationDAO extends DBContext{
        PreparedStatement ps = null;
     ResultSet rs = null;
     
     public List<Position> getAllPosition(){
        List<Position> list = new ArrayList<>();
        String sql = "select * from Position" ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Position   p = new Position(rs.getInt(1),rs.getString(2));
                list.add(p);
            }
            return list;
            
        } catch (SQLException e) {
        }
        
        return null;
    }
     
       public List<AcademicDegree> getAllAcademicDegree(){
        List<AcademicDegree> list = new ArrayList<>();
        String sql = "select * from AcademicDegree" ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                AcademicDegree   ad = new AcademicDegree(rs.getInt(1),rs.getString(2));
                list.add(ad);
            }
            return list;
            
        } catch (SQLException e) {
        }
        
        return null;
    }
     
     public List<AcademicTitle> getAllAcademicTitle(){
        List<AcademicTitle> list = new ArrayList<>();
        String sql = "select * from AcademicTitle" ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                AcademicTitle   at = new AcademicTitle(rs.getInt(1), rs.getString(2));
                list.add(at);
            }
            return list;
            
        } catch (SQLException e) {
        }
        
        return null;
    }
     
     public static void main(String[] args){ 
         DepartmentDAO dao =  new DepartmentDAO();
    
         
     }
      
     
     
   
     
}
