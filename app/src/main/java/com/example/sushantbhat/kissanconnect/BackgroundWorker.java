package com.example.sushantbhat.kissanconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    String username = "", userType = "";
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String login_url = "http://dollarbee.000webhostapp.com/login.php";
        String register_url = "http://dollarbee.000webhostapp.com/register.php";
        String addCrop_url = "http://dollarbee.000webhostapp.com/addCrop.php";
        String getCrop_url = "http://dollarbee.000webhostapp.com/cropList.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                username = user_name;
                String password = params[2];
                String user_type = params[3];
                userType = user_type;
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        + URLEncoder.encode("user_type","UTF-8")+"="+URLEncoder.encode(user_type,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register")){
            try{
                String user_name = params[1];
                String password = params[2];
                String phone = params[3];
                String email = params[4];
                String address = params[5];
                String userType = params[6];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        + URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        + URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        + URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        + URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        + URLEncoder.encode("user_type","UTF-8")+"="+URLEncoder.encode(userType,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("addCrop")){
            try{
                String cropName = params[1];
                String seller = params[2];
                username = seller;
                String price = params[3];
                String quantity = params[4];
                URL url = new URL(addCrop_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(cropName,"UTF-8")+"&"
                        + URLEncoder.encode("seller","UTF-8")+"="+URLEncoder.encode(seller,"UTF-8")+"&"
                        + URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+"&"
                        + URLEncoder.encode("quantity","UTF-8")+"="+URLEncoder.encode(quantity,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("getCrop")){
            try{
                URL url = new URL(getCrop_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = " ";
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Login Success")){
            Intent i = new Intent(context, ProfilePage.class);
            i.putExtra("username",username);
            i.putExtra("usertype",userType);
            context.startActivity(i);
        }
        else if(result.equals("Register success")){
            alertDialog.setMessage("Registration successful");
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent i = new Intent(context, Welcome.class);
                    context.startActivity(i);
                }
            });

        }
        else if(result.equals("addCrop success")){
            alertDialog.setMessage("Crop details added successfully. You will be notified once we find consumer.");
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent i = new Intent(context, ProfilePage.class);
                    i.putExtra("username",username);
                    i.putExtra("usertype","Farmers");
                    context.startActivity(i);
                }
            });

        }
        else if(result.equals("Register error")){
            alertDialog.setMessage("Username exists");
            alertDialog.show();
        }
        else if(result.equals("Login error")){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if(result.equals("addCrop error")){
            alertDialog.setMessage(result);
            alertDialog.show();
        }
        else if(result.equals("cropList error")){
            alertDialog.setMessage("Couldn't fetch crop details");
            alertDialog.show();
        }
        else{
            /*alertDialog.setMessage(result);
            alertDialog.show();*/
            String crops[] = result.split(";");
            cropList(crops);
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public void cropList(String[] crops) {
        TableLayout stk = (TableLayout) ((Activity)context).findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(context);
        tbrow0.setWeightSum(5);

        TextView tv0 = new TextView(context);
        tv0.setTypeface(null, Typeface.BOLD);
        tv0.setText("CROP ");
        tv0.setTextColor(Color.WHITE);
        //setParams(tv0);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(context);
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setText("SELLER ");
        tv1.setTextColor(Color.WHITE);
        //setParams(tv1);
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(context);
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setText("PRICE ");
        tv2.setTextColor(Color.WHITE);
        //setParams(tv2);
        tbrow0.addView(tv2);

        TextView tv3 = new TextView(context);
        tv3.setTypeface(null, Typeface.BOLD);
        tv3.setText("QUANTITY ");
        tv3.setTextColor(Color.WHITE);
        //setParams(tv3);
        tbrow0.addView(tv3);

        TextView tv4 = new TextView(context);
        tv4.setText(" ");
        tv4.setTextColor(Color.WHITE);
        //setParams(tv4);
        tbrow0.addView(tv4);

        stk.addView(tbrow0);
        for (int i = 0; i < crops.length; i++) {
            TableRow tbrow = new TableRow(context);
            tbrow0.setWeightSum(5);

            String cropData[] = crops[i].split("-");

            TextView t1v = new TextView(context);
            t1v.setText(cropData[0] + " ");
            t1v.setTextColor(Color.WHITE);
            //t1v.setGravity(Gravity);
            //setParams(t1v);
            tbrow.addView(t1v);

            TextView t2v = new TextView(context);
            t2v.setText(cropData[1] + " ");
            t2v.setTextColor(Color.WHITE);
            //t2v.setGravity(Gravity.CENTER);
            //setParams(t2v);
            tbrow.addView(t2v);

            TextView t3v = new TextView(context);
            t3v.setText("Rs." + cropData[2] + " ");
            t3v.setTextColor(Color.WHITE);
            //t3v.setGravity(Gravity.CENTER);
            //setParams(t3v);
            tbrow.addView(t3v);

            TextView t4v = new TextView(context);
            t4v.setText(cropData[3] + "kg" + " ");
            t4v.setTextColor(Color.WHITE);
            //t4v.setGravity(Gravity.CENTER);
            //setParams(t4v);
            tbrow.addView(t4v);

            Button btn = new Button(context);
            btn.setText("View details");
            btn.setTextColor(Color.WHITE);
            btn.setGravity(Gravity.CENTER);
            tbrow.addView(btn);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sayHello(finalI);

                }
            });
            stk.addView(tbrow);
        }

    }
    void sayHello(int i){
        Toast.makeText(context, "Hello" + i , Toast.LENGTH_LONG).show();
    }

    void setParams(TextView tv){
        tv.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

    }
}
