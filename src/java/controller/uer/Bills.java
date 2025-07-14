/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import config.EmailSender;
import dal.AppointmentDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Bills", urlPatterns = {"/bills"})
public class Bills extends HttpServlet {

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
            out.println("<title>Servlet Bills</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Bills at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        AppointmentDAO apoiAppointmentDAO = new AppointmentDAO();
       
        AccountUser acc = (AccountUser) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect("login");
            return;
        }

       
        
        String filter = request.getParameter("filter");

        if (filter == null || filter.isEmpty()) {
            filter = "pending";
        }
        
        List<AppointmentView> list = apoiAppointmentDAO.getAppointmentsByUsername(acc.getUsername());
        List<AppointmentView> filteredBills = new ArrayList<>();
        for (AppointmentView b : list) {
            switch (filter) {
                case "pending": // trươgf hơpj chưa thanh toán , đã đặt 
                    if ("pending".equals(b.getPaymentStatus()) && b.getStatus() == 1) {
                        filteredBills.add(b);
                    }
                    break;
                case "success": //trường hợp đã thanh toán , đã đặt , , muốn hủy vì đã thanh toán
                    if ("success".equals(b.getPaymentStatus()) && (b.getStatus() == 1 || b.getStatus() == 3)) {
                        filteredBills.add(b);
                    }
                    break;
                case "done":
                    if (b.getStatus() == 2) {
                        filteredBills.add(b);
                    }
                    break;
                case "canceled":
                    if (b.getStatus() == 0) {
                        filteredBills.add(b);
                    }
                    break;
            }
        }
        
        request.setAttribute("bills", filteredBills);
        request.setAttribute("filter", filter);
        request.getRequestDispatcher("medicalExaminationForm.jsp").forward(request, response);
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
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
