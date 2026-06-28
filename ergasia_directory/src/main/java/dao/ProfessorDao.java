package dao;

import model.Professors;
import utilities.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDao {
    private Connection connection;

    public ProfessorDao() {
        connection = DbUtil.getConnection();}

    public int getProfessorRegistrationNumber(String username) {
        int registrationNumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from proffesor where username=?");
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

    public Professors GetProfessorDetails (String username) {
        Professors professors=new Professors();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from proffesor where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                professors.setRegistrationNumber(rs.getInt("RegistrationNumber"));
                professors.setUsername(rs.getString("username"));
                professors.setName(rs.getString("Name"));
                professors.setSurname(rs.getString("Surname"));
                professors.setDepartment(rs.getString("Department"));
                professors.setRole("professors");
                //String htmlRow = createHTMLRow(code, name, surName, dept);
                //out.println(htmlRow);
                System.out.println("PRINTING THE PROFESSOR: "+ professors.toString());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }

}
