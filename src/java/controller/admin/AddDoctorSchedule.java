/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.DoctorDAO;
import dal.WeeklyDoctorScheduleDAO;
import dal.WeeklyScheduleSlotDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;
import java.sql.Time;
import java.time.LocalDate;
import dal.DoctorScheduleDAO;
import dal.DoctorScheduleSlotsDAO;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AddDoctorSchedule", urlPatterns = {"/adddoctorschedule"})
public class AddDoctorSchedule extends HttpServlet {

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
            out.println("<title>Servlet AddDoctorSchedule</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDoctorSchedule at " + request.getContextPath() + "</h1>");
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
        DoctorDAO dDao = new DoctorDAO();
        List<Doctor> list = dDao.getDoctorsWithoutSchedule();
        request.setAttribute("listD", list);
        request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);
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
        String actionform = request.getParameter("actionform");

        if (actionform.equals("serachdoctor")) {

            String doctorUsername = request.getParameter("doctorUsername");
            DoctorDAO dDao = new DoctorDAO();
            DoctorScheduleDAO DSD = new DoctorScheduleDAO();
            List<Doctor> list = dDao.getDoctorsWithoutSchedule();
            request.setAttribute("listD", list);
            Doctor doctor = dDao.getDoctorByDoctorUsername1(doctorUsername);
            if (doctor == null) {
                request.setAttribute("errorBs", "Không tìm thấy bác sĩ!");
                request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);
                return;
            }
            int doctorId = dDao.getDoctorIdByDoctorUsername(doctorUsername);

            if (DSD.checkDoctorHasSchedule(doctorId)) {
                request.setAttribute("error", "Bác sĩ " + doctorUsername + " đã có lịch làm việc!");
                request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);
                return;
            }
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);

        } else if (actionform.equals("selectDoctor")) {

            DoctorDAO dDao = new DoctorDAO();
            List<Doctor> list = dDao.getDoctorsWithoutSchedule();
            request.setAttribute("listD", list);
            String doctorId = request.getParameter("doctorId");
            Doctor doctor = dDao.getDoctorByDoctorId1(doctorId);

            request.setAttribute("selectedDoctorId", doctorId);
            request.setAttribute("doctor", doctor);
            request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);

        } else if (actionform.equals("addschedule")) {

            String doctorID = request.getParameter("doctorID");

            if (doctorID == null) {
                request.setAttribute("Hãy chọn bác sĩ để thêm lịch!", this);
                request.getRequestDispatcher("admin/addDoctorSchedule.jsp").forward(request, response);
                return;
            }

            LocalDate start = LocalDate.parse(request.getParameter("startDate"));
            LocalDate end = LocalDate.parse(request.getParameter("endDate"));
            saveTemplate(request, Integer.parseInt(doctorID));

            applySchedule(request, Integer.parseInt(doctorID), start, end);
            response.sendRedirect("doctorschedule?action=all");

        }

    }

    private void saveTemplate(HttpServletRequest request, int doctorId) throws ServletException, IOException {

        WeeklyDoctorScheduleDAO WDSD = new WeeklyDoctorScheduleDAO();
        WeeklyScheduleSlotDAO WSSD = new WeeklyScheduleSlotDAO();
        for (int i = 2; i <= 8; i++) {
            String[] day = request.getParameterValues("day_" + i);
            if (day != null) {

                int template_Id = WDSD.insertTemplateSchdule(doctorId, i);

                for (String slots : day) {

                    String slotstime[] = slots.split("-");

                    Time timeStart = Time.valueOf(slotstime[0]);

                    Time timeEnd = Time.valueOf(slotstime[1]);
                    System.out.println(slots);
                    System.out.println(timeEnd);
                    System.out.println(timeStart);
                    WSSD.insertSlot(template_Id, timeStart, timeEnd);
                }

            }

        }

    }

    private void applySchedule(HttpServletRequest request, int doctorID, LocalDate start, LocalDate end) throws ServletException, IOException {
        DoctorScheduleDAO DSD = new DoctorScheduleDAO();
        DoctorScheduleSlotsDAO DSSD = new DoctorScheduleSlotsDAO();
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            int dayOfWeek = date.getDayOfWeek().getValue() + 1;
            String[] day = request.getParameterValues("day_" + dayOfWeek);
            if (day != null) {
                System.out.println("doctorid:" + doctorID);
                System.out.println("date" + date);
                int scheduleId = DSD.insertDoctorSchedule(doctorID, java.sql.Date.valueOf(date), 1);
                System.out.println("ScID" + scheduleId);
                for (String days : day) {

                    String slotstime[] = days.split("-");

                    Time timeStart = Time.valueOf(slotstime[0]);

                    Time timeEnd = Time.valueOf(slotstime[1]);

                    DSSD.insertScheduleSlot(scheduleId, timeStart, timeEnd);
                }
            }
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
