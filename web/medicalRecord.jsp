<%-- 
    Document   : medicalRecord
    Created on : 7 Jul 2025, 23:39:56
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-dashboard">
            <div class="con" style="margin-right: 150px;margin-left: 150px;padding-top: 10px">
                <div class="row">
                    <!-- Bên trái: Menu -->
                    <div class="col-md-3 mb-4">
                        <div class="rounded shadow overflow-hidden sticky-bar">
                            <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                <br><br><br><br>

                                <c:if test="${sessionScope.user.getImg() != 'default'}">
                                    <img src="${sessionScope.user.getImg()}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                </c:if>
                                <c:if test="${sessionScope.user.getImg() == 'default'}">
                                    <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                </c:if>

                                <h5 class="mt-3 mb-1">${sessionScope.user.getName()}</h5>
                                <p class="text-muted mb-0">${sessionScope.user.getUsername()}</p>
                            </div>

                            <ul class="list-unstyled sidebar-nav mb-0">
                                <li class="navbar-item">
                                    <a href="profile" class="navbar-link">
                                        <i class="ri-airplay-line align-middle navbar-icon"></i> Dashboard
                                    </a>
                                </li>

                                <c:if test="${sessionScope.user.getRole() == 4}">
                                    <li class="navbar-item">
                                        <a href="myFeedback?action=myfeedback" class="navbar-link">
                                            <i class="ri-chat-1-line align-middle navbar-icon"></i> Phản hồi
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myPatient?action=mypatient" class="navbar-link">
                                            <i class="ri-empathize-line align-middle navbar-icon"></i> Bệnh nhân của tôi
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myAppointment?action=all" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Lịch hẹn
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="myWorkingSchedule" class="navbar-link">
                                            <i class="ri-empathize-line align-middle navbar-icon"></i> Lịch làm việc
                                        </a>
                                    </li>
                                </c:if>

                                <c:if test="${sessionScope.user.getRole() == 5}">
                                    <li class="navbar-item">
                                        <a href="records" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Hồ sơ khám bệnh
                                        </a>
                                    </li>
                                    <li class="navbar-item">
                                        <a href="bills" class="navbar-link">
                                            <i class="ri-calendar-check-line align-middle navbar-icon"></i> Phiếu khám bệnh
                                        </a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>


                    <div class="col-md-9">


                        <div class="rounded shadow mt-4">

                            <div class="layout-specing" style="">

                                <h3 class="mb-4">🩺 Khám bệnh cho bệnh nhân</h3>

                                <form action="examination" method="post">
                                    <!-- 1. Thông tin bệnh nhân -->
                                    <div class="card mb-4">
                                        <div class="card-header bg-info text-white fw-bold">Thông tin bệnh nhân</div>
                                        <div class="card-body row">
                                            <div class="col-md-6">👤 Họ tên: <strong>${patient.patientName}</strong></div>
                                            <div class="col-md-6">🎂 Ngày sinh: <strong><fmt:formatDate value="${patient.dob}" pattern="dd/MM/yyyy"/></strong></div>
                                            <div class="col-md-6">📧 Email: <strong>${patient.phone}</strong></div>
                                            <div class="col-md-6">📍 Địa chỉ: <strong>${patient.address}</strong></div>
                                            <input type="hidden" name="patientId" value="${patient.patientId}" />
                                            <input type="hidden" name="doctorId" value="${doctorId}" />
                                        </div>
                                    </div>

                                    <!-- 2. Nội dung khám -->
                                    <div class="card mb-4">
                                        <div class="card-header bg-primary text-white fw-bold">Nội dung khám</div>
                                        <div class="card-body">
                                            <div class="mb-3">
                                                <label>Triệu chứng</label>
                                                <textarea class="form-control" name="symptoms" rows="2" placeholder="Nhập triệu chứng bệnh nhân..."></textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label>Chẩn đoán</label>
                                                <textarea class="form-control" name="diagnosis" rows="2" placeholder="Nhập chẩn đoán..."></textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label>Kết luận</label>
                                                <textarea class="form-control" name="conclusion" rows="2" placeholder="Nhập kết luận khám..."></textarea>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="card mb-4">
                                        <div class="card-header bg-success text-white fw-bold">
                                            💊 Kê đơn thuốc
                                        </div>
                                        <div class="card-body">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#medicineModal">
                                                ➕ Thêm thuốc
                                            </button>

                                            <div id="selectedMedicineList">
                                                <p class="text-muted">Chưa có thuốc nào được chọn.</p>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- 4. Hướng dẫn dùng thuốc -->
                                    <div class="card mb-4">
                                        <div class="card-header bg-warning fw-bold">Hướng dẫn dùng thuốc</div>
                                        <div class="card-body">
                                            <div class="mb-3">
                                                <label>Hướng dẫn dùng thuốc (chung)</label>
                                                <textarea class="form-control" name="instruction" rows="2" placeholder="Ví dụ: Uống sau ăn, ngày 3 lần..."></textarea>
                                            </div>
                                            <div class="mb-3">
                                                <label>Ghi chú thêm (nếu có)</label>
                                                <textarea class="form-control" name="note" rows="2" placeholder="Ví dụ: Tái khám sau 7 ngày..."></textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal fade" id="medicineModal" tabindex="-1" aria-labelledby="medicineModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title fw-bold">📦 Danh sách thuốc</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <table class="table table-bordered table-hover">
                                                        <thead class="table-success">
                                                            <tr>
                                                                <th>STT</th>
                                                                <th>Tên thuốc</th>
                                                                <th>Hình ảnh</th>
                                                                <th>Đơn vị</th>
                                                                <th>Công dụng</th>
                                                                <th>Chọn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="m" items="${medicineList}" varStatus="loop">
                                                                <tr class="medicine-row">
                                                                    <td>${loop.index + 1}</td>
                                                                    <td>${m.medicineName}</td>
                                                                    <td><img src="${m.image}" width="40" style="border-radius: 4px;"></td>
                                                                    <td>${m.unit}</td>
                                                                    <td>${m.usage}</td>
                                                                    <td><input 
                                                                            type="checkbox" 
                                                                            class="form-check-input medicine-checkbox"
                                                                            data-id="${m.medicineId}"></td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                                                    <button type="button" class="btn btn-success" onclick="confirmSelection()">OK</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                    <div class="d-flex justify-content-end">
                                        <button type="submit" class="btn btn-primary px-4 fw-bold">
                                            💾 Lưu bệnh án
                                        </button>
                                    </div>

                                </form>

                            </div>



                        </div>

                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>

        <jsp:include page="layout/search.jsp"/>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>




        <script>
                                                        function confirmSelection() {
                                                            const checkboxes = document.querySelectorAll('.medicine-checkbox:checked');
                                                            const selectedContainer = document.getElementById('selectedMedicineList');
                                                            if (checkboxes.length === 0) {
                                                                selectedContainer.innerHTML = "<p class='text-muted'>Chưa có thuốc nào được chọn.</p>";
                                                                return;
                                                            }

                                                            let html = "<table class='table table-bordered w-100'>";
                                                            let hiddenInputs = "";
                                                            checkboxes.forEach(function (cb, index) {
                                                                const row = cb.closest('tr');
                                                                const image = row.cells[2].querySelector('img')?.getAttribute('src') || 'default.png'; // lấy ảnh từ <td> đầu tiên
                                                                const name = row.cells[1].innerText.trim();
                                                                const usage = row.cells[4].innerText.trim();
                                                                const medicineId = cb.getAttribute('data-id');

                                                                html += "<tr style='border-bottom: 1px solid #dee2e6;'>"; // đường kẻ ngăn cách
                                                                html += "<td class='py-2'>";
                                                                html += "<img src='" + image + "' alt='Ảnh thuốc' width='40' height='40' class='me-2 rounded-circle' />";
                                                                html += "<span class='ms-2 fw-semibold'>" + name + "</span>";
                                                                html += "</td>";
                                                                html += "<td class='py-2 text-muted'>" + usage + "</td>";
                                                                html += "</tr>";

                                                                // Hidden input để submit về server
                                                                hiddenInputs += "<input type='hidden' name='medicineIds' value='" + medicineId + "' />";
                                                            });
                                                            html += "</table>";
                                                            // Hiển thị danh sách thuốc
                                                            selectedContainer.innerHTML = html + hiddenInputs;
                                                            // Đóng modal
                                                            const modalEl = document.getElementById('medicineModal');
                                                            const modal = bootstrap.Modal.getOrCreateInstance(modalEl);
                                                            modal.hide();
                                                        }
        </script>









        <style>
            #selectedMedicineList {
                min-height: 100px;
                margin-top: 10px;

                padding: 10px;
                background-color: #fff;
                color: #000;
            }

        </style>

    </body>

</html>


