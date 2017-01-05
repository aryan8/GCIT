<%@page import="com.gcit.lms.entity.Borrower"%>
<%@ include file="index.html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    %>
<body>
	<div class="container theme-showcase" role="main">

<div class="jumbotron">
			<h1>GCIT Library Management System</h1>
			<h3>Add New Borrower</h3>
			<form action="addborrower" method="post">
				<span>
				 <label>Borrower Name:</label><br />
				  <input  type="text" name="borrowerName" /></span><br />
				 <span>
				 <label>Borrower Address:</label><br />
				  <input  type="text" name="borrowerAddress" /></span><br />
				  <span>
				 <label>Borrower Phone:</label><br />
				  <input  type="text" name="borrowerPhone" /></span><br />
				<td><button class="btn btn-success" 
						type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>ADD</button>
			</form>
		</div>
	</div>
	</body>


	