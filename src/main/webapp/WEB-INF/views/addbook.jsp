
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	
<%
	ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
	AdminService service = (AdminService) ac.getBean("adminService");
	List<Author> authors = service.readAllAuthorsWithOutPage();
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
		<h3>Add New Book</h3>
		<form action="addbook" method="post">

			<table class="table">
				<tr>
					<td><label>Title</label></td>
					<td><input type="text" class="form-control" name="title" /></td>
				</tr>
				<tr>

					<td><label>Select Authors</label></td>
					<td><select multiple class="form-control selectpicker" data-live-search="true" data-size="5" name="authorId[]">
							<%	
								for (Author a : authors) {
							%>
							<option name="author" value=<%=a.getAuthorId()%>>
								<%
									out.println(a.getAuthorName());
								%>
							</option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<td><label>Select Genres</label></td>
					<td><select multiple class="form-control selectpicker" data-live-search="true" data-size="5" name="genre_ids[]">
							<%
								for (Genre g : genres) {
							%>
							<option name="genre_id" value=<%=g.getGenreId()%>>
								<%
									out.println(g.getGenreName());
								%>
							</option>
							<%
								}
							%>
					</select></td>
				</tr>
				<tr>
					<td><label>Select Publisher</label></td>
					<td><select class="form-control" name="publisherId">

							<%
								for (Publisher p : publishers) {
							%>
							<option name="publisherId" value=<%=p.getPublisherId()%>>
								<%
									out.println(p.getPublisherName());
								%>
							</option>
							<%
								}
							%>
					</select></td>
				</tr>
			</table>
			<td><button class="btn btn-success" type="submit"><span class="glyphicon glyphicon-plus-sign" ></span>
			Add</button>
		</form>
	</div>
</body>
