package com.example.assignment222.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment222.R;
import com.example.assignment222.classes.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity2_signUp extends AppCompatActivity {
    private EditText editTextUsername;
    private ImageView error_image1;
    private EditText editTextPassword;
    private ImageView error_image2;
    private EditText editTextConfirmPassword;
    private ImageView error_image3;
    private TextView error_message;
    private TextView correct_message;
    private ImageView correct_image;
    private Button signup_button;

    private ArrayList<User> al_user;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_signup);
        setupSharedPrefs();

        // Initialize views
        editTextUsername=findViewById(R.id.editTextUsername);

        error_image1=findViewById(R.id.error_image1);
        error_image1.setVisibility(View.INVISIBLE);

        editTextPassword=findViewById(R.id.editTextPassword);

        error_image2=findViewById(R.id.error_image2);
        error_image2.setVisibility(View.INVISIBLE);

        editTextConfirmPassword=findViewById(R.id.editTextConfirmPassword);

        error_image3=findViewById(R.id.error_image3);
        error_image3.setVisibility(View.INVISIBLE);

        error_message=findViewById(R.id.error_message);

        correct_message=findViewById(R.id.correct_message);

        correct_image=findViewById(R.id.correct_image);

        signup_button=findViewById(R.id.signup_button);

        al_user=new ArrayList<>();
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = editTextUsername.getText().toString();
                String user_password = editTextPassword.getText().toString();
                String user_confirmPassword = editTextConfirmPassword.getText().toString();


                // if the fields not fill
                if (user_name.isEmpty() || user_password.isEmpty() || user_confirmPassword.isEmpty()) {
                    error_message.setText("Please fill in all fields");
                    // if user name not fill
                    if(user_name.isEmpty()){
                        error_image1.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_image1.setVisibility(View.INVISIBLE);
                    }

                    // if password not fill
                    if(user_password.isEmpty()){
                        error_image2.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_image2.setVisibility(View.INVISIBLE);
                    }

                    // if confirm password not fill
                    if(user_confirmPassword.isEmpty()){
                        error_image3.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_image3.setVisibility(View.INVISIBLE);
                    }

                }
                // else if the password doesn't match
                else if (!user_password.equals(user_confirmPassword)) {
                    error_image2.setVisibility(View.VISIBLE);
                    error_image3.setVisibility(View.VISIBLE);
                    error_message.setText("Passwords doesn't match");
                }
                // else sign up
                else {
                    error_image2.setVisibility(View.INVISIBLE);
                    error_image3.setVisibility(View.INVISIBLE);

                    boolean user_exist=false;

                    String prevs_UserString = prefs.getString(DATA, "");
                    if (!prevs_UserString.isEmpty()) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<User>>() {}.getType();
                        al_user = gson.fromJson(prevs_UserString, type);

                        for(int i=0;i<al_user.size();i++){
                            if(al_user.get(i).getName().equals(user_name)){
                                user_exist=true;
                                break;
                            }
                        }
                    }

                    if(user_exist==true){
                        error_image1.setVisibility(View.VISIBLE);
                        error_message.setText("Username exists");
                    }
                    else{
                        error_image1.setVisibility(View.INVISIBLE);
                        error_message.setText("");

                        User user=null;
                        user=new User(user_name,user_confirmPassword);
                        al_user.add(user);

                        Gson gson = new Gson();
                        String userString = gson.toJson(al_user);

                        editor.putString(DATA, userString);
                        editor.apply();

                        correct_image.setVisibility(View.VISIBLE);
                        correct_message.setText("Account created successfully");

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity2_signUp.this,MainActivity1_login.class);
                                startActivity(intent);
                                finish();
                            }
                        }, 1000);
                    }

                }

            }
        });

    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}