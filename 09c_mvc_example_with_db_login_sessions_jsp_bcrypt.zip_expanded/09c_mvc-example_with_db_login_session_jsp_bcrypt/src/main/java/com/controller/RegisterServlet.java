package com.controller;

import java.io.IOException;
import com.dao.SystemDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;

	public RegisterServlet() {
		super();
		dao = new SystemDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Get form parameters
		String username = request.getParameter("newusername");
		String name = request.getParameter("newname");
		String surname = request.getParameter("newsurname");
		String department = request.getParameter("newdepartment");
		String password = request.getParameter("newpassword1");
		String role = request.getParameter("role");
		int registrationnumber = Integer.parseInt(request.getParameter("registrationnumber"));
		
		System.out.println("Registration attempt - Role: " + role + ", Username: " + username);
		
		// Check if username is available
		String usernameValidation = dao.signupusernameCheck(username);
		
		if (usernameValidation.equals("ok")) {
			// Username is available, proceed with registration
			// Note: BCrypt hashing is now handled inside the signup method
			HttpSession session = request.getSession();
			
			// Pass plain text password - it will be hashed in SystemDao.signup()
			dao.signup(registrationnumber, username, name, surname, department, role, password);
			
			synchronized(session) {
				session.setAttribute("username", username);
				session.setAttribute("name", name);
				session.setAttribute("surname", surname);
				session.setAttribute("role", role);

				if (role.equals("secretary")) {
					// TODO: Implement secretary page
					System.out.println("New secretary registered: " + username);
				} else if (role.equals("student")) {
					System.out.println("New student registered: " + username);
					request.setAttribute("role", "student");
					request.setAttribute("username", username);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/student.jsp");
					dispatcher.forward(request, response);
				} else if (role.equals("professor")) {
					// TODO: Implement professor page
					System.out.println("New professor registered: " + username);
				}
			}
		} else {
			// Username already exists
			request.setAttribute("message", usernameValidation);
			request.setAttribute("user", username);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}
	}
}