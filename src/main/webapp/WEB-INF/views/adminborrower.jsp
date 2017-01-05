
<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("adminService");
List<Borrower> borrower = service.readBorrower();
	%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
</head>
<body>
<div class="container theme-showcase" role="main">
  <div class="jumbotron">
<h1>GCIT Library Management System</h1>
<h3>Administrator Borrower Management</h3>
	<button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal" onclick="javascript:location.href='addborrower'"><span class="glyphicon glyphicon-plus-sign"></span>
						Add Borrower</button>
<table class="table" id="branchesTable">
	<tr>
		<th>#</th>
		<th>Borrower Name</th>
		<th>Borrower Address</th>
		<th>Borrower Phone</th>
		<th></th>
		<th></th>
	</tr>
	<%for(Borrower p : borrower){ %>
	<tr>
	<td><%=borrower.indexOf(p)+1%>
	<td><%=p.getName() %></td>
	<td><%=p.getAddress() %></td>
	<td><%=p.getPhone()%></td>
	
	<td><a class="btn btn-info" href="editborrower?cardNo=<%=p.getCardNo()%>"><span class="glyphicon glyphicon-edit"></span>Edit</a>
						<td><a class="btn btn-danger" href="deleteborrower?cardNo=<%=p.getCardNo()%>"><span class="glyphicon glyphicon-remove-circle"></span>Delete</a></td>
	</tr>
	<%} %>

</table>
</div>
</div>
<div class="modal fade bs-example-modal-lg" id="editauthormodal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>
</body>
</html>