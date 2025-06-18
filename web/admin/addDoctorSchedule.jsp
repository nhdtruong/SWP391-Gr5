<%-- 
    Document   : addScheduleDoctor
    Created on : 16 Jun 2025, 22:15:50
    Author     : DELL
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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

                        <div class="row">
                            <div class="col-md-12 mb-4" style="text-align: center">
                                <label for="doctor" class="form-label ">Lập lịch cứng cho bác sĩ</label>

                            </div>


                            <div class="col-md-5 mb-4 row">

                                <div class="col-md-8">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="adddoctorschedule?action=serachdoctor" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="doctorUsername" id="s" placeholder="Nhập mã bác sĩ để tìm kiếm">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>



                            <c:if test="${not empty doctor}">
                                <div class="col-md-12 mb-4" style="text-align: center">
                                    <label for="doctor" class="form-label ">Lập lịch cứng cho bác sĩ : ${doctor.doctor_name}</label>

                                </div>

                            </c:if>

                            <c:if test="${empty doctor }">
                                <div class="col-md-12 mb-4" style="text-align: center">
                                    <label for="doctor" class="form-label ">Không thấy bác sĩ</label>

                                </div>

                            </c:if>




                        </div>
                        <form action="adddoctorschedule?action=addschedule" method="post">
                            <div class="row">
                                <c:if test="${not empty doctor }">
                                    <input type="hidden" name="doctorID" value="${doctor.doctor_id}">
                                </c:if>
                                <c:forEach var="i" begin="2" end="8" varStatus="loop">
                                    <div class="col-md-6">
                                        <div class="card mb-4 shadow-sm">
                                            <div class="card-header d-flex justify-content-between align-items-center bg-light">
                                                <strong>
                                                    <c:choose>
                                                        <c:when test="${i == 8}">Chủ nhật</c:when>
                                                        <c:otherwise>Thứ ${i}</c:otherwise>
                                                    </c:choose>
                                                </strong>
                                                <div>
                                                    <input type="checkbox" id="checkAll_${i}" onchange="toggleAllSlots(${i})">
                                                    <label for="checkAll_${i}" class="ms-1">Chọn tất cả</label>
                                                </div>
                                            </div>
                                            <div class="card-body">
                                                <div><strong>Slot Sáng</strong></div>
                                                <div class="row mb-2">

                                                    <c:forEach var="h" begin="7" end="10">
                                                        <div class="col-md-3">
                                                            <div class="form-check">
                                                                <input class="form-check-input slot-${i}" type="checkbox"
                                                                       name="day_${i}" value="1_${h}:00:00-${h+1}:00:00"
                                                                       id="slot_${i}_m_${h}">
                                                                <label class="form-check-label" for="slot_${i}_m_${h}">
                                                                    ${h}:00 - ${h+1}:00
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <div><strong>Slot Chiều</strong></div>
                                                <div class="row">
                                                    <c:forEach var="h" begin="13" end="16">
                                                        <div class="col-md-3">
                                                            <div class="form-check">
                                                                <input class="form-check-input slot-${i}" type="checkbox"
                                                                       name="day_${i}" value="2_${h}:00:00-${h+1}:00:00"
                                                                       id="slot_${i}_a_${h}">
                                                                <label class="form-check-label" for="slot_${i}_a_${h}">
                                                                    ${h}:00 - ${h+1}:00
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Mỗi 2 thẻ thì xuống dòng -->
                                    <c:if test="${loop.index % 2 == 1}">
                                    </div><div class="row">
                                    </c:if>
                                </c:forEach>
                            </div>

                            <div class="text-center mt-4">
                                <button type="submit" class="btn btn-primary px-4 py-2">Lưu lịch</button>
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
                                                        function toggleAllSlots(day) {
                                                            const checkAll = document.getElementById('checkAll_' + day);
                                                            const slots = document.querySelectorAll('.slot-' + day);
                                                            slots.forEach(slot => {
                                                                slot.checked = checkAll.checked;
                                                            });
                                                        }

                                                        //   Khi click vào từng checkbox slot
                                                        document.addEventListener("DOMContentLoaded", () => {
                                                            for (let day = 2; day <= 8; day++) {
                                                                const checkAll = document.getElementById('checkAll_' + day);
                                                                const slots = document.querySelectorAll('.slot-' + day);

                                                                slots.forEach(slot => {
                                                                    slot.addEventListener("change", () => {
                                                                        const allChecked = Array.from(slots).every(s => s.checked);
                                                                        checkAll.checked = allChecked;
                                                                    });
                                                                });
                                                            }
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
