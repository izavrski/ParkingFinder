package air.foi.hr.parkingfinder;

/**
 * Created by izavrski on 8.8.2015..
 */
public class User {
    String name, licensePlate, username, password;
    int balance;


    public User (String name, int balance, String username, String password, String licensePlate){
        this.name=name;
        this.balance=balance;
        this.username=username;
        this.password=password;
        this.licensePlate=licensePlate;
    }

    public User (String username, String password){
        this.username=username;
        this.password=password;
        this.name="";
        this.balance=50;
        this.licensePlate="";
    }
}
