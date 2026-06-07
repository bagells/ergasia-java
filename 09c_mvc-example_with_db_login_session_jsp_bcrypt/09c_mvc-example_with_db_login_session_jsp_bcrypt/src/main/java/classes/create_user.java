package classes;
import java.util.Scanner;


public class create_user {
    public static void main(String[] args) {
        String username;
        String name;
        String department;
        int reggie;
        int semie;

        Scanner scanner  = new Scanner(System.in);
        Users user1 = new Users("kostas","kostadinos gianopoulos","pliroforikos") {
        };
        //username,String name,String  department,int reggie ,int semie//
        // auto edo einai me scanner gia na bori na vali o user auta pou theli stin klasi student
        System.out.println("Enter username");
        username = scanner.nextLine();
        System.out.println("Enter name ");
        name = scanner.nextLine();
        System.out.println("Enter department");
        department  = scanner.nextLine();
        System.out.println("Enter registration number");
        reggie = scanner.nextInt();
        System.out.println("Enter semester");
        semie = scanner.nextInt();
        students student1 = new students( username,name,department,reggie,semie){
        };
        professors professor = new professors("profferor jake","jake","science");
        secretaries secretary = new secretaries("secretary 1","xristos","comptuer science");

        scanner.close();
    }
}