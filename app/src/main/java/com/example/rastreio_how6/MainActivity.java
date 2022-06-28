package com.example.rastreio_how6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rastreio_how6.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.login_main, new LoginFragment()).commit();
        }
    }
}