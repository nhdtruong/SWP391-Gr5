
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
                                            <th style="width: 24%;">Ca sáng</th>
                                            <th style="width: 24%;">Ca chiều</th>
                                            <th style="width: 24%;">Ca tối</th>
                                            <th style="width: 8%;">Trạng thái</th>
                                            <th style="width: 5%;">Sửa</th>
                                            <th style="width: 5%;">Xóa</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="day" items="${WorkingDateSchedule}">
                                            <tr>

                                                <td>
                                                    <fmt:formatDate value="${day.workingDate}" pattern="dd / MM / yyyy" />
                                                </td>

 
                                                <td>
                                                    <c:if test="${empty day.getMorningSlots()}">
                                                        <span class="text-muted">Trống</span>
                                                    </c:if>
                                                    <c:forEach var="slot" items="${day.getMorningSlots()}">
                                                        <span class="badge bg-success me-1 mb-1">
                                                            ${slot.slotStart} - ${slot.slotEnd}
                                                        </span>
                                                    </c:forEach>
                                                </td>

                                                <td>
                                                    <c:if test="${empty day.getAfternoonSlots()}">
                                                        <span class="text-muted">Trống</span>
                                                    </c:if>
                                                    <c:forEach var="slot" items="${day.getAfternoonSlots()}">
                                                        <span class="badge bg-warning text-dark me-1 mb-1">
                                                            ${slot.slotStart} - ${slot.slotEnd}
                                                        </span>
                                                    </c:forEach>
                                                </td>


                                                <td>
                                                    <c:if test="${empty day.getEveningSlots()}">
                                                        <span class="text-muted">Trống</span>
                                                    </c:if>
                                                    <c:forEach var="slot" items="${day.getEveningSlots()}">
                                                        <span class="badge bg-secondary text-light me-1 mb-1">
                                                            ${slot.slotStart} - ${slot.slotEnd}
                                                        </span>
                                                    </c:forEach>
                                                </td>

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

                                                <td>
                                                    <form action="updateDoctorScheduleDetail" method="get">
                                                        <input type="hidden" name="doctorId" value="${doctorId}" />
                                                        <input type="hidden" name="doctorName" value="${doctorName}" />
                                                        <input type="hidden" name="workingDate" value="${day.workingDate}" />
                                                        <button type="submit" class="btn btn-sm btn-danger">Sửa</button>
                                                    </form>
                                                </td>

                                                <!-- Nút xóa -->
                                                <td>
                                                    <form action="doctorschedule?action=delete" method="post"
                                                          onsubmit="return confirm('Bạn có chắc muốn xóa lịch ngày này?');">
                                                        <input type="hidden" name="doctorId" value="${doctorId}" />
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
