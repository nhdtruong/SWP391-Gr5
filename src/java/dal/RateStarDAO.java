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
public class RateStarDAO extends DBContext {

    public boolean insertRateStar(int patientId, int doctorId, int star, String feedback) {
        String sql = "INSERT INTO ratestar (patient_id, doctor_id, star, feedback) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setInt(3, star);
            ps.setString(4, feedback);

            int rows = ps.executeUpdate();
            return rows > 0; // thành công nếu có ít nhất 1 dòng được insert
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RateStar> getAllRateStarByDoctorId2(int doctorId) {
        List<RateStar> rateStars = new ArrayList<>();
        String sql = """
        SELECT 
            r.ratestar_id,
            r.star,
            r.feedback,
            p.patient_name
        FROM ratestar r
        JOIN patients p ON r.patient_id = p.patient_id
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

                rateStars.add(rate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rateStars;
    }

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
