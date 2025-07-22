<%-- 
    Document   : myAppointment
    Created on : 7 Jul 2025, 03:25:20
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
            <div class="con" style="margin-right: 150px;margin-left: 150px;padding-top: 10px">
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
                                            <h5 class="mb-0">Lịch hẹn từ bệnh nhân</h5>
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
                                                <table class="table mb-0 table-center" style="text-align: center">
                                                    <thead>
                                                        <tr>                          
                                                            <th class="border-bottom p-3" >STT</th>
                                                            <th class="border-bottom p-3" >Bệnh nhân</th>
                                                            <th class="border-bottom p-3" >Dịch vụ</th>
                                                            <th class="border-bottom p-3" >Ngày hẹn</th>
                                                            <th class="border-bottom p-3" >Lý do khám</th>
                                                            <th class="border-bottom p-3" >Trạng thái</th>

                                                            <th class="border-bottom p-3" >Hành động</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:if test="${empty Appointment}">
                                                        <tr>
                                                            <td colspan="6" class="text-center text-danger p-3">
                                                                Không có lịch hẹn nào được tìm thấy
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:set var="i" value="${start + 1}"/>   
                                                    <c:forEach items="${Appointment}" var="a">
                                                        <tr> 
                                                            <td class="p-3">${i}</td>

                                                            <td class="p-3">${a.patient.patientName}</td>
                                                            <td class="p-3">${a.serviceName}</td>
                                                            <td class="p-3"><fmt:formatDate value="${a.workingDate}" pattern="dd/ MM/ yyyy"/><br>
                                                                <fmt:formatDate value="${a.slotStart}" pattern="HH:mm"/> - <fmt:formatDate value="${a.slotEnd}" pattern="HH:mm"/> 
                                                            </td>
                                                            <c:if test="${empty a.note }"><td class="p-3">Không</td></c:if>
                                                            <c:if test="${not empty a.note }"><td class="p-3">${a.note}</td></c:if>
                                                                <td class="p-3">
                                                                <c:choose>
                                                                    <c:when test="${a.status == 1}">
                                                                        <span style="color: green;">Đã đặt</span>
                                                                    </c:when>
                                                                    <c:when test="${a.status == 0}">
                                                                        <span style="color: red;">Đã hủy</span>
                                                                    </c:when>
                                                                    <c:when test="${a.status == 2}">
                                                                        <span style="color: goldenrod;">Đã khám</span>
                                                                    </c:when>
                                                                    <c:when test="${a.status == 3}">
                                                                        <span style="color: orange;">Yêu cầu hủy lịch</span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span style="color: gray;">Không xác định</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>   


                                                            <td class="p-3">


                                                                <a href="examination?patientId=${a.patient.patientId}" class="btn btn-primary" title="Sửa bác sĩ">
                                                                    <i class="fa-solid fa-pen-to-square"></i>Khám
                                                                </a>

                                                                <!-- Nút xóa -->
                                                                <a href="#" class="btn btn-danger" 
                                                                   onclick="openDeleteModal('${d.getDoctor_id()}', '${d.getDoctor_name()}')" 
                                                                   title="Xóa bác sĩ">
                                                                    <i class="fa-solid fa-trash"></i>Y/c Dời lịch
                                                                </a>
                                                            </td>


                                                        </tr>
                                                        <c:set var="i" value="${i + 1}"/>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <c:set var="page" value="${page}"/>
                                <div class="row text-center">
                                    <div class="col-12 mt-4">
                                        <div class="d-md-flex align-items-center text-center" style="justify-content: center">
                                            <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                                <c:choose>
                                                    <c:when test="${page < numPageDisplay  && num < numPageDisplay  }">
                                                        <c:forEach begin="${1}" end="${num}" var="i">
                                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                            </c:forEach>

                                                    </c:when>

                                                    <c:when test="${page < numPageDisplay  && num > numPageDisplay  }">
                                                        <c:forEach begin="${1}" end="${numPageDisplay}" var="i">
                                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                            </c:forEach>

                                                    </c:when>


                                                    <c:when test="${(num - page + 1 ) <= numPageDisplay && num >= numPageDisplay}">
                                                        <li class="page-item"><a class="page-link" href="${url}&page=${page-1}">Prev</a></li>
                                                            <c:forEach begin="${num - (numPageDisplay-1)}" end="${num}" var="i">
                                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                            </c:forEach>

                                                    </c:when>   


                                                    <c:otherwise >
                                                        <li class="page-item"><a class="page-link" href="${url}&page=${page-1}">Prev</a></li>
                                                            <c:forEach begin="${page-(numPageDisplay/2)+1}" end="${page+(numPageDisplay/2)}" var="i">
                                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                            </c:forEach>
                                                        <li class="page-item"><a class="page-link" href="${url}&page=${page+1}">Next</a></li>  
                                                        </c:otherwise>      

                                                </c:choose>


                                            </ul>
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


