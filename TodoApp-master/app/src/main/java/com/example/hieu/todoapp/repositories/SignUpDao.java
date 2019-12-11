package com.example.hieu.todoapp.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hieu.todoapp.helpers.DatabaseHelper;
import com.example.hieu.todoapp.models.SignUp;

import java.util.ArrayList;
import java.util.List;


public class SignUpDao {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME = "SignUp";
    public static final String SQL_SIGN_UP = "CREATE TABLE SignUp(username " +
            "text primary key, password text);";
    public static final String TAG = "SignUpDao";

    public SignUpDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public SignUpDao(DatabaseHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    //insert
    public int insertNguoiDung(SignUp nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());

        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getall
    public List<SignUp> getAllNguoiDung() {
        List<SignUp> dsNguoiDung = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            SignUp ee = new SignUp();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            dsNguoiDung.add(ee);
            Log.d("//======", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    //check login

    public SignUp getUser(String username) {

        SignUp user = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truyen vao Ten bang, array bao gom ten cot, ten cot khoa chinh, gia tri khoa chinh, cac tham so con lai la null

        Cursor cursor = db.query(TABLE_NAME, new String[]{"username", "password"}, "username=?", new String[]{username}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex("username"));

            String password = cursor.getString(cursor.getColumnIndex("password"));

            // khoi tao user voi cac gia tri lay duoc
            user = new SignUp(user_name, password);


        }
        cursor.close();

        return user;
    }

}
