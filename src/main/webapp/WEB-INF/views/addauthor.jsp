<%@ include file="index.html"%>

<%@page import="com.gcit.lms.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    %>
<body>
	<div class="container theme-showcase" role="main">

<div class="jumbotron">
			<h1>GCIT Library Management System</h1>
			<h3>Add New Author</h3>
			<form action="addauthor" method="post">
				<span>
				 <label>Author Name:</label><br />
				  <input  type="text" name="authorName" /></span><br />
				
				<td><button class="btn btn-success" 
						type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>Add</button>
			</form>
		</div>
	</div>
	</body>


	