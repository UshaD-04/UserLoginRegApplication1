package com.usha.userloginregapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText username1;
    EditText password1;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 = findViewById(R.id.editTextTextEmailAddress);
        password1 = findViewById(R.id.editTextTextEmailAddress);
        db = new DatabaseHelper(this);


        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String username = username1.getText().toString().trim();
                    String password = password1.getText().toString().trim();
                    String dbusername = db.getAllUsers().get(0).getUsername();
                    String dbpassword = db.getAllUsers().get(0).getPassword();

                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(LoginActivity.this, "Enter your Email and Password to login", Toast.LENGTH_SHORT).show();
                    } else {
                        if (username.equals(dbusername) && password.equals(dbpassword)) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "User does not exist kindly register", Toast.LENGTH_SHORT).show();

                        }
                    }

                }catch (Exception ignore){}
                Toast.makeText(LoginActivity.this, "User does not exist kindly register", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
            }
        });

//        db.getUser();

    }
}