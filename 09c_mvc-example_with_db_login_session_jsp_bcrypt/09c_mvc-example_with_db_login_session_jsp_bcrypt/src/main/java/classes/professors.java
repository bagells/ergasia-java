package classes;

import java.util.ArrayList;
import java.util.List;


public class professors extends Users{
	List<Courses> courses_kathigiti = new ArrayList<Courses>();
	//List
	/**
	 *
	 * @param username
	 * @param name
	 * @param department
	 */
	public professors(String username,String name,String  department) {
		super(username,name,department);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Courses courses1 = new Courses("math",12,2);
		Courses.updatel_List();
//na valo tin lista stous proffesors kai students
	}
	
	public static grades vathmologisi(students mathitis,int ejetastikh_periodos,String course_name) {
		grades antikeimeno_bathmou = new grades();
		antikeimeno_bathmou.student_name=mathitis.name;
		antikeimeno_bathmou.course_name=course_name;//eho ponokefalo :(
		antikeimeno_bathmou.exam_period=ejetastikh_periodos;
		antikeimeno_bathmou.grade=10;
		return antikeimeno_bathmou;
		
	}
	public <grades> List<grades> student_to_be_graded_list_creator(List<students> lista_matiton,Courses mathima){
		
		List<grades> grades_list = new ArrayList<grades>();
		for (students mathites:lista_matiton) {
			if(mathites.getDepartment()!=this.getDepartment()) {
				//throw new Exception("Error");
			}else {
				grades_list.add((grades) professors.vathmologisi(mathites, 1, mathima.course_name));
			}
			
		}
		
		return grades_list;
	}
	public void  epilogi_mathimatos_gia_vathmologisi(Courses mathima) {
		for (Courses courses_kathigiti_antikeimeno :courses_kathigiti) {
			
			
		}
		
		
	}
	public void all_grades() {
		for (Courses course: courses_kathigiti) {
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

