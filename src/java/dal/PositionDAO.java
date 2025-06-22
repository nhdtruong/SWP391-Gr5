/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;
import model.Position;

/**
 *
 * @author DELL
 */
public class PositionDAO extends DBContext {

    public List<Position> getAllPosition() {
        String sql = "select * from position ";
        List<Position> listPo = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position po = new Position(rs.getInt(1), rs.getString(2));
                listPo.add(po);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return listPo;
    }
    
    public Position GetPositionName(int doctor_id){
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.getDoctorByDoctorId(doctor_id+"");
        if(doctor==null){
            return  null;
        }
        int positionid = doctor.getPosition().getId();
        
        
        String sql = "select * from position where position_id =  "+positionid;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Position po = new Position(rs.getInt(1), rs.getString(2));
                return po;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    public static void main(String[] args) {
        PositionDAO d = new PositionDAO();
        System.out.println(d.getAllPosition());
    }
}
