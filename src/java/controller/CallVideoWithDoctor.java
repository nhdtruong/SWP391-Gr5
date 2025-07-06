/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DepartmentDAO;
import dal.DoctorDAO;

import dal.PositionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Deparment;
import model.Doctor;


/**
 *
 * @author DELL
 */
@WebServlet(name = "CallVideoWithDoctor", urlPatterns = {"/callVideoWithDoctor"})
public class CallVideoWithDoctor extends HttpServlet {

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
            out.println("<title>Servlet CallVideoWithDoctor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CallVideoWithDoctor at " + request.getContextPath() + "</h1>");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String categoryService_id = request.getParameter("categoryService_id");

        String url = null;
        DoctorDAO dDao = new DoctorDAO();
        DepartmentDAO deDao = new DepartmentDAO();
        List<Doctor> listDoctorCall  = null;
        List<Deparment> listDeparment = deDao.getAllDeparment();


        try (PrintWriter out = response.getWriter()) {
            if (action.equals("all")) {
                listDoctorCall = dDao.getDoctorsByServiceCategory(Integer.parseInt(categoryService_id));
                url = "callVideoWithDoctor?action=all&categoryService_id="+categoryService_id;
            }
           
            if (action.equals("filter")) {

                String gender = request.getParameter("gender");
                String department_id = request.getParameter("department_id");

                request.setAttribute("gender", gender);
                request.setAttribute("department_id", department_id);

                listDoctorCall = dDao.getDoctorsByServiceCategoryFilter(Integer.parseInt(categoryService_id),gender, department_id);
                url = "callVideoWithDoctor?action=filter&categoryService_id="+categoryService_id+"&gender="+gender+"&department_id="+department_id;

            }

            if (listDoctorCall != null) {
                int page, numberPerPage = 6;
                int size = listDoctorCall.size();
                int numberPage = (size % numberPerPage == 0) ? (size / numberPerPage) : ((size / numberPerPage) + 1);
                String xPage = request.getParameter("page");
                if (xPage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xPage);
                }
                int start = (page - 1) * numberPerPage;
                int end = Math.min(page * numberPerPage, size);
                int numPageDisplay = 4;
                List<Doctor> listDoctorDisplay = getListDoctorDisplay(listDoctorCall, start, end);
                request.setAttribute("categoryService_id",categoryService_id);
                request.setAttribute("department", listDeparment);
                request.setAttribute("listD", listDoctorDisplay);
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("num", numberPage);
                request.setAttribute("numPageDisplay", numPageDisplay);
                request.getRequestDispatcher("callVideoDoctor.jsp").forward(request, response);
            }

        }


    }
    
    
     private List<Doctor> getListDoctorDisplay(List<Doctor> listDoctor, int start, int end) {
        List<Doctor> listDoctorDisplay = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listDoctorDisplay.add(listDoctor.get(i));
        }
        return listDoctorDisplay;
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
