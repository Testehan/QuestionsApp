package com.testehan.questionsapp;

import static com.testehan.questionsapp.model.QuestionConstants.*;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.testehan.questionsapp.database.DatabaseLifecycleHandler;
import com.testehan.questionsapp.database.DatabaseOperations;
import com.testehan.questionsapp.model.Question;
import com.testehan.questionsapp.model.QuestionsProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private QuestionsProvider questionsProvider = new QuestionsProvider();
    private boolean questionsStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

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
                Toast.makeText(getApplicationContext(),"Strangers category selected",Toast.LENGTH_LONG).show();
                questionsProvider.setSelectedQuestionCategory(STRANGERS);
                handleActivityColors(STRANGERS);
                return true;
            case R.id.family:
                Toast.makeText(getApplicationContext(),"Family category selected",Toast.LENGTH_LONG).show();
                questionsProvider.setSelectedQuestionCategory(FAMILY);
                handleActivityColors(FAMILY);
                return true;
            case R.id.friends:
                Toast.makeText(getApplicationContext(),"Friends category selected",Toast.LENGTH_LONG).show();
                questionsProvider.setSelectedQuestionCategory(FRIENDS);
                handleActivityColors(FRIENDS);
                return true;
            case R.id.dates:
                Toast.makeText(getApplicationContext(),"Dates category NOT WORKING",Toast.LENGTH_LONG).show();
                questionsProvider.setSelectedQuestionCategory(DATES);
                handleActivityColors(DATES);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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

        questionsProvider.getSelectedCategoryQuestions().clear();
    }

    public void onButtonClicked(View v){
        populateSelectedCategoryQuestions();


        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nextQuestion());

        changeStartButtonText();
    }

    private void populateSelectedCategoryQuestions() {
        if (questionsProvider.getSelectedCategoryQuestions().isEmpty()){
            DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
            DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);
            ArrayList<Question> returnedQuestions = databaseOperations.getQuestionsOfCategory(questionsProvider.getSelectedQuestionCategory());

            if (returnedQuestions.isEmpty()){
                // this means that all questions from the DB that have the selected category were visited already
                databaseOperations.updateQuestionsOfCategory(questionsProvider.getSelectedQuestionCategory());
                returnedQuestions = databaseOperations.getQuestionsOfCategory(questionsProvider.getSelectedQuestionCategory());
            }
            questionsProvider.setSelectedCategoryQuestions(returnedQuestions);
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
        Question currentQuestion = questionsProvider.getNextQuestion();
        currentQuestion.setVisited();
        questionsProvider.removeSelectedQuestion(currentQuestion);

        DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
        DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);

        databaseOperations.updateQuestionVisited(currentQuestion);

        System.out.println("question with id" + currentQuestion.getQuestionId() + " was set as visited " + currentQuestion.getVisited());

        return currentQuestion.getQuestionText();
    }

    private void initDatabase() {                           // TODO Just pass the context to a different class that will handle all things related to DB stuff
        DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
        DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);

        // if these 2 numbers don't match, then we need to populate the db...Of course this code
        // needs improvements if we will need to update the questions from the app, because if we add
        // 2 new questions, then people updating the app will have all the questions reinserted in the app,
        // and we don't want this.. TODO...for now this is good, but we will need a solution for thism like findinf out how to move this in onCreate from DB

        if (QuestionsProvider.NUMBER_OF_QUESTIONS != databaseOperations.getNumberOfQuestionsInDB()) {
            System.out.println("++++++++++++++ ++++++++++inserting the questions in the db " + QuestionsProvider.NUMBER_OF_QUESTIONS + "!=" + databaseOperations.getNumberOfQuestionsInDB());
            databaseOperations.deleteAllQuestionsFromDB();
            for (Question question : questionsProvider.getQuestions()) {
                databaseOperations.insertQuestion(question);
            }
        }

    }
}
