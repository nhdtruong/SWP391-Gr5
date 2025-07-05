package controller.admin;

import dal.CategoryServiceDAO;
import dal.DepartmentDAO;
import dal.ServiceDAO;
import model.Service;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.CategoryServices;
import model.Deparment;

@WebServlet(name = "ServiceManageServlet", urlPatterns = {"/servicemanager"})
public class ServiceManageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String url = null;
        ServiceDAO serviceDAO = new ServiceDAO();
        CategoryServiceDAO cateDAO = new CategoryServiceDAO();
        DepartmentDAO deDao = new DepartmentDAO();
        List<Service> listService = null;
        List<Deparment> listDeparment = deDao.getAllDeparment();
        List<CategoryServices> listCategory = cateDAO.getAllCategoryServiceses();

        try (PrintWriter out = response.getWriter()) {
            if (action.equals("all")) {
                listService = serviceDAO.getAllServices();
                url = "servicemanager?action=all";
            }
            if (action.equals("search")) {
                String text = request.getParameter("text");
                listService = serviceDAO.getAllServiceBySearchName(text);
                url = "servicemanager?action=search&text=" + text;

            }
            if (action.equals("filter")) {

                String cartegoryService_id = request.getParameter("cartegoryService_id");
                String department_id = request.getParameter("department_id");
                request.setAttribute("cartegoryService_id", cartegoryService_id);
                request.setAttribute("department_id", department_id);
                listService = serviceDAO.getAllServiceByFilter(cartegoryService_id, department_id);
                url = "servicemanager?action=filter&cartegoryService_id=" + cartegoryService_id + "&department_id=" + department_id;

            }

            if (listService != null) {
                int page, numberPerPage = 9;
                int size = listService.size();
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
                List<Service> listServiceDisplay = getListServiceDisplay(listService, start, end);
                request.setAttribute("category", listCategory);
                request.setAttribute("department", listDeparment);
                request.setAttribute("service", listServiceDisplay);
                request.setAttribute("url", url);
                request.setAttribute("page", page);
                request.setAttribute("start", start);
                request.setAttribute("end", end);
                request.setAttribute("num", numberPage);
                request.setAttribute("numPageDisplay", numPageDisplay);
                request.getRequestDispatcher("admin/service.jsp").forward(request, response);
            }

        }
    }


private List<Service> getListServiceDisplay(List<Service> listService, int start, int end) {
        List<Service> listDoctorDisplay = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listDoctorDisplay.add(listService.get(i));
        }
        return listDoctorDisplay;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
//       
}
