<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String userid = (String) request.getAttribute("userid");
String result = (String) request.getAttribute("result");
if (userid != null && result != null) {
	HttpSession se = request.getSession();
	se.setAttribute("userid", userid);
	String url = "";
	if (result.equalsIgnoreCase("VOTER")) {
		url = "VotingControllerServlet;jsessionid="+se.getId();
	} else {
		url = "AdminControllerServlet;jsessionid="+se.getId();
	}
	out.println(url);
} else {
	out.println("error");
}
%>