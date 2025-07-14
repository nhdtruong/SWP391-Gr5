

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
                                        <label class="form-label">Tên dịch vụ<span class="text-danger">*</span></label>
                                        <input type="text" name="service_name" value="${service.service_name}" class="form-control" required>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Áp dụng BHYT<span class="text-danger">*</span></label>
                                        <select name="is_bhyt" id="bhytSelect" class="form-control" onchange="toggleDiscountInput()">
                                            <option value="1" ${service.is_bhyt == true ? 'selected' : ''}>Có</option>
                                            <option value="0" ${service.is_bhyt == false ? 'selected' : ''}>Không</option>
                                        </select>
                                    </div>
                                    <div class="col-md-12 mb-3">
                                        <label class="form-label">Ghi chú - Mô tả</label>
                                        <textarea name="description" id="editors" class="form-control" rows="3">${service.description}</textarea>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Thể loại<span class="text-danger">*</span></label>
                                        <select name="category_service_id" class="form-select" id="categoryServiceSelect">
                                            <c:forEach items="${category}" var="cat">
                                                <option value="${cat.id}" <c:if test="${service.category_service.id == cat.id}">selected</c:if>>${cat.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Chuyên khoa<span class="text-danger">*</span></label>
                                        <select class="form-select" name="department_id" id="department">
                                            <option value="0" <c:if test="${empty service.department.id }">selected</c:if>>Không </option>

                                            <c:forEach var="d" items="${department}">
                                                <option value="${d.id}" <c:if test="${d.id == service.department.id}">selected</c:if>>${d.department_name}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                    <div class="col-md-12 mt-3" id="doctorInfoDiv" style="display: none;">
                                        <label class="form-label">Bác sĩ đang thực hiện dịch vụ gọi video:</label>
                                        <c:if test="${not empty doctor}">
                                            <div class="alert alert-info">
                                                ${doctor.doctor_name} - (${doctor.department.department_name})
                                            </div>
                                        </c:if>
                                        <c:if test="${empty doctor}">
                                            <div class="text-muted fst-italic">Chưa có bác sĩ nào thực hiện dịch vụ này.</div>
                                        </c:if>
                                    </div>


                                    <div class="col-md-6 mb-3">
                                        <label class="form-label">Phí(VNĐ)<span class="text-danger">*</span></label>
                                        <input type="text" id="feeInput" name="fee" class="form-control"
                                               value="<fmt:formatNumber value='${service.fee}' pattern='#,##0'/>"  oninput="CheckFee(this);" required/>
                                    </div>
                                    <div class="col-md-1 mb-3">
                                        <label class="form-label">Giảm giá (%)</label>
                                        <div class="d-flex align-items-center">
                                            <input value="${(service.discount *100).intValue()}" 
                                                   data-original-value="${(service.discount * 100 ).intValue()}" 
                                                   type="number" class="form-control me-1"
                                                   id="feeDiscount" name="discount"
                                                   min="0" max="100" step="1"
                                                   oninput="CheckDiscountPercent(this)"
                                                   placeholder="0 - 100" required disabled>
                                            <span>%</span>
                                        </div>
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




                                           function CheckFee(text) {

                                                const feeValue = text.value.replace(/,/g, ''); // Xoá dấu phẩy ngăn cách số
                                                const digitsOnly = feeValue.replace(/\D/g, ''); // Lấy phần chỉ chứa số

                                                const feeNumber = parseInt(digitsOnly, 10);

                                                if (digitsOnly < 0) {
                                                    text.setCustomValidity('Giá không hợp lệ! Phải lớn hơn 0.');
                                                } else if (feeNumber > 999999999) {
                                                    text.setCustomValidity('Số quá lớn.');
                                                } else {
                                                    text.setCustomValidity('');
                                                }

                                                return true;

                                            }
                                            
                                            function toggleDiscountInput() {
                                                const bhytSelect = document.getElementById("bhytSelect");
                                                const feeDiscount = document.getElementById("feeDiscount");

                                                if (bhytSelect.value === "1") {
                                                    feeDiscount.disabled = false;

                                                    // Nếu đang rỗng thì phục hồi lại giá trị gốc
                                                    if (!feeDiscount.value) {
                                                        feeDiscount.value = feeDiscount.getAttribute("data-original-value") || "";
                                                    }

                                                } else {
                                                    feeDiscount.disabled = true;
                                                    feeDiscount.value = ""; // Xóa khi không áp dụng
                                                }
                                            }


                                            window.addEventListener("DOMContentLoaded", toggleDiscountInput);

                                            // Gọi tự động khi trang vừa load để xử lý mặc định từ server
                                            window.addEventListener("DOMContentLoaded", toggleDiscountInput);
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
                                            document.addEventListener('DOMContentLoaded', function () {
                                                const categorySelect = document.getElementById('categoryServiceSelect');
                                                const doctorInfoDiv = document.getElementById('doctorInfoDiv');

                                                function toggleDoctorInfoDiv() {
                                                    if (categorySelect.value === '2') {
                                                        doctorInfoDiv.style.display = 'block';
                                                    } else {
                                                        doctorInfoDiv.style.display = 'none';
                                                    }
                                                }

                                                // Gọi khi người dùng thay đổi
                                                categorySelect.addEventListener('change', toggleDoctorInfoDiv);

                                                // Gọi khi trang vừa load xong
                                                toggleDoctorInfoDiv();
                                            });


        </script>
    </body>
</html>