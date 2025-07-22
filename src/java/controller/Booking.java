/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DoctorDAO;
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
import model.Doctor;
import model.Service;
import dal.DoctorScheduleDAO;
import java.sql.Date;
import java.sql.Time;
import model.WorkingDateSchedule;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Booking", urlPatterns = {"/booking"})
public class Booking extends HttpServlet {

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
            out.println("<title>Servlet Booking</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Booking at " + request.getContextPath() + "</h1>");
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
        session.setAttribute("token","chuyenkhoa");
       
        
        if (stepName.equals("doctor")) {
            session.removeAttribute("serviceBooking");
            session.removeAttribute("patient");
            DoctorDAO doctDAO = new DoctorDAO();
            String departmentId = request.getParameter("departmentId");
            String departmentName = request.getParameter("departmentName");
            List<Doctor> listDoctor = doctDAO.getAllDoctorInDepartmanentHaveSchedule(Integer.parseInt(departmentId));
            session.setAttribute("departmentName", departmentName);
            session.setAttribute("departmentId", departmentId);
            session.removeAttribute("doctorId");
            session.removeAttribute("doctorName");
            request.setAttribute("listDoctor", listDoctor);  
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            
        } 
        else if (stepName.equals("service")) {
            
            ServiceDAO serviceDao = new ServiceDAO();
            String doctorId = request.getParameter("doctorId");
            String doctorName = request.getParameter("doctorName");
            session.setAttribute("doctorId", doctorId);
            session.setAttribute("doctorName", doctorName);
            session.removeAttribute("serviceBooking");
            session.removeAttribute("isBHYT");
            String departmentId = (String) session.getAttribute("departmentId");
            departmentId = request.getParameter("departmentId");
            var tuan = Integer.parseInt(doctorId);
            var sang = Integer.parseInt(departmentId);
            
            var truong = request.getParameter("truong");
            request.setAttribute("truong", truong);
            List<Service> listService = serviceDao.getServicesByDoctorAndDepartment(Integer.parseInt(doctorId), Integer.parseInt(departmentId));
            request.setAttribute("listService",listService);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        }
        else if(stepName.equals("dateTime")){
            
            DoctorScheduleDAO DSD = new DoctorScheduleDAO();
            ServiceDAO serviceDao = new ServiceDAO();
            String doctorId = (String) session.getAttribute("doctorId");
            List<WorkingDateSchedule> listWDS =  DSD.getWorkingScheduleOfDoctor10Day(Integer.parseInt(doctorId));
            String serviceId = request.getParameter("serviceId");
            String isBHYT = request.getParameter("isBHYT");
            if(isBHYT != null){
               session.setAttribute("isBHYT", isBHYT); 
            }
            Service serviceBooking = serviceDao.getServiceById(Integer.parseInt(serviceId));
            session.setAttribute("serviceBooking", serviceBooking);
            session.removeAttribute("dateBooking");
            session.removeAttribute("slotStart");
            session.removeAttribute("slotEnd");
            request.setAttribute("listWDS", listWDS);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
 
        }
        else if(stepName.equals("chooseRecords")){
            String dateBooking_ = request.getParameter("dateBooking");
            String slotId_ = request.getParameter("slotId");
            String slotStart_ = request.getParameter("slotStart");
            String slotEnd_ = request.getParameter("slotEnd");
            Date dateBooking = Date.valueOf(dateBooking_);
            Time slotStart = Time.valueOf(slotStart_);
            Time slotEnd = Time.valueOf(slotEnd_);
            session.setAttribute("dateBooking",dateBooking );
            session.setAttribute("slotId", slotId_);
            session.setAttribute("slotStart", slotStart);
            session.setAttribute("slotEnd", slotEnd);
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
