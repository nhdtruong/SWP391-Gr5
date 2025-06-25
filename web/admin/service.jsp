

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                        <!-- Row chứa tiêu đề, tìm kiếm, lọc và thêm mới -->
                        <div class="row" style="text-align: center">
                            <div class="col-md-3 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Service</h5>
                                </div>
                                <div class="col-md-7">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="servicemanager?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input value="${text}" type="text" class="form-control border rounded-pill" name="text" id="s" placeholder="Tìm kiếm dịch vụ...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-7">
                                <form action="servicemanager?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="justify-content-md-end row">

                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-3">
                                                <label class="form-label">Thể loại</label>
                                            </div>
                                            <div class="col-md-9">
                                                <select name="cartegoryService_id" class="form-select">
                                                    <option <c:if test="${ cartegoryService_id == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${category}" var="ca">
                                                        <option <c:if test="${ca.getId().toString() == cartegoryService_id}"> selected </c:if> value="${ca.getId()}">${ca.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-5 row align-items-center">
                                            <div class="col-md-4">
                                                <label class="form-label">Chuyên khoa</label>
                                            </div>
                                            <div class="col-md-8">
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
                            <div class="col-md-2">
                                <a href="addservice" type="button"class="btn btn-info">Add+</a>         

                            </div>                             
                        </div>

                        <!-- Danh sách bảng -->
                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center" style="text-align: center">
                                        <thead >
                                            <tr>
                                                <th class="border-bottom p-3">STT</th>
                                                <th class="border-bottom p-3">Tên dịch vụ</th>
                                                <th class="border-bottom p-3">Thể loại</th>
                                                <th class="border-bottom p-3">Chuyên khoa</th>
                                                <th class="border-bottom p-3">Phí</th>
                                                <th class="border-bottom p-3">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody >
                                             <c:if test="${empty service}">
                                                <tr>
                                                    <td colspan="6" class="text-center text-danger p-3">
                                                        Không có dịch vụ nào được tìm thấy.
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <c:set var="st" value="${requestScope.start + 1 }"/>
                                            <c:forEach var="s" items="${service}"  >

                                                <tr>
                                                    <td class="p-3">${st}</td>
                                                    <td class="p-3">${s.service_name}</td>
                                                    <td class="p-3">${s.category_service.name}</td>
                                                    <td class="p-3">${s.department.department_name}</td>
                                                    <td class="p-3"><fmt:formatNumber value="${s.fee}" pattern="#,##0"/> đ</td>
                                                    <td class="p-3">
                                                        <a href="#" class="btn btn-info" onclick="openServiceDetailModal('${fn:escapeXml(s.getService_name())}', '${fn:escapeXml(s.getCategory_service().getName())}', '${fn:escapeXml(s.getDepartment().getDepartment_name())}', '${fn:escapeXml(s.getDescription())}', '${s.isIs_bhyt() ? "Có" : "Không"}', '${s.getFee()}', '${s.getDiscount()}', '${fn:escapeXml(s.getPayment_type_id()) == 1 ? "Thanh toán tại bệnh viện" : "Thanh toán online"}', '${s.getImg()}')">Chi tiết</a>
                                                        <a href="updateservice?action=updateService&serviceId=${s.service_id}" class="btn btn-primary">Sửa</a>
                                                        <a href="#" class="btn btn-danger"
                                                           onclick="openDeleteModal('${s.getService_id()}' )">Xóa</a>

                                                    </td>
                                                </tr>
                                                <c:set var="st" value="${st + 1}"  />
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!-- Phân trang -->
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

                <!-- Modal Chi tiết dịch vụ -->
                <div class="modal fade" id="detailModal" tabindex="-1" aria-labelledby="detailModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-xl">
                        <div class="modal-content" style="width: 80% ;margin: auto">
                            <div class="modal-header bg-info text-white">
                                <h5 class="modal-title" id="detailModalLabel">Thông tin Dịch vụ</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                            </div>
                            <div class="modal-body p-4">
                                <div class="row">
                                    <!-- Hình ảnh dịch vụ -->
                                    <div class="col-md-4 text-center">
                                        <img id="serviceImage" src="" alt="Service Image" class="img-fluid rounded shadow" style="width: 200px;height: 200px; object-fit: cover;">
                                    </div>

                                    <!-- Thông tin chi tiết -->
                                    <div class="col-md-8">
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Tên dịch vụ:</div>
                                            <div class="col-md-9" id="serviceName"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Thể loại:</div>
                                            <div class="col-md-9" id="serviceCategory"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Thuộc khoa:</div>
                                            <div class="col-md-9" id="serviceDepartment"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-12 fw-bold">Mô tả:</div>
                                        </div>
                                        <div class="row mb-2">
                                            <div style="margin-left: 30px" class="col-md-12" id="serviceDescription"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Áp dụng BHYT:</div>
                                            <div class="col-md-9" id="serviceInsurance"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Phí:</div>
                                            <div class="col-md-9" id="serviceFee"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Giảm giá:</div>
                                            <div class="col-md-9" id="serviceDiscount"></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-3 fw-bold">Phương thức thanh toán:</div>
                                            <div class="col-md-9" id="servicePayment"></div>
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
                            <form action="updateservice?action=delete" method="post">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                </div>
                                <div class="modal-body">
                                    <p>Bạn có chắc chắn muốn xóa dịch vụ này không?</p>
                                    <input type="hidden" name="service_id" id="deleteServiceId">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                </div>
                            </form>
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
        <script>
                                                               function openServiceDetailModal(name, category, department, description, bhyt, fee, discount, payment, img) {
                                                                   document.getElementById('serviceName').innerText = name;
                                                                   document.getElementById('serviceCategory').innerText = category;
                                                                   document.getElementById('serviceDepartment').innerText = department;
                                                                   document.getElementById('serviceDescription').innerHTML = description?.trim() || 'Đang cập nhật';
                                                                   document.getElementById('serviceInsurance').innerText = bhyt;
                                                                   document.getElementById('serviceFee').innerText = parseFloat(fee).toLocaleString('vi-VN') + " VNĐ";
                                                                   document.getElementById('serviceDiscount').innerText = parseFloat(discount).toLocaleString('vi-VN') + " VNĐ";
                                                                   document.getElementById('servicePayment').innerText = payment;

                                                                   document.getElementById('serviceImage').src = img && img !== 'default'
                                                                           ? img
                                                                           : 'assets/images/services/defaultService.png';

                                                                   new bootstrap.Modal(document.getElementById('detailModal')).show();
                                                               }

                                                               function openDeleteModal(serviceId) {
                                                                   
                                                                   document.getElementById('deleteServiceId').value = serviceId;
                                                                   new bootstrap.Modal(document.getElementById('deleteModal')).show();
                                                               }



        </script>
        <style>
            tbody, td, tfoot, th, thead, tr {
                border-color: inherit;
                border-style: none;
                border-width: 0;
            }
        </style>
    </body>
</html>