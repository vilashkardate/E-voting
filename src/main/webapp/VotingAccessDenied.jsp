<%@page import="evoting.entity.CandidateInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
<link href="stylesheet/backgroundimage.css" rel="stylesheet">
<link href="stylesheet/pageheader.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<title>Voting Submitted Already</title>
</head>
<body>

	<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		session.invalidate();
		response.sendRedirect("accessdenied.html");
		return;
	}
	CandidateInfo info=(CandidateInfo)request.getAttribute("info");
	%>
	<div class='sticky'>
		<div class='candidate'>VOTE FOR CHANGE</div>
		<br>
		<div class='subcandidate'>Your Vote already submitted</div><br><br>
		<div class='subcandidate'>Your Vote Casted to :- <%=info.getCandidateId() %> </div><br>
		<div ><img src='data:image/jpg;base64,<%= info.getSymbol() %>' style='width:300px;height:200px;'/>
			<br/>
			Candidate Name: <%= info.getCandidateName()%>
			<br />
 				Party: <%= info.getParty()%>	
 			<br />
		</div>
		
		<div class='logout'>
			<a href='login.html'>logout</a>
		</div>

	</div>

</body>
</html>