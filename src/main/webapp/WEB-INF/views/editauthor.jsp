<%@ include file="index.html"%>

<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<div class="container theme-showcase" role="main">

		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h3>Edit Author</h3>
			<form action="editauthor" method="post">
				<span> <label>Author Name</label><br /> <input type="text"
					name="authorName" value="${author.getAuthorName()}" />
				</span>
					 <span> <input
					type="hidden" name="authorId" value="${author.getAuthorId()}" /></span>
				<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal"
						onclick="javascript:location.href='editauthor?authorId=${author.getAuthorId()}&publisherName=${author.getAuthorName()}'">Update</button>
			</td>
			</form>
		</div>
	</div>

</body>
</html>