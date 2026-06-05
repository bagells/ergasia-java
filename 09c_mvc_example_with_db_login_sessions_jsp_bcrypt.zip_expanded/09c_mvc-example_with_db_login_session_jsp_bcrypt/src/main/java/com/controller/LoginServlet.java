package com.controller;

import java.io.IOException;
import com.dao.StudentDao;
import com.dao.SystemDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;
	private StudentDao studentDao;

	public LoginServlet() {
		super();
		dao = new SystemDao();
		studentDao = new StudentDao();
		System.out.println("LoginServlet initialized: " + dao);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// Check if username exists
		String usernameValidation = dao.loginusernameCheck(username);

		if (!usernameValidation.equals(username)) {
			// Username doesn't exist
			request.setAttribute("message", usernameValidation);
			request.setAttribute("username", username);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		} else {
			// Username exists, check if account is locked
			String lockStatus = dao.checkAccountLockStatus(username);
			
			if (lockStatus.equals("locked")) {
				// Account is locked
				java.sql.Timestamp lockedUntil = dao.getLockedUntilTime(username);
				String message;
				
				if (lockedUntil != null) {
					// Calculate remaining lock time
					long remainingMillis = lockedUntil.getTime() - System.currentTimeMillis();
					long remainingMinutes = remainingMillis / (60 * 1000);
					
					if (remainingMinutes > 0) {
						message = "Account is locked due to multiple failed login attempts. Please try again in " + 
						          remainingMinutes + " minute(s).";
					} else {
						message = "Account is locked due to multiple failed login attempts. Please try again shortly.";
					}
				} else {
					message = "Account is locked. Please contact administrator.";
				}
				
				request.setAttribute("message", message);
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
				return;
			}
			
			// Account not locked, proceed with password check
			String passwordValidation = dao.passwordCheck(username, password);

			if (passwordValidation.equals("You logged in!")) {
				// Login successful - reset failed attempts
				dao.resetFailedAttempts(username);
				
				String role = dao.getRole(username);
				HttpSession session = request.getSession(true);
				
				synchronized(session) {
					session.setAttribute("username", username);
					session.setAttribute("role", role);
					session.setAttribute("registrationnumber", studentDao.getStudentRegistrationNumber(username));

					if (role.equals("student")) {
						System.out.println("User logged in as STUDENT: " + username);
						System.out.println("Registration Number: " + studentDao.getStudentRegistrationNumber(username));
						RequestDispatcher view = request.getRequestDispatcher("/student.jsp");
						view.forward(request, response);
					} else if (role.equals("professor")) {
						// TODO: Implement professor page
						System.out.println("User logged in as PROFESSOR: " + username);
					} else {
						// Secretary or other role
						session.setAttribute("role", "secretary");
						System.out.println("User logged in as SECRETARY: " + username);
						// TODO: Implement secretary page
					}
				}
			} else {
				// Password incorrect - increment failed attempts
				dao.incrementFailedAttempts(username);
				
				request.setAttribute("message", passwordValidation);
				RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
				view.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		doPost(req, resp);
	}
}