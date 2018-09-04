package com.testehan.questionsapp.model;

import java.util.ArrayList;
import java.util.Arrays;

import static com.testehan.questionsapp.model.QuestionConstants.DATES;
import static com.testehan.questionsapp.model.QuestionConstants.FAMILY;
import static com.testehan.questionsapp.model.QuestionConstants.FRIENDS;
import static com.testehan.questionsapp.model.QuestionConstants.STRANGERS;

public class QuestionsProvider {

    public static ArrayList<Question> getListOfQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

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

        // 19 questions

        question = new Question("What is your all time favourite movie, and how many times have you seen it? ",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What is your favourite board game? Do you usually win?",Arrays.asList(STRANGERS,FRIENDS));
        questions.add(question);
        question = new Question("Where is your perfect vacation spot?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What is your favourite style of music..how often do you listen to it?",Arrays.asList(STRANGERS,FRIENDS));
        questions.add(question);
        question = new Question("What is a book that you recommend?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What annoys you most about women?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What annoys you most about men?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What do you like most about women?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What do you like most about men?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's your favourite color? Why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's your all-time favourite band, and what would you give to meet them?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's your least favourite thing to do? Why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's your preferred way to meet new people?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Do you remember jokes very well? What's the best joke you've heard recently?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What are the top three qualities that first draw you to someone new?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);

        // 34 questions

        question = new Question("What's one of your greatest achievements?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's your favorite birthday memory?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("As a kid, what did you want to grow up to be?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's the most dangerous situation you've encountered? How did you react?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Who has made the biggest impact on you? Explain.",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Who has been your friend the longest? How and when did you meet him or her?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What childhood accident stands out in your mind?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Did you ever cheat on a school exam? Describe the situation.",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's the best advice you've ever been given?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What dream has come true for you?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's one of the biggest purchases you made, and how did you negotiate the transaction?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What nightmare woke you up in a panic?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("How many foreign countries have you visited? Which one did you like the most?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Who was your first boyfriend or girlfriend? Any details?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Life is not measured by the number of breaths you take, but by the moments that take your breath away (George Carlin). When have you had such a moment?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What was your most romantic date?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What was the best or worst job you ever had?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's the most adventurous or daring thing you have ever done?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("How would you describe one of your happiest childhood memories?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);

        // 53 questions

        question = new Question("If you could be invisible for a day, what would you do?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could eliminate one weakness or limitation in your life, what would it be?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could change anything about your relationship with your parents, what would it be?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could go back in time, what year would you visit? Why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you had an unlimited shopping spree at only one store, which one would you choose?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could talk with only one person for the rest of your life, who would it be and why?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could do something very daring without fear, what would you do?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you won a million dollars in the lottery, how would you spend it?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could change one thing about your appearance, what would it be?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could change one thing about your personality, how would you be different?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you were able to listen to only one music CD ever again, what would it be and why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could restore one broken relationship, which one would you choose? Why?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could look into the future to find out one thing, what would you want to know?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could read everyone's mind for one week only, would you tell anyone or keep it a secret?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could live forever on earth, would you choose to do so? Explain.",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you had to do your life over, what one thing would you do differently?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could trade places with anyone for one week, who would you want to trade with and why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could be any age for the rest of your life, what age would you choose? Why?",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you were to get a tattoo, what would it be? And where would you put it?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("If you could take back something you've said, what would it be?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);

        // 73 questions

        question = new Question("What does success mean to you?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What is beauty?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("How would you describe the perfect day?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What advice can you give about how to conquer fear?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What advice can you give about how to relieve stress?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Why do you think it's so hard for people to say they're sorry?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Why are bad habits so hard to break?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What makes a true friendship?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What does it mean when two people are said to have chemistry?",Arrays.asList(FRIENDS));
        questions.add(question);
        question = new Question("How important is physical appearance when it comes to achieving success or getting one's way in life?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What is the meaning of life?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("Why is life so hard? Why is life so complicated?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);
        question = new Question("What's one thing you know for sure?",Arrays.asList(FAMILY,FRIENDS));
        questions.add(question);

        // 86 questions

        question = new Question("No dates question yet...",Arrays.asList(DATES));
        questions.add(question);

        // 87
//
//        question = new Question("",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
//        questions.add(question);
//        question = new Question("",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
//        questions.add(question);
//        question = new Question("",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
//        questions.add(question);
//        question = new Question("",Arrays.asList(STRANGERS,FAMILY,FRIENDS));
//        questions.add(question);


        return questions;
    }
}
