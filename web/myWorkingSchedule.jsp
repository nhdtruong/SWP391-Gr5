<%-- 
    Document   : myWorkingSchedule
    Created on : 7 Jul 2025, 03:24:47
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-dashboard py-4">
            <div class="con" style="margin-right: 150px;margin-left: 150px;padding-top: 100px">
                <div class="row">
                    <!-- Bên trái: Menu -->
                    <div class="col-md-3 mb-4">
                        <div class="rounded shadow overflow-hidden sticky-bar">
                            <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                <br><br><br><br>

                                <c:if test="${sessionScope.user.getImg() != 'default'}">
                                    <img src="${sessionScope.user.getImg()}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                </c:if>
                                <c:if test="${sessionScope.user.getImg() == 'default'}">
                                    <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                </c:if>

                                <h5 class="mt-3 mb-1">${sessionScope.user.getName()}</h5>
                                <p class="text-muted mb-0">${sessionScope.user.getUsername()}</p>
                            </div>

                            <ul class="list-unstyled sidebar-nav mb-0">
                                <li class="navbar-item">
                                    <a href="profile" class="navbar-link">
                                        <i class="ri-airplay-line align-middle navbar-icon"></i> Dashboard
                                    </a>
                                </li>

                                <c:if test="${sessionScope.user.getRole() == 4}">
                                    <li class="navbar-item">
                                        <a href="myFeedback?action=myfeedback" class="navbar-link">
                                            <i class="ri-chat-1-line align-middle navbar-icon"></i> Phản hồi
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myPatinet?action=mypatient" class="navbar-link">
                                            <i class="ri-empathize-line align-middle navbar-icon"></i> Bệnh nhân của tôi
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myAppointment?action=all" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Lịch hẹn
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myWorkingSchedule" class="navbar-link">
                                            <i class="ri-empathize-line align-middle navbar-icon"></i> Lịch làm việc
                                        </a>
                                    </li>
                                </c:if>

                                <c:if test="${sessionScope.user.getRole() == 5}">
                                    <li class="navbar-item">
                                        <a href="records" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Hồ sơ khám bệnh
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="bills" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Phiếu khám bệnh
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>

                    <!-- Bên phải: Lịch làm việc -->
                    <div class="col-md-9">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h5 class="mb-0">📅 Lịch làm việc</h5>
                        </div>

                        <!-- Tuần điều hướng -->
                        <div class="d-flex justify-content-center mb-3">
                            <div>
                                <c:if test="${weekIndex > 0}">
                                    <a href="?doctorId=${doctorId}&doctorName=${doctorName}&weekIndex=${weekIndex - 1}"
                                       class="btn btn-outline-primary me-2">← Tuần trước</a>
                                </c:if>
                                <c:if test="${weekIndex < weeklySchedule.size() - 1}">
                                    <a href="?doctorId=${doctorId}&doctorName=${doctorName}&weekIndex=${weekIndex + 1}"
                                       class="btn btn-outline-primary">Tuần tiếp →</a>
                                </c:if>
                            </div>
                        </div>

                        <!-- Bảng lịch -->
                        <div class="table-responsive mb-5">
                            <table class="table table-bordered text-center align-middle">
                                <thead class="table-light">
                                    <tr>
                                        <th>Ca</th>
                                            <c:forEach var="day" items="${weeklySchedule[weekIndex]}" begin="0" end="6">
                                            <th>
                                                <fmt:formatDate value="${day.workingDate}" pattern="EEEE" /><br>
                                                <fmt:formatDate value="${day.workingDate}" pattern="dd/MM/yyyy" />
                                            </th>
                                        </c:forEach>
                                    </tr>
                                </thead>

                                <tbody>
                                    <!-- Ca sáng -->
                                    <tr>
                                        <td><strong>Ca sáng</strong></td>
                                        <c:forEach var="day" items="${weeklySchedule[weekIndex]}" begin="0" end="6">
                                            <td>
                                                <c:if test="${empty day.morningSlots}">
                                                    <span class="text-muted">Trống</span>
                                                </c:if>
                                                <c:forEach var="slot" items="${day.morningSlots}">
                                                    <div class="badge bg-success mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>

                                    <!-- Ca chiều -->
                                    <tr>
                                        <td><strong>Ca chiều</strong></td>
                                        <c:forEach var="day" items="${weeklySchedule[weekIndex]}" begin="0" end="6">
                                            <td>
                                                <c:if test="${empty day.afternoonSlots}">
                                                    <span class="text-muted">Trống</span>
                                                </c:if>
                                                <c:forEach var="slot" items="${day.afternoonSlots}">
                                                    <div class="badge bg-warning text-dark mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>

                                    <!-- Ca tối -->
                                    <tr>
                                        <td><strong>Ca tối</strong></td>
                                        <c:forEach var="day" items="${weeklySchedule[weekIndex]}" begin="0" end="6">
                                            <td>
                                                <c:if test="${empty day.eveningSlots}">
                                                    <span class="text-muted">Trống</span>
                                                </c:if>
                                                <c:forEach var="slot" items="${day.eveningSlots}">
                                                    <div class="badge bg-secondary mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                </c:forEach>
                                            </td>
                                        </c:forEach>
                                    </tr>

                                   
                                   

                                    <!-- Hành động -->
                                    <tr>
                                        <td><strong>Hành động</strong></td>
                                        <c:forEach var="day" items="${weeklySchedule[weekIndex]}" begin="0" end="6">
                                            <td>
                                                <c:if test="${not empty day.slots}">
                                                    <div class="d-flex justify-content-center gap-1 mt-2">
                                                        <form action="updateDoctorScheduleDetail?action=update" method="get">
                                                            <input type="hidden" name="doctorId" value="${doctorId}" />
                                                            <input type="hidden" name="doctorName" value="${doctorName}" />
                                                            <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                            <button type="submit" class="btn btn-sm btn-info">Sửa</button>
                                                        </form>
                                                        
                                                    </div>
                                                </c:if>
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <style>
           
        </style>
    </body>

</html>