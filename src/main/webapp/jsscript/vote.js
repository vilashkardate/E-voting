function addvote() {

 	var candId=$("input[name='flat']:checked").val();
	data={candidateid:candId};
  $.post("AddVoteControllerServlet",data,function(responseText){
  	alert(responseText);
     		if(responseText.trim()=='success')
     		{
     			swal("Congratulation ", "Your Vote has been casted ", "success")
     			.then((value) => {
     			
					window.location = "VotingResponse.jsp";
				});
     		}else
     		{
     			swal("Sorry", "Your Vote cannot be casted ", "error")
     			.then((value) => {
					window.location = "VotingResponse.jsp";
				});
     		}
      });
	
}