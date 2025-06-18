<%-- 
    Document   : records
    Created on : 13 Jun 2025, 03:09:38
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <div class="main-wrapper d-flex flex-column min-vh-100"> 

            <jsp:include page="layout/menu_white.jsp"/>

            <section class="bg-dashboard">
                <div class="container" style="width: 1250px">
                    <div class="row justify-content-center">
                        <jsp:include page="layout/profileMenu.jsp"/>

                        <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0" style="padding: 0 30px">
                            <div class="d-flex justify-content-between align-items-center pb-3">
                                <h4 class="mb-0">Danh sách hồ sơ bệnh nhân</h4>
                                <a href="addrecords" class="btn btn-primary">
                                    <i class="fa-solid fa-plus me-1"></i> Thêm hồ sơ
                                </a>
                            </div>
                            <c:if test="${empty records}">
                                <div class="text-center mt-5">
                                    <h5 class="mt-3 text-muted">Bạn chưa có hồ sơ bệnh nhân. Vui lòng tạo mới hồ sơ để được đặt khám.</h5>
                                    <img src="assets\images\norecords\norecords.png" alt="Không có dữ liệu" style="max-width: 100%; height: auto;">

                                </div>
                            </c:if>
                            <c:if test="${not empty records}">
                                <ul class="list-unstyled">
                                    <c:forEach items="${records}" var="r">

                                        <li class="mb-4">
                                            <div class="card shadow-sm">
                                                <div class="card-body">
                                                    <ul class="list-unstyled mb-4">

                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-user text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Họ và tên:</strong>
                                                            <span class="text-primary">${r.getPatientName()}</span>
                                                        </li>

                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-calendar-day text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Ngày sinh:</strong>
                                                            <span>${r.getDob()}</span>
                                                        </li>

                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-phone text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Số điện thoại:</strong>
                                                            <span>${r.getPhone()}</span>
                                                        </li>

                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-venus-mars text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Giới tính:</strong>
                                                            <span>${r.getGender()}</span>
                                                        </li>

                                                        <li class="d-flex align-items-start margin5px">
                                                            <i class="fa-solid fa-location-dot text-primary me-2 mt-1"></i>
                                                            <strong class="me-2 text-secondary">Địa chỉ:</strong>
                                                            <span>${r.getAddress()}</span>
                                                        </li>

                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-people-group text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Dân tộc:</strong>
                                                            <span>${r.getNation()}</span>
                                                        </li>

                                                    </ul>

                                                    <div class="d-flex justify-content-end gap-2">
                                                        <button type="button" class="btn btn-primary"
                                                                onclick="openDetailModal(
                                                                                '${fn:escapeXml(fn:escapeXml(r.getPatientName()))}',
                                                                                '${fn:escapeXml(r.getDob())}',
                                                                                '${fn:escapeXml(r.getPhone())}',
                                                                                '${fn:escapeXml(fn:escapeXml(r.getGender()))}',
                                                                                '${fn:escapeXml(fn:escapeXml(r.getAddress()))}',
                                                                                '${fn:escapeXml(fn:escapeXml(r.getNation()))}'
                                                                                )">
                                                            <i class="fa-solid fa-circle-info me-1"></i> Chi tiết
                                                        </button>
                                                        <a href="updaterecords?id=${r.getPatientId()}" class="btn btn-warning">
                                                            <i class="fa-solid fa-pen-to-square me-1"></i> Sửa hồ sơ
                                                        </a>
                                                        <button type="button" class="btn btn-danger"
                                                                onclick="openDeleteModal('${fn:escapeXml(fn:escapeXml(r.getPatientName()))}','${r.getPatientId()}')"
>
                                                            <i class="fa-solid fa-trash me-1"></i> Xóa hồ sơ
                                                        </button>
                                                    </div>




                                                </div>
                                            </div>
                                        </li>

                                    </c:forEach>
                                </ul>
                            </c:if>

                        </div>

                    </div>




                    <div class="modal fade" id="detailModal" tabindex="-1">
                        <div class="modal-dialog modal-lg modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header bg-primary text-white">
                                    <h5 class="modal-title">
                                        <i class="fa-solid fa-circle-info me-2"></i>Chi tiết hồ sơ
                                    </h5>
                                    <button type="button" class="btn-close bg-white" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body">
                                    <p>
                                        <i class="fa-solid fa-user text-primary me-2 minwi"></i>
                                        <strong class="minw" >Họ tên:</strong> <span id="detailName" class="text-primary"></span>
                                    </p>
                                    <p>
                                        <i class="fa-solid fa-calendar-day text-primary me-2 minwi "></i>
                                        <strong class="minw">Ngày sinh:</strong> <span id="detailDob"></span>
                                    </p>
                                    <p>
                                        <i class="fa-solid fa-phone text-primary me-2 minwi"></i>
                                        <strong class="minw">Số điện thoại:</strong> <span id="detailPhone"></span>
                                    </p>
                                    <p>
                                        <i class="fa-solid fa-venus-mars text-primary me-2 minwi"></i>
                                        <strong class="minw">Giới tính:</strong> <span id="detailGender"></span>
                                    </p>
                                    <p>
                                        <i class="fa-solid fa-location-dot text-primary me-2 minwi"></i>
                                        <strong class="minw">Địa chỉ:</strong> <span id="detailAddress"></span>
                                    </p>
                                    <p>
                                        <i class="fa-solid fa-people-group text-primary me-2 minwi" ></i>
                                        <strong class="minw">Dân tộc:</strong> <span id="detailNation"></span>
                                    </p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Modal Xóa hồ sơ -->
                    <div class="modal fade" id="deleteModal" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header bg-danger text-white">
                                    <h5 class="modal-title"><i class="fa-solid fa-triangle-exclamation me-2"></i>Xóa hồ sơ</h5>
                                    <button type="button" class="btn-close bg-white" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc chắn muốn xóa hồ sơ <strong id="deleteName"></strong> không?
                                </div>
                                <div class="modal-footer">
                                    <form method="post" action="updaterecords?action=delete">
                                        <input type="hidden" name="patientId" id="deleteId">
                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>



                </div>

            </section>

            <div class="mt-auto">
                <jsp:include page="layout/footer.jsp"/>
            </div>

        </div> 

        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>




        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">


        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
    <style>
        .modal-backdrop.show {
            opacity: 0 !important;
        }
        .margin5px{
            margin-bottom: 5px;
        }
        .minw{
            display: inline-block;
            min-width: 150px;
        }
        .minwi{
            min-width: 20px;
        }
        .modal-content{
            width: 80%;
        }
        p{
            margin-bottom: 1.5rem;
        }

    </style>
    <script>
                                                                    function openDetailModal(name, dob, phone, gender, address, nation) {
                                                                        document.getElementById("detailName").innerText = name;
                                                                        document.getElementById("detailDob").innerText = dob;
                                                                        document.getElementById("detailPhone").innerText = phone;
                                                                        document.getElementById("detailGender").innerText = gender;
                                                                        document.getElementById("detailAddress").innerText = address;
                                                                        document.getElementById("detailNation").innerText = nation;
                                                                        var myModal = new bootstrap.Modal(document.getElementById('detailModal'));
                                                                        myModal.show();
                                                                    }

                                                                    function openDeleteModal(name, id) {
                                                                        document.getElementById("deleteName").innerText = name;
                                                                        document.getElementById("deleteId").value = id;
                                                                        var delModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                                                                        delModal.show();
                                                                    }
    </script>


</html>
