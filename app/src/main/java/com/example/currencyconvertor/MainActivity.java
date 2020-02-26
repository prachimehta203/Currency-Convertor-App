package com.example.currencyconvertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    public RequestQueue mQueue ;
    String usd_euro;
    String usd_inr;
    String usd_yen;
    String eur_usd,eur_inr,eur_jpy;
    String inr_usd,inr_eur,inr_jpy;
    String jpy_usd,jpy_inr,jpy_eur;

    Spinner spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        additemonspinners();
        mQueue = Volley.newRequestQueue(this);
        jasonParse1();
        jasonParse2();
        jasonParse3();
        jasonParse4();






    }


    public   void jasonParse1(){
        String url="https://api.exchangeratesapi.io/latest?base=USD";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject usd = null;
                        try {
                            usd = response.getJSONObject("rates");

                            usd_euro = usd.getString("EUR");
                            usd_inr = usd.getString("INR");
                            usd_yen = usd.getString("JPY");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }
    public   void jasonParse2(){
        String url="https://api.exchangeratesapi.io/latest?base=EUR";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject eur = null;
                        try {

                            eur = response.getJSONObject("rates");


                            eur_usd = eur.getString("USD");
                            eur_inr = eur.getString("INR");
                            eur_jpy = eur.getString("JPY");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }
    public   void jasonParse3(){
        String url="https://api.exchangeratesapi.io/latest?base=INR";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject inr = null;
                        try {

                            inr = response.getJSONObject("rates");

                            inr_usd = inr.getString("USD");
                            inr_eur = inr.getString("EUR");
                            inr_jpy = inr.getString("JPY");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }
    public   void jasonParse4(){
        String url="https://api.exchangeratesapi.io/latest?base=JPY";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject jpy = null;

                        try {

                            jpy = response.getJSONObject("rates");
                            jpy_usd = jpy.getString("USD");
                            jpy_inr = jpy.getString("INR");
                            jpy_eur = jpy.getString("EUR");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);

    }


    public void additemonspinners (){
        spinner1 = (Spinner) findViewById(R.id.s1);
        spinner2 = (Spinner) findViewById(R.id.s2);
        List<String> list =new ArrayList<String>();
        list.add("US Dollar");
        list.add("European EURO");
        list.add("Japanese Yen");
        list.add("Indian Rupees");
        ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(dataadapter);
        spinner2.setAdapter(dataadapter);


    }

public void clickFunction(View view){

    EditText text = (EditText)findViewById(R.id.editText3);
    String b = (String) text.getText().toString();
    String string1 = spinner1.getSelectedItem().toString();
    String string2 = spinner2.getSelectedItem().toString();

    double amt;
    String test;
    if(!b.isEmpty())
    {
        if(string1.equals(string2))
        {
            Toast.makeText(this, "No conversion to be made!!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="European EURO" && string2=="US Dollar") || (string2=="US Dollar" &&  string1=="European EURO")){
            amt=Double.parseDouble(b)* Double.parseDouble(usd_euro);
            Toast.makeText(this,amt + "€!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Japanese Yen" && string2=="US Dollar")|| (string2=="US Dollar" &&  string1=="Japanese Yen"      )){
            amt=Double.parseDouble(b)* Double.parseDouble(usd_yen);
            Toast.makeText(this,amt + "¥!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Indian Rupees" && string2=="US Dollar") || ( string2=="US Dollar" &&  string1=="Indian Rupees")){
            amt=Double.parseDouble(b)* Double.parseDouble(usd_inr);
            Toast.makeText(this,amt + "₹!", Toast.LENGTH_SHORT).show();
        }

        if((string1=="US Dollar" && string2=="European EURO")|| (string2=="European EURO" && string1=="US Dollar")){
            amt=Double.parseDouble(b)*Double.parseDouble(eur_usd);
            Toast.makeText(this,amt + "$!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Japanese Yen" && string2=="European EURO")|| (string2=="European EURO" && string1=="Japanese Yen")){
            amt=Double.parseDouble(b)*Double.parseDouble(eur_jpy);
            Toast.makeText(this,amt + "¥!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Indian Rupees" && string2=="European EURO")|| (   string2=="European EURO" && string1=="Indian Rupees" )){
            amt=Double.parseDouble(b)*Double.parseDouble(eur_inr);
            Toast.makeText(this,amt + "₹!", Toast.LENGTH_SHORT).show();
        }

        if((string1=="US Dollar" && string2=="Japanese Yen")|| (string2=="Japanese Yen" && string1=="US Dollar"  )){
            amt=Double.parseDouble(b)*Double.parseDouble(jpy_usd);
            Toast.makeText(this,amt + "$!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="European EURO" && string2=="Japanese Yen")|| ( string2=="Japanese Yen" && string1=="European EURO"  )){
            amt=Double.parseDouble(b)*Double.parseDouble(jpy_eur);
            Toast.makeText(this,amt + "€!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Indian Rupees" && string2=="Japanese Yen")|| (  string2=="Japanese Yen" &&  string1=="Indian Rupees"  )){
            amt=Double.parseDouble(b)*Double.parseDouble(jpy_inr);
            Toast.makeText(this,amt + "₹!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="US Dollar" && string2=="Indian Rupees") || ( string2=="Indian Rupees"  &&  string1=="US Dollar" )){
            amt=Double.parseDouble(b)*Double.parseDouble(inr_usd);
            Toast.makeText(this,amt + "$!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="Japanese Yen" && string2=="Indian Rupees")|| (string2=="Indian Rupees" && string1=="Japanese Yen"  )){
            amt=Double.parseDouble(b)* Double.parseDouble(inr_jpy);
            Toast.makeText(this,amt + "¥!", Toast.LENGTH_SHORT).show();
        }
        if((string1=="European EURO" && string2=="Indian Rupees")|| ( string2=="Indian Rupees" && string1=="European EURO"       )){
            amt=Double.parseDouble(b)*Double.parseDouble(inr_eur);
            Toast.makeText(this,amt + "€!", Toast.LENGTH_SHORT).show();
        }


    }

    else{
        Toast.makeText(this, "Enter the amount to be converted!", Toast.LENGTH_SHORT).show();


    }

}
}

