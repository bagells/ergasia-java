package model;

public class Users {
	//Setting the attributes of class Users
    private String username;
	private String name;
	private String surname;
	private String role;
	//Declaring UserCounter as static in order to be a class attribute and not an instance attribute
	public static int UserCounter=0;

	//Constructor of class Users
	public Users(){
		//Count the times that a user is created by class Users or a subclass (Client,Seller,Admin)
		UserCounter+=1;
	}
	public void setUsername(String username) {
		this.username=username;

	}

	public void setName(String name) {
		this.name=name;
	}

	public void setSurname(String surname) {
		this.surname=surname;
	}

	public void setRole(String role) {
		this.role=role;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getRole() {
		return role;
	}


}
