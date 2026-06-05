<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet"	href="./css/index.css" type="text/css"/>
<script src="./javascript/index.js"  type="text/javascript" ></script>
<script src="./javascript/error.js"  type="text/javascript" ></script>
</head>
<body >

	
<script type="text/javascript">
    var msg = "<%= request.getAttribute("message") %>";
    var user="<%= request.getAttribute("user") %>";
    if (msg=="Wrong Password!" || msg=="There is no user with the username: "+user+", please enter a valid username!" || msg=="There is already a user with the username: "+user+", please enter a different username." )
    	{
    	Swal.fire({
    		  icon: 'error',
    		  title: 'Oops...',
    		  text: msg
    		})

    	}    
</script>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setHeader("Expires", "0"); // Proxies.
if (request.getAttribute("msg")=="You had no access to that page, you were logged out for safety reasons."){%> <script type="text/javascript">Swal.fire({icon: 'error',title: 'Oops...',text:'<%=request.getAttribute("msg")%>'})</script><% } %>



<div class="login-wrap" >
	<div class="login-html" >
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
		<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
		<div class="login-form">
			<!-- FIXED: Use relative path instead of absolute path -->
			<form name="SignIn" action="<%= request.getContextPath() %>/login" onsubmit="return validateSignInForm()" method="post">
			<div class="sign-in-htm">
				<div class="group">
					<label for="user" class="label">Username</label>
					<input id="user" type="text" name="username" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password"  onkeypress="return RestrictSpace()" name="password" class="input" data-type="password">
				</div>				
				<div class="group">
					<input type="submit" class="button" value="Sign In">
				</div>			
			</div>
			</form>
			<!-- FIXED: Use relative path instead of absolute path -->
			<form name="SignUp" action="<%= request.getContextPath() %>/register" onsubmit="return validateSignUpForm()" method="post">
			<div class="sign-up-htm">
				<div class="group">
					<label for="registrationnumber" class="label">Registration Number</label>
					<input id="registrationnumber" type="text" name="registrationnumber" class="input">
				</div>
				<div class="group">
					<label for="username" class="label">Username</label>
					<input id="username" type="text" name="newusername" class="input">
				</div>
				<div class="group">
					<label for="name" class="label">Name</label>
					<input id="name" type="text" name="newname" class="input">
				</div>
				<div class="group">
					<label for="surname" class="label">Surname</label>
					<input id="surname" type="text" name="newsurname" class="input">
				</div>
				<div class="group">
					<label for="department" class="label">Department</label>
					<input id="department" type="text" name="newdepartment" class="input">
				</div>
				<div class="group">
					<label for="pass" class="label">Password</label>
					<input id="pass" type="password"  onkeypress="return RestrictSpace()" name="newpassword1" class="input" data-type="password">
				</div>
				<div class="group">
					<label for="pass" class="label">Repeat Password</label>
					<input id="pass" type="password" onkeypress="return RestrictSpace()" name="newpassword2" class="input" data-type="password">
				</div>
				<div class="group">
					<input type="radio" id="secretary" name="role" value="secretary">
  					<label for="secretary" style="color:#aaa;font-size:12px;" >Secretary</label>   
  					<input type="radio" id="student" name="role" value="student">
 				    <label for="student" style="color:#aaa;font-size:12px;">Student</label>  
  					<input type="radio" id="professor" name="role" value="professor">
  					<label for="professor" style="color:#aaa;font-size:12px;">Professor</label>  
				</div>
				<div class="group">
					<input type="submit" class="button" value="Sign Up">
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>