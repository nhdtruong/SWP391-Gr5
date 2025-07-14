/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class MedicalRecord {

    private int recordId;
    private int patientId;
    private int doctorId;
    private String symptoms;
    private String diagnosis;
    private String conclusion;
    private String instruction;
    private String note;
    private Date visitDate;
    private Patient patient;
    private String doctorName;
    private String departmentName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private List<Medicine> medicines;

    public MedicalRecord() {
    }

    public MedicalRecord(int recordId, int patientId, int doctorId, String symptoms, String diagnosis, String conclusion, String instruction, String note, Date visitDate, Patient patient, String doctorName, String departmentName, List<Medicine> medicines) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.conclusion = conclusion;
        this.instruction = instruction;
        this.note = note;
        this.visitDate = visitDate;
        this.patient = patient;
        this.doctorName = doctorName;
        this.departmentName = departmentName;
        this.medicines = medicines;
    }

    
    
    public MedicalRecord(int recordId, int patientId, int doctorId, String symptoms, String diagnosis, String conclusion, String instruction, String note, Date visitDate, Patient patient, List<Medicine> medicines) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.conclusion = conclusion;
        this.instruction = instruction;
        this.note = note;
        this.visitDate = visitDate;
        this.patient = patient;
        this.medicines = medicines;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

}
