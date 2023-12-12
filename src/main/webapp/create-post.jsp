
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
    	quill = new Quill('#editor', {
    	    theme: 'snow',
    	    placeholder: 'Nhập nội dung bài viết...'
    	  });
    	  console.log("hiii", '<%= user.getId()%>')
    	 
    	 let btn = document.getElementById("submit");
    	 console.log(btn);
    	 btn.onclick = async function() {
    	    	console.log("submit")
    	    	var delta = quill.root.innerHTML;
    	    	console.log(delta)
    	    	var select = document.getElementById("category_id");
    	        var formData = new FormData(document.getElementById("myForm"));
    			
    	        // Thêm các trường không nằm trong form
    	        formData.append("user_id", '<%= user.getId()%>');
    	        formData.append("category_id", select.value);
				formData.append("content", delta)
    	        console.log(Array.from(formData));
  
    	        try {
   	        	  const res = await fetch(
   	        	    '/forum/create-post',
   	        	    {
   	        	      method: 'POST',
   	        	      body: formData,
   	        	    },
   	        	  );

				alert('Đăng bài viết thành công!')
				window.location.href = "/forum/home"
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
				<a href="/forum/home" class="font-bold text-lg">FORUMZ</a>
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
	
	
	
		<div class="w-full bg-white min-h-[500px] max-w-[1240px] rounded-md p-3 flex flex-col">
			<h2 class="font-semibold block pb-2 text-xl border-b-[1px]">Đăng bài</h2>
			
			<form class="mt-2 flex-auto h-full flex flex-col" id="myForm" action="">
				<div class="flex gap-4 w-full items-end">
					<div class=" w-[50%]">
						<p>Nhập tiêu đề <span class="text-rose-400">*</span></p>
						<input name="title" class="border-[1px] w-full text-sm p-2 rounded-md" type="text" placeholder="Nhập tiêu đề..."/>
					</div>
					<div class=" w-[50%]">
					<p>Chọn thể loại<span class="text-rose-400">*</span></p>
						  <select id="category_id" name="selectedOption" class="border-[1px] h-[35px]">
			                <c:forEach var="item" items="${myData}">
			                    <option value="${item.id}">${item.name}</option>
			                </c:forEach>
			            </select>
					</div>
				</div>
				<div class="mt-2 h-full flex flex-col">
					<p>Nhập nội dung <span class="text-rose-400">*</span></p>
					<div id="editor"></div>
				</div>
				<div class="w-full mt-2">
					
				</div>
			</form>
			<button id="submit" class="bg-[#5c7099] text-white py-1 px-2 rounded-md">Đăng ngay</button>
		</div>
	</div>
</body>
</html>