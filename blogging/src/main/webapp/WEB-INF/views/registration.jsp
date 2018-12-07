<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap-min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/resources/js/app.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-3.3.1.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</head>

<body>
<div class="user-header-left">
    <a href="login" style="text-align:center;margin-left:10px">Login Page</a>
</div>
<div class="container">

    <form method="POST" action="${contextPath}/users/save" class="form-signin">
        <h2 class="form-heading">Create a new account</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <!--<span>${message}</span>-->
            <input id="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input id="password" type="password" class="form-control" placeholder="Password"/>
            <input id="email" type="text" class="form-control" placeholder="Email"/>
            <%--<span class="form-control" style="width:100%; margin-top:5px;display: inline-block;">${error}</span>--%>
            <%--<input type="button" class="btn btn-primary" type="submit" value="Log In" style = "width:100%"/>--%>
            <input type="button" class="btn btn-primary" onclick="javascript:register()" value="Register"/>
            <%--<button style="width:100%" type="submit" class="btn btn-block">Register</button>--%>
        </div>
    </form>
</div>
<!-- /container -->

</body>
</html>