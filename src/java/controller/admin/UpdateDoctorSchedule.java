/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.WeeklyScheduleSlotDAO;
import dal.WeeklyDoctorScheduleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import model.DoctorScheduleSlots;
import dal.DoctorScheduleDAO;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateDoctorSchedule", urlPatterns = {"/updateDoctorSchedule"})
public class UpdateDoctorSchedule extends HttpServlet {

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
            
            String action = request.getParameter("action");
            
            if (action.equals("updateSchedule")) {
                
                WeeklyScheduleSlotDAO WSSD = new WeeklyScheduleSlotDAO();
                
                String doctorID = request.getParameter("doctorId");
                
                Map<Integer, List<DoctorScheduleSlots>> result = WSSD.getTemplateScheduleByDoctorId(Integer.parseInt(doctorID));
                
                request.setAttribute("ScheduleTemplate", result);
                
                request.setAttribute("doctorId", doctorID);
                
                request.getRequestDispatcher("admin/updateDoctorSchedule.jsp").forward(request, response);
               
            }
            else if (action.equals("updateScheduleExcute")) {
                
                String doctorId = request.getParameter("doctorId");
                
                WeeklyDoctorScheduleDAO WDS = new WeeklyDoctorScheduleDAO();
                
                WDS.deleteWeeklyScheduleByDoctorId(Integer.parseInt(doctorId));
                
                
                for (int i = 2; i <= 8; i++) {
                    String[] day = request.getParameterValues("day_" + i);
                    
                    if (day != null) {

                        int template_Id = WDS.insertTemplateSchdule(Integer.parseInt(doctorId), i);
                        
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
                request.getRequestDispatcher("admin/updateDoctorSchedule.jsp").forward(request, response);
             
            }
            else if(action.equals("deleteSchedule")){
                String doctorId = request.getParameter("doctorId");
                System.out.println("xoas cai nay:"+doctorId);
                DoctorScheduleDAO DSD = new DoctorScheduleDAO();
               int x = DSD.deleteDoctorScheduleByDoctorId(Integer.parseInt(doctorId));
                response.sendRedirect("doctorschedule?action=all");
                
            } 
           
            
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
