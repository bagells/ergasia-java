<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Grading Management System - Secretary's menu</title>
<meta name="description" content="University Grades App">
<script src="./javascript/error.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/student.css">
<link rel="stylesheet" type="text/css" href="css/table.css">

<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#myNavbar">
                <span class="glyphicon glyphicon-menu-hamburger"></span>
            </button>          
		<h1 class="brand brand-name navbar-left" style="font-family:Georgia" > University Grading Management System - Secretary's Menu </h1>
        </div>
       
        <%String name="secretary"; %><div style="text-align:right;color:#bfbfbf;size:12px;">Welcome, <%= session.getAttribute("username") %> </div>
        
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">

	<ul class="nav navbar-nav">
		<li><a href="secretary?action=viewSecretaryDetails">View
				secretary's personal data</a></li>
		<li><a href="secretary?action=viewStudentGrades">Show
				student's grades</a></li>
		<li><a href="logout">Log out</a></li>
	</ul>
        </div>
    </div>
</nav>

	<%if ("viewSecretaryDetails".equals(request.getAttribute("action"))) {%>

	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>Username</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td data-column="Name"><%=session.getAttribute("name")%></td>
				<td data-column="Surname"><%=session.getAttribute("surname")%></td>
				<td data-column="Username"><%=session.getAttribute("username")%></td>
			</tr>
		</tbody>
	</table>


	<%} %>
</body>
</html>