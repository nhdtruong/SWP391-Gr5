/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class AccountUser {
    
    private String username;
    private String password;
    private String email;
    private String img;
    private int status;
    private int role;
    private Role ORole;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public AccountUser() {
    }

    
    
  
 public AccountUser(String email) {
        this.email = email;
    
    }
 
    public AccountUser(String username, String email) {
        this.username = username;
        this.email = email;
    }

      public AccountUser(String username,String password, int role ,String email, String img, int status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.img = img;
        this.status = status;
        this.role = role;
    }
   
    
    public AccountUser(String username, int role, String password, String email, String img, int status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.img = img;
        this.status = status;
        this.role = role;
    }

 
    public AccountUser(String username, String email, int role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    

    public AccountUser(String username,Role Role, String password , String email, String img, int status) {
        this.username = username;
        this.password = password;        
        this.email = email;
        this.img = img;
        this.status = status;
        this.ORole= Role;
    }

    public AccountUser(String username, Role ORole, String email, String img, int status) {
        this.username = username;
        this.email = email;
        this.img = img;
        this.status = status;
        this.ORole = ORole;
    }

    public AccountUser(String username , int role, String password, String name, String gender, String email, String img) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.img = img;
        this.role = role;
    }
    
 
    public AccountUser(String username, String password, String name, String gender, String email, String img) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.img = img;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

 

    public int getRole() {
        return role;
    }

    public void setRole_(int role) {
        this.role= role;
    }

    
    public Role getORole() {
        return ORole;
    }

    public void setORole(Role ORole) {
        this.ORole = ORole;
    }

    
    
}
