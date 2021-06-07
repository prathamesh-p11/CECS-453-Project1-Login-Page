package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class activity_signup extends AppCompatActivity {

    private EditText txt_signUsername;
    private EditText txt_signPassword;
    private Button singButton;
    private AwesomeValidation awesomeValidation;
    private Data userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_signUsername = findViewById(R.id.txt_signUsername);
        txt_signPassword = findViewById(R.id.txt_signPassword);

        singButton = findViewById(R.id.btn_signup2);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Retrieve the serialized userdata object sent by login activity and store in local variable
        Intent i = getIntent();
        userdata = (Data) i.getSerializableExtra("Data");

        //Perform validation after sign up button is clicked
        singButton.setOnClickListener(v -> {
            addValidationToView();

            //Check if username field is not empty
            if (txt_signUsername.length() == 0)
                txt_signUsername.setError("Username is required");
            //Check if username field is unique: Checkusername function returns true is the username already exists in data
            else if(userdata.CheckUsername(txt_signUsername.getText().toString()))
            {
                txt_signUsername.setError("This username is already taken");
            }
            else if(awesomeValidation.validate())
            {
                //If validation is successful, add user credentials to userdata object
                userdata.AddCredential(txt_signUsername.getText().toString(), txt_signPassword.getText().toString());

                //Send the newly updated userdata object back to the login activity
                Intent login = new Intent(getApplicationContext(), activity_login.class);
                login.putExtra("Data", userdata);
                startActivity(login);

            }
        });
    }

    private void addValidationToView()
    {
        //Regex from: https://stackoverflow.com/a/58771373
        awesomeValidation.addValidation(this, R.id.txt_username,"^(?=.{2,20}$)(?:[a-zA-Z\\d]+(?:(?:\\.|-|_)[a-zA-Z\\d])*)+$", R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.txt_signEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        String regexPassword = ".{8,}";
        awesomeValidation.addValidation(this, R.id.txt_signPassword, regexPassword, R.string.invalid_password);
        awesomeValidation.addValidation(this, R.id.txt_signPassword2, R.id.txt_signPassword, R.string.invalid_confirm_password);
        awesomeValidation.addValidation(this, R.id.txt_signPhone, "^[+]?[0-9]{10,13}$", R.string.invalid_phone);
    }
}