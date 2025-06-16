/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DepartmentDAO;
import dal.DoctorDAO;
import dal.QualificationDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.AcademicDegree;
import model.AcademicTitle;
import model.Deparment;
import model.Doctor;
import model.Position;

/**
 *
 * @author DELL
 */
@MultipartConfig
@WebServlet(name = "UpdateDeleteDoctor", urlPatterns = {"/updatedoctor"})
public class UpdateDeleteDoctor extends HttpServlet {

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
        String action = request.getParameter("action");
        if (action == null) {

            response.sendRedirect("dashboard");
            return;
        }
        if (action.equals("updateDoc")) {
            String doctorId = request.getParameter("doctorId");
            DoctorDAO dDAO = new DoctorDAO();
            Doctor d = dDAO.getDoctorByDoctorId(doctorId);
            DepartmentDAO deDao = new DepartmentDAO();
            List<Deparment> listDe = deDao.getAllDeparment();
            QualificationDAO qua = new QualificationDAO();
            List<Position> listP = qua.getAllPosition();
            List<AcademicDegree> listAd = qua.getAllAcademicDegree();
            List<AcademicTitle> listAt = qua.getAllAcademicTitle();

            request.setAttribute("Position", listP);
            request.setAttribute("AcademicDegree", listAd);
            request.setAttribute("AcademicTitle", listAt);
            request.setAttribute("department", listDe);
            request.setAttribute("doctor", d);
            request.getRequestDispatcher("admin/updatedoctor.jsp").forward(request, response);
        }
        if (action.equals("update")) {

            String doctor_id = request.getParameter("doctor_id");
            String doctor_name = request.getParameter("name");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String DOB = request.getParameter("DOB");
            String description = request.getParameter("description");
            String department_id = request.getParameter("department_id");
            String EducationHistory = request.getParameter("EducationHistory");
            String specialized = request.getParameter("specialized");
            String positionId = request.getParameter("positionId");
            String academicDegreeId = request.getParameter("academicDegreeId");
            String academicTitleId = request.getParameter("academicTitleId");
            String status = request.getParameter("status");

            DoctorDAO d = new DoctorDAO();
            UserDAO udao = new UserDAO();
            String username = d.getUsernameByDoctorId(doctor_id);
            udao.updateAccountByAdmin(username, 4, Integer.parseInt(status));

            d.updateDoctor(Integer.parseInt(doctor_id), doctor_name,
                    gender, phone, DOB, description, Integer.parseInt(department_id), Integer.parseInt(status), specialized, EducationHistory,
                    Integer.parseInt(positionId), Integer.parseInt(academicDegreeId), Integer.parseInt(academicTitleId));

            response.sendRedirect("doctormanager?action=all");

        }

        if (action.equals("delete")) {
            String doctor_id = request.getParameter("doctor_id");
            DoctorDAO d = new DoctorDAO();
            String username = d.getUsernameByDoctorId(doctor_id);
            UserDAO u = new UserDAO();
            d.deleteDoctorByAdmin(username);
            u.deleteAccountByAdmin(username);
            request.setAttribute("deleteDoctorSuccess", "deleteDoctorSuccess");
            request.getRequestDispatcher("admin/doctor.jsp").forward(request, response);

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
