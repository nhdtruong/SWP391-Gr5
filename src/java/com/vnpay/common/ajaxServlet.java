/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vnpay.common;

import dal.AppointmentDAO;
import dal.PaymentDAO;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Patient;
import model.Service;
import config.GenerateAppoinmentCode;
import config.GenerateVnpTxnRef;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author CTT VNPAY
 */
@WebServlet(name = "Payment", urlPatterns = {"/payment"})
public class ajaxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("payment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //
        HttpSession session = req.getSession();
        PaymentDAO payDAO = new PaymentDAO();
        AppointmentDAO appointmentDao = new AppointmentDAO();

        Patient p = (Patient) session.getAttribute("patient");
        Service s = (Service) session.getAttribute("serviceBooking");

        String bankCode = req.getParameter("bankCode");
        String paymentMethod = req.getParameter("paymentMethod");
        double amountDouble = Double.parseDouble(req.getParameter("total"));

        if (req.getParameter("total") == null) {
            resp.sendRedirect("home");
            return;
        }

        if (paymentMethod.equals("taiPhongKham")) {
            String token = (String) session.getAttribute("token");
            String reason = (String) session.getAttribute("reason");
            Date dateBooking = (Date) session.getAttribute("dateBooking");
            Time slotStart = (Time) session.getAttribute("slotStart");
            Time slotEnd = (Time) session.getAttribute("slotEnd");
            int slotId = 0 ,doctorId = 0;  int appointmentId =0;
            String appointmentCode = GenerateAppoinmentCode.generateAppoinmentCode();
            if (!token.equals("packageService")) {
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
            }else{
                 appointmentId = appointmentDao.insertAppointment(
                    appointmentCode,
                    p.getPatientId(),
                    s.getService_id(),
                    dateBooking,
                    slotStart,
                    slotEnd,
                    reason);
                
            }

            payDAO.insertPayment(appointmentId, amountDouble,
                    "CASH", "pending",
                    "",
                    "",
                    "");
            req.setAttribute("result", "true");
            resp.sendRedirect("billsDetail?appointment_code=" + appointmentCode);

        } else if (paymentMethod.equals("vnPay")) {

            String vnp_TxnRef = GenerateVnpTxnRef.generateVnpTxnRef();
            payDAO.insertPayment(null, amountDouble, "VNPAY", "pending", vnp_TxnRef, "", "");

            vnPay(req, resp, amountDouble, vnp_TxnRef, bankCode);

        }

    }

    protected void vnPay(HttpServletRequest req, HttpServletResponse resp, double amountDouble, String vnp_TxnRef, String bankCode)
            throws ServletException, IOException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";

        long amount = (long) (amountDouble * 100);

        String vnp_IpAddr = Config.getIpAddress(req);

        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) {
            vnp_Params.put("vnp_Locale", locate);
        } else {
            vnp_Params.put("vnp_Locale", "vn");
        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        resp.sendRedirect(paymentUrl);

    }

}
