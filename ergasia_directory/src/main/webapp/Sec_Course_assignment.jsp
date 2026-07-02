<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: meow
  Date: 6/29/26
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!Connection connection = utilities.DbUtil.getConnection(); %>

<%         try {

    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from courses ");
    ResultSet rs = preparedStatement.executeQuery();
    while(rs.next()){
        System.out.println(rs.getString("course_name"));
    }

}
catch(SQLException e) {
    e.printStackTrace();
}%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="login-form">
    <!-- FIXED: Use relative path instead of absolute path -->
    <form name="SignIn" action="<%= request.getContextPath() %>/Sec_course_assignment_2.jsp"  method="post">
        <div class="sign-in-htm">
            <div class="group">
                <label for="prof" class="label">Ονομα καθηγητη </label>
                <input id="prof" type="text" name="prof_name" class="input">
            </div>
            <div class="group">
                <label for="class" class="label">Ονομα </label>
                <input id="class" type="text"   name="class_name" class="input" >
            </div>

            <div class="group">
                <input type="submit" class="button" value="Sign In">
            </div>
        </div>
    </form>
    <!-- FIXED: Use relative path instead of absolute path -->

</div>
<table> <th><b>Course_name</b></th>
    <%        try {


        String name=request.getParameter("prof_name");
        System.out.println(name);

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from courses ");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){%>
    <tr><td><%=rs.getString("course_name")%></td></tr><%}



    }
    catch(SQLException e) {
        e.printStackTrace();
    }%>



</table>

</body>
</html>
