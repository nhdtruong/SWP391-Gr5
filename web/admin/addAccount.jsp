<%-- 
    Document   : addAccount
    Created on : 10 Jun 2025, 14:45:35
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
         <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>   
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container py-5" style="margin-top: 100px">
                <div class="row justify-content-center">
                    <div class="col-lg-6 col-md-8">
                        <div class="card shadow rounded border-0">
                            <div class="card-body p-4">
                                <h4 class="text-center mb-4">Thêm Tài Khoản Mới</h4>

                                <c:if test="${not empty requestScope.error}">
                                    <div class="alert alert-danger text-center">
                                        ${requestScope.error}
                                    </div>
                                </c:if>

                                <form action="addaccount" method="POST" onSubmit="document.getElementById('submit').disabled=true;">
                                    <div class="mb-3">
                                        <label class="form-label">Tên tài khoản <span class="text-danger">*</span></label>
                                        <input type="text" class="form-control" name="username" value="${requestScope.username}" oninput="CheckUserName(this);" required>
                                        <small class="text-danger">${requestScope.errorTK}</small>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Email <span class="text-danger">*</span></label>
                                        <input type="email" class="form-control" name="email" value="${requestScope.email}"  oninput="CheckEmail(this);" required>
                                        <small class="text-danger">${requestScope.errorEM}</small>
                                    </div>

                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                        <input type="password" class="form-control" name="password" value="${requestScope.password}"  oninput="CheckPassword(this);" required>
                                    </div>

                                    

                                    <div class="mb-4">
                                        <label class="form-label">Quyền người dùng <span class="text-danger">*</span></label>
                                        <select class="form-select" name="role_id" required>
                                            <option value="5" ${requestScope.role_id == '5' ? 'selected' : ''}>User</option>
                                            <option value="1" ${requestScope.role_id == '1' ? 'selected' : ''}>Admin</option>
                                            <option value="2" ${requestScope.role_id == '2' ? 'selected' : ''}>Manager</option>
                                            <option value="3" ${requestScope.role_id == '3' ? 'selected' : ''}>Staff</option>
                                        </select>
                                    </div>

                                    <div class="d-grid">
                                        <button type="submit" id="submit" class="btn btn-primary">Thêm Tài Khoản</button>
                                    </div>

                                    
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

                
            </main>
             <jsp:include page="../admin/layout/footer.jsp"/>
        </div>

         <style>
    html, body {
        height: 100%;
        margin: 0;
    }

    .page-wrapper {
        display: flex;
        flex-direction: column;
        min-height: 100vh; /* đảm bảo chiều cao toàn trang */
    }

    main.page-content {
        flex: 1; /* đẩy footer xuống cuối cùng */
    }

    footer {
        margin-top: auto;
    }
</style>

<script>
    
   function CheckPassword(text) {
    const value = text.value;

    // Nếu chứa bất kỳ dấu cách nào
    if (/\s/.test(value)) {
        text.setCustomValidity('Mật khẩu không được chứa dấu cách.');
    } else {
        text.setCustomValidity('');
    }

    text.reportValidity();
    return true;
}
    
    function CheckUserName(text) {
    const value = text.value;
 
    if (/^\s/.test(value)) {
        text.setCustomValidity('Tên đăng nhập không được bắt đầu bằng khoảng trống.');
    } else {
        const username = /^(?!.*\.\.)(?!.*\.$)[^\W][\w.]{0,29}$/;
        if (!username.test(value)) {
            text.setCustomValidity('Tên đăng nhập không hợp lệ.');
        } else {
            text.setCustomValidity('');
        }
    }
  
        text.reportValidity();
  

    return true;
}

function CheckEmail(text) {
    const value = text.value;
    if (/^\s/.test(value)) {
        text.setCustomValidity('Không được bắt đầu bằng khoảng trắng.');
    } else {
        // Biểu thức kiểm tra email hợp lệ
        const email = /^[\w.-]+@([\w-]+\.)+[\w-]{2,4}$/;
        if (!email.test(value.trim())) {
            text.setCustomValidity('Email không hợp lệ.');
        } else {
            text.setCustomValidity('');
        }
    }

    text.reportValidity();
    return true;
}




</script>
 

    </body>
</html>
