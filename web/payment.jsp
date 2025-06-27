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
                        <!-- Cột trái: gồm 2 card nhỏ -->
                        <div class="col-12 col-lg-3">
                            <!-- Thông tin Bệnh nhân -->
                            <div class="card mb-4">
                                <div class="card-header">
                                    <h5 class="mb-0">Thông tin Bệnh nhân</h5>
                                </div>
                                <div class="card-body">
                                    <ul class="list-unstyled">
                                        <!-- Tên bệnh nhân -->
                                        <li class="mb-2 d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-user opaxity"></i>
                                            </div>
                                            <div>
                                                <p>${sessionScope.patient.patientName}</p>
                                            </div>
                                        </li>

                                        <!-- Số điện thoại -->
                                        <li class="mb-2 d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-phone opaxity"></i>
                                            </div>
                                            <div>
                                                <p>${sessionScope.patient.phone}</p>
                                            </div>
                                        </li>

                                        <!-- Địa chỉ -->
                                        <li class="mb-2 d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-location-dot opaxity"></i>
                                            </div>
                                            <div>
                                                <p>${sessionScope.patient.address}</p>
                                            </div>
                                        </li>
                                    </ul>

                                </div>
                            </div>

                            <!-- Thông tin cơ sở y tế -->
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
                                                <p>Bệnh Viện Đại Học FPT</p>
                                                <span class="mb-0 text-muted">78 Đ.Giải Phóng, Đống Đa, Tp.Hà Nội</span>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <!-- Cột phải: Chọn phương thức thanh toán -->
                        <div class="col-12 col-lg-9">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="mb-0">Chọn phương thức thanh toán</h5>
                                </div>
                                <div class="card-body" style="padding: 0">
                                    <form action="payment" method="post">
                                        <div class="row g-3">
                                            <!-- Phương thức thanh toán -->

                                            <div class="col-md-7">
                                                <div class="gap-4" style="margin: 20px 0px 0px 20px">
                                                    <!-- VietQR -->
                                                    <label class="d-flex align-items-start mb-2 payment-option" for="vietQR">
                                                        <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="vietQR" value="vnPay" />
                                                        <div>
                                                            <span class="form-check-label">VnPay</span><br />
                                                            <small class="text-muted">Thanh toán Chuyển khoản bằng các ứng dụng Ngân hàng/Ví điện tử</small>
                                                        </div>
                                                    </label>

                                                    <!-- Ví Momo -->
                                                    <label class="d-flex align-items-start mb-2 payment-option" for="vimo">
                                                        <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="vimo" value="Ví Momo" disabled />
                                                        <div>
                                                            <span class="form-check-label">Ví Momo</span>
                                                        </div>
                                                    </label>

                                                    <!-- Thẻ quốc tế -->
                                                    <label class="d-flex align-items-start mb-2 payment-option" for="theQT">
                                                        <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="theQT" value="Thẻ quốc tế Visa, Master, JCB" disabled  />
                                                        <div>
                                                            <span class="form-check-label">Thẻ quốc tế Visa, Master, JCB</span><br />
                                                            <small class="text-muted">Một số ngân hàng phải đăng kí (thanh toán Online)</small>
                                                        </div>
                                                    </label>

                                                    <!-- Internet Banking -->
                                                    <label class="d-flex align-items-start mb-2 payment-option" for="internetBanking">
                                                        <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="internetBanking" value="ATM / IB" disabled />
                                                        <div>
                                                            <span class="form-check-label">Thẻ ATM nội địa / Internet Banking</span><br />
                                                            <small class="text-muted">Một số ngân hàng phải đăng kí (Online/Internet banking)</small>
                                                        </div>
                                                    </label>

                                                    <!-- Thanh toán hộ -->
                                                    <label class="d-flex align-items-start mb-2 payment-option" for="hom">
                                                        <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="hom" value="Thanh toán hộ" disabled />
                                                        <div>
                                                            <span class="form-check-label">Thanh toán hộ</span><br />
                                                            <small class="text-muted">Chia sẻ link thanh toán cho người thân</small>
                                                        </div>
                                                    </label>

                                                    <!-- Phòng khám -->
                                                    <c:if test="${sessionScope.serviceBooking.payment_type_id == 1}" >
                                                        <label class="d-flex align-items-start mb-2 payment-option" for="phongKham">
                                                            <input class="form-check-input me-2 mt-1" type="radio" name="paymentMethod" id="phongKham" value="taiPhongKham" />
                                                            <div>
                                                                <span class="form-check-label">Thanh toán tại Phòng khám</span>
                                                            </div>
                                                        </label>
                                                    </c:if>

                                                </div>

                                            </div>
                                            <!-- Thông tin thanh toán -->
                                            <div class="col-md-5" >
                                                <div class=" rounded p-2 mb-2 d-flex align-items-center " style="background-color: #f8f9fa;">
                                                    <i class="bi bi-credit-card-2-front fs-4 me-2" style="color: #11a2f3"></i>
                                                    <h4 class="" style="font-weight: 700;color:  #11a2f3">Thông tin thanh toán</h4>
                                                </div>
                                                <div class="mb-3" style="margin-right: 20px; padding: 12px;
                                                     border: 3px solid #bee5eb;
                                                     border-radius: 6px;

                                                     user-select: none;
                                                     transition: all 0.2s ease-in-out;">

                                                    <div class="">

                                                        <div class="d-flex justify-content-between align-items-center border-bottom mb-4">
                                                            <span class="text-body d-flex align-items-center opaxity1" style="font-weight:600">
                                                                <i class="bi bi-diagram-3 me-2 "></i>Chuyên khoa
                                                            </span>
                                                            <span>${sessionScope.departmentName}</span>
                                                        </div>

                                                        <div class="d-flex justify-content-between align-items-center border-bottom mb-4">
                                                            <span class="text-body d-flex align-items-center opaxity1" style="font-weight:600">
                                                                <i class="bi bi-person-vcard me-2 "></i>Bác sĩ
                                                            </span>
                                                            <span>${sessionScope.doctorName}</span>
                                                        </div>

                                                        <div class="d-flex justify-content-between align-items-center border-bottom mb-4">
                                                            <span class="text-body d-flex align-items-center opaxity1" style="font-weight:600">
                                                                <i class="bi bi-clipboard2-check me-2 "></i>Dịch vụ
                                                            </span>
                                                            <span>${sessionScope.serviceBooking.service_name}</span>
                                                        </div>

                                                        <div class="d-flex justify-content-between align-items-center border-bottom mb-4">
                                                            <span class="text-body d-flex align-items-center opaxity1" style="font-weight:600">
                                                                <i class="bi bi-calendar-event me-2 "></i>Ngày khám
                                                            </span>
                                                            <span><fmt:formatDate value="${sessionScope.dateBooking}" pattern="dd/ MM/ yyyy"/></span>
                                                        </div>

                                                        <div class="d-flex justify-content-between align-items-center border-bottom mb-4">
                                                            <span class="text-body d-flex align-items-center opaxity1" style="font-weight:600">
                                                                <i class="bi bi-clock me-2 "></i>Giờ khám
                                                            </span>
                                                            <span><fmt:formatDate value="${sessionScope.slotStart}" pattern="HH:mm"/> - <fmt:formatDate value="${sessionScope.slotEnd}" pattern="HH:mm"/></span>
                                                        </div>

                                                        <div class="d-flex justify-content-between align-items-center mt-3 pt-2 fs-5 fw-bold">
                                                            <span class="text-body" style="font-weight:600">Tiền khám</span>
                                                            <c:if test="${sessionScope.isBHYT == '0'}"><c:set var="feePay" value="${sessionScope.serviceBooking.fee}"/><span style="color:#11a2f3"> <fmt:formatNumber value="${sessionScope.serviceBooking.fee}" pattern="#,##0"/> đ</span></c:if>
                                                            <c:if test="${sessionScope.isBHYT == '1'}">
                                                                <c:set var="feePay" value="${sessionScope.serviceBooking.fee - sessionScope.serviceBooking.discount}"/>
                                                                <span style="color:#11a2f3">

                                                                    <fmt:formatNumber value="${sessionScope.serviceBooking.fee - sessionScope.serviceBooking.discount}" pattern="#,##0"/> đ
                                                                </span>
                                                            </c:if>
                                                        </div>


                                                    </div>
                                                </div>
                                                <div class="d-flex justify-content-between mt-2 mb-2" style="margin-right: 20px">
                                                    <c:set var="feeTGTT" value="${1100}"/>             
                                                    <span>Phí nền tảng + Phí TGTT:</span>
                                                    <span> <fmt:formatNumber value="${feeTGTT}" pattern="#,##0"/> đ</span>
                                                </div>
                                                <div class="d-flex justify-content-between align-items-center mt-3 mb-4 fs-4" style="margin-right: 20px">
                                                    <c:set var="total" value="${feePay + feeTGTT}"/>
                                                    <strong>Tổng cộng:</strong>
                                                    <strong style="color:#11a2f3"> <fmt:formatNumber value="${total}" pattern="#,##0"/> đ đ</strong>
                                                </div>


                                                <input type="hidden" name="total" value="${total}" />
                                                <button type="submit" class="btn btn-secondary w-100" id="btnPay">Thanh toán</button>

                                            </div>
                                        </div>
                                    </form>
                                </div>

                                <!-- Footer -->
                                <div class="card-footer d-flex justify-content-between mt-4">
                                    <a href="confirmInformation" class="btn btn-outline-secondary">
                                        <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                    </a>

                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <jsp:include page="layout/footer.jsp"/>

        </div>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Bootstrap 5 JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">


        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const radios = document.querySelectorAll('input[name="paymentMethod"]');
                const payButton = document.getElementById("btnPay");

                radios.forEach(radio => {
                    radio.addEventListener("change", function () {
                        payButton.removeAttribute("disabled");
                        payButton.classList.remove("btn-secondary");
                        payButton.classList.add("btn-primary");
                    });
                });
            });
        </script>





        <style>
            .card,
            .card:hover,
            .card:focus,
            .card:active {
                box-shadow: none !important;
                transform: none !important;
                transition: none !important;
                border: none !important;
            }
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
            .opaxity1{
                opacity: 0.9;
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
            .payment-option {
                cursor: pointer;
                padding: 10px;
                border-radius: 6px;
                user-select: none;
            }

            .payment-option:hover {
                background-color: #f8f9fa;
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
