/*
    Assignment1
    Written by:
        Arjang Fahim 098765432
        Arjang Fahim 123455678


 */

package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
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


        //Validate username and password here
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                if(username.length() == 0)
                {
                    Toast.makeText(getApplicationContext(),"Username field cannot be empty", Toast.LENGTH_LONG).show();
                }
                else if(password.length() == 0)
                {
                    Toast.makeText(getApplicationContext(),"Password field cannot be empty", Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean isValidUsername = userdata.CheckUsername(username);

                    if(!isValidUsername)
                    {
                        //notify user and prompt to signup
                        Toast.makeText(getApplicationContext(),"Entered username is not valid, please signup to continue", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        boolean isCorrectPassword = userdata.CheckCredentials(username, password);
                        if(!isCorrectPassword)
                        {
                            //notify user: incorrect password
                            Toast.makeText(getApplicationContext(),"Login attempt failed! Incorrect Password", Toast.LENGTH_LONG).show();
                            //Clear password field
                            txt_password.setText("");
                        }
                        else
                        {
                            //login successful!
                            Toast.makeText(getApplicationContext(),"Login attempt successful!", Toast.LENGTH_LONG).show();
                            //go to welcome
                        }
                    }
                }
            }
        });

        //Lets user create a new account and store user info
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}