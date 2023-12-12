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
	
	 btn.onclick = async function(event) {
		event.preventDefault();
		
	    	console.log("submit", threadId)
	    	var formData = new FormData(document.getElementById("myForm"));
	    	
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
        	<% if (session.getAttribute("me") != null) { %>
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



<div class="relative h-fit w-full max-w-[1240px] mt-[50px] overflow-x-auto shadow-md sm:rounded-lg">
    <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">
                    ID
                </th>
                <th scope="col" class="px-6 py-3">
                    <div class="flex items-center">
                        Username
                    </div>
                </th>
                <th scope="col" class="px-6 py-3">
                    <div class="flex items-center">
                        Name
                    </div>
                </th>
                <th scope="col" class="px-6 py-3">
                    <div class="flex items-center">
                        Avatar
                    </div>
                </th>
                <th scope="col" class="px-6 py-3">
                    <div class="flex items-center">
                        Role
                    </div>
                </th>
                <th scope="col" class="px-6 py-3">
                    <div class="flex w-full justify-end">
                        Action
                    </div>
                </th>
            </tr>
        </thead>
        <tbody>
            
            	<c:forEach var="item" items="${myData}">
	                    <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
			                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
			                   ${item.getId()}
			                </th>
			                <td class="px-6 py-4">
			                    ${item.getUsername()}
			                </td>
			                <td class="px-6 py-4">
			                   ${item.getFirstname()} ${item.getLastname()}
			                </td>
			                 <td class="px-6 py-4">
			                    <img class="w-[30px] h-[30px] rounded-full object-cover" src="${item.getAvatar()}" alt="avatar">
			                </td>
			                <td class="px-6 py-4">
			                   ${item.getRole()}
			                </td>
			                <td class="px-6 py-4 text-right">
			                    <a href="#" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Delelte</a>
			                </td>
			            </tr>
	                </c:forEach>
        </tbody>
    </table>
</div>


   

    






  </div>
</body>
</html>