<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="top-header">
    <div class="header-bar d-flex justify-content-between border-bottom">
        <div class="d-flex align-items-center">
            <a href="home" class="logo-icon">
                <img src="assets/images/logo-icon.png" height="30" class="small" alt="">
                <span class="big">
                    <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
                    <img src="assets/images/logo-light.png" height="24" class="logo-dark-mode" alt="">
                </span>
            </a>
            <a id="close-sidebar" class="btn btn-icon btn-pills btn-soft-primary ms-2" href="#">
                <i class="uil uil-bars"></i>
            </a>
        </div>

        <ul class="list-unstyled mb-0">
            <li class="list-inline-item mb-0 ms-1">
                <div class="dropdown dropdown-primary" style="position: relative;">
                    <!-- Avatar Click -->
                    <div class="avatar-toggle" style="cursor: pointer;" onclick="toggleDropdown()">
                        <img src="<c:out value='${sessionScope.user.img == "default" ? "assets/images/avata.png" : sessionScope.user.img}'/>"
                             class="avatar avatar-ex-small rounded-circle" alt="">
                    </div>

                    <!-- Dropdown menu -->
                    <div id="userDropdown" class="dropdown-menu-custom">
                        <a class="dropdown-item d-flex align-items-center text-dark" href="user?action=profile">
                            <img src="<c:out value='${sessionScope.user.img == "default" ? "assets/images/avata.png" : sessionScope.user.img}'/>"
                                 class="avatar avatar-ex-small rounded-circle" alt="">
                            <div class="flex-1 ms-2">
                                <span class="d-block mb-1">${sessionScope.user.name}</span>
                                <small class="text-muted">${sessionScope.user.username}</small>
                            </div>
                        </a>
                        <div class="dropdown-divider border-top"></div>
                        <a class="dropdown-item text-dark" href="logout">
                            <span class="mb-0 d-inline-block me-1">
                                <i class="uil uil-sign-out-alt align-middle h6"></i>
                            </span>
                            Đăng xuất
                        </a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>


<!-- Script điều khiển dropdown -->

<style>
/* Custom dropdown styling */
.dropdown-menu-custom {
    position: absolute;
    top: calc(100% + 10px); /* Cách avatar 10px */
    right: 0;
    min-width: 220px;
    background-color: #fff;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    border-radius: 8px;
    padding: 10px 0;
    z-index: 999;
    display: none;
    opacity: 0;
    transform: translateY(10px);
    transition: all 0.3s ease;
    visibility: hidden;
}

/* Hiện dropdown khi active */
.dropdown-menu-custom.show {
    display: block;
    opacity: 1;
    transform: translateY(0);
    visibility: visible;
}

/* Avatar */
.avatar-toggle img {
    width: 35px;
    height: 35px;
    object-fit: cover;
    border: 2px solid #e5e5e5;
}
</style>

<script>
    function toggleDropdown() {
        const dropdown = document.getElementById('userDropdown');
        dropdown.classList.toggle('show');
    }

    // Ẩn dropdown khi click ra ngoài
    document.addEventListener('click', function (event) {
        const toggle = document.querySelector('.avatar-toggle');
        const dropdown = document.getElementById('userDropdown');

        if (!toggle.contains(event.target) && !dropdown.contains(event.target)) {
            dropdown.classList.remove('show');
        }
    });
</script>

