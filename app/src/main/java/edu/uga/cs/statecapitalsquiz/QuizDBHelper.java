package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizDBHelper";

    private static final String DB_NAME = "quiz.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_QUESTIONS = "question_and_answer";
    public static final String QUESTIONS_COLUMN_ID = "_id";
    public static final String QUESTIONS_COLUMN_QUESTIONS = "questions";
    public static final String QUESTIONS_COLUMN_ANSWERS = "answers";

    private static QuizDBHelper helperInstance;

    private static final String CREATE_QUESTION_AND_ANSWER = "create table " + TABLE_QUESTIONS +
            "("+ QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUESTIONS_COLUMN_QUESTIONS + " TEXT , " +
            QUESTIONS_COLUMN_ANSWERS + " TEXT)";

    private QuizDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    public static synchronized QuizDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUESTION_AND_ANSWER );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUESTIONS + " created" );
    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("drop table if exists " + TABLE_QUESTIONS);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUESTIONS + " upgraded");
    }

}
