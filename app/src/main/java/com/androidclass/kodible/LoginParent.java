package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class LoginParent extends AppCompatActivity {
    EditText emaiET,passwordET;
    String email,passw;
    Button buttonLogin;
    AwesomeValidation awesomeValidation;
    DatabaseHelper database;
    Spinner accountTypeSpinner;
    TextView registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_parent);
        awesomeValidation = new AwesomeValidation(BASIC);

        emaiET= findViewById(R.id.loginEmailET);
        passwordET=findViewById(R.id.loginPasswordET);
        buttonLogin=findViewById(R.id.loginButtonB);
        registerTV=findViewById(R.id.loginRegister);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginParent.this,RegistrarParent.class);
                startActivity(intent);
            }
        });

        database=new DatabaseHelper(LoginParent.this);
        accountTypeSpinner=findViewById(R.id.accountType_spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(LoginParent.this,R.array.accountType, R.layout.support_simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(adapter);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{1,}";
        awesomeValidation.addValidation(LoginParent.this, R.id.loginEmailET, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(LoginParent.this, R.id.loginPasswordET,regexPassword,R.string.err_pass);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = accountTypeSpinner.getSelectedItem().toString();
                if(item.equals("Child")) {
                    if (TextUtils.isEmpty(emaiET.getText().toString()) || TextUtils.isEmpty(passwordET.getText().toString())) {
                        Toast.makeText(LoginParent.this, "fields cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        email = emaiET.getText().toString().trim();
                        passw = passwordET.getText().toString().trim();
                        Boolean lookforUserInDatabase = database.checkUser(email, passw);
                        if (awesomeValidation.validate() && (lookforUserInDatabase == true)) {
                            //get user input to strings

                            Intent registerRegIntent = new Intent(LoginParent.this, Dashboard.class);
                            startActivity(registerRegIntent);
                        } else {
                            //if email incorrect display invalid
                            Toast.makeText(getApplicationContext(), "invalid inputs", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(item.equals("Parent")){
                    if (TextUtils.isEmpty(emaiET.getText().toString()) || TextUtils.isEmpty(passwordET.getText().toString())) {
                        Toast.makeText(LoginParent.this, "fields cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        email = emaiET.getText().toString().trim();
                        passw = passwordET.getText().toString().trim();
                        Boolean lookforUserInDatabase = database.checkUser(email, passw);
                        if (awesomeValidation.validate() && (lookforUserInDatabase == true)) {
                            //get user input to strings

                            Intent registerRegIntent = new Intent(LoginParent.this, ChildProgress.class);
                            startActivity(registerRegIntent);
                        } else {
                            //if email incorrect display invalid
                            Toast.makeText(getApplicationContext(), "invalid inputs", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}