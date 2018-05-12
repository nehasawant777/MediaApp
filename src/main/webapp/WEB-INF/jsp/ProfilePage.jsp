<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Profile Page</title>
</head>
<body>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img src="logo.png" width="30"
			height="30" class="d-inline-block align-top" alt=""> Media App
		</a>
      
      <a:set var="val1" value="${sessionScope.adminloggedin}"></a:set>
      <a:if test="${val1=='no'}">
      
        <a class="nav-link" href="/">Edit Profile <span class="sr-only">(current)</span></a>
     
        <a class="nav-link" href="/FriendsList">FriendList <span class="sr-only">(current)</span></a>
        
        <a href="/viewNotification" >    <button type="button" class="btn btn-primary" >
  Notifications <span class="badge badge-light"></span>
</button></a>
</a:if> 
<a:if test="${val1=='yes'}">
<a class="nav-link" href="/AllUsers">All Users <span class="sr-only">(current)</span></a>
</a:if> 
   
</nav>


<div class="container">
	 Profile Page
	
	<div class="row">
  		<div class="col-6 col-sm-3"><img src="${imgSrc}" class="img-thumbnail" alt="profile page image" height="300" width="300"></div>
  		<div class="col-6 col-sm-3">
  			<div class="row"><h1>"${profile_name}"</h1></div>
  			<div class="row">${profile_desc}"</div>
  		</div>
		<div class="container">
			<div class="row">
				<a:forEach var="i" items="${postList}">
					<div class="col-6 col-sm-3">
				 		<a href="/viewPost?postID=${i.postId}" > 
				 			<%-- <c:out value = "${i.postId}"/> --%>
				 				<img src="${i.imgUrl}" class="img-thumbnail"   alt="profile page image" height="300" width="300">
						 </a> 
					</div>
				</a:forEach>
			</div>

		</div>
	</div>
	<a:set var="val1" value="${ownProfile}"></a:set>
	<%-- <c:out value = "${ownProfile}"/> --%>
	  <a:if test="${val1 == 'Yes'}">
			<div class="row">
				<button type="button" class="btn btn-info"><a href="/recordAudio" > Create New Post</a> </button>
			</div>
	   </a:if>
</div>


</body>
</html>