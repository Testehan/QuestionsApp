package com.testehan.questionsapp;

import static com.testehan.questionsapp.model.QuestionConstants.*;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.testehan.questionsapp.database.DatabaseLifecycleHandler;
import com.testehan.questionsapp.database.DatabaseOperations;
import com.testehan.questionsapp.model.Question;
import com.testehan.questionsapp.model.QuestionsController;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private QuestionsController questionsController = new QuestionsController();
    private boolean questionsStarted = false;

    private InterstitialAd mInterstitialAd;
    private int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        initAds();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        resetQuestionSetup();
        switch (id){
            case R.id.strangers:
                showHelpMessage("Strangers");
                questionsController.setSelectedQuestionCategory(STRANGERS);
                handleActivityColors(STRANGERS);
                return true;
            case R.id.family:
                showHelpMessage("Family");
                questionsController.setSelectedQuestionCategory(FAMILY);
                handleActivityColors(FAMILY);
                return true;
            case R.id.friends:
                showHelpMessage("Friends");
                questionsController.setSelectedQuestionCategory(FRIENDS);
                handleActivityColors(FRIENDS);
                return true;
            case R.id.dates:
                showHelpMessage("Dates");
                questionsController.setSelectedQuestionCategory(DATES);
                handleActivityColors(DATES);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void onButtonClicked(View v){
        if (questionNumber == 10){
            handleAds();
        } else {
            populateSelectedCategoryQuestions();

            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(nextQuestion());

            changeStartButtonText();

            questionNumber++;
        }
    }

    public void onShareButtonClicked(View v){       //TODO ADD share text body etc below...
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Here is the share content body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    private void showHelpMessage(String category) {
        String message = "Press Start button to see the questions from " +category+ " category \n\n OR \n\n Select a new category from the menu";
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

//        Toast toast = Toast.makeText(getApplicationContext(),category + " category selected",Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.show();
    }

    private void handleActivityColors(Integer category) {
        switch (category){
            case STRANGERS:
                findViewById(R.id.mainView).setBackgroundColor(Color.rgb(239, 236, 231));
                break;
            case FAMILY:
                findViewById(R.id.mainView).setBackgroundColor(Color.rgb(100,164,217));
                break;
            case FRIENDS:
                findViewById(R.id.mainView).setBackgroundColor(Color.rgb(123,205,200));
                break;
            case DATES:
                findViewById(R.id.mainView).setBackgroundColor(Color.rgb(255,160,152));
                break;
        }
    }

    private void resetQuestionSetup() {
        Button button = (Button) findViewById(R.id.button);
        button.setText(R.string.start);
        questionsStarted = false;

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("");

        questionsController.getSelectedCategoryQuestions().clear();
    }

    private void handleAds() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            System.out.println("The interstitial wasn't loaded yet.");
        }
        questionNumber = 0;
    }

    private void initAds() {
        MobileAds.initialize(this, "ca-app-pub-4551088019011645~1202349694");
        mInterstitialAd = new InterstitialAd(this);                         // TODO Use my code when publishing the app
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // ca-app-pub-4551088019011645~1202349694
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });
    }

    private void populateSelectedCategoryQuestions() {
        if (questionsController.getSelectedCategoryQuestions().isEmpty()){
            DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
            DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);
            ArrayList<Question> returnedQuestions = databaseOperations.getQuestionsOfCategory(questionsController.getSelectedQuestionCategory());

            if (returnedQuestions.isEmpty()){
                // this means that all questions from the DB that have the selected category were visited already
                databaseOperations.updateQuestionsOfCategory(questionsController.getSelectedQuestionCategory());
                returnedQuestions = databaseOperations.getQuestionsOfCategory(questionsController.getSelectedQuestionCategory());
            }
            questionsController.setSelectedCategoryQuestions(returnedQuestions);
        }
    }

    private void changeStartButtonText() {
        if (!questionsStarted) {
            Button button = (Button) findViewById(R.id.button);
            button.setText(R.string.next_question);
            questionsStarted = true;
        }
    }

    private String nextQuestion() {
        Question currentQuestion = questionsController.getNextQuestion();
        currentQuestion.setVisited();
        questionsController.removeSelectedQuestion(currentQuestion);

        DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
        DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);

        databaseOperations.updateQuestionVisited(currentQuestion);

        return currentQuestion.getQuestionText();
    }

    private void initDatabase() {                           // TODO Just pass the context to a different class that will handle all things related to DB stuff
        DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
        DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);

        // if these 2 numbers don't match, then we need to populate the db...Of course this code
        // needs improvements if we will need to update the questions from the app, because if we add
        // 2 new questions, then people updating the app will have all the questions reinserted in the app,
        // and we don't want this.. TODO...for now this is good, but we will need a solution for thism like findinf out how to move this in onCreate from DB

        if (QuestionsController.NUMBER_OF_QUESTIONS != databaseOperations.getNumberOfQuestionsInDB()) {
            System.out.println("++++++++++++++ ++++++++++inserting the questions in the db " + QuestionsController.NUMBER_OF_QUESTIONS + "!=" + databaseOperations.getNumberOfQuestionsInDB());
            databaseOperations.deleteAllQuestionsFromDB();
            for (Question question : questionsController.getQuestions()) {
                databaseOperations.insertQuestion(question);
            }
        }

    }
}
