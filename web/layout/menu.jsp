<%-- 
    Document   : menu
    Created on : 13 May 2025, 23:41:42
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            /* Ẩn menu con mặc định */
            /* Tạo vùng tương đối để định vị dropdown */
            .dropdown1 {
                position: relative;
            }

            /* Menu con ban đầu ẩn, có hiệu ứng mờ và dịch lên */
            .dropdown-menu1 {
                display: block;
                opacity: 0;
                visibility: hidden;
                transform: translateY(10px);
                transition: all 0.3s ease;
                position: absolute;
                top: 100%;
                left: 0;
                background: white;
                padding: 10px 0;
                list-style: none;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                border-radius: 8px;
                min-width: 180px;
                z-index: 1000;
                margin-top: -5px;
                margin-left: 15px;
            }

            /* Khi hover thì hiện ra từ từ */
            .dropdown1:hover .dropdown-menu1 {
                opacity: 1;
                visibility: visible;
                transform: translateY(0);
            }

            /* Kiểu cho các mục trong dropdown */
            .dropdown-menu1 li a {
                padding: 10px 15px;
                display: block;
                color: #333;
                text-decoration: none;
                transition: background 0.2s;
            }

            .dropdown-menu1 li a:hover {
                background: #f0f0f0;
            }
            .dropdown-menu{
                top: 100px;
            }

        </style>   
        <header id="topnav" class="navigation sticky">
            <div class="container">

                <div>
                    <a class="logo" href="home">
                        <img src="assets/images/logo-light.png" height="24" class="l-light" alt="">
                        <img src="assets/images/logo-dark.png" class="l-dark" height="24" alt="">
                    </a>
                </div>

                <div class="menu-extras">
                    <div class="menu-item">
                        <a class="navbar-toggle" id="isToggle" onclick="toggleMenu()">
                            <div class="lines">
                                <span></span>
                                <span></span>
                                <span></span>
                            </div>
                        </a>
                    </div>
                </div>

                <ul class="dropdowns list-inline mb-0">
                    <!-- Icon tìm kiếm -->
                    <li class="list-inline-item mb-0 ms-1">
                        <a href="javascript:void(0)" class="btn btn-icon btn-pills btn-primary" data-bs-toggle="offcanvas" data-bs-target="#offcanvasTop" aria-controls="offcanvasTop">
                            <i class="uil uil-search"></i>
                        </a>
                    </li>

                    <!-- Avatar + Dropdown -->
                    <li class="list-inline-item mb-0 ms-1">
                        <div class="dropdown dropdown-primary" style="position: relative;">
                            <c:choose>
                                <c:when test="${sessionScope.user != null}">
                                    <div class="avatar-toggle" onclick="toggleUserDropdown()" style="cursor: pointer;">
                                        <img src="<c:out value='${sessionScope.user.img == "default" ? "assets/images/avata.png" : sessionScope.user.img}'/>"
                                             class="avatar avatar-ex-small rounded-circle" alt="">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-primary p-1" onclick="window.location.href = 'login?action=login'">Login</button>
                                </c:otherwise>
                            </c:choose>

                            <!-- Dropdown menu -->
                            <div id="userDropdownMenu" class="dropdown-menu dd-menu dropdown-menu-end bg-white shadow border-0 mt-3 py-3"
                                 style="
                                 min-width: 200px;
                                 display: none;
                                 position: absolute;

                                 right: 0px;
                                 z-index: 999;
                                 border-radius: 8px;
                                 box-shadow: 0 4px 16px rgba(0,0,0,0.1);
                                 ">
                                <c:if test="${sessionScope.user != null}">
                                    <!-- Avatar + Tên -->
                                    <a class="dropdown-item d-flex align-items-center text-dark" href="#">
                                        <img src="<c:out value='${sessionScope.user.img == "default" ? "assets/images/avata.png" : sessionScope.user.img}'/>"
                                             class="avatar avatar-md-sm rounded-circle border shadow" alt="">
                                        <div class="flex-1 ms-2">
                                            <span class="d-block mb-1">${sessionScope.user.username}</span>
                                        </div>
                                    </a>

                                    <div class="dropdown-divider border-top"></div>

                                    <!-- Quản lý theo role -->
                                    <c:choose>
                                        <c:when test="${sessionScope.user.role == 1}">
                                            <a class="dropdown-item text-dark" href="dashboard?action=default"><i class="uil uil-setting me-1"></i> Quản lý</a>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 2}">
                                            <a class="dropdown-item text-dark" href="dashboard?action=default"><i class="uil uil-calendar-alt me-1"></i> Quản lý</a>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 4}">
                                            <a class="dropdown-item text-dark" href="profile?action=profile"><i class="uil uil-user-md me-1"></i> Quản lý</a>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 5}">
                                            <a class="dropdown-item text-dark" href="profile?action=profile"><i class="uil uil-user me-1"></i> Tài khoản của tôi</a>
                                             <a class="dropdown-item text-dark" href="bills"><i class="uil uil-user me-1"></i> Phiếu khám bệnh</a>
                                        </c:when>
                                    </c:choose>

                                    <!-- Logout -->
                                    <a class="dropdown-item text-dark" href="logout">
                                        <i class="uil uil-sign-out-alt me-1"></i> Đăng xuất
                                    </a>
                                    <c:if test="${sessionScope.user.role == 3}">
                                        <a class="dropdown-item text-dark" href="dashboard">
                                            <i class="uil uil-sign-out-alt me-1"></i> Quản Lý
                                        </a>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </li>
                </ul>


                <div id="navigation">

                    <ul class="navigation-menu nav-left nav-light">
                        <li><a href="home" class="sub-menu-item">Trang chủ</a></li>
                        <li><a href="doctor?action=all" class="sub-menu-item">Bác sĩ</a></li>

                        <!-- Dropdown for Dịch vụ y tế -->
                        <li class="dropdown1">
                            <a href="service" class="sub-menu-item">
                                Dịch vụ y tế <span style="font-size: 1em;">▼</span>
                            </a>
                            <ul class="dropdown-menu1">

                                <li><a href="specialty" class="sub-menu-item">Đặt khám chuyên khoa</a></li>
                                <li><a href="doctor" class="sub-menu-item">Đặt khám theo bác sĩ</a></li>
                                <li><a href="callVideoWithDoctor?action=all&categoryService_id=2" class="sub-menu-item">Gọi video với bác sĩ</a></li>
                                <li><a href="healthPackageService?action=all&categoryService_id=4" class="sub-menu-item">Gói khám sức khỏe</a></li>


                            </ul>
                        </li>

                        <li><a href="contact" class="sub-menu-item">Liên hệ</a></li>
                        <li><a href="blogs" class="sub-menu-item">Tin tức & chủ đề</a></li>
                    </ul>


                </div>
            </div>
        </header>
        <script>
            function toggleUserDropdown() {
                const dropdown = document.getElementById('userDropdownMenu');
                dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
            }

            document.addEventListener('click', function (e) {
                const dropdown = document.getElementById('userDropdownMenu');
                const toggle = document.querySelector('.avatar-toggle');
                if (dropdown && toggle && !dropdown.contains(e.target) && !toggle.contains(e.target)) {
                    dropdown.style.display = 'none';
                }
            });
        </script>

    </body>
</html>
