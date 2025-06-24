<%-- 
    Document   : PatientListForAdmin
    Created on : Jun 21, 2025, 7:56:08 PM
    Author     : dantr
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
                        <form action="patientmanage?action=all" method="get">
                            <div class="row">

                                <div class="col-md-11 row">
                                    <div class="col-md-4">
                                        <h5 class="mb-0">Patients</h5>
                                        <h6>${requestScope.success}</h6>
                                    </div>
                                    <div class="col-md-7">
                                        <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                            <div id="search" class="menu-search mb-0">

                                                <div>
                                                    <input type="text" value="${searchname}" class="form-control border rounded-pill" name="searchname" id="s" placeholder="Tìm kiếm Bệnh nhân...">
                                                    <input hidden="" type="text"  name="action" value="all"/>
                                                </div>

                                            </div>
                                        </div> 
                                    </div>
                                </div>
                                <div class="col-md-1">
                                    <div class="justify-content-md-end row">
                                        <div class="d-grid">
                                            <button type="submit" class="btn btn-primary" >Lọc</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center">
                                        <thead>
                                            <tr>
                                                <th class="border-bottom p-3" >ID</th>
                                                <th class="border-bottom p-3" >Họ tên</th>
                                                <th class="border-bottom p-3" >Giới tính</th>
                                                <th class="border-bottom p-3" >Ngày sinh</th>
                                                <th class="border-bottom p-3" ></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${patientDetails}" var="a">
                                                <tr>
                                                    <th class="p-3">${a.patientId}</th>
                                                    <td class="p-3">${a.patientName}</td>
                                                    <td class="p-3">${a.gender}</td>
                                                    <td class="p-3">${a.dob}</td>
                                                    <td class="text-center p-1">
                                                        <a href="patientmanage?action=detail&id=${a.patientId}" type="button"class="btn btn-info">Chi tiết</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <c:set var="page" value="${page}"/>
                            <div class="row text-center">
                                <div class="col-12 mt-4">
                                    <div class="d-md-flex align-items-center text-center">
                                        <a style="
                                           cursor: pointer;
                                           padding: 5px 10px;
                                           border-radius: 5px;
                                           background-color: #f0f0f0;
                                           margin-right: 8px;
                                           transition: background-color 0.2s ease;
                                           " href="patientmanage?action=all&searchname=${gender}&pagIndex=${pagIndex-1}">&lt;</a>

                                        <span style="font-weight: bold; color: #007bff;">${pagIndex}</span>/<span style="color: #555;">${numberPage}</span>

                                        <a style="
                                           cursor: pointer;
                                           padding: 5px 10px;
                                           border-radius: 5px;
                                           background-color: #f0f0f0;
                                           margin-left: 8px;
                                           transition: background-color 0.2s ease;
                                           " href="patientmanage?action=all&searchname=${gender}&pagIndex=${pagIndex+1}" >&gt;</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${account}" var="a">
                        <div class="modal fade" id="edit${a.username}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header border-bottom p-3">
                                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body p-3 pt-4">
                                        <form action="account?action=update" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="mb-3">
                                                        <label class="form-label">Tên người dùng</label>
                                                        <input value="${a.username}" readonly name="username" id="name" type="text" class="form-control">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Quyền <span class="text-danger">*</span></label>
                                                        <select name="role_id" class="form-select" aria-label="Default select example">
                                                            <c:forEach items="${role}" var="r">
                                                                <option <c:if test="${a.role.name == r.name}">selected</c:if> value="${r.role_id}">${r.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                                    <select name="status" class="form-select" aria-label="Default select example">
                                                        <option <c:if test="${a.status == true}">selected</c:if> value="true">Active</option>
                                                        <option <c:if test="${a.status == false}">selected</c:if> value="false">Disable</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="d-grid">
                                                        <button type="submit" id="submit" class="btn btn-primary">Chỉnh sửa</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    </c:forEach>

                    <div class="modal fade" id="filter" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header border-bottom p-3">
                                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <!--                                <div class="modal-body p-3 pt-4">
                                                                    <form action="account?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                                                        <div class="row">
                                                                            <div class="col-lg-12">
                                                                                <div class="mb-3">
                                                                                    <label class="form-label">Quyền <span class="text-danger">*</span></label>
                                                                                    <select name="role_id" class="form-select" aria-label="Default select example">
                                                                                        <option selected value="all">Tất cả</option>
                                <c:forEach items="${role}" var="r">
                                    <option value="${r.role_id}">${r.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                        <select name="status" class="form-select" aria-label="Default select example">
                            <option selected value="all">Tất cả</option>
                            <option value="1">Active</option>
                            <option value="0">Disable</option>
                        </select>
                    </div>
                </div>

            </form>
        </div>-->
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

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

