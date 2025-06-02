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
import model.AccountUser;
import model.Role;


/**
 *
 * @author DELL
 */
public class UserDAO  extends DBContext{
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    public AccountUser Login(String username ,String password) throws SQLException{
         String sql = "select u.username ,u.role_id ,u.password,u.email,u.img ,u.status from users u where u.username =? and u.password = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1) ,rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }
    public AccountUser CheckAcc(String username ,String email) {
         String sql = "select u.username  from users u where u.username = ? or u.email =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1) ,rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public AccountUser CheckAccByUsername(String username ) {
         String sql = "select u.username  from users u where u.username = ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public AccountUser CheckAccByEmail(String email) {
         String sql = "select u.email  from users u where  u.email = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1) );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
     public void RegisterNewUser(String username, int  role,String password,String email,String img ,int status) {
         String sql = "insert into users values (?,?,?,?,?,?);";
        try { 
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, role);
            ps.setString(3, password);
            ps.setString(4, email);
            ps.setString(5, img);
            ps.setInt(6, status);
            ps.executeUpdate();
           
        } catch (SQLException e) {
            System.out.println(e);
        } 
    }
     
     public Role getRoleByUserRole_id(int role_id){
          String sql = "select r.id ,r.name from roles r where r.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role_id);
            ResultSet rs =  ps.executeQuery();
            while(rs.next()){
                return new Role(rs.getInt(1), rs.getString(2));
            }
           
        } catch (SQLException e) {
            System.out.println(e);
        }
         return null;
     }
      public List<AccountUser> getListAllAccount() {
         String sql = "select u.username ,u.role_id,u.email,u.img ,u.status from users u";
         List<AccountUser> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
             
                AccountUser acc = new AccountUser(rs.getString(1), 
                        getRoleByUserRole_id(rs.getInt(2)), 
                        rs.getString(3), 
                        rs.getString(4),
                        rs.getInt(5));
                list.add(acc);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } 
        return null;
    }
         public AccountUser CheckAccChangePass(String username ,String email) {
         String sql = "select u.username ,u.email,u.role_id from users u where u.username = ? and u.email =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1), rs.getString(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
       public void Recover(String username, String password)  {
        String sql = "UPDATE users\n" +
"SET password = ?, \n" +
"    status = 1\n" +
"WHERE username = ?;";
         
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(2, username);
            ps.setString(1, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    
    public static void main(String[] args) throws SQLException {
         UserDAO dao =  new UserDAO();
         AccountUser account =  new AccountUser();
              List<AccountUser> list = dao.getListAllAccount();
              System.out.println(list);
           Role r = dao.getRoleByUserRole_id(3);
           System.out.println(r);
           System.out.println(list);
        // System.out.println(dao.CheckAcc("admin"));
        
    }
    
    
    
}
