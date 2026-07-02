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


        String name=request.getParameter("prof_name");
        System.out.println(name);
        String course_name=request.getParameter("class_name");
        System.out.println(course_name);
        int id_setter =0;
        PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * from proffesor where name=? ");
        preparedStatement3.setString(1, name);
        ResultSet rs3 = preparedStatement3.executeQuery();
        rs3.next();
        int prof_id=rs3.getInt("id");
        System.out.println(prof_id);
        PreparedStatement preparedStatement4 = connection.prepareStatement("SELECT * from courses where course_name=? ");
        preparedStatement4.setString(1, course_name);

        ResultSet rs4 = preparedStatement4.executeQuery();
        rs4.next();
        int course_id=rs4.getInt("course_id");
        System.out.println(course_id);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from dilosi_k ");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            if (rs.getInt("id")>=id_setter){
                id_setter=rs.getInt("id")+1;
            }
        }
        System.out.println(id_setter);
        PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO dilosi_k (id,p_id,c_id) values (?,?,?) ");
        preparedStatement2.setInt(1, id_setter);
        preparedStatement2.setInt(2, prof_id);
        preparedStatement2.setInt(3, course_id);
        int rest =preparedStatement2.executeUpdate();
        System.out.println(rest + " record(s) inserted");

    }
    catch(SQLException e) {
        e.printStackTrace();
    }%>



</table>

</body>
</html>
