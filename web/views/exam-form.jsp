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

                    <div class="col-lg-6">

                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:choose>
                                        <c:when test="${isEdit}">
                                            Edit exam
                                        </c:when>
                                        <c:otherwise>
                                            Add exam
                                        </c:otherwise>
                                    </c:choose>
                                </h5>

                                <!-- Vertical Form -->
                                <form
                                    <c:choose>
                                        <c:when test="${isEdit}">
                                            action="${pageContext.request.contextPath}/admin/exam/edit"
                                        </c:when>
                                        <c:otherwise>
                                            action="${pageContext.request.contextPath}/admin/exam/add"
                                        </c:otherwise>
                                    </c:choose>
                                    method="POST"
                                    class="row g-3">
                                    <c:if test="${isEdit}">
                                        <input type="hidden" name="id" value="${exam.id}"/>
                                    </c:if>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="inputNanme4" name="name" value="${exam.name}" required>
                                    </div>
                                    <div class="col-12">
                                        <label class="form-label">Subject</label>
                                        <select name="subjectId" id="subject-id" class="form-select" aria-label="Subject" required>
                                            <c:forEach var="subject" items="${subjects}">
                                                <option value="${subject.id}">${subject.id} - ${subject.name}</option>
                                            </c:forEach> 
                                        </select>
                                        <script>
                                            document.getElementById("subject-id").value = "${exam.subjectId}";
                                        </script>
                                    </div>
                                    <div class="col-12">
                                        <label class="form-label">Slot</label>
                                        <select name="slotId" id="slot-id" class="form-select" aria-label="Slot" required>
                                            <c:forEach var="slot" items="${slots}">
                                                <option value="${slot.id}">${slot.name} (${slot.startTime} - ${slot.endTime})</option>
                                            </c:forEach> 
                                        </select>
                                        <script>
                                            document.getElementById("slot-id").value = "${exam.slotId}";
                                        </script>
                                    </div>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Date</label>
                                        <input type="date" class="form-control" id="inputNanme4"  name="date" value="${exam.date}" required>
                                    </div>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Room</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="room" value="${exam.room}" required>
                                    </div> 
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Max number of students(0 for unlimited)</label>
                                        <input type="number" class="form-control" id="inputNanme4"  name="maxSize" min="0" value="${exam.maxSize}" required>
                                    </div>
                                    <div class="col-12">
                                        <label for="inputNanme4" class="form-label">Note</label>
                                        <input type="text" class="form-control" id="inputNanme4"  name="note" value="${exam.note}">
                                    </div>
                                    <div>
                                        <button type="submit" class="btn btn-primary me-1">Submit</button>
                                        <a href="${pageContext.request.contextPath}/admin/exam"
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
