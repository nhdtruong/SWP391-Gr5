/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

/**
 *
 * @author DELL
 */
public class WorkingDateSchedule {

    private Date workingDate;
    private List<Slot> slots = new ArrayList<>();
    private int status;

    public WorkingDateSchedule(Date workingDate, int status) {
        this.workingDate = workingDate;
        this.status = status;
    }

    public WorkingDateSchedule() {
       
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Slot> getMorningSlots() {
        List<Slot> result = new ArrayList<>();
        for (Slot s : slots) {
            LocalTime start = s.getSlotStart().toLocalTime();
            if (start.isBefore(LocalTime.NOON)) { 
                result.add(s);
            }
        }
        return result;
    }

    public List<Slot> getAfternoonSlots() {
        List<Slot> result = new ArrayList<>();
        for (Slot s : slots) {
            LocalTime start = s.getSlotStart().toLocalTime();
            if (!start.isBefore(LocalTime.NOON) && start.isBefore(LocalTime.of(18, 0))) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Slot> getEveningSlots() {
        List<Slot> result = new ArrayList<>();
        for (Slot s : slots) {
            LocalTime start = s.getSlotStart().toLocalTime();
            if (!start.isBefore(LocalTime.of(18, 0))) { 
                result.add(s);
            }
        }
        return result;
    }
    
     @Override
    public String toString() {
        return "WorkingDateSchedule{" +
                "workingDate=" + workingDate +
                ", status=" + status +
                ", slots=" + slots +
                '}';
    }

}
