<%-- 
    Document   : updaterecords
    Created on : 14 Jun 2025, 01:19:59
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <div class="container" style="margin-top: 70px; background-color: white;">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb" class="mt-3">
                        <ul class="breadcrumb bg-transparent mb-0">
                            <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                            <li class="breadcrumb-item"><a href="#" style="color: #00b5f1;">Đặt khám chuyên khoa</a></li>
                        </ul>
                    </nav>
                </div>

                <div class="col-12 d-flex justify-content-center align-items-center" style="height: 80px;">
                    <h4 class="fw-bold mb-0">CẬP NHẬT HỒ SƠ</h4>
                </div>
            </div>
        </div>
        <div class="main-wrapper d-flex flex-column min-vh-100" style="background: #e8f2f7; padding-top: 35px"> 





            <div class="container d-flex justify-content-center" style="padding-bottom: 50px" >
                <div class="w-100" style="max-width: 800px;">
                    <div class="mb-4" style="height: 50px; background-color: #d4e9ff; border-radius: 8px; display: flex; align-items: center; padding: 0 10px"><h6 class="text-primary mb-0" style="margin-left: 15px">Vui lòng cung cấp thông tin chính xác để được phục vụ tốt nhất.</h6></div>
                    <h5 class=" mb-4"><span class="text-danger">(*)Thông tin bắt buộc nhập</span></h5>
                    <h4 class=" mb-4">Thông tin chung</h4>
                    <form action="updaterecords?action=update" method="post">
                        <div class="row g-3">
                            <!-- Cột trái -->
                            <div class="col-md-6">
                                <!-- Các input như đã có -->
                                <input type="hidden" name="patientId" value="${records.patientId}" />
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Họ và tên (có dấu)<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control hei" name="patientName" value="${records.patientName}" oninvalid="CheckFullName(this);"  oninput="CheckFullName(this);" required placeholder="Ví dụ: Dư Văn Huy">
                                </div>
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Số điện thoại<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control hei" name="phone" oninvalid="CheckPhone(this);" oninput="CheckPhone(this);" value="${records.phone}" required placeholder="Vui lòng nhập số điện thoại...">
                                </div>

                                <div class="mb-4">
                                    <label class="form-label fw-bold">Nghề nghiệp</label>
                                    <input type="text" class="form-control hei" name="job" value="${records.job}" placeholder="Nghề nghiệp">
                                </div>
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Địa chỉ Email</label>
                                    <input type="email" class="form-control hei" name="email" oninvalid="CheckEmail(this);" oninput="CheckEmail(this);" value="${records.email}" placeholder="Địa chỉ Email">
                                </div>
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Dân tộc</label>
                                    <input type="text" class="form-control hei" name="nation" value="${records.nation}" placeholder="Dân tộc">
                                </div>

                            </div>

                            <!-- Cột phải -->
                            <div class="col-md-6">
                                <!-- Select2 CSS -->


                                <div class="mb-4">
                                    <label class="form-label fw-bold">
                                        Ngày sinh (năm/tháng/ngày) <span class="text-danger">*</span>
                                    </label>
                                    <div class="d-flex gap-2">
                                        <select class="form-select hei" id="year" name="year" required>
                                            <option disabled hidden <c:if test="${empty year}">selected</c:if>>Năm</option>
                                            <c:forEach var="y" items="${year}">
                                                <option value="${y}" <c:if test="${year == y}">selected</c:if>>${y}</option>
                                            </c:forEach>
                                        </select>

                                        <!-- Month -->
                                        <select class="form-select hei" id="month" name="month" required>
                                            <option disabled hidden <c:if test="${empty month}">selected</c:if>>Tháng</option>
                                            <c:forEach var="m" items="${month}">
                                                <option value="${m}" <c:if test="${month == m}">selected</c:if>>${m}</option>
                                            </c:forEach>
                                        </select>

                                        <!-- Day -->
                                        <select class="form-select hei" id="day" name="day" required>
                                            <option disabled hidden <c:if test="${empty day}">selected</c:if>>Ngày</option>
                                            <c:forEach var="d" items="${day}">
                                                <option value="${d}" <c:if test="${day == d}">selected</c:if>>${d}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <!-- Select2 JS -->


                                <div class="mb-4">
                                    <label class="form-label fw-bold">Giới tính <span class="text-danger">*</span> 
                                        <span style="color: red;">${errorGender}</span>
                                    </label>
                                    <select class="form-select hei" name="gender" required>
                                        <option disabled hidden <c:if test="${empty records.gender}">selected</c:if>>Chọn giới tính</option>
                                        <option value="Nam" <c:if test="${records.gender == 'Nam'}">selected</c:if>>Nam</option>
                                        <option value="Nữ" <c:if test="${records.gender == 'Nữ'}">selected</c:if>>Nữ</option>
                                        <option value="Khác" <c:if test="${records.gender == 'Khác'}">selected</c:if>>Khác</option>
                                        </select>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label fw-bold">Mã định danh/CCCD/Passport<span class="text-danger">*</span> <span style="color: red; align-content: center;">${errorCCCD}</span></label>
                                    <input type="text" class="form-control hei" name="cccd" oninput="CheckCCCD(this);" oninvalid="CheckCCCD(this);" value="${records.cccd}" required placeholder="Vui lòng nhập Mã định danh/CCCD/Passport">
                                </div>
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Mã BHYT <span style="color: red; align-content: center;">${errorBHYT}</span></label>
                                    <input type="text" class="form-control hei" name="bhyt" oninput="CheckBHYT(this);" oninvalid="CheckBHYT(this);" value="${records.bhyt}" placeholder="Mã bảo hiểm y tế">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="mb-4">
                                    <label class="form-label fw-bold">Địa chỉ theo CCCD<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control hei" name="address" value="${records.address}" required placeholder="Số nhà/Tên đường/Ấp thôn xóm( bao gồm tỉnh/thành, quận/huyện, phường/xã)">
                                </div>
                            </div>
                        </div>


                        <div class="mt-4 text-center">
                            <button type="submit" class="btn btn-primary px-4">Cập nhật</button>
                        </div>
                    </form>
                </div>
            </div>



            <div class="mt-auto">
                <jsp:include page="layout/footer.jsp"/>
            </div>

        </div> 

        <jsp:include page="layout/search.jsp"/>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="assets/js/app.js"></script>
        <style>

            .hei{
                height: 45px !important;

            }

            /* Apply height to Select2 rendered box */
            .select2-container .select2-selection--single {
                height: 45px !important;


            }

            .select2-selection__rendered {


            }

            .select2-selection__arrow {

                height: 45px !important;
            }
        </style>
        <script>
<!-- jQuery CDN -->
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function () {
                    // Khởi tạo select2 cho 3 dropdown
                    $('#year, #month, #day').select2({
            minimumResultsForSearch: Infinity, // tắt ô tìm kiếm
                    width: '100%'
            });
            // Thêm năm từ 1900 đến hiện tại
            const yearSelect = $('#year');
            const currentYear = new Date().getFullYear();
            for (let y = currentYear; y >= 1900; y--) {
            yearSelect.append(new Option(y, y));
            }

            // Thêm tháng 1–12
            const monthSelect = $('#month');
            for (let m = 1; m <= 12; m++) {
            monthSelect.append(new Option(m, m));
            }

            // Cập nhật ngày theo tháng và năm
            function updateDays() {
            const year = parseInt($('#year').val());
            const month = parseInt($('#month').val());
            const daySelect = $('#day');
            daySelect.empty().append(new Option('Ngày', ''));
            if (!isNaN(year) && !isNaN(month)) {
            const daysInMonth = new Date(year, month, 0).getDate();
            for (let d = 1; d <= daysInMonth; d++) {
            daySelect.append(new Option(d, d));
            }
            }

            daySelect.trigger('change.select2');
            }

            $('#year, #month').on('change', updateDays);
                    });
                    </script>

        </script>

    </body>



</html>
