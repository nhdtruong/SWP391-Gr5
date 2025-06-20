
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
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


                        <div class="row mt-4">
                            <div class="col-12">
                                <h5 class="mb-3">Lịch làm việc của bác sĩ: ${doctorName}</h5>
                                <table class="table table-bordered table-striped text-center align-middle">
                                    <thead class="table-light">
                                        <tr>
                                            <th style="width: 10%;">Ngày làm việc</th>
                                            <th style="width: 25%;">Ca sáng</th>
                                            <th style="width: 25%;">Ca chiều</th>
                                            <th style="width: 10%;">Trạng thái</th>
                                            <th style="width: 10%;">Sửa</th>
                                            <th style="width: 10%;">Xóa</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="day" items="${WorkingDateSchedule}">
                                            <tr>
                                                <!-- Ngày làm việc -->
                                                <td>
                                                    <fmt:formatDate value="${day.workingDate}" pattern="dd / MM / yyyy"/>
                                                </td>

                                                <!-- Ca Sáng -->
                                                <td>
                                                    <c:forEach var="shift" items="${day.shifts}">

                                                        <c:if test="${shift.shift == 1}">
                                                            <c:forEach var="slot" items="${shift.slots}">
                                                                <span class="badge bg-success me-1 mb-1">
                                                                    ${slot.slotStart} - ${slot.slotEnd}
                                                                </span>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>

                                                <!-- Ca Chiều -->
                                                <td>
                                                    <c:forEach var="shift" items="${day.shifts}">

                                                        <c:if test="${shift.shift == 2}">
                                                            <c:forEach var="slot" items="${shift.slots}">
                                                                <span class="badge bg-warning text-dark me-1 mb-1">
                                                                    ${slot.slotStart} - ${slot.slotEnd}
                                                                </span>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>

                                                <!-- Trạng thái -->
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${day.status == 1}">
                                                            <span class="badge bg-primary">Xuất bản</span>
                                                        </c:when>
                                                        <c:when test="${day.status == 0}">
                                                            <span class="badge bg-secondary">Tạm ngưng</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span class="badge bg-dark">Không rõ</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>


                                                <!-- Nút sửa -->
                                                <td>
                                                    <form action="updateDoctorScheduleDetail?action=delete" method="get" ">
                                                        <input type="hidden" name="doctorId" value="${param.doctorId}" />
                                                        <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                        <button type="submit" class="btn btn-sm btn-danger">Sửa</button>
                                                    </form>
                                                </td>


                                                <!-- Nút xóa -->
                                                <td>
                                                    <form action="doctorschedule?action=delete" method="post" onsubmit="return confirm('Bạn có chắc muốn xóa lịch ngày này?');">
                                                        <input type="hidden" name="doctorId" value="${param.doctorId}" />
                                                        <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                        <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                        <!-- Modal sửa lịch -->
                        <div class="modal fade" id="editScheduleModal" tabindex="-1" aria-hidden="true">
                            <div class="modal-dialog modal-xl">
                                <div class="modal-content">
                                    <form action="updateDoctorSchedule?action=updateScheduleExcute" method="post">
                                        <input type="hidden" name="doctorId" value="${doctorId}">
                                        <input type="hidden" name="workingDate" id="editWorkingDateInput">

                                        <div class="modal-header">
                                            <h5 class="modal-title">Chỉnh sửa lịch làm việc ngày <span id="editWorkingDateText"></span></h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>

                                        <div class="modal-body">
                                            <div class="card mb-4 shadow-sm">
                                                <div class="card-header d-flex justify-content-between align-items-center bg-light">
                                                    <strong>Slot làm việc</strong>
                                                    <div>
                                                        <input type="checkbox" id="checkAll_modal" onchange="toggleAllSlotsInModal()">
                                                        <label for="checkAll_modal" class="ms-1">Chọn tất cả</label>
                                                    </div>
                                                </div>

                                                <div class="card-body">
                                                    <!-- Slot Sáng -->
                                                    <div><strong>Ca Sáng</strong></div>
                                                    <div class="row mb-3">
                                                        <c:forEach var="h" begin="7" end="10">
                                                            <c:set var="slotStart" value="${h lt 10 ? '0' : ''}${h}:00:00"/>
                                                            <c:set var="slotEnd" value="${(h + 1) lt 10 ? '0' : ''}${h + 1}:00:00"/>
                                                            <c:set var="isChecked" value="false"/>
                                                            <c:forEach var="slot" items="${editSlots}">
                                                                <c:if test="${slot.shift == 1 and slot.start == slotStart and slot.end == slotEnd}">
                                                                    <c:set var="isChecked" value="true"/>
                                                                </c:if>
                                                            </c:forEach>
                                                            <div class="col-md-3">
                                                                <div class="form-check">
                                                                    <input class="form-check-input slot-modal" type="checkbox"
                                                                           name="slots"
                                                                           value="1_${slotStart}-${slotEnd}"
                                                                           id="slot_m_${h}"
                                                                           <c:if test="${isChecked}">checked</c:if>>
                                                                    <label class="form-check-label" for="slot_m_${h}">
                                                                        ${h}:00 - ${h+1}:00
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>

                                                    <!-- Slot Chiều -->
                                                    <div><strong>Ca Chiều</strong></div>
                                                    <div class="row">
                                                        <c:forEach var="h" begin="13" end="16">
                                                            <c:set var="slotStart" value="${h lt 10 ? '0' : ''}${h}:00:00"/>
                                                            <c:set var="slotEnd" value="${(h + 1) lt 10 ? '0' : ''}${h + 1}:00:00"/>
                                                            <c:set var="isChecked" value="false"/>
                                                            <c:forEach var="slot" items="${editSlots}">
                                                                <c:if test="${slot.shift == 2 and slot.start == slotStart and slot.end == slotEnd}">
                                                                    <c:set var="isChecked" value="true"/>
                                                                </c:if>
                                                            </c:forEach>
                                                            <div class="col-md-3">
                                                                <div class="form-check">
                                                                    <input class="form-check-input slot-modal" type="checkbox"
                                                                           name="slots"
                                                                           value="2_${slotStart}-${slotEnd}"
                                                                           id="slot_a_${h}"
                                                                           <c:if test="${isChecked}">checked</c:if>>
                                                                    <label class="form-check-label" for="slot_a_${h}">
                                                                        ${h}:00 - ${h+1}:00
                                                                    </label>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary">Lưu</button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>





                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                                                            function openEditSchedule(doctorId, workingDate) {
                                                                document.getElementById('editWorkingDateInput').value = workingDate;
                                                                document.getElementById('editWorkingDateText').innerText = workingDate;
                                                                const modal = new bootstrap.Modal(document.getElementById('editScheduleModal'));
                                                                modal.show();
                                                            }

                                                            function toggleAllSlots(day) {
                                                                const checkAll = document.getElementById('checkAll_' + day);
                                                                const slots = document.querySelectorAll('.slot-' + day);
                                                                slots.forEach(slot => {
                                                                    slot.checked = checkAll.checked;
                                                                });
                                                            }

                                                            //   Khi click vào từng checkbox slot
                                                            function toggleAllSlotsInModal() {
                                                                const checkAll = document.getElementById('checkAll_modal').checked;
                                                                const slots = document.querySelectorAll('.slot-modal');
                                                                slots.forEach(slot => {
                                                                    slot.checked = checkAll;
                                                                });
                                                            }

                                                            // Tự động cập nhật checkbox "Chọn tất cả" nếu tất cả checkbox slot đều được chọn
                                                            document.addEventListener("DOMContentLoaded", () => {
                                                                const checkAll = document.getElementById('checkAll_modal');
                                                                const slots = document.querySelectorAll('.slot-modal');

                                                                slots.forEach(slot => {
                                                                    slot.addEventListener("change", () => {
                                                                        const allChecked = Array.from(slots).every(s => s.checked);
                                                                        checkAll.checked = allChecked;
                                                                    });
                                                                });
                                                            });
        </script>
 <style>
            /* Tông màu chủ đạo: xanh dương nhẹ và trắng */

            .card {
                border: 1px solid #cce5ff;
                border-radius: 12px;
                background-color: #ffffff;
                transition: box-shadow 0.3s;
            }

            .card:hover {
                box-shadow: 0 4px 12px rgba(0, 123, 255, 0.15);
            }

            .card-header {
                background-color: #e7f3ff !important;
                color: #004085;
                border-bottom: 1px solid #b8daff;
                font-size: 16px;
            }

            .form-check-label {
                cursor: pointer;
                font-weight: 500;
                color: #0056b3;
            }

            .form-check-input:checked {
                background-color: #007bff;
                border-color: #007bff;
            }

            .form-check-input:focus {
                box-shadow: 0 0 0 0.15rem rgba(0, 123, 255, 0.25);
            }

            button[type="submit"] {
                background-color: #007bff;
                border-color: #007bff;
                font-weight: bold;
                transition: background-color 0.3s ease;
            }

            button[type="submit"]:hover {
                background-color: #0056b3;
            }

            .slot-title {
                font-weight: 600;
                color: #0069d9;
                margin-bottom: 5px;
            }
        </style>

    </body>

</html>
