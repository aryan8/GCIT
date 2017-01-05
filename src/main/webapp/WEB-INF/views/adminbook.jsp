
<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>


<% 
	String message = "";
	if(request.getAttribute("message") != null) {
		message = (String) request.getAttribute("message");
	}
%>


<%-- 	<% 
	ApplicationContext ac =RequestContextUtils.getWebApplicationContext(request); AdminService
	service = (AdminService) ac.getBean("adminService"); 
	List<Book> book = service.readAllBooks();
	%>


<body>
	<div class="container theme-showcase" role="main">
		<div class="jumbotron">
			<h3>Administrator Book Management</h3>
			<button class="btn btn-success" data-toggle="modal"
				data-target="#editauthormodal"
				onclick="javascript:location.href='addbook'">
				<span class="glyphicon glyphicon-plus-sign"></span>Add Book
			</button>
			<table class="table" id="bookTable">
				<tr>
					<th>#</th>
					<th>Book Name</th>
					<th>Author Name</th>
					<th>Genre</th>
					<th>Publisher</th>
					<th></th>
					<th></th>
				</tr>
				<%
					for (Book b : book) {
				%>
				<tr>
					<td><%=book.indexOf(b) + 1%> 
					<td><%=b.getTitle()%></td>
					<td>
						<%
					
						 		

									for (int i = 0; i < b.getAuthors().size(); i++){
										out.print(b.getAuthors().get(i).getAuthorName() + " - ");
								}
					
								%>
					</td>
					<td>
					<% 
								for (int i = 0; i < b.getGenres().size(); i++) {
							
									out.print(b.getGenres().get(i).getGenreName() + " - "); 
								}
							%>
						</td>
						<td>
						<%
						
								out.print(b.getPublisher().getPublisherName() + " - ");
						
							
						%>
						</td>
					<td><a class="btn btn-info"
						href="updatebook?bookId=<%=b.getBookId()%>"><span
							class="glyphicon glyphicon-edit"></span>Edit</a>
					
					<td><a class="btn btn-danger"
						href="deletebook?bookId=<%=b.getBookId()%>"><span
							class="glyphicon glyphicon-remove-circle"></span>Delete </a></td>
							<td></td>
				</tr>
				<%
					}
				%>

			</table>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="editauthormodal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content"></div>
		</div>
	</div>
</body> --%>





<script type="text/javascript">
function searchBooks(){
	var searchString = $('#searchString').val() || "";
	
	console.log(searchString);
	
	$.ajax({
		  url: "searchbooks",
		  data: { 
			  searchString: searchString
		  }
		}).done(function(data) {
		 	$("#booksTable").html(data);
		});
}
searchBooks();

function getBookDetails(bookId) {
	var url = "updatebook";
	
	if(bookId == 0) {
		url = "addbook";
	}
	
	$.ajax({
		url: url,
		data: {
			bookId: bookId
		}
	}).done(function(data){
		$('#bookContent').html(data);
	});
}

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
			<h3>Administrator Book Management</h3>

			<div class="input-group">
				<input type="text" class="form-control" placeholder="Book Name"
					aria-describedby="basic-addon1" name="searchString"
					id ="searchString" onkeyup="searchBooks()">
			</div>
			<button class="btn btn-success" data-toggle="modal"
				data-target="#editauthormodal"
				onclick="getBookDetails(0)">Add Book</button>

			<% if(!message.isEmpty()) { %>
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Great! </strong> ${message}
			</div>
			<% } %>
			<div id="booksTable">
			</div>
		</div>
	</div>
	<div class="modal fade bs-example-modal-lg" id="editauthormodal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" id="bookContent"></div>
		</div>
	</div>
</body>
</html>  