<%-- 
    Document   : updateAppointent
    Created on : 1 Jul 2025, 14:36:12
    Author     : DELL
--%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                        <div style="margin-bottom: 30px"> <h4>Chỉnh sửa lịch hẹn </h4></div>

                        <c:set var="a" value="${appointmentView}"></c:set>

                            <form action="updateDoctorScheduleDetail?action=update" method="post">
                                <ul class="list-unstyled">
                                    <li class="mb-4 appointment-item">
                                        <div class="card shadow-sm">
                                            <div class="card-body">
                                                <ul class="list-unstyled mb-4">

                                                    <!-- Hàng 1: Mã phiếu ở giữa -->
                                                    <li class="d-flex justify-content-center align-items-center margin5px mb-3">
                                                        <i class="fa-solid fa-user text-primary me-2"></i>
                                                        <strong class="me-2 text-secondary">Mã phiếu:</strong>
                                                        <span class="text-primary">${a.appointment_code}</span>
                                                </li>

                                                <!-- Hàng 2+: Chia mỗi hàng 2 mục -->
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-calendar-day text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Tên:</strong>
                                                            <span>${a.patientName}</span>
                                                        </li>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-phone text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Chuyên khoa:</strong>
                                                            <span>${a.departmentName}</span>
                                                        </li>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-venus-mars text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Dịch vụ:</strong>
                                                            <span>${a.serviceName}</span>
                                                        </li>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-location-dot text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Ngày khám:</strong>
                                                            <span>${a.workingDate}</span>
                                                        </li>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <li class="d-flex align-items-center margin5px">
                                                            <i class="fa-solid fa-people-group text-primary me-2"></i>
                                                            <strong class="me-2 text-secondary">Dân tộc:</strong>
                                                            <c:if test="${empty r.getNation()}">
                                                                <span>Giờ khám dự kiến:</span>
                                                            </c:if>
                                                            <span>${a.slotStart}</span>
                                                        </li>
                                                    </div>
                                                </div>

                                            </ul>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </form>
                        <div class="container mt-4">
                            <div class="card mx-auto shadow rounded-4" style="max-width: 500px; border: none;">
                                <div class="card-body px-4 py-4 text-center">
                                    <h5 class="fw-bold text-uppercase">PHIẾU KHÁM BỆNH</h5>
                                    <p class="mb-0 fw-semibold">Phòng Khám Da Khoa Vigor Health</p>
                                    <p class="text-muted small">100-102-102A-104-106-108 Trương Định, Phường 9, Quận 3, Tp. Hồ Chí Minh</p>

                                    <!-- Mã phiếu và giờ khám -->
                                    <div class="d-flex justify-content-between align-items-center my-3 px-3">
                                        <div class="text-start">
                                            <div class="fw-semibold">Mã phiếu</div>
                                            <div class="barcode text-uppercase">T2506302NFWI5</div>
                                        </div>
                                        <div class="text-end">
                                            <div class="fw-semibold">Giờ khám dự kiến</div>
                                            <div class="text-primary fs-5 fw-bold">10:00</div>
                                        </div>
                                    </div>

                                    <!-- Trạng thái đặt khám -->
                                    <button class="btn btn-warning w-100 mb-2 fw-bold">Đặt khám thành công</button>
                                    <p class="text-danger fw-bold">Số tiền phải thanh toán: 320.000 VNĐ</p>
                                    <p class="text-muted small">(Đã bao gồm phí khám + phí tiện ích)</p>

                                    <!-- Thông tin chi tiết -->
                                    <div class="text-start bg-light p-3 rounded-3">
                                        <p><strong>Chuyên khoa:</strong> Da liễu</p>
                                        <p><strong>Mã phiếu:</strong> T2506302NFWI5</p>
                                        <p><strong>Bác sĩ:</strong> Trần Nguyễn Uyên</p>
                                        <p><strong>Dịch vụ:</strong> Da liễu</p>
                                        <p><strong>Hình thức khám:</strong> Không có BHYT</p>
                                        <p><strong>Thời gian khám:</strong> 10:00 - 19/7/2025</p>
                                        <p><strong>Phí khám:</strong> 320.000 VNĐ</p>
                                        <p><strong>Bệnh nhân:</strong> HUY DU VĂN</p>
                                        <p><strong>Ngày sinh:</strong> 05/06/2022</p>
                                        <p><strong>Mã bệnh nhân:</strong> MP-2506300HPZSI</p>
                                    </div>

                                    <p class="text-start mt-3"><strong>Lưu ý:</strong></p>
                                </div>
                            </div>
                        </div>



                    </div>
                </div>





                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

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

    </body>

</html>
