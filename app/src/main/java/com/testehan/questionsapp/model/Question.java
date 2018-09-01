package com.testehan.questionsapp.model;

import java.util.List;

public class Question {
    private final String questionText;
    // I will need to add here category or tags to differentiate between questions
    private final List<Integer> questionCategories;

    public Question(String questionText, List<Integer> categories)
    {
        this.questionText = questionText;
        this.questionCategories = categories;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<Integer> getQuestionCategories() {
        return questionCategories;
    }
}
