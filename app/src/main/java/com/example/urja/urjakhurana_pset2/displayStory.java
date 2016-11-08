package com.example.urja.urjakhurana_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class displayStory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);
        Bundle extras = getIntent().getExtras();
        String createdStory = extras.getString("thestory");
        TextView showStory = (TextView) findViewById(R.id.showStory);
        showStory.setText(createdStory);
    }
}
