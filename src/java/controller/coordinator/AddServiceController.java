/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.coordinator;

import dal.CategoryServiceDAO;
import dal.DepartmentDAO;
import dal.DoctorDAO;
import dal.DoctorServiceDAO;
import dal.QualificationDAO;
import dal.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.AcademicDegree;
import model.AcademicTitle;
import model.CategoryServices;
import model.Deparment;
import model.Doctor;
import model.Position;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddServiceController", urlPatterns = {"/addservice"})
public class AddServiceController extends HttpServlet {

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
        DepartmentDAO deDao = new DepartmentDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        CategoryServiceDAO caDao = new CategoryServiceDAO();
        List<Deparment> listDe = deDao.getAllDeparment();
        List<CategoryServices> listCa = caDao.getAllCategoryServiceses();
        List<Doctor> listDoc = doctorDAO.getDoctorsNotInCategory(2);
        request.setAttribute("listDoc", listDoc);
        request.setAttribute("category", listCa);
        request.setAttribute("department", listDe);
        request.getRequestDispatcher("admin/addService.jsp").forward(request, response);

    }

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ServiceDAO serviceDAO = new ServiceDAO();

        String serviceName = request.getParameter("service_name");
        String bhyt = request.getParameter("is_bhyt");
        String description = request.getParameter("description");
        String categoryServiceId = request.getParameter("category_service_id");
        String derpartmentId = request.getParameter("department_id");
        String fee = request.getParameter("fee").replace(",", "");
        String discount_ = request.getParameter("discount");
        String paymentTypeId = request.getParameter("payment_type_id");
        String img = "default";

        double discount = 0;
        if (discount_ != null) {
            discount = Double.parseDouble(discount_) / 100;
        }

        int serviceId = serviceDAO.addService(serviceName, (Integer.parseInt(bhyt) == 1) ? true : false,
                description,
                Integer.parseInt(categoryServiceId),
                Integer.parseInt(derpartmentId),
                Double.parseDouble(fee), discount,
                Integer.parseInt(paymentTypeId), img);
        if (categoryServiceId.equals("2")) {

            String doctorId = request.getParameter("doctorId");
            DoctorServiceDAO doctorServiceDAO = new DoctorServiceDAO();
            doctorServiceDAO.addDoctorToService(Integer.parseInt(doctorId), serviceId);

        }
        
        if (serviceId > 0) {
            request.setAttribute("success", "success");
            request.getRequestDispatcher("servicemanager?action=all").forward(request, response);
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
