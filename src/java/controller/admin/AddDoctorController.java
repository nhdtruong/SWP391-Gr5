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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import model.AcademicDegree;
import model.AcademicTitle;
import model.AccountUser;
import model.Deparment;
import model.Position;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddDoctorController", urlPatterns = {"/adddoctor"})
public class AddDoctorController extends HttpServlet {

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
        QualificationDAO qua = new QualificationDAO();
        DepartmentDAO d = new DepartmentDAO();
        List<Position> listP = qua.getAllPosition();
        List<AcademicDegree> listAd = qua.getAllAcademicDegree();
        List<AcademicTitle> listAt = qua.getAllAcademicTitle();
        List<Deparment> listD = d.getAllDeparment();
        request.setAttribute("Position", listP);
        request.setAttribute("AcademicDegree", listAd);
        request.setAttribute("AcademicTitle", listAt);
        request.setAttribute("Department", listD);
        request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);

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

        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        QualificationDAO qua = new QualificationDAO();
        DepartmentDAO de = new DepartmentDAO();
        List<Position> listP = qua.getAllPosition();
        List<AcademicDegree> listAd = qua.getAllAcademicDegree();
        List<AcademicTitle> listAt = qua.getAllAcademicTitle();
        List<Deparment> listD = de.getAllDeparment();
        request.setAttribute("Position", listP);
        request.setAttribute("AcademicDegree", listAd);
        request.setAttribute("AcademicTitle", listAt);
        request.setAttribute("Department", listD);
        
        UserDAO u = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        String doctorName = request.getParameter("doctorName");
        String gender = request.getParameter("gender");
        String departmentId_ = request.getParameter("departmentId");
        String positionId_ = request.getParameter("positionId");
        String academicTitleId_ = request.getParameter("academicTitleId");
        String academicDegreeId_ = request.getParameter("academicDegreeId");
        int role_id = 4;
        String img = "default";
        int status = 2; // chưa đc đăng nhập cần đổi mk  // 0 bị khóa , 1 : ok 

        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("username", username);
        request.setAttribute("doctorName", doctorName);
        request.setAttribute("gender", gender);
        request.setAttribute("departmentId", departmentId_);
        request.setAttribute("positionId", positionId_);
        request.setAttribute("academicDegreeId", academicDegreeId_);
        request.setAttribute("academicTitleId", academicTitleId_);

        AccountUser account1 = u.CheckAccByUsername(username);
        AccountUser account2 = u.CheckAccByEmail(email);
  
        if (account1 != null && account2 != null) {
            request.setAttribute("errorEM", "Email đã tồn tại!");
            request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
            return;
        } else if (account1 != null) {
            request.setAttribute("errorTK", "Tên đăng nhập đã tồn tại!");
            request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
            return;
        } else if (account2 != null) {
            request.setAttribute("errorEM", "Email đã tồn tại!");
            request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
            return;
        } else {

            int departmentId = Integer.parseInt(departmentId_);
            int positionId = Integer.parseInt(positionId_);
            int academicTitleId = Integer.parseInt(academicTitleId_);
            int academicDegreeId = Integer.parseInt(academicDegreeId_);
            
            u.RegisterNewUser(username,
                    role_id,
                    password,
                    email,
                    img,
                    status);
            DoctorDAO d = new DoctorDAO();

            d.InsertDoctorByAdmin(username, doctorName,
                    gender,
                    departmentId, positionId,
                    academicTitleId, academicDegreeId,
                    status,img);
        
            request.setAttribute("success", "success");
            request.getRequestDispatcher("admin/adddoctor.jsp").forward(request, response);
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
