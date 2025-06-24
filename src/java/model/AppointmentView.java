/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author DELL
 */
public class AppointmentView {
    
    private int appointmentId;
    private int slotId;               
    private String patientName;
    private String doctorName;
    private String serviceName;
    private Date workingDate;
    private Time slotStart;
    private Time slotEnd;
    private int status;
    private String note;

    public AppointmentView() {
    }

    
    
    
    public AppointmentView(int appointmentId, int slotId, String patientName, String doctorName, String serviceName, Date workingDate, Time slotStart, Time slotEnd, int status, String note) {
        this.appointmentId = appointmentId;
        this.slotId = slotId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceName = serviceName;
        this.workingDate = workingDate;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.status = status;
        this.note = note;
    }

    
    
    
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Date getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(Date workingDate) {
        this.workingDate = workingDate;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    

}
