package com.example.calculator_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {
    settings context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.calculator_2.ThemeManager.setTheme(this);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        {
            Button item = findViewById(R.id.button);
            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    com.example.calculator_2.ThemeManager.changeTo(context, com.example.calculator_2.ThemeManager.THEME_1);
                }
            };
            item.setOnClickListener(buttonClickListener);
        }

        {
            Button item = findViewById(R.id.button2);
            View.OnClickListener buttonClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    com.example.calculator_2.ThemeManager.changeTo(context, com.example.calculator_2.ThemeManager.THEME_1);
                }
            };
            item.setOnClickListener(buttonClickListener);
        }

    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}