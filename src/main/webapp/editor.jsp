<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		    <a href="/forum" class="font-bold text-lg">FORUMZ</a>
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


	
<p class="block text-3xl font-bold mb-5 text-center text-[#f85a38]">VIDEO CUTTER✂️</p>
	<div class="w-full flex gap-5 justify-center">
    
    <div class="flex flex-col h-full w-[350px] border-[1px] rounded-lg p-5 pt-2">
    	<p class="block text-xl font-bold mb-5 text-center text-[#f85a38]">Danh sách xử lý</p>
    	 <div class="flex flex-col gap-2">
	    	 <c:forEach var="item" items="${myData}">
	    	  <c:choose>
				  <c:when test="${item.data == 'processing'}">
				    <div class="flex items-center justify-center gap-1 w-full h-[45px] bg-[#f1f2f6]">
				    <p>Đang xử lý</p>
				    <svg xmlns="http://www.w3.org/2000/svg" width="23" height="23" viewBox="0 0 24 24"><path fill="currentColor" d="M12,1A11,11,0,1,0,23,12,11,11,0,0,0,12,1Zm0,19a8,8,0,1,1,8-8A8,8,0,0,1,12,20Z" opacity=".25"/><path fill="currentColor" d="M12,4a8,8,0,0,1,7.89,6.7A1.53,1.53,0,0,0,21.38,12h0a1.5,1.5,0,0,0,1.48-1.75,11,11,0,0,0-21.72,0A1.5,1.5,0,0,0,2.62,12h0a1.53,1.53,0,0,0,1.49-1.3A8,8,0,0,1,12,4Z"><animateTransform attributeName="transform" dur="0.75s" repeatCount="indefinite" type="rotate" values="0 12 12;360 12 12"/></path></svg>
				    </div>
				  </c:when>
				  <c:when test="${item.data != 'processing'}">
				    <video controls src="${item.data}" class="bg-gray-100"></video>
				  </c:when>
				</c:choose>
	         </c:forEach>
    	 </div>
    </div>
    
    
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
       
       <form id="myForm" action.prevent="" method = "post"  class="flex flex-col gap-5">
        <input type="file" 
	        class="button bg-[#5c7099] w-fit text-white px-2 py-1 rounded-md"
	        id="videoFile" 
	        name="videoFile" 
	        accept="video/*" 
	        onchange="changeFile()"
	      >
	      <div>
	        Start (seconds):<input type="text" name="start" value="00:00" id="Start">
	        End (seconds): <input type="text" name="end" value="00:00" id="End">
	       </div>
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
  		getValues()
  	    	console.log("submit")
  	    	var formData = new FormData(document.getElementById("myForm"));
  			formData.append("user_id", "388c16bf-bf78-4988-9133-83a30b032c3d")
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

				alert('Đã đưa tiến trình xử lý vào hàng chờ thành công!')
				location.reload();
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
			document.getElementById('Start').value=videojs.round(values.start,0);
			document.getElementById('End').value=videojs.round(values.end, 0);
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
