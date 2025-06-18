<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
     <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }

        .otp-container {
            max-width: 400px;
            margin: 100px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 30px 20px;
            text-align: center;
        }

        .otp-container h2 {
            margin-bottom: 10px;
            color: #007BFF;
        }

        .otp-container p {
            color: #666;
            font-size: 14px;
            margin-bottom: 20px;
        }

        .otp-input {
            font-size: 24px;
            letter-spacing: 10px;
            padding: 10px;
            width: 100%;
            text-align: center;
            border: 2px solid #ccc;
            border-radius: 5px;
            outline: none;
        }

        .otp-button {
            margin-top: 20px;
            padding: 10px 0;
            width: 100%;
            background-color: #007BFF;
            border: none;
            color: white;
            font-weight: bold;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        .otp-button:hover {
            background-color: #0056b3;
        }

        .message {
            margin-top: 15px;
            font-size: 14px;
        }

        .error1 {
            color: red;
            margin-top: 20px;
        }

       
    </style>
     <jsp:include page="layout/head.jsp"/>
    <body>
      <div class="page-wrapper d-flex flex-column min-vh-100">
        
        <jsp:include page="layout/menu_white.jsp"/>

        <main class="flex-grow-1">
            <c:if test="${requestScope.type == null}">
                <div class="otp-container">
                    <h2>Xác minh OTP</h2>
                    <p>Vui lòng nhập mã gồm 6 số đã gửi về email của bạn null</p>

                    <form action="accepcode" method="post">
                        <input type="text" name="otp" maxlength="6" required 
                               class="otp-input" pattern="\d{6}" placeholder="______"  />
                        <button type="submit" class="otp-button">Xác nhận</button>
                    </form>

                    <c:if test="${requestScope.error != null}">
                        <p class="error1" style="color: red">${requestScope.error}</p>
                    </c:if>
                </div>
            </c:if>

            <c:if test="${requestScope.type == '1'}">
                <div class="otp-container">
                    <h2>Xác minh OTP</h2>
                    <p>Vui lòng nhập mã gồm 6 số đã gửi về email của bạn 1</p>

                    <form action="accepcodeChangePass" method="post">
                        <input type="text" name="otp" maxlength="6" required 
                               class="otp-input" pattern="\d{6}" placeholder="______"  />
                        <button type="submit" class="otp-button">Xác nhận</button>
                    </form>

                    <c:if test="${requestScope.error != null}">
                        <p class="error1" style="color: red">${requestScope.error}</p>
                    </c:if>
                </div>
            </c:if>
        </main>

        <jsp:include page="layout/footer.jsp"/>
    </div>

    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/feather.min.js"></script>
    <script src="assets/js/app.js"></script>
    </body>
</html>
