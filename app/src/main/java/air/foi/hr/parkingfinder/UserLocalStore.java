package air.foi.hr.parkingfinder;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by izavrski on 8.8.2015..
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){

        userLocalDatabase=context.getSharedPreferences(SP_NAME,0);
    }

    public void storeUserData (User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name", user.name);
        spEditor.putString("licensePlate", user.licensePlate);;
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String name = userLocalDatabase.getString("name", "");
        int balance = userLocalDatabase.getInt("balance", 50);
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password","");
        String licensePlate = userLocalDatabase.getString("licensePlate","");

        User storedUser = new User(name, balance, username, password, licensePlate);

        return storedUser;
    }

    public void setUserLoggedIn (boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn );
        spEditor.commit();
    }

    public  boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("loggedIn", false)== true){
            return true;
        } else {
            return false;
        }
    }

    public void clearUserData (){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
