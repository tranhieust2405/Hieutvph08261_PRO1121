package com.example.hieu.todoapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hieu.todoapp.R;
import com.example.hieu.todoapp.models.SignUp;
import com.example.hieu.todoapp.repositories.SignUpDao;

public class SignupActivity extends AppCompatActivity {

    private EditText edUserName;
    private EditText edtPassword;
    private Button btnSignUp;
    SignUpDao signUpDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void initView() {

        edUserName = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
    }

    public void addUser() {
        signUpDao = new SignUpDao(SignupActivity.this);
        SignUp user = new SignUp(edUserName.getText().toString(), edtPassword.getText().toString());
        try {
            if (validateForm() > 0) {
                if (signUpDao.insertNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(SignupActivity.this, LoginActivity.class);
                    startActivity(b);

                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.toString());

        }


    }

    public int validateForm() {
        int check = 1;
        if (edUserName.getText().length() == 0 || edtPassword.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPassword.getText().toString();
            if (pass.length() < 6) {
                edtPassword.setError(getString(R.string.notify_length_pass));
                check = -1;
            }

        }
        return check;
    }
}
