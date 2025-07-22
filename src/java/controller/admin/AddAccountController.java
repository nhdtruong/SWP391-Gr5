/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import config.EmailSender;
import config.PasswordUtils;
import controller.uer.RegisterServerlet;
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
@WebServlet(name = "AddAccountController", urlPatterns = {"/addaccount"})
public class AddAccountController extends HttpServlet {

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
            request.getRequestDispatcher("admin/addAccount.jsp").forward(request, response);
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
        request.getRequestDispatcher("admin/addAccount.jsp").forward(request, response);
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
        UserDAO u = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role_id = request.getParameter("role_id");

        String img = "default";
        int status = 1;

        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("username", username);
        request.setAttribute("role_id", role_id);
        AccountUser account1 = u.CheckAccByUsername(username);
        AccountUser account2 = u.CheckAccByEmail(email);

        
        String hashedPassword = PasswordUtils.hashPassword(password);
        
        if (account1 != null && account2 != null) {
            request.setAttribute("errorEM", "Email đã tồn tại!");
            request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("admin/addAccount.jsp").forward(request, response);
            return;
        } else if (account1 != null) {
            request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("admin/addAccount.jsp").forward(request, response);
            return;
        } else if (account2 != null) {
            request.setAttribute("errorEM", "Email đã tồn tại!");
            request.getRequestDispatcher("admin/addAccount.jsp").forward(request, response);
            return;
        } else {
            if (Integer.parseInt(role_id) == 5) {
                u.registerNewUser(username, Integer.parseInt(role_id), hashedPassword, email, img, 2);

            } else {
                u.registerNewUser(username, Integer.parseInt(role_id), hashedPassword, email, img, status);

            }

            response.sendRedirect("accountmanager?action=all");
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
