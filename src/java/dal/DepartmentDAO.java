/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
public class DepartmentDAO extends DBContext{
    PreparedStatement ps = null;
     ResultSet rs = null;
     
     public List<Deparment> getAllDeparment(){
        List<Deparment> list = new ArrayList<>();
        String sql = "SELECT de.department_id ,de.department_name ,de.img\n" +
"FROM department de\n" +
"WHERE department_name <> N'Đa Khoa';" ;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Deparment   de = new Deparment(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(de);
            }
            return list;
            
        } catch (Exception e) {
        }
        
        return null;
    }
     
     public static void main(String[] args){ 
         DepartmentDAO dao =  new DepartmentDAO();
          List<Deparment> listDe = dao.getAllDeparment();
          System.out.println(listDe);
     }
    
     
     
}

