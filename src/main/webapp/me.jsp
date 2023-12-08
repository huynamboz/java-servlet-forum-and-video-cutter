
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@page import="model.bean.User"%>
<!DOCTYPE html>
<html>
<% User user = (User)session.getAttribute("me"); %>
<head>

    <link rel="stylesheet" type="text/css" href="./css/global.css">
    <script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
    <link href="https://cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
    let quill = null;
    window.onload = function()
    {
    	
    	 let btn = document.getElementById("submit");
    	 console.log(btn);
    	 btn.onclick = async function() {
    	    	console.log("submit")
    	    	
    	        var formData = new FormData(document.getElementById("myForm"));
    			
    	        // Thêm các trường không nằm trong form
    	        formData.append("user_id", '<%= user.getId()%>');
    	        
 
    	        formData.append("username", '<%= user.getUsername()%>');
    	        
    	        try {
   	        	  const res = await fetch(
   	        	    '/forum/me',
   	        	    {
   	        	      method: 'POST',
   	        	      body: formData,
   	        	    },
   	        	  );

				alert('Cập nhật thông tin thành công!')
				window.location.href = "/forum/"
   	        	  console.log(resData);
   	        	} catch (err) {
   	        	  console.log(err.message);
   	        	}
    	    }
    	  
    };
     
    
	  
	</script>
<title>Insert title here</title>
</head>
<body>
<div class="container-main flex justify-center w-full">

	<div>
		<div class="header">
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
				<% if (session.getAttribute("id") != null) { %>
				    <div class="flex gap-1 items-center">
				    	<a href='/forum/create-post' class="bg-[var(--blue-100)] text-white mr-5 rounded-md px-3 py-1">
							Đăng bài
						</a>
						<p><%= user.getName() %></p>
				    	<img class="avatar" src="https://data.voz.vn/avatars/s/360/360612.jpg?1631028275" alt="avatar" />
				    </div>
			   <%} else { %>
			   	<div class="nav-link__right-action">
			   		<a class="nav-link__right-action--btn" href="/forum/login.jsp">Login</a>
			   		<a class="nav-link__right-action--btn" href="/forum/register.jsp">Register</a>
			   	</div>
			   <%} %>
			</div>
		</div>
	</div>
	
	
	
		<div class="w-full bg-white h-fit max-w-[1240px] rounded-md p-3 flex flex-col">
			<h2 class="font-semibold block pb-2 text-xl border-b-[1px]">Chỉnh sủa thông tin cá nhân</h2>
			
			<form class="mt-2 flex-auto h-full flex flex-col" id="myForm" action="">
				<div class="flex gap-4 w-full items-end">
					<div class=" w-[50%]">
						<p>Tên <span class="text-rose-400">*</span></p>
						<input name="name" value="<%= user.getFirstname() + " " + user.getLastname() %>" class="border-[1px] w-full text-sm p-2 rounded-md" type="text" placeholder="Nhập tiêu đề..."/>
					</div>
					<div class=" w-[50%]">
						<p>Ảnh đại diện <span class="text-rose-400">*</span></p>
						<input value="<%= user.getAvatar() %>" name="avatar" class="border-[1px] w-full text-sm p-2 rounded-md" type="text" placeholder="Nhập link ảnh..."/>
					</div>
				</div>
				<div class="mt-2 h-full flex flex-col">
					<div class=" w-[50%]">
						<p>Role <span class="text-rose-400">*</span></p>
						<input name="role" value="user" disabled class="border-[1px] w-full text-sm p-2 rounded-md" type="text" placeholder="Nhập tiêu đề..."/>
					</div>
					<div class=" w-[50%] mt-2">
						<p>Mật khẩu mới <span class="text-rose-400">*</span></p>
						<input value="<%=user.getPassword() %>" name="password" class="border-[1px] w-full text-sm p-2 rounded-md" type="text" placeholder="Nhập mật khẩu mới..."/>
					</div>
				</div>
				<div class="w-full mt-2">
					
				</div>
			</form>
			<button id="submit" class="bg-[#5c7099] text-white py-1 px-2 rounded-md">Cập nhật</button>
		</div>
	</div>
</body>
</html>