
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="layout/head.jsp"/>
    <body>
        <jsp:include page="layout/menu_white.jsp"/>
        <section class="bg-half-150 bg-light d-table w-100">
            <div class="container">
                <div class="row mt-5 justify-content-center">
                    <div class="col-12">
                        <div class="section-title text-center">
                            <h3 class="sub-title mb-4">Danh sách bác sĩ</h3>
                            <p class="para-desc mx-auto text-muted">Nếu bạn,người nhà của mình cần nhận được sự trợ giúp ngay lập tức, điều trị khẩn cấp hãy đặt lịch hẹn.</p>
                            <nav aria-label="breadcrumb" class="d-inline-block mt-3">
                                <ul class="breadcrumb bg-transparent mb-0">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <li class="breadcrumb-item"><a href="#">Doctors</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9 col-lg-9 row align-items-center">
                        <c:forEach items="${doctor}" var="d">
                            <div class="col-xl-4 col-lg-4 col-md-6 mt-2 pt-2 d-flex">
                                <div class="card team border-0 rounded shadow overflow-hidden d-flex flex-column w-100 h-100">
                                    <div style="display: none">${d.doctor_id}</div>

                                    <!-- Avatar -->
                                    <div class="team-person text-center mt-3">
                                        <img src="${d.img}" style="width: 80px; height: 80px; border-radius: 50%;" class="img-fluid" alt="">
                                    </div>

                                    <!-- Nội dung chính -->
                                    <div class="card-body text-center flex-grow-1 d-flex flex-column justify-content-center">
                                        <a href="#" class="title text-dark h5 d-block mb-0">${d.doctor_name}</a>
                                        <small class="text-muted speciality" style="min-height: 36px; display: block;">Chức vụ: ${d.position.getName()}</small>
                                        <p class="text-muted mb-0">${d.number_rate_star} feedbacks</p>
                                    </div>

                                    <!-- Button footer -->
                                    <div class="pt-2 pb-3 text-center">
                                        <button class="btn btn-soft-primary me-1" onclick="window.location.href = 'book?type=appointment&id=${d.doctor_id}'">Đặt lịch</button>
                                        <button class="btn btn-soft-primary" onclick="window.location.href = 'doctor?action=detail&id=${d.doctor_id}'">Chi tiết</button>  
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                        <div style="
                             text-align: center;
                             margin-top: 20px;
                             font-size: 18px;
                             font-weight: 500;
                             color: #333;
                             font-family: 'Segoe UI', sans-serif;
                             ">
                            <span style="
                                  cursor: pointer;
                                  padding: 5px 10px;
                                  border-radius: 5px;
                                  background-color: #f0f0f0;
                                  margin-right: 8px;
                                  transition: background-color 0.2s ease;
                                  ">&lt;</span>

                            <span style="font-weight: bold; color: #007bff;">1</span>/<span style="color: #555;">2</span>

                            <span style="
                                  cursor: pointer;
                                  padding: 5px 10px;
                                  border-radius: 5px;
                                  background-color: #f0f0f0;
                                  margin-left: 8px;
                                  transition: background-color 0.2s ease;
                                  ">&gt;</span>
                        </div>
                    </div>

                    <div class="col-lg-3 col-md-3 mt-3 pt-2">
                        <div class="card border-0 sidebar sticky-bar rounded shadow">
                            <div class="card-body">
                                <form action="doctor?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class="widget mb-4 pb-2">
                                        <h5 class="widget-title">Lọc</h5>
                                        <div class="row align-items-center">
                                            <div class="col-md-12">
                                                <label class="form-label">Giới tính</label>
                                            </div>
                                            <div class="col-md-12">
                                                <select name="gender" class="form-select">
                                                    <option <c:if test="${gender == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <option <c:if test="${gender == 'true'}"> selected </c:if> value="true">Nam</option>
                                                    <option <c:if test="${gender == 'false'}"> selected </c:if> value="false">Nữ</option>
                                                    </select>  
                                                </div>
                                            </div>
                                            <br>
                                            <div class="row align-items-center">
                                                <div class="col-md-12">
                                                    <label class="form-label">Chuyên môn</label>
                                                </div>
                                                <div class="col-md-12">
                                                    <select name="speciality" class="form-select">
                                                        <option <c:if test="${speciality == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${listDepartment}" var="s">
                                                        <option <c:if test="${speciality == s.id}"> selected </c:if> value="${s.id}">${s.department_name}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <br>
                                        <div class="widget mb-4 pb-2">
                                            <h5 class="widget-title">Sắp xếp</h5>
                                            <div class="row align-items-center">
                                                <div class="col-md-12">
                                                    <select name="SortType" onchange="Sort(this.value)" class="form-select">
                                                        <option <c:if test="${sort == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                        <option <c:if test="${sort == 'star'}"> selected </c:if> value="star">Star</option>
                                                        <option <c:if test="${sort == 'latest'}"> selected </c:if> value="latest">Mới nhất</option>
                                                        <option <c:if test="${sort == 'popular'}"> selected </c:if> value="popular">Phổ biến</option>
                                                        </select>  
                                                    </div>
                                                </div>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Lọc</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            <c:set var="page" value="${page}"/>
            <div class="row text-center">
                <div class="col-12 mt-4">
                    <div class="d-md-flex align-items-center text-center justify-content-between">
                        <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                            <c:forEach begin="${1}" end="${num}" var="i">
                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                </c:forEach>
                        </ul>
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
    <script>
        function Sort(type) {
            window.location.href = "doctor?action=sort&type=" + type;
        }
    </script>
</body>

</html>
