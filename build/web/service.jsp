
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
         
        <section class="bg-half-180 d-table w-100" style="background: url('assets/images/bg/banner2.png') center;">
            
            <div class="container">
                <div class="row mt-5 mt-lg-0">
                    <div class="col-12">
                        <div class="heading-title">
                            <img src="assets/images/logo-icon.png" height="50" alt="">
                            <h4 class="display-4 fw-bold  title- mt-3 mb-4" style="color:#00e0ff">DỊCH VỤ Y TẾ TOÀN DIỆN <br> Hỗ Trợ Y Tế Trực Tuyến</h4>
                            <p class="para-desc  mb-0" style="color: rgb(43 25 25)">Khám phá và tận hưởng sự tiện lợi của dịch vụ y tế tại Doctris</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="bg-half-50 bg-light d-table w-100" >
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Danh sách dịch vụ</h3>
                            <p class="para-desc mx-auto text-muted">Nếu bạn,người nhà của mình cần nhận được sự trợ giúp ngay lập tức, điều trị khẩn cấp hãy đặt lịch hẹn.</p>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#">Danh sách dịch vụ</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                 <div class="row align-items-center">
                    <c:forEach items="${service}" var="s">
                        <div class="col-xl-4 col-lg-4 col-md-6 mt-4 pt-2" style="" >
                            <div class="card team border-0 rounded shadow overflow-hidden">
                                <div class="team-img position-relative" style="display: flex ;margin: auto">
                                    <img style="height:80px;width: 80px"src="${s.getImg()}" class="" alt="">
                                </div>
                                <div class="card-body content text-center">
                                    <a href="detailservice?id=${s.getId()}" class="title text- h5 d-block mb-0">${s.getName()}</a>
                                    <small class="text-muted speciality">${s.getName()}</small>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-12 mt-4 pt-2 text-center">
                        <a href="#" class="btn btn-primary">Tìm kiếm nhiều hơn</a>
                    </div>
                </div>
            </div>
        </section>

        </div>

       
        <jsp:include page="layout/footer.jsp"/>

        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>

        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>
