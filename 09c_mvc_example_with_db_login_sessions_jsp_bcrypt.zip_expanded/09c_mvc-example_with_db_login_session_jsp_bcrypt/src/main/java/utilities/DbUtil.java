package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DbUtil {
	private static Connection connection = null;
    private static DataSource datasource = null;


    public static Connection getConnection() {
        String jdbcURL
                = "jdbc:postgresql://localhost:5432/student";
        String username = "kostas";
        String password = "faggot";
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection connection
                    = DriverManager.getConnection(
                    jdbcURL, username, password);
            System.out.println(
                    "Connected to PostgreSQL database!");
            return connection;
        }
        catch (Exception e) {
        e.printStackTrace();
         }
        if (connection != null) {
            return connection;}
        else {
            try {
            	InitialContext ctx = new InitialContext();
    			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/LiveDataSource3");
    			connection = datasource.getConnection();
            }catch(Exception e) {
                e.printStackTrace();
            }

            return connection;

        }

    }


}

