package com.example.sushantbhat.kissanconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    String username = "", userType = "";
    String lang = "", id = "", widgetType="";
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
                lang = params[4];
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
                lang = params[7];

                if(user_name.equals("") || password.equals("") || phone.equals("") || email.equals("") || address.equals("") || userType.equals("")){
                    return "Register error blank";
                }

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
                lang = params[5];


                if(cropName.equals("") || price.equals("") || quantity.equals("")){
                    return "addCrop error";
                }

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
                lang = params[1];
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
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
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
        else if(type.equals("langChange")){
            try{
                lang = params[1];
                id = params[2];
                String word = params[3];
                widgetType = params[4];
                String append = "";

                word = word.replace(" ","%20");
                word = word.replace("\n","");
                String lang_url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20170118T054718Z.b0929ea00fdfe498.e6398b4f0ef3b019acf6b3d98572aebb989e6e05&text='"+ word + "'&lang=" + lang + ";";
                URL url = new URL(lang_url);


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuffer str = new StringBuffer();
                String line = null;
                while((line = bufferedReader.readLine()) != null)
                {
                    str.append(line);
                }
                String result=str.toString();
                result=result.substring(result.lastIndexOf('[')+2,result.lastIndexOf('"'));
                result=result.substring(1,result.length()-1);
                if(widgetType.equals("AlertDialog")){
                    append = params[5];
                    result += ";" + append;
                }
                return "^Lang^" + result;

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
            /*alertDialog.setTitle("Registration status");
            alertDialog.setMessage("Registration successful");
            alertDialog.show();
            alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Intent i = new Intent(context, Welcome.class);
                    context.startActivity(i);
                }
            });*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Registration status / Registration successful.", widgetType, "Register Success");
        }
        else if(result.equals("addCrop success")){
            /*alertDialog.setTitle("Note");
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
            });*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Note / Crop details added successfully. You will be notified once we find consumer.", widgetType, "AddCrop Success");

        }
        else if(result.equals("Register error blank")){
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Registration status / Please check entered values.", widgetType, "Register error");
        }

        else if(result.equals("Register error")){
            /*alertDialog.setTitle("Registration status");
            alertDialog.setMessage("Username exists");
            alertDialog.show();*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Registration status / Username exists.", widgetType, "Register error");
        }
        else if(result.equals("Login error")){
            /*alertDialog.setTitle("Login status");
            alertDialog.setMessage(result);
            alertDialog.show();*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Login status / Login error", widgetType, "Login error");
        }
        else if(result.equals("addCrop error")){
            /*alertDialog.setTitle("Note");
            alertDialog.setMessage("Couldn't add details, please check entered values.");
            alertDialog.show();*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Note / Couldn't add details, please check entered values.", widgetType, "Addcrop error");
        }
        else if(result.equals("cropList error")){
            /*alertDialog.setTitle("Note");
            alertDialog.setMessage("Couldn't fetch crop details");
            alertDialog.show();*/
            widgetType = "AlertDialog";
            BackgroundWorker bw = new BackgroundWorker(context);
            bw.execute("langChange", lang, null, "Note / Couldn't fetch crop details", widgetType, "CropList error");
        }

        else if(result.startsWith("^Lang^")){
            result = result.replace("^Lang^","");
            if(widgetType.equals("TextView")) {
                TextView textView = (TextView) ((Activity) context).findViewById(Integer.parseInt(id));
                textView.setText(result + " ");
            }
            else if(widgetType.equals("Button")){
                Button button = (Button) ((Activity) context).findViewById(Integer.parseInt(id));
                button.setText(result);
            }
            else if(widgetType.equals("Spinner")){
                String userTypes[] = result.split(" ");
                Spinner spinner = (Spinner) ((Activity) context).findViewById(R.id.UserType);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, userTypes);
                spinner.setAdapter(adapter);
            }
            else if(widgetType.equals("AlertDialog")){

                String type = result.split(";")[1];
                String val[] = result.split(";")[0].split("/");

                if(type.equals("Register Success")){
                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            Intent i = new Intent(context, Welcome.class);
                            context.startActivity(i);
                        }
                    });
                }
                else if(type.equals("AddCrop Success")){
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

                alertDialog.setTitle(val[0]);
                alertDialog.setMessage(val[1]);
                alertDialog.show();
            }
        }
        else{
            //alertDialog.setMessage(result);
            //alertDialog.show();
            String crops[] = result.split(";");
            cropList(crops);

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    public void cropList(String[] crops) {

        BackgroundWorker bw = new BackgroundWorker(context);
        String wType = "";

        TableLayout stk = (TableLayout) ((Activity)context).findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(context);
        tbrow0.setWeightSum(5);

        wType = "TextView";

        TextView tv0 = new TextView(context);
        tv0.setId(new Integer(0));
        tv0.setTypeface(null, Typeface.BOLD);
        tv0.setText("CROP ");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        //setParams(tv0);
        tbrow0.addView(tv0);
        bw.execute("langChange",lang,Integer.toString(0),"CROP",wType);

        TextView tv1 = new TextView(context);
        tv1.setId(new Integer(1));
        tv1.setTypeface(null, Typeface.BOLD);
        tv1.setText("SELLER ");
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        tv1.setTextColor(Color.WHITE);
        //setParams(tv1);
        tbrow0.addView(tv1);
        new BackgroundWorker(context).execute("langChange",lang,Integer.toString(1),"SELLER ",wType);

        TextView tv2 = new TextView(context);
        tv2.setId(new Integer(2));
        tv2.setTypeface(null, Typeface.BOLD);
        tv2.setText("PRICE / kg ");
        tv2.setTextColor(Color.WHITE);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        //setParams(tv2);
        tbrow0.addView(tv2);
        new BackgroundWorker(context).execute("langChange",lang,Integer.toString(2),"PRICE ",wType);

        TextView tv3 = new TextView(context);
        tv3.setId(new Integer(3));
        tv3.setTypeface(null, Typeface.BOLD);
        tv3.setText("QUANTITY (kg) ");
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        tv3.setTextColor(Color.WHITE);
        //setParams(tv3);
        tbrow0.addView(tv3);
        new BackgroundWorker(context).execute("langChange",lang,Integer.toString(3),"QUANTITY ",wType);

        TextView tv4 = new TextView(context);
        tv4.setText(" ");
        tv4.setTextColor(Color.WHITE);
        //setParams(tv4);
        tbrow0.addView(tv4);
        //new BackgroundWorker(context).execute("langChange",lang,Integer.toString(1),"SELLER",wType);

        stk.addView(tbrow0);
        for (int i = 0; i < crops.length; i++) {
            int base = i+1;
            TableRow tbrow = new TableRow(context);
            tbrow0.setWeightSum(5);

            String cropData[] = crops[i].split("-");

            TextView t1v = new TextView(context);
            t1v.setId(new Integer(base*10+1));
            t1v.setText(cropData[0] + " ");
            t1v.setTextColor(Color.WHITE);
            //t1v.setGravity(Gravity);
            //setParams(t1v);
            tbrow.addView(t1v);
            new BackgroundWorker(context).execute("langChange",lang,Integer.toString(base*10+1),cropData[0],wType);

            TextView t2v = new TextView(context);
            t2v.setId(new Integer(base*10+2));
            t2v.setText(cropData[1] + " ");
            t2v.setTextColor(Color.WHITE);
            //t2v.setGravity(Gravity.CENTER);
            //setParams(t2v);
            tbrow.addView(t2v);
            new BackgroundWorker(context).execute("langChange",lang,Integer.toString(base*10+2),cropData[1],wType);

            TextView t3v = new TextView(context);
            t3v.setId(new Integer(base*10+3));
            t3v.setText("Rs." + cropData[2] + " ");
            t3v.setTextColor(Color.WHITE);
            //t3v.setGravity(Gravity.CENTER);
            //setParams(t3v);
            tbrow.addView(t3v);
            new BackgroundWorker(context).execute("langChange",lang,Integer.toString(base*10+3),cropData[2],wType);

            TextView t4v = new TextView(context);
            t4v.setId(new Integer(base*10+4));
            t4v.setText(cropData[3] + "kg" + " ");
            t4v.setTextColor(Color.WHITE);
            //t4v.setGravity(Gravity.CENTER);
            //setParams(t4v);
            tbrow.addView(t4v);
            new BackgroundWorker(context).execute("langChange",lang,Integer.toString(base*10+4),cropData[3],wType);

            Button btn = new Button(context);
            btn.setId(new Integer(base*10+5));
            btn.setText("Details");
            btn.setTextColor(Color.WHITE);
            btn.setGravity(Gravity.CENTER);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            tbrow.addView(btn);
            final int finalI = i;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sayHello(finalI);

                }
            });
            stk.addView(tbrow);
            new BackgroundWorker(context).execute("langChange",lang,Integer.toString(base*10+5),"details","Button");
        }

    }
    void sayHello(int i){
        Toast.makeText(context, "Hello" + i , Toast.LENGTH_LONG).show();
    }

    void setParams(TextView tv){
        tv.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

    }
}
