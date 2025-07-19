/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.CategoryServiceDAO;
import dal.DepartmentDAO;
import dal.DoctorServiceDAO;
import dal.ServiceDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryServices;
import model.Deparment;
import model.Doctor;
import model.Service;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UpdateDeleteService", urlPatterns = {"/updateservice"})
public class UpdateDeleteService extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("dashboard");
            return;
        }
        if (action.equals("updateService")) {
            String serviceId = request.getParameter("serviceId");
            ServiceDAO sDao = new ServiceDAO();
            Service s = sDao.getServiceById(Integer.parseInt(serviceId));
            DepartmentDAO deDao = new DepartmentDAO();
            CategoryServiceDAO caDao = new CategoryServiceDAO();
            List<Deparment> listDe = deDao.getAllDeparment();
            List<CategoryServices> listCa = caDao.getAllCategoryServiceses();
            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            Doctor doctor = doctorServiceDAO.getDoctorByService(Integer.parseInt(serviceId));
            request.setAttribute("doctor", doctor);
            request.setAttribute("category", listCa);
            request.setAttribute("department", listDe);
            request.setAttribute("service", s);
            request.getRequestDispatcher("admin/updateService.jsp").forward(request, response);
        }
        if (action.equals("update")) {

            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            ServiceDAO serviceDAO = new ServiceDAO();
            String serviceId = request.getParameter("serviceId");
            String serviceName = request.getParameter("service_name");
            String bhyt = request.getParameter("is_bhyt");
            String description = request.getParameter("description");
            String categoryServiceId = request.getParameter("category_service_id");
            String derpartmentId = request.getParameter("department_id");
            String fee = request.getParameter("fee").replace(",", "");
            String discount_ = request.getParameter("discount");
            String paymentTypeId = request.getParameter("payment_type_id");
            System.out.println("fee" + fee + "serciid" + serviceId);
            String img = "default";
            double discount = 0;
            if (discount_ != null) {
                discount = Double.parseDouble(discount_) / 100;
            }
            serviceDAO.updateService(Integer.parseInt(serviceId), serviceName,
                    (Integer.parseInt(bhyt) == 1) ? true : false, description,
                    Integer.parseInt(categoryServiceId), Integer.parseInt(derpartmentId),
                    Double.parseDouble(fee),
                    discount, Integer.parseInt(paymentTypeId),
                    img);

            request.setAttribute("success", "success");
            request.getRequestDispatcher("servicemanager?action=all").forward(request, response);

        }

        if (action.equals("delete")) {
            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            String service_id = request.getParameter("service_id");
            String returnUrl = request.getParameter("returnUrl");

            doctorServiceDAO.removeDoctorServiceByServiceId(Integer.parseInt(service_id));
            ServiceDAO serviceDAO = new ServiceDAO();
            serviceDAO.deleteService(Integer.parseInt(service_id));

            response.sendRedirect("servicemanager?action=all"); // fallback

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
