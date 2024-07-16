package com.example.assignment222.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment222.R;

public class MainActivity3_home extends AppCompatActivity {

    private Button button1;
    private TextView textView_dog_1;
    private TextView textView_dog_2;

    private Button button2;
    private TextView textView_jokes_1;
    private TextView textView_jokes_2;

    private Button button3;
    private TextView textView_zipCode_1;
    private TextView textView_zipCode_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_home);

        // Initialize views
        button1=findViewById(R.id.button1);
        textView_dog_1=findViewById(R.id.textView_dog_1);
        textView_dog_2=findViewById(R.id.textView_dog_2);
        textView_dog_2.setVisibility(View.INVISIBLE);

        button2=findViewById(R.id.button2);
        textView_jokes_1=findViewById(R.id.textView_jokes_1);
        textView_jokes_2=findViewById(R.id.textView_jokes_2);
        textView_jokes_2.setVisibility(View.INVISIBLE);

        button3=findViewById(R.id.button3);
        textView_zipCode_1=findViewById(R.id.textView_zipCode_1);
        textView_zipCode_2=findViewById(R.id.textView_zipCode_2);
        textView_zipCode_2.setVisibility(View.INVISIBLE);


        //************* action

        //button1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_dog_1.setVisibility(View.INVISIBLE);
                textView_dog_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity3_home.this, MainActivity4_Dog_API.class);
                        startActivity(intent);
                        finish();
                    }
                }, 100);
            }
        });

        //button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_jokes_1.setVisibility(View.INVISIBLE);
                textView_jokes_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity3_home.this, MainActivity5_jokes_API.class);
                        startActivity(intent);
                        finish();
                    }
                }, 100);
            }
        });

        //button3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_zipCode_1.setVisibility(View.INVISIBLE);
                textView_zipCode_2.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity3_home.this, MainActivity6_zipCode_API.class);
                        startActivity(intent);
                        finish();
                    }
                }, 100);
            }
        });

    }
}
