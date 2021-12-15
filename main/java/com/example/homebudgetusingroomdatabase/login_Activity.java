package com.example.homebudgetusingroomdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_Activity extends AppCompatActivity {

    EditText name,password;
    Button login,signup;
    database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.namelog);
        password = findViewById(R.id.passlog);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        db = new database(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = name.getText().toString();
                String passval = name.getText().toString();

                if(value.equals("") || passval.equals("")){
                    Toast.makeText(login_Activity.this, "Cant accept empty value", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = db.getinfo();
                if(c.getCount() == 0){
                    Toast.makeText(login_Activity.this, "No data in database", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    while(c.moveToNext()) {
                        if(c.getString(1).equals(value) && c.getString(2).equals(passval)){
                            Intent i = new Intent(login_Activity.this,MainActivity.class);//give access
                            startActivity(i);
                            return;
                        }
                    }
                    Toast.makeText(login_Activity.this, "No user found with this name \n Please signup first", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = name.getText().toString();
                String passval = name.getText().toString();

                if(value.equals("") || passval.equals("")){
                    Toast.makeText(login_Activity.this, "Cant accept empty value", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor c = db.getinfo();
                if(c.getCount() == 0){
                    boolean flag = db.insert_data(value,passval);
                    if(flag){
                        Toast.makeText(login_Activity.this, "User Created successfully.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(login_Activity.this, "Error occured.", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }else{
                    while(c.moveToNext()) {
                        if(c.getString(1).equals(value) && c.getString(2).equals(passval)){
                            Toast.makeText(login_Activity.this, "User already exists please login.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    boolean flag = db.insert_data(value,passval);
                    if(flag){
                        Toast.makeText(login_Activity.this, "User Created successfully.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(login_Activity.this, "Error occured.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}