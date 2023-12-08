<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.bean.User"%>
<html>
<head>
    <meta charset="UTF-8">
    
    <title>Home Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./css/global.css">
	<link rel="stylesheet" type="text/css" href="./css/home.css">
	<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>

<% User user = (User)session.getAttribute("me"); %>
<div class="container-main">
 	
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
			    	<a href="/forum/me"><img class="avatar" src="<%= user.getAvatar() %>" alt="avatar" /></a>
			    </div>
		   <%} else { %>
		   	<div class="nav-link__right-action">
		   		<a class="nav-link__right-action--btn" href="/forum/login.jsp">Login</a>
		   		<a class="nav-link__right-action--btn" href="/forum/register.jsp">Register</a>
		   	</div>
		   <%} %>
		</div>
	</div>

	<div class="main">
		<div class="main-left">
			<section class="category" id="category-tech">
				<h2>Tin công nghệ</h2>
				
				<div class="category-post__list flex flex-col gap-2">
					
					<div class="flex gap-2 p-2 border-b-[1px]">
						<img class="avatar-second"	 src="https://data.voz.vn/avatars/s/1809/1809531.jpg?1653477151" alt="avatar"/>
						<div class="flex flex-col gap-1">
							<p><span class="post-type-discuss">
								Thảo luận
							</span>
							Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử</p>
							<p>
								<span class="text-[var(--gray-second)]">46 minutes</span>
								<span>Trịnh Huy Nam</span>
							</p>
						</div>
						
						<div class="ml-auto flex flex-col items-center">
							<p class="text-[var(--gray-second)]">Comments</p>
							<p>20</p>
						</div>
					</div>
					
					
					<div class="flex gap-2 p-2 border-b-[1px]">
						<img class="avatar-second"	 src="https://data.voz.vn/avatars/s/1809/1809531.jpg?1653477151" alt="avatar"/>
						<div class="flex flex-col gap-1">
							<p><span class="post-type-discuss">
								Thảo luận
							</span>
							Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử</p>
							<p>
								<span class="text-[var(--gray-second)]">46 minutes</span>
								<span>Trịnh Huy Nam</span>
							</p>
						</div>
						
						<div class="ml-auto flex flex-col items-center">
							<p class="text-[var(--gray-second)]">Comments</p>
							<p>20</p>
						</div>
					</div>
					
					
					<div class="flex gap-2 p-2 border-b-[1px]">
						<img class="avatar-second"	 src="https://data.voz.vn/avatars/s/1809/1809531.jpg?1653477151" alt="avatar"/>
						<div class="flex flex-col gap-1">
							<p><span class="post-type-discuss">
								Thảo luận
							</span>
							Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử</p>
							<p>
								<span class="text-[var(--gray-second)]">46 minutes</span>
								<span>Trịnh Huy Nam</span>
							</p>
						</div>
						
						<div class="ml-auto flex flex-col items-center">
							<p class="text-[var(--gray-second)]">Comments</p>
							<p>20</p>
						</div>
					</div>
				
				</div>
				
			</section>
		</div>
		<div class="main-right">
			<section class=" flex flex-col" id="post-latest">
				<h3>Latest post</h3>
				
				<div class="post-latest__list h-full overflow-y-auto">
				
					<div class="post-latest__item">
						<img src="https://data.voz.vn/avatars/s/360/360612.jpg?1631028275" alt="avatar" />
						<div class="post-latest__item__content">
							<span class="post-type-review">
								Đánh giá
							</span>
							<span>
								<a href="#" >
								Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử
								</a>
							</span>
							<p> Đăng lúc: 20/12/2023
							</p>
						</div>
					</div>
					
					
					<div class="post-latest__item">
						<img src="https://data.voz.vn/avatars/s/360/360612.jpg?1631028275" alt="avatar" />
						<div class="post-latest__item__content">
							<span class="post-type-discuss">
								Thảo luận
							</span>
							<span>
								<a href="#" >
								Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử
								</a>
							</span>
							<p> Đăng lúc: 20/12/2023
							</p>
						</div>
					</div>
					
					
					<div class="post-latest__item">
						<img src="https://data.voz.vn/avatars/s/360/360612.jpg?1631028275" alt="avatar" />
						<div class="post-latest__item__content">
							<span class="post-type-question">
								Thắc mắc
							</span>
							<span>
								<a href="#" >
								Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử
								</a>
							</span>
							<p> Đăng lúc: 20/12/2023
							</p>
						</div>
					</div>
					
					<div class="post-latest__item">
						<img src="https://data.voz.vn/avatars/s/360/360612.jpg?1631028275" alt="avatar" />
						<div class="post-latest__item__content">
							<span class="post-type-sharing">
								Kiến thức
							</span>
							<span>
								<a href="#" >
								Review mua ốp lưng/ kính cường lực cho điện thoại (iphone) trên thương mại điện tử
								</a>
							</span>
							<p> Đăng lúc: 20/12/2023
							</p>
						</div>
					</div>
					
					
				</div>
			</section>
		</div>
	</div>
	
</div>

</body>
</html>