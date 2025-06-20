/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DepartmentDAO;
import dal.DoctorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Deparment;
import model.Doctor;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DoctorController", urlPatterns = {"/doctor"})
public class DoctorController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        DepartmentDAO ddao = new DepartmentDAO();

        List<Deparment> listDepartment = ddao.getAllDeparment();
        request.setAttribute("listDepartment", listDepartment);

        String gender = request.getParameter("gender") == null ? "all" : request.getParameter("gender");

        String gender_str = "";
        if (gender == "all") {
            gender_str = "";
        } else if (gender.equals("true")) {
            gender_str = "Nam";
        } else {
            gender_str = "Nữ";
        }

        String speciality = request.getParameter("speciality");
        String SortType = request.getParameter("SortType") == null ? "" : request.getParameter("SortType");
        int speciality_id = 0;
        int[] departmentIds = new int[0];
        try {
            speciality_id = Integer.parseInt(speciality);
            departmentIds[0] = speciality_id;
        } catch (Exception e) {
        }

        DoctorDAO doctorDAO = new DoctorDAO();

        String pageIndex_str = request.getParameter("pagIndex") == null ? "1" : request.getParameter("pagIndex");
        int pageIndex = 0;
        try {
            pageIndex = Integer.parseInt(pageIndex_str);
        } catch (Exception e) {
        }

        List<Doctor> doctors = doctorDAO.GetListDoctor(gender_str, departmentIds, SortType, pageIndex, 6);
//        PrintWriter out = response.getWriter();
//        out.print("gender: "+gender_str);
//        out.print("departmentIds: "+departmentIds.length);
//        out.print("SortType: "+SortType);
//        out.print("pageIndex: "+pageIndex);
//        out.print("size: "+doctors.size());
//        out.print("\n"+doctorDAO.sang);
        request.setAttribute("doctor", doctors);

        request.getRequestDispatcher("doctor.jsp").forward(request, response);
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
    protected void processRequest2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        DoctorDAO doctorDAO = new DoctorDAO();
        Doctor doctor = doctorDAO.getDoctorByDoctorId(id);
        request.setAttribute("doctor", doctor);
        request.getRequestDispatcher("doctordetail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("detail")) {
            processRequest2(request, response);
        } else {
            processRequest(request, response);
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
