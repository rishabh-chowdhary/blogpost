<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Static content -->
<link rel="stylesheet" href="/resources/css/style.css">
<link rel="stylesheet" href="/resources/css/bootstrap-min.css">
<script type="text/javascript" src="/resources/js/app.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
<title>Post Your Blog</title>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<h1>Post Your Blog</h1>
	<hr>
	<div style="float: left;padding: 10px; width: 100%">
		<div class="row">
			<form action="postBlog" method="post">
				<div class="col-md-6">
					<div class="form-group">
						<input type="text" id="blog_title" class="form-control"
							placeholder="title *" value="">
					</div>
					<div class="form-group">
						<textarea id="description" class="form-control"
							placeholder="Description" style="width: 100%; height: 150px;"></textarea>
					</div>
					<div class="form-group">

                        <input type="button" class="btn btn-primary" onclick="javascript:postBlog()" value="Submit"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>