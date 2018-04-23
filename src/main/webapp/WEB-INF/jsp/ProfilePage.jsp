<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Profile Page</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

<nav class="navbar navbar-default">
  <div class="container-fluid" align="left">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <a class="navbar-brand" href="#"><img alt="My Personal Page" src="logo.png">Media App</a>
    </div>
    
   
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/">Create Profile <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/FriendsList">FriendList <span class="sr-only">(current)</span></a>
      </li>
      </ul>

    
    
  </div>
</nav>
<div class="container">
	 Profile Page
	</div>
<div class="row">
  <div class="col-6 col-sm-3"><img src="${imgSrc}" class="img-thumbnail" alt="profile page image" height="300" width="300">
  </div>
  <div class="col-6 col-sm-3"><h1>"${profile_name}"
</h1><br>
${profile_desc}"</div>
</div>

</body>
</html>