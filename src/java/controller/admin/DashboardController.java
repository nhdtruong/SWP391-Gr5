/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import DTOStatic.StaticDepartment;
import dal.AppointmentDAO;
import dal.DepartmentDAO;
import dal.DoctorDAO;
import dal.PatientDAO;
import dal.PaymentDAO;
import dal.ServiceDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Deparment;
import model.Service;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard"})
public class DashboardController extends HttpServlet {

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
        request.getRequestDispatcher("admin/dashboard.jsp").forward(request, response);
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
        PatientDAO patientDAO = new PatientDAO();
        var sobenhnhan = patientDAO.CountPatient();
        DoctorDAO doctorDAO = new DoctorDAO();
        var sobacsi = doctorDAO.CountNumberDoctor();
        PaymentDAO paymentDAO = new PaymentDAO();
        var total_revenue = paymentDAO.getTotalRevenue();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        var total_Appointment = appointmentDAO.CountAppointment();
        UserDAO userDAO = new UserDAO();
        var total_user = userDAO.CountUser();

        PaymentDAO dao = new PaymentDAO();
        List<Map<String, Object>> revenueList = dao.getRevenueByMonth(2025);
        request.setAttribute("revenueList", revenueList);
        List<Map<String, Object>> stats = appointmentDAO.getAppointmentsLast5Days();

        List<Map<String, Object>> test = appointmentDAO.getAppointmentsLast5Days();
        System.out.println(">>>> appointmentStats size = " + test.size());
        for (Map<String, Object> m : test) {
            System.out.println(m.get("day") + " => " + m.get("total"));
        }
        DepartmentDAO departmentDAO = new DepartmentDAO();
        var listDep = departmentDAO.getAllDeparment();
        List<StaticDepartment> staticDepartments = new ArrayList<>();
        for (Deparment dep : listDep) {
            StaticDepartment sd = new StaticDepartment();
            sd.setId(dep.getId());
            sd.setDepartment_name(dep.getDepartment_name());
            sd.setNumber(departmentDAO.CountDoctorInDepartment(dep.getId()));
            
            staticDepartments.add(sd);
        }
        
        for (var deparment : staticDepartments) {
            System.out.println(deparment.getDepartment_name());
            System.out.println(deparment.getNumber());
        }
        
        ServiceDAO serviceDAO = new ServiceDAO();
        var serviceList = serviceDAO.getAllServices();
        List<StaticDepartment> staticDepartmentss = new ArrayList<>();
        for (Service service : serviceList) {
            StaticDepartment sd = new StaticDepartment();
            sd.setId(service.getService_id());
            sd.setDepartment_name(service.getService_name());
            sd.setNumber(serviceDAO.CountService(service.getService_id()));
            
            staticDepartmentss.add(sd);
        }
        
        
        
        request.setAttribute("staticDepartmentss", staticDepartmentss);
        request.setAttribute("staticDepartments", staticDepartments);
        request.setAttribute("appointmentStats", stats);
        request.setAttribute("total_user", total_user);
        request.setAttribute("total_Appointment", total_Appointment);
        request.setAttribute("sobenhnhan", sobenhnhan);
        request.setAttribute("sobacsi", sobacsi);
        request.setAttribute("total_revenue", total_revenue);
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
