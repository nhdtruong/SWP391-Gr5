
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <div class="card border-0 shadow p-4">
                            <h5>Thêm Dịch vụ Mới</h5>
                            <form action= "addservice" method="POST">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Tên dịch vụ<span class="text-danger">*</span></label>
                                        <input type="text" name="service_name"  oninput="CheckMaxLength200(this);" class="form-control" required>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Áp dụng BHYT<span class="text-danger">*</span></label>
                                        <select name="is_bhyt" class="form-select" id="bhytSelect" onchange="toggleDiscountInput()">
                                            <option value="0">Không</option>
                                            <option value="1">Có</option>
                                        </select>
                                    </div>

                                    <div class="col-md-12 mb-3">
                                        <label class="form-label">Mô tả</label>
                                        <textarea name="description" id="editors" class="form-control" rows="3">${service.description}</textarea>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Thể loại<span class="text-danger">*</span></label>
                                        <select name="category_service_id" class="form-select">
                                            <c:forEach items="${category}" var="cat">
                                                <option value="${cat.id}">${cat.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Khoa<span class="text-danger">*</span></label>
                                        <select class="form-select" name="department_id" id="department">
                                            <c:forEach var="d" items="${department}">
                                                <option  value="${d.id}">${d.department_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phí (VND)<span class="text-danger">*</span></label>
                                        <input type="text" id="feeInput" name="fee" class="form-control" oninput="CheckFee(this);" placeholder="Nhập số tiền, ví dụ:100,000" required>
                                    </div>

                                    <div class="col-md-1 mb-3">
                                        <label class="form-label">Giảm giá (%)</label>
                                        <div class="d-flex align-items-center">
                                            <input type="number" class="form-control me-1"
                                                   id="feeDiscount" name="discount"
                                                   min="0" max="100" step="1"
                                                   oninput="CheckDiscountPercent(this)"
                                                   placeholder="0 - 100" required disabled>
                                            <span>%</span>
                                        </div>
                                    </div>
                                    <!-- hidden input để lưu tỷ lệ 0.x gửi về server -->
                                    <input type="hidden" id="discount" name="discount" value="0" />
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phương thức thanh toán<span class="text-danger">*</span></label>
                                        <select name="payment_type_id" class="form-select">
                                            <option value="2" <c:if test="${service.payment_type_id == 2}">selected</c:if>>Thanh toán online</option>
                                            <option value="1" <c:if test="${service.payment_type_id == 1}">selected</c:if>>Thanh toán tại bệnh viện</option>


                                            </select>
                                        </div>

                                        <div class="mt-3">
                                            <button type="submit" class="btn btn-primary">Thêm</button>
                                            <a href="servicemanager?action=all" class="btn btn-secondary">Hủy</a>
                                        </div>
                                    </div>
                                </form>
                            </div>                
                        </div>
                    </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/trumbowyg@2/dist/ui/trumbowyg.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3/dist/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/trumbowyg@2/dist/trumbowyg.min.js"></script>

        <script>
                                            // Format tiền khi nhập vào (giống 100000 → 100.000)
                                            function formatMoneyInput(input) {
                                                input.addEventListener('input', function () {
                                                    let value = this.value.replace(/\D/g, ''); // chỉ giữ số
                                                    this.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ','); // thêm dấu chấm
                                                });
                                            }
                                            const feeInput = document.getElementById('feeInput');
                                            formatMoneyInput(feeInput);

                                            // Format discount
                                            const feeDiscount = document.getElementById('feeDiscount');
                                            formatMoneyInput(feeDiscount);

                                            $(document).ready(function () {
                                                $('#editors').trumbowyg();
                                            });

                                            function readURL(input) {
                                                if (input.files && input.files[0]) {
                                                    var reader = new FileReader();

                                                    reader.onload = function (e) {
                                                        const preview = document.getElementById('previewImage');
                                                        preview.src = e.target.result;
                                                        preview.style.display = 'block';
                                                    };
                                                    reader.readAsDataURL(input.files[0]);
                                                }
                                            }


                                            function CheckFee(text) {

                                                const feeValue = text.value.replace(/,/g, ''); // Xoá dấu phẩy ngăn cách số
                                                const digitsOnly = feeValue.replace(/\D/g, ''); // Lấy phần chỉ chứa số

                                                const feeNumber = parseInt(digitsOnly, 10);

                                                if (digitsOnly.length < 4) {
                                                    text.setCustomValidity('Giá không hợp lệ! Phải có ít nhất 4 chữ số.');
                                                } else if (feeNumber > 999999999) {
                                                    text.setCustomValidity('Số quá lớn.');
                                                } else {
                                                    text.setCustomValidity('');
                                                }

                                                return true;

                                            }


                                            function CheckDiscountPercent(percentInput) {
                                                const value = percentInput.value.trim();
                                                const percent = parseInt(value, 10);

                                                if (!isNaN(percent) && percent >= 0 && percent <= 100) {
                                                    percentInput.setCustomValidity('');
                                                } else {
                                                    percentInput.setCustomValidity('Giảm giá phải từ 0 đến 100 (%)');
                                                }
                                            }



                                            function CheckMaxLength200(text) {
                                                const value = text.value;

                                                // Kiểm tra khoảng trắng ở đầu
                                                if (/^\s/.test(value)) {
                                                    text.setCustomValidity('Không được có khoảng trắng ở đầu.');
                                                }
                                                // Kiểm tra độ dài tối đa
                                                else if (value.length > 200) {
                                                    text.setCustomValidity("Vui lòng nhập không quá 200 ký tự.");
                                                } else {
                                                    text.setCustomValidity('');
                                                }

                                                text.reportValidity();
                                                return true;
                                            }

                                            function toggleDiscountInput() {
                                                const bhytSelect = document.getElementById("bhytSelect");
                                                const discountInput = document.getElementById("feeDiscount");

                                                if (bhytSelect.value === "1") {
                                                    discountInput.disabled = false;
                                                } else {
                                                    discountInput.disabled = true;
                                                    discountInput.value = ""; // Xóa giá trị nếu không áp dụng BHYT
                                                }
                                            }

                                            // Nếu cần kích hoạt sẵn theo dữ liệu đã có
                                            window.addEventListener("DOMContentLoaded", function () {
                                                toggleDiscountInput();
                                            });


        </script>
    </body>
</html>
