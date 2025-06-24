<%-- 
    Document   : updateDoctorScheduleDetail
    Created on : 20 Jun 2025, 05:58:56
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
                        <div style="margin-bottom: 30px"> <h4>Sửa lịch cho bác sĩ: ${doctorName}</h4></div>

                        <c:set var="day" value="${WDS}"></c:set>
                            <form action="updateDoctorScheduleDetail?action=update" method="post">
                                <div class="row">
                                    <!-- Cột trái: Thông tin lịch -->
                                    <div class="col-md-8">

                                        <input type="hidden" name="doctorId" value="${doctorId}">
                                    <input type="hidden" name="doctorName" value="${doctorName}">

                                    <div class="card mb-4 shadow-sm">
                                        <div class="card-header d-flex justify-content-between align-items-center bg-light">
                                            <strong>
                                                <input name="workingDate" value="${day.workingDate}" type="hidden">
                                                Ngày làm: <fmt:formatDate value="${day.workingDate}" pattern="dd/ MM/ yyyy"/>
                                            </strong>
                                            <div>
                                                <input type="checkbox" id="checkAll" onchange="toggleAllSlots()">
                                                <label for="checkAll" class="ms-1">Chọn tất cả slot Sáng và Chiều</label>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <!-- Slot Sáng -->
                                            <div><strong>Slot Sáng</strong></div>
                                            <div class="row mb-2">
                                                <c:forEach var="h" begin="7" end="10">
                                                    <c:set var="slotStart" value="${h < 10 ? '0' : ''}${h}:00:00" />
                                                    <c:set var="slotEnd" value="${(h + 1) < 10 ? '0' : ''}${h + 1}:00:00" />
                                                    <c:set var="isChecked" value="false" />
                                                    <c:forEach var="slot" items="${day.slots}">
                                                        <c:if test="${slot.slotStart == slotStart and slot.slotEnd == slotEnd}">
                                                            <c:set var="isChecked" value="true" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <div class="col-md-3">
                                                        <div class="form-check">
                                                            <input class="form-check-input slot" type="checkbox"
                                                                   name="day" value="${slotStart}-${slotEnd}"
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
                                            <div><strong>Slot Chiều</strong></div>
                                            <div class="row">
                                                <c:forEach var="h" begin="13" end="16">
                                                    <c:set var="slotStart" value="${h}:00:00" />
                                                    <c:set var="slotEnd" value="${h+1}:00:00" />
                                                    <c:set var="isChecked" value="false" />
                                                    <c:forEach var="slot" items="${day.slots}">
                                                        <c:if test="${slot.slotStart == slotStart and slot.slotEnd == slotEnd}">
                                                            <c:set var="isChecked" value="true" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <div class="col-md-3">
                                                        <div class="form-check">
                                                            <input class="form-check-input slot" type="checkbox"
                                                                   name="day" value="${slotStart}-${slotEnd}"
                                                                   id="slot_a_${h}"
                                                                   <c:if test="${isChecked}">checked</c:if>>
                                                            <label class="form-check-label" for="slot_a_${h}">
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
                                                    <c:set var="slotStart" value="${h}:00:00" />
                                                    <c:set var="slotEnd" value="${h+1}:00:00" />
                                                    <c:set var="isChecked" value="false" />
                                                    <c:forEach var="slot" items="${day.slots}">
                                                        <c:if test="${slot.slotStart == slotStart and slot.slotEnd == slotEnd}">
                                                            <c:set var="isChecked" value="true" />
                                                        </c:if>
                                                    </c:forEach>

                                                    <div class="col-md-3">
                                                        <div class="form-check">
                                                            <input class="form-check-input slot1" type="checkbox"
                                                                   name="day" value="${slotStart}-${slotEnd}"
                                                                   id="slot_e_${h}"
                                                                   <c:if test="${isChecked}">checked</c:if>>
                                                            <label class="form-check-label" for="slot_e_${h}">
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
                                                    // Khi click vào checkbox "Chọn tất cả"
                                                    function toggleAllSlots() {
                                                        const checkAll = document.getElementById('checkAll');
                                                        const slots = document.querySelectorAll('.slot');
                                                        slots.forEach(slot => {
                                                            slot.checked = checkAll.checked;
                                                        });
                                                    }

                                                    //   Khi click vào từng checkbox slot
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
