package classes;
import java.util.ArrayList;
import java.util.List;



public class students extends Users{
    int registrationNumber;
    int semester;
    List<Courses> courses_foithth = new ArrayList<Courses>();//εδω θα βαζουμε τα courses καθε student//ισως οχι και η καλύτερη μου ιδέα θα δούμε πως θα εξελιχθεί αυτό
    //List<String> courses = new ArrayList<String>();

    /**
     *
     * @param username
     * @param name
     * @param department
     * @param reggie
     * @param semie
     */
    public students(String username,String name,String  department,int reggie ,int semie) {
        super(username,name,department);
        registrationNumber =reggie ;
        semester =semie;

        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    public void showcourses() {
        for (Courses course: courses_foithth) {
            System.out.print(course.course_name);

        }
    }
    public void all_grades() {
        for (Courses course: courses_foithth) {
            for(grades_list lista1 : course.lista_vathmon_kai_periodon) {
                for(grades vathmoi : lista1.lista_vathmon_kai_periodon2) {
                    if(vathmoi.student_name==this.name) {
                        System.out.print(vathmoi.grade );
                    }
                }
            }

        }
    }

}