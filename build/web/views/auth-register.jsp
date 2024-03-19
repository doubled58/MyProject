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
        <title>Register</title>
        <%@include file="templates/head.jsp" %>
    </head>
    <body>
        <main>
            <div class="container">

                <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

                                <div class="d-flex justify-content-center py-4">
                                    <a href="index.html" class="logo d-flex align-items-center w-auto">
                                        <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="">
                                        <span class="d-none d-lg-block">Exam Management</span>
                                    </a>
                                </div><!-- End Logo -->

                                <div class="card mb-3">

                                    <div class="card-body">

                                        <div class="pt-4 pb-2">
                                            <h5 class="card-title text-center pb-0 fs-4">Create an Account</h5>
                                            <p class="text-center small">Enter your personal details to create account</p>
                                        </div>

                                        <form action="${pageContext.request.contextPath}/auth/register"
                                              method="POST"
                                              class="row g-3 needs-validation" novalidate>
                                            <div class="col-12">
                                                <label for="yourName" class="form-label">Full name</label>
                                                <input type="text" name="name" value="${user.name}" class="form-control" id="yourName" required>
                                                <div class="invalid-feedback">Please, enter your name!</div>
                                            </div>

                                            <div class="col-12">
                                                <label for="yourEmail" class="form-label">Date of birth</label>
                                                <input type="date" name="dob" value="${user.dob}" class="form-control" id="yourEmail" required>
                                                <div class="invalid-feedback">Please enter a valid DOB!</div>
                                            </div>

                                            <div class="col-12">
                                                <label for="yourEmail" class="form-label">Email</label>
                                                <input type="email" name="email" value="${user.email}" class="form-control" id="yourEmail" required>
                                                <div class="invalid-feedback">Please enter a valid Email adddress!</div>
                                            </div>

                                            <div class="col-12">
                                                <label for="yourUsername" class="form-label">Username</label>
                                                <div class="input-group has-validation">
                                                    <span class="input-group-text" id="inputGroupPrepend">@</span>
                                                    <input type="text" name="username" value="${user.username}" class="form-control" id="yourUsername" required>
                                                    <div class="invalid-feedback">Please choose a username.</div>
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <label for="yourPassword" class="form-label">Password</label>
                                                <input type="password" name="password" value="${user.password}" class="form-control" id="yourPassword" required>
                                                <div class="invalid-feedback">Please enter your password!</div>
                                            </div>

                                            <div class="col-12">
                                                <label class="form-label">Class</label>
                                                <select name="classId" id="class-id" class="form-select" aria-label="Class">
                                                    <c:forEach var="klass" items="${klasses}">
                                                        <option value="${klass.id}">${klass.name}</option>
                                                    </c:forEach> 
                                                </select>
                                                <script>
                                                    document.getElementById("class-id").value = "${user.classId}";
                                                </script>
                                            </div>

                                            <div class="col-12">
                                                <p class="small mb-0 fst-italic">${message}</p>
                                            </div>

                                            <div class="col-12">
                                                <button class="btn btn-primary w-100" type="submit">Create Account</button>
                                            </div>
                                            <div class="col-12">
                                                <p class="small mb-0">Already have an account? <a href="${pageContext.request.contextPath}/auth/login">Log in</a></p>
                                            </div>
                                        </form>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </section>

            </div>
        </main><!-- End #main -->

        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

        <!-- Vendor JS Files -->
        <script src="${pageContext.request.contextPath}/assets/vendor/apexcharts/apexcharts.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/chart.js/chart.umd.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/echarts/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/quill/quill.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/simple-datatables/simple-datatables.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/tinymce/tinymce.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

    </body>
</html>
