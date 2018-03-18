package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        showAppVersion(R.id.app_version_textview);

    }

    /**
     * gets the version of the app from Gradle and shows it in a textView
     * @param TextViewID the textView where you want to display the app Version
     */
    public void showAppVersion(int TextViewID) {
        TextView appVersionTextview = findViewById(TextViewID);
        appVersionTextview.setText(getText(R.string.app_name) + " " + BuildConfig.VERSION_NAME);
    }

    /**
     * Called when the user taps the Review button
     */
    public void openSafetyRules(View view) {
        Intent intent = new Intent(this, safetyRules.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Take Quiz button
     */
    public void openQuizActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
