package com.example.sushantbhat.kissanconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Welcome extends AppCompatActivity {

    Button registerButton , loginButton;
    EditText username;
    EditText password;
    Spinner userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        username = (EditText)findViewById(R.id.UserNameField);
        password = (EditText)findViewById(R.id.PasswrdField);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        loginButton = (Button) findViewById(R.id.LoginButton);

        String userTypes[] = {"Farmers", "Experts", "Consumers"};
        userType = (Spinner) findViewById(R.id.UserType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userTypes);
        userType.setAdapter(adapter);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Welcome.this, Register.class);
                startActivity(i);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getCrop();
                validateUser();
            }
        });
    }

    public void validateUser(){
        String usrname = username.getText().toString();
        String passw = password.getText().toString();
        String userTyp = userType.getSelectedItem().toString();
        BackgroundWorker bw = new BackgroundWorker(this);
        bw.execute("login",usrname,passw, userTyp);

    }


}
