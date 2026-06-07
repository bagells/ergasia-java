package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Student_file_reader_and_creator {
	String susrname;
	String sname;
	String sdepartment;
	int reg;
	int semester;
	int endcounter =5;
	
	public  Student_file_reader_and_creator() {

		// TODO Auto-generated constructor stub
		
	}
	public students create(String filename) {
	    File myObj =  new File(filename);
	    endcounter=5;
	    // try-with-resources: Scanner will be closed automatically
	    try (Scanner myReader = new Scanner(myObj)) {
	      while (myReader.hasNextLine()&& endcounter!=0) {
	        String data = myReader.nextLine();
	        String[] arr = data.split("[.]");
	        System.out.println(arr.toString());
	        for (String str : arr) {
	        	System.out.println("i entered");
	           System.out.println(str);
	 	       switch(endcounter) {
		        case 5:
					try {
						if(str instanceof String) {
							susrname = str;
						}else{
							throw new StudentFieldException();
						}
					}catch(StudentFieldException e){
						System.out.println("surname should only consist of alphabetical characters," + e);
					}
		        	break;
		        case 4:
					try {
						if(str instanceof String) {
							sname = str;
						}else{
							throw new StudentFieldException();
						}
					}catch(StudentFieldException e){
						System.out.println("name should only consist of alphabetical characters," + e);
					}
		        	break;
		        case 3:
					try {
						if(str instanceof String) {
							sdepartment = str;
						}else{
							throw new StudentFieldException();
						}
					}catch(StudentFieldException e){
						System.out.println("department should only consist of characters," + e);
					}
		        	break;
		        case 2:
					try {
						reg = Integer.parseInt(str);
					}catch(Exception e){
						System.out.println("the registration number should only consist of numbers, please enter a valid input");
					}
		        	break;
		        case 1:
					try {
						semester = Integer.parseInt(str);
					}catch(Exception e){
						System.out.println("the semester should only consist of numbers, please enter a valid input");
					}
				   break;
		       }
	 	      endcounter--;
	        }
/*
	       
	        susrname=arr[0];
	        sname=arr[1];
	        sdepartment=arr[2];
	        System.out.println(arr[3]);
	        reg=Integer.parseInt(arr[3].toString());
	        semester=Integer.parseInt(arr[4]);*/
	        
	        
	        
	        }
	        
	     // System.out.println(susrname);
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		
		
		students vaggelis = new students(susrname,sname,sdepartment,reg,semester);
		System.out.println("dimiourgisa enan onoma:");
		System.out.println(susrname);
		
		
		
		return vaggelis;
		
	}

}
