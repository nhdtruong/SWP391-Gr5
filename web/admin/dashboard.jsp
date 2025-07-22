
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ page import="java.util.*, java.util.Map" %>
<%
    // Lấy danh sách revenueList từ request
    List<Map<String, Object>> revenueList = (List<Map<String, Object>>) request.getAttribute("revenueList");
    if (revenueList == null) {
        revenueList = new ArrayList<>();
    }
%>

<%@ page import="DTOStatic.StaticDepartment" %>
<%
    List<StaticDepartment> list = (List<StaticDepartment>) request.getAttribute("staticDepartments");
%>


<%@ page import="DTOStatic.StaticDepartment" %>
<%
    List<StaticDepartment> list2 = (List<StaticDepartment>) request.getAttribute("staticDepartmentss");
%>
<!DOCTYPE html>
<html lang="en">
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>

    <script src="https://cdn.amcharts.com/lib/4/core.js"></script>
    <script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
    <script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>
    <jsp:include page="../admin/layout/adminhead.jsp"/>
    <body>
        <div class="page-wrapper doctris-theme toggled">
            <jsp:include page="../admin/layout/menu.jsp"/>      

            <main class="page-content bg-light">
                <jsp:include page="../admin/layout/headmenu.jsp"/>

                <div class="container-fluid">
                    <div class="layout-specing">
                        <h5 class="mb-0">Dashboard</h5>
                        <div class="row">
                            <div class="col-xl-6 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-bed h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${sobenhnhan}</h5>
                                            <p class="text-muted mb-0">Bệnh nhân</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-6 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-social-distancing h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${sobacsi}</h5>
                                            <p class="text-muted mb-0">Bác sĩ</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-file-medical-alt h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0"><fmt:formatNumber pattern="#,###,###,###" value="${total_revenue}"/> đ</h5>
                                            <p class="text-muted mb-0">Doanh thu</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-medkit h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${total_user}</h5>
                                            <p class="text-muted mb-0">Người dùng</p>
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <div class="col-xl-4 col-lg-4 col-md-4 mt-4">
                                <div class="card features feature-primary rounded border-0 shadow p-4">
                                    <div class="d-flex align-items-center">
                                        <div class="icon text-center rounded-md">
                                            <i class="uil uil-medkit h3 mb-0"></i>
                                        </div>
                                        <div class="flex-1 ms-2">
                                            <h5 class="mb-0">${total_Appointment}</h5>
                                            <p class="text-muted mb-0">Appointments</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-xl-12 col-lg-12 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Thống kê số Doanh thu</h6>
                                        <div class="mb-0 position-relative">
                                        </div>
                                    </div>
                                    <div id="chartdiv" style="width: 100%; height: 500px;"></div>
                                </div>
                            </div>

                            <!--                            <div class="col-xl-4 col-lg-5 mt-4">
                                                            <div class="card shadow border-0 p-4">
                                                                <div class="d-flex justify-content-between align-items-center mb-3">
                                                                    <h6 class="align-items-center mb-0">Thống kê lịch hẹn</h6>
                                                                    <div class="mb-0 position-relative">
                                                                    </div>
                                                                </div>
                                                                <div id="chartdiv2" style="width: 100%; height: 500px;"></div>
                                                            </div>
                                                        </div>-->
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Thống kê bác sỹ theo chuyên khoa</h6>
                                        <div class="mb-0 position-relative">
                                        </div>
                                    </div>
                                    <div id="chartdiv3" style="width: 100%; height: 500px;"></div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xl-12 col-lg-12 mt-4">
                                <div class="card shadow border-0 p-4">
                                    <div class="d-flex justify-content-between align-items-center mb-3">
                                        <h6 class="align-items-center mb-0">Thống kê bác sỹ theo dịch vụ</h6>
                                        <div class="mb-0 position-relative">
                                        </div>
                                    </div>
                                    <div id="chartdiv4" style="width: 100%; height: 500px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../admin/layout/footer.jsp"/>
            </main>
        </div>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/simplebar.min.js"></script>
        <script src="assets/js/apexcharts.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
            var options1 = {
                series: [{
                        name: 'Appointment',
                        data: [<c:forEach items="${appointment7day}" var="a">${a.count},</c:forEach>]
                    }, {
                        name: 'Reservation',
                        data: [<c:forEach items="${reservation7day}" var="r">${r.count},</c:forEach>]
                    }],
                chart: {
                    type: 'bar',
                    height: 350,
                    stacked: true,
                    toolbar: {
                        show: true
                    },
                    zoom: {
                        enabled: true
                    }
                },
                responsive: [{
                        breakpoint: 480,
                        options: {
                            legend: {
                                position: 'bottom',
                                offsetX: -10,
                                offsetY: 0
                            }
                        }
                    }],
                plotOptions: {
                    bar: {
                        horizontal: false,
                        borderRadius: 10
                    },
                },
                xaxis: {
                    type: 'text',
                            categories: [<c:forEach items="${appointment7day}" var="a">'<fmt:formatDate pattern="dd/MM/yyyy" value="${a.date}"/>',</c:forEach>
                            ],
                },
                legend: {
                    position: 'right',
                    offsetY: 40
                },
                fill: {
                    opacity: 1
                }
            };
            var chart1 = new ApexCharts(document.querySelector("#dashboard"), options1);
            chart1.render();
            </script>

            <script type="text/javascript">
                function Astatistic(type) {
                    window.location.href = "dashboard?action=statistic&atype=" + type + "&rtype=${sessionScope.rtype}";
                }
        </script>

        <script type="text/javascript">
            function Rstatistic(type) {
                window.location.href = "dashboard?action=statistic&rtype=" + type + "&atype=${sessionScope.atype}";
            }
        </script>

        <script>
            var options2 = {
                series: [${Revenueappointment}, ${Revenuereservation}],
                chart: {
                    width: 450,
                    type: 'pie',
                },

                labels: ['Appointment', 'Reservation'],
                responsive: [{
                        breakpoint: 600,
                        options: {
                            chart: {
                                width: 500
                            },
                            legend: {
                                position: 'bottom'
                            },
                        }
                    }]
            };
            var chart2 = new ApexCharts(document.querySelector("#department"), options2);
            chart2.render();
        </script>
        <script>
            // Chuyển dữ liệu từ JSP sang JS
            var chartData = [
            <% for (int i = 0; i < revenueList.size(); i++) {
            Map<String,Object> item = revenueList.get(i);
            int month = (Integer)item.get("month");
            double revenue = (Double)item.get("revenue");
            %>
            { "month": "<%=month%>", "revenue": <%=revenue%> }<%= (i < revenueList.size()-1) ? "," : "" %>
            <% } %>
            ];
        </script>

        <!-- Div chứa biểu đồ -->


        <script>
            am4core.ready(function () {
                // Giao diện animation
                am4core.useTheme(am4themes_animated);

                // Tạo chart
                var chart = am4core.create("chartdiv", am4charts.XYChart);
                chart.data = chartData;

                // Trục X: tháng
                var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
                categoryAxis.dataFields.category = "month";
                categoryAxis.title.text = "Tháng";

                // Trục Y: doanh thu
                var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
                valueAxis.title.text = "Doanh thu";

                // Series cột
                var series = chart.series.push(new am4charts.ColumnSeries());
                series.dataFields.valueY = "revenue";
                series.dataFields.categoryX = "month";
                series.name = "Doanh thu";
                series.columns.template.tooltipText = "Tháng {categoryX}: [bold]{valueY}[/]";
                series.columns.template.fillOpacity = 0.8;

                var columnTemplate = series.columns.template;
                columnTemplate.strokeWidth = 2;
                columnTemplate.strokeOpacity = 1;

            });
        </script>


        <script>
            // Tạo mảng data từ JSP
            var chartData4 = [
            <% for (int i = 0; i < list.size(); i++) { %>
            { category: "<%= list.get(i).getDepartment_name() %>", value: <%= list.get(i).getNumber() %> }<%= (i < list.size()-1 ? "," : "") %>
            <% } %>
            ];

            console.log(chartData4);

            am5.ready(function () {
                // Tạo root
                var root = am5.Root.new("chartdiv3");

                // Thêm theme
                root.setThemes([am5themes_Animated.new(root)]);

                // Tạo chart XY
                var chart = root.container.children.push(am5xy.XYChart.new(root, {
                    layout: root.verticalLayout
                }));

                // Trục X
                var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
                    categoryField: "category",
                    renderer: am5xy.AxisRendererX.new(root, {minGridDistance: 30})
                }));
                xAxis.get("renderer").labels.template.setAll({
                    rotation: -45, // xoay 45 độ (âm xoay về trái, dương xoay về phải)
                    centerY: am5.p50, // căn giữa theo trục Y
                    centerX: am5.p100, // căn phải
                    fontStyle: "italic"     // in nghiêng
                });
                xAxis.data.setAll(chartData4);

                // Trục Y
                var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
                    renderer: am5xy.AxisRendererY.new(root, {})
                }));

                // Series cột
                var series = chart.series.push(am5xy.ColumnSeries.new(root, {
                    name: "Số bác sĩ",
                    xAxis: xAxis,
                    yAxis: yAxis,
                    valueYField: "value",
                    categoryXField: "category"
                }));

                // 👉 SỬA Ở ĐÂY: dùng chartData4
                series.data.setAll(chartData4);



                // Thêm bullet label hiển thị giá trị trên cột
                series.bullets.push(function () {
                    return am5.Bullet.new(root, {
                        locationY: 0, // vị trí theo chiều Y (0 = đỉnh cột, 1 = đáy)
                        sprite: am5.Label.new(root, {
                            text: "{valueY}",
                            populateText: true,
                            centerX: am5.p50,
                            centerY: am5.p100,
                            dy: -10,
                            fontSize: 14,
                            fontWeight: "bold",
                            fill: am5.color(0x000000)
                        })
                    });
                });


                series.appear(1000);
                chart.appear(1000, 100);
            });
        </script>
        
        <script>
            // Tạo mảng data từ JSP
            var chartData5 = [
            <% for (int i = 0; i < list2.size(); i++) { %>
            { category: "<%= list2.get(i).getDepartment_name() %>", value: <%= list2.get(i).getNumber() %> }<%= (i < list2.size()-1 ? "," : "") %>
            <% } %>
            ];

            console.log(chartData5);

            am5.ready(function () {
                // Tạo root
                var root = am5.Root.new("chartdiv4");

                // Thêm theme
                root.setThemes([am5themes_Animated.new(root)]);

                // Tạo chart XY
                var chart = root.container.children.push(am5xy.XYChart.new(root, {
                    layout: root.verticalLayout
                }));

                // Trục X
                var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
                    categoryField: "category",
                    renderer: am5xy.AxisRendererX.new(root, {minGridDistance: 30})
                }));
                xAxis.get("renderer").labels.template.setAll({
                    rotation: -45, // xoay 45 độ (âm xoay về trái, dương xoay về phải)
                    centerY: am5.p50, // căn giữa theo trục Y
                    centerX: am5.p100, // căn phải
                    fontStyle: "italic"     // in nghiêng
                });
                xAxis.data.setAll(chartData5);

                // Trục Y
                var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
                    renderer: am5xy.AxisRendererY.new(root, {})
                }));

                // Series cột
                var series = chart.series.push(am5xy.ColumnSeries.new(root, {
                    name: "Số bác sĩ",
                    xAxis: xAxis,
                    yAxis: yAxis,
                    valueYField: "value",
                    categoryXField: "category"
                }));

                // 👉 SỬA Ở ĐÂY: dùng chartData4
                series.data.setAll(chartData5);



                // Thêm bullet label hiển thị giá trị trên cột
                series.bullets.push(function () {
                    return am5.Bullet.new(root, {
                        locationY: 0, // vị trí theo chiều Y (0 = đỉnh cột, 1 = đáy)
                        sprite: am5.Label.new(root, {
                            text: "{valueY}",
                            populateText: true,
                            centerX: am5.p50,
                            centerY: am5.p100,
                            dy: -10,
                            fontSize: 14,
                            fontWeight: "bold",
                            fill: am5.color(0x000000)
                        })
                    });
                });


                series.appear(1000);
                chart.appear(1000, 100);
            });
        </script>


    </body>

</html>