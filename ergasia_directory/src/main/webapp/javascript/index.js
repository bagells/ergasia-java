
function validateSignInForm() {
	var x = document.forms["SignIn"]["username"].value;
	var y = document.forms["SignIn"]["password"].value;
	if (x == "" && y == "") {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'Please fill out your username and password '
		})
		return false;
	}
	if (y == "") {

		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'Please fill out your password '
		})

		return false;
	}
	if (x == "") {
		Swal.fire({
			icon: 'error',
			title: 'Oops...',
			text: 'Please fill out your username '
		})
		return false;
	}
}

function validateSignUpForm() {
	/*	  var x = document.forms["SignUp"]["newusername"].value;
		  var r = document.forms["SignUp"]["registrationnumber"].value;
		  var y = document.forms["SignUp"]["newname"].value;
		  var z = document.forms["SignUp"]["newsurname"].value;
		  var d = document.forms["SignUp"]["department"].value;
		  var p1 = document.forms["SignUp"]["newpassword1"].value;
		  var p2 = document.forms["SignUp"]["newpassword2"].value;
		  var w = document.forms["SignUp"]["role"].value;
		  
		  if (x == "" && r == "" && y == "" && z == "" && d == "" && p1 == "" && p2 == "" && w == "" ) {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Fill out your information '
				})
			return false;
		  }
		  if (x == "")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Username must be filled out '
				})
			  return false;
		  }
		  if (r == "")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'RegistrationNumber must be filled out '
				})
			  return false;
		  }
		  if (y == "")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Name must be filled out '
				})
			  return false;
		  }
		  if (z == "")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Surname must be filled out '
				})
			
			  return false;
		  }
		  if (p2 == "" && p1!="")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Please repeat your password '
				})
			 
			  return false;
		  }
		  if (p1 == "")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Password must be filled out '
				})
		
			  return false;
		  }
		  if (w!="secretary" && w!="student" && w!= "professor")
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Please choose your role in the company '
				})
			 
			  return false;
		  }
		  
		  if (p1!=p2)
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Please make sure to repeat your password correctly '
				})
			  document.forms["SignUp"]["newpassword1"].value="";
			  document.forms["SignUp"]["newpassword2"].value="";
			  return false;
		  } 
		  if (x.length<8 || x.length>16 )
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Username must contain 8 to 16 characters '
				})
			  document.forms["SignUp"]["newusername"].value="";
			  return false;
		  }
		  if (y.length > 32 || checkalphabet(y) )
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Name must contain up to 32 characters and no special characters or numbers '
				})
			  document.forms["SignUp"]["newname"].value="";
			  return false;
		  }
		  if(z.length > 32 || checkalphabet(z))
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Surname must contain up to 32 characters and no special characters or numbers '
				})
			 
			  document.forms["SignUp"]["newsurname"].value="";
			  return false;
		  }
		  if (p1.length>128 || p2.length>128 || p1.length<3|| p2.length<3)
		  {
			  Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: 'Password must be between 3 and 128 characters '
				})
		
			  document.forms["SignUp"]["newpassword1"].value="";
			  document.forms["SignUp"]["newpassword2"].value="";
			  return false;
		  }
	}
	
	function RestrictSpace() {
		if (event.keyCode == 32) {
			return false;
		}
	}
	 function checkalphabet(string){
		 var regName = /^[a-zA-Z ]+$/;
		 if(!regName.test(string)){
			 return true;
		 }else{
			 return false;
		 }
	*/
	return true;
}


