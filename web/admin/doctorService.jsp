<%-- 
    Document   : doctorService
    Created on : 5 Jul 2025, 00:54:56
    Author     : DELL
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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


                        <h3 class="mb-3">Quản lý bác sĩ thực hiện dịch vụ</h3>
                        <div class="card p-4">
                            <h5>Thông tin dịch vụ</h5>
                            <p><strong>Tên dịch vụ:</strong> ${service.service_name}</p>
                            <c:if test="${not empty service.department}" ><p><strong>Chuyên khoa:</strong> ${service.department.department_name}</p></c:if>

                            </div>

                            <div class="card mt-4 p-4">
                                <h5 class="mb-3">Bác sĩ đang đảm nhiệm</h5>
                            <c:if test="${empty doctorsOfService}">
                                <p class="text-danger">Chưa có bác sĩ nào đảm nhiệm dịch vụ này.</p>
                            </c:if>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Họ tên</th>
                                        <th>Chuyên khoa</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="doc" items="${doctorsOfService}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${doc.doctor_name}</td>
                                            <td>${doc.department.department_name}</td>
                                            <td>
                                                <form action="doctorService?action=delete" method="post" style="display:inline;">
                                                    <input type="hidden" name="doctorId" value="${doc.doctor_id}">
                                                    <input type="hidden" name="serviceId" value="${service.service_id}">
                                                    <input type="hidden" name="departmentId" value="${service.department.id}" />
                                                    <button type="submit" class="btn btn-danger btn-sm"
                                                            onclick="return confirm('Xác nhận xóa bác sĩ này khỏi dịch vụ?')">Xóa</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:if test="${not empty service.department}">
                            <div class="card mt-4 p-4">
                                <h5 class="mb-3">Thêm bác sĩ từ chuyên khoa</h5>
                                <form action="doctorService?action=add" method="post" class="row g-3">
                                    <input type="hidden" name="serviceId" value="${service.service_id}" />
                                    <input type="hidden" name="departmentId" value="${service.department.id}" />
                                    <div class="col-md-6">
                                        <select name="doctorId" class="form-select" required>
                                            <option value="">-- Chọn bác sĩ --</option>
                                            <c:forEach var="doc" items="${doctorsInDepartment}">
                                                <option value="${doc.doctor_id}">${doc.doctor_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-primary">Thêm bác sĩ</button>
                                    </div>
                                </form>
                            </div>
                        </c:if>






                    </div>                       
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>


    </body>
</html>