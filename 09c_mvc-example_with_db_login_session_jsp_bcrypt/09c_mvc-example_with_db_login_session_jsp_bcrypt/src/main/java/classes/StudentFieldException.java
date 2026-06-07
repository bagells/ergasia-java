package classes;

/**
 * αυτό το exception class χρησιμοποιείται όταν στη δημιουργία ενός αντικειμένου student
 * κάποιο πεδίο παίρνει διαφορετική τιμή απο αυτή που περιμένει ο τύπος μεταβλητής του
 */
public class StudentFieldException extends Exception{
    public StudentFieldException() {
        super("please enter a valid input");
    }
}