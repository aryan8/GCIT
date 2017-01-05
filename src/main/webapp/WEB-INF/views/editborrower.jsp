<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*    LibrarianService service = new  LibrarianService();
	   Branch branch = new Branch();
	   branch.setBranchId(Integer.parseInt(request.getParameter("branchId"))); */

	Borrower borrower = (Borrower) request.getAttribute("borrower");
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
			
			<h3>Edit Borrower</h3>
			<form action="editborrower" method="post">
				<span> <label>Borrower Name</label><br /> <input type="text"
					name="borrowerName" value="<%=borrower.getName()%>" />
				</span><br /> <span><label>Borrower Address</label><br /> <input
					type="text" name="borrowerAddress"
					value="<%=borrower.getAddress()%>" /> </span><br />
					<span><label>Borrower Phone</label><br /> <input
					type="text" name="borrowerPhone"
					value="<%=borrower.getPhone()%>" /> </span><br />
					 <span> <input
					type="hidden" name="cardNo" value="<%=borrower.getCardNo()%>" /></span>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal"
						onclick="javascript:location.href='editborrower?cardNo=<%=borrower.getCardNo()%>&borrowerName=<%=borrower.getName()%>&borrowerAddress=<%=borrower.getAddress()%>&borrowerPhone=<%=borrower.getPhone()%>'">Update</button>
			</form>
		</div>
	</div>

</body>
</html>