<%-- 
    Document   : class_form
    Created on : Mar 2, 2024, 7:05:07 PM
    Author     : ns
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin - Exam</title>
        <%@include file="templates/head.jsp" %>
    </head>
    <body>
        <%@include file="templates/header.jsp" %>
        <%@include file="templates/sidebar.jsp" %>
        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Exam</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active">List request</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title mb-0">Request registers</h5>
                                <a class="btn btn-primary mb-2" href="${pageContext.request.contextPath}/admin/exam">
                                    <i class="bi bi-arrow-left-short"></i>
                                    All exams
                                </a>
                                <c:if test="${message ne null}">
                                    <p>${message}</p>
                                </c:if>

                                <!-- Table with stripped rows -->
                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th># ${rl.size()}</th>
                                            <th>Student Name</th>
                                            <th>Exam Name</th>
                                            <th>Date</th>
                                            <th>Room</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                            <!--<th data-type="date" data-format="YYYY/DD/MM">Start Date</th>-->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" varStatus="status" items="${rl}">
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${item.u.username}</td>
                                                <td>${item.exam.name}</td>
                                                <td>
                                                    <fmt:formatDate pattern = "dd-MM-yyyy" 
                                                                    value = "${item.exam.date}" />
                                                </td>
                                                <td>${item.exam.room}</td>


                                                <td>${item.isStatus()==true?"Confirmed":"Pending"}</td>
                                                <td>
                                                    <c:if test="${item.status==false}">
                                                        <a href="${pageContext.request.contextPath}/ChangeRequestStatus?rid=${item.getID()}&ss=1&eid=${item.exam.id}&sid=${item.student_id}"
                                                           class="btn btn-primary me-2 mb-sm-2 mb-md-0">Confirm</a>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <!-- End Table with stripped rows -->

                            </div>
                        </div>

                    </div>
                </div>
            </section>

        </main><!-- End #main -->
        <script>
            document.getElementById("nav-link-exam").className = "nav-link";
        </script>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
