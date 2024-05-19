package com.example.sudok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.example.sudok.aboutpage.About;
import com.example.sudok.gameLogic.Game;
import com.example.sudok.settings.Prefs;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(this);
        Button newButton = findViewById(R.id.new_button);
        newButton.setOnClickListener(this);
        Button aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


        public void onClick(View v) {
            if (v.getId() == R.id.about_button) {
                Intent i = new Intent(this, About.class);
                startActivity(i);
            } else if (v.getId() == R.id.new_button) {
                openNewGameDialog();
            } else if (v.getId() == R.id.exit_button) {
                finish();
            } else if (v.getId() == R.id.continue_button) {
                startGame(Game.DIFFICULTY_CONTINUE);
            }
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            startActivity(new Intent(this, Prefs.class));
            return true;
        } else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey! I'm enjoying " + getString(R.string.app_name) + " - it's a blast! 😄\n" +
                            "Join me in the fun! Install " + getString(R.string.app_name) + " now!\n" +
                            "https://play.google.com/store/apps/details?id=" + getPackageName());
            sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                    "Check out " + getString(R.string.app_name) + " - it's awesome!");
            sendIntent.setType("text/plain");


            startActivity(Intent.createChooser(sendIntent,
                    getString(R.string.text_share_app)));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private static final String TAG = "Sudoku";

    private void openNewGameDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.new_game_title)
                .setItems(R.array.difficulty,
                        new DialogInterface.OnClickListener()
                        {

                            public void onClick(DialogInterface dialoginterface, int i) {startGame(i);
                            }
                        })
                .show();
    }

    private void startGame(int i) {
        Log.d(TAG, "clicked on " + i);


        Log.d(TAG, "clicked on " + i);
        Intent intent = new Intent(MainActivity.this, Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY, i);
        startActivity(intent);
    }




}

