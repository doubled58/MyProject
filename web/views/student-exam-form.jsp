<%-- 
    Document   : class_form
    Created on : Mar 2, 2024, 7:05:07 PM
    Author     : ns
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student - Exam</title>
        <%@include file="templates/head.jsp" %>
    </head>
    <body>
        <%@include file="templates/header.jsp" %>
        <%@include file="templates/student-sidebar.jsp" %>
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

                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    Edit student note
                                </h5>

                                <!-- Vertical Form -->
                                <form
                                    action="${pageContext.request.contextPath}/student/exam/edit"
                                    method="POST"
                                    class="row g-3">
                                    <input type="hidden" name="examId" value="${examId}"/>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Note</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="note" value="${note}">
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary me-1">Submit</button>
                                        <a href="${pageContext.request.contextPath}/student/exam/me"
                                           class="btn btn-danger me-2">Cancel</a>
                                    </div>
                                </form><!-- Vertical Form -->
                                <p class="mt-3">${message}</p>
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
