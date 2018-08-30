package com.testehan.questionsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.testehan.questionsapp.model.QuestionsProvider;

public class MainActivity extends AppCompatActivity {

    private QuestionsProvider questionsProvider = new QuestionsProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View v){
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(nextQuestion());
    }

    private String nextQuestion() {
        return questionsProvider.getNextQuestionText();
    }
}
