/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOStatic;

/**
 *
 * @author Admin
 */
public class StaticDepartment {
    private int id;
    private String department_name;
    private int number;

    public StaticDepartment() {
    }

    public StaticDepartment(int id, String department_name, int number) {
        this.id = id;
        this.department_name = department_name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
