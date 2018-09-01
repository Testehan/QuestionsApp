package com.testehan.questionsapp.model;

import static com.testehan.questionsapp.model.QuestionConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionsProvider {

    public final ArrayList<Question> questions;

    public QuestionsProvider() {
        questions = new ArrayList<>();

        initDummyData();
    }

    public String getNextQuestionText(){
        int questionNo = new Random().nextInt(questions.size());
        return questions.get(questionNo).getQuestionText();
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

    private void initDummyData() {
        Question question = new Question("Do you squeeze the toothpaste tube or roll it?", Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's one of your nicknames?",Arrays.asList(STRANGERS));
        questions.add(question);
        question = new Question("What kinds of movies do you most enjoy?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What magic tricks do you know?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's one guilty pleasure you enjoy too much to give up?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's the most embarrassing thing you've seen someone else do?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Who is the closest friend you've ever had? Describe that relationship.",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's the worst tasting thing you've ever eaten?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Are you a hugger or a non-hugger?",Arrays.asList(STRANGERS,FRIENDS));
        questions.add(question);
        question = new Question("What was the most recent compliment you received and savoured?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("When are you shy?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Are you usually late, early, or on time?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's one of your hobbies?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What bad habit do you wish you could break?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What bores you?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What do you think about more than anything else?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What fear would you like to overcome?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("In what situations are you most uncomfortable?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Whom do you run to when something bad happens in your life?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
    }
}
