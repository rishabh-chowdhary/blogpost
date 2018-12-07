<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header">
    <div style="width:100%">
        <c:choose>

            <c:when test = "${sessionUser != null}">
                <input style="align:right;float:right;margin-right:10px" id="logout" type="button" class="btn btn-primary" onclick="javascript:logout()" value="Logout"/>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>

    </div>

	<div class="user-header-left">
        <a href="blog" style="text-align:center;margin-left:10px">Post New Blog<c:if test="${sessionUser == null}"> (Login Required)</c:if>
        </a>

	</div>

 	<div class="user-header" style="width:100%;text-align: center;">
 	 <c:choose>
         
         <c:when test = "${sessionUser != null}">
            <span style="font-weight: bold;">Welcome,  ${sessionUser.name}!</span>
             <p>
             <a href="index">Home</a>
             </p>
         </c:when>
         <c:otherwise>
            <span style="font-weight: bold;">Welcome, Guest!</span>
             <a href="login">login here</a>
         </c:otherwise>
      </c:choose>
 	</div>

</div>