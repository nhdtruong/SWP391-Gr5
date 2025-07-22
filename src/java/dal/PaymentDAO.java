/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
public class PaymentDAO extends DBContext {

    public List<Map<String, Object>> getRevenueByMonth(int year) {
        List<Map<String, Object>> revenueList = new ArrayList<>();

        String sql = "SELECT MONTH(pay_date) AS month, SUM(amount) AS total_revenue "
                + "FROM payment "
                + "WHERE status = 'success' AND YEAR(pay_date) = ? "
                + "GROUP BY MONTH(pay_date) "
                + "ORDER BY MONTH(pay_date)";

        Map<Integer, Double> dataMap = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int month = rs.getInt("month");
                    double revenue = rs.getDouble("total_revenue");
                    dataMap.put(month, revenue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Lấy tháng hiện tại nếu là năm hiện tại
        int maxMonth = 12;
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int currentYear = cal.get(java.util.Calendar.YEAR);
        if (year == currentYear) {
            maxMonth = cal.get(java.util.Calendar.MONTH) + 1; // Calendar.MONTH đếm từ 0
        }

        // Tạo đủ tháng từ 1 đến maxMonth
        for (int m = 1; m <= maxMonth; m++) {
            Map<String, Object> item = new HashMap<>();
            item.put("month", m);
            item.put("revenue", dataMap.getOrDefault(m, 0.0));
            revenueList.add(item);
        }

        return revenueList;
    }

    public double getTotalRevenue() {
        double total = 0.0;

        String sql = "SELECT SUM(amount) AS total_revenue FROM payment WHERE status = 'success'";

        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total_revenue");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return total;
    }

    public List<Map<String, Object>> GetStatic() {
        List<Map<String, Object>> revenueList = new ArrayList<>();

        String sql = "SELECT YEAR(pay_date) AS year, SUM(amount) AS total_revenue "
                + "FROM payment WHERE status='success' "
                + "GROUP BY YEAR(pay_date) ORDER BY YEAR(pay_date)";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> item = new HashMap<>();
                item.put("year", rs.getInt("year"));
                item.put("revenue", rs.getDouble("total_revenue"));
                revenueList.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenueList;
    }

    public void insertPayment(Integer appointmentId, double amount, String method, String status,
            String vnpTxnRef, String vnpTransactionNo, String note) {
        String sql = "INSERT INTO payment (appointment_id, amount, method, status, vnp_txn_ref, vnp_transaction_no, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            if (appointmentId != null) {
                ps.setInt(1, appointmentId);
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
            }

            ps.setDouble(2, amount);
            ps.setString(3, method);
            ps.setString(4, status);
            ps.setString(5, vnpTxnRef);
            ps.setString(6, vnpTransactionNo);
            ps.setString(7, note);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi insert payment: " + e.getMessage());
        }
    }

    public void updateAppointmentIdForPaymentSuccess(int appointmentId, String txnRef, String vnp_TransactionNo) {
        String sql = "UPDATE payment SET status = 'success',appointment_id = ?, vnp_transaction_no  = ?  WHERE vnp_txn_ref = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, appointmentId);
            ps.setString(2, vnp_TransactionNo);
            ps.setString(3, txnRef);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePaymentStatusFailed(String txnRef) {
        String sql = "UPDATE payment SET status = 'failed' WHERE vnp_txn_ref = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, txnRef);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
