
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>

        <div class="row">
             <div class="col-md-6 bg-home d-flex bg-light align-items-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5 col-md-8">
                        <div class="card login-page bg-white shadow mt-4 rounded border-0">
                            <div class="card-body">
                                <h4 class="text-center">Đăng ký</h4>  
                                <p style="color: red; align-content: center;">
                                    ${requestScope.error}
                                </p>
                                <form action="register" method="POST" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled=true;">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="mb-3">                                               
                                                <label class="form-label">Tên tài khoản <span class="text-danger">*</span> <span style="color: red; align-content: center;">${requestScope.errorTK}</span></label>
                                                <input value="${requestScope.username}" type="text"  oninput="CheckUserName(this);" class="form-control" name="username" required="">
                                            </div>
                                        </div>
                                       
                                    </div>
                                    
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Email <span class="text-danger">*</span> <span style="color: red; align-content: center;">${requestScope.errorEM}</span></label> 
                                            <input value="${requestScope.email}" type="email"  oninput="CheckEmail(this);" class="form-control" name="email" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3">
                                            <label class="form-label">Mật khẩu <span class="text-danger">*</span></label>
                                            <input value="${requestScope.password}" type="password"    oninput="CheckPassword(this);" id="password"  class="form-control" name="password" required="">
                                             
                                        </div>
                                            
                                    </div>
                                    <div class="col-md-12">
                                        <div class="mb-3" style="position: relative">
                                            <label class="form-label">Nhập lại mật khẩu <span class="text-danger">*</span></label>
                                            <input value="${requestScope.repassword}" type="password" oninput="CheckRePassword(this);" class="form-control" name="repassword" required="">
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="d-grid">
                                            <button class="btn btn-primary" id="submit">Đăng ký</button>
                                        </div>
                                    </div>
                                    <div class="mx-auto">
                                        <p class="mb-0 mt-3"><small class="text-dark me-2">Đã có tài khoản ?</small> <a href="login?action=login" class="text-dark fw-bold">Đăng nhập</a></p>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                                
                </div>
                                                        
            </div>
                                        
        </div>
                   <div   class="col-md-6 "style="background-image: url('assets/images/imgLogin/login.jpg')"> 
              <div class="style_bg_img_10x_7" >  
                 <div class="style_shape_1HA08"></div> 
              </div> 
              </div>                       
        </div>                                
         <style>
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
           
        
        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
    </body>

</html>
