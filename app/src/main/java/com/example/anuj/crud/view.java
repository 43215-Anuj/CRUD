package com.example.anuj.crud;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class view extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    Context context = this;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        e1 = (EditText) findViewById(R.id.E1);

        b1 = (Button) findViewById(R.id.Btn1);

        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.d("Reading: ", "Reading student details..");

                String st= e1.getText().toString();
                Cursor cursor=loginDataBaseAdapter.getData(st);

                cursor.moveToNext();
                Toast.makeText(getApplicationContext(),
                        cursor.getString(cursor.getColumnIndex("ID"))+" "+
                                cursor.getString(cursor.getColumnIndex("STUDENT_NAME"))+" "
                                +cursor.getString(cursor.getColumnIndex("ROLL_NO"))+" "
                                +cursor.getString(cursor.getColumnIndex("COURSE")), Toast.LENGTH_LONG).show();
            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}