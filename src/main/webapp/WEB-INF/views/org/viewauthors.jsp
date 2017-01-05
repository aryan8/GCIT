<%@include file="template.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("service"); 

	//AdminService service = new AdminService();
	Integer count = service.getAuthorsCount();
	Integer pages = 1;
	if (count % 10 > 0) {
		pages = (count / 10) + 1;
	} else {
		pages = (count / 10);
	}
	List<Author> authors = new ArrayList<Author>();
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = service.readAllAuthors(1, null);
	}
%>
<script type="text/javascript">

function searchAuthors(){
	
	$.ajax({
		  url: "searchAuthors",
		  data: 
		  { 
			  searchString: $('#searchString').val()
		  }
		}).done(function(data) {
		 	$("#authorsTable").html(data);
		});
}


</script>
<div class="container theme-showcase" role="main">
	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h1>GCIT Library Management System</h1>
		<p>Welcome to GCIT Library Management System. Have Fun Shopping!</p>
		<h3>Hello Admin! What do you want to do?</h3>
		<form action="searchAuthors" method="post">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Author Name"
					aria-describedby="basic-addon1" name="searchString" id="searchString" onkeypress="">
				
			</div>
		</form>

		<nav aria-label="Page navigation">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<%
					for (int i = 1; i <= pages; i++) {
				%>
				<li><a href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
				<%
					}
				%>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<table class="table" id="authorsTable">
			<tr>
				<th>#</th>
				<th>Author Name</th>
				<th>Edit Author</th>
				<th>Delete Author</th>
			</tr>
			<%
				for (Author a : authors) {
			%>
			<tr>
				<td><%=authors.indexOf(a) + 1%></td>
				<td><%=a.getAuthorName()%></td>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal"
						href="editauthor.jsp?authorId=<%=a.getAuthorId()%>">Edit</button></td>
				<td><button class="btn btn-danger"
						onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
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
