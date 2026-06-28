package controller;

import dao.ProfessorDao;
import dao.SecretaryDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Professors;
import model.Secretaries;

import java.io.IOException;


@WebServlet("/professor")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   private ProfessorDao professorDao;
   private Professors professors;


    public ProfessorController() {
        super();
		professorDao = new  ProfessorDao();

    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		professors= professorDao.GetProfessorDetails(username);
		System.out.println("USERNAME= "+ username + "  ROLE= "+role + "PROFESSOR NAME = "+ professors.getName());
		session.setAttribute("name", professors.getName());
		System.out.println("NAME +  " +professors.getName());



		if(action.equalsIgnoreCase("viewGradesPerCourse")) {
			request.setAttribute("action","viewGradesPerCourse");

			System.out.println("Name= "+professors.getName());
			session.setAttribute("surname", professors.getSurname());
			session.setAttribute("department", professors.getDepartment());

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ProfGradesPerCourse.jsp");
			requestDispatcher.forward(request,response);
		}else if (action.equalsIgnoreCase("NewGrades")){
			request.setAttribute("action","NewGrades");

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/NewGradesPerCourse.jsp");
			requestDispatcher.forward(request,response);

		}
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}



}