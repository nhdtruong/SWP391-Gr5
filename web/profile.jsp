
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
       
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-dashboard">
            <div class="container">
                <div class="row justify-content-center">
                    <jsp:include page="layout/profileMenu.jsp"/>
                    <div class="col-xl-8 col-lg-8 col-md-7 mt-4 pt-2 mt-sm-0 pt-sm-0" style="padding:0 30px">
                        <h3 class="mb-0"></h3>
                        <div class="rounded shadow " style="margin-top: 0px">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0" style="padding-bottom: 10px">Thông tin tài khoản</h5>

                                <p style="color: black;">
                                    <strong>Tên tài khoản:</strong> 
                                    <span style="color: blue; padding-left: 10px">${sessionScope.user.getUsername()}</span>
                                </p>

                                <p>
                                    <strong>Gmail:</strong> 
                                    <span style="color: blue; padding-left: 10px">${sessionScope.user.getEmail()}</span>
                                </p>
                            </div>
                            <div class="p-4">
                                <form action="user?action=update_image" method="POST" enctype="multipart/form-data">
                                    <h5 class="mb-0">Chỉnh sửa thông tin :</h5>
                                    <div>
                                        <p class="text-muted">Cập nhật ảnh đại diện.</p>
                                        <div id="myfileupload">
                                            <input type="file" name="image" id="uploadfile" name="ImageUpload" onchange="readURL(this);" />
                                        </div>
                                        <div id="thumbbox">
                                            <img class="rounded" height="20%" width="30%" alt="Thumb image" id="thumbimage" style="display: none" />
                                            <a class="removeimg" href="javascript:"></a>
                                        </div>
                                        <div id="boxchoice">
                                            <a href="javascript:" class="Choicefile"><i class="fas fa-cloud-upload-alt"></i> Chọn ảnh</a>
                                            <p style="clear:both"></p>
                                            <input type="submit" id="submit" style="display: none" name="send" class="Update btn btn-primary"
                                                   value="Cập nhật">
                                            <p style="clear:both"></p>
                                        </div> 
                                    </div>
                                </form>
                                <form action="updateprofile?action=updateInfor" method="POST">
                                    <label class="form-label"><span style="color: red; align-content: center;">${requestScope.error}</span></label>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Tên tài khoản  <span style="color: red; align-content: center;">${requestScope.errorTK}</span></label>
                                                <input name="username" value="${requestScope.username}" id="name" type="text" class="form-control"  >
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="mb-3">
                                                <label class="form-label">Email  <span style="color: red; align-content: center;">${requestScope.errorEM}</span></label>
                                                <input name="email"  value="${requestScope.email}" id="email" type="email" class="form-control"  >
                                            </div>
                                        </div>

                                       
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <input type="submit" id="submit" name="send" class="btn btn-primary" value="Lưu">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <div class="rounded shadow mt-4">
                            <div class="p-4 border-bottom">
                                <h5 class="mb-0">Đổi mật khẩu :</h5>
                                <p style="color: red; align-content: center;">

                                </p>
                                <p style="color: blue; align-content: center;">

                                </p>
                            </div>

                            <div class="p-4">
                                <form action="updateprofile?action=changePassword" method="POST">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Mật khẩu cũ <span class="text-danger">*</span> <span style="color: red; align-content: center;">${requestScope.errorMK}</span></label> 
                                                <input value="${inputOldPassword}"type="password"  name="inputOldPassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                               <label class="form-label">Mật khẩu mới <span class="text-danger">*</span> </label>
                                                <input value="${newPassword}" oninvalid="CheckPassword(this);" oninput="CheckPassword(this);" id="password" type="password" name="newPassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12">
                                            <div class="mb-3">
                                                <label class="form-label">Nhập lại mật khẩu mới <span class="text-danger">*</span> </label>
                                                <input value="${reNewPassword}" oninvalid="CheckRePassword(this);" oninput="CheckRePassword(this);"type="password" name="reNewPassword" class="form-control" required="">
                                            </div>
                                        </div><!--end col-->

                                        <div class="col-lg-12 mt-2 mb-0">
                                            <button class="btn btn-primary">Thay đổi</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="layout/footer.jsp"/>
        <a href="#" onclick="topFunction()" id="back-to-top" class="btn btn-icon btn-pills btn-primary back-to-top"><i data-feather="arrow-up" class="icons"></i></a>
            <jsp:include page="layout/search.jsp"/>
            <jsp:include page="layout/facebookchat.jsp"/>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
         <style>
            .Choicefile{
                display: block;
                background: #396CF0;
                border: 1px solid #fff;
                color: #fff;
                width: 150px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                padding: 5px 0px;
                border-radius: 5px;
                font-weight: 500;
                align-items: center;
                justify-content: center;
            }

            .Choicefile:hover {
                text-decoration: none;
                color: white;
            }

            #uploadfile,
            .removeimg {
                display: none;
            }

            #thumbbox {
                position: relative;
                width: 100%;
                margin-bottom: 20px;
            }

            .removeimg {
                height: 25px;
                position: absolute;
                background-repeat: no-repeat;
                top: 5px;
                left: 5px;
                background-size: 25px;
                width: 25px;
                border-radius: 50%;

            }

            .removeimg::before {
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                content: '';
                border: 1px solid red;
                background: red;
                text-align: center;
                display: block;
                margin-top: 11px;
                transform: rotate(45deg);
            }

            .removeimg::after {
                content: '';
                background: red;
                border: 1px solid red;
                text-align: center;
                display: block;
                transform: rotate(-45deg);
                margin-top: -2px;
            }
        </style>
        <script>
            function readURL(input, thumbimage) {
                if (input.files && input.files[0]) { //Sử dụng  cho Firefox - chrome
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("#thumbimage").attr('src', e.target.result);
                    }
                    reader.readAsDataURL(input.files[0]);
                } else { // Sử dụng cho IE
                    $("#thumbimage").attr('src', input.value);

                }
                $("#thumbimage").show();
                $('.filename').text($("#uploadfile").val());
                $(".Choicefile").hide();
                $(".Update").show();
                $(".removeimg").show();
            }
            $(document).ready(function () {
                $(".Choicefile").bind('click', function () {
                    $("#uploadfile").click();

                });
                $(".removeimg").click(function () {
                    $("#thumbimage").attr('src', '').hide();
                    $("#myfileupload").html('<input type="file" id="uploadfile"  onchange="readURL(this);" />');
                    $(".removeimg").hide();
                    $(".Choicefile").show();
                    $(".Update").hide();
                    $(".filename").text("");
                });
            })
        </script>
    </body>

</html>
