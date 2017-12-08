package com.example.sushantbhat.kissanconnect;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilePage extends Activity {
    TextView welcomeMesg;
    Button sellCrop, buyCrop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        Bundle b = getIntent().getExtras();
        final String user = b.getString("username");
        final String userType = b.getString("usertype");

        welcomeMesg= (TextView)findViewById(R.id.welcomeMsg);
        welcomeMesg.setText("Welcome " + user);
        sellCrop = (Button) findViewById(R.id.sellCrop);
        buyCrop = (Button) findViewById(R.id.buyCrop);

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
    }
    void showError(String userType){
        Toast.makeText(this, "This feature is available only to " + userType, Toast.LENGTH_LONG).show();
    }


}
