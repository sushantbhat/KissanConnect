package com.example.sushantbhat.kissanconnect;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity {

    Spinner userType;
    Button registerButton;
    EditText userName, password, phone, email, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        String userTypes[] = {"Farmers", "Experts", "Consumers"};
        userType = (Spinner) findViewById(R.id.UserType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userTypes);
        userType.setAdapter(adapter);

        registerButton = (Button) findViewById(R.id.RegisterButton);
        userName = (EditText) findViewById(R.id.UserName);
        password = (EditText) findViewById(R.id.Password);
        phone = (EditText) findViewById(R.id.Phone);
        email = (EditText) findViewById(R.id.Email);
        address = (EditText) findViewById(R.id.Address);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }

    void insertData(){
        String usrname = userName.getText().toString();
        String passw = password.getText().toString();
        String pno = phone.getText().toString();
        String emailId = email.getText().toString();
        String adress = address.getText().toString();
        String userTyp = userType.getSelectedItem().toString();
        BackgroundWorker bw = new BackgroundWorker(this);
        //Toast.makeText(this, userTyp, Toast.LENGTH_LONG).show();
        bw.execute("register",usrname,passw, pno, emailId,adress, userTyp);
    }

}
