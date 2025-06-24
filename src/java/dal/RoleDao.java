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
import model.Role;

/**
 *
 * @author DELL
 */
public class RoleDao  extends DBContext{
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Role> getListRole(){
        String sql = "select * from roles";
        List<Role> list= new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql); 
            rs = ps.executeQuery();   
            while (rs.next()) {
                Role role=  new Role(rs.getInt(1), rs.getString(2));
                list.add(role);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
}
