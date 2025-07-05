<%-- 
    Document   : callVideoDoctor
    Created on : 5 Jul 2025, 16:25:23
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class=" bg-half-50 d-table w-100" style="background: url('assets/images/bg/banner4.png') center; margin-top: 70px">

            <div class="container">
                <div class="row mt-5 mt-lg-0">
                    <div class="row mt-5 mt-lg-0">
                        <div class="col-12">
                            <div class="heading-title mb-4">
                                <div class="row align-items-center">
                                    <!-- Nội dung bên trái -->
                                    <div class="col-md-7">
                                        <div class="p-4  shadow"  style="background-color:white; border-radius: 20px;">
                                            <h3 class="fw-bold mb-3" style="color: #00b4d8;">GỌI VIDEO VỚI BÁC SĨ</h3>
                                            <ul class="mb-4" style="list-style: none; padding-left: 0;">
                                                <li>✔️ Khám/tư vấn sức khỏe từ xa với 20+ bác sĩ từ 10+ chuyên khoa</li>
                                                <li>✔️ Được nhắn tin với bác sĩ trước, trong và sau buổi khám</li>
                                                <li>✔️ Được tư vấn với bác sĩ tối thiểu 15 phút</li>
                                                <li>✔️ Đặt lịch linh hoạt - hỗ trợ dời lịch, hoàn tiền khi hủy phiếu</li>
                                                <li>✔️ Bảo mật thông tin theo quy định y tế</li>
                                            </ul>
                                            <div class="d-flex align-items-center flex-wrap gap-2 mb-3">
                                                <p class="mb-0 fw-medium">
                                                    Liên hệ <span class="fw-bold">chuyên gia</span> để tư vấn thêm:
                                                </p>
                                                <a href="tel:19002115" class="text-primary fw-bold" style="font-size: 1.1rem;">📞 19002115</a>
                                                <span>hoặc</span>
                                                <a href="#" class="btn btn-warning text-white fw-bold">💬 Chat ngay</a>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Ảnh bên phải -->
                                    <div class="col-md-5 text-center">
                                        <img src="assets/images/bg/banner3.png" alt="Gọi video với bác sĩ" class="img-fluid" style="max-width: 100%;margin-bottom:-90px">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <section class="section">
            <div class="container">
                <div class="row"> 
                    <div class="col-lg-10 col-md-10 row align-items-center">
                        <c:if test="${numberPage== 0}">
                            <div class="alert alert-warning text-center" style="width: 100%;">
                                Không có bác sĩ nào phù hợp với bộ lọc của bạn.
                            </div>
                        </c:if>

                        <c:forEach items="${listD}" var="d">
                            <div class="col-xl-6 col-lg-6 col-md-6 mt-2 pt-2 d-flex">
                                <div class="card team border-0 rounded shadow overflow-hidden d-flex flex-column w-100 h-100">
                                    <div style="display: none">${d.doctor_id}</div>
                                    <div class="card shadow rounded-4 d-flex flex-row p-3" style="max-width: 800px; background-color: #f5fcff;">
                                        <!-- Hình bác sĩ -->
                                        <div class="text-center me-3 position-relative">
                                            <img src="${d.img}" class="rounded-circle" style="width: 120px; height: 120px;" alt="Bác sĩ">
                                            <div class="position-absolute top-100 start-50 translate-middle bg-white px-3 py-1 rounded shadow-sm fw-bold" style="margin-top: 8px;">
                                                Xem chi tiết
                                            </div>
                                            <!-- Rating -->
                                            <div class="mt-5 d-flex justify-content-center align-items-center border rounded-pill px-3 py-1 text-primary fw-bold" style="gap: 10px; border-color: #00bfff;">
                                                <span>4 ⭐</span>
                                                <div style="border-left: 1px solid #00bfff; height: 20px;"></div>
                                                <span>40 👤</span>
                                            </div>
                                        </div>

                                        <!-- Thông tin bác sĩ -->
                                        <div class="flex-grow-1">
                                            <h5 class="fw-bold mb-3" style="color: #00cfff;">
                                                <c:if test="${d.getAcademicTitle().getName().isEmpty() && d.getAcademicDegree().getName().isEmpty()}">
                                                    <a >${d.getPosition().getName()}.${d.getDoctor_name()} </a>

                                                </c:if>
                                                <c:if test="${d.getAcademicTitle().getName().isEmpty() && !d.getAcademicDegree().getName().isEmpty()}">
                                                    <a >${d.getAcademicDegree().getName()}.Bác sĩ.${d.getDoctor_name()} </a>

                                                </c:if>
                                                <c:if test="${!d.getAcademicTitle().getName().isEmpty() && !d.getAcademicDegree().getName().isEmpty()}">
                                                    <a >${d.getAcademicTitle().getName()}.${d.getAcademicDegree().getName()}.${d.getDoctor_name()} </a>

                                                </c:if>
                                                <c:if test="${!d.getAcademicTitle().getName().isEmpty() && d.getAcademicDegree().getName().isEmpty()}">
                                                    <a >${d.getAcademicTitle().getName()}.${d.getDoctor_name()} </a>

                                                </c:if>
                                            </h5>
                                            <ul class="list-unstyled mb-4" style="color: #06293a;">
                                                <li class="d-flex align-items-start mb-2">
                                                    <span style="min-width: 24px; opacity: 0.7;">🩺</span>
                                                    <span class="ms-2">Chuyên khoa: ${d.department.department_name}</span>
                                                </li>
                                                <li class="d-flex align-items-start mb-2">
                                                    <span style="min-width: 24px; opacity: 0.7;">📋</span>
                                                    <span class="ms-2">Chuyên trị: ${d.specialized}</span>
                                                </li>
                                                <li class="d-flex align-items-start mb-2">
                                                    <span style="min-width: 24px; opacity: 0.7;">🗓</span>
                                                    <span class="ms-2">Lịch khám: Hẹn khám</span>
                                                </li>
                                                <li class="d-flex align-items-start mb-2">
                                                    <span style="min-width: 24px; opacity: 0.7;">💼</span>
                                                    <span class="ms-2">Giá khám: ${d.fee}</span>
                                                </li>
                                            </ul>
                                            <a href="booking" class="btn btn-info text-white fw-bold px-4 py-2 rounded-pill">Đặt khám ngay</a>
                                        </div>
                                    </div>  


                                </div>
                            </div>

                        </c:forEach>

                    </div>

                    <div class="col-lg-2 col-md-2 mt-3 pt-2">
                        <div class="card border-0 sidebar sticky-bar rounded shadow">
                            <div class="card-body">
                                <form action="doctor?action=filter" method="get" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="widget mb-4 pb-2">
                                        <h5 class="widget-title">Lọc</h5>
                                        <div class="row align-items-center">
                                            <div class="col-md-12">
                                                <label class="form-label">Giới tính</label>
                                            </div>
                                            <div class="col-md-12">
                                                <select name="gender" class="form-select">
                                                    <option <c:if test="${gender == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${gender == 'true'}"> selected </c:if> value="true">Nam</option>
                                                    <option <c:if test="${gender == 'false'}"> selected </c:if> value="false">Nữ</option>
                                                    </select>  
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row align-items-center">
                                                <div class="col-md-12">
                                                    <label class="form-label">Chuyên môn</label>
                                                </div>
                                                <div class="col-md-12">
                                                    <select name="speciality" class="form-select">
                                                        <option <c:if test="${speciality == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${listDepartment}" var="s">
                                                        <option <c:if test="${speciality == s.id}"> selected </c:if> value="${s.id}">${s.department_name}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <br>
                                        <div class="widget mb-4 pb-2">
                                            <h5 class="widget-title">Sắp xếp</h5>
                                            <div class="row align-items-center">
                                                <div class="col-md-12">
                                                    <select name="SortType" onchange="Sort(this.value)" class="form-select">
                                                        <option <c:if test="${sort == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                        <option <c:if test="${sort == 'star'}"> selected </c:if> value="star">Star</option>
                                                        <option <c:if test="${sort == 'latest'}"> selected </c:if> value="latest">Mới nhất</option>
                                                        <option <c:if test="${sort == 'popular'}"> selected </c:if> value="popular">Phổ biến</option>
                                                        </select>  
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Lọc</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </section>
        <jsp:include page="layout/footer.jsp"/>



        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <style>
            .section{
                padding: 50px;
            }
            @media (min-width: 1400px) {
                .container {
                    max-width: 1400px !important;
                }
            }
        </style>

    </body>

</html>