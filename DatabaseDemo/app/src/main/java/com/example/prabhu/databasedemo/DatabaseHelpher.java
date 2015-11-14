package com.example.prabhu.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PRABHU on 11/12/2015.
 */
public class DatabaseHelpher extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="student";
    private static final int DATABASE_VERSION = 1;
    private static final String STUDENT_TABLE = "stureg";
    private static final String STU_TABLE = "create table "+STUDENT_TABLE +"(name TEXT,email TEXT primary key,roll TEXT,address TEXT,branch TEXT)";

Context context;

    public DatabaseHelpher(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(STU_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);

        // Create tables again
        onCreate(db);
    }
/* Insert into database*/
    public void insertIntoDB(String name,String email,String roll,String address,String branch){
        Log.d("insert", "before insert");

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("roll", roll);
         values.put("address", address);
        values.put("branch", branch);

        // 3. insert
        db.insert(STUDENT_TABLE, null, values);
        // 4. close
        db.close();
        Toast.makeText(context, "insert value", Toast.LENGTH_LONG);
        Log.i("insert into DB", "After insert");
    }
/* Retrive  data from database */
    public List<DatabaseModel> getDataFromDB(){
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        String query = "select * from "+STUDENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                DatabaseModel model = new DatabaseModel();
                model.setName(cursor.getString(0));
                model.setEmail(cursor.getString(1));
                model.setRoll(cursor.getString(2));
                model.setAddress(cursor.getString(3));
                model.setBranch(cursor.getString(4));

                modelList.add(model);
            }while (cursor.moveToNext());
        }


        Log.d("student data", modelList.toString());


        return modelList;
    }


    /*delete a row from database*/

    public void deleteARow(String email){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(STUDENT_TABLE, "email" + " = ?", new String[] { email });
        db.close();
    }

}
