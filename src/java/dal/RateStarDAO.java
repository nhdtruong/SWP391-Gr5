/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.util.ArrayList;
import java.util.List;
import model.RateStar;
import context.DBContext;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.AbstractList;
/**
 *
 * @author dantr
 */
public class RateStarDAO extends DBContext{
    public List<RateStar> getAllRateStarByDoctorId(int doctorId) {
    List<RateStar> rateStars = new ArrayList<>();
    String sql = """
        SELECT 
            r.ratestar_id,
            r.star,
            r.feedback,
            p.patient_name,
            s.service_name
        FROM ratestar r
        JOIN patients p ON r.patient_id = p.patient_id
        JOIN service s ON r.service_id = s.service_id
        WHERE r.doctor_id = ?
    """;

    try (
         PreparedStatement stmt = connection.prepareStatement(sql)) {

        stmt.setInt(1, doctorId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RateStar rate = new RateStar();
            rate.setRatestarId(rs.getInt("ratestar_id"));
            rate.setStar(rs.getInt("star"));
            rate.setFeedback(rs.getString("feedback"));
            rate.setPatientName(rs.getString("patient_name"));
            rate.setService_Name(rs.getString("service_name"));

            rateStars.add(rate);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return rateStars;
}

    
}
