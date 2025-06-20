/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorDAO;
import dal.WeeklyDoctorScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;
import java.sql.Time;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddDoctorSchedule", urlPatterns = {"/adddoctorschedule"})
public class AddDoctorSchedule extends HttpServlet {

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
            out.println("<title>Servlet AddDoctorSchedule</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDoctorSchedule at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);
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
        if (action.equals("serachdoctor")) {
            String doctorUsername = request.getParameter("doctorUsername");
            DoctorDAO dDAO = new DoctorDAO();
            Doctor doctor = dDAO.getDoctorByDoctorUsername(doctorUsername);
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);

        }
        if (action.equals("addschedule")) {

            String doctorID = request.getParameter("doctorID");

            WeeklyDoctorScheduleDAO WDS = new WeeklyDoctorScheduleDAO();
            for (int i = 2; i <= 8; i++) {
                String[] day = request.getParameterValues("day_" + i);
                if (day != null) {

                    int template_Id = WDS.insertTemplate(Integer.parseInt(doctorID), i);

                    for (String slots : day) {
                        String parts[] = slots.split("_");

                        int shift = Integer.parseInt(parts[0]);

                        String slotstime[] = parts[1].split("-");

                        Time timeStart = Time.valueOf(slotstime[0]);

                        Time timeEnd = Time.valueOf(slotstime[1]);

                        WDS.insertSlot(template_Id, shift, timeStart, timeEnd);
                    }

                }

            }
            request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);

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
