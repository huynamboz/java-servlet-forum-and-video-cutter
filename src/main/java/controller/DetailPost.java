package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Thread;
import model.bean.User;
import model.bo.*;
/**
 * Servlet implementation class DetailPost
 */
@WebServlet(name = "detail", urlPatterns = "/detail")
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
        System.out.println(queryString);
        Thread data = threadBo.getDetail(queryString);
        // Set the data as an attribute in the request
        request.setAttribute("myData", data);

        // Forward the request to a JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/detail.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
