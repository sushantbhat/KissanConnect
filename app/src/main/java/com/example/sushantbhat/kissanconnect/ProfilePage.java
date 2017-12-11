package com.example.sushantbhat.kissanconnect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class ProfilePage extends AppCompatActivity {
    TextView welcomeMesg;
    Button sellCrop, buyCrop, askExpert, answerQueries, fertilizerGuide, cropInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        Bundle b = getIntent().getExtras();

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");

        final String user = b.getString("username");
        final String userType = b.getString("usertype");

        welcomeMesg= (TextView)findViewById(R.id.welcomeMsg);
        welcomeMesg.setText("Welcome " + user);
        sellCrop = (Button) findViewById(R.id.sellCrop);
        buyCrop = (Button) findViewById(R.id.buyCrop);
        askExpert = (Button) findViewById(R.id.askExpert);
        answerQueries = (Button) findViewById(R.id.answerQueries);
        cropInfo = (Button) findViewById(R.id.cropInfo);
        fertilizerGuide = (Button) findViewById(R.id.fertilizerGuide);


        sellCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equals("Farmers")) {
                    Intent i = new Intent(ProfilePage.this, SellCrop.class);
                    i.putExtra("user", user);
                    startActivity(i);
                }
                else{
                    showError("farmers");
                }
            }
        });

        buyCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equals("Consumers")) {
                    Intent i = new Intent(ProfilePage.this, CropList.class);
                    startActivity(i);
                }
                else{
                    showError("consumers");
                }
            }
        });

        askExpert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorTemp();
            }
        });

        answerQueries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorTemp();
            }
        });

        fertilizerGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorTemp();
            }
        });

        cropInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showErrorTemp();
            }
        });


        try {
            if(!lang.equals("en"))
                changeLang(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void showError(String userType){
        Toast.makeText(this, "This feature is available only to " + userType, Toast.LENGTH_LONG).show();
    }

    void showErrorTemp(){
        Toast.makeText(this, "This feature is not yet available. ", Toast.LENGTH_LONG).show();
    }



    void changeLang(String lang) throws Exception{
        BackgroundWorker bw = new BackgroundWorker(this);
        String widgetType = "TextView";

        TextView tv = (TextView) findViewById(R.id.welcomeMsg);
        String word = tv.getText().toString();
        String id = Integer.toString(R.id.welcomeMsg);
        bw.execute("langChange",lang,id,word,widgetType);

        widgetType = "Button";

        Button button = (Button) findViewById(R.id.sellCrop);
        word = button.getText().toString();
        id = Integer.toString(R.id.sellCrop);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        button = (Button) findViewById(R.id.buyCrop);
        word = button.getText().toString();
        id = Integer.toString(R.id.buyCrop);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        button = (Button) findViewById(R.id.askExpert);
        word = button.getText().toString();
        id = Integer.toString(R.id.askExpert);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        button = (Button) findViewById(R.id.answerQueries);
        word = button.getText().toString();
        id = Integer.toString(R.id.answerQueries);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        button = (Button) findViewById(R.id.fertilizerGuide);
        word = button.getText().toString();
        id = Integer.toString(R.id.fertilizerGuide);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        button = (Button) findViewById(R.id.cropInfo);
        word = button.getText().toString();
        id = Integer.toString(R.id.cropInfo);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.LangChange:

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
                                    SharedPreferences.Editor editor2 = getSharedPreferences("LangChoice", MODE_PRIVATE).edit();
                                    editor2.putString("lang", "en");
                                    editor2.apply();
                                    //changeLang("en");

                                    TextView tv = (TextView) findViewById(R.id.welcomeMsg);
                                    tv.setText("Welcome");

                                    Button button = (Button) findViewById(R.id.sellCrop);
                                    button.setText("Sell \n crop");

                                    button = (Button) findViewById(R.id.buyCrop);
                                    button.setText("Buy \n crop");

                                    button = (Button) findViewById(R.id.cropInfo);
                                    button.setText("Crop \n info");

                                    button = (Button) findViewById(R.id.fertilizerGuide);
                                    button.setText("Fertilizer \n guide");

                                    button = (Button) findViewById(R.id.answerQueries);
                                    button.setText("Answer \n queries");

                                    button = (Button) findViewById(R.id.askExpert);
                                    button.setText("Ask \n expert");


                                } catch (Exception e) {

                                }
                                break;
                            case 1:
                                try {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("LangChoice", MODE_PRIVATE).edit();
                                    editor2.putString("lang", "kn");
                                    editor2.apply();
                                    changeLang("kn");
                                } catch (Exception e) {

                                }
                                break;
                            case 2:
                                try {
                                    SharedPreferences.Editor editor2 = getSharedPreferences("LangChoice", MODE_PRIVATE).edit();
                                    editor2.putString("lang", "hi");
                                    editor2.apply();
                                    changeLang("hi");
                                } catch (Exception e) {

                                }
                                break;
                        }
                    }

                });

                b.show();
                return true;


            case R.id.LogOut:
                Intent i = new Intent(ProfilePage.this, Welcome.class);
                startActivity(i);
                //showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
