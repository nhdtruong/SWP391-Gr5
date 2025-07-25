/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.coordinator;

import dal.DoctorDAO;
import dal.DoctorServiceDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Doctor;
import model.Service;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DoctorService", urlPatterns = {"/doctorService"})
public class DoctorService extends HttpServlet {

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
            out.println("<title>Servlet DoctorService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoctorService at " + request.getContextPath() + "</h1>");
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

        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        String departmentId_R = request.getParameter("departmentId");
        if (departmentId_R.isEmpty()) {

            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            ServiceDAO serviceDAO = new ServiceDAO();
            Service service = serviceDAO.getServiceById(serviceId);
            request.setAttribute("service", service);
            List<Doctor> doctorsOfService = doctorServiceDAO.getDoctorsByService(serviceId);
            request.setAttribute("doctorsOfService", doctorsOfService);
            request.getRequestDispatcher("admin/doctorService.jsp").forward(request, response);
            
            return;
        }
        int departmentId = Integer.parseInt(departmentId_R);

        DoctorDAO doctorDAO = new DoctorDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
        Service service = serviceDAO.getServiceById(serviceId);
        List<Doctor> doctorsInDepartment = doctorDAO.getDoctorsNotInService(departmentId, serviceId);
        List<Doctor> doctorsOfService = doctorServiceDAO.getDoctorsByService(serviceId);

        request.setAttribute("service", service);
        request.setAttribute("doctorsInDepartment", doctorsInDepartment);
        request.setAttribute("doctorsOfService", doctorsOfService);
        request.getRequestDispatcher("admin/doctorService.jsp").forward(request, response);

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

        if (action.equals("add")) {
            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            doctorServiceDAO.addDoctorToService(doctorId, serviceId);
            response.sendRedirect("doctorService?serviceId=" + serviceId + "&departmentId=" + departmentId);

        } else if (action.equals("delete")) {

            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            int serviceId = Integer.parseInt(request.getParameter("serviceId"));
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            int departmentId = Integer.parseInt(request.getParameter("departmentId"));
            doctorServiceDAO.removeDoctorFromService(doctorId, serviceId);
            response.sendRedirect("doctorService?serviceId=" + serviceId + "&departmentId=" + departmentId);

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
