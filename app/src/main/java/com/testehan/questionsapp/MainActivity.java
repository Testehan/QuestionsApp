package com.testehan.questionsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

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

    public void onButtonClicked(View v){
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


        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nextQuestion());

        changeStartButtonText();
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
