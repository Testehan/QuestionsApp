package com.testehan.questionsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.testehan.questionsapp.database.DatabaseLifecycleHandler;
import com.testehan.questionsapp.database.DatabaseOperations;
import com.testehan.questionsapp.model.Question;
import com.testehan.questionsapp.model.QuestionsProvider;

public class MainActivity extends AppCompatActivity {

    private QuestionsProvider questionsProvider = new QuestionsProvider();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();

    }

    public void onButtonClicked(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nextQuestion());
    }

    private String nextQuestion() {
        return questionsProvider.getNextQuestionText();
    }

    private void initDatabase() {
        DatabaseLifecycleHandler databaseLifecycleHandler = DatabaseLifecycleHandler.getInstance(this);
        DatabaseOperations databaseOperations = new DatabaseOperations(databaseLifecycleHandler);

        // if these 2 numbers don't match, then we need to populate the db...Of course this code
        // needs improvements if we will need to update the questions from the app, because if we add
        // 2 new questions, then people updating the app will have all the questions reinserted in the app,
        // and we don't want this.. TODO...for now this is good, but we will need a solution for thism like findinf out how to move this in onCreate from DB

        System.out.println("++++++++++++++ ++++++++++ "+ questionsProvider.getQuestions().size() + "!=" + databaseOperations.getNumberOfQuestionsInDB());
        if (questionsProvider.getQuestions().size() != databaseOperations.getNumberOfQuestionsInDB()) {
            System.out.println("++++++++++++++ ++++++++++inserting the questions in the db " + questionsProvider.getQuestions().size() + "!=" + databaseOperations.getNumberOfQuestionsInDB());
//            databaseOperations.deleteAllQuestionsFromDB();
            for (Question question : questionsProvider.getQuestions()) {
                databaseOperations.insertQuestion(question);
            }
        }

    }
}
