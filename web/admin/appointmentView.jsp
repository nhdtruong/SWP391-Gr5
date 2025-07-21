<%-- 
    Document   : appointmentView
    Created on : 24 Jun 2025, 02:14:16
    Author     : DELL
--%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing" style="">
                        <div class="row">
                            
                            <div class="col-md-3 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Appointment</h5>
                                </div>
                                <div class="col-md-8">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="appointmentManager?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="text" id="s" placeholder="Tìm kiếm bác sĩ...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-8 ">
                                <form class="row" action="appointmentManager?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class=" justify-content-md-end row">


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

                                            <div class="col-md-5 row align-items-center">
                                                <div class="col-md-5" style="text-align: end">
                                                    <label  class="form-label">Trạng thái thanh toán</label>
                                                </div>
                                                <div class="col-md-7">
                                                    <select name="paymentStatus" class="form-select">
                                                        <option <c:if test="${paymentStatus == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${paymentStatus == 'success'}"> selected </c:if> value="success">Đã thanh toán</option>
                                                    <option <c:if test="${paymentStatus == 'pending'}"> selected </c:if> value="pending">Chưa thanh toán</option>
                                                    <option <c:if test="${paymentStatus == 'refunded'}"> selected </c:if> value="refunded">Đã hoàn tiền</option>

                                                    </select>  
                                                </div>
                                            </div>      

                                            <div class="col-md-1 md-0">
                                                <button type="submit" class="btn btn-primary">Lọc</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-1">
                                    <a href="adddoctor" type="button"class="btn btn-info">Add+</a>         
                                </div>                         
                            </div>

                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <table class="table mb-0 table-center" style="text-align: center">
                                            <thead>
                                                <tr>                          
                                                    <th class="border-bottom p-3" >STT</th>
                                                    <th class="border-bottom p-3" >Bác sĩ</th>                                        
                                                    <th class="border-bottom p-3" >Bệnh nhân</th>
                                                    <th class="border-bottom p-3" >Dịch vụ</th>
                                                    <th class="border-bottom p-3" >Ngày hẹn</th>
                                                    <th class="border-bottom p-3" >Giờ hẹn</th>
                                                    <th class="border-bottom p-3" >Trạng thái</th>

                                                    <th class="border-bottom p-3" >Trạng thái thanh toán</th>

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
                                                    <td class="p-3">${a.doctorName}</td>
                                                    <td class="p-3">${a.patientName}</td>
                                                    <td class="p-3">${a.serviceName}</td>
                                                    <td class="p-3"><fmt:formatDate value="${a.dateBooking}" pattern="dd/ MM/ yyyy"/></td>
                                                    <td class="p-3"><fmt:formatDate value="${a.slotStart}" pattern="HH:mm"/> - <fmt:formatDate value="${a.slotEnd}" pattern="HH:mm"/> </td>
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
                                                        <c:choose>
                                                            <c:when test="${a.paymentStatus == 'pending'}">
                                                                <span style="color: gray;">Chưa thanh toán</span>
                                                            </c:when>
                                                            <c:when test="${a.paymentStatus == 'success'}">
                                                                <span style="color: green;">Đã thanh toán</span>
                                                            </c:when>
                                                            <c:when test="${a.paymentStatus == 'refunded'}">
                                                                <span style="color: blue;">Đã hoàn tiền</span>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span style="color: darkred;">Không rõ</span>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td class="p-3">


                                                        <a href="updateAppoitment?action=updateAppointment&appointmentId=${a.appointmentId}" class="btn btn-primary" title="Sửa bác sĩ">
                                                            <i class="fa-solid fa-pen-to-square"></i>
                                                        </a>

                                                        <!-- Nút xóa -->
                                                        <a href="#" class="btn btn-danger" 
                                                           onclick="openDeleteModal('${d.getDoctor_id()}', '${d.getDoctor_name()}')" 
                                                           title="Xóa bác sĩ">
                                                            <i class="fa-solid fa-trash"></i>
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


                <c:if test="${not empty refundRequest}">
                    <div class="refund-alert position-fixed top-50 start-50 translate-middle text-center shadow-lg border border-warning bg-light"
                         role="alert"
                         style="z-index: 1055; width: 90%; max-width: 700px; border-radius: 1rem; font-size: 18px; padding: 2rem;">

                        <div class="d-flex flex-column align-items-center">
                            <i class="fa-solid fa-circle-exclamation text-warning" style="font-size: 3rem;"></i>

                            <h4 class="fw-bold text-dark mt-3 mb-4">
                                Có <span class="text-danger">${refundRequest}</span> phiếu khám yêu cầu hủy cần xử lý!
                            </h4>

                            <div class="d-flex justify-content-center gap-3">
                                <form method="post" action="appointmentManager">
                                    <input type="hidden" name="stopNotify" value="stopNotifyRefund" />
                                    <button type="submit" class="btn btn-outline-dark px-4">Xác nhận</button>
                                </form>

                            </div>
                        </div>
                    </div>

                </c:if>

                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/simplebar.min.js"></script>
        <script src="assets/js/select2.min.js"></script>
        <script src="assets/js/select2.init.js"></script>
        <script src="assets/js/flatpickr.min.js"></script>
        <script src="assets/js/flatpickr.init.js"></script>
        <script src="assets/js/jquery.timepicker.min.js"></script> 
        <script src="assets/js/timepicker.init.js"></script> 
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

      

    </body>

</html>
