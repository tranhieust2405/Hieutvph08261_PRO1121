package com.example.hieu.todoapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hieu.todoapp.repositories.SignUpDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbTodoapp";
    public static final int VESION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SignUpDao.SQL_SIGN_UP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + SignUpDao.TABLE_NAME);
        ;
        onCreate(db);

    }
}
