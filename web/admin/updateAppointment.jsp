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
                        <div class="mb-4">
                            <h4 class="fw-bold text-primary">📝 Chỉnh sửa lịch hẹn</h4>
                        </div>

                        <c:set var="a" value="${appointmentView}" />

                        <form action="updateDoctorScheduleDetail?action=update" method="post">
                            <div class="card shadow border-0">
                                <div class="card-body p-4">
                                    <!-- Mã phiếu -->
                                    <div class="text-center mb-4">
                                        <h5 class="mb-0">Mã phiếu khám: 
                                            <span class="text-danger fw-bold">${a.appointment_code}</span>
                                        </h5>
                                    </div>

                                    <div class="row g-3">
                                        <!-- Tên bệnh nhân -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Tên bệnh nhân</label>
                                            <div class="form-control bg-light">${a.patientName}</div>
                                        </div>

                                        <!-- Chuyên khoa -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Chuyên khoa</label>
                                            <div class="form-control bg-light">${a.departmentName}</div>
                                        </div>

                                        <!-- Dịch vụ -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Dịch vụ</label>
                                            <div class="form-control bg-light">${a.serviceName}</div>
                                        </div>

                                        <!-- Ngày khám -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Ngày khám</label>
                                            <div class="form-control bg-light">${a.workingDate}</div>
                                        </div>

                                        <!-- Giờ khám -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Giờ khám dự kiến</label>
                                            <div class="form-control bg-light">${a.slotStart}</div>
                                        </div>

                                        <!-- Ghi chú -->
                                        <div class="col-md-6">
                                            <label class="form-label fw-semibold text-secondary">Ghi chú</label>
                                            <div class="form-control bg-light">${a.note}</div>
                                        </div>
                                    </div>

                                    <!-- Nút chức năng -->
                                    <div class="mt-5 d-flex justify-content-center gap-4">
                                        <button type="submit" class="btn btn-primary px-4 py-2">
                                            <i class="fa-solid fa-calendar-check me-1"></i> Dời lịch hẹn
                                        </button>

                                        <a href="appointmentManager?action=refund&code=${a.appointment_code}" 
                                           class="btn btn-danger px-4 py-2">
                                            <i class="fa-solid fa-money-bill-wave me-1"></i> Hoàn tiền
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>
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

    .card .card-body {
        padding: 1.5rem  1.5rem  0rem  1.5rem;
    }
</style>

