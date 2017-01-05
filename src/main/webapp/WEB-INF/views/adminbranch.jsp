
<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    AdminService service = (AdminService) ac.getBean("adminService");
    List<Branch> branch = service.readBranch();
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
<h3>Administrator Branch Management</h3>
	<button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal" onclick="javascript:location.href='addbranch'"><span class="glyphicon glyphicon-plus-sign"></span>Add branch</button>
<table class="table" id="branchesTable">
	<tr>
		<th>#</th>
		<th>Branch Id</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
		
		<th></th>
		<th></th>
	</tr>
	<%for(Branch p: branch){ %>
	<tr>
	<td><%=branch.indexOf(p)+1%>
	<td><%=p.getBranchId()%></td>
	<td><%=p.getBranchName() %></td>
	<td><%=p.getBranchAddr() %></td>
	
	<td><a class="btn btn-info" href="updatebranch?branchId=<%=p.getBranchId()%>"><span class="glyphicon glyphicon-edit"></span>Edit</a>
						<td><a class="btn btn-danger" href="deletebranch?branchId=<%=p.getBranchId()%>"><span class="glyphicon glyphicon-remove-circle"></span>Delete</a></td>
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