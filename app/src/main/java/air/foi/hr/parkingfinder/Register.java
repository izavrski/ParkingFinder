package air.foi.hr.parkingfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button btnRegister;
    EditText etName, etLicensePlate, etUsername, etPassword, etBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText)findViewById(R.id.etName);
        etLicensePlate = (EditText)findViewById(R.id.etLicensePlate);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etBalance = (EditText)findViewById(R.id.etBalance);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:

                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String licensePlate = etLicensePlate.getText().toString();
                int balance = Integer.parseInt(etBalance.getText().toString());

                User user = new User(name, balance, username, password, licensePlate);

                registerUser(user);

                break;
        }
    }

    private void registerUser(User user){

        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

                startActivity(new Intent(Register.this, Login.class));


            }
        });


    }
}
