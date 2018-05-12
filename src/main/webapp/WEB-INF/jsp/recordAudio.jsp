
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Audio-only Example</title>
  <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

  <link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
 <link href="/resources/node_modules/video.js/dist/video-js.min.css" rel="stylesheet">
  <link href="/resources/node_modules/videojs-record/dist/css/videojs.record.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>

  <style>
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  </style>
    <style>
  /* change player background color */
  #myImage {
      background-color: #efc3e6;
  }
  </style>
</head>
<body>
<nav class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="#"> <img src="logo.png" width="30"
			height="30" class="d-inline-block align-top" alt=""> Media App
		</a>
		<a class="nav-link" href="/">Edit Profile <span class="sr-only">(current)</span></a>
     
        <a class="nav-link" href="/FriendsList">FriendList <span class="sr-only">(current)</span></a>
         <a href="/viewNotification" >    <button type="button" class="btn btn-primary" >
  Notifications <span class="badge badge-light"></span>
</button></a> 
<fb:login-button data-max-rows="1" data-size="large" data-button-type="continue_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="false" scope="public_profile,email,user_friends" onlogin="checkLoginState();">
</fb:login-button>
	</nav>
	<div class="container">
	Create Post
		<div class="row">
			<audio id="myAudio" class="video-js vjs-default-skin"></audio>

		</div>
		<div class="row">
		
		<video id="myImage" class="video-js vjs-default-skin"></video>
		</div>
		<!-- <div class="row">
		<label ><b>Caption</b></label>
        <input type="text" placeholder="Caption" id="postCaption" name="postCaption">
		</div> -->
		<div class="row">	
		<form id="audioForm" action="/base64Audio" method="post" >
			<input type="hidden" id="recording" name="recording">
			<input type="hidden" id="post_image" name="post_image">
			<input type="hidden" id="myFriends" name ="myFriends">
			<label ><b>Caption</b></label>
       		 <input type="text" placeholder="Caption" id="postCaption" name="postCaption">
       		 <c:out value = "${myFriends}"/>
       		 
		</form>
		</div>
		<div class="row">
		<button id="saveButton" type="button" class="btn btn-info">save</button>
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
      /* document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!'; */
      $('[name="myId"]').val(response.id);
      $('[name="myName"]').val(response.name);
      $('[name="myEmail"]').val(response.email);
      
      FB.api('/me/friends',function(response) {
      	console.log(response);
      	response.data.forEach(function (ele,i){
      		$("#tableBody").append(
      				/* '<tr><th scope="row"'+i+'</th>'+'<td><a href="/FriendProfile?friendId='+ele.id+'" >'+ ele.name +'</a></td>'+'<td>'+ele.id+'</td>'+'<tr> ' */
      				);
      		var earlierVal = $('[name="myFriends"]').val();
      		$('[name="myFriends"]').val(earlierVal+ele.id+"/"+ele.name+"/");
      		console.log($('[name="myFriends"]'));
      	});
     /*  $("#redirectForm").submit(); */
      });
    });
    
    
  }
</script>
<script>
var player1 = videojs("myAudio", {
    controls: true,
    width: 600,
    height: 300,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

// error handling
player1.on('deviceError', function() {
    console.log('device error:', player1.deviceErrorCode);
});

// user clicked the record button and started recording
player1.on('startRecord', function() {
    console.log('started recording!');
});

// user completed recording and stream is available
player1.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player1.recordedData);
    console.log(player1.recordedData);
    var reader = new FileReader();
    var base64data;
    reader.readAsDataURL(player1.recordedData);
    reader.onloadend= function() {
    	base64data= reader.result;
        console.log(base64data);
        $("#recording").val(base64data);
    }  
});

$(document).ready(function(){
	$("#saveButton").on("click", function(){
		$("#audioForm").submit();
	});
});
</script>
<script>
var player = videojs("myImage", {
    controls: true,
    width: 320,
    height: 240,
    controlBar: {
        volumePanel: false,
        fullscreenToggle: false
    },
    plugins: {
        record: {
            image: true,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'));
});

// error handling
player.on('deviceError', function() {
    console.warn('device error:', player.deviceErrorCode);
});

// snapshot is available
player.on('finishRecord', function() {
    // the blob object contains the image data that
    // can be downloaded by the user, stored on server etc.
   
    	console.log('snapshot ready: ', player.recordedData);
   	 	console.log(player.recordedData);
   	 var base64data;
        	base64data= player.recordedData;	
            console.log(base64data);
            $("#post_image").val(base64data);
        
    
});

	$(document).ready(function(){
		$("#saveButton").on("click", function(){
			$("#audioForm").submit();
		});
	});
</script>


</body>
</html>
