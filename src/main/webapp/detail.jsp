<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.bean.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://cdn.tailwindcss.com"></script>
  <title>Document</title>
</head>
<style>
  body {
    background-color:#535964;
    min-height: 100vh;
    color: #fff;
    display: flex;
    justify-content: center;
    padding-top: 56px;
    padding-bottom: 40px;
  }
</style>
<script>
window.onload = function()
{
	
	 let btn = document.getElementById("submit");
	 console.log(btn, '${myData.id}');
	 const threadId = '${myData.id}'
	 btn.onclick = async function(event) {
		event.preventDefault();
		
	    	console.log("submit", threadId)
	    	var formData = new FormData(document.getElementById("myForm"));
	    	formData.append("id_thread", threadId)
	        // Thêm các trường không nằm trong form
	        console.log(Array.from(formData));

	        try {
	        	  const res = await fetch(
	        	    '/forum/detail',
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
</script>
<% User user = (User)session.getAttribute("me"); %>
<body class="">
  <div class="fixed top-0 left-0 w-full header bg-[#5c7099] flex h-[56px] justify-between text-white mb-5 px-3">
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
			    	<a href="/forum/me"><img class="avatar w-[30px] h-[30px] rounded-full object-cover"  src="<%= user.getAvatar() %>" alt="avatar" /></a>
			    </div>
		   <%} else { %>
		   	<div class="nav-link__right-action">
		   		<a class="nav-link__right-action--btn" href="/forum/login.jsp">Login</a>
		   		<a class="nav-link__right-action--btn" href="/forum/register.jsp">Register</a>
		   	</div>
		   <%} %>
    </div>
  </div>

  <div class="flex flex-col mt-10 w-full max-w-[1240px]">
    <div class="flex flex-col gap-2">
      <span class="bg-[#4448ff] w-fit px-2 rounded-md">Kien thuc</span>
      <h1 class="text-2xl">${myData.getTitle()}</h1>
      <span>${myData.getUser('name')} - ${myData.getCreatedAt()}</span>
    </div>
    <div class="flex rounded-md mt-5 ">
      <div class="flex flex-col  rounded-tl-md rounded-bl-md items-center p-2 min-w-[200px] bg-[#e2e3e5] border-r-[1px] border-r-[#97999e]">
        <img class="w-[120px] h-[120px] rounded-full object-cover" src="${myData.getUser('avatar')}" alt="">
        <p class="text-[#23497c] font-medium text-xl">${myData.getUser('name')}</p>
      </div>
      <div class="p-2 bg-[#ebeced] w-full text-[#343a40] rounded-br-md rounded-tr-md">
        <p class="block border-b-[1px] border-[#b7b8bb] mb-2">${myData.getCreatedAt()}</p>
        
        <div class="text-lg">
        	${myData.getContent()}
        </div>
      </div>
    </div>


    <div class="text-lg border-b-[1px]">Comments</div>
    <div class="mt-5 flex flex-col">
      <form class="w-full flex flex-col justify-end" action="" id="myForm">
        <div class="flex gap-5 ">
          <img class="w-[50px] h-[50px] rounded-full" src="<%= user.getAvatar() %>" alt="">
          <textarea class="text-[#535964] w-full font-medium p-2 text-lg" placeholder="Nhập bình luận" name="body" id=""  ></textarea>
        </div>
        <button id="submit" class="ml-auto mt-2 bg-[#4448ff] text-white px-6 py-1 rounded-md">Gửi</button>
      </form>
    </div>

    
     <c:forEach var="item" items="${messages}">
         <div class="flex rounded-md mt-5 w-full">
	      <div class="min-w-[200px] flex flex-col items-center p-2 rounded-tl-md rounded-bl border-r-[1px] border-r-[#97999e] bg-[#e2e3e5]">
	        <img class="w-[120px] h-[120px] rounded-full object-cover" src="${item.getData('avatar')}" alt="">
	        <p class="text-[#23497c] font-medium text-xl">${item.getData('nameUser')}</p>
	      </div>
	      <div class="bg-[#ebeced] p-2 text-[#343a40] flex flex-col w-full rounded-br-md rounded-tr-md">
	        <p class="block border-b-[1px] border-[#b7b8bb] mb-2">${item.getData('createdAt')}</p>
	        <div class="flex-auto">
	          <!-- content -->
	          ${item.getData('body')}
	        </div>
	        <div class="flex justify-between">
	          <p class="font-medium">Report</p>
	          <p>Report</p>
	        </div>
	      </div>
	    </div>
     </c:forEach>







  </div>
</body>
</html>