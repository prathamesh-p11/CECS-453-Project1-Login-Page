package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class activity_welcome extends AppCompatActivity
{
    private TextView txt_loggedInUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txt_loggedInUsername = findViewById(R.id.txt_WelcomeMsg);

        //Retrieve the name of user currently logged in from login activity sent as string by intent
        Intent i = getIntent();
        String username = i.getStringExtra("username");
        String welcomeMessage = getString(R.string.welcome_message, username);      //using string resource file to avoid string concatination warning in setText
        txt_loggedInUsername.setText(welcomeMessage);
    }
}