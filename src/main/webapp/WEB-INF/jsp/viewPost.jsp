 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>View Post</title>
</head>
<body>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

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
<fb:login-button data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" scope="public_profile,email,user_friends" onlogin="checkLoginState();">
</fb:login-button>
	</nav>
	
<div class="container">
	 View Post
	
	<div class="container" style="margin-top:100px;color:	#A9A9A9">
		<div class="row">
			<figure class="figure">
  				<img src ="${imageURL}" class="figure-img img-fluid rounded" >
  				<figcaption class="figure-caption">"${postCaption}"</figcaption>
			</figure>
			<audio autoplay>
				<source src=	"${audioURI}" type= "audio/webm">
			</audio> 
		</div>
		<div class="row">
		<div class="list-group">
		<a:forEach var="i" items="${comments}">
  				<a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
   					 <div class="d-flex w-100 justify-content-between">
     				 <h5 class="mb-1">"${i.commentOwnerName}"</h5>
      				<!-- <small>3 days ago</small> -->
    					</div>
   					 <p class="mb-1">"${i.commentText}"</p>
  				</a>
  		</a:forEach>
  
  
</div>
</div>
<%--  <c:out value = "${sessionScope.adminloggedin}"/> --%>
<a:if test="${val1=='yes'}">
<div class="row">
	<a href="/AdminDelete?postID=${postID}" > 
				<button type="button" class="btn btn-danger">Delete Post</button>
	</a> 
</div>
</a:if> 


	<div class="row">
			<button type="button" class="btn btn-info" onclick="document.getElementById('id01').style.display='block'">Add your Comment</button>
			<div id="id01" class="modal" tabindex="-1" role="dialog">
			<form class="modal-content animate" action="/commentSave" method="POST">
  				<div class="modal-dialog" role="document">
    				<div class="modal-content">
      	<div class="modal-header">
        		<h5 class="modal-title">Type your comment</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p><input type="text" placeholder="Caption" id="commentText" name="commentText"></p>
        <input type="hidden" id="postID" name="postID" value="${postID}"/>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" type="submit">Save Comment</button>
        <button type="button" class="btn btn-secondary" onclick="document.getElementById('id01').style.display='none'" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
  </form>
</div>
		</div>
	</div>
	
</div>
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      testAPI();
    } else {
      // The person is not logged into your app or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '210919616349720',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });

  };

  // Load the SDK asynchronously
  (function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=id,name,email', function(response) {
      console.log(response);
      $('[name="myId"]').val(response.id);
      $('[name="myName"]').val(response.name);
      $('[name="myEmail"]').val(response.email);
      
      FB.api('/me/friends',function(response) {
      	console.log(response);
      	response.data.forEach(function (ele,i){
      		$("#tableBody").append(
      				'<tr><th scope="row"'+i+'</th>'+'<td><a href="/FriendProfile?friendId='+ele.id+'" >'+ ele.name +'</a></td>'+'<td>'+ele.id+'</td>'+'<tr> '
      				);
      		var earlierVal = $('[name="myFriends"]').val();
      		$('[name="myFriends"]').val(earlierVal+ele.id+"/"+ele.name+"/");
      	});
     /*  $("#redirectForm").submit(); */
      });
    });
    
    
  }
</script>
</body>
</html>