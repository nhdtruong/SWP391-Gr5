
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>

    <body>
        <style>
    .custom-warning-box {
        background-color: #fff3cd;
        color: #856404;
        padding: 15px;
        border-left: 5px solid #ffeeba;
        border-radius: 5px;
        margin-top: 15px;
    }
    .change-pass-btn {
        display: inline-block;
        margin-top: 10px;
        padding: 8px 16px;
        background-color: #ffc107;
        color: #212529;
        text-decoration: none;
        border-radius: 4px;
        font-weight: 500;
    }
    .change-pass-btn:hover {
        background-color: #e0a800;
        color: white;
    }
</style>
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <!-- Hero Start -->
        <div class="row">
          <div class="col-md-6 bg-home d-flex bg-light align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-8 ">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Đăng nhập</h4> 
                                <c:set var="cookie" value="${pageContext.request.cookies}"/>
                                <form action="login?action=checkLogin" method="POST" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled=true;">
                                    <p style="color: red; align-content: center;">
                                        ${requestScope.error}
                                    </p>
                                    <p style="color: blue; align-content: center;">
                                        ${requestScope.success}
                                    </p>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Tài khoản <span class="text-danger">*</span></label>
                                                <input type="text" value="${username}" class="form-control" placeholder="User name" name="username" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                                <input type="password"  value="" class="form-control" name="password" placeholder="Password" required="">
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="d-flex justify-content-between">
                                                <div class="mb-3">
                                                    <div class="form-check">
                                                        <input ${(cookie.rem.value eq 'ON')?"checked":""} class="form-check-input align-middle" type="checkbox" name="remember" id="remember-check">
                                                        <label class="form-check-label" for="remember-check">Lưu tài khoản</label>
                                                    </div>
                                                </div>
                                                <a href="changepassword?action=forgotpass" class="text-dark h6 mb-0">Quên mật khẩu ?</a>
                                            </div>
                                        </div>
                                        <div class="col-lg-12 mb-0">
                                            <div class="d-grid">
                                                <button class="btn btn-primary" id="submit">Đăng nhập</button>
                                            </div>
                                            <div class="text-center" style="margin-top: 10px">
                                             </div>
                                        </div>
                                         <c:if test="${acc.getStatus() == 2}">
                                             <div class="col-lg-12 custom-warning-box">
                                              <p class="warning-text">Tài khoản '${acc.getUsername()}' cần đổi mật khẩu!</p>
                                              <a href="changepassword?action=hdn&username=${acc.getUsername()}" class="change-pass-btn">Hành động ngay</a>
                                               </div>              
                                         </c:if>

                                                        
                                        <div class="col-12 text-center">
                                            <p class="mb-0 mt-3"><small class="text-dark me-2">Chưa có tài khoản ?</small> <a href="register?action=register" class="text-dark fw-bold">Đăng ký</a></p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12 my-3">
    
                 </div>                                        
            </div> 
           </div>
           <div   class="col-md-6 "style="background-image: url('assets/images/imgLogin/login.jpg')"> 
              <div class="style_bg_img_10x_7" >  
                 <div class="style_shape_1HA08"></div> 
              </div> 
           </div>                                        
        </div>
        

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <style>
            .google-login-btn {
    background-color: #ffffff;
    color: #444;
    border: 1px solid #ccc;
    font-weight: 500;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 10px;
    margin-top : 10px;
}



     .style_bg_img_10x_7  { height: 100%;
    width: 100%;
    position: absolute;
    top: 0;
    right: 0;
    background-size: cover;
    background-position: 50%;
       } 
       .style_shape_1HA08 {
      background-color: #f1eff2;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 0;
    width: 50px;
    -webkit-clip-path: polygon(0 0,100% 0,75% 100%,0 100%);
    clip-path: polygon(0 0,100% 0,5% 100%,0 100%);
       }
        </style>
            
    </body>

</html>