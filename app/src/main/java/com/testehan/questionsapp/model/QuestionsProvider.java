package com.testehan.questionsapp.model;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsProvider {

    public final ArrayList<Question> questions;

    public QuestionsProvider() {
        questions = new ArrayList<>();

        initDummyData();
    }

    public String getNextQuestionText(){
        int questionNo = new Random().nextInt(questions.size());
        int x;
        return questions.get(questionNo).getQuestionText();
    }

    private void initDummyData() {
        Question question = new Question("Do you squeeze the toothpaste tube or roll it?Do you squeeze the toothpaste tube or roll it?");
        questions.add(question);
        question = new Question("What's one of your nicknames?");
        questions.add(question);
        question = new Question("What kinds of movies do you most enjoy?");
        questions.add(question);
        question = new Question("What magic tricks do you know?");
        questions.add(question);
        question = new Question("What's one guilty pleasure you enjoy too much to give up?");
        questions.add(question);
        question = new Question("What's the most embarrassing thing you've seen someone else do?");
        questions.add(question);
        question = new Question("Who is the closest friend you've ever had? Describe that relationship.");
        questions.add(question);
        question = new Question("What's the worst tasting thing you've ever eaten?");
        questions.add(question);
        question = new Question("Are you a hugger or a non-hugger?");
        questions.add(question);
        question = new Question("What was the most recent compliment you received and savoured?");
        questions.add(question);
        question = new Question("When are you shy?");
        questions.add(question);
        question = new Question("Are you usually late, early, or on time?");
        questions.add(question);
        question = new Question("What's one of your hobbies?");
        questions.add(question);
        question = new Question("What bad habit do you wish you could break?");
        questions.add(question);
        question = new Question("What bores you?");
        questions.add(question);
        question = new Question("What do you think about more than anything else?");
        questions.add(question);
        question = new Question("What fear would you like to overcome?");
        questions.add(question);
        question = new Question("In what situations are you most uncomfortable?");
        questions.add(question);
        question = new Question("Whom do you run to when something bad happens in your life?");
        questions.add(question);
    }
}
