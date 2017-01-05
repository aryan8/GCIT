<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*    LibrarianService service = new  LibrarianService();
	   Branch branch = new Branch();
	   branch.setBranchId(Integer.parseInt(request.getParameter("branchId"))); */

	Branch branch = (Branch) request.getAttribute("branch");
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
		
			<h3>Edit Branch</h3>
			<form action="updatebranch" method="post">
		<span> <label>Branch Name</label><br /> <input type="text"
					name="branchName" value="<%=branch.getBranchName()%>" />
				</span><br /> <span><label>Branch Address</label><br /> <input
					type="text" name="branchAddress"
					value="<%=branch.getBranchAddr()%>" /> </span><br />
					 <span> <input
					type="hidden" name="branchId" value="<%=branch.getBranchId()%>" /></span>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal"
						onclick="javascript:location.href='updatebranch?branchId=<%=branch.getBranchId()%>&branchName=<%=branch.getBranchName()%>&branchAddress=<%=branch.getBranchAddr()%>'">Update</button>
			</form>
		</div>
	</div>

</body>
</html>