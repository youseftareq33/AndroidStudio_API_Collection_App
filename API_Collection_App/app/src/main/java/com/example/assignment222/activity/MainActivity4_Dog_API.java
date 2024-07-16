package com.example.assignment222.activity;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment222.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity4_Dog_API extends AppCompatActivity {

    private ImageView imageViewDogs;
    private Button buttonGenerateDogPhoto;
    private final String url = "https://dog.ceo/api/breeds/image/random";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_dog_api);

        // Initialize views
        imageViewDogs = findViewById(R.id.imageViewDogs);
        buttonGenerateDogPhoto=findViewById(R.id.buttonGenerateDogPhoto);
        queue = Volley.newRequestQueue(this);

        //************* action
        buttonGenerateDogPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String imageUrl = jsonObject.getString("message");

                                    ImageRequest imageRequest = new ImageRequest(imageUrl,
                                            new Response.Listener<android.graphics.Bitmap>() {
                                                @Override
                                                public void onResponse(android.graphics.Bitmap response) {
                                                    imageViewDogs.setImageBitmap(response);
                                                }
                                            }, 0, 0, null,
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(MainActivity4_Dog_API.this, error.toString(), Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    queue.add(imageRequest);

                                    // Close keyboard
                                    InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    input.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity4_Dog_API.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(stringRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity4_Dog_API.this, MainActivity3_home.class);
        startActivity(intent);
        finish();
    }
}
