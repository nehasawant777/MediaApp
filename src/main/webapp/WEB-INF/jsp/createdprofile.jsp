 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Home</title>
</head>
<body>

	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

	<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img src="logo.png" width="30"
			height="30" class="d-inline-block align-top" alt=""> Media App
		</a>
	</nav>
	<div class="container">
	 Create Profile Page
	</div>
	<div class="container" style="margin-top:100px;color:	#A9A9A9">
		<form action="/upload" method="POST" enctype="multipart/form-data">
			<div class="form-group">
    				<label for="profile_name">Profile Name:</label>
    				<input type="profile_name" class="form-control" name="profile_name" id="profile_name" value="${profile_name}">
  			</div>
  			<div class="form-group">
    				<label for="profile_desc">Profile Description:</label>
   				 <input type="profile_desc" class="form-control" name="profile_desc" id="profile_desc" value="${profile_desc}">
  			</div>
  			
  			<div class="form-group">
    				<img alt="Profile Page Image" src="${profile_picture}" height="300"

				width="300" class="img-fluid img-thumbnail">
  			</div>
			
		</form>
	</div>

</body>
</html>