<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*    LibrarianService service = new  LibrarianService();
	   Branch branch = new Branch();
	   branch.setBranchId(Integer.parseInt(request.getParameter("branchId"))); */

	Publisher publisher = (Publisher) request.getAttribute("publisher");
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
			<h1>GCIT Library Management System</h1>
			<h3>Edit Publisher</h3>
			<form action="updatepublisher" method="post">
				<span> <label>Publisher Name</label><br /> <input type="text"
					name="publisherName" value="<%=publisher.getPublisherName()%>" />
				</span><br /> <span><label>Publisher Address</label><br /> <input
					type="text" name="publisherAddress"
					value="<%=publisher.getPublisherAddress()%>" /> </span><br />
					<span><label>Publisher Phone</label><br /> <input
					type="text" name="publisherPhone"
					value="<%=publisher.getPublisherPhone()%>" /> </span><br />
					 <span> <input
					type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>" /></span>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal"
						onclick="javascript:location.href='updatepublisher?publisherId=<%=publisher.getPublisherId()%>&publisherName=<%=publisher.getPublisherName()%>&publisherAddress=<%=publisher.getPublisherAddress()%>&publisherPhone=<%=publisher.getPublisherPhone()%>'">Update</button>
			</form>
		</div>
	</div>

</body>
</html>