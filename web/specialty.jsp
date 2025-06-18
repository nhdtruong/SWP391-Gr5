<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
     <jsp:include page="layout/head.jsp"/>
    <body>
         <jsp:include page="layout/menu_white.jsp"/>
         
          <section class="section">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Trang chủ</a></li>
                                    <li class="breadcrumb-item"><a href="#" style="color: #00b5f1; ">Đặt khám chuyên khoa</a></li>
                                </ul>
                            </nav>
                    </div>
                </div>
                <div class="row align-items-center">
                    <c:forEach items="${listDe}" var="d">
                        <div class="col-xl-4 col-lg-4 col-md-6 mt-4 pt-2" style="" >
                            <div class="card team border-0 rounded shadow overflow-hidden">
                                <div class="team-img position-relative" style="display: flex ;margin: auto">
                                    <img style="height:80px;width: 80px"src="${d.getImg()}" class="" alt="">
                                </div>
                                <div class="card-body content text-center">
                                    <a href="booking?department_id=${d.getId()}&departmentName=${d.getDepartment_name()}" class="title text- h5 d-block mb-0">${d.getDepartment_name()}</a>
                                   
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    
                </div>
            </div>
        </section>
         
          <jsp:include page="layout/footer.jsp"/>

        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>

        <jsp:include page="layout/search.jsp"/>
        <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
</html>
