
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
                                        <input type="text" name="service_name" class="form-control" required>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Áp dụng BHYT<span class="text-danger">*</span></label>
                                        <select name="is_bhyt" class="form-select">
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
                                        <input type="text" id="feeInput" name="fee" value="0" class="form-control" oninput="CheckFee(this); CheckDiscount(document.getElementById('feeDiscount'))">
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Giảm giá (VND)</label>
                                        <input type="text" id ="feeDiscount" name="discount" class="form-control" value="0" oninput="CheckDiscount(this)">
                                    </div>

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
                const feeValue = text.value.replace(/,/g, ''); // bỏ dấu phẩy
                const digitsOnly = feeValue.replace(/\D/g, ''); // chỉ lấy số

                if (digitsOnly.length < 4) {
                    text.setCustomValidity('Giá không hợp lệ!');
                } else {
                    text.setCustomValidity('');
                }
                return true;
            }
            function CheckDiscount(text) {
                const discountValue = parseFloat(text.value.replace(/,/g, ''));
                const feeInput = document.getElementById('feeInput');
                const feeValue = parseFloat(feeInput.value.replace(/,/g, ''));

                if (!isNaN(discountValue) && !isNaN(feeValue) && discountValue > feeValue) {
                    text.setCustomValidity('Giảm giá không được lớn hơn phí!');
                } else {
                    text.setCustomValidity('');
                }
                return true;
            }

        </script>
    </body>
</html>
