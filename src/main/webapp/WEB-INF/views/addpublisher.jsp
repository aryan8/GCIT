<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ include file="index.html"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    
    %>
<body>
<div class="jumbotron">
			<h1>GCIT Library Management System</h1>
			<h3>Add New Publisher</h3>
			<form action="addpublisher" method="post">
				<span>
				 <label>Publisher Name:</label><br />
				  <input  type="text" name="publisherName" /></span><br />
				 <span>
				 <label>Publisher Address:</label><br />
				  <input  type="text" name="publisherAddress" /></span><br />
				  <span>
				 <label>Publisher Phone:</label><br />
				  <input  type="text" name="publisherPhone" /></span><br />
				<td><button class="btn btn-success" 
						type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>Add</button>
			</form>
		</div>
	</div>
	</body>


	