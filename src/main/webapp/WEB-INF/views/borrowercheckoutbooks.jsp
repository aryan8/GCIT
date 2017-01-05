<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Copies"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.servlet.BorrowerServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Integer cardNo = (Integer)request.getAttribute("cardNo");
	Integer branchId = (Integer) request.getAttribute("branchId");
    List<Book> books = (List<Book>)request.getAttribute("books");
    System.out.println("books: " + books);
    System.out.println(cardNo+" - "+branchId);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
</head>
<body>

<table class="table" id="branchesTable">
	<tr>
		<th>Book Id</th>
		<th>Book title</th>
		<th></th>
	</tr>
	<%for(Book b: books){ %>
		<tr>
			<td><%=books.indexOf(b) + 1%></td>
			<td><%=b.getTitle()%></td>
			<td><a class="btn btn-info" href="InsertBookLoan?cardNo=<%=cardNo%>&branchId=<%=branchId%>&bookId=<%=b.getBookId()%>">Pick</a></td>
		</tr>
		
		<%} %>
		
</table>
</body>
</html>