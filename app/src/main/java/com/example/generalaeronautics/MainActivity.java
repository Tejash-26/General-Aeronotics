package com.example.generalaeronautics;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText emailid;
    EditText password;
    String user="tejutejash26@gmail.com";
    String pass="1";
    String name;
    String word;
    ProgressDialog dialog;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    private void alert(String message){
        AlertDialog dig =new AlertDialog.Builder(MainActivity.this)
                .setTitle("message")
                .setMessage(message)
                .setPositiveButton("yea  boiii", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();;
                    }
                })
                .create();
        dig.show();
    }




    public void submit(View view){
        emailid = (EditText) findViewById(R.id.editTextTextEmailAddress);
        password = (EditText) findViewById(R.id.editTextTextPassword2);
        name =(String)emailid.getText().toString();
        word = (String)password.getText().toString();
        if(!validateEmail()) {
            return;
        }
        if(name.equals(user) && word.equals(pass)){
            emailid.setText("");
            password.setText("");
            dialog=new ProgressDialog(this);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait");
            dialog.show();
            Intent intent = new Intent(MainActivity.this,home_page.class);
            startActivity(intent);


        }
        else{
            Toast.makeText(getBaseContext(),"wrong password or Email id",Toast.LENGTH_LONG).show();
        }

    }
    private boolean validateEmail() {
        EditText temp=(EditText) findViewById(R.id.editTextTextEmailAddress);
        String emailInput =(String)emailid.getText().toString();

        if (emailInput.isEmpty()) {
            emailid.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailid.setError("Please enter a valid email address");
            return false;
        } else {
            emailid.setError(null);
            return true;
        }
    }
}