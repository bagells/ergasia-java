package classes;
import java.sql.*;
public class sql_tester {
    public static void main(String[] args)
    {
        String jdbcURL
                = "jdbc:postgresql://localhost:5432 /student";
        String username = "postgres";
        String password = "password";

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
            //Προσωρινό για να "γινεται" ""updated  "" το  db
            String drop_old_table="DROP TABLE users ";
            statement.execute(drop_old_table);
            // Create a table if not exists
            String createTableSQL
                    = "CREATE TABLE  users (id SERIAL PRIMARY KEY,username VARCHAR(500), name VARCHAR(500), password VARCHAR(500),role VARCHAR(500))";
            statement.execute(createTableSQL);
            System.out.println("Table 'users' created!");

            // Insert a row into the table
            String insertSQL
                    = "INSERT INTO users (name,username,password,role) VALUES ('John Doe','Johnis', 'password','student')";
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

                                + ", Password: "
                                + resultSet.getString("password"));

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