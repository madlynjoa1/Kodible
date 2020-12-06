package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class RegistrarParent extends AppCompatActivity {
    EditText userNameRegister;
    EditText emailRegister;
    EditText passwordRegister;
    EditText confirmPassRegister;
    Button registerButton;
    AwesomeValidation awesomeValidation;
    DatabaseHelper database;
    TextView loginTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_parent);
        //validatiom
        awesomeValidation = new AwesomeValidation(BASIC);
        //database
        database= new DatabaseHelper((this));

        emailRegister=findViewById(R.id.regEmail);
        passwordRegister=findViewById(R.id.regpassword);
        confirmPassRegister=findViewById(R.id.regComPassword);
        registerButton=findViewById(R.id.registerbutton);
        loginTV=findViewById(R.id.login);
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrarParent.this,LoginParent.class);
                startActivity(intent);
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(userNameRegister.getText().toString()) || TextUtils.isEmpty(emailRegister.getText().toString()) ||
                        TextUtils.isEmpty(passwordRegister.getText().toString()) || TextUtils.isEmpty(confirmPassRegister.getText().toString())){
                    Toast.makeText(RegistrarParent.this, "fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else if(awesomeValidation.validate()) {
                    //get user input to strings
                    String userNameValidate=userNameRegister.getText().toString();
                    String emailValidate= emailRegister.getText().toString();
                    String passwordValidate=passwordRegister.getText().toString();
                    String confirmPasswordValidate=confirmPassRegister.getText().toString();

                    if(passwordRegister.getText().toString().equals(confirmPassRegister.getText().toString())) {

                        Toast.makeText(getApplicationContext(), "Created", Toast.LENGTH_LONG).show();
                        long val=database.addUser(emailRegister.getText().toString(),passwordRegister.getText().toString());
                        Intent registerRegIntent = new Intent(RegistrarParent.this, MainActivity.class);
                        startActivity(registerRegIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password don't match", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    //if email incorrect display invalis
                    Toast.makeText(getApplicationContext(), "invalid inputs", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}