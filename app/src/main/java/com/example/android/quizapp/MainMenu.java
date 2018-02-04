package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /** Called when the user taps the Review button */
    public void openSafetyRules(View view) {
        Intent intent = new Intent(this, safetyRules.class);
        startActivity(intent);
    }

    /** Called when the user taps the Review button */
    public void openQuizActivity (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
