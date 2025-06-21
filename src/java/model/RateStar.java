/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class RateStar {

    private int ratestarId;
    private Integer patientId;
    private Integer doctorId;
    private Integer serviceId;
    private Integer star;
    private String feedback;
    
    private String Service_Name;
    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    

    public RateStar() {
    }

    public RateStar(int ratestarId, Integer patientId, Integer doctorId, Integer serviceId, Integer star, String feedback, String Service_Name) {
        this.ratestarId = ratestarId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.serviceId = serviceId;
        this.star = star;
        this.feedback = feedback;
        this.Service_Name = Service_Name;
    }

    public String getService_Name() {
        return Service_Name;
    }

    public void setService_Name(String Service_Name) {
        this.Service_Name = Service_Name;
    }

    public int getRatestarId() {
        return ratestarId;
    }

    public void setRatestarId(int ratestarId) {
        this.ratestarId = ratestarId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
