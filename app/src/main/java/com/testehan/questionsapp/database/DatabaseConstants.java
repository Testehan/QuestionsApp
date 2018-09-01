package com.testehan.questionsapp.database;

public class DatabaseConstants {

    // Database Info
    public static final String DATABASE_NAME = "QUESTIONS_DATABASE";
    public static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_QUESTIONS = "questions";
    public static final String TABLE_CATEGORIES = "categories";

    // QUESTIONS Table Columns
    public static final String QUESTION_PK_ID = "id";
    public static final String QUESTION_TEXT = "text";
    public static final String QUESTION_VISITED = "visited";
//    public static final String QUESTION_FAVORITED = "favorited";    TODO

    // CATEGORIES Table Columns
    public static final String CATEGORY_PK_ID = "id";
    public static final String CATEGORY_QUESTION_FK_ID = "question_id";
    public static final String CATEGORY_TYPE = "category_type";
}
