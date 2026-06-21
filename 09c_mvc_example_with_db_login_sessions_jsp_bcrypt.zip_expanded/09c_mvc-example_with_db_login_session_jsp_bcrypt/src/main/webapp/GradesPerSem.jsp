<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Student Grades</title>
  <meta name="description" content="University Grades App">
  <script src="./javascript/error.js" type="text/javascript"></script>
  <link rel="stylesheet" type="text/css" href="css/student.css">
  <link rel="stylesheet" type="text/css" href="css/table.css">

  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<%

  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
  response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
  response.setHeader("Expires", "0"); // Proxies.

  if(session.getAttribute("username")==null)
  {
    response.sendRedirect("index.jsp");
  }else{

    if(!"student".equals(session.getAttribute("role")))
    {

      session.removeAttribute("username");
      session.invalidate();
      request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
  }
%>

    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <sql:setDataSource
                var="myDB"
                driver="org.postgresql.Driver"
                url="jdbc:postgresql://localhost:5432/postgres"
                user="postgres" password="password"
        />

    <sql:query dataSource="${myDB}" var="Sgrade">
      declare @session_id as numeric;
      set @session_id = <%= session.getAttribute("s_id") %>
      SELECT * FROM grades AS g
          INNER JOIN courses AS c ON g.c_id=c.course_id
          WHERE g.s_id=@session_id
          ORDER BY exetatistiki;
    </sql:query>


    <table id="tabl"
           style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
      <thead>
      <tr>
        <th>course:</th>
        <th>grade:</th>
        <th>semester:</th>
      </tr>
      </thead>
      <tbody>
        <%--<c:forEach items="${requestScope.courses},${requestScope.professor}" var="c">--%>
        <c:forEach items="${Sgrade.rows}" var="g">
          <tr>
            <td><c:out value="${g.course_name}" /></td>
          </tr>
          <tr>
            <td><c:out value="${g.grade}" /></td>
          </tr>
          <tr>
            <td><c:out value="${g.exetatistiki}" /></td>
          </tr>
        </c:forEach>
      </tbody>
</table>


</body>
</html>
