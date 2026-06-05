package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sql_tester {
	 public static void main(String[] args)
	    {
	        String jdbcURL
	            = "jdbc:postgresql://localhost:8080/student";
	        String username = "postgres";
	        String password = "database";

	        try {
	            // Load the PostgreSQL JDBC driver
	            Class.forName("org.postgresql.Driver");

	            // Establish the connection
	            Connection connection
	                = DriverManager.getConnection(
	                    jdbcURL, username, password);
	            System.out.println(
	                "Connected to PostgreSQL database!");

	            // Create a statement
	            Statement statement
	                = connection.createStatement();

	            // Create a table if not exists
	            String createTableSQL
	                = "CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(50), email VARCHAR(50))";
	            statement.execute(createTableSQL);
	            System.out.println("Table 'users' created!");

	            // Insert a row into the table
	            String insertSQL
	                = "INSERT INTO users (name, email) VALUES ('John Doe', 'john.doe@example.com')";
	            statement.executeUpdate(insertSQL);
	            System.out.println(
	                "Inserted data into 'users' table!");

	            // Retrieve data from the table
	            String selectSQL = "SELECT * FROM users";
	            ResultSet resultSet
	                = statement.executeQuery(selectSQL);

	            while (resultSet.next()) {
	                System.out.println(
	                    "User ID: " + resultSet.getInt("id")
	                    + ", Name: "
	                    + resultSet.getString("name")
	                    + ", Email: "
	                    + resultSet.getString("email"));
	            }

	            // Close the connection
	            connection.close();
	            System.out.println("Connection closed.");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


}
