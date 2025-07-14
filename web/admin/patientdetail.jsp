<%-- 
    Document   : patientdetail
    Created on : Jun 21, 2025, 8:49:48 PM
    Author     : dantr
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 

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
                            <div class="col-lg-12">
                                <ul class="nav nav-pills nav-justified flex-column flex-sm-row rounded" id="pills-tab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link rounded active" id="pills-cloud-tab" data-bs-toggle="pill" href="#info" role="tab" aria-controls="infor" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Thông tin</h4>
                                            </div>
                                        </a>
                                    </li>

                                    <li class="nav-item">
                                        <a class="nav-link rounded" id="pills-smart-tab" data-bs-toggle="pill" href="#edit" role="tab" aria-controls="edit" aria-selected="false">
                                            <div class="text-center pt-1 pb-1">
                                                <h4 class="title font-weight-normal mb-0">Chỉnh sửa</h4>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane show active row" id="info" role="tabpanel" aria-labelledby="info">
                                <div class="col-lg-12 col-md-12 mt-4">
                                    <div class="bg-white rounded shadow overflow-hidden">
                                        <div class="p-4 border-bottom">
                                            <h5 class="mb-0">Thông tin Dịch Vụ</h5>
                                        </div>
                                        <br><br><br><br><br>
                                        <div class="text-center avatar-profile margin-nagative mt-n5 position-relative pb-4 border-bottom">
                                            <c:if test="${patient.img == 'default'}">
                                                <img src="assets/images/avata.png" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                            </c:if>
                                            <c:if test="${patient.img != 'default'}">
                                                <img src="${patient.img}" class="rounded-circle shadow-md avatar avatar-md-md" alt="">
                                            </c:if>
                                            <h5 class="mt-3 mb-1">${patient.patientName}</h5>
                                            <p class="text-muted mb-0">${patient.username}</p>
                                        </div>
                                        <div class="list-unstyled p-4">
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Patient_id</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.patientId}</p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-user align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Giới tính</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.gender}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-book-open align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Số điện thoại</h6>
                                                <p class="text-muted mb-0 ms-2">0${patient.phone}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Email</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.email}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">BHYT</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.bhyt}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">CCCD</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.cccd}</p>
                                            </div>

                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-user align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Công việc </h6>
                                                <p class="text-muted mb-0 ms-2">${patient.job}</p>
                                            </div>


                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-italic align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">dân tộc</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.nation}</p>
                                            </div>


                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Ngày sinh</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.dob} </p>
                                            </div>
                                            <div class="d-flex align-items-center mt-2">
                                                <i class="uil uil-medical-drip align-text-bottom text-primary h5 mb-0 me-2"></i>
                                                <h6 class="mb-0">Địa chỉ</h6>
                                                <p class="text-muted mb-0 ms-2">${patient.address}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit">
                            <div class="card border-0 shadow overflow-hidden">
                                <div class="tab-content p-4" id="pills-tabContent">
                                    <form action="patientmanage?action=update_patient&username=${patient.username}" method="POST" class="mt-4" onsubmit="return validateForm();">
                                        <div class="row">
                                            <!-- Họ tên -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Họ tên</label>
                                                    <input name="name" id="name" type="text" class="form-control" value="${patient.patientName}" required pattern="^[\p{L} ]+$" title="Chỉ chứa chữ và khoảng trắng">
                                                </div>
                                            </div>

                                            <!-- Hidden fields -->
                                            <input hidden name="patient_id" type="text" value="${patient.patientId}">
                                            <input hidden name="username" type="text" value="${patient.username}">

                                            <!-- Giới tính -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Giới tính</label>
                                                    <div>
                                                        <label><input name="gender" type="radio" value="Nam" class="form-check-input" ${patient.gender=='Nam'?"checked":""} required> Nam</label>
                                                        <label class="ms-3"><input name="gender" type="radio" value="Nữ" class="form-check-input" ${patient.gender=='Nữ'?"checked":""}> Nữ</label>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Số điện thoại -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Số điện thoại</label>
                                                    <input name="phone" type="tel" class="form-control" value="${patient.phone}" required pattern="^0\d{9}$" title="Số điện thoại phải bắt đầu bằng 0 và đủ 10 chữ số">
                                                </div>
                                            </div>

                                            <!-- Email -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Email</label>
                                                    <input name="email" type="email" class="form-control" value="${patient.email}" required>
                                                </div>
                                            </div>

                                            <!-- BHYT -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">BHYT</label>
                                                    <input name="bhyt" type="text" class="form-control" value="${patient.bhyt}" pattern="^[A-Z0-9]{10,15}$" title="Mã BHYT gồm 10-15 ký tự chữ hoặc số" required>
                                                </div>
                                            </div>

                                            <!-- CCCD -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">CCCD</label>
                                                    <input name="cccd" type="text" class="form-control" value="${patient.cccd}" pattern="^\d{12}$" title="CCCD gồm đúng 12 chữ số" required>
                                                </div>
                                            </div>

                                            <!-- Công việc -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Công việc</label>
                                                    <input name="job" type="text" class="form-control normalize-space" value="${patient.job}" required>
                                                </div>
                                            </div>

                                            <!-- Địa chỉ -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Địa chỉ</label>
                                                    <input name="address" type="text" class="form-control normalize-space" value="${patient.address}" required>
                                                </div>
                                            </div>

                                            <!-- Dân tộc -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Dân tộc</label>
                                                    <input name="nation" type="text" class="form-control normalize-space" value="${patient.nation}" required>
                                                </div>
                                            </div>

                                            <!-- Ngày sinh -->
                                            <div class="col-lg-12">
                                                <div class="mb-3">
                                                    <label class="form-label">Ngày sinh</label>
                                                    <input name="DOB" type="date" class="form-control" value="${patient.dob}" required min="1922-01-01" max="2003-01-01">
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Nút submit -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="submit" id="submit" name="send" class="btn btn-primary" value="Cập nhật">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <jsp:include page="../admin/layout/footer.jsp"/>
                        </main>
                    </div>

                    <script src="assets/js/bootstrap.bundle.min.js"></script>
                    <script src="assets/js/simplebar.min.js"></script>
                    <script src="assets/js/feather.min.js"></script>
                    <script src="assets/js/app.js"></script>
                    <script>

                                        function cleanInputValue(input) {
                                            input.value = input.value
                                                    .replace(/^\s+|\s+$/g, '')   // Bỏ khoảng trắng đầu/cuối
                                                    .replace(/\s{2,}/g, ' ');    // Gộp nhiều dấu cách giữa thành 1
                                        }

                                        window.addEventListener('DOMContentLoaded', () => {
                                            document.querySelectorAll('.normalize-space').forEach(input => {
                                                input.addEventListener('blur', () => cleanInputValue(input)); // ✅ Chỉ xử lý khi rời ô
                                            });
                                        });

                                        function validateForm() {
                                            const nameInput = document.querySelector("input[name='name']");
                                            let name = nameInput.value;

                                            // ✅ Loại bỏ khoảng trắng đầu/cuối và thay thế nhiều khoảng trắng bằng 1 khoảng trắng
                                            name = name.trim().replace(/\s+/g, ' ');

                                            // ✅ Cập nhật lại giá trị input đã chuẩn hóa
                                            nameInput.value = name;

                                            // ✅ Kiểm tra độ dài hợp lệ (nếu cần)
                                            if (name.length < 2) {
                                                alert("Họ tên quá ngắn. Vui lòng nhập đầy đủ họ tên.");
                                                return false;
                                            }

                                            return true;
                                        }


                                        function readURL(input, thumbimage) {
                                            if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
                                                var reader = new FileReader();
                                                reader.onload = function (e) {
                                                    $("#thumbimage").attr('src', e.target.result);
                                                }
                                                reader.readAsDataURL(input.files[0]);
                                            } else { // Sử dụng cho IE
                                                $("#thumbimage").attr('src', input.value);

                                            }
                                            $("#thumbimage").show();
                                            $('.filename').text($("#uploadfile").val());
                                            $(".Choicefile").hide();
                                            $(".Update").show();
                                            $(".removeimg").show();
                                        }
                                        $(document).ready(function () {
                                            $(".Choicefile").bind('click', function () {
                                                $("#uploadfile").click();

                                            });
                                            $(".removeimg").click(function () {
                                                $("#thumbimage").attr('src', '').hide();
                                                $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                                                $(".removeimg").hide();
                                                $(".Choicefile").show();
                                                $(".Update").hide();
                                                $(".filename").text("");
                                            });
                                        })
                    </script>
                    </body>

                    </html>

