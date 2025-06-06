<%-- 
    Document   : updatedoctor
    Created on : 6 Jun 2025, 03:29:55
    Author     : DELL
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="card border-0 shadow p-4">
                            <h5 class="mb-3">Chỉnh sửa thông tin bác sĩ</h5>

                            <!-- Form cập nhật toàn bộ -->
                            <form action="updatedoctor?action=update" method="POST" >
                                <div class="row">
                                    <input type="hidden" name="doctor_id" value="${doctor.getDoctor_id()}"/>
                                    <!-- Ảnh đại diện -->
                                    <p class="text-muted">Ảnh đại diện</p>
                                    <div class="mb-3 d-flex align-items-center gap-4">
                                        <div>
                                            <c:if test="${doctor.getImg() == 'default'}"> 
                                                <img id="thumbimage" class="rounded-circle shadow" src="assets/images/avata.png" alt="Ảnh đại diện" style="width: 120px;" />
                                            </c:if>
                                            <c:if test="${doctor.img != 'default'}">
                                                <img id="thumbimage" class="rounded-circle shadow" src="${doctor.getImg()}" alt="Ảnh đại diện" style="width: 120px;" />
                                            </c:if>
                                        </div>

                                        <div class="flex-grow-1">
                                            <input type="file" name="image" id="uploadfile" class="form-control" onchange="readURL(this);" />
                                            <small class="text-muted">Chọn ảnh mới để cập nhật (jpg, png...)</small>
                                        </div>
                                    </div>

                                    <div class="mb-3 text-center">
                                        <img id="previewImage" class="rounded-circle shadow" src="#" alt="Preview" style="display: none; width: 120px;" />
                                    </div>

                                    <!-- Họ tên -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Họ tên</label>
                                        <input name="name" type="text" class="form-control" value="${doctor.getDoctor_name()}">
                                    </div>

                                    <!-- Giới tính -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Giới tính</label><br/>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" value="Nam" ${doctor.getGender()=="Nam"?"checked":""}>
                                            <label class="form-check-label">Nam</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="gender" value="Nữ" ${doctor.getGender()=="Nữ"?"checked":""}>
                                            <label class="form-check-label">Nữ</label>
                                        </div>
                                    </div>

                                    <!-- Số điện thoại -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Số điện thoại</label>
                                        <input name="phone" type="text" class="form-control" value="0${doctor.getPhone()}">
                                    </div>

                                    <!-- Ngày sinh -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Ngày sinh</label>
                                        <input name="DOB" type="date" class="form-control" min="1922-01-01" max="2003-01-01" value="${doctor.getDOB()}">
                                    </div>

                                    
                                    <div class="col-md-12 mb-3">
                                        <label class="form-label">Mô tả</label>
                                        <textarea name="description" id="editors">${doctor.getDescription()}</textarea>

                                    </div>


                                    <!-- Chuyên môn -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Chuyên khoa</label>
                                        <select name="department_id" class="form-select">
                                            <c:forEach items="${department}" var="d">
                                                <option value="${d.getId()}" ${doctor.getDepartment().getId() == d.getId() ? "selected" : ""}>
                                                    ${d.getDepartment_name()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <!-- Trạng thái -->
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Trạng thái</label><br/>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="status" value="1" ${doctor.getStatus()=="1"?"checked":""}>
                                            <label class="form-check-label">Hoạt động</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="status" value="0" ${doctor.getStatus()=="0"?"checked":""}>
                                            <label class="form-check-label">Khóa</label>
                                        </div>
                                    </div>

                                    <!-- Nút cập nhật -->
                                    <div class="col-12 mt-3 text-center">
                                        <button type="submit" class="btn btn-success px-4">Cập nhật tất cả</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
     
     
      

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/trumbowyg@2/dist/ui/trumbowyg.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/trumbowyg@2/dist/trumbowyg.min.js"></script>

<script>
  $(document).ready(function(){
    $('#editors').trumbowyg();
  });
</script>
    </body>

</html>
