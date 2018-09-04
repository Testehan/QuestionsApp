package com.testehan.questionsapp.model;

import static com.testehan.questionsapp.model.QuestionConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionsController {

    public static final int NUMBER_OF_QUESTIONS = 87;

    private Integer selectedQuestionCategory = FRIENDS;

    public ArrayList<Question> selectedCategoryQuestions;

    public final ArrayList<Question> questions;


    public QuestionsController() {
        questions = new ArrayList<>();
        selectedCategoryQuestions = new ArrayList<>();
    }

    public Question getNextQuestion(){
        int questionNo = new Random().nextInt(selectedCategoryQuestions.size());
        return selectedCategoryQuestions.get(questionNo);
    }

    public void removeSelectedQuestion(Question question){
        selectedCategoryQuestions.remove(question);
    }

    public ArrayList<Question> getSelectedCategoryQuestions(){
        return this.selectedCategoryQuestions;
    }

    public void setSelectedCategoryQuestions(ArrayList<Question> selectedCategoryQuestions) {
        this.selectedCategoryQuestions = selectedCategoryQuestions;
    }

    public ArrayList<Question> getQuestions(){
        if (questions.isEmpty()){
            questions.addAll(QuestionsProvider.getListOfQuestions());
        }
        return this.questions;
    }

    public Integer getSelectedQuestionCategory() {
        return selectedQuestionCategory;
    }

    public void setSelectedQuestionCategory(Integer selectedQuestionCategory) {
        this.selectedQuestionCategory = selectedQuestionCategory;
    }


}
