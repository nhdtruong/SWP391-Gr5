<%-- 
    Document   : medicalExaminationForm
    Created on : 30 Jun 2025, 21:38:30
    Author     : DELL
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <div class="main-wrapper d-flex flex-column min-vh-100"> 

            <jsp:include page="layout/menu_white.jsp"/>

            <section class="bg-dashboard">
                <div class="container" style="width: 1250px">
                    <div class="row justify-content-center">
                        <div class="col-12" style="height: 80px;margin-right: -30px">
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0" style="margin-left: -30px">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>

                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Phiếu khám bệnh</a></li>

                                </ul>
                            </nav>
                        </div>
                        <jsp:include page="layout/profileMenu.jsp"/>

                        <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0" style="padding: 0 30px">
                            <div class="d-flex justify-content-between align-items-center pb-3">
                                <h4 class="mb-0">Danh sách phiếu khám bệnh</h4>

                            </div>
                            <div class="mb-3">
                                <div class="d-flex gap-3 flex-wrap" role="group" aria-label="Lọc phiếu khám">
                                    <a href="?filter=pending" class="btn ${filter == 'pending' ? 'btn-primary' : 'btn-outline-secondary'}">Chưa thanh toán</a>
                                    <a href="?filter=success" class="btn ${filter == 'success' ? 'btn-primary' : 'btn-outline-secondary'}">Đã thanh toán</a>
                                    <a href="?filter=done" class="btn ${filter == 'done' ? 'btn-primary' : 'btn-outline-secondary'}">Đã khám</a>
                                    <a href="?filter=canceled" class="btn ${filter == 'canceled' ? 'btn-primary' : 'btn-outline-secondary'}">Đã huỷ</a>
                                </div>
                            </div>

                            <c:if test="${not empty bills}">
                                <ul class="list-unstyled">
                                    <c:forEach items="${bills}" var="b">
                                        <li class="mb-4 appointment-item" data-status="${b.status}" data-payment="${b.paymentStatus}">
                                            <div class="card shadow-sm">
                                                <div class="card-body position-relative">
                                                    <!-- Header -->
                                                    <div class="d-flex justify-content-between align-items-start mb-3 pb-2 border-bottom" style="border-bottom: 2px dashed #ccc;">
                                                        <div>
                                                            <div class="mb-1">
                                                                <span class="me-2 text-secondary">Mã phiếu:</span>
                                                                <strong class="">${b.appointment_code}</strong>
                                                            </div>
                                                            <div>
                                                                <h5>${b.patientName}</h5>
                                                            </div>
                                                        </div>
                                                        <div>
                                                            <c:if test="${b.status == 1}">
                                                                <span class="px-4 py-2" style="font-size: 1.1rem;border-radius: 10px; background-color: #02cd60;color:whitesmoke;font-weight: 500;">
                                                                    Đặt khám thành công
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${b.status == 0}">
                                                                <span class="px-4 py-2" style="font-size: 1.1rem; border-radius: 10px; background-color: #d6d6d6; color: white; font-weight: 500; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                                                    Đã hủy
                                                                </span>
                                                            </c:if>
                                                            <c:if test="${b.status == 3}">
                                                                <span class="px-4 py-2" style="font-size: 1.1rem; border-radius: 10px; background-color: #dc3545; color: white; font-weight: 500;">
                                                                    Đang yêu cầu hủy
                                                                </span>
                                                            </c:if>




                                                        </div>
                                                    </div>

                                                    <!-- Body -->
                                                    <ul class="list-unstyled mb-4">

                                                        <!-- Tên bệnh viện -->
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-hospital text-primary me-2 opacit"></i>
                                                            <h5 class="mb-0" style="color:#2698D6">Bệnh viện đại học FPT</h5>
                                                        </li>

                                                        <!-- Chuyên khoa -->
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-stethoscope text-primary me-2 opacit"></i>
                                                            <span class="me-2 text-secondary" style="min-width: 150px;">Chuyên khoa:</span>
                                                            <span>${b.departmentName}</span>
                                                        </li>

                                                        <!-- Dịch vụ -->
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-hand-holding-medical text-primary me-2 opacit"></i>
                                                            <span class="me-2 text-secondary" style="min-width: 150px;">Dịch vụ:</span>
                                                            <span>${b.serviceName}</span>
                                                        </li>

                                                        <!-- Ngày khám -->
                                                        <li class="d-flex align-items-start margin5px">
                                                            <i class="fa-solid fa-calendar-days text-primary me-2 mt-1 opacit"></i>
                                                            <span class="me-2 text-secondary" style="min-width: 150px; ">Ngày khám:</span>
                                                            <span style="color:#2698D6">
                                                                <fmt:formatDate value="${b.workingDate}" pattern="dd/ MM/ yyyy" />
                                                            </span>
                                                        </li>

                                                        <!-- Giờ khám -->
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-clock text-primary me-2 opacit"></i>
                                                            <span class="me-2 text-secondary" style="min-width: 150px; ">Giờ khám dự kiến:</span>
                                                            <span style="color:#2698D6">
                                                                <fmt:formatDate value="${b.slotStart}" pattern="HH:mm" /> 
                                                            </span>
                                                        </li>
                                                    </ul>

                                                    <!-- Buttons -->
                                                    <div class="d-flex justify-content-end gap-2">
                                                        <button type="button" class="btn btn-primary" onclick="openDetailModal">
                                                            <i class="fa-solid fa-circle-info me-1"></i> Chi tiết
                                                        </button>
                                                        <c:if test="${b.status != 0 && b.status!=3}">
                                                            <button type="button" class="btn btn-danger"
                                                                    onclick="openCancelModal('${b.appointmentId}', '${b.paymentStatus}', '${filter}', '${b.appointment_code}')">
                                                                <i class="fa-solid fa-trash me-1"></i> Hủy phiếu
                                                            </button>
                                                        </c:if>
                                                        <c:if test="${b.status == 3}">
                                                            <button type="button" class="btn btn-warning text-white px-3 py-2"
                                                                    onclick="openCancelModal('${b.appointmentId}', '${b.paymentStatus}', '${filter}', '${b.appointment_code}')">
                                                                <i class="fa-solid fa-rotate-left me-1"></i> Thu hồi
                                                            </button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </c:forEach>
                                </ul>


                            </c:if>
                            <c:if test="${empty bills}">
                                <div class="alert alert-info text-center">
                                    <h5 class="mt-3 text-muted">Không tìm thấy</h5>
                                    <img src="assets/images/norecords/norecords.png" alt="Không có dữ liệu" style="width: 200px; height:200px;">
                                </div>
                            </c:if>
                        </div>

                    </div>



                    <div class="modal fade" id="cancelModal" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header bg-danger text-white">
                                    <h5 class="modal-title">
                                        <i class="fa-solid fa-triangle-exclamation me-2"></i>Xác nhận hủy phiếu
                                    </h5>
                                    <button type="button" class="btn-close bg-white" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc chắn muốn hủy phiếu khám này không?
                                </div>
                                <div class="modal-footer">
                                    <form method="post" action="bills?action=cancel">
                                        <input type="hidden" name="filter" id="filter">
                                        <input type="hidden" name="appointmentCode" id="appointmentCode">
                                        <input type="hidden" name="appointmentId" id="cancelAppointmentId">
                                        <input type="hidden" name="paymentStatus" id="cancelPaymentStatus">
                                        <button type="submit" class="btn btn-danger">Hủy phiếu</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    </form>
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

        <c:if test="${not empty error}">
            <div class="position-fixed top-50 start-50 translate-middle w-100" style="z-index: 1050;">
                <div class="alert alert-warning alert-dismissible fade show mx-auto shadow-lg text-center"
                     role="alert"
                     style="width: 90%; max-width: 800px; font-size: 18px; border-radius: 1rem; padding: 1.5rem;">

                    <strong class="text-danger" style="font-size: 20px;">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i>
                        Phiếu khám <span class="text-uppercase">"${appointmentCode}"</span> đã được thanh toán!
                    </strong>

                    <hr class="my-3">

                    <div class="text-dark" style="line-height: 1.8;">
                        Phiếu này <strong>không thể hủy ngay</strong> do đã thanh toán. <br>
                        Nếu bạn vẫn muốn hủy, hệ thống sẽ <strong>gửi yêu cầu hoàn tiền</strong> đến quản trị viên. <br>
                        <span class="text-danger fw-bold">Bạn có chắc chắn muốn tiếp tục?</span> <br>
                        Thời gian xử lý dự kiến: <strong>8 – 12 tiếng</strong>.
                    </div>

                    <!-- Form ẩn gửi yêu cầu hoàn tiền nếu người dùng xác nhận -->
                    <form method="post" action="bills?action=requestRefund" class="mt-3">
                        <input type="hidden" name="appointmentId" value="${appoinmentId}">
                        <input type="hidden" name="appointmentCode" value="${appointmentCode}">
                        <button type="submit" class="btn btn-danger">
                            Gửi yêu cầu hoàn tiền
                        </button>
                        <button type="button" class="btn btn-secondary ms-2" data-bs-dismiss="alert">
                            Đóng
                        </button>
                    </form>

                    <button type="button" class="btn-close position-absolute top-0 end-0 m-3"
                            data-bs-dismiss="alert" aria-label="Đóng"></button>
                </div>
            </div>
        </c:if>

    </body>

    <script>


                                                                        function openCancelModal(appointmentId, paymentStatus, filter, appointmentCode) {
                                                                            // Gán giá trị vào form ẩn trong modal
                                                                            document.getElementById("cancelAppointmentId").value = appointmentId;
                                                                            document.getElementById("cancelPaymentStatus").value = paymentStatus;
                                                                            document.getElementById("filter").value = filter;
                                                                            document.getElementById("appointmentCode").value = appointmentCode;
                                                                            const modal = new bootstrap.Modal(document.getElementById('cancelModal'));
                                                                            modal.show();
                                                                        }



    </script>
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

        .opacit{
            opacity: 0.6;
        }
    </style>

</html>
