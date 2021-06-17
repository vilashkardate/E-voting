<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
boolean result=(boolean)request.getAttribute("result");
boolean userfound=(boolean)request.getAttribute("userfound");
if(userfound)
{
	out.println("uap");
}else if(result)
{
	out.println("success");
}else
{
	out.println("error");
}
%>