<%@page import="evoting.entity.CandidateInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="jsscript/vote.js"></script>
<script src="jsscript/jquery.js"></script>
<link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
<link href="stylesheet/backgroundimage.css" rel="stylesheet">
<link href="stylesheet/pageheader.css" rel="stylesheet">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

<title>Vote the Candidate</title>
</head>
<body>
	<%
	String userid = (String) session.getAttribute("userid");
	if (userid == null) {
		response.sendRedirect("accessdenied.html");
		return;
	}

	List<CandidateInfo> canInfo = (List<CandidateInfo>) request.getAttribute("candidateList");
	%>
<div class='sticky' style="top:40px;" >
		<div class='candidate'>VOTE FOR CHANGE</div>
		<br>
		<div class='subcandidate'>Who you want vote</div>
		<br>
		<br>
		<div class='logout'>
			<a href='login.html'>logout</a>
		</div>
	</div>

	<div class='buttons'>

		<%
		for (CandidateInfo c : canInfo) {
		%>	
		<input id='<%= c.getCandidateId() %>' value='<%= c.getCandidateId() %>'
			name='flat' type='radio' onclick='addvote()' />
	<label for='<%= c.getCandidateId() %>'><img src='data:image/jpg;base64,<%= c.getSymbol() %>' style='width:300px;height:200px;'/></label>
		<br />
		<div class='candidateprofile'>
			<p>
				Candidate Id: <%=c.getCandidateId()%>
				<br />
				Candidate Name: <%= c.getCandidateName()%>
				<br />
 				Party: <%= c.getParty()%>
 				
 				<br />
		</div>
		
		<%
		}
		%>
	</div>

</body>
</html>