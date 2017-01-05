<%@include file="template.html"%>

<div class="container theme-showcase" role="main">

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h1>GCIT Library Management System</h1>
		<p>Welcome to GCIT Library Management System. Have Fun Shopping!</p>
		<h3>Hello Admin! Add Author</h3>
		<form action="addAuthor" method="post">
			<span> Enter Author Name below: <input type="text"
				name="authorName">
			</span><br />
			<button type="submit" class="btn btn-info">Add Author</button>
		</form>
	</div>

</div>
<!-- /container -->
