<%-- 
    Document   : myPatient
    Created on : 8 Jul 2025, 04:09:38
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
        <section class="bg-dashboard">
            <div class="con" style="margin-right: 200px;margin-left: 200px;padding-top: 50px">
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
                                        <a href="myPatient?action=mypatient" class="navbar-link">
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


                    <div class="col-md-9">


                        <div class="rounded shadow mt-4">

                            <div class="layout-specing" style="">
                                <div class="row">

                                    <div class="col-md-4 ">
                                        <div class="col-md-12">
                                            <h5 class="mb-0">Bệnh nhân của tôi</h5>
                                        </div>

                                    </div>
                                    <div class="col-md-8 ">
                                        <form class="row" action="appointmentManager?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                            <div class=" justify-content-md-around row ">


                                                <div class="col-md-5 row align-items-center">
                                                    <div class="col-md-5" style="text-align: end">
                                                        <label  class="form-label">Trạng thái</label>
                                                    </div>
                                                    <div class="col-md-7">
                                                        <select name="status" class="form-select">
                                                            <option <c:if test="${status == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                            <option <c:if test="${status == '1'}"> selected </c:if> value="1">Đã đặt lịch</option>                                       
                                                            <option <c:if test="${status == '0'}"> selected </c:if> value="0">Đã hủy lịch</option>
                                                            <option <c:if test="${status == '2'}"> selected </c:if> value="2">Đã khám</option>
                                                            <option <c:if test="${status == '3'}"> selected </c:if> value="3">Yêu cầu hủy lịch</option>
                                                            </select>  
                                                        </div>
                                                    </div>

                                                    <div class="col-md-1 md-0">
                                                        <button type="submit" class="btn btn-primary">Lọc</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>

                                    <div class="row">
                                        <div class="col-12 mt-4">
                                            <div class="table-responsive bg-white shadow rounded">
                                                <table class="table mb-0 table-center text-center">
                                                    <thead>
                                                        <tr>                          
                                                            <th class="border-bottom p-3">Họ Tên</th>
                                                            <th class="border-bottom p-3">Ngày sinh</th>
                                                            <th class="border-bottom p-3">Số điện thoại</th>
                                                            <th class="border-bottom p-3">Nghề nghiệp</th>
                                                            <th class="border-bottom p-3">Giới tính</th>
                                                            <th class="border-bottom p-3">Địa chỉ</th>
                                                            <th class="border-bottom p-3">Hành động</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:if test="${empty patients}">
                                                        <tr>
                                                            <td colspan="7" class="text-center text-danger p-3">
                                                                Không có bệnh nhân nào được tìm thấy
                                                            </td>
                                                        </tr>
                                                    </c:if>

                                                    <c:forEach items="${patients}" var="p">
                                                        <tr>
                                                            <td class="p-3">${p.patientName}</td>
                                                            <td class="p-3"><fmt:formatDate value="${p.dob}" pattern="dd/MM/yyyy"/></td>
                                                            <td class="p-3">${p.phone}</td>
                                                            <td class="p-3">${p.job}</td>
                                                            <td class="p-3">${p.gender}</td>
                                                            <td class="p-3">${p.address}</td>
                                                            <td class="p-3">
                                                                <!-- Nút xem bệnh án -->
                                                                <a href="examination?patientId=${p.patientId}" class="btn btn-primary" title="Xem bệnh án">
                                                                    <i class="fa-solid fa-folder-open"></i> Bệnh án
                                                                </a>

                                                                <!-- Nút xóa bệnh nhân (nếu cần) -->
                                                                <a href="#" class="btn btn-danger" 
                                                                   onclick="confirmDelete('${p.patientId}', '${p.patientName}')" 
                                                                   title="Xóa bệnh nhân">
                                                                    <i class="fa-solid fa-trash"></i> Xóa
                                                                </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                    </div>
                                </div>

                            </div>



                        </div>

                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>

        <jsp:include page="layout/search.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

    </body>

</html>
