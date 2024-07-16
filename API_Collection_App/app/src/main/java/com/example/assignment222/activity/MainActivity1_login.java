package com.example.assignment222.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment222.R;
import com.example.assignment222.classes.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity1_login extends AppCompatActivity {
    private EditText editTextUsername;
    private ImageView error_image1;
    private EditText editTextPassword;
    private ImageView error_image2;
    private TextView error_message;
    private CheckBox checkBox;
    private Button buttonLogin;
    private Button buttonSignup;

    private ArrayList<User> al_user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_login);

        // Initialize views
        editTextUsername=findViewById(R.id.editTextUsername);
        error_image1=findViewById(R.id.error_image1);
        error_image1.setVisibility(View.INVISIBLE);

        editTextPassword=findViewById(R.id.editTextPassword);
        error_image2=findViewById(R.id.error_image2);
        error_image2.setVisibility(View.INVISIBLE);

        error_message=findViewById(R.id.error_message);

        checkBox=findViewById(R.id.checkBox);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonSignup=findViewById(R.id.buttonSignup);

        // retrieve data
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String userString = prefs.getString("DATA", "");

        al_user=new ArrayList<>();

        if (!userString.isEmpty()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            al_user = gson.fromJson(userString, type);
        }



        // Set saved data if remember checkbox is checked
        String PREF_NAME = "MyPrefs";
        String KEY_USERNAME = "username";
        String KEY_PASSWORD = "password";
        String KEY_REMEMBER = "remember";

        SharedPreferences prefs_remember_me = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if (prefs_remember_me.getBoolean(KEY_REMEMBER, false)) {
            editTextUsername.setText(prefs_remember_me.getString(KEY_USERNAME, ""));
            editTextPassword.setText(prefs_remember_me.getString(KEY_PASSWORD, ""));
            checkBox.setChecked(true);
        }

        //************* action

        // Signup button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity1_login.this, MainActivity2_signUp.class);
                startActivity(intent);
            }
        });

        // Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = editTextUsername.getText().toString();
                String user_password = editTextPassword.getText().toString();

                // if the fields not fill
                if(user_name.isEmpty() || user_password.isEmpty()){
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
                }
                //
                else{
                    boolean user_exist=false;
                    boolean correct_password=false;

                    for(int i=0;i<al_user.size();i++){
                        if(al_user.get(i).getName().equals(user_name) && al_user.get(i).getPassword().equals(user_password)){
                            user_exist=true;
                            correct_password=true;
                            break;
                        }

                    }

                    // if Username or Password uncorrected
                    if(user_exist==false || correct_password==false){
                        error_message.setText("Username or Password uncorrected");

                        error_image1.setVisibility(View.VISIBLE);
                        error_image2.setVisibility(View.VISIBLE);
                    }
                    else{
                        error_message.setText("");

                        error_image1.setVisibility(View.INVISIBLE);
                        error_image2.setVisibility(View.INVISIBLE);

                        // Save data if remember checkbox is checked
                        if (checkBox.isChecked()) {
                            SharedPreferences.Editor editor = prefs_remember_me.edit();
                            editor.putString(KEY_USERNAME, user_name);
                            editor.putString(KEY_PASSWORD, user_password);
                            editor.putBoolean(KEY_REMEMBER, true);
                            editor.apply();
                        } else {
                            SharedPreferences.Editor editor = prefs_remember_me.edit();
                            editor.clear();
                            editor.apply();
                        }

                        Intent intent = new Intent(MainActivity1_login.this, MainActivity3_home.class);
                        startActivity(intent);
                    }
                }

            }
        });


    }


}