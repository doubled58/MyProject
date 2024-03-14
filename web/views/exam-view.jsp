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
                        <li class="breadcrumb-item active">Exam</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title mb-0">Exams</h5>
                                <a class="btn btn-primary mb-2" href="${pageContext.request.contextPath}/admin/exam/add">
                                    <i class="bi bi-plus"></i>
                                    Add exam
                                </a>

                                <!-- Table with stripped rows -->
                                <table class="table datatable">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Name</th>
                                            <th>Slot</th>
                                            <th>Subject</th>
                                            <th>Date</th>
                                            <th>Room</th>
                                            <th>Size</th>
                                            <th>Note</th>
                                            <th>Action</th>
                                            <!--<th data-type="date" data-format="YYYY/DD/MM">Start Date</th>-->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="item" varStatus="status" items="${exams}">
                                            <tr>
                                                <td>${status.count}</td>
                                                <td>${item.name}</td>
                                                <td>${item.slot.name} (${item.slot.startTime} - ${item.slot.endTime})</td>
                                                <td>${item.subject.id} - ${item.subject.name}</td>
                                                <td>
                                                    <fmt:formatDate pattern = "dd-MM-yyyy" 
                                                                    value = "${item.date}" />
                                                </td>
                                                <td>${item.room}</td>
                                                <td>${item.maxSize}</td>
                                                <td>${item.note}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/admin/exam/registed?examId=${item.id}"
                                                       class="btn btn-primary me-2 mb-sm-2">Registed List</a>
                                                    <a href="${pageContext.request.contextPath}/admin/exam/edit?id=${item.id}"
                                                       class="btn btn-warning me-2 mb-sm-2">Edit</a>
                                                    <a href="${pageContext.request.contextPath}/admin/exam/delete?id=${item.id}"
                                                       class="btn btn-danger me-2 mb-sm-2">Delete</a>
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
