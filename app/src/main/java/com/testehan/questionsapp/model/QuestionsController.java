package com.testehan.questionsapp.model;

import static com.testehan.questionsapp.model.QuestionConstants.*;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsController {

    public static final int NUMBER_OF_QUESTIONS = 501;
    public static final int NUMBER_OF_DATE_QUESTIONS = 36;

    private Integer selectedQuestionCategory = FRIENDS;

    public ArrayList<Question> selectedCategoryQuestions;

    public final ArrayList<Question> questions;

    int questionNo = -1;


    public QuestionsController() {
        questions = new ArrayList<>();
        selectedCategoryQuestions = new ArrayList<>();
    }

    public Question getNextQuestion(){
        if (selectedQuestionCategory != DATES) {
            questionNo = new Random().nextInt(selectedCategoryQuestions.size());
        } else {
            if (questionNo == NUMBER_OF_DATE_QUESTIONS){              // starting with 0, and we have 37 questions
                questionNo = 0;
            } else {
                questionNo++;
            }
        }
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
        this.questionNo = -1;
        this.selectedQuestionCategory = selectedQuestionCategory;
    }


}
