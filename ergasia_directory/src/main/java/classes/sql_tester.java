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
            String drop_old_table="DROP TABLE  student CASCADE ";
            statement.execute(drop_old_table);
            String drop_old_table2="DROP TABLE  secratary ";
            statement.execute(drop_old_table2);
            String drop_old_table3="DROP TABLE  proffesor CASCADE ";
            statement.execute(drop_old_table3);
            String drop_old_table4="DROP TABLE  grades ";
            statement.execute(drop_old_table4);
            String drop_old_table5="DROP TABLE  courses  CASCADE ";
            statement.execute(drop_old_table5);
            String drop_old_table6="DROP TABLE  dilosi   CASCADE ";
            statement.execute(drop_old_table6);
            String drop_old_table7="DROP TABLE  dilosi_k   CASCADE ";
            statement.execute(drop_old_table7);
            // Create a table if not exists
           // String createTableSQL
             //       = "CREATE TABLE  users (id SERIAL PRIMARY KEY,username VARCHAR(500), name VARCHAR(500), password VARCHAR(500),role VARCHAR(500))";
            //statement.execute(createTableSQL);
            //System.out.println("Table 'users' created!");


//            System.out.println(
  //                  "Inserted data into 'users' table!");
            String vasi_vaggeli="create table if not exists student(\n" +
                    "                                      id numeric,\n" +
                    "                                      username varchar(30),\n" +
                    "                                      name varchar(30),\n" +
                    "                                      surename varchar(30),\n" +
                    "                                      courses numeric,\n" +
                    "                                      grades\"numeric\",\n" +
                    "                                      password varchar(256),\n" +
                    "                                      department varchar(30),\n" +
                    "                                      primary key (id)\n" +
                    ");\n" +
                    "create table if not exists courses(\n" +
                    "                                      course_id numeric,\n" +
                    "                                      course_name varchar(30),\n" +
                    "                                      primary key (course_id)\n" +
                    ");\n" +
                    "create table if not exists grades\n" +
                    "(\n" +
                    "    s_id         numeric,\n" +
                    "    c_id         numeric,\n" +
                    "    grade        numeric,\n" +
                    "    exetatistiki numeric,\n" +
                    "    foreign key (s_id) references student(id),\n" +
                    "    foreign key (c_id) references courses(course_id),\n" +
                    "    primary key (s_id,c_id,exetatistiki)\n" +
                    " );\n" +
                    "create table if not exists dilosi(\n" +
                    "                                     id numeric,\n" +
                    "                                     c_id numeric,\n" +
                    "                                     s_id numeric,\n" +
                    "                                     primary key  (id),\n" +
                    "                                     foreign key (c_id) references  courses(course_id),\n" +
                    "                                     foreign key (s_Id) references  student(id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "create table if not exists proffesor(\n" +
                    "                                        id numeric,\n" +
                    "                                        name varchar(30),\n" +
                    "                                        username varchar(30),\n" +
                    "                                        surename varchar(30),\n" +
                    "                                        courses numeric,\n" +
                    "                                        password varchar(256),\n" +
                    "                                        department varchar(30),\n" +
                    "                                        primary key (id),\n" +
                    "                                        foreign key (courses) REFERENCES courses(course_id)\n" +
                    "\n" +
                    ");\n" +
                    "create table if not exists dilosi_k(\n" +
                    "                                       id numeric,\n" +
                    "                                       p_id numeric,\n" +
                    "                                       c_id numeric,\n" +
                    "                                       primary key (id),\n" +
                    "                                       foreign key(p_id) references proffesor(id),\n" +
                    "                                       foreign key (c_id) references courses(course_Id)\n" +
                    ");\n" +
                    "create table if not exists secratary(\n" +
                    "                                        id numeric,\n" +
                    "                                        username varchar(30),\n" +
                    "                                        name varchar(30),\n" +
                    "                                        surename varchar(30),\n" +
                    "                                        courses numeric,\n" +
                    "                                        password varchar(256),\n" +
                    "                                        department varchar(30),\n" +
                    "                                        primary key (id),\n" +
                    "                                        foreign key (courses) REFERENCES courses(course_id)\n" +
                    ");\n";

            statement.execute(vasi_vaggeli);
      //      String insertSql2 ="INSERT INTO grades (grade_id,grade_number) VALUES ('0','10')";
        //    statement.executeUpdate(insertSql2);
            // Παω να φτιαξω ενα course
            String insertSql ="INSERT INTO courses (course_id,course_name) VALUES ('5','Μαθηματικός προγραμματισμός')";
            statement.executeUpdate(insertSql);
            String insertSql3 ="INSERT INTO courses (course_id,course_name) VALUES ('6','Βάσεις Δεδομένων')";
            statement.executeUpdate(insertSql3);


            // Insert a row into the table
            String insertSQL= "INSERT INTO student (id,name,surename,username,courses,grades,password,department) VALUES ('29035','Chris','anthemos','miku','6','0','123','cs')";
            statement.executeUpdate(insertSQL);
            String insertSQL6= "INSERT INTO student (id,name,surename,username,courses,grades,password,department) VALUES ('29067','Efmorfia','kougioumtg','teto','5','0','123','cs')";
            statement.executeUpdate(insertSQL6);
            String insertSQL2= "INSERT INTO dilosi (id,c_id,s_id) VALUES ('2','5','29035')";
            statement.executeUpdate(insertSQL2);
            String insertSQL4= "INSERT INTO proffesor (id,name,surename,courses,password) VALUES ('1','Patsakis','Konstantinos','6','legolas')";
            statement.executeUpdate(insertSQL4);
            String insertSql5= "INSERT INTO dilosi_k (id,p_id,c_id) VALUES ('2','1','5')";
            statement.executeUpdate(insertSql5);
            String insertSql2 ="INSERT INTO grades (s_id,c_id,grade,exetatistiki) VALUES ('29035','5','10','1')";
            statement.executeUpdate(insertSql2);
            String selectSQL = "SELECT * FROM student";
            ResultSet resultSet
                    = statement.executeQuery(selectSQL);
            //τυπωνει ολα τα αποτελεσματα
            while (resultSet.next()) {
                System.out.println(
                                "ΔΙλωση ID: " + resultSet.getInt("id")
                                      + ", course_id: "
                                    + resultSet.getString("name")
                                        + "name:"
                                    + resultSet.getString("surename"));

            }
            String selectSQL2 = "SELECT * FROM dilosi_k";
            ResultSet resultSet2
                    = statement.executeQuery(selectSQL2);
            //τυπωνει ολα τα αποτελεσματα
            while (resultSet2.next()) {
                System.out.println(
                        "ΔΙλωση ID: " + resultSet2.getInt("id")
                                + ", prof_id: "
                                + resultSet2.getString("p_id")
                                + "course_id:"
                                + resultSet2.getString("c_id"));

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