/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Appointment {
    private int appointmentId;
    private String appointmentCode ;
    private int patientId;
    private int slotId;
    private int serviceId;
    private Date createAt;
    private int status;         
    private String note;

    public Appointment(int appointmentId, String appointmentCode, int patientId, int slotId, int serviceId, Date createAt, int status, String note) {
        this.appointmentId = appointmentId;
        this.appointmentCode = appointmentCode;
        this.patientId = patientId;
        this.slotId = slotId;
        this.serviceId = serviceId;
        this.createAt = createAt;
        this.status = status;
        this.note = note;
    }

    public String getAppointmentCode() {
        return appointmentCode;
    }

    public void setAppointmentCode(String appointmentCode) {
        this.appointmentCode = appointmentCode;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    
    
    public Appointment() {
    }

    public Appointment(int appointmentId, int patientId, int slotId, int serviceId, int status, String note) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.slotId = slotId;
        this.serviceId = serviceId;
        this.status = status;
        this.note = note;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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
