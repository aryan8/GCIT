
<%@ include file="index.html"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    LibrarianService service= new LibrarianService();
    List<Branch>  branches = service.readBranch();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Management</title>
</head>
<body>
<div class="container theme-showcase" role="main">
  <div class="jumbotron">
<h1>GCIT Library Management System</h1>
<h3>Librarian Manual</h3>

<table class="table" id="branchesTable">
	<tr>
		<th>Branch Id</th>
		<th>Branch Name</th>
		<th>Branch Address</th>
		<th></th>
		<th></th>
	</tr>
	<%for(Branch b: branches){ %>
	<tr>
	<td><%=branches.indexOf(b)+1%>
	<td><%=b.getBranchName() %></td>
	<td><%=b.getBranchAddr() %></td>
	<td><button class="btn btn-success" data-toggle="modal"
						data-target="#editauthormodal" onclick="javascript:location.href='updateBranchLib?branchId=<%=b.getBranchId()%>&branchName=<%=b.getBranchName()%>&branchAddress=<%=b.getBranchAddr()%>'">Update</button>
	<td><button class="btn btn-info" data-toggle="modal"
						data-target="#editauthormodal" onclick="javascript:location.href='addCopyBranchLib?branchId=<%=b.getBranchId()%>&branchName=<%=b.getBranchName()%>&branchAddress=<%=b.getBranchAddr()%>'">Add Copy</button>
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
</body>
</html>