<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="sidebar" class="sidebar-wrapper">
    <div class="sidebar-content" data-simplebar style="height: calc(100% - 60px);">
        <div class="sidebar-brand">
            <a href="home">
                <img src="assets/images/logo-dark.png" height="24" class="logo-light-mode" alt="">
            </a>
        </div>

        <ul class="sidebar-menu pt-3">
            <c:if test="${sessionScope.user.getRole()== 1}">
                <li><a href="dashboard?action=default"><i class="uil uil-dashboard me-2 d-inline-block"></i>Bảng điều khiển</a></li>
                <li><a href="accountmanager?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Tài khoản</a></li>
                <li><a href="doctormanager?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bác sĩ</a></li>
                <li><a href="patientmanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bệnh nhân</a></li>
                <li><a href="servicemanager?action=all"><i class="uil uil-apps me-2 d-inline-block"></i>Quản lý Dịch vụ</a></li>
                <li><a href="appointmentmanage?action=all"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Quản lý lịch hẹn</a></li>
                <li><a href="reservationmanager?action=all"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Quản lý đặt lịch dịch vụ</a></li>
                <li><a href="blogmanage?action=all"><i class="uil uil-flip-h me-2 d-inline-block"></i>Quản lý blog</a></li>
            </c:if>
                
            <c:if test="${sessionScope.user.getRole()== 4}">
                <li><a href="doctormanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bác sĩ</a></li>
                <li><a href="patientmanage?action=all"><i class="uil uil-user me-2 d-inline-block"></i>Quản lý Bệnh nhân</a></li>
                <li><a href="servicemanage?action=all"><i class="uil uil-apps me-2 d-inline-block"></i>Quản lý Dịch vụ</a></li>
                <li><a href="blogmanage?action=all"><i class="uil uil-flip-h me-2 d-inline-block"></i>Quản lý blog</a></li>
            </c:if>
                
            <c:if test="${sessionScope.user.getRole()== 2}">
                <li><a href="appointmentmanage?action=all"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Quản lý lịch hẹn</a></li>
                <li><a href="reservationmanage?action=all"><i class="uil uil-stethoscope me-2 d-inline-block"></i>Quản lý đặt lịch dịch vụ</a></li>
            </c:if>
        </ul>
    </div>
</nav>
