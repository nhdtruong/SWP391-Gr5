

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>
    <body>

        <div class="wrapper">
            <jsp:include page="layout/menu_white.jsp"/>
            <div class="main-content" >
                <div class="container my-4" style="min-height: 600px">
                    <c:set value="" var="booking"/>
                    <div class="col-12" style="height: 80px;margin-right: -30px">
                        <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                            <ul class="breadcrumb bg-transparent mb-0" style="margin-left: -30px">
                                <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <c:if test="${sessionScope.token == 'chuyenkhoa'}">
                                        <c:if test="${truong==null}"  >
                                        <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Đặt khám chuyên khoa</a></li>
                                        </c:if>
                                        <c:set value="booking" var="booking"/>
                                    </c:if>
                                    <c:if test="${sessionScope.token == 'online'}">
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Hỗ trợ trực tuyến</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.token == 'packageService'}">
                                        <c:set value="booking.HealthPackage" var="booking"/>
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Gói khám sức khỏe</a></li>
                                    </c:if>

                                <c:if test="${stepName == 'doctor'}">
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Chọn bác sĩ</a></li>
                                    </c:if>
                                    <c:if test="${stepName == 'service'}">
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Chọn dịch vụ</a></li>
                                    </c:if>
                                    <c:if test="${stepName == 'dateTime'}">
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Chọn ngày khám</a></li>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                    <div class="row g-4">


                        <div class="col-12 col-lg-3">
                            <div class="card">
                                <div class="card-header">
                                    <h5 class="mb-0">Thông tin cơ sở y tế</h5>
                                </div>
                                <div class="card-body">
                                    <ul class="list-unstyled">
                                        <li class="mb-3 d-flex">
                                            <div class="me-2">
                                                <i class="fa-solid fa-hospital opacity"></i>
                                            </div>
                                            <div>
                                                <p>Bệnh Viện Đại Học FPT</p>
                                                <span class="mb-0 text-muted">78 Đ. Giải Phóng, Đống Đa, Tp. Hà Nội</span>
                                            </div>
                                        </li>

                                        <c:if test="${not empty sessionScope.departmentName}">
                                            <li class="d-flex">
                                                <div class="me-3">
                                                    <i class="fa-solid fa-notes-medical opacity"></i>
                                                </div>
                                                <div>
                                                    <p>Chuyên khoa: </p>
                                                    <span>${sessionScope.departmentName}</span>
                                                </div>
                                            </li>
                                        </c:if>

                                        <c:if test="${not empty sessionScope.doctorName}">
                                            <li class="d-flex">
                                                <div class="me-3">
                                                    <i class="fa-solid fa-user-doctor opacity"></i>
                                                </div>
                                                <div>
                                                    <p>Bác sĩ: </p>
                                                    <span>${sessionScope.doctorName}</span>
                                                </div>
                                            </li>
                                        </c:if>

                                        <c:if test="${not empty sessionScope.serviceBooking}">
                                            <li class="d-flex">
                                                <div class="me-3">
                                                    <i class="fa-solid fa-briefcase-medical opacity"></i>
                                                </div>
                                                <div>
                                                    <p>Dịch vụ: </p>
                                                    <span>${sessionScope.serviceBooking.service_name}</span>
                                                </div>
                                            </li>
                                        </c:if>

                                        <c:if test="${not empty sessionScope.dateTime}">
                                            <li class="d-flex">
                                                <div class="me-3">
                                                    <i class="fa-solid fa-clock opacity"></i>
                                                </div>
                                                <div>
                                                    <p>Thời gian: </p>
                                                    <span>${sessionScope.dateTime}</span>
                                                </div>
                                            </li>
                                        </c:if>
                                    </ul>

                                </div>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${stepName == 'doctor'}">
                                <div class="col-12 col-lg-9">
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0">Vui lòng chọn Bác sĩ</h5>
                                        </div>
                                        <div class="card-body">
                                            <div class="d-flex mb-3 flex-wrap">
                                                <input type="text" class="form-control me-2 mb-2" placeholder="Tìm nhanh bác sĩ" style="flex: 1 1 auto;">
                                                <button class="btn btn-outline-primary me-2 mb-2">Học hàm / học vị</button>
                                                <button class="btn btn-outline-primary me-2 mb-2">Chuyên khoa</button>
                                                <button class="btn btn-outline-primary mb-2">Giới tính</button>
                                            </div>


                                            <c:if test="${empty listDoctor}">
                                                <div class="text-center mt-5">
                                                    <h5 class="mt-3 text-muted">Không tìm thấy bác sĩ có lịch khám.</h5>
                                                    <img src="assets\images\norecords\norecords.png" alt="Không có dữ liệu" style="max-width: 300px; height: auto;">

                                                </div>
                                            </c:if>

                                            <div class="doctor-list-scroll">
                                                <c:forEach var="doctor" items="${listDoctor}">
                                                    <a href="booking?stepName=service&doctorId=${doctor.doctor_id}&doctorName=${doctor.doctor_name}&departmentName=${sessionScope.departmentName}&departmentId=${sessionScope.departmentId}" class="doctor-link">
                                                        <div class="border rounded p-3 mb-3 shadow-sm doctor-card">
                                                            <div class="fw-bold text-warning">

                                                                <i class="fas fa-user-md m"> </i> 
                                                                <c:if test="${doctor.getAcademicTitle().getName().isEmpty() && doctor.getAcademicDegree().getName().isEmpty()}">
                                                                    ${doctor.getPosition().getName()}.${doctor.getDoctor_name()}

                                                                </c:if>
                                                                <c:if test="${doctor.getAcademicTitle().getName().isEmpty() && !doctor.getAcademicDegree().getName().isEmpty()}">
                                                                    ${doctor.getAcademicDegree().getName()}.Bác sĩ.${doctor.getDoctor_name()}

                                                                </c:if>
                                                                <c:if test="${!doctor.getAcademicTitle().getName().isEmpty() && !doctor.getAcademicDegree().getName().isEmpty()}">
                                                                    ${doctor.getAcademicTitle().getName()}.${doctor.getAcademicDegree().getName()}.${doctor.getDoctor_name()}

                                                                </c:if>
                                                                <c:if test="${!doctor.getAcademicTitle().getName().isEmpty() && doctor.getAcademicDegree().getName().isEmpty()}">
                                                                    ${doctor.getAcademicTitle().getName()}.${doctor.getDoctor_name()} 

                                                                </c:if> 
                                                            </div>
                                                            <div class="text-muted mt-1">
                                                                <i class="fas fa-venus-mars m"></i> Giới tính: ${doctor.gender}
                                                            </div>
                                                            <div class="text-muted">
                                                                <i class="fas fa-hospital-symbol m" ></i> Chuyên khoa: ${doctor.department.department_name}
                                                            </div>
                                                            <div class="text-muted">
                                                                <i class="fas fa-calendar-alt m"></i> Lịch khám: Thứ 
                                                                <c:forEach var="day" items="${doctor.workingWeekdays}" varStatus="loop">
                                                                    <c:choose>
                                                                        <c:when test="${day == 7}">
                                                                            CN
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            ${day + 1}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                    <c:if test="${!loop.last}">,</c:if>
                                                                </c:forEach>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </c:forEach>
                                            </div>




                                            <div class="card-footer text-start">
                                                <a href="specialty" class="btn btn-outline-secondary">
                                                    <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${stepName == 'service'}">
                                    <div class="col-12 col-lg-9">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5 class="mb-0">Vui lòng chọn dịch vụ</h5>
                                            </div>
                                            <div class="card-body">
                                                <table class="table  align-middle">
                                                    <thead class="">
                                                        <tr>
                                                            <th>#</th>
                                                            <th style="width: 53%">Tên dịch vụ</th>
                                                            <th style="width: 17%">Giá tiền</th>
                                                            <th style="width: 30%">Phương thức thanh toán</th>
                                                            <th style=""></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <c:forEach var="s" items="${listService}" varStatus="loop">
                                                            <tr>
                                                                <td><strong>${loop.index +1 }</strong></td>
                                                                <td>
                                                                    <b>${s.service_name}</b>
                                                                    ${s.description}
                                                                </td>

                                                                <td><fmt:formatNumber value="${s.fee}" type="number" pattern="#,##0"/> đ</td>
                                                                <td>
                                                                    <c:if test="${s.payment_type_id == 2}">Thanh toán online</c:if>
                                                                    <c:if test="${s.payment_type_id == 1}">Thanh toán tại bệnh viện</c:if>
                                                                    </td>
                                                                    <td>
                                                                        <button 
                                                                            type="button"
                                                                            class="btn btn-primary btn-sm dkn"
                                                                            data-service-id="${s.service_id}"
                                                                        data-has-bhyt="${s.is_bhyt}">
                                                                        Đặt khám ngay 
                                                                    </button>
                                                                </td>
                                                            </tr>

                                                            <tr id="bhyt-row-${s.service_id}" class="bhyt-row d-none">
                                                                <td></td>
                                                                <td colspan="4">
                                                                    <div class="alert alert-info d-flex justify-content-between align-items-center">
                                                                        <strong>Dịch vụ có áp dụng BHYT. Bạn có đăng ký khám BHYT?</strong>
                                                                        <div>
                                                                            <a href="booking?stepName=dateTime&doctorId=${sessionScope.doctorId}&serviceId=${s.service_id}&isBHYT=0"
                                                                               class="btn btn-outline-secondary me-2">
                                                                                Không
                                                                            </a>

                                                                            <a href="booking?stepName=dateTime&doctorId=${sessionScope.doctorId}&serviceId=${s.service_id}&isBHYT=1"
                                                                               class="btn btn-primary">
                                                                                Có
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>    

                                                    </tbody>
                                                </table>
                                            </div>


                                            <div class="card-footer text-start">
                                                <c:if test="${truong==null}">
                                                    <c:if test="${sessionScope.token == 'chuyenkhoa'}">
                                                        <a href="booking?stepName=doctor&departmentId=${sessionScope.departmentId}&departmentName=${sessionScope.departmentName}" class="btn btn-outline-secondary">
                                                            <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${sessionScope.token == 'online'}">
                                                        <a href="callVideoWithDoctor?action=all&categoryService_id=${categoryService_id}" class="btn btn-outline-secondary">
                                                            <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${sessionScope.token == 'packageService'}">
                                                        <a href="healthPackageService?action=all&categoryService_id=${categoryService_id}" class="btn btn-outline-secondary">
                                                            <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                        </a>
                                                    </c:if>
                                                </c:if>
                                                <c:if test="${truong!=null}">
                                                    <a href="doctor?action=all" class="btn btn-outline-secondary">
                                                        <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                    </a>
                                                </c:if>

                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${stepName == 'dateTime'}">
                                    <div class="col-12 col-lg-9">
                                        <div class="card">
                                            <div class="card-header">
                                                <h5 class="mb-0">Vui lòng chọn ngày khám, giờ khám</h5>
                                            </div>
                                            <div class="card-body">



                                                <label for="dateSelect" style="margin-bottom: 15px"><strong>Chọn ngày khám:</strong></label>

                                                <fmt:setLocale value="vi_VN" />

                                                <select id="dateSelect" class="form-select mb-3" onchange="showSlotsByDate(this.value)">
                                                    <c:forEach var="wds" items="${listWDS}" varStatus="i">

                                                        <option value="date${i.index}">

                                                            <fmt:formatDate value="${wds.workingDate}" pattern="EEEE - dd/ MM/ yyyy" />
                                                        </option>
                                                    </c:forEach>
                                                </select>



                                                <c:forEach var="wds" items="${listWDS}" varStatus="i">
                                                    <div id="date${i.index}" class="slot-group" style="${i.index == 0 ? '' : 'display:none'}">

                                                        <!-- Buổi sáng -->
                                                        <c:if test="${not empty wds.getMorningSlots()}">
                                                            <div style="margin-bottom: 15px"><strong>Buổi sáng</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${wds.getMorningSlots()}">
                                                                    <c:set var="status" value="${slot.status}" />
                                                                    <form action="${booking}" method="get" class="me-2 mb-2 d-inline-block">
                                                                        <input type="hidden" name="stepName" value="chooseRecords" />
                                                                        <input type="hidden" name="dateBooking" value="${wds.workingDate}" />
                                                                        <input type="hidden" name="slotId" value="${slot.slotId}" />
                                                                        <input type="hidden" name="slotStart" value="${slot.slotStart}" />
                                                                        <input type="hidden" name="slotEnd" value="${slot.slotEnd}" />
                                                                        <button 
                                                                            type="submit"
                                                                            class="btn btn-sm
                                                                            ${status == 1 ? 'btn-outline-info' :
                                                                              status == 0 ? 'btn-warning text-white disabled' :
                                                                              'btn-secondary text-white disabled'}"
                                                                            style="${status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"

                                                                            >
                                                                            <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                            <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                        </button>
                                                                    </form>
                                                                </c:forEach>
                                                            </div>
                                                        </c:if>

                                                        <!-- Buổi chiều -->
                                                        <c:if test="${not empty wds.getAfternoonSlots()}">
                                                            <div style="margin-bottom: 15px"><strong>Buổi chiều</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${wds.getAfternoonSlots()}">
                                                                    <c:set var="status" value="${slot.status}" />
                                                                    <form action="${booking}" method="get" class="me-2 mb-2 d-inline-block">
                                                                        <input type="hidden" name="stepName" value="chooseRecords" />
                                                                        <input type="hidden" name="dateBooking" value="${wds.workingDate}" />
                                                                        <input type="hidden" name="slotId" value="${slot.slotId}" />
                                                                        <input type="hidden" name="slotStart" value="${slot.slotStart}" />
                                                                        <input type="hidden" name="slotEnd" value="${slot.slotEnd}" />
                                                                        <button 
                                                                            type="submit"
                                                                            class="btn btn-sm
                                                                            ${status == 1 ? 'btn-outline-info' :
                                                                              status == 0 ? 'btn-warning text-white disabled' :
                                                                              'btn-secondary text-white disabled'}"
                                                                            style="${status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"

                                                                            >
                                                                            <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                            <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                        </button>
                                                                    </form>
                                                                </c:forEach>
                                                            </div>
                                                        </c:if>

                                                        <!-- Buổi tối -->
                                                        <c:if test="${not empty wds.getEveningSlots()}">
                                                            <div style="margin-bottom: 15px"><strong>Buổi tối</strong></div>
                                                            <div class="d-flex flex-wrap gap-2 mb-2">
                                                                <c:forEach var="slot" items="${wds.getEveningSlots()}">
                                                                    <c:set var="status" value="${slot.status}" />
                                                                    <form action="${booking}" method="get" class="me-2 mb-2 d-inline-block">
                                                                        <input type="hidden" name="stepName" value="chooseRecords" />
                                                                        <input type="hidden" name="dateBooking" value="${wds.workingDate}" />
                                                                        <input type="hidden" name="slotId" value="${slot.slotId}" />
                                                                        <input type="hidden" name="slotStart" value="${slot.slotStart}" />
                                                                        <input type="hidden" name="slotEnd" value="${slot.slotEnd}" />
                                                                        <button 
                                                                            type="submit"
                                                                            class="btn btn-sm
                                                                            ${status == 1 ? 'btn-outline-info' :
                                                                              status == 0 ? 'btn-warning text-white disabled' :
                                                                              'btn-secondary text-white disabled'}"
                                                                            style="${status != 1 ? 'pointer-events: none; opacity: 0.6;' : ''}"

                                                                            >
                                                                            <fmt:formatDate value="${slot.slotStart}" pattern="HH:mm" /> -
                                                                            <fmt:formatDate value="${slot.slotEnd}" pattern="HH:mm" />
                                                                        </button>
                                                                    </form>
                                                                </c:forEach>
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
                                                        <button type="button" class="btn btn-warning btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                        <span style="font-size: 14px;">Lịch khám đã đầy</span>
                                                    </div>
                                                    <div class="d-flex align-items-center gap-2">
                                                        <button type="button" class="btn btn-secondary btn-sm px-2 rounded-circle" style="width: 24px; height: 24px;" disabled></button>
                                                        <span style="font-size: 14px;">Quá gần hoặc giờ khám không còn hợp lệ</span>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="card-footer text-start">
                                                <a href="booking?stepName=service&doctorId=${sessionScope.doctorId}&doctorName=${sessionScope.doctorName}" class="btn btn-outline-secondary">
                                                    <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                                </a>
                                            </div>
                                        </div>
                                    </div>

                                </c:when>

                            </c:choose>




                        </div>
                    </div>
                </div>

                <jsp:include page="layout/footer.jsp"/>

            </div>

            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">


            <script>
                function showSlotsByDate(id) {
                    const allGroups = document.querySelectorAll('.slot-group');
                    allGroups.forEach(div => div.style.display = 'none');
                    document.getElementById(id).style.display = 'block';
                }


                function checkSlotTime(dateStr, slotStartStr) {
                    const now = new Date();
                    const slotDate = new Date(dateStr);
                    const timeParts = slotStartStr.split(":");
                    slotDate.setHours(parseInt(timeParts[0]));
                    slotDate.setMinutes(parseInt(timeParts[1]));
                    slotDate.setSeconds(0);

                    const diffMinutes = (slotDate - now) / (1000 * 60);

                    if (diffMinutes < 120) {
                        alert("Bạn cần đặt lịch trước ít nhất 2 tiếng so với giờ bắt đầu!");
                        return false;
                    }

                    return true;
                }


                document.addEventListener("DOMContentLoaded", function () {
                    document.querySelectorAll(".dkn").forEach(function (btn) {
                        btn.addEventListener("click", function () {
                            const serviceId = this.getAttribute("data-service-id");
                            const hasBH = this.getAttribute("data-has-bhyt") === "true";
                            const token = '${sessionScope.token}';
                            document.querySelectorAll(".bhyt-row").forEach(row => row.classList.add("d-none"));

                            if (hasBH) {
                                document.getElementById("bhyt-row-" + serviceId).classList.remove("d-none");
                            } else {
                                if (token === 'online') {
                                    window.location.href = "booking.VideoCall?stepName=chooseRecords&doctorId=${sessionScope.doctorId}&serviceId=" + serviceId + "&isBHYT=0";
                                } else if (token === 'chuyenkhoa') {
                                    window.location.href = "booking?stepName=dateTime&doctorId=${sessionScope.doctorId}&serviceId=" + serviceId + "&isBHYT=0";
                                } else if (token === 'packageService') {
                                    window.location.href = "booking.HealthPackage?stepName=dateTime&serviceId=" + serviceId + "&isBHYT=0";
                                }


                            }
                        });
                    });
                });

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