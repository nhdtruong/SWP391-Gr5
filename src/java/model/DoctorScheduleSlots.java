/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.text.SimpleDateFormat;

/**
 *
 * @author DELL
 */
public class DoctorScheduleSlots {

    private int shift;
    private Time start;
    private Time end;

    public DoctorScheduleSlots(int shift, Time start, Time end) {
        this.shift = shift;
        this.start = start;
        this.end = end;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public String toFormValue() {
        return shift + "_" + start.toString() + "-" + end.toString();
    }

 
   
}
