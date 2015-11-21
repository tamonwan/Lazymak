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
import com.parse.RequestPasswordResetCallback;

public class Changepassword extends AppCompatActivity {
    Button resetbutton;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resetbutton = (Button) findViewById(R.id.resetbutton);
        email = (EditText) findViewById(R.id.email);

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.requestPasswordResetInBackground(email.getText().toString(), new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "The code had been sent to " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Changepassword.this, MainActivity.class));

                            // An email was successfully sent with reset instructions.
                        } else {
                            Toast.makeText(getApplicationContext(), "error : please check the email address again", Toast.LENGTH_SHORT).show();
                            // Something went wrong. Look at the ParseException to see what's up.
                        }
                    }
                });
            }
        });



    }
}
