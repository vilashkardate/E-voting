function LoginUser(){
	let userid, password;

	userid = $("#username").val();
	password = $("#password").val();

	if (userid == "" || password == "") {
		swal("Error!", "userid or password should not be empty", "error");
		return;
	}

	let user = {
		userid:userid,
		password:password
	};
	
	let xhr=$.post("LoginControllerServlet",user,responseHandler);
	xhr.fail(errorHandler);
}


function responseHandler(responseText,textStatus,xhr)
{
		if(responseText.trim()==="error")
		{
			swal("Access Denied", "invalid userid/password", "error");
		}
		else if(responseText.trim().indexOf("jsessionid")!==-1)
		{
			let promise=swal("Success","Login SuccessFull","success");
			
			promise.then((value)=>{
			  window.location=responseText.trim();
			});
			
		}else
		{
			swal("Access Denied", "Some Problem Occurred", "error");	
		}
}

function errorHandler(xhr)
{
	 swal("Error","Problem on server! Try again"+xhr.statusText,"error");
}