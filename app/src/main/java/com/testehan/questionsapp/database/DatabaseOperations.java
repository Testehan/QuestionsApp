package com.testehan.questionsapp.database;

import static com.testehan.questionsapp.database.DatabaseConstants.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import com.testehan.questionsapp.model.Question;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

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

    // only questions from a category that were not visited are returned.
    public ArrayList<Question> getQuestionsOfCategory(Integer selectedQuestionCategory) {

        ArrayList<Question> questions = new ArrayList<>();

        // SELECT * FROM QUESTIONS
        // LEFT OUTER JOIN CATEGORIES
        // ON QUESTIONS.ID = CATEGORIES.question_id
        // WHERE CATEGORIES.category_type = selectedQuestionCategory;
        // AND QUESTIONS.visited = 0;

        String SELECT_QUESTIONS_BY_CATEGORY_QUERY =
                String.format("SELECT * FROM %s LEFT OUTER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = %s AND %s.%s = %s",
                        TABLE_QUESTIONS,
                        TABLE_CATEGORIES,
                        TABLE_QUESTIONS, QUESTION_PK_ID,
                        TABLE_CATEGORIES, CATEGORY_QUESTION_FK_ID,
                        TABLE_CATEGORIES, CATEGORY_TYPE,
                        selectedQuestionCategory,
                        TABLE_QUESTIONS, QUESTION_VISITED,
                        QUESTION_WAS_NOT_VISITED);

        SQLiteDatabase db = databaseLifecycleHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUESTIONS_BY_CATEGORY_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Question newQuestion = new Question();
                    newQuestion.setQuestionId(cursor.getInt(cursor.getColumnIndex(CATEGORY_QUESTION_FK_ID)));
                    newQuestion.setQuestionText(cursor.getString(cursor.getColumnIndex(QUESTION_TEXT)));

                    questions.add(newQuestion);

                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return questions;
    }

    public int updateQuestionVisited(Question question) {
        SQLiteDatabase db = databaseLifecycleHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION_VISITED, question.getVisited());

        return db.update(TABLE_QUESTIONS, values, QUESTION_PK_ID + " = ?",
                new String[] { String.valueOf(question.getQuestionId()) });
    }

    // after visiting all questions from a category, I will reset them to unvisited
    public void updateQuestionsOfCategory(Integer selectedQuestionCategory) {
        SQLiteDatabase db = databaseLifecycleHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION_VISITED, QUESTION_WAS_NOT_VISITED);

        //UPDATE QUESTIONS
        //SET visited = 0
        //WHERE id in (SELECT question_id from categories where categories.category_type = 3)

        String updateQuery = QUESTION_PK_ID + " IN (SELECT " + CATEGORY_QUESTION_FK_ID + " from " +TABLE_CATEGORIES + " where " + TABLE_CATEGORIES +"."+ CATEGORY_TYPE +"= ?)";

        db.update(TABLE_QUESTIONS, values, updateQuery, new String[] { selectedQuestionCategory.toString() });
    }
}
