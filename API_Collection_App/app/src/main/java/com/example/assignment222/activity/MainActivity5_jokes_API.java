package com.example.assignment222.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity5_jokes_API extends AppCompatActivity {
    private TextView txtJokes;
    private Button buttonGenerateJokes;
    private final String url = "https://official-joke-api.appspot.com/random_joke";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_jokes_api);

        // Initialize views
        txtJokes=findViewById(R.id.txtJokes);
        buttonGenerateJokes=findViewById(R.id.buttonGenerateJokes);
        queue = Volley.newRequestQueue(this);

        //************* action
        buttonGenerateJokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txtJokes.setText(response);
                                String setup = "";
                                String punchline="";
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    setup = jsonObject.getString("setup");
                                    punchline= jsonObject.getString("punchline");

                                    txtJokes.setText("Yousef: "+setup+"\n\n"+"Basel: "+punchline);
                                    //close keyboard
                                    InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    input.hideSoftInputFromWindow(v.getWindowToken(), 0);

                                } catch (JSONException exception) {
                                    exception.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity5_jokes_API.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity5_jokes_API.this, MainActivity3_home.class);
        startActivity(intent);
        finish();
    }
}
