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
public class UserDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public AccountUser Login(String username, String password) throws SQLException {
        String sql = "select u.username ,u.role_id ,u.email,u.img ,u.status from users u where u.username =? and u.password = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new AccountUser(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public AccountUser CheckAccChangePass(String username, String email) {
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

    public AccountUser CheckAccByUsername(String username) {
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
    
     public AccountUser CheckAccByUsernameOREmail(String username,String email) {
        String sql = "select u.username  from users u where u.username = ? or u.email = ? ";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2,email);
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
                return new AccountUser(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
public void registerNewUser(String username, int role, String password, String email, String img, int status) {
    String sql = "INSERT INTO users (username, role_id, password, email, img, status) VALUES (?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setInt(2, role);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setString(5, img);
        ps.setInt(6, status);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Lỗi khi thêm người dùng mới: " + e.getMessage());
    }
}

    public Role getRoleByUserRole_id(int role_id) {
        String sql = "select r.id ,r.name from roles r where r.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, role_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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

    public void Recover(String username, String password) {
        String sql = "UPDATE users\n"
                + "SET password = ?, \n"
                + "    status = 1\n"
                + "WHERE username = ?;";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(2, username);
            ps.setString(1, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateAccountByAdmin(String username, int roleId, int status) {
    String sql = "UPDATE users SET role_id = ?, status = ? WHERE username = ?";

    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, roleId);
        ps.setInt(2, status);
        ps.setString(3, username);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}
       public void updateAccountByUser(String currentUsername,String username, String email) {
    String sql = "UPDATE users SET username = ?, email = ? WHERE username = ?";

    try {
        ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, currentUsername);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}
       
      public void changePassByUser(String username, String newPassword) {
    String sql = "UPDATE users SET password = ? WHERE username = ?";

    try {
        ps = connection.prepareStatement(sql);
        ps.setString(1, newPassword);
        ps.setString(2, username);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}
    
    public void deleteAccountByAdmin(String username) {
    String sql = "DELETE FROM users WHERE username = ?";

    try {
        ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
    
}
    public List<AccountUser> getAccByFilterRoleId(String roleId){
       String sql = "select  u.username ,u.role_id,u.email,u.img ,u.status from users u where u.role_id = ?";
        List<AccountUser> list = new ArrayList<>();
        try {
            
            ps = connection.prepareStatement(sql);
            ps.setString(1, roleId);
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
    
       public List<AccountUser> getAccByFilterStatus(String status){
       String sql = "select u.username ,u.role_id,u.email,u.img ,u.status from users u where u.status = ?";
        List<AccountUser> list = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
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
    
      public List<AccountUser> getAccByFilter( String  roleId,String  status){
       String sql = "select  u.username ,u.role_id,u.email,u.img ,u.status from users u where u.status = ? and u.role_id = ?";
        List<AccountUser> list = new ArrayList<>();
        try {
            
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, roleId);
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
      
       public AccountUser getAccByUsername( String username){
       String sql = "select  u.username ,u.role_id,u.email,u.img ,u.status from users u where u.username = ?";
        try {
            
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {

              return new AccountUser(rs.getString(1),
                        getRoleByUserRole_id(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
           
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
       public boolean checkPasswordByUsername( String username,String password){
       String sql = "select  u.username from users u where u.username = ? and u.password = ?";
        try {
            
            ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
           return rs.next();
           
        } catch (SQLException e) {
            System.out.println(e);
        }
       return false;
    }
    
    
      public List<AccountUser> SearchAll(String text) {
        List<AccountUser> list = new ArrayList<>();
        String sql = "select  u.username ,u.role_id,u.email,u.img ,u.status from users u where  u.email LIKE ? OR u.username LIKE ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
              AccountUser acc = new AccountUser(rs.getString(1),
                        getRoleByUserRole_id(rs.getInt(2)),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
                list.add(acc);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
     
    
    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        AccountUser account = new AccountUser();
           List<AccountUser> list1 = dao.getAccByFilter("1","2");
           List<AccountUser> list2 = dao.getAccByFilterStatus("1");
        // System.out.println(list);
        //Role r = dao.getRoleByUserRole_id(3); 
        // System.out.println(r);
        //  System.out.println(list);
        // account = dao.CheckAcc("admin", "huydvhe186208@fpt.edu.vn");
        System.out.println(list1.size());
        System.out.println(list2.size());
        // System.out.println(dao.CheckAcc("admin"));
       // dao.deleteAccountByAdmin("doctor19");
        System.out.println(dao.checkPasswordByUsername("user10","SHV5MTNAMDAw" ));
        dao.registerNewUser("user11", 5, "Huy13@000", "huy@gmail.com", "default", 1);
    }

}
