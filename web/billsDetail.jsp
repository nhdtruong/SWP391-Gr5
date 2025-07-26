<%-- 
    Document   : billsDetail
    Created on : 6 Jul 2025, 22:31:48
    Author     : DELL
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body class="d-flex flex-column min-vh-100">
        <jsp:include page="layout/menu_white.jsp"/>

        <section class="" style="padding-top: 70px">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12" style="height: 80px;margin-right: -30px">
                        <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                            <ul class="breadcrumb bg-transparent mb-0" style="margin-left: -30px">
                                <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                <li class="breadcrumb-item"><a href="#" style="color: #00b5f1;">Thông tin phiếu khám bệnh</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12" style="height: 40px; margin-right: -30px;">
                        <a href="bills" class="btn btn-primary btn-sm d-inline-flex align-items-center">
                            <i class="fa-solid fa-list me-2"></i> <span>Danh sách phiếu khám</span>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <section class=" flex-grow-1" style="padding-bottom: 150px">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12" style="margin-right: -30px">
                        <div class="container mt-4">
                            <div class="card mx-auto shadow rounded-4" style="max-width: 500px; border: none; border-radius: 20px">
                                <div class="card-body px-4 py-4 text-center" >
                                    <h5 class="fw-bold text-uppercase">PHIẾU KHÁM BỆNH</h5>
                                    <p class="mb-0 fw-semibold">Bệnh Viện Đa Khoa FPT</p>
                                    <p class="text-muted small"> Khu Công nghệ cao Hòa Lạc, Km29 Đại lộ Thăng Long, huyện Thạch Thất, Hà Nội </p>

                                    <div class="container">
                                        <div class="row align-items-center mb-3" style="min-height: 70px;">
                                            <!-- Bên trái -->
                                            <div class="col-6 text-start">
                                                <div style="margin-left: 30px" class="fw-semibold">Mã phiếu</div>
                                                <div class="barcode text-uppercase text-break">${bills.appointment_code}</div>
                                            </div>

                                            <!-- Bên phải -->
                                            <div class="col-6 text-end">
                                                <div class="fw-semibold">Giờ khám dự kiến</div>
                                                <div style="margin-right: 30px" class="text-primary fs-5 fw-bold">
                                                    <fmt:formatDate value="${bills.slotStart}" pattern="HH:mm" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <c:if test="${bills.status == 1}">
                                        <button class="btn btn-success w-100 mb-3 fw-bold">Đặt khám thành công</button>
                                    </c:if>

                                    <c:if test="${bills.status == 0}">
                                        <button class="btn btn-secondary w-100 mb-3 fw-bold">Đã hủy</button>
                                    </c:if>

                                    <c:if test="${bills.status == 3}">
                                        <button class="btn btn-danger w-100 mb-3 fw-bold">Đang yêu cầu hủy</button>
                                    </c:if>
                                    <c:if test="${bills.paymentStatus == 'pending'}">
                                        <p class="text-danger fw-bold">Số tiền phải thanh toán: <fmt:formatNumber value="${bills.amount}" pattern="#,##0"/> VNĐ</p>
                                    </c:if>
                                    <c:if test="${bills.paymentStatus == 'success'}">
                                        <p class="text-success fw-bold">Thanh toán thành công: <fmt:formatNumber value="${bills.amount}" pattern="#,##0"/> VNĐ</p>
                                    </c:if>
                                    <c:if test="${bills.paymentStatus == 'failed'}">
                                        <p class="text-danger fw-bold">Thanh toán không thành công: <fmt:formatNumber value="${bills.amount}" pattern="#,##0"/> VNĐ</p>
                                    </c:if>

                                    <p class="text-muted small">(Đã bao gồm phí khám + phí tiện ích)</p>

                                    <div class="text-start bg-light p-3 rounded-3">
                                        <c:if test="${not empty bills.departmentName}">
                                            <div class="d-flex mb-3 ">
                                            <span style="min-width: 150px; margin-left:20px;">Chuyên khoa:</span>
                                            <strong>${bills.departmentName}</strong>
                                        </div>
                                        </c:if>
                                        
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Mã phiếu:</span>
                                            <strong>${bills.appointment_code}</strong>
                                        </div>
                                            <c:if test="${not empty bills.doctorName}">
                                                <div class="d-flex  mb-3">
                                                    <span style="min-width: 150px; margin-left:20px;">Bác sĩ:</span>
                                                    <strong>${bills.doctorName}</strong>
                                                </div>
                                            </c:if>
                                        
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Dịch vụ:</span>
                                            <strong>${bills.serviceName}</strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Hình thức khám:</span>
                                            <strong>Không có BHYT</strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Thời gian khám:</span>
                                            <strong><fmt:formatDate value="${bills.slotStart}" pattern="HH:mm" /> - <fmt:formatDate value="${bills.dateBooking}" pattern="dd/ MM/ yyyy" /></strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Phí khám:</span>
                                            <strong><fmt:formatNumber value="${bills.amount}" pattern="#,##0"/> VNĐ</strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Bệnh nhân:</span>
                                            <strong>${bills.patientName}</strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Ngày sinh:</span>
                                            <strong>2004</strong>
                                        </div>
                                        <div class="d-flex  mb-3">
                                            <span style="min-width: 150px; margin-left:20px;">Mã bệnh nhân:</span>
                                            <strong>MP-2506300HPZSI</strong>
                                        </div>
                                    </div>


                                    <div class="text-start mt-3">
                                        <p><strong>Lưu ý:</strong></p>
                                        <p>Cắt giảm thủ tục, Lấy số trước, Thanh toán trước, Giảm xếp hàng chờ đợi</p>


                                        <div id="more-notes" style="display: none;">
                                            <ol class="mb-2 ps-3">
                                                <li>Quý khách vui lòng giữ lại hoá đơn thanh toán tại Bệnh viện đa khoa FPT để tham gia chương trình ưu đãi hoàn tiền của Doctris.</li>
                                                <li>Medpro sẽ gửi thông báo về ưu đãi hoàn tiền qua Email hoặc Zalo ngay sau khi quý khách sử dụng dịch vụ tại Bệnh viện đa khoa FPT.</li>
                                                <li>Quý khách vui lòng<strong> đến trước 20 phút</strong> tại Quầy Tiếp Nhận để được kiểm tra thông tin đặt khám và hướng dẫn.</li>
                                                <li>Vui lòng<strong> Không ăn, Không uống trà/ Cà phê hoặc Thuốc tiểu đường</strong> Không ăn, Không uống trà/ Cà phê hoặc Thuốc tiểu đường trước giờ khám 4 tiếng.</li>
                                                <li>Vui lòng liên hệ<strong> Doctris 0335704857</strong> để được hỗ trợ thay đổi lịch hẹn hoặc giải đáp về các chương trình khuyến mãi trên Doctris.</li>
                                            </ol>
                                        </div>


                                        <button class="btn btn-link p-0 text-primary fw-bold" onclick="toggleNote()" id="toggle-btn">
                                            Xem thêm <i class="fa-solid fa-chevron-down"></i>
                                        </button>
                                    </div>

                                </div>


                            </div>
                            <div class="d-flex justify-content-center gap-2 mt-3">
                                <c:if test="${bills.status != 0 && bills.status != 3}">
                                    <button type="button" class="btn btn-danger px-4"
                                            onclick="openCancelModal(
                                                            '${bills.paymentStatus}',
                                                            '${bills.appointment_code}'
                                                            )">
                                        <i class="fa-solid fa-trash me-1"></i> Hủy phiếu
                                    </button>
                                </c:if>

                                <c:if test="${bills.status == 3}">
                                    <button type="button" class="btn btn-warning text-white px-4"
                                            onclick="openCancelModal('${bills.appointmentId}', '${bills.paymentStatus}', '${filter}', '${bills.appointment_code}')">
                                        <i class="fa-solid fa-rotate-left me-1"></i> Thu hồi
                                    </button>
                                </c:if>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </section>


        <div class="modal fade" id="cancelModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <!-- Header -->
                    <div class="modal-header bg-danger text-white">
                        <h5 class="modal-title">
                            <i class="fa-solid fa-triangle-exclamation me-2"></i> Xác nhận hủy phiếu
                        </h5>
                        <button type="button" class="btn-close bg-white" data-bs-dismiss="modal"></button>
                    </div>

                    <div class="modal-body">
                        <p class="text-danger">Bạn có chắc chắn muốn hủy phiếu khám này không?</p>
                    </div>

                    <div class="modal-footer">
                        <form method="post" action="deleteBills?action=cancel">

                            <input type="hidden" name="appointmentCode" id="appointmentCode">

                            <input type="hidden" name="paymentStatus" id="cancelPaymentStatus">

                            <button type="submit" class="btn btn-danger">Hủy phiếu</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


        <c:if test="${not empty error}">
            <div class="position-fixed top-50 start-50 translate-middle w-100" style="z-index: 1050;">
                <div class="alert alert-warning alert-dismissible fade show mx-auto shadow-lg text-center"
                     role="alert"
                     style="width: 90%; max-width: 700px; font-size: 16px; border-radius: 1rem; padding: 2rem 1.5rem;">

                    <div class="d-flex flex-column align-items-center">
                        <h5 class="text-danger fw-bold mb-3">
                            <i class="fa-solid fa-triangle-exclamation me-2"></i>
                            Phiếu khám <span class="text-uppercase">"${appointmentCode}"</span> đã được thanh toán!
                        </h5>

                        <p class="text-dark mb-3" style="line-height: 1.7;">
                            Phiếu này <strong>không thể hủy trực tiếp</strong> do đã hoàn tất thanh toán.<br>
                            Tuy nhiên, bạn có thể <strong>gửi yêu cầu hoàn tiền</strong> để được quản trị viên xem xét.<br>
                            Thời gian xử lý dự kiến: <strong>3 – 5 tiếng</strong>.
                        </p>

                        <!-- Form gửi yêu cầu hoàn tiền -->
                        <form method="post" action="deleteBills?action=requestRefund" class="w-100" style="max-width: 600px;">
                            <input type="hidden" name="appointmentId" value="${bills.appointmentId}">
                            <input type="hidden" name="appointmentCode" value="${bills.appointment_code}">
                            <input type="hidden" name="patientName" value="${bills.patientName}">
                            <input type="hidden" name="doctorName" value="${bills.doctorName}">
                            <input type="hidden" name="departmentName" value="${bills.departmentName}">
                            <input type="hidden" name="serviceName" value="${bills.serviceName}">
                            <input type="hidden" name="workingDate" value="${bills.workingDate}">
                            <input type="hidden" name="slotTime" value="${bills.slotStart}">
                            <input type="hidden" name="amount" value="${bills.amount}">
                            <input type="hidden" name="note" value="${bills.note}">

                            <div class="mb-3 text-start">
                                <label for="refundReason" class="form-label fw-bold">Lý do yêu cầu hoàn tiền (không bắt buộc):</label>
                                <textarea class="form-control" id="refundReason" name="refundReason" rows="3"
                                          placeholder="Nhập lý do..."></textarea>
                            </div>

                            <div class="d-flex justify-content-center gap-2 mt-3">
                                <button type="submit" class="btn btn-danger">
                                    <i class="fa-solid fa-paper-plane me-1"></i> Gửi yêu cầu hoàn tiền
                                </button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="alert">
                                    <i class="fa-solid fa-xmark me-1"></i> Đóng
                                </button>
                            </div>
                        </form>
                    </div>


                    <button type="button" class="btn-close position-absolute top-0 end-0 m-3" data-bs-dismiss="alert" aria-label="Đóng"></button>
                </div>
            </div>
        </c:if> 

        <!-- FOOTER và các include khác -->
        <jsp:include page="layout/footer.jsp"/>
        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

        <!-- Scripts -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>

        <!-- CSS ép buộc nếu không thể chỉnh sửa file footer.jsp -->
        <style>
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                background: #e8f4fd ;
            }
            footer {
                margin-top: auto;
            }
        </style>
        <script>
                                                function toggleNote() {
                                                    const more = document.getElementById("more-notes");
                                                    const btn = document.getElementById("toggle-btn");

                                                    if (more.style.display === "none") {
                                                        more.style.display = "block";
                                                        btn.innerHTML = 'Thu gọn <i class="fa-solid fa-chevron-up"></i>';
                                                    } else {
                                                        more.style.display = "none";
                                                        btn.innerHTML = 'Xem thêm <i class="fa-solid fa-chevron-down"></i>';
                                                    }
                                                }

                                                function openCancelModal(
                                                        paymentStatus,
                                                        appointmentCode

                                                        ) {
                                                    document.getElementById("cancelPaymentStatus").value = paymentStatus;
                                                    document.getElementById("appointmentCode").value = appointmentCode;

                                                    var modal = new bootstrap.Modal(document.getElementById('cancelModal'));
                                                    modal.show();
                                                }

        </script>
    </body>
</html>



