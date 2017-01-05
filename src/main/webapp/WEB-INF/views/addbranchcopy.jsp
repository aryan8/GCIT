<%@ include file="index.html"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Copies"%>
<%@page import="com.gcit.lms.entity.Book"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.servlet.LibrarianServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
    LibrarianService service= new LibrarianService();
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
		<form action="addCopyBranchLib" method="post">
			<td><%=copy.indexOf(b) + 1%></td>
			<td><%=b.getBooks().getTitle()%></td>
			<td><input type="number" name="noOfCopies"
				value="<%=b.getNoCopies()%>" /></td>
			<input type="hidden" name="branchId"
				value="<%=b.getBranchs().getBranchId()%>"/>
			<input type="hidden" name="bookId"
				value="<%=b.getBooks().getBookId()%>" />
			<td><button class="btn btn-success" type="submit">Add Copy</button></td>
		</form>
		</tr>
		
		<%} %>
</table>
</div>
</body>
</html>