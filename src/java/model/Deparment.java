/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Deparment {
    private int id;
    private String department_name;
    private String img;

    public Deparment() {
    }

    public Deparment(int id, String department_name) {
        this.id = id;
        this.department_name = department_name;
    }
    
    

    public Deparment(String department_name, String img) {
        this.department_name = department_name;
        this.img = img;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Deparment(int id, String department_name, String img) {
        this.id = id;
        this.department_name = department_name;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
}
