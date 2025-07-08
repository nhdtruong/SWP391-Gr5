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
@WebServlet(name = "DeleteBills", urlPatterns = {"/deleteBills"})
public class DeleteBills extends HttpServlet {

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
            out.println("<title>Servlet UpdateBills</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBills at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        String statusPayment = request.getParameter("paymentStatus");
        String appointmentCode = request.getParameter("appointmentCode");

        if (action.equals("cancel") && statusPayment.equals("pending")) {
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            appointmentDAO.cancelAppointmentById(appointmentCode);
            response.sendRedirect("billsDetail?appointment_code=" + appointmentCode);

        } else if (action.equals("cancel") && statusPayment.equals("success")) {
            
             AppointmentDAO appointmentDAO = new AppointmentDAO();
            
            AppointmentView appointmentView = appointmentDAO.getBillstByCode(appointmentCode);

            request.setAttribute("bills", appointmentView);

            request.setAttribute("appointmentCode", appointmentCode);
            request.setAttribute("error", "error");
            request.getRequestDispatcher("billsDetail.jsp").forward(request, response);

        } else if (action.equals("requestRefund")) {
            
            String departmentName = request.getParameter("departmentName");
            String serviceName = request.getParameter("serviceName");
            String patientName = request.getParameter("patientName");
            String doctorName = request.getParameter("doctorName");
            String workingDate = request.getParameter("workingDate");
            String slotTime = request.getParameter("slotTime");
            String amount = request.getParameter("amount");
            String note = request.getParameter("note");

            String refundReason = request.getParameter("refundReason");
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            UserDAO userDAO = new UserDAO();
            List<String> listEmail = userDAO.getEmailsByRole12();
            for (String email : listEmail) {
                try {
                    EmailSender.sendRefundRequestEmail(
                            email,
                            appointmentCode,
                            patientName,
                            doctorName,
                            workingDate,
                            slotTime,
                            amount,
                            refundReason
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            appointmentDAO.requestRefoundAppointmentById(appointmentCode);
            response.sendRedirect("billsDetail?appointment_code="+appointmentCode);

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
