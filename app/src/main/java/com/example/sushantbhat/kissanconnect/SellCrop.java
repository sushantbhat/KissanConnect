package com.example.sushantbhat.kissanconnect;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SellCrop extends Activity {

    Button submitButton;
    EditText cropName, price, quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_crop);
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
    }

    void addCrop(String user){
        String crpName = cropName.getText().toString();
        String prce = price.getText().toString();
        String quant = quantity.getText().toString();
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute("addCrop", crpName, user, prce, quant);
    }
}
