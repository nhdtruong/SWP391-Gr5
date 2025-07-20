/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dal.WeeklyDoctorScheduleDAO;
import dal.WeeklyPackageServiceScheduleDAO;
import dal.WeeklyScheduleSlotDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author DELL
 */
@WebServlet(name="AddPackageServiceSchedule", urlPatterns={"/addPackageServiceSchedule"})
public class AddPackageServiceSchedule extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet AddPackageServiceSchedule</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPackageServiceSchedule at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String serviceId  = request.getParameter("serviceId");
        request.setAttribute("serviceId",serviceId);
        request.getRequestDispatcher("admin/addPackageServiceSchedule.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String actionform = request.getParameter("actionform");
       if (actionform.equals("addschedule")) {

            String serviceId = request.getParameter("serviceId");
       
            saveTemplate(request, Integer.parseInt(serviceId));

            response.sendRedirect("doctorschedule?action=all");

        }
    }
    
    private void saveTemplate(HttpServletRequest request, int serviceId) throws ServletException, IOException {

        WeeklyPackageServiceScheduleDAO packageServiceScheduleDAO = new WeeklyPackageServiceScheduleDAO();
        
        for (int i = 2; i <= 8; i++) {
            String[] day = request.getParameterValues("day_" + i);
            if (day != null) {

                int week_pakageService_id = packageServiceScheduleDAO.insertTemplateSchdule(serviceId, i);

                for (String slots : day) {

                    String slotstime[] = slots.split("-");

                    Time timeStart = Time.valueOf(slotstime[0]);

                    Time timeEnd = Time.valueOf(slotstime[1]);

                    packageServiceScheduleDAO.insertSlot(week_pakageService_id, timeStart, timeEnd);
                }

            }

        }

    }


    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
