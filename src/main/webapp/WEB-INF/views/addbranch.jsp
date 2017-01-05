<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    %>
<body>
	<div class="container theme-showcase" role="main">

<div class="jumbotron">
			<h1>GCIT Library Management System</h1>
			<h3>Add New BRracnh</h3>
			<form action="addbranch" method="post">
				<span>
				 <label>Branch Name:</label><br />
				  <input  type="text" name="branchName" /></span><br />
				 <span>
				 <label>Branch Address:</label><br />
				  <input  type="text" name="branchAddress" /></span><br />
				  <span>
				<td><button class="btn btn-success" 
						type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>Add</button>
			</form>
		</div>
	</div>
	</body>


	