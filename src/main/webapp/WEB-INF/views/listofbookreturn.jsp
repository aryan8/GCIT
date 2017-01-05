<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.Loans"%>

<%@page import="com.gcit.lms.entity.Copies"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
Integer branchId = Integer.parseInt(request.getParameter("branchId"));
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
BorrowerService service = (BorrowerService) ac.getBean("borrowerService");
List<Book> books = service.showBranchBookReturn(branchId, cardNo);
    System.out.println(cardNo+" - "+branchId);
    
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
		<table class="table" id="branchesTable">

		<tr>
			<th>Book Id</th>
			<th>Book title</th>
			<th>DueDate</th>
			<th></th>
		</tr>
		<%for(Book b: books){ %>
		<tr>
			<td><%=books.indexOf(b) + 1%></td>
			<td><%=b.getTitle()%></td>
			<td><%			
			Loans l = service.readAllBooksLoans(b.getBookId());
			out.print(l.getDueDate());

			
			%>
			<td>
				<form action="returnBook" method="post">
				<input type="hidden" name=bookId value='<%=b.getBookId()%>'>
				<input type="hidden" name=branchId value='<%=branchId%>'> 
				<input type="hidden" name=cardNo value='<%=cardNo%>'>
				<td><button class="btn btn-success" type="submit"><span
										class="glyphicon glyphicon-triangle-bottom">Return</button>
				
			</form></td>
		</tr>
		
		<%} %>
	
	</table>
	</div>
	</div>
</body>
</html>