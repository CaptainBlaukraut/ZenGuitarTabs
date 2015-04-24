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

public class LevelSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        //Creating a FullScreen activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_level_select);
    }

    //set level for the game depending on which button is clicked
    public void playLevel(View view){
        int level = 1;
        switch(view.getId()) {
            case R.id.playlevel1:
                level = 1;
                break;
            case R.id.playlevel2:
                level = 2;
                break;
            case R.id.playlevel3:
                level = 3;
                break;
        }
        //Pass level to the main game activity
        GameActivity.setLevel(level);

        // starting the game
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);

    }

    // same as in playgame, just for the chords activity
    public void showChords(View view) {
        int level = 1;
        switch(view.getId()) {

            case R.id.level1:
                Log.d("Level: ", "" + level);
                level = 1;
                break;

            case R.id.level2:
                level = 2;
                break;

            case R.id.level3:
                level = 3;
                break;
        }
        ChordsOverviewActivity.setLevel(level);

        Intent intent = new Intent(this, ChordsOverviewActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.level_select, menu);
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
