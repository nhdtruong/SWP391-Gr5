/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.AppointmentDAO;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Patient;
import java.sql.Time;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ConfirmFinalInformation", urlPatterns = {"/confirmFinalInformation"})
public class ConfirmFinalInformation extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConfirmFinalInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmFinalInformation at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        Patient patient = (Patient) session.getAttribute("patient");
        int departmentId = Integer.parseInt((String) session.getAttribute("departmentId"));
        Date dateBooking = (Date) session.getAttribute("dateBooking");
        Time slotStart = (Time) session.getAttribute("slotStart");
        Time slotEnd = (Time)session.getAttribute("slotEnd");
        int check = appointmentDAO.checkPatientBookingConflictDetailed(patient.getPatientId(), departmentId, dateBooking, slotStart, slotEnd);
        if (check == 1) {
            request.setAttribute("error", "Bạn đã đặt khám chuyên khoa này trong ngày rồi. Vui lòng xem lại phiếu khám!");
            request.getRequestDispatcher("confirmInformation.jsp").forward(request, response);
        } else if (check == 2) {
            request.setAttribute("error", "Bạn đã đặt khám trùng giờ với chuyên khoa khác trong ngày rồi. Vui lòng xem lại phiếu khám!");
            request.getRequestDispatcher("confirmInformation.jsp").forward(request, response);
        } else if (check == 0) {
            response.sendRedirect("payment");
        }
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
