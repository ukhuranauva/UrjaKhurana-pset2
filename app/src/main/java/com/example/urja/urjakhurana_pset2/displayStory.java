package com.example.urja.urjakhurana_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class displayStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);
        // gets user's filled in story
        Bundle extras = getIntent().getExtras();
        String createdStory = extras.getString("thestory");
        TextView showStory = (TextView) findViewById(R.id.showStory);
        // displays the user's story
        showStory.setText(createdStory);
    }

    public void replayGame(View view) {
        // goes back to begin of game if user wants to replay game
        Intent startGame = new Intent(this, playGame.class);
        startActivity(startGame);
        finish();
    }
}
