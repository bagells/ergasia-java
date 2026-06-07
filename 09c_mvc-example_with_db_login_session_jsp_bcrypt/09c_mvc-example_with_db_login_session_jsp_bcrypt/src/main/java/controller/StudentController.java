package controller;

import java.io.IOException;

import dao.StudentDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Students;


@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

   private StudentDao studentDao;
   private Students student;


    public StudentController() {
        super();
        studentDao = new StudentDao();

    }



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		student= studentDao.GetStudentDetails(username);
		System.out.println("USERNAME= "+ username + "  ROLE= "+role + "STUDENT NAME = "+ student.getName());
		session.setAttribute("name", student.getName());
		System.out.println("NAME +  " +student.getName());



		if(action.equalsIgnoreCase("viewStudentDetails")) {
			request.setAttribute("action","viewStudentDetails");

			System.out.println("Name= "+student.getName());
			session.setAttribute("surname", student.getSurname());
			session.setAttribute("department", student.getDepartment());

			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
		}else if (action.equalsIgnoreCase("ViewStudentGrades")){
//			//TODO
		}else if (action.equalsIgnoreCase("updateDetails")){
			//TODO
		}
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}



}