/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java. sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class WorkingDateSchedule {
    private Date workingDate;
    private List<Shifts> shifts = new ArrayList<>();
    private int status; 
    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public List<Shifts> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shifts> shifts) {
        this.shifts = shifts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

}
