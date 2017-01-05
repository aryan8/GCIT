<%@ include file="index.html"%>

<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*    LibrarianService service = new  LibrarianService();
	   Branch branch = new Branch();
	   branch.setBranchId(Integer.parseInt(request.getParameter("branchId"))); */
	
	Borrower borrower = null;
	int cardNo = -1;
	   
	if(request.getAttribute("borrower") != null) {
		borrower = (Borrower) request.getAttribute("borrower");
		cardNo = borrower.getCardNo();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Library Management</title>
</head>
<body>
<div class="container theme-showcase" role="main">

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
	
	<h3>Card Validation</h3>
	<form action="cardvalidation" method="post">
	<div class="form-group">
	
		<span><label>CardNo</label><br /> <input type="text" 
			name="cardNo" value="<%=cardNo%>" /></span><br />
		<td><button class="btn btn-info" type="submit"><span
										class="glyphicon glyphicon-download-alt"></span>Enter</button></td>
		</div>
	</form>
	</div>

</div>
</body>
</html>