
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
                            <div class="col-md-4 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Account</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="accountmanager?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input value="${text}" type="text" class="form-control border rounded-pill" name="text" id="s" placeholder="Tìm kiếm tài khoản...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-6">
                                <form action="accountmanager?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">

                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Quyền</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="roleId" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${role_id == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${role}" var="r">
                                                        <option <c:if test="${role_id == r.getId().toString()}">selected</c:if> value="${r.getId()}">${r.getName()}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-4">
                                                <label class="form-label">Trạng thái</label>
                                            </div>
                                            <div class="col-md-8">
                                                <select name="status" class="form-select" aria-label="Default select example">
                                                    <option <c:if test="${status == 'all'}">selected</c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${status == '1'}">selected</c:if> value="1">Active</option>
                                                    <option <c:if test="${status == '2'}">selected</c:if> value="2">Wait</option>
                                                    <option <c:if test="${status == '0'}">selected</c:if> value="0">Disable</option>
                                                    </select>
                                                </div>  
                                            </div>
                                            <div class="col-md-1 md-0">
                                                <button type="submit" class="btn btn-primary">Lọc</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-md-2">
                                    <a href="adddoctor" type="button"class="btn btn-info">Add+</a>         

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
                                            <c:if test="${empty account}">
                                                <tr>
                                                    <td colspan="6" class="text-center text-danger p-3">
                                                        Không có tài khoản nào được tìm thấy.
                                                    </td>
                                                </tr>
                                            </c:if>
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
                                                    <c:if test="${a.getStatus() == 2}">
                                                        <td class="p-3" style="color: yellow">Wait</td>
                                                    </c:if>
                                                    <td class="p-3">
                                                        <a href="#" class="btn btn-primary"
                                                           data-bs-toggle="modal"
                                                           data-bs-target="#editModal"
                                                           onclick="openEditModal('${a.getUsername()}', '${a.getORole().getId()}', '${a.getStatus()}')">Update</a>
                                                    </td>
                                                    <c:if test="${a.getORole().getId() != 1}">
                                                        <td class="p-3">
                                                            <a href="#" class="btn btn-danger"
                                                               data-bs-toggle="modal"
                                                               data-bs-target="#deleteModal"
                                                               onclick="openDeleteModal('${a.getUsername()}', '${a.getORole().getId()}')">Delete</a>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <c:set var="page" value="${page}"/>
                        <div class="row text-center " style="" >
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center " style="justify-content: center">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <c:forEach begin="${1}" end="${num}" var="i">
                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </div>                               
                    </div>

                </div>
                 
                <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <form action="updateaccount?action=update" method="POST">
                                <div class="modal-header border-bottom p-3">
                                    <h5 class="modal-title" id="editModalLabel">Chỉnh sửa tài khoản</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                </div>
                                <div class="modal-body p-3 pt-4">
                                    <div class="mb-3">
                                        <label class="form-label">Tên tài khoản</label>
                                        <input type="text" id="editUsername" name="username" class="form-control" readonly>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Quyền</label>
                                        <select id="editRole" name="role_id" class="form-select">
                                            <c:forEach items="${role}" var="r">
                                                <option value="${r.getId()}">${r.getName()}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Trạng thái</label>
                                        <select id="editStatus" name="status" class="form-select">
                                            <option value="1">Active</option>
                                            <option value="0">Disable</option>
                                            <option value="2">Wait</option>
                                        </select>
                                    </div>
                                    <div class="d-grid">
                                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <form action="updateaccount?action=delete" method="POST">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa tài khoản</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Bạn có chắc muốn xóa tài khoản <strong id="deleteUsernameDisplay"></strong> không?</p>
                                    <input type="hidden" id="deleteUsername" name="username">
                                    <input type="hidden" id="roleOfUsername" name="role_id">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

  <c:if test="${deleteAccountOK == 'deleteAccountOK'}">
    <div class="alert alert-success text-center" role="alert"  
         style="position: fixed; 
                top: 50%; left: 50%; 
                transform: translate(-50%, -50%);
                z-index: 9999;
                width: 250px;      
                height: 250px;    
                font-size: 24px;    
                padding: 0;       
                font-weight: bold;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
                border-radius: 50%; 
                display: flex;     
                align-items: center;
                justify-content: center;
                text-align: center;">
        ✅<br>Xóa tài khoản<br>thành công!
    </div>

    <script>
        setTimeout(function () {
            window.location.href = 'accountmanager?action=all';
        }, 500); 
    </script>
</c:if>    
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

        <script>
                                                               function openEditModal(username, roleId, status) {
                                                                   document.getElementById('editUsername').value = username;
                                                                   document.getElementById('editRole').value = roleId;
                                                                   document.getElementById('editStatus').value = status;

                                                               }
                                                               function openDeleteModal(username, roleId) {
                                                                   document.getElementById('deleteUsername').value = username;
                                                                   document.getElementById('roleOfUsername').value = roleId;
                                                                   document.getElementById('deleteUsernameDisplay').innerText = username;
                                                               }

        </script>


    </body>

</html>
