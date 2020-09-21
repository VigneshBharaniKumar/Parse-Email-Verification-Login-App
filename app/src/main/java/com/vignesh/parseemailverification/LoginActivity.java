package com.vignesh.parseemailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUserName, edtLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        edtLoginUserName = findViewById(R.id.edtLoginUserName);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

    }

    public void login(View view) {

        // Login with Parse
        ParseUser.logInInBackground(edtLoginUserName.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    if(parseUser.getBoolean("emailVerified")) {
                        displayAlert("Login Sucessful", "Welcome, " + edtLoginUserName.getText().toString() + "!", false);
                    }
                    else
                    {
                        ParseUser.logOut();
                        displayAlert("Login Fail", "Please Verify Your Email first", true);
                    }
                } else {
                    ParseUser.logOut();
                    displayAlert("Login Fail", e.getMessage() + " Please re-try", true);
                }
            }
        });

    }

    private void displayAlert(String title, String message, boolean error) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        builder.create().show();

    }

    public void switchToSignUp(View view) {

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);

    }
}
