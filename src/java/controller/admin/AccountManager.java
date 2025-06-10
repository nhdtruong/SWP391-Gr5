/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.RoleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.AccountUser;
import dal.UserDAO;
import java.util.ArrayList;
import model.Role;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AccountManager", urlPatterns = {"/accountmanager"})
public class AccountManager extends HttpServlet {

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

        UserDAO uDao = new UserDAO();
        RoleDao rDao = new RoleDao();
        List<AccountUser> listAcc = null;
        List<Role> listRole = rDao.getListRole();
        String url = null;
        request.setAttribute("role", listRole);
        String action = request.getParameter("action");
        
        try {
            if (action.equals("all")) {
                url = "accountmanager?action=all";
                listAcc = uDao.getListAllAccount();
            }
            if (action.equals("search")){
                String text = request.getParameter("text");
                request.setAttribute("text",text);
                url = "accountmanager?action=all&text="+text;
                listAcc = uDao.SearchAll(text);
            }
            if (action.equals("filter")) {
                String roleId = request.getParameter("roleId");
                String status = request.getParameter("status");
                request.setAttribute("role_id",roleId);
                request.setAttribute("status", status);
                if (roleId.equals("all") && status.equals("all")) {
                    response.sendRedirect("accountmanager?action=all");
                } else if (roleId.equals("all")) {
                    listAcc = uDao.getAccByFilterStatus(status);
                    url = "accountmanager?action=filter&roleId=all&status="+status;
                } else if (status.equals("all")) {
                    listAcc = uDao.getAccByFilterRoleId(roleId);
                    url = "accountmanager?action=filter&roleId="+roleId+"&status=all";
                } else {
                    listAcc = uDao.getAccByFilter(roleId,status);
                    url = "accountmanager?action=filter&roleId="+roleId +"&status="+status;
                }

            }

            if ( listAcc != null ) {
                int page, numberPerPage = 8;
                int size = listAcc.size();
                int numberPage = (size % numberPerPage == 0) ? (size / numberPerPage) : ((size / numberPerPage) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null ) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start = (page - 1) * numberPerPage;

                int end = Math.min(page * numberPerPage, size);

                List<AccountUser> listAccDisplay = getListAccDisplay(listAcc, start, end);

                request.setAttribute("account", listAccDisplay);
                request.setAttribute("page", page);
                request.setAttribute("url", url);
                request.setAttribute("num", numberPage);
                request.getRequestDispatcher("admin/account.jsp").forward(request, response);

            }
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
    

    private List<AccountUser> getListAccDisplay(List<AccountUser> listAcc, int start, int end) {
        List<AccountUser> listAccDisplay = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listAccDisplay.add(listAcc.get(i));
        }
        return listAccDisplay;
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
