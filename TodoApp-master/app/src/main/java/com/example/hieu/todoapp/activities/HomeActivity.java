package com.example.hieu.todoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.hieu.todoapp.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void task(View view) {
        Intent intent = new Intent (HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.changepass:
//                Intent b = new Intent(HomeActivity.this, DoiMatKhauActivity.class);
//                startActivity(b);
//                break;
            case R.id.dangxuat:
                SharedPreferences preferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent c = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(c);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
