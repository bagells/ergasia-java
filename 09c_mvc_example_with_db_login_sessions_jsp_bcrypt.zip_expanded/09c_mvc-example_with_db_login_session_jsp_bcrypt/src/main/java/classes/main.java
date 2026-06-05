package classes;

import java.util.List; //thema me to getfirst, douleuei sto linkedlist, mporw na kanw import java.util.list

public class main {


	public static void main(String[] args) {
		secretaries grammateia = new secretaries("usrgrammateia","grammateia","cs");
		List<students> lista_mathiton = grammateia.student_to_be_graded_list_creator();//εστω οτι αυτος ο κωδικας δουλευει γιατι μου βγαζει ερρορς
		students kostas;
		//**PREPEI NA TSEKARW GIA NULL LISTA ME TO GET 0 AFOU DE DOULEUEI TO GETFIRST**
		kostas = lista_mathiton.get(0);

        //kostas = lista_mathiton.getFirst(); <--OG KWSTOKWDIKAS, should work me jdk 21 kai meta alla de douleuei me to 24 mou, ma pws to kanei
		//isws prepei na allajw language level sto project structure apo default sdk se kapoio allo version

        //os mathitis pos tha dw ton bathmo mou ?
		//lista foititon pros vathmologisi
		Courses cs101 =grammateia.createcourse("cs101", 4, 4);
		professors patsakis = grammateia.createprof("uspatsak", "patsakis", "cs");
		grammateia.add_course_to_prof(patsakis, cs101);//δινω στον πατσακη το cs101 ο οποιος θα το βαθμολογησει και μετά θα δω τον βαθμο μου
		//tora prepei o patsakis na vathmologhsei to mathima pou tou antistoihei + na dei tous bathmous ton foititon tou
		//πως θα το κανω αυτο TwT
		//λοιπον ο καθηγητης θα παιρνει την λιστα των φοιτητων προς βαθμολογηση 
		//φτιαχνει ενα grades για καθε μαθητη το οποιο θα το βαζει στην λιστα του course για καθε εξεταστικη
		//επειτα ο φοιτητης θα και ο καθηγητης θα βλεπουν τα δικα τους μεσω του course αντιστοιχα οκ παμε
		
		List<grades> vathmologimeni_lista =patsakis.student_to_be_graded_list_creator(lista_mathiton, cs101);
		
		System.out.print(vathmologimeni_lista);//πλακα πλακα η υλοποιηση εδω δεν χρειαζεται αλλα εστω οτι δουλευει 
		//γιατι για την ωρα η λιστα βγαινει κενη , αλλα επσισης οχι ερρορς που είναι  καλο
		//βγαζει νοημα που είναι κενη γιατι δεν εχω @string 
		// τωρα εγω ως φοιτητης θελω να δω τον βαθμο μου 
		grammateia.add_grades_to_course(cs101, vathmologimeni_lista);
		//peak
		//this is beautiful actually
		//slop of the slop 
		//τωρα θα παει να διαβασει ο κωστας 
		//αφοτου του δωσω το μαθημα
		grammateia.add_course_to_student(kostas, cs101);
		//ας επιλέξει ποιο μαθημα θελει να δει βαση ονοματος 
		kostas.all_grades();//εστω οτι αυτο δουλεύει 
		//αντιστοιχα ο πατσακης για να δει τα δικα του μαθήμαθα τα έκανε 
		patsakis.all_grades();//εστω οτι αυτο δουλευει
		
		
		
		// TODO Auto-generated method stub

	}

}
