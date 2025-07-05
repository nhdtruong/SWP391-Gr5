/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import config.EmailSender;
import config.EncodeData;
import config.PasswordUtils;
import dal.UserDAO;
import jakarta.mail.MessagingException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountUser;
import model.OTPdata;
import model.OTPutil;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/updateprofile"})
public class UpdateProfile extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
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

        String action = request.getParameter("action");
        if (action.equals("updateInfor")) {

            String username = request.getParameter("username");
            String email = request.getParameter("email");

            request.setAttribute("username", username);
            request.setAttribute("email", email);
            UserDAO uDao = new UserDAO();
            HttpSession session = request.getSession();

            AccountUser acc = (AccountUser) session.getAttribute("user");

            if (acc == null) {
                response.sendRedirect("login");
                return;
            }
            String emailCurrent = acc.getEmail();
            if (acc.getEmail().equals(email) && acc.getUsername().equals(username)) {
                request.setAttribute("error", "Bạn đang dùng tài khoản và email này!");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            } else if (!acc.getUsername().equals(username) && acc.getEmail().equals(email)) {
                AccountUser accCheck = uDao.CheckAccByUsername(username);
                if (accCheck != null) {
                    request.setAttribute("errorTK", "Tên tài khoản này đã tồn tại trên hệ thống!");
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                    return;
                }
            } else if (acc.getUsername().equals(username) && !acc.getEmail().equals(email)) {
                AccountUser accCheck = uDao.CheckAccByEmail(email);
                if (accCheck != null) {
                    request.setAttribute("errorEM", "Email này đã tồn tại trên hệ thống!");
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                    return;
                }
            } else if (!acc.getUsername().equals(username) && !acc.getEmail().equals(email)) {
                AccountUser accCheck = uDao.CheckAccByUsernameOREmail(username, email);
                if (accCheck != null) {
                    request.setAttribute("error", "Tài khoản hoặc email đã tồn tại!");
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                    return;
                }
            }
            acc.setUsername(username);
            acc.setEmail(email);
            session.setAttribute("accountPrepareUpdate", acc);
            long expiryTime = System.currentTimeMillis() + 5 * 60 * 1000;
            String otp = OTPutil.generateOTP();
            OTPdata otpData = new OTPdata(otp, expiryTime);
            session.setAttribute("currentOTP", otpData);
            try {
                EmailSender.sendOTP(otp, emailCurrent);
            } catch (MessagingException ex) {
                Logger.getLogger(RegisterServerlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("accepcode");

        }
        if (action.equals("changePassword")) {
            HttpSession session = request.getSession();
            UserDAO uDao = new UserDAO();
            AccountUser acc = (AccountUser) session.getAttribute("user");

            if (acc == null) {
                response.sendRedirect("login");
                return;
            }

         
            String inputOldPassword = request.getParameter("inputOldPassword");
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");

        
            request.setAttribute("inputOldPassword", inputOldPassword);
            request.setAttribute("newPassword", newPassword);
            request.setAttribute("reNewPassword", reNewPassword);
            request.setAttribute("username", acc.getUsername());
            request.setAttribute("email", acc.getEmail());

           
            if (!newPassword.equals(reNewPassword)) {
                request.setAttribute("errorMK", "Mật khẩu mới và xác nhận không khớp!");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                return;
            }

        
            AccountUser accFromDB = uDao.getAccountByUsername(acc.getUsername());
            boolean isMatch = PasswordUtils.checkPassword(inputOldPassword, accFromDB.getPassword());

            if (isMatch) {
           
                session.setAttribute("usernameChangePass", acc.getUsername());

        
                String newHashedPassword = PasswordUtils.hashPassword(newPassword);
                session.setAttribute("enNewPassword", newHashedPassword);

      
                long expiryTime = System.currentTimeMillis() + 5 * 60 * 1000;
                String otp = OTPutil.generateOTP();
                OTPdata otpData = new OTPdata(otp, expiryTime);
                session.setAttribute("currentOTP", otpData);

                try {
                    EmailSender.sendOTP(otp, acc.getEmail());
                } catch (MessagingException ex) {
                    Logger.getLogger(RegisterServerlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                response.sendRedirect("accepcodeChangePass"); // sang trang xác nhận OTP
            } else {
                request.setAttribute("errorMK", "Mật khẩu cũ không đúng!");
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
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
