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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from student where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
            	registrationNumber=rs.getInt("id");
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationNumber;
	}




	public Students GetStudentDetails (String username) {
		Students student=new Students();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from student where username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				student.setRegistrationNumber(rs.getInt("id"));
            	student.setUsername(rs.getString("username"));
            	student.setName(rs.getString("name"));
            	student.setSurname(rs.getString("surname"));
            	student.setDepartment(rs.getString("department"));
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
