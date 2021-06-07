/*
    Assignment1
    Written by:
        Prathamesh Patil 025910428
        Devarsh Patel


 */

package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements  Serializable
{
    private Button btn_login;
    private Button btn_signup;
    private EditText txt_username;
    private EditText txt_password;
    private Data userdata = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        if(getIntent().getSerializableExtra("Data")!=null)
        {
            userdata = (Data)getIntent().getSerializableExtra("Data");
            //userdata.ShowAll();
        }

        //Validate username and password here
        btn_login.setOnClickListener(v -> {
            String username = txt_username.getText().toString();
            String password = txt_password.getText().toString();

            if(username.length() == 0)
            {
                txt_username.setError("Username required");
            }
            else if(password.length() == 0)
            {
                txt_password.setError("Password required");
            }
            else
            {
                boolean isValidUsername = userdata.CheckUsername(username);

                if(!isValidUsername)
                {
                    //notify user and prompt to signup
                    txt_username.setError("Username is not registered");
                }
                else
                {
                    boolean isCorrectPassword = userdata.CheckCredentials(username, password);
                    if(!isCorrectPassword)
                    {
                        //notify user: incorrect password
                        Toast.makeText(getApplicationContext(),"Incorrect Password! Please try again", Toast.LENGTH_LONG).show();
                        //Clear password field
                        txt_password.setText("");
                    }
                    else
                    {
                        //login successful!
                        Toast.makeText(getApplicationContext(),"Login attempt successful!", Toast.LENGTH_LONG).show();

                        //go to welcome
                        Intent welcome = new Intent(getApplicationContext(), activity_welcome.class);
                        welcome.putExtra("username", username);  //For this to work, Data class must implement serializable interface
                        startActivity(welcome);
                    }
                }
            }
        });

        //Lets user create a new account and store user info
        btn_signup.setOnClickListener(v -> {
            Intent signup = new Intent(getApplicationContext(), activity_signup.class);
            signup.putExtra("Data", userdata);  //For this to work, Data class must implement serializable interface
            startActivity(signup);
        });
    }
}