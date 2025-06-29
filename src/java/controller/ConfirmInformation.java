/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Patient;
import dal.PatientDAO;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AccountUser;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ConfirmInformation", urlPatterns = {"/confirmInformation"})
public class ConfirmInformation extends HttpServlet {

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
            out.println("<title>Servlet ConfirmInformation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmInformation at " + request.getContextPath() + "</h1>");
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
        String patientId = request.getParameter("patientId");
        Patient patient = null;
         PatientDAO pDao = new PatientDAO();
        if(patientId == null){
            patient = (Patient) session.getAttribute("patient");
        }else{
            patient = pDao.getPatientById(Integer.parseInt(patientId));
        }
       
        
        String isBHYT = request.getParameter("isBHYT"); //  thay đổi không còn muốn sử dụng bhyt mới có dòng này , tức là các trưgf hợp khác 
        if (isBHYT == null) { // đầu tiên vào là ko có request cho nên == null ,khác null tức là  = 0 thì chạy bình thường 
            isBHYT = (String) session.getAttribute("isBHYT");
        }else{ // tức là có thay đổi ở đây
            session.setAttribute("changeY", "change");
            session.setAttribute("patient", patient);
            request.getRequestDispatcher("confirmInformation.jsp").forward(request, response);
            return;
        }
        if (isBHYT.equals("0")) { // ko sử dụng bhyt => ko cần chek có hay ko 
            
            session.setAttribute("patient", patient);
            request.getRequestDispatcher("confirmInformation.jsp").forward(request, response);

        } else if (isBHYT.equals("1") && patient.getBhyt().isEmpty()) { //  bảo có  nhưung hồ sơ ko có => sai
            request.setAttribute("error", "Hồ sơ này chưa cập nhật BHYT");
            request.setAttribute("patientId", patientId);
            AccountUser acc = (AccountUser) session.getAttribute("user");
            if (acc == null) {
                response.sendRedirect("login");
                return;
            }
            List<Patient> listPa = pDao.getPatientByUsername(acc.getUsername());
            request.setAttribute("records", listPa);
            if (acc == null) {
                response.sendRedirect("login");
                return;
            }
            request.setAttribute("records", listPa);
            request.getRequestDispatcher("chooseRecords.jsp").forward(request, response);

        } else if (isBHYT.equals("1") && !patient.getBhyt().isEmpty()) { //  bảo muốn có và hồ sơ có bhyt
            
            session.setAttribute("patient", patient);
            request.getRequestDispatcher("confirmInformation.jsp").forward(request, response);
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
        processRequest(request, response);
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
