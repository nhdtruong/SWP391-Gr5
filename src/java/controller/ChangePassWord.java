/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import config.EmailSender;
import dal.DoctorDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.AccountUser;
import model.OTPdata;
import model.OTPutil;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ChangePassWord", urlPatterns = {"/changepassword"})
public class ChangePassWord extends HttpServlet {

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

        String action = request.getParameter("action");
        String type = request.getParameter("type");
        if (action.equals("hdn")) {
            String username = request.getParameter("username");
            request.setAttribute("username", username);
            request.getRequestDispatcher("recover.jsp").forward(request, response);
            return;
        }
        if (action.equals("forgotpass")) {
            request.setAttribute("type", 1);
            request.getRequestDispatcher("recover.jsp").forward(request, response);
            return;
        }

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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String type = request.getParameter("type");

        if (action.equals("checkUser_Email")) {
            UserDAO uDao = new UserDAO();
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            AccountUser acc = uDao.CheckAccChangePass(username, email);
            if ((acc == null && type == null) || (acc == null && type.equals("1"))) {
                request.setAttribute("type", type);
                request.setAttribute("error", "Tài khoản và Email không khớp");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                request.getRequestDispatcher("recover.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("accountChangPass", acc);
                long expiryTime = System.currentTimeMillis() + 5 * 60 * 1000;
                String otp = OTPutil.generateOTP();
                OTPdata otpData = new OTPdata(otp, expiryTime);
                session.setAttribute("currentOTP", otpData);
                try {
                    EmailSender.sendOTP(otp, email);
                } catch (MessagingException ex) {
                    Logger.getLogger(RegisterServerlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("accepcode");

            }

        }
        if (action.equals("updatepassword")) {

            String password = request.getParameter("password");
            String repassword = request.getParameter("repassword");
            HttpSession session = request.getSession();
            UserDAO u = new UserDAO();
            AccountUser accountUser2 = (AccountUser) session.getAttribute("accountChangPass");
            if (accountUser2.getRole() == 4) {
                DoctorDAO d = new DoctorDAO();
                u.Recover(accountUser2.getUsername(), password);
                d.RecoverStatusForDoctor(accountUser2.getUsername());
            }
            u.Recover(accountUser2.getUsername(), password);

            session.invalidate();
            request.setAttribute("success", "Thay đổi mật khẩu thành công!");
            request.getRequestDispatcher("changepasssucess.jsp").forward(request, response);
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
