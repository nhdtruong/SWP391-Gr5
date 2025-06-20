

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>
            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>
                <div class="container-fluid">
                    <div class="layout-specing">
                        <div class="row">
                            <div class="col-md-3 row">
                                <div class="col-md-4">
                                    <h5 class="mb-0">Doctor Schedule</h5>
                                </div>
                                <div class="col-md-8">
                                    <div class="search-bar p-0 d-lg-block ms-2">                                                        
                                        <div id="search" class="menu-search mb-0">
                                            <form action="doctormanager?action=search" method="POST" id="searchform" class="searchform">
                                                <div>
                                                    <input type="text" class="form-control border rounded-pill" name="text" id="s" placeholder="Tìm kiếm bác sĩ...">
                                                    <input type="submit" id="searchsubmit" value="Search">
                                                </div>
                                            </form>
                                        </div>
                                    </div> 
                                </div>
                            </div>
                            <div class="col-md-8 ">
                                <form class="row" action="doctorschedule?action=filter" method="POST" onSubmit="document.getElementById('submit').disabled = true;">
                                    <div class=" justify-content-md-end row">
                                        
                                          
                                        <div class="col-md-4 row align-items-center">
                                            <div class="col-md-5" style="text-align: end">
                                                <label  class="form-label">Chuyên khoa</label>
                                            </div>
                                            <div class="col-md-7">
                                                <select name="department_id" class="form-select">
                                                    <option <c:if test="${department_id == 'all'}"> selected </c:if> value="all">Tất cả</option>
                                                    <c:forEach items="${department}" var="de">
                                                        <option <c:if test="${de.getId().toString() == department_id}"> selected </c:if> value="${de.getId()}">${de.getDepartment_name()}</option>
                                                    </c:forEach>
                                                </select>  
                                            </div>
                                        </div>
                                        <div class="col-md-1 md-0">
                                            <button type="submit" class="btn btn-primary">Lọc</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-1">
                                <a href="adddoctorschedule" type="button"class="btn btn-info">Add+</a>         
                            </div>                         
                        </div>

                        <div class="row">
                            <div class="col-12 mt-4">
                                <div class="table-responsive bg-white shadow rounded">
                                    <table class="table mb-0 table-center" style="text-align: center">
                                        <thead>
                                            <tr>                          
                                                <th class="border-bottom p-3" >STT</th>
                                                <th class="border-bottom p-3" >Mã Bác sĩ</th>                                        
                                                <th class="border-bottom p-3" >Họ tên</th>
                                                <th class="border-bottom p-3" >Chuyên khoa</th>
                                                <th class="border-bottom p-3" >Lịch</th>
                                                <th class="border-bottom p-3" ></th>
                                                <th class="border-bottom p-3" ></th>
                                                <th class="border-bottom p-3" ></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${empty doctor}">
                                                <tr>
                                                    <td colspan="6" class="text-center text-danger p-3">
                                                        Không có bác sĩ được tìm thấy.
                                                    </td>
                                                </tr>
                                            </c:if>
                                        <c:set var="i" value="${start + 1}"/>   
                                            <c:forEach items="${doctor}" var="d" >
                                                <tr>                                                 
                                                    <td class="p-3">${i}</td>
                                                    <td class="p-3">${d.getUsername()}</td>
                                                    <td class="p-3">${d.getDoctor_name()}</td>
                                                    <td class="p-3">${d.getDepartment().getDepartment_name()}</td>
                                                    <c:if test="${d.getStatus() == 1}">
                                                        <td class="p-3" style="color: green">Active</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() == 0}">
                                                        <td class="p-3" style="color: red">Disable</td>
                                                    </c:if>
                                                    <c:if test="${d.getStatus() ==  2}">
                                                        <td class="p-3" style="color: yellow">Wait</td>
                                                    </c:if>   
                                                    <td class="p-3">
                                                        <a href="doctorScheduleDetail?doctorId=${d.getDoctor_id()}&doctorName=${d.getDoctor_name()}" class="btn btn-info">
                                                          
                                                            Detail
                                                        </a>
                                                    </td>

                                                    <td class="p-3">
                                                        <a href="updateDoctorSchedule?action=updateSchedule&doctorId=${d.getDoctor_id()}" class="btn btn-primary">Update</a>
                                                    </td>

                                                    <td class="p-3">
                                                        <a href="#" class="btn btn-danger"
                                                           onclick="openDeleteModal('${d.getDoctor_id()}', '${d.getDoctor_name()}')">
                                                            Delete
                                                        </a>
                                                    </td>


                                                </tr>
                                                <c:set var="i" value="${i + 1}"/>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <c:set var="page" value="${page}"/>
                        <div class="row text-center">
                            <div class="col-12 mt-4">
                                <div class="d-md-flex align-items-center text-center" style="justify-content: center">
                                    <ul class="pagination justify-content-center mb-0 mt-3 mt-sm-0">
                                        <c:choose>
                                            <c:when test="${page < numPageDisplay  && num < numPageDisplay  }">
                                                <c:forEach begin="${1}" end="${num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                    </c:forEach>

                                            </c:when>

                                            <c:when test="${page < numPageDisplay  && num > numPageDisplay  }">
                                                <c:forEach begin="${1}" end="${numPageDisplay}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                    </c:forEach>

                                            </c:when>


                                            <c:when test="${(num - page + 1 ) <= numPageDisplay && num >= numPageDisplay}">
                                                <li class="page-item"><a class="page-link" href="${url}&page=${page-1}">Prev</a></li>
                                                    <c:forEach begin="${num - (numPageDisplay-1)}" end="${num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                    </c:forEach>

                                            </c:when>   


                                            <c:otherwise >
                                                <li class="page-item"><a class="page-link" href="${url}&page=${page-1}">Prev</a></li>
                                                    <c:forEach begin="${page-(numPageDisplay/2)+1}" end="${page+(numPageDisplay/2)}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="${url}&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item"><a class="page-link" href="${url}&page=${page+1}">Next</a></li>  
                                                </c:otherwise>      

                                        </c:choose>


                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

             
             



                <c:if test="${deleteDoctorSuccess == 'deleteDoctorSuccess'}">
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
                        ✅<br>Xóa bác sĩ<br>thành công!
                    </div>

                    <script>
                        setTimeout(function () {
                            window.location.href = 'doctormanager?action=all';
                        }, 500);
                    </script>
                </c:if> 
                <script>
                 
                 

                </script>






                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
       
    </body>

</html>
