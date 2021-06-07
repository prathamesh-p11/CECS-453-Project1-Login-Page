package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_welcome extends AppCompatActivity
{
    private TextView loggedInUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        loggedInUsername = findViewById(R.id.txt_WelcomeMsg);
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        loggedInUsername.setText("Welcome "+username);
    }
}