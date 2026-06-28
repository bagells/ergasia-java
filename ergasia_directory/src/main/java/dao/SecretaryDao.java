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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id from secratary where username=?");
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

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from secratary where username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                secretaries.setRegistrationNumber(rs.getInt("id"));
                secretaries.setUsername(rs.getString("username"));
                secretaries.setName(rs.getString("name"));
                secretaries.setSurname(rs.getString("surname"));
                secretaries.setDepartment(rs.getString("department"));
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
