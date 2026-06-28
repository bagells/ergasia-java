package classes;
import java.util.ArrayList;
import  java.util.Scanner;


public class Courses {
    String course_name;
    int ect;
    int semester;
    //  ArrayList<grades> lista_vathmon_kai_periodon= new ArrayList<grades>();
    ArrayList<grades_list> lista_vathmon_kai_periodon= new ArrayList<grades_list>();



    public Courses(String course_name, int ect, int semester){


    }
    public static void main(String[] args) {
        Courses courses = new Courses("math", 2, 3);
    }

    static void updatel_List() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> course= new ArrayList<String>();
        course.add(scanner.nextLine());

    }
}