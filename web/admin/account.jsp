
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
                        <div class="row" style="text-align: center">
                            <div class="col-md-5 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Account</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="account?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="txt" id="s" placeholder="Tìm kiếm tài khoản...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-7">
                                <form action="account?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Quyền</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="role_id" class="form-select" aria-label="Default select example">
                                                   <option <c:if test="${role_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${role}" var="r">
                                                        <option <c:if test="${role_id == r.getId()}">selected</c:if> value="${r.getId()}">${r.getName()}</option>
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
                            </div>


                            <div class="row">
                                <div class="col-12 mt-4">
                                    <div class="table-responsive bg-white shadow rounded">
                                        <table class="table mb-0 table-center" style="text-align: center">
                                            <thead>
                                                <tr>
                                                    <th class="border-bottom p-3" >Tên tài khoản</th>
                                                    <th class="border-bottom p-3" >Email</th>
                                                    <th class="border-bottom p-3" >Quyền</th>
                                                    <th class="border-bottom p-3" >Trạng thái</th>
                                                    <th class="border-bottom p-3" ></th>
                                                    <th class="border-bottom p-3" ></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${account}" var="a">
                                                <tr>
                                                    <td class="p-3">${a.getUsername()}</td>
                                                    <td class="p-3">${a.getEmail()}</td>
                                                    <td class="p-3">${a.getORole().getName()}</td>
                                                    <c:if test="${a.getStatus() == 1}">
                                                        <td class="p-3" style="color: green">Active</td>
                                                    </c:if>
                                                    <c:if test="${a.getStatus() == 0}">
                                                        <td class="p-3" style="color: red">Disable</td>
                                                    </c:if>
                                                    <td class="text-end p-3">
                                                        <a href="#" type="button"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit${a.getUsername()}">Edit</a>
                                                    </td>
                                                    <td class="text-end p-3">
                                                        <a href="#" type="button"class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit${a.getUsername()}">Delete</a>
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

                <c:forEach items="${account}" var="a">
                    <div class="modal fade" id="edit${a.getUsername()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                                    <input value="${a.getUsername()}" readonly name="username" id="name" type="text" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Quyền <span class="text-danger">*</span></label>
                                                    <select name="role_id" class="form-select" aria-label="Default select example">
                                                        <c:forEach items="${role}" var="r">
                                                            <option <c:if test="${a.getORole().getName() == r.getName()}">selected</c:if> value="${r.getId()}">${r.getName()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Trạng thái <span class="text-danger">*</span></label>
                                                <select name="status" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${a.getStatus() == 1}">selected</c:if> value="1">Active</option>
                                                    <option <c:if test="${a.getStatus() == 0}">selected</c:if> value="0">Disable</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-12">
                                                    <div class="d-grid">
                                                        <button type="submit" id="submit" class="btn btn-primary">Chỉnh sửa</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                </c:forEach>


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
