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

public class activity_login extends AppCompatActivity
{
    private Button btn_login;
    private Button btn_signup;
    private EditText txt_username;
    private EditText txt_password;
    private Data userdata = new Data();

    //Called when activity is started
    //Use: to initialize the activity
    //Input parameter: Bundle object (An object similar to map having string keys and Parcels(Containers for data) as values)
    //We can use this parameter to save the state of app in a bundle object and pass back to the activity so that it is automatically initialized with that state
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //Changes the look of an activity
        //Parameter: Layout in form of xml file
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);

        //Retrieve and store the new userdata Data object(if it exists) from signup
        //Data object is sent as serialized data(format required to transfer objects using intents) and we need to convert it back to object form
        if(getIntent().getSerializableExtra("Data")!=null)
        {
            userdata = (Data)getIntent().getSerializableExtra("Data");
            //userdata.ShowAll();
        }

        //Validate username and password here
        btn_login.setOnClickListener(v -> {
            String username = txt_username.getText().toString();
            String password = txt_password.getText().toString();

            //Check if username field is not empty
            if(username.length() == 0)
            {
                txt_username.setError("Username required");
            }
            //Check if password field is not empty
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
                        //Display toast to notify login has been successful!
                        Toast.makeText(getApplicationContext(),"Login attempt successful!", Toast.LENGTH_LONG).show();

                        //go to welcome activity
                        Intent welcome = new Intent(getApplicationContext(), activity_welcome.class);
                        welcome.putExtra("username", username);  //For this to work, Data class must implement serializable interface
                        startActivity(welcome);
                    }
                }
            }
        });

        //After signup button is clicked -> open sign up activity and pass stored credentials to it
        //Lets user create a new account and store user info
        btn_signup.setOnClickListener(v -> {
            Intent signup = new Intent(getApplicationContext(), activity_signup.class);
            signup.putExtra("Data", userdata);  //For this to work, Data class must implement serializable interface
            startActivity(signup);
        });
    }
}