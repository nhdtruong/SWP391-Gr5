/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.DoctorScheduleDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AccountUser;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MyWorkingSchedule", urlPatterns = {"/myWorkingSchedule"})
public class MyWorkingSchedule extends HttpServlet {

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
       
        HttpSession session = request.getSession();
        AccountUser acc = (AccountUser)session.getAttribute("user");
        if(acc == null){
            response.sendRedirect("home");
            return;
        }
        UserDAO userDAO = new UserDAO();
        
        int doctorId = userDAO.getDoctorIdByUsername(acc.getUsername());

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
 
        request.getRequestDispatcher("myWorkingSchedule.jsp").forward(request, response);
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
