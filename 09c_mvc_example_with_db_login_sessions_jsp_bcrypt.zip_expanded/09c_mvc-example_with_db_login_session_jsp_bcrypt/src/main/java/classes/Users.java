package classes;
public abstract class Users {
    String username;
    String name;
    String  department;
    int counter ;//na ajanetai kata ena kathe fora pou kaleitai o constructors //isos ayti i metavlitli prepei na einai global

    /**
     *
     * @param username2
     * @param name2
     * @param department2
     */
    public Users(String username2,String name2,String  department2){
        setUsername(username2);
        setName(name2);
        setDepartment(department2);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    int getcounter(){
        return counter;

    }
    void setcounter(int a) {
        counter =a ;

    }
    String getcusername(){
        return username;

    }String getname(){
        return name;

    }String getdepartment(){
        return department;

    }



}
