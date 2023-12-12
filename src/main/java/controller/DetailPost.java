package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Thread;
import model.bean.User;
import model.bean.Message;
import model.bo.*;
/**
 * Servlet implementation class DetailPost
 */
@WebServlet(name = "detail", urlPatterns = "/detail")
@MultipartConfig
public class DetailPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    ThreadBO threadBo = new ThreadBO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("me");
    	
    	if (user == null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
    	};
    	
        
        String queryString = request.getParameter("id");
        Thread data = threadBo.getDetail(queryString);
        
        Message[] messages = threadBo.getListMessage(queryString);
        
        // Set the data as an attribute in the request
        request.setAttribute("myData", data);
        request.setAttribute("messages", messages);

        // Forward the request to a JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UUID uuid=UUID.randomUUID();   
		HttpSession session = request.getSession();
    	User user = (User)session.getAttribute("me");
		
		String id = uuid.toString();
		
		String user_id = user.getId();
		
		String thread_id = request.getParameter("id_thread");
		
		String body = request.getParameter("body");

		System.out.println(body + " hehe : "+ thread_id + " " + user_id);
		
		threadBo.createMessage(user_id, body, thread_id, id);
//		doGet(request, response);
	}

}
