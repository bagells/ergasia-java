package classes;
import java.sql.*;
public class sql_tester {
    public static void main(String[] args)
    {
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

            // Create a statement
            Statement statement
                    = connection.createStatement();
            //Προσωρινό για να "γινεται" ""updated  "" το  db
            String drop_old_table="DROP TABLE users ";
            statement.execute(drop_old_table);
            // Create a table if not exists
           // String createTableSQL
             //       = "CREATE TABLE  users (id SERIAL PRIMARY KEY,username VARCHAR(500), name VARCHAR(500), password VARCHAR(500),role VARCHAR(500))";
            //statement.execute(createTableSQL);
            //System.out.println("Table 'users' created!");

            // Insert a row into the table
            //String insertSQL
              //      = "INSERT INTO users (name,username,password,role) VALUES ('John Doe','Johnis', 'password','student')";
            //statement.executeUpdate(insertSQL);
//            System.out.println(
  //                  "Inserted data into 'users' table!");
            String vasi_vaggeli="BEGIN;\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS public.student\n" +
                    "(\n" +
                    "    username \"char\",\n" +
                    "    id numeric,\n" +
                    "    password \"char\",\n" +
                    "    student_grade numeric,\n" +
                    "    course \"char\",\n" +
                    "    name \"char\",\n" +
                    "    surename \"char\",\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS public.professor\n" +
                    "(\n" +
                    "    username \"char\",\n" +
                    "    id numeric,\n" +
                    "    password \"char\",\n" +
                    "    grade numeric,\n" +
                    "    course \"char\",\n" +
                    "    name \"char\",\n" +
                    "    surename \"char\",\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS public.secretary\n" +
                    "(\n" +
                    "    username \"char\",\n" +
                    "    id numeric,\n" +
                    "    password \"char\",\n" +
                    "    courses \"char\",\n" +
                    "    name \"char\",\n" +
                    "    surename \"char\",\n" +
                    "    PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS public.courses\n" +
                    "(\n" +
                    "    course_name \"char\",\n" +
                    "    course_semester numeric,\n" +
                    "    course_grade numeric\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE IF NOT EXISTS public.grades\n" +
                    "(\n" +
                    "    grade numeric\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.student\n" +
                    "    ADD FOREIGN KEY (student_grade)\n" +
                    "    REFERENCES public.courses (course_grade) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.student\n" +
                    "    ADD FOREIGN KEY (course)\n" +
                    "    REFERENCES public.courses (course_name) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.professor\n" +
                    "    ADD FOREIGN KEY (course)\n" +
                    "    REFERENCES public.courses (course_name) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.secretary\n" +
                    "    ADD FOREIGN KEY (courses)\n" +
                    "    REFERENCES public.courses (course_name) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.courses\n" +
                    "    ADD FOREIGN KEY (course_grade)\n" +
                    "    REFERENCES public.grades (grade) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "\n" +
                    "ALTER TABLE IF EXISTS public.grades\n" +
                    "    ADD FOREIGN KEY (grade)\n" +
                    "    REFERENCES public.professor (grade) MATCH SIMPLE\n" +
                    "    ON UPDATE NO ACTION\n" +
                    "    ON DELETE NO ACTION\n" +
                    "    NOT VALID;\n" +
                    "\n" +
                    "END;";
            // Retrieve data from the table
            statement.execute(vasi_vaggeli);

           // String selectSQL = "SELECT * FROM users";
            //ResultSet resultSet
              //      = statement.executeQuery(selectSQL);
            //τυπωνει ολα τα αποτελεσματα
            //while (resultSet.next()) {
              //  System.out.println(
                //        "User ID: " + resultSet.getInt("id")
                  //              + ", Name: "
                    //            + resultSet.getString("name")
//
  //                              + ", Password: "
    //                            + resultSet.getString("password"));

       //     }

            // Close the connection
            connection.close();
            System.out.println("Connection closed.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}