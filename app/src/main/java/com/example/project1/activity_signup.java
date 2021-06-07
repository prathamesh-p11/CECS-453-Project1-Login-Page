package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
                addValidationToView();
                if (username.length() == 0)
                    username.setError("Username is required");
                else if(!userdata.CheckUsername(username.getText().toString()))
                {
                    username.setError("This username is already taken");
                }
                else if(awesomeValidation.validate())
                {
                    Intent i = getIntent();
                    userdata = (Data) i.getSerializableExtra("Data");
                    userdata.AddCredential(username.getText().toString(), password.getText().toString());
                    //userdata.ShowAll();

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
}