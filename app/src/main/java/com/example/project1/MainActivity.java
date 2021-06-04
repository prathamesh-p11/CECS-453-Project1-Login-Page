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

                boolean isValidUsername = userdata.CheckUsername(username);

                if(!isValidUsername)
                {
                    //notify user and prompt to signup
                }
                else
                {
                    boolean isCorrectPassword = userdata.CheckCredentials(username, password);
                    if(!isCorrectPassword)
                    {
                        //notify user: incorrect password
                    }
                    else
                    {
                        //login successful!
                        //go to welcome
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