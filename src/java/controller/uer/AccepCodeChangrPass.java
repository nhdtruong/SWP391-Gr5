/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.uer;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.AccountUser;
import model.OTPdata;

/**
 *
 * @author DELL
 */
@WebServlet(name="AccepCodeChangrPass", urlPatterns={"/accepcodeChangePass"})
public class AccepCodeChangrPass extends HttpServlet {
   
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
            out.println("<title>Servlet AccepCodeChangrPass</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccepCodeChangrPass at " + request.getContextPath () + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("type","1");
        request.getRequestDispatcher("accepcode.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        String rawOtp = request.getParameter("otp");
        String cleanOtp = rawOtp.trim();
        OTPdata otpdata = (OTPdata) session.getAttribute("currentOTP");

        if (otpdata == null) {
            request.setAttribute("error", "Không tìm thấy mã OTP. Vui lòng yêu cầu mã mới.");
            request.getRequestDispatcher("accepcode.jsp").forward(request, response);
            return;
        }

        if (cleanOtp.equals(otpdata.getOtp()) && otpdata.isExpired()) {
            // Mã đúng và hết hạn
            request.setAttribute("error", "Mã OTP đã hết hạn. Vui lòng yêu cầu mã mới.");
            request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
            return;
        }
        if (otpdata.isExpired()) {
            request.setAttribute("error", "Mã OTP đã hết hạn. Vui lòng yêu cầu mã mới.");
            request.getRequestDispatcher("accepcode.jsp").forward(request, response);
            return;
        }

        if (cleanOtp.equals(otpdata.getOtp())) {
            // ddungs 
            UserDAO uDao = new UserDAO();
            String username = (String) session.getAttribute("usernameChangePass");
            String enNewPassword = (String) session.getAttribute("enNewPassword");
            uDao.changePassByUser(username, enNewPassword);
            session.removeAttribute(username);
            session.removeAttribute(enNewPassword);
            response.sendRedirect("profile");

        } else {

            request.setAttribute("error", "Mã OTP không khớp.");
            request.getRequestDispatcher("accepcode.jsp").forward(request, response);
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
