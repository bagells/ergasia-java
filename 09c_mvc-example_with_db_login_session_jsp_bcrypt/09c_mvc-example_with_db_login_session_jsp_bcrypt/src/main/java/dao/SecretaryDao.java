package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Secretaries;
import model.Students;
import utilities.DbUtil;

public class SecretaryDao {
    private Connection connection;

    public SecretaryDao() {
        connection = DbUtil.getConnection();}

    public int getSecretaryRegistrationNumber(String username) {
        int registrationNumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RegistrationNumber from SECRETARIES where USERS_username=?");
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

    public Secretaries GetSecretaryDetails (String username) {
        Secretaries secretaries=new Secretaries();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from SECRETARIES where USERS_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                secretaries.setRegistrationNumber(rs.getInt("RegistrationNumber"));
                secretaries.setUsername(rs.getString("USERS_username"));
                secretaries.setName(rs.getString("Name"));
                secretaries.setSurname(rs.getString("Surname"));
                secretaries.setDepartment(rs.getString("Department"));
                secretaries.setRole("secretaries");
                //String htmlRow = createHTMLRow(code, name, surName, dept);
                //out.println(htmlRow);
                System.out.println("PRINTING THE SECRETARY: "+ secretaries.toString());
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return secretaries;
    }

}
