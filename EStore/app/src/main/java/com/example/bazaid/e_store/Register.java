package com.example.bazaid.e_store;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText nametxt;
    EditText emailtxt;
    EditText passwordtxt;
    String name;
    String email;
    String password;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nametxt=(EditText) findViewById(R.id.registerName);
        emailtxt=(EditText) findViewById(R.id.registeremail);
        passwordtxt=(EditText) findViewById(R.id.registerPasswaord);

        context=this.context;
    }
    public void register (View view){
        name=nametxt.getText().toString();
        email=emailtxt.getText().toString();
        password=passwordtxt.getText().toString();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("Please check your e-mail!!!") || response.contains("E-mail already exists!!!")){

                }
                Toast.makeText(Register.this,response,Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error instanceof TimeoutError){
                    Toast.makeText(Register.this, "Timeout Error!!!", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof NoConnectionError){
                    Toast.makeText(Register.this, "No Connection Error!!!", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof AuthFailureError){
                    Toast.makeText(Register.this, "Authentication Failure Error!!!", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof NetworkError){
                    Toast.makeText(Register.this, "Network Error!!!", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ServerError){
                    Toast.makeText(Register.this, "Server Error!!!", Toast.LENGTH_SHORT).show();
                }
                else if(error instanceof ParseError){
                    Toast.makeText(Register.this, "JSON Parse Error!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            protected Map<String,String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constants.KEY_NAME, name);
                params.put(Constants.KEY_EMAIL, email);
                params.put(Constants.KEY_PASSWORD, password);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("User-Agent", "EStore");
                return headers;
            }
        };

        MySingleton.getInstance(Register.this).addToRequestQueue(stringRequest);
    }
}
