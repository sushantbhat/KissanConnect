package com.example.sushantbhat.kissanconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

    Spinner userType;
    Button registerButton;
    EditText userName, password, phone, email, address;

    String userTypes[] = {"Farmer", "Expert", "Consumer"};
    String tableNames[] = {"Farmers", "Experts", "Consumers"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");

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

        try {
            if(!lang.equals("en"))
                changeLang(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void insertData(){
        String usrname = userName.getText().toString();
        String passw = password.getText().toString();
        String pno = phone.getText().toString();
        String emailId = email.getText().toString();
        String adress = address.getText().toString();
        int position = userType.getSelectedItemPosition();
        String userTyp = tableNames[position];

        if(userTyp.equals("Experts")){
            Toast.makeText(this, "Experts feture is not yet available",Toast.LENGTH_LONG).show();
            return;
        }

        BackgroundWorker bw = new BackgroundWorker(this);

        //Toast.makeText(this, userTyp, Toast.LENGTH_LONG).show();
        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");


        bw.execute("register",usrname,passw, pno, emailId,adress, userTyp, lang);
    }

    void changeLang(String lang) throws Exception{
        BackgroundWorker bw = new BackgroundWorker(this);
        String widgetType = "TextView";

        TextView tv = (TextView) findViewById(R.id.headingText);
        String word = tv.getText().toString();
        String id = Integer.toString(R.id.headingText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.usernameText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.usernameText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.passwordText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.passwordText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.emailText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.emailText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.phoneText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.phoneText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.usertypeText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.usertypeText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.addressText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.addressText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        widgetType = "Button";

        Button button = (Button) findViewById(R.id.RegisterButton);
        word = button.getText().toString();
        id = Integer.toString(R.id.RegisterButton);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        widgetType = "Spinner";

        //String userTypes[] = {"Farmers", "Experts", "Consumers"};
        id = Integer.toString(R.id.UserType);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,userTypes[0] + " " + userTypes[1] + " " + userTypes[2],widgetType);


    }

}
