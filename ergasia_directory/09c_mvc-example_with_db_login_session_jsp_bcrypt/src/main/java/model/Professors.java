package model;

public class Professors extends Users{

    private int registrationNumber=0;
    private String department=null;

    public void setRegistrationNumber(int i) {
        this.registrationNumber=i;
    }

    public int getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setDepartment(String department) {
        this.department=department;
    }

    public String getDepartment() {
        return this.department;
    }

}
