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
import java.util.List;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DoctorScheduleDetail", urlPatterns = {"/doctorScheduleDetail"})
public class DoctorScheduleDetail extends HttpServlet {

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
            out.println("<title>Servlet DoctorScheduleDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoctorScheduleDetail at " + request.getContextPath() + "</h1>");
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

        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String doctorName = request.getParameter("doctorName");

        DoctorScheduleDAO dsd = new DoctorScheduleDAO();
        List<List<WorkingDateSchedule>> weeklySchedule = dsd.getWorkingScheduleByWeek(doctorId);

        String weekIndexRaw = request.getParameter("weekIndex");
        int weekIndex = 0;
        if (weekIndexRaw != null) {
            try {
                weekIndex = Integer.parseInt(weekIndexRaw);
                if (weekIndex < 0 || weekIndex >= weeklySchedule.size()) {
                    weekIndex = 0;
                }
            } catch (NumberFormatException e) {
                weekIndex = 0;
            }
        }

        request.setAttribute("weeklySchedule", weeklySchedule);
        request.setAttribute("weekIndex", weekIndex);
        request.setAttribute("doctorId", doctorId);
        request.setAttribute("doctorName", doctorName);

        request.getRequestDispatcher("admin/weeklyScheduledoctor.jsp").forward(request, response);
        
        //   request.getRequestDispatcher("admin/doctorScheduleDetail.jsp").forward(request, response); ban đầu làm theo ngày

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
