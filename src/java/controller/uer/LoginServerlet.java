/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import config.EncodeData;
import config.PasswordUtils;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.AccountUser;

/**
 *
 * @author DELL
 */
@WebServlet(name = "LoginServerlet", urlPatterns = {"/login"})
public class LoginServerlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        UserDAO dao = new UserDAO();
        String action = "";
        action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (action.equals("login")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (action.equals("checkLogin")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String remember = request.getParameter("remember");
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            AccountUser account = dao.Login(username);

            if (account == null) {    
                request.setAttribute("error", "Tài khoản không tồn tại!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            boolean isMatch = PasswordUtils.checkPassword(password, account.getPassword());
            if (!isMatch) {
                request.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else if (account.getStatus() == 0) {
                request.setAttribute("error", "Tài khoản đã bị khóa !");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else if (account.getStatus() == 2) {
                request.setAttribute("acc", account);
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            } else {

                session.setAttribute("user", account);
                Cookie cuname = new Cookie("username", username);
                Cookie cpass = new Cookie("password", password);
                Cookie rem = new Cookie("remember", remember);
                if (remember != null) {
                    cuname.setMaxAge(60 * 60 * 24 * 30);
                    cpass.setMaxAge(60 * 60 * 24 * 30);
                    rem.setMaxAge(60 * 60 * 24 * 30);
                } else {
                    cuname.setMaxAge(0);
                    cpass.setMaxAge(0);
                    rem.setMaxAge(0);
                }
                if (account.getRole() == 1) {
                    response.sendRedirect("dashboard");
                } else if (account.getRole() == 2) {
                    response.sendRedirect("dashboard");
                } else if (account.getRole() == 3) {
                    response.sendRedirect("dashboard");
                } else if (account.getRole() == 4) {
                    response.sendRedirect("home");
                } else if (account.getRole() == 5) {
                    response.sendRedirect("home");
                } else {
                    response.sendRedirect("home");
                }

            }

        }

    }

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        AccountUser account = dao.Login("admin");
        System.out.println(account);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServerlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServerlet.class.getName()).log(Level.SEVERE, null, ex);
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
