package com.example.messenger_rksi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthActivity extends AppCompatActivity {
    API api;

    EditText loginEditText;
    EditText passwordEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        api = new API(findViewById(android.R.id.content));

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        submitButton = findViewById(R.id.submitButton);
    }

    public void submit(View view) {
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        api.Auth(login, password);
    }
}