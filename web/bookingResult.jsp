<%-- 
    Document   : bookingSuccess
    Created on : 24 Jun 2025, 03:41:44
    Author     : DELL
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${result == 'true'}" >ĐẶt lịch hẹn thành công <a href="home">Home</a> </c:if>
        <c:if test="${result == 'false'}" >ĐẶt lịch thất bại <a href="home">Home</a> </c:if>
        
    </body>
</html>
