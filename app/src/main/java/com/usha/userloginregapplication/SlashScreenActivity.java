package com.usha.userloginregapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SlashScreenActivity extends AppCompatActivity {
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash_screen);
        db = new DatabaseHelper(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(db.isTableExists("user",true)) {
                    if(db.getAllUsers().size() > 0){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                    }


                }else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                }


                finish();

            }
        }, 3000);

    }
}