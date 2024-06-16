package com.example.sudok.score;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.sudok.MainActivity;
import com.example.sudok.R;
import com.example.sudok.gameLogic.Game;
import java.util.Random;

public class ScoreActivity extends AppCompatActivity {

    private TextView scoreTextView;
    private TextView iqTextView;
    private Button playAgainButton;
    private Button mainMenuButton;
    private Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTextView = findViewById(R.id.scoreTextView);
        iqTextView = findViewById(R.id.iqTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        mainMenuButton = findViewById(R.id.mainMenuButton);
        random = new Random();

        Intent intent = getIntent();
        boolean isComplete = intent.getBooleanExtra("isComplete", false);

        int finalScore;
        int finalIQ;
        if (isComplete) {
            finalScore = 500;
            finalIQ = 200;
        } else {
            finalScore = 160 + random.nextInt(101); // от 100 до 500
            finalIQ = finalScore / 2; // IQ равен 50% от очков
        }

        animateText(scoreTextView, finalScore, () -> animateText(iqTextView, finalIQ, null));

        LottieAnimationView topAnimationView = findViewById(R.id.backgroundAnimationView);
        topAnimationView.setAnimation(R.raw.first);
        topAnimationView.loop(true);
        topAnimationView.playAnimation();

        playAgainButton.setOnClickListener(v -> {
            Intent playAgainIntent = new Intent(ScoreActivity.this, Game.class);
            startActivity(playAgainIntent);
            finish();
        });

        mainMenuButton.setOnClickListener(v -> {
            Intent mainMenuIntent = new Intent(ScoreActivity.this, MainActivity.class);
            startActivity(mainMenuIntent);
            finish();
        });
    }


    private void animateText(TextView textView, int finalValue, Runnable onAnimationEnd) {
        final int[] currentValue = {0};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentValue[0] < finalValue) {
                    currentValue[0]++;
                    textView.setText(textView.getId() == R.id.scoreTextView ? "Очки: " + currentValue[0] : "Ваш IQ: " + currentValue[0]);
                    handler.postDelayed(this, 10);
                } else {
                    if (onAnimationEnd != null) {
                        onAnimationEnd.run();
                    }
                }
            }
        };
        handler.post(runnable);
    }
}

