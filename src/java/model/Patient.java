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
public class Patient {
    
    private int patientId;
    private String username;
    private String patientName;
    private String gender;
    private Date dob; 
    private String job;
    private String phone;
    private String email;
    private String bhyt;
    private String nation;
    private String cccd;
    private String address;
    
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public Patient() {
    }

    public Patient(String patientName, String gender, Date dob, String job, String phone, String email, String bhyt, String nation, String cccd, String address) {
        this.patientName = patientName;
        this.gender = gender;
        this.dob = dob;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.bhyt = bhyt;
        this.nation = nation;
        this.cccd = cccd;
        this.address = address;
    }
    
    
    

    public Patient(int patientId, String patientName, String gender, Date dob, String job, String phone, String email, String bhyt, String nation, String cccd, String address) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.gender = gender;
        this.dob = dob;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.bhyt = bhyt;
        this.nation = nation;
        this.cccd = cccd;
        this.address = address;
    }
    
    public Patient(int patientId, String username, String patientName, String gender, Date dob, String job, String phone, String email, String bhyt, String nation, String cccd, String address) {
        this.patientId = patientId;
        this.username = username;
        this.patientName = patientName;
        this.gender = gender;
        this.dob = dob;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.bhyt = bhyt;
        this.nation = nation;
        this.cccd = cccd;
        this.address = address;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBhyt() {
        return bhyt;
    }

    public void setBhyt(String bhyt) {
        this.bhyt = bhyt;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
