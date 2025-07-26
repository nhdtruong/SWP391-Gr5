/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOStatic;

/**
 *
 * @author Admin
 */
public class AppointmentStat {

    public int year;
    public int month;
    public int total;

    public AppointmentStat() {
    }

    public AppointmentStat(int year, int month, int total) {
        this.year = year;
        this.month = month;
        this.total = total;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AppointmentStat{" + "year=" + year + ", month=" + month + ", total=" + total + '}';
    }

}
