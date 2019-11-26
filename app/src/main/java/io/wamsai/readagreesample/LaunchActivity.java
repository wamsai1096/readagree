package io.wamsai.readagreesample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import io.wamsai.readagree.ReadAgree;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        boolean isNeed = ReadAgree.showReadAgreeDialogIfNeed(this, isAgree -> {
            if (isAgree) {
                finish();
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                // ReadAgree  will exit app
            }

            return false; // not handle
        });

        if (!isNeed) {
            new Handler().postDelayed(() -> {
                finish();
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
            }, 3000);
        }
    }
}
