package com.vignesh.parseemailverification;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText edtEmail, edtUserName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SignUp");

        ParseInstallation.getCurrentInstallation().saveInBackground();

        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

    }

    public void signUp(View view) {

        try {

            // Sign up with Parse
            ParseUser user = new ParseUser();
            user.setUsername(edtUserName.getText().toString());
            user.setPassword(edtPassword.getText().toString());
            user.setEmail(edtEmail.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        ParseUser.logOut();
                        //Toast.makeText(MainActivity.this, "Account Created Successfully!, Please verify your email before Login", Toast.LENGTH_LONG).show();
                        displayAlert("Account Created Successfully!", "Please verify your email before Login", false);
                    } else {
                        ParseUser.logOut();
                        //Toast.makeText(MainActivity.this, "Error Account Creation failed, Account could not be created", Toast.LENGTH_LONG).show();
                        displayAlert("Error Account Creation failed", "Account could not be created" + " :" + e.getMessage(), true);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayAlert(String title, String message, final boolean error) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (!error) {

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

            }
        });
        builder.create().show();

    }


    public void switchToLogin(View view) {

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);

    }
}
