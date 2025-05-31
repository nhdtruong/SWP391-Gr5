<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
     <jsp:include page="layout/head.jsp"/>
    <body>
         <style>
            .success-container {
                max-width: 500px;
                margin: 100px auto;
                padding: 30px;
                text-align: center;
                background-color: #f2f2f2;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            }

            .success-container h2 {
                color: #28a745;
                margin-bottom: 20px;
            }

            .success-container p {
                font-size: 16px;
                color: #333;
                margin-bottom: 30px;
            }

            .login-button {
                background-color: #007bff;
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 5px;
                font-size: 16px;
                cursor: pointer;
                text-decoration: none;
                transition: background-color 0.3s ease;
            }

            .login-button:hover {
                background-color: #0056b3;
            }
        </style>
        <jsp:include page="layout/menu_white.jsp"/>
        
        
       <div class="success-container">
            <h2>🎉 Đăng ký thành công!</h2>
            <p>Tài khoản của bạn đã được tạo.Đăng nhập ngay để sử dụng dịch vụ.</p>
            <a href="login?action=login" class="login-button">Đăng nhập ngay</a>
        </div>

         
        <jsp:include page="layout/footer.jsp"/>
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>
</html>