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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>Blog-list</title>
</head>
<body onload="javascript:loadComments('${blogDetails.id}')">

	<jsp:include page="common/header.jsp"></jsp:include>
	<div class="row" style="margin-left:10px">

		<!-- Post Content Column -->
		<div class="col-lg-8">

			<!-- Title -->
			<h1 class="mt-4">${ blogDetails.title }</h1>


			<hr>

			<!-- Date/Time -->
			<p>
				Posted on
				<fmt:formatDate type="both" value="${blogDetails.createdTime}" />
			</p>
			<p>
				Author:<b>${blogDetails.authorName}</b>
			</p>

			<hr>
			<hr>
			<!-- Post Content -->
			<p class="lead">${ blogDetails.description }</p>
			<hr>

			<c:if
				test="${sessionUser != null &&  sessionUser.id != blogDetails.userId}">
				<!-- Comments Form -->
				<div class="card my-4" id="commentsDiv">
					<h5 class="card-header">Leave a Comment:</h5>
					<div class="card-body">
							<div class="form-group">
								<textarea id="comments" class="form-control" rows="3"></textarea>
							</div>
							<input type="hidden" id="blogId" value="${ blogDetails.id}">
							<input type="button" class="btn btn-primary" onclick="javascript:postComments()" value="Submit"/>
					</div>
				</div>
			</c:if>


			
			<div id="user-comments">
			</div>
		</div>


	</div>

</body>
</html>