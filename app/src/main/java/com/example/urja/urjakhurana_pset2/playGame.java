package com.example.urja.urjakhurana_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
public class playGame extends AppCompatActivity {
    Story story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        InputStream readFile = null;
        // http://stackoverflow.com/questions/14515550/inputstream-from-assets-folder-on-android-returning-empty
        AssetManager am = getAssets();
        try {
            readFile = am.open("madlib2_university.txt");
        } catch (IOException e) {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, "File not found", duration);
            toast.show();
        }
        story = new Story(readFile);
        EditText fillInText = (EditText) findViewById(R.id.textField);
        fillInText.setHint(story.getNextPlaceholder());
    }

    public void fillIn(View view) {
        EditText fillInText = (EditText) findViewById(R.id.textField);
        story.fillInPlaceholder(fillInText.getText().toString());
        if (story.getPlaceholderRemainingCount() != 0) {
            fillInText.setText("");
            fillInText.setHint(story.getNextPlaceholder());
        } else {
            Intent displayStory = new Intent(this, displayStory.class);
            String stringStory = story.toString();
            displayStory.putExtra("thestory", stringStory);
            startActivity(displayStory);
            finish();
        }
    }
}
