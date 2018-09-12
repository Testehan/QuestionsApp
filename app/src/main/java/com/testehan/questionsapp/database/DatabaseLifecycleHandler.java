package com.testehan.questionsapp.database;

import static com.testehan.questionsapp.database.DatabaseConstants.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseLifecycleHandler extends SQLiteOpenHelper {

    private static DatabaseLifecycleHandler INSTANCE;

    private DatabaseLifecycleHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
//        db.setForeignKeyConstraintsEnabled(true);     TODO For now it works like this. Consider changing API level
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS +
                "(" +
                    QUESTION_PK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // Define a primary key
                    QUESTION_TEXT + " TEXT, " +
                    QUESTION_VISITED + " INTEGER " +
                ")";

        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES +
                "(" +
                    CATEGORY_PK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CATEGORY_QUESTION_FK_ID + " INTEGER REFERENCES " + TABLE_QUESTIONS + "," +
                    CATEGORY_TYPE + " INTEGER" +
                ")";

        // do the population of the db as well here..

        db.execSQL(CREATE_QUESTIONS_TABLE);
        db.execSQL(CREATE_CATEGORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
//            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//            onCreate(db);
// more info here : https://stackoverflow.com/questions/8133597/android-upgrading-db-version-and-adding-new-table
        }

    }


    public static synchronized DatabaseLifecycleHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (INSTANCE == null) {
            INSTANCE = new DatabaseLifecycleHandler(context.getApplicationContext());
        }
        return INSTANCE;
    }


}
