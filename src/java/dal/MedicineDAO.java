/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.Medicine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MedicineDAO extends DBContext {

    
    public List<Medicine> GetMedicineByRecord_id(int record_id){
        List<Medicine> list = new ArrayList<>();
        String sql = "  select m.* from prescription_medicine ps left join medicine m on ps.medicine_id = m.medicine_id where ps.record_id = "+record_id;

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine m = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getString("unit"),
                        rs.getString("usage"),
                        rs.getString("image")
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT medicine_id, medicine_name, unit, usage, image FROM medicine";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine m = new Medicine(
                        rs.getInt("medicine_id"),
                        rs.getString("medicine_name"),
                        rs.getString("unit"),
                        rs.getString("usage"),
                        rs.getString("image")
                );
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
