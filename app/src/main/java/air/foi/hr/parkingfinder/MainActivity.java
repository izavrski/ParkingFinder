package air.foi.hr.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogout;
    EditText etName, etLicensePlate, etUsername, etBalance;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etLicensePlate = (EditText)findViewById(R.id.etLicensePlate);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etBalance = (EditText)findViewById(R.id.etBalance);

        btnLogout = (Button)findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate()==true) {
            displayUserDetails();
        }else {
            startActivity(new Intent(this, Login.class));
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

        etUsername.setText(user.username);
        etName.setText(user.name);
        etLicensePlate.setText(user.licensePlate);
        etBalance.setText(user.balance + "");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogout:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);


                startActivity(new Intent(this, Login.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
