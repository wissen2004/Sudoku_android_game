package com.example.sudok;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SpleshActivity extends AppCompatActivity {
    LottieAnimationView lotty_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);

        lotty_s =findViewById(R.id.lotty_s);
        lotty_s.setAnimation(R.raw.animation_t);
        lotty_s.animate().alpha(1f).setDuration(4200).withEndAction(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                Intent intent = new Intent(SpleshActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}