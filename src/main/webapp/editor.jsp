<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Video cutter</title>
  <script src="https://cdn.tailwindcss.com"></script>
	<!--video-js-->
	<link href="http://vjs.zencdn.net/4.12.2/video-js.css" rel="stylesheet">
	<script src="./js/video.min.js"></script>
	
	<!--RangeSlider Pluging-->
	<script src="./js/rangeslider.js"></script>
	<link href="./css/rangeslider.min.css" rel="stylesheet">
	
    <!--Demo CSS-->
	<link href="demo.css" rel="stylesheet">

  </head>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }
    body {
      padding: 0;
    }
  </style>
  <body>

    <div class="header bg-[#5c7099] flex h-[56px] justify-between text-white mb-5 px-3">
      <div class="flex gap-2 items-center">
        <p class="font-bold text-lg">FORUMZ</p>
        <div class="ml-5">
          <p>Trang chủ</p>
        </div>
      </div>
      <div class="nav-link__right flex items-center gap-5">
        <div class="">
          <div class="bg-white py-1 px-3 rounded-md">
            <input class="border-none bg-transparent text-gray-900" placeholder="Tìm kiếm..." type="text">
          </div>
        </div>
            <div class="flex gap-1 items-center">
              <a href='/forum/create-post' class="bg-[var(--blue-100)] text-white mr-5 rounded-md px-3 py-1">
              Đăng bài
            </a>
            <p>av</p>
              <a href="/forum/me"><img class="avatar" src="" alt="avatar" /></a>
            </div>
           <div class="nav-link__right-action">
             <a class="nav-link__right-action--btn" href="/forum/login.jsp">Login</a>
             <a class="nav-link__right-action--btn" href="/forum/register.jsp">Register</a>
           </div>
      </div>
    </div>


	

	<div class="w-full flex flex-col items-center justify-center">
    <p class="block text-3xl font-bold mb-5 text-[#f85a38]">VIDEO CUTTER✂️</p>
		<div>
      <div class="relative w-[940px] h-[464px]">
        <div id="require-select" class="w-full h-full absolute top-0 left-0 bg-[#dce7f5] z-10 flex
          justify-center items-center text-lg font-medium">Please select video💻</div>
        <video id="vid1"  class="video-js vjs-default-skin" controls preload="none" width="940" height="464"
        poster="https://w0.peakpx.com/wallpaper/675/542/HD-wallpaper-youtube-thumbnail-geometric-black.jpg"
        data-setup=''>
          <!-- <source src="" id="src-video" type='video/mp4' /> -->
          <!-- <source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />
          <source src="http://video-js.zencoder.com/oceans-clip.ogv" type='video/ogg' /> -->
          <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
        </video>
      </div>
      <p class="text-lg my-5 font-bold text-[#f85a38]">Select a section of the video🪄</p>
       <div class="flex flex-col gap-2">
        <div class="flex gap-3">
          <div class="button bg-[#5c7099] w-fit text-white px-2 py-1 rounded-md" onClick="playBetween()">Play Between</div>
          <div class="button bg-[#5c7099] w-fit text-white px-2 py-1 rounded-md" onClick="loopBetween()">Loop Between</div>
          <div class="button bg-[#5c7099] w-fit text-white px-2 py-1 rounded-md" onClick="getValues()">Get Arrow Values</div><br/>
       </div>
       <div>
        Start (seconds):<input type="text" value="2" id="Start">
        End (seconds): <input type="text" value="5" id="End">
       </div>
       <form id="myForm" action.prevent="" method = "post"  class="flex flex-col gap-5">
        <input type="file" 
	        class="button bg-[#5c7099] w-fit text-white px-2 py-1 rounded-md"
	        id="videoFile" 
	        name="videoFile" 
	        accept="video/*" 
	        onchange="changeFile()"
	      >
	     <button id="submit" class="bg-[#16b67c] text-white px-10 py-1 mt-4 rounded-md">PROCESS</button>
       </form>
       </div>
       
    </div>
	</div>
  
  </body>
  <script>
  
  window.onload = function()
  {
  	
  	 let btn = document.getElementById("submit");
  	 console.log(btn);
  	 btn.onclick = async function(event) {
  		event.preventDefault();
  	    	console.log("submit")
  	    	var formData = new FormData(document.getElementById("myForm"));
  			
  	        // Thêm các trường không nằm trong form
  	        console.log(Array.from(formData));

  	        try {
 	        	  const res = await fetch(
 	        	    '/forum/editor',
 	        	    {
 	        	      method: 'POST',
 	        	      body: formData,
 	        	    },
 	        	  );

				alert('Đăng bài viết thành công!')
				
 	        	  console.log(resData);
 	        	} catch (err) {
 	        	  console.log(err.message);
 	        	}
  	    }
  	  
  };
		
		//Example of options ={hidden:false,locked:true,panel:false}
		var options = {hidden:false},
			mplayer=videojs("vid1");
			mplayer.rangeslider(options);
         
		function playBetween(){
			var start,end;
			start = document.getElementById('Start').value;
			end = document.getElementById('End').value;
			mplayer.playBetween(start,end);
		}
        function loopBetween() {
            var start = document.getElementById('Start').value;
            var end = document.getElementById('End').value;
            mplayer.loopBetween(start, end);
        }
		function getValues(){
			var values = mplayer.getValueSlider();
            console.log(values);
			document.getElementById('Start').value=videojs.round(values.start,2);
			document.getElementById('End').value=videojs.round(values.end,2);
		}
		
		function showhide(){
			var plugin=mplayer.rangeslider.options;
			if(plugin.hidden)
				mplayer.showSlider();
			else
				mplayer.hideSlider();
		}
		function lockunlock(){
			var plugin=mplayer.rangeslider.options;
			if(plugin.locked)
				mplayer.unlockSlider();
			else
				mplayer.lockSlider();
		}
		function showhidePanel(){
			var plugin=mplayer.rangeslider.options;
			if(!plugin.panel)
				mplayer.showSliderPanel();
			else
				mplayer.hideSliderPanel();
		}
		function showhideControlTime(){
			var plugin=mplayer.rangeslider.options;
			if(!plugin.controlTime)
				mplayer.showControlTime();
			else
				mplayer.hideControlTime();
		}
    function startAppendVideo() {
      // get element
      var video = document.getElementById('vid1_html5_api');
      console.log(video);
      // create source element
      var source = document.createElement('source');
      // set source attributes
      source.setAttribute('src', 'http://vjs.zencdn.net/v/oceans.mp4');
      source.setAttribute('type', 'video/mp4');
      // append source element to video element
      video.appendChild(source);
      // load the new source
      // video.load();

    }
    function changeFile() {
      var file = document.getElementById('videoFile').files[0];
      var url = URL.createObjectURL(file);
      var video = document.getElementById('vid1_html5_api');
      console.log(video);
      var source = document.createElement('source');
      // set source attributes
      source.setAttribute('src', url);
      source.setAttribute('type', 'video/mp4');
      // append source element to video element
      video.appendChild(source);

      let blur = document.getElementById('require-select');
      blur.style.display = 'none';
    }
	</script>
</html>
