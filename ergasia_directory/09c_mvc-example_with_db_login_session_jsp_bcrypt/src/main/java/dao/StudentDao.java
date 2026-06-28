package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Students;
import utilities.DbUtil;



public class StudentDao {

	private Connection connection;

	public StudentDao() {
        connection = DbUtil.getConnection();
    }

	public int getStudentRegistrationNumber(String username) {
		int registrationNumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RegistrationNumber from STUDENTS where USERS_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
            	registrationNumber=rs.getInt("registrationNumber");
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationNumber;
	}




	public Students GetStudentDetails (String username) {
		Students student=new Students();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from STUDENTS where USERS_username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				student.setRegistrationNumber(rs.getInt("RegistrationNumber"));
            	student.setUsername(rs.getString("USERS_username"));
            	student.setName(rs.getString("Name"));
            	student.setSurname(rs.getString("Surname"));
            	student.setDepartment(rs.getString("Department"));
            	student.setRole("student");
            	//String htmlRow = createHTMLRow(code, name, surName, dept);
				//out.println(htmlRow);
            	System.out.println("PRINTING THE STUDENT: "+ student.toString());
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        }
		return student;
	}



}
