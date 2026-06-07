<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link REL=STYLESHEET
      HREF="StyleSheet.css"
      TYPE="text/css">

    <title>Professor Per course</title>
</head>
    <body>
    <%if ("viewProfPerCourse".equals(request.getAttribute("action"))) {%>

    <select name="courses">
        <c:forEach items="${listcourses}" var="courses">
            <option value="${courses.id}">${courses.name}</option>
        </c:forEach>
    </select>
    <select name="professors">
        <p:forEach items="${listprofessors}" var="professors">
            <option value="${professors.id}">${professors.name}</option>
        </p:forEach>
    </select>

    <table>
        <thead>
        <tr>
            <th>Course Name</th>
            <th>Professor Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.courses},${requestScope.professor}" var="c">
            <tr>
                <td><c:out value="${c.courseName}" /></td>
            </tr>
            <tr>
                <td><p:out value="${p.professorName}" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%} %>

    </body>
</html>