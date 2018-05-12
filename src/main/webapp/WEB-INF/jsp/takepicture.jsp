
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Image-only Example - Record Plugin for Video.js</title>
  

 <link href="/resources/node_modules/video.js/dist/video-js.min.css" rel="stylesheet">
  <link href="/resources/node_modules/videojs-record/dist/css/videojs.record.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>

  <script>
  // disable analytics tracking (note: used in vjs.zencdn.net CDN-hosted version
  // of video.js only, see https://github.com/videojs/video.js#quick-start)
  /* window.HELP_IMPROVE_VIDEOJS = false; */
  </script>

  <style>
  /* change player background color */
  #myImage {
      background-color: #efc3e6;
  }
  </style>
</head>
<body>

<video id="myImage" class="video-js vjs-default-skin"></video>

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
            $("#recording").val(base64data);
        
    
});

	$(document).ready(function(){
		$("#saveButton").on("click", function(){
			$("#audioForm").submit();
		});
	});
</script>
<form id="audioForm" action="/base64Audio" method="post" >
<input type="hidden" id="recording" name="123">
</form>
<button id="saveButton">save</button>

</body>
</html>
