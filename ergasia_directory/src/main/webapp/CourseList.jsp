<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course List</title>
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

        if(!"secretary".equals(session.getAttribute("role")))
        {

            session.removeAttribute("username");
            session.invalidate();
            request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
%>
</body>
</html>