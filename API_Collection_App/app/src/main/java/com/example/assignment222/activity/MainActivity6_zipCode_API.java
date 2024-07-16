package com.example.assignment222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment222.R;

public class MainActivity6_zipCode_API extends AppCompatActivity {
    private EditText editTextZipCode;
    private ImageView error_image1;
    private TextView error_message;
    private TextView textViewZipCode;
    private Button buttonZipCode;
    private String url = "https://api.zippopotam.us/us/";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_zipcode_api);

        // Initialize views
        editTextZipCode = findViewById(R.id.editTextZipCode);
        error_image1=findViewById(R.id.error_image1);
        error_image1.setVisibility(View.INVISIBLE);
        error_message=findViewById(R.id.error_message);
        error_message.setVisibility(View.INVISIBLE);
        textViewZipCode = findViewById(R.id.textViewZipCode);
        buttonZipCode = findViewById(R.id.buttonZipCode);
        queue = Volley.newRequestQueue(this);


        //************* action
        buttonZipCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country=editTextZipCode.getText().toString();
                if(country.isEmpty()){
                    error_image1.setVisibility(View.VISIBLE);
                    error_message.setVisibility(View.VISIBLE);
                }
                else {
                    error_image1.setVisibility(View.INVISIBLE);
                    error_message.setVisibility(View.INVISIBLE);

                    url = url + country;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    textViewZipCode.setText(response);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity6_zipCode_API.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(stringRequest);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity6_zipCode_API.this, MainActivity3_home.class);
        startActivity(intent);
        finish();
    }
}
