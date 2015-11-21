package com.mycompany.lazy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity {
    Button registerbutton;
    EditText username,password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerbutton = (Button) findViewById(R.id.registerbutton);

        username = (EditText) findViewById(R.id.registeredname);
        password = (EditText) findViewById(R.id.registeredpassword);
        email = (EditText) findViewById(R.id.email);

        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "successfully registered :" + username.getText().toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, MainActivity.class));

                        } else {

                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                        }
                    }
                });

            }
        });
    }

}
