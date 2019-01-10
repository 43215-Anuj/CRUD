package com.example.anuj.crud;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add extends AppCompatActivity {
        EditText e1,e2,e3;
        Button b1;
        Context context = this;
        LoginDataBaseAdapter loginDataBaseAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);
            loginDataBaseAdapter = new LoginDataBaseAdapter(this);
            loginDataBaseAdapter = loginDataBaseAdapter.open();
            e1 = findViewById(R.id.E1);
            e2 = findViewById(R.id.E2);
            e3 = findViewById(R.id.E3);

            b1 = findViewById(R.id.Btn1);
            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    String StudentName = e1.getText().toString();
                    String StudentRoll = e2.getText().toString();
                    String StudentCourse = e3.getText().toString();
                    if (StudentName.equals("") || StudentRoll.equals("") || StudentCourse.equals(""))
                    {

                        Toast.makeText(getApplicationContext(), "All Fields are Necessary", Toast.LENGTH_LONG).show();
                        return;
                    }

                    else
                    {

                        loginDataBaseAdapter.insertEntry(StudentName,StudentRoll,StudentCourse);
                        Toast.makeText(getApplicationContext(), "Details Successfully Added ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(add.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    }
                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            loginDataBaseAdapter.close();
        }
    }

