

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                                <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Đặt khám chuyên khoa</a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="row g-4">

                        <!-- Thông tin cơ sở y tế -->
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
                                        <li class="d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-briefcase-medical opaxity"></i>
                                            </div>
                                            <div>
                                                <p>Chuyên khoa: </p>
                                                <span>${sessionScope.departmentName}</span>
                                            </div>
                                        </li>
                                        <c:if test="${not empty sessionScope.doctor}">
                                        <li class="d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-stethoscope opaxity"></i>
                                            </div>
                                            <div>
                                                <p>Chuyên khoa: </p>
                                                <span>Khám Da Liễu</span>
                                            </div>
                                        </li>
                                        </c:if>
                                       <c:if test="${not empty sessionScope.doctor}">
                                        <li class="d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-stethoscope opaxity"></i>
                                            </div>
                                            <div>
                                                <p>Chuyên khoa: </p>
                                                <span>Khám Da Liễu</span>
                                            </div>
                                        </li>
                                        </c:if>
                                       <c:if test="${not empty sessionScope.doctor}">
                                        <li class="d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-stethoscope opaxity"></i>
                                            </div>
                                            <div>
                                                <p>Chuyên khoa: </p>
                                                <span>Khám Da Liễu</span>
                                            </div>
                                        </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Chọn dịch vụ -->
                        <div class="col-12 col-lg-9">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="mb-0">Vui lòng chọn dịch vụ</h5>
                                </div>
                                <div class="card-body">
                                    <table class="table  align-middle">
                                        <thead class="">
                                            <tr>
                                                <th>#</th>
                                                <th style="width: 55%">Tên dịch vụ</th>
                                                <th style="width: 17%">Giá tiền</th>
                                                <th style=""></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Dịch vụ 1 -->
                                            <c:forEach var="s" items="${serviceOfDepartment}" varStatus="loop">
                                                <tr>
                                                    <td><strong>${loop.index +1 }</strong></td>
                                                    <td>
                                                        <b>${s.service_name}</b>
                                                        <div class="text-muted small">
                                                            <div>Lịch khám: Thứ 2 - CN</div>
                                                            <div>Phí khám: 119,500 đ, BHYT giảm trừ theo quy định.</div>
                                                        </div>
                                                    </td>
                                                   <td><fmt:formatNumber value="${s.fee}" type="number" pattern="#,##0"/> đ</td>
                                                    <td>
                                                        <button class="btn btn-primary btn-sm dkn">Đặt khám ngay</button>
                                                    </td>
                                                </tr>
                                                <tr id="bhyt-row" style="display: none;">
                                                    <td></td>
                                                    <td><b>Bạn có đăng ký khám BHYT?</b></td>
                                                    <td colspan="2">
                                                        <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="bhyt" id="bhytNo" value="0">
                                                            <label class="form-check-label" for="bhytNo">Không</label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="bhyt" id="bhytYes" value="1">
                                                            <label class="form-check-label" for="bhytYes">Có</label>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>    

                                        </tbody>
                                    </table>
                                </div>

                                <!-- Nút quay lại -->
                                <div class="card-footer text-start">
                                    <button class="btn btn-outline-secondary">
                                        <i class="bi bi-arrow-left-circle me-1"></i> Quay lại
                                    </button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <jsp:include page="layout/footer.jsp"/>

        </div>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">



        <script>
            
        </script>


        <style>
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
                text-align: center;
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

    </body>

</html>