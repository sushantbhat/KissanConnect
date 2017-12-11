package com.example.sushantbhat.kissanconnect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SellCrop extends Activity {

    Button submitButton;
    EditText cropName, price, quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_crop);

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");

        Bundle bundle = getIntent().getExtras();
        final String user = bundle.getString("user");

        submitButton = (Button) findViewById(R.id.submitButton);
        cropName = (EditText) findViewById(R.id.cropName);
        price = (EditText) findViewById(R.id.price);
        quantity = (EditText) findViewById(R.id.quantity);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCrop(user);
            }
        });

        try {
            if(!lang.equals("en"))
                changeLang(lang);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addCrop(String user){
        String crpName = cropName.getText().toString();
        String prce = price.getText().toString();
        String quant = quantity.getText().toString();

        SharedPreferences prefs = getSharedPreferences("LangChoice", MODE_PRIVATE);
        String lang = prefs.getString("lang","en");

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("addCrop", crpName, user, prce, quant, lang);
    }

    void changeLang(String lang) throws Exception{
        BackgroundWorker bw = new BackgroundWorker(this);
        String widgetType = "TextView";

        TextView tv = (TextView) findViewById(R.id.headingText);
        String word = tv.getText().toString();
        String id = Integer.toString(R.id.headingText);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.cropnameText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.cropnameText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.priceText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.priceText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        tv = (TextView) findViewById(R.id.quantityText);
        word = tv.getText().toString();
        id = Integer.toString(R.id.quantityText);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

        widgetType = "Button";

        Button button = (Button) findViewById(R.id.submitButton);
        word = button.getText().toString();
        id = Integer.toString(R.id.submitButton);
        bw = new BackgroundWorker(this);
        bw.execute("langChange",lang,id,word,widgetType);

    }
}
