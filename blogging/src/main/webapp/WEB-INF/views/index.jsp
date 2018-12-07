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
	<script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/resources/js/app.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<title>Blog-list</title>
</head>
<body>

	<jsp:include page="common/header.jsp"></jsp:include>

	<c:if test="${blogs !=null }">
		<table class="table table-filter">
			<tbody>
				<c:forEach items="${blogs}" var="blog">
				
				<tr data-status="pagado">
					<td><a href="javascript:;" class="star"> <i
							class="glyphicon glyphicon-star"></i>
					</a></td>
					<td>
						<div class="media">
							<a href="#" class="pull-left"> <img
								src="https://s3.amazonaws.com/uifaces/faces/twitter/fffabs/128.jpg"
								class="media-photo">
							</a>
							<div class="media-body">
								<span class="media-meta pull-right"><fmt:formatDate type = "both"  value = "${blog.updatedTime}" /></span>
								<h4 class="title">
									<a href="/blogDetails?id=${blog.id} ">
									${blog.title} <span class="pull-right pagado"></span>
									</a>
								</h4>
								<p class="summary">${fn:substring(blog.description, 0, 50)}</p>
                                <c:choose>
                                    <c:when
                                            test="${sessionUser != null &&  sessionUser.id == blog.userId}">
                                        <p class="summary">By: <b>me</b></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="summary">By: <b>${blog.authorName}</b></p>
                                    </c:otherwise>
                                </c:choose>

							</div>
						</div>
					</td>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>
	</c:if>

</body>
</html>