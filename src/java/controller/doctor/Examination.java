/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dal.MedicalRecordDAO;
import dal.MedicineDAO;
import dal.PatientDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.AccountUser;
import model.Medicine;
import model.Patient;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Examination", urlPatterns = {"/examination"})
public class Examination extends HttpServlet {

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
            out.println("<title>Servlet Examination</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Examination at " + request.getContextPath() + "</h1>");
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
        AccountUser acc = (AccountUser) session.getAttribute("user");
        if (acc == null) {
            response.sendRedirect("home");
            return;
        }
        UserDAO userDAO = new UserDAO();

        MedicineDAO medicineDAO = new MedicineDAO();
        List<Medicine> medicineList = medicineDAO.getAllMedicines();
        request.setAttribute("medicineList", medicineList);
        int doctorId = userDAO.getDoctorIdByUsername(acc.getUsername());
        request.setAttribute("doctorId", doctorId);
        String patientId = request.getParameter("patientId");
        PatientDAO patientDAO = new PatientDAO();
        Patient patient = patientDAO.getPatientById(Integer.parseInt(patientId));
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("medicalRecord.jsp").forward(request, response);

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
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int patientId = Integer.parseInt(request.getParameter("patientId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));


        String symptoms = request.getParameter("symptoms");
        String diagnosis = request.getParameter("diagnosis");
        String conclusion = request.getParameter("conclusion");


        String instruction = request.getParameter("instruction");
        String note = request.getParameter("note");

        String[] selectedMedicineIds = request.getParameterValues("medicineIds");

        
     
        MedicalRecordDAO dao = new MedicalRecordDAO();
        boolean success = dao.insertMedicalRecord(
                patientId,
                doctorId,
                symptoms,
                diagnosis,
                conclusion,
                instruction,
                note,
                selectedMedicineIds
        );

        if (success) {
              response.sendRedirect("myPatient");
        } else {
            request.setAttribute("error", "Lưu bệnh án thất bại.");
            request.getRequestDispatcher("recordForm.jsp").forward(request, response);
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
