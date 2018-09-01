package com.testehan.questionsapp.model;

import java.util.List;

public class Question {
    private Integer questionId;
    private String questionText;
    private List<Integer> questionCategories;

    public Question(){};

    // constructor used when populating the DB with the initial data
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

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
