/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.coordinator;

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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Deparment;
import model.Doctor;
import model.Position;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DoctorScheduleManager", urlPatterns = {"/doctorschedule"})
public class DoctorScheduleManager extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.setAttribute("stopRemain", false);
        String action = request.getParameter("action");
        String url = null;
        DoctorDAO dDao = new DoctorDAO();
        DepartmentDAO deDao = new DepartmentDAO();
        PositionDAO pDao = new PositionDAO();
        List<Doctor> listDoctor = null;
        List<Deparment> listDeparment = deDao.getAllDeparment();
        List<Position> listPosition = pDao.getAllPosition();

        try (PrintWriter out = response.getWriter()) {
            if (action.equals("all")) {
                listDoctor = dDao.getAllDoctorHaveSchedule();
                url = "doctorschedule?action=all";
            }
            if (action.equals("search")) {
                String text = request.getParameter("text");
                request.setAttribute("text", text);
                listDoctor = dDao.getAllDoctorHaveScheduleBySearchNameOrUsername(text);
                url = "doctorschedule?action=search&text=" + text;
            }
            if (action.equals("filter")) {

                String department = request.getParameter("department_id");

                request.setAttribute("department_id", department);

                listDoctor = dDao.getAllDoctorHaveScheduleFilterDepartment(department);
                url = "doctorschedule?action=filter&&department=" + department;

            }

            if (listDoctor != null) {
                int page, numberPerPage = 9;
                int size = listDoctor.size();
                int numberPage = (size % numberPerPage == 0) ? (size / numberPerPage) : ((size / numberPerPage) + 1);
                String xPage = request.getParameter("page");
                if (xPage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xPage);
                }
                int start = (page - 1) * numberPerPage;
                int end = Math.min(page * numberPerPage, size);
                int numPageDisplay = 7;
                List<Doctor> listDoctorDisplay = getListDoctorDisplay(listDoctor, start, end);
                request.setAttribute("position", listPosition);
                request.setAttribute("department", listDeparment);
                request.setAttribute("doctor", listDoctorDisplay);
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("start", start);
                request.setAttribute("num", numberPage);
                request.setAttribute("numPageDisplay", numPageDisplay);
                request.getRequestDispatcher("admin/doctorSchedule.jsp").forward(request, response);
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
