/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Time;
/**
 *
 * @author DELL
 */
public class Slot {
    
    private int slotId;
    private Time slotStart;
    private Time slotEnd;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Time getSlotStart() {
        return slotStart;
    }

    public void setSlotStart(Time slotStart) {
        this.slotStart = slotStart;
    }

    public Time getSlotEnd() {
        return slotEnd;
    }

    public void setSlotEnd(Time slotEnd) {
        this.slotEnd = slotEnd;
    }
    
    @Override
    public String toString() {
        return "Slot{" +
                "slotId=" + slotId +
                ", slotStart=" + slotStart +
                ", slotEnd=" + slotEnd +
                ", status=" + status +
                '}';
    }

}
