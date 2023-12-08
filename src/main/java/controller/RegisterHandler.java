package controller;
import java.util.UUID;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.bo.UserBO;
import model.bo.UserBO;

@WebServlet("/RegisterServlet")
public class RegisterHandler extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserBO userBO = new UserBO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		UUID uuid=UUID.randomUUID();   
		String id = uuid.toString();
		String role = "user";
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        userBO.createUser(id, role, username, plainPassword, firstname, lastname);
    	response.sendRedirect("login.jsp");
        
    }
}