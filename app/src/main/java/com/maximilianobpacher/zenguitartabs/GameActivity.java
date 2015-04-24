package com.maximilianobpacher.zenguitartabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends Activity {

    //------------------------------------ Variables -------------------------------------\\
    //------------------------------------------------------------------------------------\\

    // Boolean Variables: For status:
    // infiniteMode for infinite Rounds
    // roundIsOver for acceptance of Answers
    // buttonIsGreen is required to Check if the Button is already blinking
    boolean infiniteMode = false;
    boolean roundIsOver = false;
    boolean buttonIsGreen = false;

    // chordsForQuestions contains a list of Chords, that is used for the questions
    // chorsForAnswers contains a list of Chords, that is rtequired for the Answers
    // 2 variables, cause in Chords for Questions, showed chords get deleted to not show them twice
    // for Answers always all of the Chords have to be available
    ArrayList<Chord> chordsForQuestions = new ArrayList<Chord>();
    ArrayList<Chord> chordsForAnswers = new ArrayList<Chord>();

    // rightAnswers and wrongAnswers counts them for the result
    int rightAnswers = 0;
    int wrongAnswers = 0;

    // int level is required to select the correct chordsForQuestions for the Level
    // int correctAnswerButton is a randomNumber to Assign the Correct Answer to a random Button
    // and for Checking the answers correctness.

    static int level = 1;
    private int correctAnswerButton = 0;

    // Button rightButton is the Button which the Right answer Stands on
    Button rightButton;

    // rightChord saves the current Chord
    Chord rightChord;

    //------------------------------------- Methods --------------------------------------\\
    //------------------------------------------------------------------------------------\\

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Creating a FullScreen activity
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Connecting with Controller
        setContentView(R.layout.activity_main_game);

        //Setting up the Mode - Getting the right chords.
        getChordsForQuestions();
        getChordsForAnswers();



        //chordButtonList = chordsForQuestions;
        rightChord = chordsForQuestions.get(0);

        if(!infiniteMode) {
            chordsForQuestions.remove(rightChord);
        }

        // Setting first Chord for Quiz
        ImageView chordImage = (ImageView) findViewById(R.id.chordImage);
        chordImage.setImageDrawable(this.getResources().getDrawable(rightChord.getChordImage()));

        // using printAnswersToButton to print the Answers
        printAnswersToButton(rightChord, chordsForAnswers);
    }


    // Setter for Level Method
    public static void setLevel(int l) {
        level = l;
    }

    // Selecting Chords for the Level and Applying correct caption for the Level
    private void getChordsForQuestions() {

        chordsForQuestions = ChordLibrary.getChordList(level);

        // Setting the Toplinetext in the different levels
        if (level == 0) {

            TextView topline =(TextView) findViewById(R.id.Topline);
            topline.setText("Zen Mode");
            infiniteMode = true;
           }
        else {
            TextView topline =(TextView) findViewById(R.id.Topline);
            topline.setText("Level " + level);
            infiniteMode = false;
        }
    }

    //Getting Answerschords
    private void getChordsForAnswers() {

        chordsForAnswers = ChordLibrary.getChordList(level);
    }

    // Creating the Level (Showing Chord and Answers)
   public void createLevel() {

        // false for accepting button pressing, true for not accepting. so multiple pressed buttons
        // are prevented
        roundIsOver = false;

       // Getting the correct chord in Levelmode. Taking the first element.
       // Afterwards deleting it to not use it again. Always "poping out" first elem
        if(!infiniteMode){
            rightChord = chordsForQuestions.get(0);
            chordsForQuestions.remove(rightChord);
        }

        // Working in infiniteMode. As infiniteMode hast 100 rounds, chords have to be shown more often,
        // so chords don't get deleted here
        else {
            // Randomly selecting a chord.
            Random randomGenerator = new Random();
            int randomIndex = randomGenerator.nextInt(chordsForQuestions.size());
            rightChord = chordsForQuestions.get(randomIndex);

            // Showing right and wrong Answers in the topline
            TextView topline = (TextView) findViewById(R.id.Topline);
            topline.setText("Infinite Mode: " + rightAnswers + "/" +(rightAnswers+wrongAnswers));
        }

        // Setting the Chordimage
       ImageView chordImage = (ImageView) findViewById(R.id.chordImage);
       chordImage.setImageDrawable(this.getResources().getDrawable(rightChord.getChordImage()));

        // Getting an List, where all button elements are stored
        ArrayList<Button> buttons = getButtonList();

        // Setting the backgroundpicture for the buttons
        for (Button b : buttons) {
            b.setBackgroundResource(R.drawable.gamebutton);
        }

        // printing Answers to the Button
        printAnswersToButton(rightChord, chordsForAnswers);
    }

    // onResume for setting new Chords and AnswerButtons after each Answer
    protected void onResume() {
        super.onResume();

    }


    // printAnswersToButton assigns random Answers to the Buttons
   public int printAnswersToButton(Chord displayedChord, ArrayList <Chord> chordsForButtons) {

       // Getting the Buttons in the view and assigning them to variables.
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);

        ArrayList<Button> answerButtons = new ArrayList<Button>();

        for (Button b : answerButtons) {
            b.setBackgroundResource(R.drawable.gamebutton);
        }

        answerButtons.add(b1);
        answerButtons.add(b2);
        answerButtons.add(b3);
        answerButtons.add(b4);

       // Shuffling Buttons to printing the Answers randomly on them. So i can print the correct
       // Answer on the first Button in the List, which is randomly one of the four buttons
        Collections.shuffle(answerButtons);

       // Printing correct Answer on a random Button
        answerButtons.get(0).setText(displayedChord.getChordName());

       // Checking on which Button the correct Answer got printed for comparing it with the clicked
       // Button later on
       if(answerButtons.get(0) == b1){
           correctAnswerButton = 0;
           rightButton = b1;
       }
       else if(answerButtons.get(0) == b2){
           correctAnswerButton = 1;
           rightButton = b2;
       }
       else if(answerButtons.get(0) == b3){
           correctAnswerButton = 2;
           rightButton = b3;
       }
       else{
           correctAnswerButton = 3;
           rightButton = b4;
       }

        //Removing the displayedChord, to not show the correct answer two times
        chordsForButtons.remove(displayedChord);

        // Getting the 3 first element out of the List to show them on the other chord
            answerButtons.get(1).setText(chordsForButtons.get(0).getChordName());
            answerButtons.get(2).setText(chordsForButtons.get(1).getChordName());
            answerButtons.get(3).setText(chordsForButtons.get(2).getChordName());

       // Adding the Chord again to be save, that the Chord can be used for answers in the next rnd
       chordsForButtons.add(displayedChord);

   return correctAnswerButton;
   }

    // getButtonLists returns a List of the Answerbuttons, so you can work on them
    public ArrayList<Button> getButtonList() {

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);

        ArrayList<Button> buttons = new ArrayList<Button>();

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);

        return buttons;
    }

    // checkAnswers checks wether the Button clicked is the correct Answer
    public void checkAnswer(View view) {

        // Button Config for Blinking
        int startBlinking = 500;
        int blinkingDuration = 250;
        int timesOfBlinking = 5;
        int selectedButton = 0;

        // Checking witch Button was pressed
        switch (view.getId()) {
            case R.id.button1:
                selectedButton = 0;
                break;
            case R.id.button2:
                selectedButton = 1;
                break;
            case R.id.button3:
                selectedButton = 2;
                break;
            case R.id.button4:
                selectedButton = 3;
                break;
        }

        if (!roundIsOver) {

            // Dealing with correct Answers
            if (selectedButton == correctAnswerButton) {

                rightAnswers++;

                // Making the Background of the Correct/pressed (is the same) answer green
                rightButton.setBackgroundResource(R.drawable.rightanswer);

            }

            //Dealing with false Answers
            else {
                wrongAnswers++;

                Button wrongButton = (Button) findViewById(view.getId());

                // Make wrong Buttons background red
                wrongButton.setBackgroundResource(R.drawable.wronganswer);

                //Letting the correct Answer Blink
                for (int i = 0; i < timesOfBlinking; i++) {
                    rightButtonHandler.postDelayed(letCorrectAnswerBlink, startBlinking + blinkingDuration * i);

                }

            }
            // Handler for Starting next round
            startNextRoundHandler.postDelayed(runNextRound, startBlinking + blinkingDuration * timesOfBlinking + 200);
            roundIsOver = true;
        }
    }

    // Runnable for Letting the Correct Answer Blink
    Handler rightButtonHandler = new Handler();
    private Runnable letCorrectAnswerBlink = new Runnable() {
        @Override
        public void run() {
            try {
                if (!buttonIsGreen) {
                    //Changing background
                    rightButton.setBackgroundResource(R.drawable.rightanswer);
                    buttonIsGreen = true;
                } else {
                    //Changing Background
                    rightButton.setBackgroundResource(R.drawable.gamebutton);
                    buttonIsGreen = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler startNextRoundHandler = new Handler();
    private Runnable runNextRound = new Runnable() {
        @Override
        public void run() {
            try {
                prepareForNextRound();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    private void prepareForNextRound() {

        //Check for Game Over: case 1 infiteMode
        if (!infiniteMode) {
            if (chordsForQuestions.size() == 0) {
                // Passing required information to GameOverPage & Starting Activity

                GameOverActivity.setRightAnswers(rightAnswers);
                GameOverActivity.setWrongAnswers(wrongAnswers);

                Intent intent = new Intent(this, GameOverActivity.class);
                GameActivity.this.startActivity(intent);
            } else {
                createLevel();
            }
        }

        //Check for Game over - Zen-Mode
        else{
            if(wrongAnswers >= 3 || (wrongAnswers+rightAnswers) == 100) {

                // Same as in Levelmode
                GameOverActivity.setRightAnswers(rightAnswers);
                GameOverActivity.setWrongAnswers(wrongAnswers);

                Intent intent = new Intent(this, GameOverActivity.class);
                GameActivity.this.startActivity(intent);
            }
            else{
                createLevel();
            }

            }
    }
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main_game, menu);
            return true;
        }

}
