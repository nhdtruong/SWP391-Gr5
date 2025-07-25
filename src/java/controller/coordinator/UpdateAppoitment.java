/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.coordinator;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AppointmentView;
import dal.AppointmentDAO;
import dal.DepartmentDAO;
import dal.DoctorDAO;
import dal.DoctorScheduleDAO;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Doctor;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateAppoitment", urlPatterns = {"/updateAppoitment"})
public class UpdateAppoitment extends HttpServlet {

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
            out.println("<title>Servlet UpdateAppoitment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAppoitment at " + request.getContextPath() + "</h1>");
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
        String appointmentId = request.getParameter("appointmentId");
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        AppointmentView appointmentView = appointmentDAO.getAppointmentsByAppointmentId(Integer.parseInt(appointmentId));
        request.setAttribute("appointmentView", appointmentView);
        request.getRequestDispatcher("admin/updateAppointment.jsp").forward(request, response);
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

        if (action.equals("reschedule")) {
            DoctorScheduleDAO DSD = new DoctorScheduleDAO();
            DoctorDAO doctorDAO = new DoctorDAO();
            DepartmentDAO departmentDAO = new DepartmentDAO();

            String appointmentId = request.getParameter("appointmentId");
            String doctorId_reDoctorName = request.getParameter("doctorId_reDoctorName");
            String[] parts = doctorId_reDoctorName.split("_", 2);
            String doctorId = parts[0];
            String reDoctorName = parts[1];
            System.out.println("id:" + doctorId + "re:" + reDoctorName);
            String slotId = request.getParameter("slotId");
            String slotIdReqChange = request.getParameter("slotIdReqChange");
            String patientName = request.getParameter("patientName");
            String currentDoctorName = request.getParameter("currentDoctorName");
            String dateBookingStr = request.getParameter("dateBooking");
            String slotEnd = request.getParameter("slotEnd");
            String slotStart = request.getParameter("slotStart");
            String departmentName = request.getParameter("departmentName");

            int departmentId = departmentDAO.getDepartmentIdByDoctorId(Integer.parseInt(doctorId));
            List<Doctor> listDoctor = doctorDAO.getAllDoctorInDepartmanent1(departmentId);
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
            request.setAttribute("currentDoctorName", currentDoctorName);
            request.setAttribute("reDoctorName", reDoctorName);

            request.setAttribute("dateBooking", dateBooking);
            request.setAttribute("dateBooking", dateBooking);
            request.setAttribute("slotStart", slotStart);
            request.setAttribute("slotEnd", slotEnd);
            request.setAttribute("departmentName", departmentName);
            request.setAttribute("listDoctor", listDoctor);
            request.setAttribute("slotId", slotId);
            request.setAttribute("slotIdReqChange", slotIdReqChange);
            request.setAttribute("listWDS", listWDS);
            request.getRequestDispatcher("admin/reSchedule.jsp").forward(request, response);
        } else if (action.equals("excuteUpdate")) {

            String doctorId = request.getParameter("doctorId");
            String slotId = request.getParameter("slotId");
            String appointmentId = request.getParameter("appointmentId");
            String dateBooking_ = request.getParameter("dateBooking");
            String slotId_ = request.getParameter("slotId");
            String slotStart_ = request.getParameter("slotStart");
            String slotEnd_ = request.getParameter("slotEnd");

            System.out.println("Date input: " + dateBooking_);

           
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

            AppointmentDAO appointmentDAO = new AppointmentDAO();

            boolean updated = appointmentDAO.rescheduleAppointment(
                    Integer.parseInt(appointmentId),
                    Integer.parseInt(doctorId),
                    Integer.parseInt(slotId),
                    dateBooking,
                    slotStart,
                    slotEnd
            );
            if (updated) {
                response.sendRedirect("updateAppoitment?appointmentId=" + appointmentId);
            } else {

                request.setAttribute("error", "Không thể cập nhật lịch hẹn");
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
