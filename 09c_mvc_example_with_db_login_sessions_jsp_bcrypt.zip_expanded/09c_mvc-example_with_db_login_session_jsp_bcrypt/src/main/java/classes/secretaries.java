package classes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class secretaries extends Users{
	/**
	 *
	 * @param username
	 * @param name
	 * @param department
	 */
	public secretaries(String username,String name,String  department) {
		super(username,name,department);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	public professors createprof(String username,String name,String  department) {
		professors pikrakis = new professors( username, name, department);
		return pikrakis;
		
		
		
		
		
	}
	public students createstudent(String username,String name,String  department,int reggie,int semester) {
		students vaggelis = new students( username, name, department,reggie,semester);
		return vaggelis;





	}
	public Courses createcourse(String course_name,int ects , int semester) {
		Courses cs101 = new Courses(course_name,ects,semester);
		
		return cs101;
	}
	public void add_course_to_prof(professors kathigitis,Courses mathima) {
		
		kathigitis.courses_kathigiti.add(mathima);
		
		
	}
	public void add_course_to_student(students mathitis,Courses mathima) {
		
		mathitis.courses_foithth.add(mathima);
		
		
	}
	//πρεπει να δημιουρησω λιστες φοιτητων προς βαθμολογηση 
	public <students> List<students> student_to_be_graded_list_creator(){
		
		List<students> student_list = new ArrayList<students>();
		//εδω θα ηθελα να φτιαξω τρια αντικειμενα student ως secretary , με την συνάρτηση μου , αλλα δεν θέλω να κάνω την κλάση secretaries
		//static  οποτε απλα θα φτιαξω τεμπελικα εδω 2-3 students αφού ουτώς ή αλλως με´τα θα τους διαβάζουμε απο αρχέιο 
	//	students kostas = new students("kostas","kostas2","cs",24098,4);//δεν ξερω γιατι αυτο βγαζει ερρορ καλα γαμα το βαρεθηκα
		Student_file_reader_and_creator dimiourgos = new Student_file_reader_and_creator();
		Path path = Paths.get("Untitled1.txt");
		if(Files.isReadable(path)) {
			System.out.println("vlepo_toarheio1");
			students kostas =(students) dimiourgos.create("Untitled1.txt");
			student_list.add(kostas);
		}else {
			System.out.println("DEN_vlepo_toarheio1");
		}
		//μυστηριωδες καστ ίσως δημιουργήσει προβληματα αργότερα
		//στην τελικη υλοποιηση θα χρησιμοποιουμε ενα αρχειο για πολλους students ο υποσχομαι
		Path path2 = Paths.get("student2.txt");
		if(Files.isReadable(path2)) {
			System.out.println("vlepo_toarheio2");
			students chris =(students) dimiourgos.create("student2.txt"); 
			student_list.add(chris);
		}else {
			System.out.println("DEN_vlepo_toarheio2");
		
		
		
		}
		Path path3 = Paths.get("student3.txt");
		if(Files.isReadable(path3)) {
			System.out.println("vlepo_toarheio3");
			students EFI =(students) dimiourgos.create("student3.txt"); 
			student_list.add(EFI);
		}else {
			System.out.println("DEN_vlepo_toarheio3");
		
		
		
		}
		//students EFI =(students) dimiourgos.create("student3.txt");
		
		
		
		
		return student_list	;
	}
		//εστω οτι δημιουργησα την σχτική λίστα των φοιτητών προς βαθμολόγηση
		
		
	
	
	//συναρτηση που βαζει την βαθμολογημένη λίστα σε course 
	public void add_grades_to_course(Courses mathima, List<grades> lista ) {
		mathima.lista_vathmon_kai_periodon.add((grades_list)lista);
		//αυτο βγαζει ερρορ αλλα στο οτι δουλευει γαμω το καστινγκ γαμω το καστινγκ γαμω το καστινγκ
		
	}
	
		


//+ena function ιγια grades απο τον βαγγελη
//+ courses στους καθηγητες απο τον βαγγελη 
	
}
