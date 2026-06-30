package controller;

import dao.SecretaryDao;
import dao.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Secretaries;
import model.Students;

import java.io.IOException;


@WebServlet("/secretary")
public class SecretaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   private SecretaryDao secretaryDao;
   private Secretaries secretary;


    public SecretaryController() {
        super();
        secretaryDao = new SecretaryDao();


	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		secretary= secretaryDao.GetSecretaryDetails(username);
		System.out.println("USERNAME= "+ username + "  ROLE= "+role + "SECRETARY NAME = "+ secretary.getName());
		session.setAttribute("name", secretary.getName());
		System.out.println("NAME +  " +secretary.getName());

		if(action.equalsIgnoreCase("viewCourse")) {
			request.setAttribute("action","viewCourse");

			//System.out.println("Name= "+secretary.getName());
			session.setAttribute("surname", secretary.getSurname());
			session.setAttribute("department", secretary.getDepartment());

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/kostas.jsp");
			requestDispatcher.forward(request,response);
		}else if (action.equalsIgnoreCase("AssignCourseToProf")){
			request.setAttribute("action","AssignCourseToProf");

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AssignCourse.jsp");
			requestDispatcher.forward(request,response);
		}else if (action.equalsIgnoreCase("viewSecretaryDetails")){
			request.setAttribute("action","viewSecretaryDetails");
			//System.out.println("Name= "+secretary.getName());
			session.setAttribute("surname", secretary.getSurname());
			session.setAttribute("department", secretary.getDepartment());

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/secretary.jsp");
			requestDispatcher.forward(request,response);
		}

	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}