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
        <title>Admin - Subject</title>
        <%@include file="templates/head.jsp" %>
    </head>
    <body>
        <%@include file="templates/header.jsp" %>
        <%@include file="templates/sidebar.jsp" %>
        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Subject</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active">Subject</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="row">

                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:choose>
                                        <c:when test="${isEdit}">
                                            Edit subject
                                        </c:when>
                                        <c:otherwise>
                                            Add subject
                                        </c:otherwise>
                                    </c:choose>
                                </h5>

                                <!-- Vertical Form -->
                                <form
                                    <c:choose>
                                        <c:when test="${isEdit}">
                                            action="${pageContext.request.contextPath}/admin/subject/edit"
                                        </c:when>
                                        <c:otherwise>
                                            action="${pageContext.request.contextPath}/admin/subject/add"
                                        </c:otherwise>
                                    </c:choose>
                                    method="POST"
                                    class="row g-3">
                                    <c:if test="${isEdit}">
                                        <input type="hidden" name="id" value="${subject.id}"/>
                                    </c:if>
                                    <c:if test="${!isEdit}">
                                        <div class="col-12">
                                            <label for="inputNanme4" class="form-label">ID</label>
                                            <input type="text" class="form-control" id="inputNanme4"  name="id" value="${subject.id}">
                                        </div>
                                    </c:if>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="name" value="${subject.name}">
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary me-1">Submit</button>
                                        <a href="${pageContext.request.contextPath}/admin/subject"
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
            document.getElementById("nav-link-subject").className = "nav-link";
        </script>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
