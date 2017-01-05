<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>


<script type="text/javascript">

function searchAuthors(pageNo){
	var searchString = $('#searchString').val() || "";
	
	console.log(searchString);
	
	$.ajax({
		  url: "searchauthors",
		  data: { 
			  searchString: searchString,
			  pageNo: pageNo
		  }
		}).done(function(data) {
		 	$("#authorsTable").html(data);
		});
}

searchAuthors(1);

</script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
</head>
<body>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h3>Administrator Author Management</h3>

			<div class="input-group">
				<input type="text" class="form-control" placeholder="Author Name"
					aria-describedby="basic-addon1" name="searchString"
					id ="searchString" onkeyup="searchAuthors(1)">
			</div>
			<button class="btn btn-success" data-toggle="modal"
				data-target="#editauthormodal"
				onclick="javascript:location.href='addauthor'">Add
				author</button>

			<div id="authorsTable">
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="editauthormodal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content"></div>
		</div>
	</div>
</body>
</html>
<%-- 			<table class="table">
				<tr>
					<th>#</th>
					<th>Author Id</th>
					<th>Author Name</th>

					<th></th>
					<th></th>
				</tr>
				<%
					int counter = 1;
					for (Author p : authors) {
				%>
				<tr>
					<td><%=counter++ + ((pageNo - 1) * 10)%>
					<td><%=p.getAuthorId()%></td>
					<td><%=p.getAuthorName()%></td>


					<td><a class="btn btn-info"
						href="UpdateAuthor?authorId=<%=p.getAuthorId()%>">Edit</a>
					<td><a class="btn btn-danger"
						href="DeleteAuthor?authorId=<%=p.getAuthorId()%>">Delete</a></td>
				</tr>
				<%
					}
				%>

			</table> --%>