package com.example.sushantbhat.kissanconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    Button registerButton , loginButton;
    EditText username;
    EditText password;
    Spinner userType;
    TextView greetingText;
    String userTypes[] = {"Farmer", "Expert", "Consumer"};
    String tableNames[] = {"Farmers", "Experts", "Consumers"};
    //String language = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        username = (EditText)findViewById(R.id.UserNameField);
        password = (EditText)findViewById(R.id.PasswrdField);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        loginButton = (Button) findViewById(R.id.LoginButton);
        greetingText = (TextView) findViewById(R.id.GreetingText);
        //String userTypes[] = {"Farmers", "Experts", "Consumers"};
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



        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","NA");
        if(lang.equals("NA")) {


            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Select language");

            String[] types = {"English", "ಕನ್ನಡ", "हिंदी"};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    switch (which) {
                        case 0:
                            try {
                                changeLang("en");
                            } catch (Exception e) {

                            }
                            break;
                        case 1:
                            try {
                                changeLang("kn");
                            } catch (Exception e) {

                            }
                            break;
                        case 2:
                            try {
                                changeLang("hi");
                            } catch (Exception e) {

                            }
                            break;
                    }
                }

            });

            b.show();
        }
        else{
            try {
                changeLang(lang);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Select language");

        String[] types = {"English", "ಕನ್ನಡ", "हिंदी"};
        b.setItems(types, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                switch (which) {
                    case 0:
                        try {
                            changeLang("en");
                        } catch (Exception e) {

                        }
                        break;
                    case 1:
                        try {
                            changeLang("kn");
                        } catch (Exception e) {

                        }
                        break;
                    case 2:
                        try {
                            changeLang("hi");
                        } catch (Exception e) {

                        }
                        break;
                }
            }

        });

        b.show();

        return true;
    }*/

    void changeLang(String lang) throws Exception{
        SharedPreferences.Editor editor2 = getSharedPreferences("LangChoice", MODE_PRIVATE).edit();
        editor2.putString("lang", lang);
        editor2.apply();
        if(true){//!lang.equals("en")) {
            BackgroundWorker bw = new BackgroundWorker(this);
            String widgetType = "TextView";

            TextView tv = (TextView) findViewById(R.id.GreetingText);
            String word = tv.getText().toString();
            String id = Integer.toString(R.id.GreetingText);
            bw.execute("langChange", lang, id, word, widgetType);

            tv = (TextView) findViewById(R.id.UsernameText);
            word = tv.getText().toString();
            id = Integer.toString(R.id.UsernameText);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, word, widgetType);

            tv = (TextView) findViewById(R.id.PasswordText);
            word = tv.getText().toString();
            id = Integer.toString(R.id.PasswordText);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, word, widgetType);

            tv = (TextView) findViewById(R.id.UsertypeText);
            word = tv.getText().toString();
            id = Integer.toString(R.id.UsertypeText);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, word, widgetType);

            widgetType = "Button";

            Button button = (Button) findViewById(R.id.LoginButton);
            word = button.getText().toString();
            id = Integer.toString(R.id.LoginButton);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, word, widgetType);

            button = (Button) findViewById(R.id.RegisterButton);
            word = button.getText().toString();
            id = Integer.toString(R.id.RegisterButton);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, word, widgetType);

            widgetType = "Spinner";

            //String userTypes[] = {"Farmers", "Experts", "Consumers"};
            id = Integer.toString(R.id.UserType);
            bw = new BackgroundWorker(this);
            bw.execute("langChange", lang, id, userTypes[0] + " " + userTypes[1] + " " + userTypes[2], widgetType);
        }
    }

    public void validateUser(){

        String usrname = username.getText().toString();
        String passw = password.getText().toString();
        int position = userType.getSelectedItemPosition();
        String userTyp = tableNames[position];

        if(userTyp.equals("Experts")){
            Toast.makeText(this,"Experts feture is not yet available",Toast.LENGTH_LONG).show();
            return;
        }

        BackgroundWorker bw = new BackgroundWorker(this);

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");

        bw.execute("login",usrname,passw, userTyp, lang);

    }

    @Override
    public void onBackPressed() {
    }


}
