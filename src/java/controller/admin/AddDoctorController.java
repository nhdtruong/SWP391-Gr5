/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import config.EmailSender;
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
@WebServlet(name="AddDoctorController", urlPatterns={"/adddoctor"})
public class AddDoctorController extends HttpServlet {
   
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
        request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
        
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
        response.setContentType("text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                UserDAO u = new UserDAO();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                String email = request.getParameter("email");
                int role_id = 4;
                String img = "default";
                int  status = 2; // chưa đc đăng nhập cần đổi mk  // 0 bị khóa , 1 : ok 
     
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("username", username);
               
                AccountUser account1 = u.CheckAccByUsername(username );
                AccountUser account2 = u.CheckAccByEmail(email);
                AccountUser accountUser = new AccountUser(username, role_id, password, email, img, status);
                 if(account1 != null && account2 != null ){
                    request.setAttribute("errorEM", "Email đã tồn tại!");
                    request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }else if  (account1 != null ) {
                    request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                } else if (account2!= null ){
                    request.setAttribute("errorEM", "Email đã tồn tại!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                    return;
                }else{
                    
                    
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
