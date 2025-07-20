/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Service;

/**
 *
 * @author DELL
 */
@WebServlet(name = "BookingHealthPackageService", urlPatterns = {"/booking.HealthPackage"})
public class BookingHealthPackageService extends HttpServlet {

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
            out.println("<title>Servlet BookingHealthPackageService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingHealthPackageService at " + request.getContextPath() + "</h1>");
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
        String stepName = request.getParameter("stepName");
        request.setAttribute("stepName", stepName);
        session.setAttribute("isBHYT", "0");
        session.setAttribute("token", "packageService");
        if (stepName.equals("service")) {
            session.removeAttribute("departmentId");
            session.removeAttribute("patient");
            session.removeAttribute("dateBooking");
            session.removeAttribute("slotStart");
            session.removeAttribute("slotEnd");
            ServiceDAO serviceDao = new ServiceDAO();
            String service_id = request.getParameter("service_id");
            String categoryService_id = request.getParameter("categoryService_id");
            session.removeAttribute("serviceBooking");
            List<Service> listService = serviceDao.getServicesByServiceId(Integer.parseInt(service_id));
            request.setAttribute("listService", listService);
            request.setAttribute("categoryService_id", categoryService_id);
            request.getRequestDispatcher("booking.jsp").forward(request, response);

        } else if (stepName.equals("dateTime")) {
            String serviceId = request.getParameter("serviceId");
            

        } else if (stepName.equals("chooseRecords")) {
              String serviceId = request.getParameter("serviceId");
            ServiceDAO serviceDao = new ServiceDAO();
            Service serviceBooking = serviceDao.getServiceById(Integer.parseInt(serviceId));
            session.setAttribute("serviceBooking", serviceBooking);

            response.sendRedirect("chooseRecords");
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
