package com.maximilianobpacher.zenguitartabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.maximilianobpacher.zenguitartabs.R;

public class GameOverActivity extends Activity {

    private static int rightAnswers = 0;
    private static int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        setContentView(R.layout.activity_game_over);

        // Setting up the View

        TextView resultField = (TextView) findViewById(R.id.results);

        resultField.setText("Correct Answers: " + rightAnswers + "/"+ (rightAnswers+wrongAnswers));
    }

    public static void setRightAnswers(int rightAnswers) {
        GameOverActivity.rightAnswers = rightAnswers;
    }

    public static void setWrongAnswers(int wrongAnswers) {
        GameOverActivity.wrongAnswers = wrongAnswers;
    }

    // Onclicks for replay level and go to menu Button
    public void replayLevel(View view){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


    public void goToMenu(View view){
        Intent intent = new Intent(this, StartScreenActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_over, menu);
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
