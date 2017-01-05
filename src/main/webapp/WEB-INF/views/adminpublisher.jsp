<%@ include file="index.html"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    AdminService service = (AdminService) ac.getBean("adminService");
    List<Publisher> publisher = service.readPublisher();
    %>
<div class="container theme-showcase" role="main">
  <div class="jumbotron">
<h3>Administrator Publisher Management</h3>
	<button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal" onclick="javascript:location.href='addpublisher'"><span class="glyphicon glyphicon-plus-sign"></span>Add publisher</button>
<table class="table" id="branchesTable">
	<tr>
		<th>#</th>
		<th>Publisher Id</th>
		<th>Publisher Name</th>
		<th>Publisher Address</th>
		<th>Publisher Phone</th>
		<th></th>
		<th></th>
	</tr>
	<%for(Publisher p: publisher){ %>
	<tr>
	<td><%=publisher.indexOf(p)+1%>
	<td><%=p.getPublisherId()%></td>
	<td><%=p.getPublisherName() %></td>
	<td><%=p.getPublisherAddress() %></td>
	<td><%=p.getPublisherPhone() %></td>
	
	<td><a class="btn btn-info" href="updatepublisher?publisherId=<%=p.getPublisherId()%>"><span class="glyphicon glyphicon-edit"></span>Edit</a>
						<td><a class="btn btn-danger" href="deletepublisher?publisherId=<%=p.getPublisherId()%>"><span class="glyphicon glyphicon-remove-circle"></span>Delete</a></td>
	</tr>
	<%} %>

</table>
</div>
</div>
<div class="modal fade bs-example-modal-lg" id="editauthormodal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>