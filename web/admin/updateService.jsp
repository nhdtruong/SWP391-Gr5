

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <div class="card border-0 shadow p-4">
                            <h5>Chỉnh sửa Dịch vụ</h5>
                            <form action="updateservice?action=update" method="POST">
                                <input type="hidden" name="serviceId" value="${service.service_id}">
                                <div class="row">
                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Tên dịch vụ</label>
                                        <input type="text" name="service_name" value="${service.service_name}" class="form-control" required>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Áp dụng BHYT</label>
                                        <select name="is_bhyt" class="form-control">
                                            <option value="1" ${service.is_bhyt == true ? 'selected' : ''}>Có</option>
                                            <option value="0" ${service.is_bhyt == false ? 'selected' : ''}>Không</option>
                                        </select>
                                    </div>

                                    <div class="col-md-12 mb-3">
                                        <label class="form-label">Ghi chú - Mô tả</label>
                                        <textarea name="description" id="editors" class="form-control" rows="3">${service.description}</textarea>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Thể loại</label>
                                        <select name="category_service_id" class="form-select">
                                            <c:forEach items="${category}" var="cat">
                                                <option value="${cat.id}" <c:if test="${service.category_service.id == cat.id}">selected</c:if>>${cat.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Khoa</label>
                                        <select class="form-select" name="department_id" id="department">
                                            <c:forEach var="d" items="${department}">
                                                <option value="${d.id}"<c:if test="${d.id==service.department.id}">selected</c:if>>${d.department_name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phí</label>
                                        <input type="text" id="feeInput" name="fee" class="form-control"
                                               value="<fmt:formatNumber value='${service.fee}' pattern='#,##0'/>"  oninput="CheckFee(this); CheckDiscount(document.getElementById('feeDiscount'))" />
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Giảm giá</label>
                                        <input type="text" id ="feeDiscount" name="discount" class="form-control"
                                               value="<fmt:formatNumber value='${service.discount}' pattern='#,##0'/>"  oninput="CheckDiscount(this)" />
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phương thức thanh toán</label>
                                        <select name="payment_type_id" class="form-select">

                                            <option value="1" <c:if test="${service.payment_type_id == 1}">selected</c:if>>Thanh toán tại bệnh viện</option>
                                            <option value="2" <c:if test="${service.payment_type_id == 2}">selected</c:if>>Thanh toán online</option>

                                            </select>
                                        </div>

                                        <div class="mt-3">
                                            <button type="submit" class="btn btn-primary">Lưu</button>
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
        <script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
        <script>
            // Format tiền khi nhập vào (giống 100000 → 100.000)
            function formatMoneyInput(input) {
                input.addEventListener('input', function () {
                    let value = this.value.replace(/\D/g, ''); // chỉ giữ số
                    this.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ','); // thêm dấu chấm
                });
            }

            // Format fee
            const feeInput = document.getElementById('feeInput');
            formatMoneyInput(feeInput);

            // Format discount
            const feeDiscount = document.getElementById('feeDiscount');
            formatMoneyInput(feeDiscount);

            // Trước khi submit, xoá dấu chấm trong cả fee và discount

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