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
import java.util.List;
import model.AppointmentView;
import dal.AppointmentDAO;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name="AppointmentManager", urlPatterns={"/appointmentManager"})
public class AppointmentManager extends HttpServlet {
   
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
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String url = null;
        AppointmentDAO  appointmentDao = new AppointmentDAO();
        List<AppointmentView> listAppointment = null;
        
        
        try (PrintWriter out = response.getWriter()) {
            
            if (action.equals("all")) {
                listAppointment = appointmentDao.getAllAppointments();
                url = "appointmentManager?action=all";
            }else if (action.equals("search")) {
                String text = request.getParameter("text");
                request.setAttribute("text", text);
                listAppointment = appointmentDao.getAllAppointmentsSearchDoctor(text);
                url = "appointmentManager?action=search&text="+text;
            }else if (action.equals("filter")){
                 String status = request.getParameter("status");
                 String paymentStatus = request.getParameter("paymentStatus");
                request.setAttribute("status", status);
                request.setAttribute("paymentStatus", paymentStatus);
                listAppointment = appointmentDao.getAppointmentsByFilter(status, paymentStatus);
                url = "appointmentManager?action=filter&status="+status+"&paymentStatus="+paymentStatus;
            }
            
            if (listAppointment!= null) {
                int page, numberPerPage = 9;
                int size = listAppointment.size();
                int numberPage = (size % numberPerPage == 0) ? (size / numberPerPage) : ((size / numberPerPage) + 1);
                String xPage = request.getParameter("page");
                if (xPage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xPage);
                }
                int start = (page - 1) * numberPerPage;
                int end = Math.min(page * numberPerPage, size);
                int numPageDisplay = 7;
                List<AppointmentView> listAppointmentDisplay = getListAppointmentDisplay(listAppointment, start, end);
                request.setAttribute("Appointment", listAppointmentDisplay);
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("num", numberPage);
                request.setAttribute("numPageDisplay", numPageDisplay);
                request.getRequestDispatcher("admin/appointmentView.jsp").forward(request, response);
            }
            
        }
    } 
    
        private List<AppointmentView> getListAppointmentDisplay(List<AppointmentView> lisAppoint, int start, int end) {
        List<AppointmentView> listAppoitmentDisplay = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listAppoitmentDisplay.add(lisAppoint.get(i));
        }
        return listAppoitmentDisplay;
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
        processRequest(request, response);
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
        processRequest(request, response);
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
