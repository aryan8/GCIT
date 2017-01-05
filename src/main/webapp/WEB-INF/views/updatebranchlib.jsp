<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*    LibrarianService service = new  LibrarianService();
	   Branch branch = new Branch();
	   branch.setBranchId(Integer.parseInt(request.getParameter("branchId"))); */
	   ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	   LibrarianService service = (LibrarianService) ac.getBean("librarianService"); 
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
			<h3>Branch Management</h3>
			<form action="editbranch" method="post">
				<span>
				 <label>Branch Name</label><br />
					 <input type="text"
					name="branchName" value="<%=branch.getBranchName()%>" />
				</span><br /> <span><label>Address</label><br /> <input
					type="text" name="branchAddress"
					value="<%=branch.getBranchAddr()%>" /> </span><br /> <span> <input
					type="hidden" name="branchId" value="<%=branch.getBranchId()%>" /></span>
				<td><button class="btn btn-success" type="submit">Submit</button>
			</form>
		</div>
	</div>

</body>
</html>