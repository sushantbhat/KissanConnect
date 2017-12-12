package com.example.sushantbhat.kissanconnect;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CropDetails extends Activity {

    TextView nameV;
    Button notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);

        Bundle bundle = getIntent().getExtras();
        String seller = bundle.getString("seller");

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");


        nameV = (TextView) findViewById(R.id.NameValue);
        nameV.setText(seller);
        notify = (Button) findViewById(R.id.NotifyButton);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMsg();
            }
        });


        fetchDetails(lang);

        changeLang(lang);
    }

    void showMsg(){
        Toast.makeText(this, "This feature is not yet available", Toast.LENGTH_LONG).show();
    }


    void changeLang(String lang){
        if(!lang.equals("en")){
            BackgroundWorker bw = new BackgroundWorker(this);
            String widgetType = "TextView";

            TextView tv = (TextView) findViewById(R.id.Heading);
            String word = tv.getText().toString();
            String id = Integer.toString(R.id.Heading);
            bw.execute("langChange", lang, id, word, widgetType);

            tv = (TextView) findViewById(R.id.SellerName);
            word = tv.getText().toString();
            id = Integer.toString(R.id.SellerName);
            bw = new BackgroundWorker(this);
            bw.execute("langChange",lang,id,word,widgetType);

            tv = (TextView) findViewById(R.id.SellerPhone);
            word = tv.getText().toString();
            id = Integer.toString(R.id.SellerPhone);
            bw = new BackgroundWorker(this);
            bw.execute("langChange",lang,id,word,widgetType);

            tv = (TextView) findViewById(R.id.SellerAddress);
            word = tv.getText().toString();
            id = Integer.toString(R.id.SellerAddress);
            bw = new BackgroundWorker(this);
            bw.execute("langChange",lang,id,word,widgetType);

            tv = (TextView) findViewById(R.id.SellerEmail);
            word = tv.getText().toString();
            id = Integer.toString(R.id.SellerEmail);
            bw = new BackgroundWorker(this);
            bw.execute("langChange",lang,id,word,widgetType);

            widgetType = "Button";

            Button button = (Button) findViewById(R.id.NotifyButton);
            word = button.getText().toString();
            id = Integer.toString(R.id.NotifyButton);
            bw = new BackgroundWorker(this);
            bw.execute("langChange",lang,id,word,widgetType);
        }
    }

    void fetchDetails(String lang){
        BackgroundWorker bw = new BackgroundWorker(this);
        bw.execute("sellerDetails",nameV.getText().toString().trim(),lang);

    }

}
