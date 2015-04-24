package com.maximilianobpacher.zenguitartabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maximilianobpacher.zenguitartabs.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChordsOverviewActivity extends Activity {

    static int level = 0;
    int iterator = 0;
    Chord currentChord;
    ArrayList <Chord> chords = new ArrayList<Chord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        //Creating a FullScreen activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_chords_overview);

        //Getting the Chords, that shall be displayed
        chords = ChordLibrary.getChordList(level);

        //Selecting the chord, that shall be displayed
        currentChord = chords.get(iterator);

        //Setting the Chord Image
        ImageView chordImage = (ImageView) findViewById(R.id.chordPicture);
        chordImage.setImageDrawable(this.getResources().getDrawable(currentChord.getChordImage()));

        //Setting the Chord Text
        TextView chordName = (TextView) findViewById(R.id.chordName);
        chordName.setText(currentChord.getChordName() + "-Chord");

        iterator++;

    }

    public static void setLevel(int l) {
        level = l;
    }

    public void showNext(View view) {

        if (iterator < 7) {

            //Showing I'm done Button in case of the last chord
            if(iterator == 6){
                Button b = (Button) findViewById(R.id.nextButton);
                b.setText("I'm done!");
            }

            //Getting the next Chord to show
            currentChord = chords.get(iterator);

            //Hiding the prev Button on the first Chord... cause there is no prev...
            Button b = (Button) findViewById(R.id.prevButton);
            b.setVisibility(View.VISIBLE);

            //Setting new Chord Picture
            ImageView chordImage = (ImageView) findViewById(R.id.chordPicture);
            chordImage.setImageDrawable(this.getResources().getDrawable(currentChord.getChordImage()));

            //Setting the Name to the Chord Picture
            TextView chordName = (TextView) findViewById(R.id.chordName);
            chordName.setText(currentChord.getChordName() + "-Chord");

            iterator++;
        }

        else{

            //Going to Level select if all Chords have been shown
            Intent intent = new Intent(this, LevelSelectActivity.class);
            this.startActivity(intent);
        }
    }

    public void showPrevious(View view) {
            // -2 cause the Iterator alwas counts up after showing the chord, so -1 would be the current,
            // while -2 is the prev.
            iterator= iterator-2;

            //Getting the prev chord
            currentChord = chords.get(iterator);

            //Checking if it's the first chord, to know if prev button is required
            if(iterator == 0) {
                Button b = (Button) findViewById(R.id.prevButton);
                b.setVisibility(View.INVISIBLE);
            }

            //Setting Chord Picture
            ImageView chordImage = (ImageView) findViewById(R.id.chordPicture);
            chordImage.setImageDrawable(this.getResources().getDrawable(currentChord.getChordImage()));

            //Setting Chord Name
            TextView chordName = (TextView) findViewById(R.id.chordName);
            chordName.setText(currentChord.getChordName() + "-Chord");

            iterator++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chords_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
