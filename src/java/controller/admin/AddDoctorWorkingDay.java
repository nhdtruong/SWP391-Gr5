/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.DoctorScheduleDAO;
import dal.DoctorScheduleSlotsDAO;
import java.net.URLEncoder;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddDoctorWorkingDay", urlPatterns = {"/addDoctorWorkingDay"})
public class AddDoctorWorkingDay extends HttpServlet {

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
            out.println("<title>Servlet AddDoctorWorkingDay</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDoctorWorkingDay at " + request.getContextPath() + "</h1>");
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
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("doctorName", doctorName);
        DoctorScheduleDAO DSD = new DoctorScheduleDAO();
        List<Date> listDate = DSD.getAllDaysWorkingInscheduleOfDoctor(Integer.parseInt(doctorId));
        request.setAttribute("listDate", listDate);
        request.getRequestDispatcher("admin/addWorkingDayDoctor.jsp").forward(request, response);

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
        String doctorId = request.getParameter("doctorId");
        String doctorName = request.getParameter("doctorName");
        String workingDate = request.getParameter("workingDate");
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("doctorName", doctorName);
        String status = request.getParameter("status");
        DoctorScheduleDAO DSD = new DoctorScheduleDAO();
        DoctorScheduleSlotsDAO DSSD = new DoctorScheduleSlotsDAO();
        List<Date> listDate = DSD.getAllDaysWorkingInscheduleOfDoctor(Integer.parseInt(doctorId));
        request.setAttribute("listDate", listDate);
        String[] day = request.getParameterValues("day");

        if (workingDate == null || workingDate.isEmpty()) {

            request.setAttribute("errorD", "Hãy chọn ngày làm việc!");
            request.getRequestDispatcher("admin/addWorkingDayDoctor.jsp").forward(request, response);

        } else if (day == null) {
            request.setAttribute("error", "Hãy chọn slot làm việc!");
            request.getRequestDispatcher("admin/addWorkingDayDoctor.jsp").forward(request, response);

        } else {
            int scheduleId = DSD.insertDoctorSchedule(Integer.parseInt(doctorId), java.sql.Date.valueOf(workingDate), Integer.parseInt(status));

            for (String days : day) {

                String slotstime[] = days.split("-");

                Time timeStart = Time.valueOf(slotstime[0]);

                Time timeEnd = Time.valueOf(slotstime[1]);

                DSSD.insertScheduleSlot(scheduleId, timeStart, timeEnd);
            }
            response.sendRedirect("doctorScheduleDetail?doctorId=" + doctorId
                    + "&doctorName=" + URLEncoder.encode(doctorName, "UTF-8"));
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
