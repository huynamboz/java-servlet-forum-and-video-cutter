package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bo.CategoryBO;
import model.bean.Category;
import model.bo.ThreadBO;
/**
 * Servlet implementation class CreateHandler
 */
@WebServlet(name = "create-post", urlPatterns = "/create-post")
@MultipartConfig
public class CreateHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryBO categoryBO = new CategoryBO();
	ThreadBO threadBO = new ThreadBO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateHandler() {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create-post.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		UUID uuid=UUID.randomUUID();   
		
		String id = uuid.toString();
		
		String title = request.getParameter("title");
		
		String categoryId = request.getParameter("category_id");
		
		String content = request.getParameter("content");
		
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

		
		System.out.println("hello 1" +title + categoryId + content + userId);
		
		threadBO.createThread(id, title, userId, content, categoryId);
		
//		doGet(request, response);
	}

}
