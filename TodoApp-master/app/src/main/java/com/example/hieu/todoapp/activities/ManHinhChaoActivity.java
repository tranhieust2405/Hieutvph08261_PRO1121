package com.example.hieu.todoapp.activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.hieu.todoapp.R;

public class ManHinhChaoActivity extends AppCompatActivity {
    private ImageView imghello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);

        CountDownTimer countDownTimer = new CountDownTimer(1500, 1500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        }.start();

    }
}
