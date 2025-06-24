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
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-3 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Doctor</h5>
                                </div>
                                <div class="col-md-8">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="doctormanager?action=search" method="POST" id="searchform" class="searchform">
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
                                <form class="row" action="doctormanager?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class=" justify-content-md-end row">


                                        <div class="col-md-4 row align-items-center">
                                            <div class="col-md-5" style="text-align: end">
                                                <label  class="form-label">Chuyên khoa</label>
                                            </div>
                                            <div class="col-md-7">
                                                <select name="department_id" class="form-select">
                                                    <option <c:if test="${department_id == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${department}" var="de">
                                                        <option <c:if test="${de.getId().toString() == department_id}"> selected </c:if> value="${de.getId()}">${de.getDepartment_name()}</option>
                                                    </c:forEach>
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
                                                    <td class="p-3"><fmt:formatDate value="${a.workingDate}" pattern="dd/ MM/ yyyy"/></td>
                                                    <td class="p-3"><fmt:formatDate value="${a.slotStart}" pattern="HH:mm"/> - <fmt:formatDate value="${a.slotEnd}" pattern="HH:mm"/> </td>
                                                    <c:if test="${a.getStatus() == 1}">
                                                        <td class="p-3" style="color: green">Active</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() == 0}">
                                                        <td class="p-3" style="color: red">Disable</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() ==  2}">
                                                        <td class="p-3" style="color: yellow">Wait</td>
                                                    </c:if>   
                                                    <td class="p-3">
                                                        

                                                        <a href="updatedoctor?action=updateDoc&doctorId=${d.getDoctor_id()}" class="btn btn-primary">Update</a>

                                                        <a href="#" class="btn btn-danger"
                                                           onclick="openDeleteModal('${d.getDoctor_id()}', '${d.getDoctor_name()}')">
                                                            Delete
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





                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
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
