package com.vignesh.parseemailverification;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Welcome Home!");

    }

    public void logOut(View view) {

        ParseUser.logOut();
        displayAlert("So, you're going...", "Ok...Bye-bye then", false);

    }

    private void displayAlert(String title, String message, boolean error) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        builder.create().show();

    }

}
