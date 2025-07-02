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
    private String appointment_code;
    private int slotId;
    private String patientName;
    private String doctorName;
    private double amount;
    private String paymentStatus;
    private String serviceName;
    private String departmentName;
    private Date workingDate;
    private Date created_at;
    private boolean is_refunded;
    private Time slotStart;
    private Time slotEnd;
    private int status;
    private String note;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public AppointmentView(int appointmentId, String appointment_code, int slotId, String patientName, String doctorName, String serviceName, String departmentName, Date workingDate, Date created_at, boolean is_refunded, Time slotStart, Time slotEnd, int status, String note) {
        this.appointmentId = appointmentId;
        this.appointment_code = appointment_code;
        this.slotId = slotId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceName = serviceName;
        this.departmentName = departmentName;
        this.workingDate = workingDate;
        this.created_at = created_at;
        this.is_refunded = is_refunded;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.status = status;
        this.note = note;
    }

    public AppointmentView(int appointmentId, String appointment_code, int slotId, String patientName, String doctorName,
            String serviceName, String departmentName, Date workingDate, Date created_at, boolean is_refunded,
            Time slotStart, Time slotEnd, int status, String note,
            double amount, String paymentStatus) {
        this.appointmentId = appointmentId;
        this.appointment_code = appointment_code;
        this.slotId = slotId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceName = serviceName;
        this.departmentName = departmentName;
        this.workingDate = workingDate;
        this.created_at = created_at;
        this.is_refunded = is_refunded;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.status = status;
        this.note = note;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAppointment_code() {
        return appointment_code;
    }

    public void setAppointment_code(String appointment_code) {
        this.appointment_code = appointment_code;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public boolean isIs_refunded() {
        return is_refunded;
    }

    public void setIs_refunded(boolean is_refunded) {
        this.is_refunded = is_refunded;
    }

    public AppointmentView() {
    }

    public AppointmentView(int appointmentId, String appointment_code, int slotId, String patientName, String doctorName, String serviceName, Date workingDate, Date created_at, boolean is_refunded, Time slotStart, Time slotEnd, int status, String note) {
        this.appointmentId = appointmentId;
        this.appointment_code = appointment_code;
        this.slotId = slotId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.serviceName = serviceName;
        this.workingDate = workingDate;
        this.created_at = created_at;
        this.is_refunded = is_refunded;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.status = status;
        this.note = note;
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
