<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% AdminService service = new AdminService();
    Author author = new Author();
    author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
    author = service.readAuthorByPK(author);
    %>

	<h2>Welcome to GCIT Library Management System. Have Fun Shopping!</h2>
	<form action="editAuthor" method="post">
		<span> Enter Author Name to Edit: <input type="text" name="authorName" value='<%=author.getAuthorName()%>'>
		</span><br />
		<span><input type="hidden" size="50px" name="authorId" value='<%=author.getAuthorId() %>'> </span>
		<button class="btn btn-info" type="submit">Edit Author</button>
	</form>
