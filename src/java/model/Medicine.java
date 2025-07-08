/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class Medicine {
    
    private int medicineId;
    private String medicineName;
    private String unit;       
    private String usage;    
    private String image;

    public Medicine(int medicineId, String medicineName, String unit, String usage, String image) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.unit = unit;
        this.usage = usage;
        this.image = image;
    }

    public Medicine() {
    }
    
    
    

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

  
    
    
    
}
