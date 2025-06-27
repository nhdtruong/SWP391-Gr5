<%-- 
    Document   : ConfirmInformation
    Created on : 22 Jun 2025, 05:51:40
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <div class="wrapper">
            <jsp:include page="layout/menu_white.jsp"/>
            <div class="main-content" >
                <div class="container my-4" style="min-height: 600px">

                    <div class="col-12" style="height: 80px;margin-right: -30px">
                        <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                            <ul class="breadcrumb bg-transparent mb-0" style="margin-left: -30px">
                                <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Xác nhận thông tin</a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="row g-4">


                        <div class="col-12 col-lg-3">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="mb-0">Thông tin cơ sở y tế</h5>
                                </div>
                                <div class="card-body">
                                    <ul class="list-unstyled">
                                        <li class="mb-3 d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-hospital opaxity"></i>
                                            </div>
                                            <div>
                                                <p>Bệnh Viện Đaị Học FPT</p>
                                                <span class="mb-0 text-muted">78 Đ.Giải Phóng, Đống Đa, Tp.Hà Nội</span>
                                            </div>
                                        </li>

                                    </ul>
                                </div>
                            </div>
                        </div>



                        <div class="col-12 col-lg-9">
                            <div class="col-12 col-lg-12">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="mb-0">Xác nhận thông tin khám</h5>
                                    </div>
                                    <div class="card-body" style="padding: 0">
                                        <table class="table  align-middle">
                                            <thead class="">
                                                <tr>
                                                    <th style="width: 5%">#</th>
                                                    <th style="width: 19%">Chuyên khoa</th>
                                                    <th style="width: 23%">Dịch vụ</th>
                                                    <th style="width: 15%" >Bác sĩ</th>
                                                    <th style="width: 20%">Thời gian khám</th>
                                                    <th style="width: 20%">Tiền khám</th>
                                                    <th style="width: 5%"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <td>1</td>
                                            <td>${sessionScope.departmentName}</td>
                                            <td>${sessionScope.serviceBooking.service_name}</td>
                                            <td>${sessionScope.doctorName}</td>
                                            <td><fmt:formatDate value="${sessionScope.slotStart}" pattern="HH:mm"/> - <fmt:formatDate value="${sessionScope.slotEnd}" pattern="HH:mm"/><br/><fmt:formatDate value="${sessionScope.dateBooking}" pattern="dd/ MM/ yyyy"/></td>
                                            <c:if test="${sessionScope.isBHYT == '0'}"><td> <fmt:formatNumber value="${sessionScope.serviceBooking.fee}" pattern="#,##0"/> đ</td></c:if>
                                            <c:if test="${sessionScope.isBHYT == '1'}">
                                                <td>
                                                    <del>
                                                        <fmt:formatNumber value="${sessionScope.serviceBooking.fee}" pattern="#,##0"/> đ
                                                    </del>
                                                    &nbsp;
                                                    <fmt:formatNumber value="${sessionScope.serviceBooking.fee - sessionScope.serviceBooking.discount}" pattern="#,##0"/> đ
                                                </td>
                                            </c:if>
                                            <td>
                                                <a href="booking?stepName=doctor&departmentId=${sessionScope.departmentId}&departmentName=${sessionScope.departmentName}" title="Xóa lịch khám" onclick="return confirm('Bạn có chắc muốn xóa thông tin đặt khám này?')">
                                                    <i class="fa fa-trash" style="color: red;"></i>
                                                </a>
                                            <td>




                                                </tbody>
                                        </table>
                                    </div>



                                </div>
                            </div>
                            <div class="col-12 col-lg-12" style="margin-top: 40px">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="mb-0">Thông tin bệnh nhân</h5>
                                    </div>
                                    <c:set var="p" value="${sessionScope.patient}"/>
                                    <div class="card-body">
                                        <div class="row mb-2">
                                            <div class="col-md-6"><i class="bi bi-person-fill me-1"></i> <span class="x">Họ và tên:</span><strong>${p.patientName}</strong></div>
                                            <div class="col-md-6"><i class="bi bi-gender-ambiguous me-1"></i> <span class="x">Giới tính:</span><strong>${p.gender}</strong></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-6"><i class="bi bi-calendar-event me-1"></i> <span class="x">Ngày sinh:</span><strong><fmt:formatDate value="${p.dob}" pattern="dd/ MM/ yyyy"/></strong></div>
                                            <div class="col-md-6"><i class="bi bi-person-vcard me-1"></i> <span class="x">CMND:</span><strong>${p.cccd}</strong></div>
                                        </div>
                                        <div class="row mb-2">
                                            <div class="col-md-6"><i class="bi bi-envelope-fill me-1"></i> <span class="x">Email:</span>
                                                <c:if test="${not empty p.email}"><strong>${p.email}</strong></c:if>
                                                <c:if test="${empty p.email}"><strong>Chưa cập nhật</strong></c:if>
                                                </div>
                                                <div class="col-md-6"><i class="bi bi-people-fill me-1"></i> <span class="x">Dân tộc:</span>
                                                <c:if test="${not empty p.nation}"><strong>${p.nation}</strong></c:if>
                                                <c:if test="${empty p.nation}"><strong>Chưa cập nhật</strong></c:if>
                                                </div>
                                            </div>
                                            <div class="row mb-2">
                                                <div class="col-md-6"><i class="bi bi-credit-card me-1"></i> <span class="x">Mã BHYT:</span>
                                                <c:if test="${not empty p.bhyt}"><strong>${p.bhyt}</strong></c:if>
                                                <c:if test="${empty p.bhyt}"><strong>Chưa cập nhật</strong></c:if>
                                                </div>
                                                <div class="col-md-6"><i class="bi bi-geo-alt-fill me-1"></i> <span class="x">Địa chỉ:</span><strong>${p.address}</strong></div>
                                        </div>

                                        <div class="alert mt-3 mb-0" role="alert"
                                             style="background-color: #f8d7da; color: #842029;">
                                            <i class="bi bi-exclamation-triangle-fill me-1"></i>
                                            Trong thời gian quy định, nếu quý khách hủy phiếu khám sẽ được hoàn lại tiền khám và các dịch vụ đặt thêm (không bao gồm phí tiện ích).
                                        </div>
                                    </div>


                                </div>
                                <div class="card-footer d-flex justify-content-between" style="margin-top: 20px">
                                    <a href="chooseRecords" class="btn btn-outline-secondary">
                                        <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                    </a>
                                    <a href="confirmFinalInformation" class="btn btn-info">
                                        <i class="fa-solid fa-check me-1"></i> Xác nhận
                                    </a>       
                                </div>
                            </div>

                        </div>




                    </div>
                </div>
            </div>

            <jsp:include page="layout/footer.jsp"/>

        </div>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Bootstrap 5 JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <c:if test="${not empty error}">
            <script>
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        var errorModal = new bootstrap.Modal(document.getElementById("errorModal"));
                                                        errorModal.show();
                                                    });
            </script>
        </c:if>

        <script>
            function showSlotsByDate(id) {
                const allGroups = document.querySelectorAll('.slot-group');
                allGroups.forEach(div => div.style.display = 'none');
                document.getElementById(id).style.display = 'block';
            }
        </script>




        <style>
            .x{
                display:  inline-block;
                min-width: 80px;
            }
            .doctor-link {
                text-decoration: none;
                color: inherit;
                display: block;
            }

            .doctor-card {
                transition: border 0.3s, box-shadow 0.3s;
            }

            .doctor-link:hover .doctor-card {
                border: 2px solid #4da6ff; /* Viền xanh khi hover */
                box-shadow: 0 0 10px rgba(77, 166, 255, 0.3); /* Bóng xanh nhẹ */
                border-radius: 10px;
            }
            .card{
                border-radius: 8px;
                background: #fff;
                box-shadow: 0 4px 30px 0 rgba(116,157,206,.2);
                overflow: hidden;
                padding-bottom: 3px;
            }

            html, body {
                height: 100%;
                margin: 0;
            }

            .wrapper {
                display: flex;
                flex-direction: column;
                min-height: 100vh; /* Quan trọng để đẩy footer xuống */
            }

            .main-content {
                flex: 1;
                margin-top: 74px;
                background: #e8f4fd /* Chiếm hết chiều cao còn lại */
            }
            .card-header{
                background: linear-gradient(36deg,#00b5f1,#00e0ff);
            }
            .mb-0{
                color: white;

            }

            .table.no-border,
            .table.no-border th,
            .table.no-border td {
                border: none ;
            }
            .table>:not(caption)>*>* {
                padding: 1rem 1rem;
                color: var(--bs-table-color-state, var(--bs-table-color-type, var(--bs-table-color)));
                background-color: var(--bs-table-bg);
                border-bottom-width: var(--bs-border-width);
                box-shadow: inset 0 0 0 9999px var(--bs-table-bg-state, var(--bs-table-bg-type, var(--bs-table-accent-bg)));
                font-family: Roboto,sans-serif!important;
                font-size: medium;
            }

            tbody, td, tfoot, th, thead, tr {
                border-color:white;
                border-style: none;
                border-width: 0;

            }
            tbody > tr{
                border-top:  .5px solid #b2b2b2;

            }
            .card-body{
                font-family: Roboto,sans-serif!important;

            }
            p {
                margin-bottom: 0px;
            }
            .opaxity{
                opacity: 0.5;
            }
            .dkn{
                background: linear-gradient(36deg,#00b5f1,#00e0ff);

            }
            .btn.btn-sm {
                padding: 9px 5px;
                font-weight: 600;
                font-family: Roboto,sans-serif!important;
                font-size: 15px;
                width: 160px;
            }

        </style>
        <!-- Modal thông báo lỗi -->
        <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content border-danger">
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title" id="errorModalLabel"><i class="fa fa-exclamation-triangle me-2"></i>Thông báo lỗi</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Đóng"></button>
                    </div>
                    <div class="modal-body text-center fw-bold">
                        ${error}
                    </div>
                    <div class="modal-footer justify-content-center">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
