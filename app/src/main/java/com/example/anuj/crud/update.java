package com.example.anuj.crud;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    Button b1;
    Context context = this;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        e1 = (EditText) findViewById(R.id.E1);
        e2 = (EditText) findViewById(R.id.E2);
        e3 = (EditText) findViewById(R.id.E3);
        e4 = (EditText) findViewById(R.id.E4);

        b1 = (Button) findViewById(R.id.Btn1);
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String StudentId = e1.getText().toString();
                String StudentName = e2.getText().toString();
                String StudentRoll = e3.getText().toString();
                String StudentCourse = e4.getText().toString();
                if (StudentId.equals("") || StudentName.equals("") || StudentRoll.equals("") || StudentCourse.equals("")) {

                    Toast.makeText(getApplicationContext(), "All Fields are Necessary", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    loginDataBaseAdapter.updateEntry(StudentId, StudentName, StudentRoll, StudentCourse);
                    Toast.makeText(getApplicationContext(), "Details Successfully Updated ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(update.this, MainActivity.class);
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