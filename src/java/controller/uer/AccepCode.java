/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OTPdata;
import dal.UserDAO;
import model.AccountUser;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AccepCode", urlPatterns = {"/accepcode"})
public class AccepCode extends HttpServlet {

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
        request.getRequestDispatcher("accepcode.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("accepcode.jsp").forward(request, response);
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
            UserDAO u = new UserDAO();

            AccountUser accountUser1 = (AccountUser) session.getAttribute("prepareAccountRegister");
            AccountUser accountUser2 = (AccountUser) session.getAttribute("accountChangPass");
            AccountUser accountUser3 = (AccountUser) session.getAttribute("accountPrepareUpdate");
            
            if (accountUser1 != null) {
                u.registerNewUser(accountUser1.getUsername(), accountUser1.getRole(), 
                        accountUser1.getPassword(), accountUser1.getEmail(), accountUser1.getImg(), accountUser1.getStatus());
                session.invalidate();
                
                request.getRequestDispatcher("registersuccess.jsp").forward(request, response);
                
     
            } else if (accountUser2 != null) {
                request.setAttribute("type", "2");
                request.getRequestDispatcher("recover.jsp").forward(request, response);
            }else if(accountUser3 != null){
                AccountUser accountCurrent = (AccountUser) session.getAttribute("user");
                
                u.updateAccountByUser(accountCurrent.getUsername(), accountUser3.getUsername(),accountUser3.getEmail());
                accountCurrent.setUsername(accountUser3.getUsername());
                accountCurrent.setEmail(accountUser3.getEmail());
                session.setAttribute("user",accountCurrent);
                response.sendRedirect("profile");
                
            }

        } else {

            request.setAttribute("error", "Mã OTP không khớp.");
            request.getRequestDispatcher("accepcode.jsp").forward(request, response);
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
