/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vnpay.common;

import config.GenerateAppoinmentCode;
import dal.AppointmentDAO;
import dal.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import model.Patient;
import model.Service;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author HP
 */
@WebServlet(name = "VnpayReturn", urlPatterns = {"/bookingResult"})
public class VnpayReturn extends HttpServlet {
    // OrderDao orderDao = new OrderDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Map fields = new HashMap();
            for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
                String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    fields.put(fieldName, fieldValue);
                }
            }

            String vnp_SecureHash = request.getParameter("vnp_SecureHash");
            if (fields.containsKey("vnp_SecureHashType")) {
                fields.remove("vnp_SecureHashType");
            }
            if (fields.containsKey("vnp_SecureHash")) {
                fields.remove("vnp_SecureHash");
            }
            String signValue = Config.hashAllFields(fields);
            if (signValue.equals(vnp_SecureHash)) {

                String txnRef = request.getParameter("vnp_TxnRef");           // mã đơn hàng  đã sinh
                String amountStr = request.getParameter("vnp_Amount");        // số tiền * 100 (VNPay quy định)
                double amount = Double.parseDouble(amountStr) / 100.0;
                String vnp_TransactionNo = request.getParameter("vnp_TransactionNo");

                boolean transSuccess = false;
                PaymentDAO paymentDAO = new PaymentDAO();
                if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                    HttpSession session = request.getSession();
                    PaymentDAO payDAO = new PaymentDAO();
                    AppointmentDAO appointmentDao = new AppointmentDAO();

                    String token = (String) session.getAttribute("token");
                    Patient p = (Patient) session.getAttribute("patient");
                    Service s = (Service) session.getAttribute("serviceBooking");
                    String reason = (String) session.getAttribute("reason");
                    Date dateBooking = (Date) session.getAttribute("dateBooking");
                    Time slotStart = (Time) session.getAttribute("slotStart");
                    Time slotEnd = (Time) session.getAttribute("slotEnd");

                    int slotId = 0, doctorId = 0;
                    int appointmentId = 0;
                    String appointmentCode = GenerateAppoinmentCode.generateAppoinmentCode();
                    if (token.equals("packageService")) {
                        appointmentId = appointmentDao.insertAppointment(
                                appointmentCode,
                                p.getPatientId(),
                                s.getService_id(),
                                dateBooking,
                                slotStart,
                                slotEnd,
                                reason);
                    } else if(token.equals("online")){
                        doctorId = Integer.parseInt((String) session.getAttribute("doctorId"));
                        appointmentId = appointmentDao.insertAppointment(
                                appointmentCode,
                                p.getPatientId(),
                                doctorId,
                                s.getService_id(),
                                reason);
                    }else  {
                         slotId = Integer.parseInt((String) session.getAttribute("slotId"));
                        doctorId = Integer.parseInt((String) session.getAttribute("doctorId"));

                        appointmentId = appointmentDao.insertAppointment(
                                appointmentCode,
                                p.getPatientId(),
                                doctorId,
                                slotId,
                                s.getService_id(),
                                dateBooking,
                                slotStart,
                                slotEnd,
                                reason);
                       

                    }

                    paymentDAO.updateAppointmentIdForPaymentSuccess(appointmentId, txnRef, vnp_TransactionNo);

                    response.sendRedirect("billsDetail?appointment_code=" + appointmentCode);

                    transSuccess = true;
                    return;
                } else {
                    paymentDAO.updatePaymentStatusFailed(txnRef);
                    request.setAttribute("result", "false");
                    request.getRequestDispatcher("bookingResult.jsp").forward(request, response);
                    return;
                }

            } else {

                System.out.println("GD KO HOP LE (invalid signature)");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
