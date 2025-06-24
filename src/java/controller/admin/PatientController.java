/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.PatientDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;
import model.Patient;

/**
 *
 * @author dantr
 */
@WebServlet(name="PatientController", urlPatterns={"/patientmanage"})
public class PatientController extends HttpServlet {

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

        String name = request.getParameter("searchname") == null ? "" : request.getParameter("searchname");
        PatientDAO patientDAO = new PatientDAO();
        int number = patientDAO.searchPatientsByNameWithPagingNumber(name);
        int numberPage = 0;
        if (number % 6 == 0) {
            numberPage = number / 6;
        } else {
            numberPage = (number / 6);
            numberPage++;
        }
        String pageIndex_str = request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex");
        int pageIndex = 1;
        try {
            pageIndex = Integer.parseInt(pageIndex_str);

        } catch (Exception e) {
            pageIndex = 1;
        }
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        if (pageIndex > numberPage) {
            pageIndex = numberPage;
        }

        List<Patient> patients = patientDAO.searchPatientsByNameWithPaging(name, pageIndex, 6);
        request.setAttribute("pagIndex", pageIndex);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("searchname", name);
        request.setAttribute("patientDetails", patients);

        request.getRequestDispatcher("admin/PatientListForAdmin.jsp").forward(request, response);
    }

    protected void processRequestForDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_str = request.getParameter("id") == null ? "" : request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_str);
        } catch (Exception e) {
        }
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.getPatientById(id);
        request.setAttribute("patient", patient);
        patient.setImg(patientDAO.getImgByPatientid(patient.getPatientId()));
        request.getRequestDispatcher("admin/patientdetail.jsp").forward(request, response);
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

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (action.equals("all")) {
            processRequest(request, response);
        } else if (action.equals("detail")) {
            processRequestForDetail(request, response);
        }
//
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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (action.equals("update_patient")) {
            processRequestUpdate(request, response);
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

    protected void processRequestUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String DOB = request.getParameter("DOB");
        String email = request.getParameter("email");
        String bhyt = request.getParameter("bhyt");
        String cccd = request.getParameter("cccd");
        String nation = request.getParameter("nation");
        String job =request.getParameter("job");
        int patientId = Integer.parseInt(request.getParameter("patient_id"));
        Patient p = new Patient();
        p.setPatientId(patientId);
        p.setPatientName(name);
        p.setGender(gender);
        p.setPhone(phone);
        p.setEmail(email);
        p.setBhyt(bhyt);
        p.setCccd(cccd);
        p.setAddress(address);
        p.setNation(nation);
        p.setDob(Date.valueOf(DOB));
        p.setJob(job);
        PatientDAO pdao = new PatientDAO();
        pdao.updatePatient(p);
        response.sendRedirect("patientmanage?action=detail&id="+patientId);
    }

}
