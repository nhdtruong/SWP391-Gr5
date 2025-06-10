

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <style>
            body {
                background-color: #f0f8ff;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                color: #000;
                margin: 0;
                padding: 0;
            }

            .container {
                display: flex;
                justify-content: center;
                padding: 50px 20px;
            }

            .card {
                background-color: #fff;
                border: 1px solid #cce5ff;
                border-radius: 10px;
                padding: 30px;
                box-shadow: 0 4px 12px rgba(0, 123, 255, 0.15);
                max-width: 1000px;
                width: 100%;
            }

            .card h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 30px;
            }

            .form-row {
                display: flex;
                gap: 40px;
                flex-wrap: wrap;
            }

            .form-column {
                flex: 1;
                min-width: 300px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                display: block;
                margin-bottom: 6px;
                font-weight: 500;
            }

            .form-control {
                width: 100%;
                padding: 10px;
                border-radius: 5px;
                border: 1px solid #aad4ff;
                background-color: #f8fbff;
                transition: all 0.3s ease;
            }

            .form-control:focus {
                border-color: #66b3ff;
                box-shadow: 0 0 0 0.15rem rgba(102, 179, 255, 0.25);
                outline: none;
            }

            .text-center {
                text-align: center;
            }

            .btn-primary {
                background-color: #3399ff;
                border: none;
                padding: 12px 30px;
                color: white;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-primary:hover {
                background-color: #1a8cff;
            }

            .mt-4 {
                margin-top: 2rem;
            }

            .error-text {
                color: red;
                font-size: 13px;
            }
            select.form-control {

                -webkit-appearance: auto;

            }
        </style>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content ">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container">
                    <div class="card">
                        <h2>Tạo tài khoản bác sĩ</h2>
                        <p class="text-danger">(*) Trường bắt buộc phải điền</p>
                        <form action="adddoctor?action" method="post">
                            <div class="form-row">

                                
                                <div class="form-column">
                                    <div class="form-group">
                                        <label for="username">Tên tài khoản <span class="text-danger">*</span><span class="error-text">${requestScope.errorTK}</span></label>
                                        <input type="text" class="form-control" id="username" name="username"
                                               placeholder="Nhập tên tài khoản"
                                               value="${requestScope.username}" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="email">Email <span class="text-danger">*</span><span class="error-text">${requestScope.errorEM}</span></label>
                                        <input type="email" class="form-control" id="email" name="email"
                                               placeholder="Nhập email"
                                               value="${requestScope.email}" required>

                                    </div>

                                    <div class="form-group">
                                        <label for="password">Mật khẩu<span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu" required>
                                    </div>
                                </div>

                     
                                <div class="form-column">
                                    <div class="form-group">
                                        <label for="doctorName">Tên bác sĩ <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" id="doctorName" name="doctorName"
                                               placeholder="Nhập tên bác sĩ"
                                               value="${requestScope.doctorName}" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="gender">Giới Tính</label>
                                        <select class="form-control" id="gender" name="gender" required>
                                            <option value="Nam" ${requestScope.gender == 'Nam' ? 'selected' : ''}>Nam</option>
                                            <option value="Nữ" ${requestScope.gender == 'Nữ' ? 'selected' : ''}>Nữ</option>
                                            <option value="Khác" ${requestScope.gender == 'Khác' ? 'selected' : ''}>Khác</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="department">Chuyên khoa <span class="text-danger">*</span></label>
                                        <select class="form-control" id="department" name="departmentId" required>

                                            <c:forEach var="d" items="${requestScope.Department}">
                                                <option value="${d.getId()}" ${requestScope.departmentId == d.getId() ? 'selected' : ''}>
                                                    ${d.getDepartment_name()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="position">Chức vụ <span class="text-danger">*</span></label>
                                        <select class="form-control" id="position" name="positionId" required>

                                            <c:forEach var="p" items="${requestScope.Position}">
                                                <option value="${p.getId()}" ${requestScope.positionId == p.getId() ? 'selected' : ''}>
                                                    ${p.getName()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="academicDegree">Học vị </label>
                                        <select class="form-control" id="academicDegree" name="academicDegreeId" required>

                                            <c:forEach var="ad" items="${requestScope.AcademicDegree}">
                                                <option value="${ad.getId()}" ${requestScope.academicDegreeId == ad.getId() ? 'selected' : ''}>
                                                    ${ad.getName()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>


                                    <div class="form-group">
                                        <label for="academicTitle">Học hàm</label>
                                        <select class="form-control" id="academicTitle" name="academicTitleId" required>

                                            <c:forEach var="at" items="${requestScope.AcademicTitle}">
                                                <option value="${at.getId()}" ${requestScope.academicTitleId == at.getId() ? 'selected' : ''}>
                                                    ${at.getName()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                        
                            <div class="text-center mt-4">
                                <button type="submit" class="btn btn-primary">Hoàn tất tạo tài khoản</button>
                            </div>
                        </form>
                    </div>
                </div>

                <c:if test="${success == 'success'}">
    <div class="alert alert-success text-center" role="alert"  
         style="position: fixed; 
                top: 50%; left: 50%; 
                transform: translate(-50%, -50%);
                z-index: 9999;
                width: 250px;      
                height: 250px;    
                font-size: 24px;    
                padding: 0;       
                font-weight: bold;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
                border-radius: 50%; 
                display: flex;     
                align-items: center;
                justify-content: center;
                text-align: center;">
         <br>Thêm bác sĩ<br>thành công!
    </div>

    <script>
        setTimeout(function () {
            window.location.href = 'doctormanager?action=all';
        }, 500); 
    </script>
</c:if>    
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>

        </div> 
              

        </div>


    </body>

</html>
