/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.uer;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import model.Patient;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateRecords", urlPatterns = {"/updaterecords"})
public class UpdateRecords extends HttpServlet {

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
            out.println("<title>Servlet UpdateRecords</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRecords at " + request.getContextPath() + "</h1>");
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
        PatientDAO pDAO = new PatientDAO();
        String id = request.getParameter("id");
        Patient records = pDAO.getPatientById(Integer.parseInt(id));
        session.setAttribute("currentRecords", records);
        String year = String.valueOf(records.getDob().toLocalDate().getYear());
        String month = String.valueOf(records.getDob().toLocalDate().getMonthValue());
        String day = String.valueOf(records.getDob().toLocalDate().getDayOfMonth());
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("day", day);
        request.setAttribute("records", records);
        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
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

        if (action.equals("update")) {
            HttpSession session = request.getSession();
            Patient currentRecords = (Patient) session.getAttribute("currentRecords");
            if(currentRecords == null){
                response.sendRedirect("login");
            }
            PatientDAO pDAO = new PatientDAO();
            String patientId = request.getParameter("patientId");
            String patientName = request.getParameter("patientName");
            String gender = request.getParameter("gender");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String job = request.getParameter("job");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String bhyt = request.getParameter("bhyt");
            String nation = request.getParameter("nation");
            String cccd = request.getParameter("cccd");
            String address = request.getParameter("address");
            request.setAttribute("year", year);
            request.setAttribute("month", month);
            request.setAttribute("day", day);
            String dobStr = year + "-" + month + "-" + day;
            Date dob = Date.valueOf(dobStr);

            Patient pTemp = new Patient(Integer.parseInt(patientId), patientName, gender, dob, job, phone, email, bhyt, nation, cccd, address);
            request.setAttribute("records", pTemp);
            
            if (bhyt.trim().isEmpty()) {
                if (!cccd.equals(currentRecords.getCccd())) {
                    Boolean isCCCD = pDAO.isCCCDExists(cccd);
                    if (isCCCD) {
                        request.setAttribute("errorCCCD", "Mã định danh đã tồn tại trên hệ thống.");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    }
                }

            } else {
                if (!cccd.equals(currentRecords.getCccd()) && !bhyt.equals(currentRecords.getBhyt())) {
                    Boolean isCCCD = pDAO.isCCCDExists(cccd);
                    Boolean isBHYT = pDAO.isBHYTExists(bhyt);
                    if (isCCCD && isBHYT) {
                        request.setAttribute("errorCCCD", "Mã đinh danh đã tồn tại trên hệ thống.");
                        request.setAttribute("errorBHYT", "Mã BHYT đã tồn tại trên hệ thống");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    } else if (isCCCD) {
                        request.setAttribute("errorCCCD", "Mã đinh danh đã tồn tại trên hệ thống.");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    } else if (isBHYT) {
                        request.setAttribute("errorBHYT", "Mã BHYT đã tồn tại trên hệ thống");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    }
                }else if(!cccd.equals(currentRecords.getCccd()) && bhyt.equals(currentRecords.getBhyt())){
                    Boolean isCCCD = pDAO.isCCCDExists(cccd);
                    if (isCCCD) {
                        request.setAttribute("errorCCCD", "Mã định danh đã tồn tại trên hệ thống.");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    }
                    
                }else if(cccd.equals(currentRecords.getCccd()) && !bhyt.equals(currentRecords.getBhyt())){
                    Boolean isBHYT = pDAO.isBHYTExists(bhyt);
                    if (isBHYT) {
                        request.setAttribute("errorBHYT", "Mã BHYT đã tồn tại trên hệ thống");
                        request.getRequestDispatcher("updaterecords.jsp").forward(request, response);
                        return;
                    }
                }

            }
            pDAO.updatePatient(pTemp);
            response.sendRedirect("records");

        }

        if (action.equals("delete")) {
            System.out.println(">>> doPost được gọi");
            System.out.println(">>> action = " + action);
            String patientId = request.getParameter("patientId");
            System.out.println(">>> patientId = '" + patientId + "'");
            PatientDAO pDao = new PatientDAO();

            pDao.deletePatientById(Integer.parseInt(patientId));
            response.sendRedirect("records");
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
