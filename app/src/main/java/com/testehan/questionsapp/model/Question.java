package com.testehan.questionsapp.model;

class Question {
    private final String questionText;
    // I will need to add here category or tags to differentiate between questions

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }
}
