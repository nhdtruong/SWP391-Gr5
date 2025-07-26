/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.AppointmentDAO;
import dal.DepartmentDAO;
import dal.DoctorDAO;
import dal.DoctorScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Doctor;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ReqChangeAppointment", urlPatterns = {"/reqChangeAppointment"})
public class ReqChangeAppointment extends HttpServlet {

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
            out.println("<title>Servlet ReqChangeAppointment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReqChangeAppointment at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");

        if (action.equals("reqChangeAppoitment")) {
            DoctorScheduleDAO DSD = new DoctorScheduleDAO();
            DoctorDAO doctorDAO = new DoctorDAO();

            String appointmentId = request.getParameter("appointmentId");
            String doctorId = request.getParameter("doctorId");
            String slotId = request.getParameter("slotId");
            String slotIdReqChange = request.getParameter("slotIdReqChange");
            String patientName = request.getParameter("patientName");
            String doctorName = request.getParameter("doctorName");
            String dateBookingStr = request.getParameter("dateBooking");
            String slotEnd = request.getParameter("slotEnd");
            String slotStart = request.getParameter("slotStart");
            String departmentName = request.getParameter("departmentName");

            List<WorkingDateSchedule> listWDS = DSD.getWorkingScheduleOfDoctor10Day(Integer.parseInt(doctorId));

            Date dateBooking = null;

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(dateBookingStr);
                dateBooking = new java.sql.Date(utilDate.getTime());
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("dateBooking", dateBooking);
            request.setAttribute("appointmentId", appointmentId);
            request.setAttribute("doctorId", doctorId);
            request.setAttribute("patientName", patientName);
            request.setAttribute("doctorName", doctorName);
            request.setAttribute("dateBooking", dateBooking);
            request.setAttribute("dateBooking", dateBooking);
            request.setAttribute("slotStart", slotStart);
            request.setAttribute("slotEnd", slotEnd);
            request.setAttribute("departmentName", departmentName);
            request.setAttribute("slotId", slotId);
            request.setAttribute("slotIdReqChange", slotIdReqChange);
            request.setAttribute("listWDS", listWDS);
            request.getRequestDispatcher("doctorReqChangeAppointment.jsp").forward(request, response);

        } else if (action.equals("excuteReqChange")) {
            
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            String appointmentId = request.getParameter("appointmentId");
            String doctorId = request.getParameter("doctorId");
            String slotId = request.getParameter("slotId");
            String dateBooking_ = request.getParameter("dateBooking");
            String slotEnd_ = request.getParameter("slotEnd");
            String slotStart_ = request.getParameter("slotStart");
            
            
            Date dateBooking = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                java.util.Date parsedDate = sdf.parse(dateBooking_);
                dateBooking = new java.sql.Date(parsedDate.getTime());

                System.out.println("Parsed date: " + dateBooking);  
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Time slotStart = Time.valueOf(slotStart_);
            Time slotEnd = Time.valueOf(slotEnd_);

             boolean updated = appointmentDAO.reqChangeAppointmentByDoctor(
                    Integer.parseInt(appointmentId),
                    Integer.parseInt(doctorId),
                    Integer.parseInt(slotId),
                    dateBooking,
                    slotStart,
                    slotEnd
            );
            if (updated) {
                response.sendRedirect("myAppointment?action=all");
            } else {

                request.setAttribute("error", "Lỗi yêu cầu đổi lịch hẹn");
                response.sendRedirect("login");
            }

            

        }
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
