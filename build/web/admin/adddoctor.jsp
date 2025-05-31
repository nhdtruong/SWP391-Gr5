

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <style>
        body {
            background-color: #f0f8ff; /* xanh da trời nhạt */
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            color: #000000;
        }

        .card {
            background-color: #ffffff;
            border: 1px solid #cce5ff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.1);
            margin: 50px auto;
            max-width: 600px;
        }

        .card h2 {
            color: #007bff;
            margin-bottom: 20px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #aad4ff;
        }

        .form-control:focus {
            border-color: #66b3ff;
            box-shadow: 0 0 0 0.15rem rgba(102, 179, 255, 0.25);
            outline: none;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
        }

        .btn-primary {
            background-color: #3399ff;
            border: none;
            padding: 10px 20px;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn-primary:hover {
            background-color: #1a8cff;
        }

        .page-content {
            padding: 20px;
        }
    </style>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="card">
            <h2>Tạo mới tài khoản bác sĩ</h2>
            <form action="adddoctor" method="post">
                <div class="form-group">
                    <label for="username">Tên tài khoản</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Nhập tên tài khoản" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Nhập email" required>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                </div>
                <div class="text-center mt-3">
                    <button type="submit" class="btn btn-primary">Tiếp tục</button>
                </div>
            </form>
        </div>
                
 
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>


    </body>

</html>
