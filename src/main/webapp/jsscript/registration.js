let username, password, cpassword, city, address, adhar, email, mobile

function addUser() {
	username = $("#username").val();
	password = $("#password").val();
	cpassword = $("#cpassword").val();
	city = $("#city").val();
	address = $("#address").val();
	adhar = $("#adhar").val();
	email = $("#email").val();
	mobile = $("#mobile").val();

	if (validateUser()) {
		if (password !== cpassword) {
			swal("Error!", "password should be same", "error");
			return;
		}
		let sendObj =
		{
			username: username,
			password: password,
			city: city,
			address: address,
			userid: adhar,
			email: email,
			mobile: mobile
		};

		let xhr = $.post("RegistrationCotrollerServlet", sendObj, responseHandler);
		xhr.fail(handleError);
	}

}

function responseHandler(responseText,textStatus,xhr)
{
	let response=responseText.trim();
	if(response==="success")
	{
		swal("success","Registration Done! You  Know can login","success");
		setTimeout(function(){
		window.location="login.html";
		},3000);
		
	}
	else if(response==="uap")
	{
	  swal("Unsuccess","Registration not Done! user already present","error");
	}
	else
	{
	 	  swal("Error","Problem on server! Try again","error");

	}
	
}

function handleError(xhr)
{
	 swal("Error","Problem on server! Try again"+xhr.statusText,"error");

}

function validateUser() {
	if (username === "" || password === "" || cpassword === "" ||
		city === "" || address === "" || adhar === "" || email === "" || mobile === "") {
		swal("Error!", "All field are mandatory", "error");
		return false;
	}
	return true;
}

function redirectUser()
{
	window.location="login.html";
}