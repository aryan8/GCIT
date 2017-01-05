<%@ include file="index.html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

<b style='color:white'><%= request.getParameter("message")	%></b>
<br/>
<br/>

<a href="<%= request.getParameter("url") %>"><button type="button" class="btn btn-info"><span
										class="glyphicon glyphicon-step-backward"></span>Go Back </button></a> 
</body>
</html>