<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Exception e=(Exception)request.getAttribute("exception"); 
out.println("Exception occure"+e.getMessage());
%>