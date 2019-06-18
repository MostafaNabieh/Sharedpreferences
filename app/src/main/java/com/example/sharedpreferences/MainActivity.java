package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private TextView mShowCount;
    private int mColor;
    private SharedPreferences sharedPreferences;
    private String sharedPrefFile =
            "com.example.sharedpreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.count_textview);
        mColor= ContextCompat.getColor(this,R.color.default_background);


        sharedPreferences=getSharedPreferences(sharedPrefFile,MODE_PRIVATE);
        mCount = sharedPreferences.getInt("count", 0);
        mShowCount.setText(String.format("%s", mCount));
        mColor = sharedPreferences.getInt("color", mColor);
        mShowCount.setBackgroundColor(mColor);

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor=sharedPreferences.edit();
        preferencesEditor.putInt("count", mCount);
        preferencesEditor.putInt("color", mColor);
        preferencesEditor.apply();
    }

    public void changeBackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCount.setBackgroundColor(color);
        mColor = color;
    }

    public void countUp(View view) {
        mCount++;
        mShowCount.setText(String.format("%s", mCount));
    }

    public void reset(View view) {
        mCount = 0;
        mShowCount.setText(String.format("%s", mCount));

        // Reset color
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCount.setBackgroundColor(mColor);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}
