package com.example.urja.urjakhurana_pset2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class playGame extends AppCompatActivity {
    // declare as global variable since it is needed everywhere
    Story story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        // initialize
        InputStream readFile = null;
        // http://stackoverflow.com/questions/14515550/inputstream-from-assets-folder-on-android-returning-empty
        AssetManager am = getAssets();
        // get random file for surprise element
        String file = getFileFromInt();
        try {
            readFile = am.open(file);
        } catch (IOException e) {
            // display toast if file does not exist to catch error
            Toast.makeText(this, "File not found.", Toast.LENGTH_SHORT).show();
        }
        // get story class
        story = new Story(readFile);
        EditText fillInText = (EditText) findViewById(R.id.textField);
        // set hint for what type of word user needs to fill in
        fillInText.setHint(story.getNextPlaceholder());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // save story when activity is stopped for e.g. rotation of screen
        outState.putSerializable("retrievalstory", story);
        outState.putString("placeholder", story.getNextPlaceholder());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        // get saved story back once activity is restarted
        super.onRestoreInstanceState(inState);
        story = (Story) inState.getSerializable("retrievalstory");
        // sets the right placeholder as hint (otherwise a different placeholder is shown)
        EditText fillInText = (EditText) findViewById(R.id.textField);
        fillInText.setHint(story.getNextPlaceholder());
    }

    public void fillIn(View view) {
        EditText fillInText = (EditText) findViewById(R.id.textField);
        // get length of value given by user to check if it isn't empty
        int stringLength = fillInText.getText().toString().length();
        if (stringLength != 0) {
            // gets user input to store in story if user has given valid answer
            story.fillInPlaceholder(fillInText.getText().toString());
        } else {
            // tells user to give a valid answer
            Toast.makeText(this, "Please fill in your word.", Toast.LENGTH_SHORT).show();
        }
        // if more placeholders left, keep on asking for input
        if (story.getPlaceholderRemainingCount() != 0) {
            fillInText.setText("");
            fillInText.setHint(story.getNextPlaceholder());
        } else {
            // if last placeholder is filled, go to next screen to show story
            Intent displayStory = new Intent(this, displayStory.class);
            String stringStory = story.toString();
            // send story in string form to next screen to display
            displayStory.putExtra("thestory", stringStory);
            startActivity(displayStory);
            finish();
        }
    }

    private String getFileFromInt() {
        Random random = new Random();
        // get random number in range from 0 to 4
        int randomInt = random.nextInt(5);
        // initialize variable
        String file = null;
        // given a random integer, choose the file corresponding to it
        switch(randomInt) {
            case 0:
                file = "madlib0_simple.txt";
                break;
            case 1:
                file = "madlib1_tarzan.txt";
                break;
            case 2:
                file = "madlib2_university.txt";
                break;
            case 3:
                file = "madlib3_clothes.txt";
                break;
            case 4:
                file = "madlib4_dance.txt";
                break;
        }
        return file;
    }
}
