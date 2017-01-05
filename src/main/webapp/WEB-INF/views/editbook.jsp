
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.gcit.lms.entity.Publisher"%>

<%@page import="com.gcit.lms.service.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%

ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
AdminService service = (AdminService) ac.getBean("adminService");
	Book b = (Book)request.getAttribute("book");
	List<Author> au = service.readAllBooksAuthors(b.getBookId());

	HashSet<Integer> selectedAuthors = new HashSet<Integer>();
	for(Author a: au){
		selectedAuthors.add(a.getAuthorId());
	}
	
	List<Genre> ge = service.readBooksGenres(b.getBookId());
	HashSet<Integer> selectedGenres = new HashSet<Integer>();
	for(Genre g: ge){
		selectedGenres.add(g.getGenreId());
	}
	
	List<Author> authors = service.readAllAuthors(0);
	List<Genre> genres = service.readAllGenre();
	List<Publisher> publishers = service.readPublisher();
%>
<script>
	$(document).ready(function () {
		$('.selectpicker').selectpicker({
			style: 'btn-default',
			size: 4
				});
			});
</script>

	<body>
		<div class="container theme-showcase" role="main">
	
	<div class="jumbotron">
			<h3>Edit Book</h3>
				<form action="updatebook" method="post">
				<input type="hidden" name="bookId" value="<%=b.getBookId() %>"></input>
			
		<table class="table">
			<tr>
				<td><label>Title</label></td>
				<td><input type="text" class="form-control" name="title" value="<%=b.getTitle() %>"></td>
			</tr>
			<tr>
			
				<td><label>Select Authors</label></td>
				<td><select class="form-control selectpicker" data-live-search="true" data-size="5"name="authorIds[]">
				<%for(Author a : authors){ 
					if(selectedAuthors.contains(a.getAuthorId())){%>
						<option selected value=<%=a.getAuthorId() %>><%out.println(a.getAuthorName());%></option>
					<%}else{ %>
						<option value=<%=a.getAuthorId() %>><%out.println(a.getAuthorName());%></option>
					<%} %>		
				<%} %>
				</select></td>
			</tr>
			<tr>
				<td><label>Select Genres</label></td>
				<td><select class="form-control selectpicker" data-live-search="true" data-size="5" name="genreIds[]">
				<%for(Genre g : genres){ 
					if(selectedGenres.contains(g.getGenreId())){%>
						<option selected value=<%=g.getGenreId()%>><%out.println(g.getGenreName());%></option>
					<%}else{ %>
						<option value=<%=g.getGenreId()%>><%out.println(g.getGenreName());%></option>
					<%} %>
				<%} %>
				</select></td>
			</tr>
			<tr>
				<td><label>Select Publisher</label></td>
				<td><select class="form-control" name="publisherId">

				<%for(Publisher p: publishers){ 
					if(p.getPublisherId() == b.getPublisher().getPublisherId()){%>
						<option selected value=<%=p.getPublisherId()%>><%out.println(p.getPublisherName());%></option>
					<%}else{ %>
						<option value=<%=p.getPublisherId()%>><%out.println(p.getPublisherName());%></option>
					<%} %>
				<%} %>
				</select></td>
			</tr>
		</table>
				<td><button class="btn btn-success" 
						type="submit"><span
										class="glyphicon glyphicon-edit"></span>Update</button>
						<div class="container">
  
 
		
	</form>
	</div>
	</body>
