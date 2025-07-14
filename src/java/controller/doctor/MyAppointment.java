/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.AppointmentDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.AccountUser;
import model.AppointmentView;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MyAppointment", urlPatterns = {"/myAppointment"})
public class MyAppointment extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        AccountUser acc = (AccountUser)session.getAttribute("user");
        if(acc == null){
            response.sendRedirect("home");
            return;
        }
        UserDAO userDAO = new UserDAO();
        
        int doctorId = userDAO.getDoctorIdByUsername(acc.getUsername());


        AppointmentDAO appointmentDao = new AppointmentDAO();

       
        String url = null;

        List<AppointmentView> listAppointment = null;

        if (action.equals("all")) {
            listAppointment = appointmentDao.getAppointmentsByDoctorId(doctorId);
            url = "myAppointment?action=all";
        } else if (action.equals("filter")) {
            String status = request.getParameter("status");

            request.setAttribute("status", status);
   
           // listAppointment = appointmentDao.getAppointmentsByFilter(status);
            url = "myAppointment?action=filter&status=" + status + "&paymentStatus=";
        }

        if (listAppointment != null) {
            int page, numberPerPage = 7;
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
            int numPageDisplay = 4;
            List<AppointmentView> listAppointmentDisplay = getListAppointmentDisplay(listAppointment, start, end);
            request.setAttribute("Appointment", listAppointmentDisplay);
            request.setAttribute("url", url);
            request.setAttribute("page", page);
            request.setAttribute("num", numberPage);
            request.setAttribute("numPageDisplay", numPageDisplay);
           request.getRequestDispatcher("myAppointment.jsp").forward(request, response);
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
