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
        <title>Profile</title>
        <%@include file="templates/head.jsp" %>
    </head>
    <body>
        <%@include file="templates/header.jsp" %>
        <c:choose>
            <c:when test="${user.role eq 'ADMIN'}">
                <%@include file="templates/sidebar.jsp" %>
            </c:when>
            <c:otherwise>
                <%@include file="templates/student-sidebar.jsp" %>
            </c:otherwise>
        </c:choose>
        <main id="main" class="main">

            <div class="pagetitle">
                <h1>Profile</h1>
                <nav>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                        <li class="breadcrumb-item active">Profile</li>
                    </ol>
                </nav>
            </div><!-- End Page Title -->
            <section class="section">
                <div class="row">

                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    Edit profile
                                </h5>

                                <!-- Vertical Form -->
                                <form
                                    action="${pageContext.request.contextPath}/profile/edit"
                                    method="POST"
                                    class="row g-3">
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="name" value="${user.name}">
                                    </div>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Date of birth</label>
                                        <input type="date" class="form-control" id="inputNanme4"  name="dob" value="${user.dob}">
                                    </div>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Email</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="email" value="${user.email}">
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary me-1">Submit</button>
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
            document.getElementById("nav-link-profile").className = "nav-link";
        </script>
        <%@include file="templates/footer.jsp" %>
    </body>
</html>
