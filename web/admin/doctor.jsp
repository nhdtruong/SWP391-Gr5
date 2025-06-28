

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                                        <div class="col-md-3 row align-items-center">
                                            <div class="col-md-5" style="text-align: end">
                                                <label  class="form-label">Giới tính</label>
                                            </div>
                                            <div class="col-md-7">
                                                <select name="gender" class="form-select">
                                                    <option <c:if test="${gender == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${gender == 'Nam'}"> selected </c:if> value="Nam">Nam</option>
                                                    <option <c:if test="${gender == 'Nữ'}"> selected </c:if> value="Nữ">Nữ</option>
                                                    <option <c:if test="${gender == 'Khác'}"> selected </c:if> value="Nữ">Khác</option>
                                                    </select>  
                                                </div>
                                            </div>
                                            <div class="col-md-4 row align-items-center">
                                                <div class="col-md-5" style="text-align: end">
                                                    <label class="form-label" >Chức vụ</label>
                                                </div>
                                                <div class="col-md-7">
                                                    <select name="position_id" class="form-select">
                                                        <option <c:if test="${position_id == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${position}" var="po">
                                                        <option <c:if test="${po.getId().toString() == position_id}"> selected </c:if> value="${po.getId()}">${po.getName()}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
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
                                                <th class="border-bottom p-3" >Họ tên</th>
                                                <th class="border-bottom p-3" >Giới tính</th>                                        
                                                <th class="border-bottom p-3" >Chức vụ</th>
                                                <th class="border-bottom p-3" >Chuyên khoa</th>
                                                <th class="border-bottom p-3" >Trạng thái</th>
                                                <th class="border-bottom p-3" ></th>
                                                <th class="border-bottom p-3" ></th>
                                                <th class="border-bottom p-3" ></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${empty doctor}">
                                                <tr>
                                                    <td colspan="6" class="text-center text-danger p-3">
                                                        Không có bác sĩ được tìm thấy.
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <c:forEach items="${doctor}" var="d">
                                                <tr>                                                 
                                                    <td class="p-3">${d.getDoctor_name()}</td>
                                                    <td class="p-3">${d.getGender()}</td>
                                                    <td class="p-3">${d.getPosition().getName()}</td>
                                                    <td class="p-3">${d.getDepartment().getDepartment_name()}</td>
                                                    <c:if test="${d.getStatus() == 1}">
                                                        <td class="p-3" style="color: green">Active</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() == 0}">
                                                        <td class="p-3" style="color: red">Disable</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() ==  2}">
                                                        <td class="p-3" style="color: yellow">Wait</td>
                                                    </c:if>   
                                                    <td class="p-3">
                                                        <a href="#" class="btn btn-info"
                                                           onclick="openDetailModal('${d.getSpecialized()}', '${fn:escapeXml(d.getEducationHistory())}', '${d.getAcademicDegree().getName()}', '${d.getAcademicTitle().getName()}', '${fn:escapeXml(d.getDescription())}', '${d.getImg()}', '${d.getDoctor_name()}', '${d.getGender()}', '${d.getPosition().getName()}', '${d.getDepartment().getDepartment_name()}', '${d.getStatus() == 1 ? "Active" : d.getStatus() == 0 ? "Disable" : "Wait"}')">
                                                            Detail
                                                        </a>
                                                    </td>

                                                    <td class="p-3">
                                                        <a href="updatedoctor?action=updateDoc&doctorId=${d.getDoctor_id()}" class="btn btn-primary">Update</a>
                                                    </td>

                                                    <c:if test="${sessionScope.user.getRole()== 1}">

                                                        <td class="p-3">
                                                            <a href="#" class="btn btn-danger"
                                                               onclick="openDeleteModal('${d.getDoctor_id()}', '${d.getDoctor_name()}')">
                                                                Delete
                                                            </a>
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

                <!-- Modal Chi tiết bác sĩ -->
                <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-xl">
                        <div class="modal-content">
                            <div class="modal-header bg-info text-white">
                                <h5 class="modal-title" id="detailModalLabel">Thông tin bác sĩ</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                            </div>
                            <div class="modal-body p-4">
                                <div class="row">
                                    <!-- Hình ảnh -->
                                    <div class="col-md-4 text-center">
                                        <img id="doctorImage" src="" alt="Doctor Image" class="img-fluid rounded shadow" style="max-height: 220px; object-fit: cover;">
                                    </div>

                                    <!-- Thông tin bác sĩ -->
                                    <div class="col-md-8">
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Họ tên:</div>
                                            <div class="col-md-10" id="detailName"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Giới tính:</div>
                                            <div class="col-md-10" id="detailGender"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Chức vụ:</div>
                                            <div class="col-md-10" id="detailPosition"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-12 fw-bold">Mô tả:</div>
                                        </div>
                                        <div class="row mb-2">

                                            <div style="margin-left: 30px" class="col-md-12" id="description"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Chuyên khoa:</div>
                                            <div class="col-md-10" id="detailDepartment"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Chuyên trị:</div>
                                            <div class="col-md-10" id="detailSpecialized"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Tiểu sử:</div>

                                        </div>
                                        <div class="row mb-2">

                                            <div style="margin-left: 30px" class="col-md-12" id="detailEducation"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Học vị:</div>
                                            <div class="col-md-10" id="detailDegree"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Học hàm:</div>
                                            <div class="col-md-10" id="detailTitle"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-2 fw-bold">Trạng thái:</div>
                                            <div class="col-md-10" id="detailStatus"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Delete Modal -->
                <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <form action="updatedoctor?action=delete" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Bạn có chắc chắn muốn xóa bác sĩ <strong id="deleteDoctorName"></strong>?</p>
                                    <input type="hidden" name="doctor_id" id="deleteDoctorId">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>



                <c:if test="${deleteDoctorSuccess == 'deleteDoctorSuccess'}">
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
                        ✅<br>Xóa bác sĩ<br>thành công!
                    </div>

                    <script>
                        setTimeout(function () {
                            window.location.href = 'doctormanager?action=all';
                        }, 500);
                    </script>
                </c:if> 
                <script>
                    function openDeleteModal(doctorId, doctorName) {
                        document.getElementById('deleteDoctorName').innerText = doctorName;
                        document.getElementById('deleteDoctorId').value = doctorId;
                        new bootstrap.Modal(document.getElementById('deleteModal')).show();

                    }

                    function openDetailModal(specialized, educationHistory, academicDegree, academicTitle, description, img, name, gender, position, department, status) {

                        if (img === 'default' || img.trim() === '') {
                            if (gender === 'Nam') {
                                document.getElementById('doctorImage').src = 'assets/images/doctors/doctormale.jpg';
                            } else if (gender === 'Nữ') {
                                document.getElementById('doctorImage').src = 'assets/images/doctors/doctorfemale.jpg';
                            } else {
                                document.getElementById('doctorImage').src = 'assets/images/doctors/doctormale.jpg';
                            }
                        } else {
                            document.getElementById('doctorImage').src = img;
                        }



                        document.getElementById('detailName').innerText = name;
                        document.getElementById('detailGender').innerText = gender;
                        document.getElementById('detailPosition').innerText = position;
                        document.getElementById('detailDepartment').innerText = department;
                        document.getElementById('detailStatus').innerText = status;
                        document.getElementById('detailSpecialized').innerText = specialized?.trim() || 'Đang cập nhật';
                        document.getElementById('detailEducation').innerHTML = educationHistory?.trim() || 'Đang cập nhật';
                        document.getElementById('detailDegree').innerText = academicDegree === "" ? 'Không' : academicDegree;
                        document.getElementById('detailTitle').innerText = academicTitle === "" ? 'Không' : academicTitle;
                        document.getElementById('description').innerHTML = description?.trim() || 'Đang cập nhật';
                        new bootstrap.Modal(document.getElementById('detailModal')).show();
                    }


                </script>






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
        <script>


        </script>
    </body>

</html>
