package com.example.anuj.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter  {

    static final String DATABASE_NAME = "add.db";
    static final int DATABASE_VERSION = 1;

    static final String ADD_STUDENTS = "CREATE TABLE  addition" + "( ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "STUDENT_NAME TEXT," + "ROLL_NO TEXT," + "COURSE TEXT); ";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open(){
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public void insertEntry(String StudentName, String RollNo, String Course) {
        db = dbHelper.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("STUDENT_NAME", StudentName);
        newValues.put("ROLL_NO ", RollNo);
        newValues.put("COURSE", Course);
        db.insert("addition", null, newValues);

    }

    public Cursor getData(String name) {
        db = dbHelper.getReadableDatabase();
        Cursor res = db.rawQuery("select * from addition where STUDENT_NAME==\'"+ name + "\'", null);
        return res;
    }

    public Cursor showalldata() {
        Cursor c = db.rawQuery("SELECT * FROM addition", null);
        if(c!=null){
            c.moveToFirst();
        }
        return c;
    }

    public int deleteEntry(String StudentName) {

        db = dbHelper.getWritableDatabase();
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = db.delete("addition", where, new String[]{StudentName});
        return numberOFEntriesDeleted;
    }

    public void updateEntry(String Id, String StudentName, String RollNo, String Course) {
        db = dbHelper.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("ID", Id);
        updatedValues.put("STUDENT_NAME", StudentName);
        updatedValues.put("ROLL_NO ", RollNo);
        updatedValues.put("COURSE", Course);


        String where = "STUDENT_NAME == ?";
        db.update("addition", updatedValues, where, new String[]{StudentName});
    }
}
