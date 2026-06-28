package controller;

import java.io.IOException;
import dao.ProfessorDao;
import dao.SecretaryDao;
import dao.StudentDao;
import dao.SystemDao;
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
	private SecretaryDao secretaryDao;
	private ProfessorDao professorDao;


	public LoginServlet() {
		super();
		dao = new SystemDao();
		studentDao = new StudentDao();
		secretaryDao= new SecretaryDao();
		professorDao = new ProfessorDao();
		System.out.println("LoginServlet initialized: " + dao);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role_sin");
		System.out.println("Ο ΡΟΛΟΣ ΜΟΥ ΕΙΝΑΙ");
		System.out.println(role);
		// Check if username exists
		String usernameValidation = dao.loginusernameCheck(username,role);

		if (!usernameValidation.equals(username)) {
			// Username doesn't exist
			System.out.println("username_doesnt_exist");
			request.setAttribute("message", usernameValidation);
			request.setAttribute("username", username);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		} else {
			// Username exists, check if account is locked
			System.out.println("username_exists");
		//	String lockStatus = dao.checkAccountLockStatus(username);
			
		/*	if (lockStatus.equals("locked")) {
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
			*/
			// Account not locked, proceed with password check
			String passwordValidation = dao.passwordCheck(username, password,role);
			System.out.println("password_check");
			if (passwordValidation.equals("You logged in!")) {
				System.out.println("Περασε");
				// Login successful - reset failed attempts
			//	dao.resetFailedAttempts(username);
				
			//	String role = "student";//εδω κανονικα θα πρεπει να παιρνει το καθε αντιστοιχο role
				HttpSession session = request.getSession(true);
				
				synchronized(session) {
					session.setAttribute("username", username);
					session.setAttribute("role", role);
					//session.setAttribute("id", studentDao.getStudentRegistrationNumber(username));

					if (role.equals("student")) {
						session.setAttribute("id", studentDao.getStudentRegistrationNumber(username));
						System.out.println("User logged in as STUDENT: " + username);
						System.out.println("Registration Numbider: " + studentDao.getStudentRegistrationNumber(username));
						RequestDispatcher view = request.getRequestDispatcher("/student.jsp");
						view.forward(request, response);
					} else if (role.equals("professor")) {
						// TODO: Implement professor page
						session.setAttribute("id", professorDao.getProfessorRegistrationNumber(username));
						System.out.println("User logged in as PROFESSOR: " + username);
						System.out.println("Registration Number: " + professorDao.getProfessorRegistrationNumber(username));
						RequestDispatcher view = request.getRequestDispatcher("/professor.jsp");
						view.forward(request, response);
					} else {
						// Secretary or other role
						session.setAttribute("id", secretaryDao.getSecretaryRegistrationNumber(username));
						session.setAttribute("role", "secretary");
						System.out.println("User logged in as SECRETARY: " + username);
						System.out.println("Registration Number: " + secretaryDao.getSecretaryRegistrationNumber(username));
						RequestDispatcher view = request.getRequestDispatcher("/secretary.jsp");
						view.forward(request, response);
						// TODO: Implement secretary page
					}
				}
			} else {
				// Password incorrect - increment failed attempts
			//	dao.incrementFailedAttempts(username);
				
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