<%@ include file="index.html"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Copies"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	 ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	LibrarianService service = (LibrarianService) ac.getBean("librarianService"); 
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
    Branch branch = new Branch();
    branch.setBranchId(branchId);
    Copies copies = new Copies();
    copies.setBranchs(branch);
    List<Copies> copy = service.showBranchBook(copies);
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
</head>
<body>
<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="container theme-showcase" role="main">

		<div class="jumbotron">
<h3>Librarian mode</h3>
<table class="table">
	<tr>
		<th>Book Id</th>
		<th>Book title</th>
		<th>Copies </th>
		<th></th>
	</tr>
	<%for(Copies b: copy){ %>
		<tr>
		<form action="addbranchcopy" method="post">
			<td><%=copy.indexOf(b) + 1%></td>
			<td><%=b.getBooks().getTitle()%></td>
			<td><input type="number" name="noOfCopies"
				value="<%=b.getNoCopies()%>" /></td>
			<input type="hidden" name="branchId"
				value="<%=b.getBranchs().getBranchId()%>"/>
			<input type="hidden" name="bookId"
				value="<%=b.getBooks().getBookId()%>" />
			<td><button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>Add Copy</button></td>
		</form>
		</tr>
		
		<%} %>
</table>
</div>
</body>
</html>