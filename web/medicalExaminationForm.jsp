<%-- 
    Document   : medicalExaminationForm
    Created on : 30 Jun 2025, 21:38:30
    Author     : DELL
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <div class="main-wrapper d-flex flex-column min-vh-100"> 

            <jsp:include page="layout/menu_white.jsp"/>

            <section class="bg-dashboard">
                <div class="container" style="width: 1250px">
                    <div class="row justify-content-center">
                        <div class="col-12" style="height: 80px;margin-right: -30px">
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0" style="margin-left: -30px">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>

                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Phiếu khám bệnh</a></li>

                                </ul>
                            </nav>
                        </div>
                        <jsp:include page="layout/profileMenu.jsp"/>

                        <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0" style="padding: 0 30px">
                            <div class="d-flex justify-content-between align-items-center pb-3">
                                <h4 class="mb-0">Danh sách phiếu khám bệnh</h4>
                            </div>
                            <div class="mb-3">
                                <div class="d-flex gap-3 flex-wrap" role="group" aria-label="Lọc phiếu khám">
                                    <a href="?filter=pending" class="btn ${filter == 'pending' ? 'btn-primary' : 'btn-outline-secondary'}">Chưa thanh toán</a>
                                    <a href="?filter=success" class="btn ${filter == 'success' ? 'btn-primary' : 'btn-outline-secondary'}">Đã thanh toán</a>
                                    <a href="?filter=done" class="btn ${filter == 'done' ? 'btn-primary' : 'btn-outline-secondary'}">Đã khám</a>
                                    <a href="?filter=canceled" class="btn ${filter == 'canceled' ? 'btn-primary' : 'btn-outline-secondary'}">Đã huỷ</a>
                                </div>
                            </div>

                            <c:if test="${not empty bills}">
                                <ul class="list-unstyled">
                                    <c:forEach items="${bills}" var="b">
                                        <li class="mb-4 appointment-item">
                                            <a href="billsDetail?appointment_code=${b.appointment_code}" class="appointment-link text-decoration-none text-dark">
                                                <div class="card shadow-sm">
                                                    <div class="card-body position-relative">
                                                        <!-- Header -->
                                                        <div class="d-flex justify-content-between align-items-start mb-3 pb-2 border-bottom" style="border-bottom: 2px dashed #ccc;">
                                                            <div>
                                                                <div class="mb-1">
                                                                    <span class="me-2 text-secondary">Mã phiếu:</span>
                                                                    <strong>${b.appointment_code}</strong>
                                                                </div>
                                                                <div>
                                                                    <h5>${b.patientName}</h5>
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <c:if test="${b.status == 1}">
                                                                    <span class="px-4 py-2" style="font-size: 1.1rem;border-radius: 10px; background-color: #02cd60;color:whitesmoke;font-weight: 500;">
                                                                        Đặt khám thành công
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${b.status == 0}">
                                                                    <span class="px-4 py-2" style="font-size: 1.1rem; border-radius: 10px; background-color: #d6d6d6; color: white; font-weight: 500; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                                                        Đã hủy
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${b.status == 3}">
                                                                    <span class="px-4 py-2" style="font-size: 1.1rem; border-radius: 10px; background-color: #dc3545; color: white; font-weight: 500;">
                                                                        Đang yêu cầu hủy
                                                                    </span>
                                                                </c:if>
                                                            </div>
                                                        </div>

                                                        <!-- Body -->
                                                        <ul class="list-unstyled mb-4" >
                                                            <li class="d-flex align-items-center margin5px">
                                                                <i class="fa-solid fa-hospital text-primary me-2 opacit"></i>
                                                                <h5 class="mb-0" style="color:#2698D6">Bệnh viện đại học FPT</h5>
<!--                                                                <img style="width: 280px; height: 280px;margin-left: 50px"  src="http://localhost:8080/doctris2/QRCodeServlet?data=http://localhost:8080/doctris2/billsDetail?appointment_code=${b.appointment_code}" alt="alt"/>-->
                                                            </li>
                                                            <c:if test="${not empty b.departmentName}">
                                                                <li class="d-flex align-items-center margin5px">
                                                                    <i class="fa-solid fa-stethoscope text-primary me-2 opacit"></i>

                                                                    <span class="me-2 text-secondary" style="min-width: 150px;">Chuyên khoa:</span>
                                                                    <span>${b.departmentName}</span>


                                                                </li>
                                                            </c:if>
                                                            <li class="d-flex align-items-center margin5px">
                                                                <i class="fa-solid fa-hand-holding-medical text-primary me-2 opacit"></i>
                                                                <span class="me-2 text-secondary" style="min-width: 150px;">Dịch vụ:</span>
                                                                <span>${b.serviceName}</span>
                                                            </li>
                                                            <li class="d-flex align-items-start margin5px">
                                                                <i class="fa-solid fa-calendar-days text-primary me-2 mt-1 opacit"></i>
                                                                <span class="me-2 text-secondary" style="min-width: 150px;">Ngày khám:</span>
                                                                <span style="color:#2698D6">
                                                                    <fmt:formatDate value="${b.dateBooking}" pattern="dd/ MM/ yyyy" />
                                                                </span>
                                                            </li>
                                                            <li class="d-flex align-items-center margin5px">
                                                                <i class="fa-solid fa-clock text-primary me-2 opacit"></i>
                                                                <span class="me-2 text-secondary" style="min-width: 150px;">Giờ khám dự kiến:</span>
                                                                <span style="color:#2698D6">
                                                                    <fmt:formatDate value="${b.slotStart}" pattern="HH:mm" />
                                                                </span>
                                                            </li>
                                                            <!--                                                            <li class="d-flex align-items-center margin5px">
                                                                                                                            <img style="width: 70px; height: 70px"  src="http://localhost:8080/doctris2/QRCodeServlet?data=http://localhost:8080/doctris2/billsDetail?appointment_code=${b.appointment_code}" alt="alt"/>
                                                                                                                        </li>-->

                                                            <div class="text-end" style="margin-top: -130px">
                                                                <img style="width: 150px; height: 150px;margin-left: 50px"  src="http://localhost:8080/doctris2/QRCodeServlet?data=http://localhost:8080/doctris2/billsDetail?appointment_code=${b.appointment_code}" alt="alt"/>
                                                            </div>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                    </c:forEach>
                                </ul>

                            </c:if>
                            <c:if test="${empty bills}">
                                <div class="alert alert-info text-center">
                                    <h5 class="mt-3 text-muted">Không tìm thấy</h5>
                                    <img src="assets/images/norecords/norecords.png" alt="Không có dữ liệu" style="width: 200px; height:200px;">
                                </div>
                            </c:if>
                        </div>
                    </div>
            </section>

            <div class="mt-auto">
                <jsp:include page="layout/footer.jsp"/>
            </div>

        </div> 

        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>




        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">


        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>



    </body>


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

</html>

