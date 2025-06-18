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
public class Doctor {
    
    
    private int doctor_id;
    private int role_id;
    private String username;
    private String doctor_name;
    private String  gender;
    private Deparment department;
    private Date DOB;
    private String  phone;
    private String email;
    private String description;
    private int status;
    private String img;
    private RateStar rateStar;
    private AcademicDegree academicDegree;
    private AcademicTitle academicTitle;
    private Position position;
    private String specialized;
    private String EducationHistory;
    
    
    public String getSpecialized() {
        return specialized;
    }

    public void setSpecialized(String specialized) {
        this.specialized = specialized;
    }

    public String getEducationHistory() {
        return EducationHistory;
    }

    public void setEducationHistory(String EducationHistory) {
        this.EducationHistory = EducationHistory;
    }
    private String adress;

  

    public Doctor(String doctor_name, Deparment department, String img, AcademicDegree academicDegree, AcademicTitle academicTitle, Position position) {
        this.doctor_name = doctor_name;
        this.department = department;
        this.img = img;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.position = position;
    }

   public Doctor(int doctor_id, String username ,String doctor_name, String gender, Date DOB, String phone, Deparment department, String adress, String img, String description, Position position, AcademicTitle academicTitle, AcademicDegree academicDegree, int status,String specialized ,String EducationHistory,String email) {
        this.doctor_id = doctor_id;
        this.username = username;
        this.doctor_name = doctor_name;
        this.gender = gender;
        this.department = department;
        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.status = status;
        this.img = img;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.position = position;
        this.specialized = specialized;
        this.EducationHistory= EducationHistory;
        this.adress = adress;
    }

 
       public Doctor(int doctor_id, String doctor_name, String gender, Date DOB, String phone, Deparment department, String adress, String img, String description, Position position, AcademicTitle academicTitle, AcademicDegree academicDegree, int status,String specialized ,String EducationHistory,String email) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.gender = gender;
        this.department = department;
        this.DOB = DOB;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.status = status;
        this.img = img;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.position = position;
        this.specialized = specialized;
        this.EducationHistory= EducationHistory;
        this.adress = adress;
    }


    public Doctor(int doctor_id, int role_id, String doctor_name, String gender, Deparment department, Date DOB, String phone, String description, int status, String img, RateStar rateStar, AcademicDegree academicDegree, AcademicTitle academicTitle, Position position, String adress) {
        this.doctor_id = doctor_id;
        this.role_id = role_id;
        this.doctor_name = doctor_name;
        this.gender = gender;
        this.department = department;
        this.DOB = DOB;
        this.phone = phone;
        this.description = description;
        this.status = status;
        this.img = img;
        this.rateStar = rateStar;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.position = position;
        this.adress = adress;
    }

    

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Deparment getDepartment() {
        return department;
    }

    public void setDepartment(Deparment department) {
        this.department = department;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public RateStar getRateStar() {
        return rateStar;
    }

    public void setRateStar(RateStar rateStar) {
        this.rateStar = rateStar;
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        this.academicDegree = academicDegree;
    }

    public AcademicTitle getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitle academicTitle) {
        this.academicTitle = academicTitle;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

  
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

     
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctor_id=" + doctor_id + ", role_id=" + role_id + ", doctor_name=" + doctor_name + ", gender=" + gender + ", department=" + department + ", DOB=" + DOB + ", phone=" + phone + ", email=" + email + ", description=" + description + ", status=" + status + ", img=" + img + ", rateStar=" + rateStar + ", academicDegree=" + academicDegree + ", academicTitle=" + academicTitle + ", position=" + position + ", specialized=" + specialized + ", EducationHistory=" + EducationHistory + ", adress=" + adress + '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
  
    
    
}
