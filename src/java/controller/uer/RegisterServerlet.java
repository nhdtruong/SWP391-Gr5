/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.uer;

import config.EmailSender;
import dal.UserDAO;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AccountUser;
import jakarta.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import config.EmailSender;
import config.EncodeData;
import model.OTPdata;
import model.OTPutil;
/**
 *
 * @author DELL
 */
@WebServlet(name="RegisterServerlet", urlPatterns={"/register"})
public class RegisterServerlet extends HttpServlet {
   //nhom nam 123
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
       String action = request.getParameter("action");
        if(action == null){
           request.getRequestDispatcher("register.jsp").forward(request, response);
           return;
       }
       if(action.equals("register")){
           request.getRequestDispatcher("register.jsp").forward(request, response);
           return;
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
    throws ServletException, IOException, UnsupportedEncodingException {
        
                response.setContentType("text/html;charset=UTF-8");
                request.setCharacterEncoding("UTF-8");
                UserDAO u = new UserDAO();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String repassword = request.getParameter("repassword");
                String email = request.getParameter("email");
                int role_id = 5;
                String img = "default";
                int  status = 1;
     
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("repassword", repassword);
                request.setAttribute("username", username);
                String enPassWord = EncodeData.enCode(password);
               
                AccountUser account1 = u.CheckAccByUsername(username );
                AccountUser account2 = u.CheckAccByEmail(email);
                AccountUser accountUser = new AccountUser(username, role_id, enPassWord, email, img, status);
                
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
                    HttpSession session = request.getSession();
                    session.setAttribute("prepareAccountRegister", accountUser);
                    long expiryTime = System.currentTimeMillis() + 5 * 60 * 1000;
                    String otp =OTPutil.generateOTP() ;
                    OTPdata otpData = new OTPdata(otp, expiryTime);
                    session.setAttribute("currentOTP", otpData);
                  try {
                      EmailSender.sendOTP(otp,email);
                  } catch (MessagingException ex) {
                      Logger.getLogger(RegisterServerlet.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    response.sendRedirect("accepcode");
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
