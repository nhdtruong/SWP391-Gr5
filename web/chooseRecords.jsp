<%-- 
    Document   : chooseRecords
    Created on : 22 Jun 2025, 02:05:58
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

        <main>
            <section class="section">
                <div class="container">
                    <div class="row mt-5 justify-content-center">
                        <div class="col-12">
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1;">Chọn hồ sơ bệnh nhân</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="row align-items-center" style="margin: 0 20%">

                        <div class="styles_choosePatient__kabkW">
                            <div class="ant-row">
                                <h1 class="styles_Title__lrJjf"
                                    style="text-align: center;
                                    background: linear-gradient(83.63deg,#00b5f1 33.34%,#00e0ff 113.91%);
                                    -webkit-background-clip: text;
                                    -webkit-text-fill-color: transparent;
                                    font-weight: 700;margin-bottom: 20px">
                                    <span>Chọn hồ sơ bệnh nhân</span>
                                </h1>
                                <div class="ant-col ant-col-24">
                                    <div style="min-height: 300px; opacity: 1;">
                                        <c:if test="${empty records}">
                                            <div class="text-center mt-5">
                                                <h5 class="mt-3 text-muted">Bạn chưa có hồ sơ bệnh nhân. Vui lòng tạo mới hồ sơ để được đặt khám.</h5>
                                                <img src="assets\images\norecords\norecords.png" alt="Không có dữ liệu" style="max-width: 100%; height: auto;">

                                            </div>
                                        </c:if>
                                        <c:if test="${not empty records}">
                                            <ul class="list-unstyled">
                                                <c:set value="${patientId}" var="pId"/>
                                                <c:forEach items="${records}" var="r">

                                                    <li class="mb-4">

                                                        <div class="card shadow-sm">
                                                            <c:if test="${not empty error && r.patientId == pId }"> <b style="display: inline-block;text-align: center;color: red">${requestScope.error}</b><br>
                                                                <span style="margin-left: 15px">Vui lòng "Cập nhật" thông tin để được áp dụng mức phí hỗ trợ theo quy định.</span><br>
                                                                <span style="margin-left: 15px">Nếu bạn không có BHYT vui lòng chọn "Tôi không có thẻ BHYT" để tiếp tục.</span>
                                                                
                                                            </c:if>
                                                            <div class="card-body">

                                                                <ul class="list-unstyled mb-4">

                                                                    <li class="d-flex align-items-center margin5px">
                                                                        <i class="fa-solid fa-user text-info me-2" style="opacity: 0.5;min-width: 20px"></i>
                                                                        <span class="me-2 text-secondary" style="min-width: 100px;">Họ và tên:</span>
                                                                        <strong class="text-primary">${r.getPatientName()}</strong>
                                                                    </li>

                                                                    <li class="d-flex align-items-center margin5px">
                                                                        <i class="fa-solid fa-calendar-day text-info me-2" style="opacity: 0.5;min-width: 20px"></i>
                                                                        <span class="me-2 text-secondary" style="min-width: 100px;">Ngày sinh:</span>
                                                                        <strong>${r.getDob()}</strong>
                                                                    </li>

                                                                    <li class="d-flex align-items-center margin5px">
                                                                        <i class="fa-solid fa-phone text-info me-2" style="opacity: 0.5;min-width: 20px"></i>
                                                                        <span class="me-2 text-secondary" style="min-width: 100px;">Số điện thoại:</span>
                                                                        <strong>${r.getPhone()}</strong>
                                                                    </li>

                                                                    <li class="d-flex align-items-start margin5px">
                                                                        <i class="fa-solid fa-location-dot text-info me-2 mt-1" style="opacity: 0.5;min-width: 20px"></i>
                                                                        <span class="me-2 text-secondary" style="min-width: 100px;">BHYT:</span>
                                                                        <c:if test="${empty r.bhyt}"><strong>Chưa cập nhật</strong></c:if>
                                                                        <strong>${r.bhyt}</strong>
                                                                    </li>

                                                                    <li class="d-flex align-items-center margin5px">
                                                                        <i class="fa-solid fa-people-group text-info me-2" style="opacity: 0.5;min-width: 20px"></i>
                                                                        <span class="me-2 text-secondary" style="min-width: 100px;">Dân tộc:</span>
                                                                        <c:if test="${empty r.getNation()}">
                                                                            <strong>Chưa cập nhật</strong>
                                                                        </c:if>
                                                                            
                                                                        <strong>${r.getNation()}</strong>
                                                                    </li>
                                                                </ul>
                                                                <div class="d-flex justify-content-between">

                                                                    <div class="d-flex gap-2">
                                                                        <button type="button" class="btn btn-danger" style="opacity: 0.7;"
                                                                                onclick="openDeleteModal('${fn:escapeXml(fn:escapeXml(r.getPatientName()))}', '${r.getPatientId()}')">
                                                                            <i class="fa-solid fa-trash me-1"></i> Xóa
                                                                        </button>
                                                                        <a href="updaterecords?id=${r.getPatientId()}&typeUpdate=unormal" class="btn btn-info" style="opacity: 0.7;">
                                                                            <i class="fa-solid fa-pen-to-square me-1"></i> Cập nhật
                                                                        </a>


                                                                    </div>

                                                                     <c:if test="${ empty error || r.patientId != pId }">
                                                                       <a href="confirmInformation?patientId=${r.patientId}" class="btn btn-primary">
                                                                        Tiếp tục <i class="fa-solid fa-arrow-right ms-1"></i>
                                                                    </a>
                                                                    </c:if>
                                                                     <c:if test="${not empty error && r.patientId == pId }"> 
                                                                       <a href="confirmInformation?patientId=${r.patientId}&isBHYT=1" class="btn btn-primary">
                                                                        Tôi không có thẻ BHYT <i class="fa-solid fa-arrow-right ms-1"></i>
                                                                    </a>
                                                                    </c:if>      
                                                                    
                                                                </div>
                                                                            
                                                            </div>
                                                        </div>
                                                    </li>

                                                </c:forEach>
                                            </ul>

                                        </c:if>

                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">

                                <a href="booking?stepName=dateTime&doctorId=${sessionScope.doctorId}&serviceId=${sessionScope.serviceBooking.service_id}" class="btn btn-outline-secondary">
                                    <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                                </a>


                                <a href="addrecords?typeAddRecords=unormal" class="btn btn-info">
                                    <i class="bi bi-plus-circle me-1"></i> Thêm hồ sơ
                                </a>
                            </div>
                        </div>

                    </div>
                    <div class="modal fade" id="deleteModal" tabindex="-1">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header bg-danger text-white">
                                    <h5 class="modal-title"><i class="fa-solid fa-triangle-exclamation me-2"></i>Xóa hồ sơ</h5>
                                    <button type="button" class="btn-close bg-white" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc chắn muốn xóa hồ sơ <strong id="deleteName"></strong> không?
                                </div>
                                <div class="modal-footer">
                                    <form method="post" action="updaterecords?action=delete&typeDelete=unormal">
                                        <input type="hidden" name="patientId" id="deleteId">
                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>                 
                </div>
            </section>

        </main>

        <jsp:include page="layout/footer.jsp"/>
        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">


        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
                                                                                    function openDeleteModal(name, id) {
                                                                                        document.getElementById("deleteName").innerText = name;
                                                                                        document.getElementById("deleteId").value = id;
                                                                                        var delModal = new bootstrap.Modal(document.getElementById('deleteModal'));
                                                                                        delModal.show();
                                                                                    }
        </script>
        <style>
            html, body {
                height: 100%;
                margin: 0;
            }

            body {
                display: flex;
                flex-direction: column;
            }

            main {
                flex: 1;
            }
            .section{
                height: 100%;
            }
            .mt-5{
                margin-top: 0rem !important;
            }
        </style>

    </body>

</html>
