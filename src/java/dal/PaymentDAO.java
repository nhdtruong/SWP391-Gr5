/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author DELL
 */
public class PaymentDAO extends DBContext {

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
    public void updateAppointmentIdForPaymentSuccess( int appointmentId ,String txnRef , String vnp_TransactionNo)  {
    String sql = "UPDATE payment SET status = 'success',appointment_id = ?, vnp_transaction_no  = ?  WHERE vnp_txn_ref = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, appointmentId);
        ps.setString(2, vnp_TransactionNo);
        ps.setString(3, txnRef);
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println(e);
    }
}
 
    public void updatePaymentStatusFailed(String txnRef)  {
    String sql = "UPDATE payment SET status = 'failed' WHERE vnp_txn_ref = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, txnRef);
        ps.executeUpdate();
    }catch(SQLException e){
        System.out.println(e);
    }
}
    
    
}
