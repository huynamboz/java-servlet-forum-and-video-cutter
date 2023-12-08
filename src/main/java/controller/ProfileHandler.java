package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bo.CategoryBO;
import model.bean.Category;
import model.bean.User;
import model.bo.ThreadBO;
import model.bo.UserBO;
/**
 * Servlet implementation class CreateHandler
 */
@WebServlet(name = "me", urlPatterns = "/me")
@MultipartConfig
public class ProfileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryBO categoryBO = new CategoryBO();
	ThreadBO threadBO = new ThreadBO();
	UserBO userBO = new UserBO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Fetch data (replace this with your data retrieval logic)
        Category[] data = categoryBO.getListCategories(); // Replace this with actual data retrieval logic

        // Set the data as an attribute in the request
        request.setAttribute("myData", data);
        System.out.print("hihi legnth");
        for(int i =0; i < data.length; i++) {
        	System.out.println(i);
        	if (data[i] != null) {
        		System.out.println("k null"+ i);
        	}
        }
        // Forward the request to a JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/me.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		
		String name = request.getParameter("name");
		
		String avatar = request.getParameter("avatar");
		
		String password = request.getParameter("password");
		
		String userId = request.getParameter("user_id");
//		
//		BufferedReader reader = request.getReader();
//		String line;
//		while ((line = reader.readLine()) != null) {
//		    System.out.println("test:" + line);
//		}
//
//		Part titlePart = request.getPart("title");
//		String title = new BufferedReader(new InputStreamReader(titlePart.getInputStream()))
//		        .lines().collect(Collectors.joining("\n"));
//
//		Part categoryIdPart = request.getPart("category_id");
//		String categoryId = new BufferedReader(new InputStreamReader(categoryIdPart.getInputStream()))
//		        .lines().collect(Collectors.joining("\n"));

		// Similarly, get other parts as needed

		
		System.out.println("hello 1" +
				name + avatar + userId);
		
		userBO.UpdateInfo(username, name, avatar, password);
		Optional<User> user = userBO.getUserByUsername(username);
		HttpSession session = request.getSession();
		session.setAttribute("me", user.get());
//		doGet(request, response);
	}

}
