package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class activity_signup extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText email;
    private EditText phone;
    private Button singButton;
    private AwesomeValidation awesomeValidation;
    private Data userdata;

    // boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = findViewById(R.id.txt_signUsername);
        password = findViewById(R.id.txt_signPassword);
        password2= findViewById(R.id.txt_signPassword2);
        email=findViewById(R.id.txt_signEmail);
        phone= findViewById(R.id.txt_signPhone);
        singButton = findViewById(R.id.btn_signup2);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        singButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean isValidUserName = true;
                addValidationToView();
                if (username.length() == 0)
                {
                    isValidUserName = false;
                    username.setError("Username is required");
                }
                else if(awesomeValidation.validate() && isValidUserName)
                {
                    Intent i = getIntent();
                    userdata = (Data) i.getSerializableExtra("Data");

                    userdata.AddCredential(username.getText().toString(), password.getText().toString());
                    userdata.ShowAll();
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    login.putExtra("Data", userdata);
                    startActivity(login);
                }
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

   /* private boolean CheckAllFields() {
        if (username.length() == 0) {
            username.setError("This field is required");
            return false;
        }
        if (password.length() == 0) {
            password.setError("This field is required");
            return false;
        } else if (password.length() < 8) {
            password.setError("Password must be minimum 8 characters");
            return false;
        }

        if (password2.length() == 0) {
            password2.setError("This field is required");
            return false;
        } else if (password2.length() < 8) {
            password2.setError("Password must be minimum 8 characters");
            return false;
        }

        if (!password2.toString().equals(password.toString())){
            password2.setError("password does not match with first enter password.");
            return false;
        }

        if (email.length() == 0) {
            email.setError("Email is required");
            return false;
        }

        if(phone.length()==0){
            phone.setError("Phone number is required");
            return false;
        }
        return true;
    }*/
}