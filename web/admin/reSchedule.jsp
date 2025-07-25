<%-- 
    Document   : reSchedule
    Created on : 8 Jul 2025, 21:40:06
    Author     : DELL
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div class="layout-specing container">

                        <!-- Thông tin bệnh nhân và phiếu -->
                        <div class="row mb-4">
                            <div class="col-md-6 mb-2">
                                <h6>Dời lịch khám cho bệnh nhân: <strong>${patientName}</strong></h6>
                            </div>
                            <div class="col-md-6 mb-2">
                                <h6>Mã phiếu: <strong>${appointmentCode}</strong></h6>
                            </div>
                            <div class="col-md-6 mb-2">
                                <h6>Đang đặt ngày: <strong><fmt:formatDate value="${dateBooking}" pattern="dd/ MM/ yyyy"/> </strong></h6>
                            </div>
                            <div class="col-md-6 mb-2">
                                <h6>Bác sĩ: <strong>${currentDoctorName}</strong></h6>
                            </div>
                            <div class="col-md-6 mb-2">
                                <h6>Chuyên khoa: <strong>${departmentName}</strong></h6>
                            </div>
                        </div>

                        <form action="updateAppoitment" method="post" id="doctorForm">
                            <input type="hidden" name="currentDoctorName" value="${currentDoctorName}" />
                            <input type="hidden" name="patientName" value="${patientName}" />
                            <input type="hidden" name="appointmentId" value="${appointmentId}" />
                            <input type="hidden" name="appointmentCode" value="${appointmentCode}" />
                            <input type="hidden" name="dateBooking" value="${dateBooking}" />
                            <input type="hidden" name="slotStart" value="${slotStart}" />
                            <input type="hidden" name="slotEnd" value="${slotEnd}" />
                            <input type="hidden" name="slotId" value="${slotId}" />
                            <input type="hidden" name="departmentName" value="${departmentName}" />
                            <input type="hidden" name="action" value="reschedule" />

                            <div class="row mb-4">
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold">Chọn bác sĩ cùng chuyên khoa:</label>

                                    <select class="form-select" name="doctorId_reDoctorName" onchange="this.form.submit()">
                                        <c:forEach var="doc" items="${listDoctor}">
                                            <option value="${doc.doctor_id}_${doc.doctor_name}" ${doc.doctor_id == doctorId ? 'selected' : ''}>
                                                ${doc.doctor_name}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </form>
                        <div class="row justify-content-center">
                            <div class="col-12 col-lg-10">

                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="mb-0">Chọn lại lịch khám phù hợ dp:</h5>
                                    </div>

                                    <div class="card-body">



                                        <fmt:setLocale value="vi_VN" />
                                        <c:set var="slotId" value="${slotId}"/>
                                        <c:forEach var="i" begin="0" end="${fn:length(listWDS) - 1}" step="2">
                                            <div class="row mb-4">

                                                <c:if test="${i < fn:length(listWDS)}">
                                                    <c:set var="day1" value="${listWDS[i]}" />
                                                    <div class="col-md-6 border-end">
                                                        <h5>🗓️ <fmt:formatDate value="${day1.workingDate}" pattern="EEEE - dd/MM/yyyy" /></h5>


                                                        <c:if test="${not empty day1.morningSlots}">
                                                            <div><strong>Buổi sáng</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${day1.morningSlots}">
                                                                    <fmt:formatDate value="${day1.workingDate}" pattern="dd/MM/yyyy" var="formattedDate" />

                                                                    <button type="button"
                                                                            class="btn btn-sm
                                                                            <c:choose>
                                                                                <c:when test="${slot.slotId == slotId}">btn-success text-white disabled</c:when>
                                                                                <c:when test="${slot.status == 1}">btn-outline-info</c:when>
                                                                                <c:when test="${slot.status == 0}">btn-warning text-white disabled</c:when>
                                                                                <c:otherwise>btn-secondary text-white disabled</c:otherwise>
                                                                            </c:choose>"
                                                                            style="${slot.status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"
                                                                            onclick="showConfirmModal('${appointmentId}', '${slot.slotStart}', '${slot.slotEnd}', '${formattedDate}', '${fn:escapeXml(reDoctorName)}', '${doctorId}', '${slot.slotId}')">

                                                                        <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                        <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                    </button>


                                                                </c:forEach>
                                                            </div>
                                                        </c:if>


                                                        <c:if test="${not empty day1.afternoonSlots}">
                                                            <div><strong>Buổi chiều</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${day1.afternoonSlots}">
                                                                    <fmt:formatDate value="${day1.workingDate}" pattern="dd/MM/yyyy" var="formattedDate" />

                                                                    <button type="button"
                                                                            class="btn btn-sm
                                                                            <c:choose>
                                                                                <c:when test="${slot.slotId == slotId}">btn-success text-white disabled</c:when>
                                                                                <c:when test="${slot.slotId == slotId}">btn-success text-white</c:when>
                                                                                <c:when test="${slot.status == 1}">btn-outline-info</c:when>
                                                                                <c:when test="${slot.status == 0}">btn-warning text-white disabled</c:when>
                                                                                <c:otherwise>btn-secondary text-white disabled</c:otherwise>
                                                                            </c:choose>"
                                                                            style="${slot.status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"
                                                                            onclick="showConfirmModal('${appointmentId}', '${slot.slotStart}', '${slot.slotEnd}', '${formattedDate}', '${fn:escapeXml(reDoctorName)}', '${doctorId}', '${slot.slotId}')">

                                                                        <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                        <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                    </button>


                                                                </c:forEach>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </c:if>


                                                <c:if test="${i + 1 < fn:length(listWDS)}">
                                                    <c:set var="day2" value="${listWDS[i + 1]}" />
                                                    <div class="col-md-6">
                                                        <h5>🗓️ <fmt:formatDate value="${day2.workingDate}" pattern="EEEE - dd/MM/yyyy" /></h5>


                                                        <c:if test="${not empty day2.morningSlots}">
                                                            <div><strong>Buổi sáng</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${day2.morningSlots}">
                                                                    <fmt:formatDate value="${day2.workingDate}" pattern="dd/MM/yyyy" var="formattedDate" />

                                                                    <button type="button"
                                                                            class="btn btn-sm
                                                                            <c:choose>
                                                                                <c:when test="${slot.slotId == slotId}">btn-success text-white disabled</c:when>
                                                                                <c:when test="${slot.status == 1}">btn-outline-info</c:when>
                                                                                <c:when test="${slot.status == 0}">btn-warning text-white disabled</c:when>
                                                                                <c:otherwise>btn-secondary text-white disabled</c:otherwise>
                                                                            </c:choose>"
                                                                            style="${slot.status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"
                                                                            onclick="showConfirmModal('${appointmentId}', '${slot.slotStart}', '${slot.slotEnd}', '${formattedDate}', '${fn:escapeXml(reDoctorName)}', '${doctorId}', '${slot.slotId}')">

                                                                        <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                        <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                    </button>

                                                                </c:forEach>
                                                            </div>
                                                        </c:if>


                                                        <c:if test="${not empty day2.afternoonSlots}">
                                                            <div><strong>Buổi chiều</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${day2.afternoonSlots}">
                                                                    <fmt:formatDate value="${day2.workingDate}" pattern="dd/MM/yyyy" var="formattedDate" />

                                                                    <button type="button"
                                                                            class="btn btn-sm
                                                                            <c:choose>
                                                                                <c:when test="${slot.slotId == slotId}">btn-success text-white disabled</c:when>
                                                                                <c:when test="${slot.status == 1}">btn-outline-info</c:when>
                                                                                <c:when test="${slot.status == 0}">btn-warning text-white disabled</c:when>
                                                                                <c:otherwise>btn-secondary text-white disabled</c:otherwise>
                                                                            </c:choose>"
                                                                            style="${slot.status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"
                                                                            onclick="showConfirmModal('${appointmentId}', '${slot.slotStart}', '${slot.slotEnd}', '${formattedDate}', '${fn:escapeXml(reDoctorName)}', '${doctorId}', '${slot.slotId}')">

                                                                        <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                        <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                    </button>

                                                                </c:forEach>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </c:forEach>


                                        <div class="mb-4 d-flex flex-wrap justify-content-center align-items-center gap-3">
                                            <div class="d-flex align-items-center gap-2">
                                                <button type="button" class="btn btn-outline-info btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                <span style="font-size: 14px;">Còn lịch</span>
                                            </div>
                                            <div class="d-flex align-items-center gap-2">
                                                <button type="button" class="btn btn-success btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                <span style="font-size: 14px;">Đang chọn lịch này</span>
                                            </div>
                                            <div class="d-flex align-items-center gap-2">
                                                <button type="button" class="btn btn-warning btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                <span style="font-size: 14px;">Lịch khám đã đầy</span>
                                            </div>
                                            <div class="d-flex align-items-center gap-2">
                                                <button type="button" class="btn btn-secondary btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                <span style="font-size: 14px;">Quá gần hoặc giờ khám không còn hợp lệ</span>
                                            </div>
                                        </div>



                                    </div>

                                    <!-- Footer -->
                                    <div class="card-footer text-start">
                                        <a href="booking?stepName=service&doctorId=${sessionScope.doctorId}&doctorName=${sessionScope.doctorName}" class="btn btn-outline-secondary">
                                            <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                        </a>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

        <div class="modal fade" id="confirmModal" tabindex="-1">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content text-center">
                    <form action="updateAppoitment" method="post" id="confirmForm">
                        <div class="modal-header">
                            <h5 class="modal-title">Xác nhận đổi lịch</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body" id="confirmText">
                           
                        </div>

                        <input type="hidden" name="appointmentId" id="appointmentId" />
                        <input type="hidden" name="doctorId" id="inputDoctorId" />
                        <input type="hidden" name="slotId" id="inputSlotId" />
                        <input type="hidden" name="dateBooking" id="inputDateBooking" />
                        <input type="hidden" name="slotStart" id="inputSlotStart" />
                        <input type="hidden" name="slotEnd" id="inputSlotEnd" />
                        <input type="hidden" name="action" value="excuteUpdate" />
                        <div class="modal-footer justify-content-center">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Xác nhận</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>



        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <script>
                                                                                function showConfirmModal(appointmentId, slotStart, slotEnd, dateBooking, reDoctorName, doctorId, slotId) {

                                                                                    const text = "Bạn có chắc chắn muốn đổi lịch hẹn sang:<br>" +
                                                                                            "📅 <strong>" + dateBooking + "</strong><br>" +
                                                                                            "🕒 <strong>" + slotStart + " - " + slotEnd + "</strong><br>" +
                                                                                            "👨‍⚕️ Bác sĩ: <strong>" + reDoctorName + "</strong>";


                                                                                    document.getElementById('confirmText').innerHTML = text;


                                                                                    document.getElementById('appointmentId').value = appointmentId;
                                                                                    document.getElementById('inputDoctorId').value = doctorId;
                                                                                    document.getElementById('inputSlotId').value = slotId;
                                                                                    document.getElementById('inputDateBooking').value = dateBooking;
                                                                                    document.getElementById('inputSlotStart').value = slotStart;
                                                                                    document.getElementById('inputSlotEnd').value = slotEnd;


                                                                                    const modal = new bootstrap.Modal(document.getElementById('confirmModal'));
                                                                                    modal.show();
                                                                                }
        </script>




        <style>
            .d-none {
                display: none;
            }
            .doctor-list-scroll {
                max-height: 500px; /* hoặc chiều cao bạn muốn */
                overflow-y: auto;
                padding-right: 10px; /* tránh che mất nội dung bởi thanh cuộn */
                margin-bottom: 1rem;
            }
            .doctor-link {
                text-decoration: none;
                color: inherit;
                display: block;
            }

            .doctor-card {
                transition: border 0.3s, box-shadow 0.3s;
            }

            .doctor-link:hover .doctor-card {
                border: 10px solid #4da6ff; /* Viền xanh khi hover */
                box-shadow: 0 0 10px rgba(77, 166, 255, 0.3); /* Bóng xanh nhẹ */
                border-radius: 10px;
            }
            .card{
                border-radius: 8px;
                background: #fff;
                box-shadow: 0 4px 30px 0 rgba(116,157,206,.2);
                overflow: hidden;
                padding-bottom: 3px;
            }

            html, body {
                height: 100%;
                margin: 0;
            }

            .wrapper {
                display: flex;
                flex-direction: column;
                min-height: 100vh; /* Quan trọng để đẩy footer xuống */
            }

            .main-content {
                flex: 1;
                margin-top: 74px;
                background: #e8f4fd /* Chiếm hết chiều cao còn lại */
            }
            .card-header{
                background: linear-gradient(36deg,#00b5f1,#00e0ff);
            }
            .mb-0{
                color: white;
                text-align: center;
            }

            .table.no-border,
            .table.no-border th,
            .table.no-border td {
                border: none ;
            }
            .table>:not(caption)>*>* {
                padding: 1rem 1rem;
                color: var(--bs-table-color-state, var(--bs-table-color-type, var(--bs-table-color)));
                background-color: var(--bs-table-bg);
                border-bottom-width: var(--bs-border-width);
                box-shadow: inset 0 0 0 9999px var(--bs-table-bg-state, var(--bs-table-bg-type, var(--bs-table-accent-bg)));
                font-family: Roboto,sans-serif!important;
                font-size: medium;
            }

            tbody, td, tfoot, th, thead, tr {
                border-color:white;
                border-style: none;
                border-width: 0;

            }
            tbody > tr{
                border-top:  .5px solid #b2b2b2;

            }
            .card-body{
                font-family: Roboto,sans-serif!important;

            }
            p {
                margin-bottom: 0px;
            }
            .opacity{
                opacity: 0.5;
            }
            .dkn{
                background: linear-gradient(36deg,#00b5f1,#00e0ff);

            }
            .btn.btn-sm {
                padding: 9px 5px;
                font-weight: 600;
                font-family: Roboto,sans-serif!important;
                font-size: 15px;
                width: 160px;
            }
            .m{
                min-width: 25px;
                margin-bottom: 10px;
            }

        </style>

    </body>

</html>

