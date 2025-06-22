<%-- 
    Document   : addWorkingDayDoctor
    Created on : 22 Jun 2025, 21:46:25
    Author     : DELL
--%>


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
                        <div style="margin-bottom: 30px"> <h4>Thêm lịch cho bác sĩ: ${doctorName}</h4></div>

                        <form action="addDoctorWorkingDay" method="post">
                            <div class="row">
                                <!-- Cột trái: Thông tin lịch -->
                                <div class="col-md-8">

                                    <input type="hidden" name="doctorId" value="${doctorId}">
                                    <input type="hidden" name="doctorName" value="${doctorName}">

                                    <div class="card mb-4 shadow-sm">
                                        <div class="card-header d-flex justify-content-between align-items-center bg-light">
                                            <strong>
                                                <input type="date" name="workingDate" class="form-control custom-date-input" />
                                            </strong>
                                           
                                            <span style="color: red; align-content: center;">${requestScope.errorD}</span>
                                            <div>
                                                <input type="checkbox" id="checkAll" onchange="toggleAllSlots()">
                                                <label for="checkAll" class="ms-1">Chọn tất cả slot Sáng và Chiều</label>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <!-- Slot Sáng -->
                                             <span style="color: red; align-content: center;">${requestScope.error}</span>
                                            <div><strong>Slot Sáng</strong></div>
                                            <div class="row mb-2">
                                                <c:forEach var="h" begin="7" end="10">
                                                        <div class="col-md-3">
                                                            <div class="form-check">
                                                                <input class="form-check-input slot" type="checkbox"
                                                                       name="day" value="${h}:00:00-${h+1}:00:00"
                                                                       id="slot_${i}_m_${h}">
                                                                <label class="form-check-label" for="slot_${i}_m_${h}">
                                                                    ${h}:00 - ${h+1}:00
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                            </div>

                                            <!-- Slot Chiều -->
                                            <div><strong>Slot Chiều</strong></div>
                                            <div class="row">
                                               <c:forEach var="h" begin="13" end="16">
                                                        <div class="col-md-3">
                                                            <div class="form-check">
                                                                <input class="form-check-input slot" type="checkbox"
                                                                       name="day" value="${h}:00:00-${h+1}:00:00"
                                                                       id="slot_${i}_a_${h}">
                                                                <label class="form-check-label" for="slot_${i}_a_${h}">
                                                                    ${h}:00 - ${h+1}:00
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                            </div>

                                            <!-- Slot Tối -->
                                            <div><strong>Slot Tối</strong></div>
                                            <div class="row">
                                                <c:forEach var="h" begin="18" end="21">
                                                        <div class="col-md-3">
                                                            <div class="form-check">
                                                                <input class="form-check-input " type="checkbox"
                                                                       name="day" value="${h}:00:00-${h+1}:00:00"
                                                                       id="slot_${i}_m_${h}">
                                                                <label class="form-check-label" for="slot_${i}_m_${h}">
                                                                    ${h}:00 - ${h+1}:00
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-4 mt-4">

                                    <div class="d-flex align-items-center mb-3 gap-2">
                                        <button type="submit" class="btn btn-primary px-4 py-2">Lưu dữ liệu</button>
                                        <a href="doctorScheduleDetail?doctorId=${doctorId}&doctorName=${doctorName}" class="btn btn-outline-secondary">Quay lại</a>
                                    </div>


                                    <label for="statusSelect"><strong>Trạng thái:</strong></label>
                                    <select name="status" id="statusSelect" class="form-select w-75">
                                        <option value="1">Xuất bản</option>
                                        <option value="0">Bản nháp</option>
                                    </select>
                                </div>
                            </div>
                        </form>


                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script>
                                                    const existingDates = [
            <c:forEach var="d" items="${listDate}" varStatus="loop">
                                                    '<fmt:formatDate value="${d}" pattern="yyyy-MM-dd" />'
                <c:if test="${!loop.last}">,</c:if>
            </c:forEach>
                                                    ];


                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        const dateInput = document.querySelector('input[name="workingDate"]');

                                                        dateInput.addEventListener("change", function () {
                                                            const selectedDate = dateInput.value;
                                                            if (existingDates.includes(selectedDate)) {
                                                                alert("Ngày này đã có lịch. Vui lòng chọn ngày khác.");
                                                                dateInput.value = ""; 
                                                            }
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
            .custom-date-input::-webkit-calendar-picker-indicator {
                filter: invert(0.5);
                cursor: pointer;
            }

            .custom-date-input {
                border-radius: 8px;
                border: 1px solid #ced4da;
                padding: 8px 12px;
                font-size: 16px;
                transition: border-color 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
            }

            .custom-date-input:focus {
                border-color: #86b7fe;
                box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
                outline: none;
            }
        </style>
        <script>

            function toggleAllSlots() {
                const checkAll = document.getElementById('checkAll');
                const slots = document.querySelectorAll('.slot');
                slots.forEach(slot => {
                    slot.checked = checkAll.checked;
                });
            }


            document.addEventListener("DOMContentLoaded", () => {

                const checkAll = document.getElementById('checkAll');
                const slots = document.querySelectorAll('.slot');

                slots.forEach(slot => {
                    slot.addEventListener("change", () => {
                        const allChecked = Array.from(slots).every(s => s.checked);
                        checkAll.checked = allChecked;
                    });
                });

            });
        </script>

    </body>

</html>
