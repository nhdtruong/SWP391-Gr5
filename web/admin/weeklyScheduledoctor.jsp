
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

                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h5 class="mb-4">Lịch làm việc của bác sĩ: ${doctorName}</h5>
                            <a href="addDoctorWorkingDay?doctorId=${doctorId}&doctorName=${doctorName}" class="btn btn-info" style="margin-left: 20px">
                                + Thêm lịch làm việc
                            </a>
                        </div>
                        <div class="row mt-4">
                            <div class="col-12">

                                <div class="table-responsive">


                                    <c:set var="currentWeek" value="${weeklySchedule[weekIndex]}" />

                                    <div class="d-flex justify-content-center mb-3">
                                        <div>
                                            <c:if test="${weekIndex > 0}">
                                                <a href="?doctorId=${doctorId}&doctorName=${doctorName}&weekIndex=${weekIndex - 1}" class="btn btn-outline-primary me-2">← Tuần trước</a>
                                            </c:if>

                                            <c:if test="${weekIndex < weeklySchedule.size() - 1}">
                                                <a href="?doctorId=${doctorId}&doctorName=${doctorName}&weekIndex=${weekIndex + 1}" class="btn btn-outline-primary">Tuần tiếp →</a>
                                            </c:if>
                                        </div>
                                    </div>


                                    <div class="mb-5">
                                        <div class="table-responsive">
                                            <table class="table table-bordered text-center align-middle">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th>Ca</th>
                                                            <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <th style="width: calc(100% / 7);">
                                                                <fmt:formatDate value="${day.workingDate}" pattern="EEEE" /><br>
                                                                <fmt:formatDate value="${day.workingDate}" pattern="dd/MM/yyyy" />
                                                            </th>
                                                        </c:forEach>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <!-- Ca Sáng -->
                                                    <tr>
                                                        <td><strong>Ca sáng</strong></td>
                                                        <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <td>
                                                                <c:if test="${empty day.morningSlots}">
                                                                    <span class="text-muted">Trống</span>
                                                                </c:if>
                                                                <c:forEach var="slot" items="${day.morningSlots}">
                                                                    <div class="badge bg-success mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                                </c:forEach>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                    <!-- Ca Chiều -->
                                                    <tr>
                                                        <td><strong>Ca chiều</strong></td>
                                                        <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <td>
                                                                <c:if test="${empty day.afternoonSlots}">
                                                                    <span class="text-muted">Trống</span>
                                                                </c:if>
                                                                <c:forEach var="slot" items="${day.afternoonSlots}">
                                                                    <div class="badge bg-warning text-dark mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                                </c:forEach>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                    <!-- Ca Tối -->
                                                    <tr>
                                                        <td><strong>Ca tối</strong></td>
                                                        <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <td>
                                                                <c:if test="${empty day.eveningSlots}">
                                                                    <span class="text-muted">Trống</span>
                                                                </c:if>
                                                                <c:forEach var="slot" items="${day.eveningSlots}">
                                                                    <div class="badge bg-secondary mb-1">${slot.slotStart} - ${slot.slotEnd}</div>
                                                                </c:forEach>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                    <!-- Trạng thái -->
                                                    <tr>
                                                        <td><strong>Trạng thái</strong></td>
                                                        <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${day.status == 1}">
                                                                        <span class="badge bg-primary mb-2">Xuất bản</span>
                                                                    </c:when>
                                                                    <c:when test="${day.status == 0}">
                                                                        <span class="badge bg-warning text-dark mb-2">Bản nháp</span>
                                                                    </c:when>

                                                                </c:choose>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                    <!-- Hành động -->
                                                    <tr>
                                                        <td><strong>Hành động</strong></td>
                                                        <c:forEach var="day" items="${currentWeek}" begin="0" end="6">
                                                            <td>
                                                                <c:if test="${not empty day.slots}">
                                                                    <div class="d-flex justify-content-center gap-1 mt-2">
                                                                        <form action="updateDoctorScheduleDetail?action=update" method="get">
                                                                            <input type="hidden" name="doctorId" value="${doctorId}" />
                                                                            <input type="hidden" name="doctorName" value="${doctorName}" />
                                                                            <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                                            <button type="submit" class="btn btn-sm btn-info">Sửa</button>
                                                                        </form>
                                                                        <form action="updateDoctorScheduleDetail?action=delete" method="post"
                                                                              onsubmit="return confirm('Xóa lịch ngày này?');">
                                                                            <input type="hidden" name="doctorId" value="${doctorId}" />
                                                                            <input type="hidden" name="doctorName" value="${doctorName}" />
                                                                            <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                                            <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
                                                                        </form>
                                                                    </div>
                                                                </c:if>
                                                            </td>
                                                        </c:forEach>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>



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
