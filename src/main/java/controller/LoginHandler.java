package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.bean.User;
import model.bo.UserBO;
import model.bo.UserBO;
@WebServlet(name = "login", urlPatterns = "/login")
public class LoginHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	UserBO userBO = new UserBO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Optional<User> user = userBO.getUserByUsername(username);
        if(!user.isEmpty()) {
        	if(password.equals(user.get().getPassword())) {
        		HttpSession session = request.getSession();
        		session.setAttribute("id", user.get().getId());
        		session.setAttribute("me", user.get());
        		response.sendRedirect("index.jsp");
        	}
        	else {
        		String errorMessage = "Wrong password!";
                request.setAttribute("errorMessage", errorMessage);
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
        	}

        }
        else {
        	String errorMessage = "Wrong username!";
            request.setAttribute("errorMessage", errorMessage);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
}