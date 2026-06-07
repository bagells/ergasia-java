<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link REL=STYLESHEET
      HREF="StyleSheet.css"
      TYPE="text/css">

    <title>Course List</title>
</head>
    <body>

        <!--https://www.codejava.net/java-ee/jsp/how-to-create-dynamic-drop-down-list-in-jsp-from-database-->

        <%if ("viewCourseList".equals(request.getAttribute("action"))) {%>

        <select name="courses">
            <c:forEach items="${listcourses}" var="courses">
                <option value="${courses.id}">${courses.name}</option>
            </c:forEach>
        </select>

        <table>
            <thead>
            <tr>
                <th>Course Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.courses}" var="c">
                <tr>
                    <td><c:out value="${c.courseName}" /></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%}%>




    </body>
</html>