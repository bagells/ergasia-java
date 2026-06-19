package classes;
import java.sql.*;
public class sql_tester {
    public static void main(String[] args)
    {
        String jdbcURL
                = "jdbc:postgresql://localhost:5432/vagelis";
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
        //    String drop_old_table="DROP TABLE  student ";
        //    statement.execute(drop_old_table);
        //    String drop_old_table2="DROP TABLE  secretary ";
        //    statement.execute(drop_old_table2);
      //      String drop_old_table3="DROP TABLE  proffesor ";
        //    statement.execute(drop_old_table3);
            //       String drop_old_table4="DROP TABLE  grades ";
          //  statement.execute(drop_old_table4);
         //   String drop_old_table5="DROP TABLE  courses  CASCADE ";
           // statement.execute(drop_old_table5);
            // Create a table if not exists
           // String createTableSQL
             //       = "CREATE TABLE  users (id SERIAL PRIMARY KEY,username VARCHAR(500), name VARCHAR(500), password VARCHAR(500),role VARCHAR(500))";
            //statement.execute(createTableSQL);
            //System.out.println("Table 'users' created!");


//            System.out.println(
  //                  "Inserted data into 'users' table!");
            String vasi_vaggeli="create table if not exists grades(\n" +
                    "                                     grade_id numeric,\n" +
                    "                                     grade_number numeric,\n" +
                    "                                     primary key (grade_id)\n" +
                    ");\n" +
                    "create table if not exists courses(\n" +
                    "                                      course_id numeric,\n" +
                    "                                      course_name varchar(30),\n" +
                    "                                      primary key (course_id)\n" +
                    ");\n" +
                    "create table if not exists student(\n" +
                    "                                      id numeric,\n" +
                    "                                      name varchar(30),\n" +
                    "                                      surename varchar(30),\n" +
                    "                                      courses numeric,\n" +
                    "                                      grades\"numeric\",\n" +
                    "                                      password varchar(30),\n" +
                    "                                      primary key (id),\n" +
                    "                                      foreign key (courses) REFERENCES courses(course_id),\n" +
                    "                                      foreign key (grades) references grades(grade_id)\n" +
                    ");\n" +
                    "create table if not exists proffesor(\n" +
                    "                                        id numeric,\n" +
                    "                                        name varchar(30),\n" +
                    "                                        surename varchar(30),\n" +
                    "                                        courses numeric,\n" +
                    "                                        password varchar(30),\n" +
                    "                                        primary key (id),\n" +
                    "                                        foreign key (courses) REFERENCES courses(course_id)\n" +
                    "\n" +
                    ");\n" +
                    "create table if not exists secratary(\n" +
                    "                                        id numeric,\n" +
                    "                                        name varchar(30),\n" +
                    "                                        surename varchar(30),\n" +
                    "                                        courses numeric,\n" +
                    "                                        password varchar(30),\n" +
                    "                                        primary key (id),\n" +
                    "                                        foreign key (courses) REFERENCES courses(course_id)\n" +
                    ");";

            statement.execute(vasi_vaggeli);
    //        String insertSql2 ="INSERT INTO grades (grade_id) VALUES ('0')";
     //       statement.executeUpdate(insertSql2);
            // Παω να φτιαξω ενα course
            String insertSql ="INSERT INTO courses (course_id,course_name) VALUES ('5','Προγραμματισμός στο διαδίκτυο')";
            statement.executeUpdate(insertSql);
            // Insert a row into the table
            String insertSQL= "INSERT INTO student (id,name,surename,courses,grades) VALUES ('24098','kostas','mattas','5','0')";
            statement.executeUpdate(insertSQL);
            String selectSQL = "SELECT * FROM student";
            ResultSet resultSet
                    = statement.executeQuery(selectSQL);
            //τυπωνει ολα τα αποτελεσματα
            while (resultSet.next()) {
                System.out.println(
                                "User ID: " + resultSet.getInt("id")
                                      + ", Name: "
                                    + resultSet.getString("name")
                                        + ",Surename:"
                                    + resultSet.getString("surename"));

            }
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