/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.WorkingDateSchedule;
import java.sql.Date;
import java.sql.Time;
import dal.DoctorScheduleSlotsDAO;
import java.net.URLEncoder;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateDoctorScheduleDetail", urlPatterns = {"/updateDoctorScheduleDetail"})
public class UpdateDoctorScheduleDetail extends HttpServlet {

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
            out.println("<title>Servlet UpdateDoctorScheduleDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateDoctorScheduleDetail at " + request.getContextPath() + "</h1>");
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
        String doctorId = request.getParameter("doctorId");
        String doctorName = request.getParameter("doctorName");
        String workingDate = request.getParameter("workingDate");
        Date date = Date.valueOf(workingDate);
        DoctorScheduleDAO DSD = new DoctorScheduleDAO();
        WorkingDateSchedule WDS = DSD.getWorkingDayScheduleOfDoctor(Integer.parseInt(doctorId), date);
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("doctorName", doctorName);
        request.setAttribute("WDS", WDS);
        request.getRequestDispatcher("admin/updateDoctorScheduleDetail.jsp").forward(request, response);
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

        DoctorScheduleDAO DSD = new DoctorScheduleDAO();
        DoctorScheduleSlotsDAO DSSD = new DoctorScheduleSlotsDAO();
        String doctorId_ = request.getParameter("doctorId");
        String doctorName_ = request.getParameter("doctorName");
        int doctorId = Integer.parseInt(doctorId_);
        String[] day = request.getParameterValues("day");
        String workingDate = request.getParameter("workingDate");

        Date date = Date.valueOf(workingDate);

        if (day != null) {
            DSD.deleteDayScheduleOfDoctor(doctorId, date);
            int schedule_id = DSD.insertDoctorSchedule(doctorId, date);
            for (String days : day) {

                String slotstime[] = days.split("-");

                Time timeStart = Time.valueOf(slotstime[0]);

                Time timeEnd = Time.valueOf(slotstime[1]);

                DSSD.insertScheduleSlot(schedule_id, timeStart, timeEnd);
            }

            response.sendRedirect("doctorScheduleDetail?doctorId=" + doctorId
                    + "&doctorName=" + URLEncoder.encode(doctorName_, "UTF-8"));
        } else if (day == null) {
            DSD.deleteDayScheduleOfDoctor(doctorId, date);
            response.sendRedirect("doctorScheduleDetail?doctorId=" + doctorId
                    + "&doctorName=" + URLEncoder.encode(doctorName_, "UTF-8"));
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
