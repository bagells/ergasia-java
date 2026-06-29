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
<table> <th><b>Course_name</b></th>
<%        try {

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
