package com.testehan.questionsapp.database;

import static com.testehan.questionsapp.database.DatabaseConstants.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.testehan.questionsapp.model.Question;

public class DatabaseOperations {
    
    private final DatabaseLifecycleHandler databaseLifecycleHandler;

    public DatabaseOperations(DatabaseLifecycleHandler databaseLifecycleHandler) {
        this.databaseLifecycleHandler = databaseLifecycleHandler;
    }


    public void insertQuestion(Question question) {
        SQLiteDatabase db = databaseLifecycleHandler.getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();

        try {
            // Insert question
            ContentValues questionValues = new ContentValues();
            questionValues.put(QUESTION_TEXT, question.getQuestionText());
            questionValues.put(QUESTION_VISITED, 0);

            long questionId = db.insertOrThrow(TABLE_QUESTIONS, null, questionValues);

            // Insert question categories
            for (int questionCategory : question.getQuestionCategories()) {
                ContentValues questionCategoryValues = new ContentValues();
                questionCategoryValues.put(CATEGORY_QUESTION_FK_ID, questionId);
                questionCategoryValues.put(CATEGORY_TYPE, questionCategory);

                db.insertOrThrow(TABLE_CATEGORIES, null, questionCategoryValues);
            }

            db.setTransactionSuccessful();

        } catch (Exception e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }

    public long getNumberOfQuestionsInDB() {
        SQLiteDatabase db = databaseLifecycleHandler.getReadableDatabase();

        return DatabaseUtils.queryNumEntries(db, TABLE_QUESTIONS);
    }

    public void deleteAllQuestionsFromDB(){
        SQLiteDatabase db = databaseLifecycleHandler.getReadableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(TABLE_CATEGORIES, null, null);
            db.delete(TABLE_QUESTIONS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            throw e;
        } finally {
            db.endTransaction();
        }
    }



}
