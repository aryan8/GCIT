<%@ include file="index.html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Integer cardNo= Integer.parseInt(request.getParameter("cardNo"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Borrower Management</title>
</head>
<body>
	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h3>Borrower Mode</h3>
			<span>
				<div class="col-sm-4">
					<div class="list-group">
						<a href="viewbranch?cardNo=<%=cardNo%>"
							class="list-group-item active">Check Out</a> <a
							href="viewbranchreturn?cardNo=<%=cardNo %>"
							class="list-group-item ">Return</a>
					</div>
				</div>
			</span>
		</div>

	</div>
</body>
</html>