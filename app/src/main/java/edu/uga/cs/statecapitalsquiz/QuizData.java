package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizData {

    public static final String DEBUG_TAG = "QuizData";

    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDbHelper;
    private static final String[] allColumns = {
      QuizDBHelper.QUESTIONS_COLUMN_ID,
      QuizDBHelper.QUESTIONS_COLUMN_QUESTIONS,
      QuizDBHelper.QUESTIONS_COLUMN_ANSWERS
    };

    public QuizData( Context context ) {
        this.quizDbHelper = QuizDBHelper.getInstance( context );
    }

    // Open the database
    public void open() {
        db = quizDbHelper.getWritableDatabase();
        Log.d( DEBUG_TAG, "JobLeadsData: db open" );
    }

    // Close the database
    public void close() {
        if (quizDbHelper != null) {
            quizDbHelper.close();
            Log.d(DEBUG_TAG, "JobLeadsData: db closed");
        }
    }

    public boolean isDBOpen()
    {
        return db.isOpen();
    }

    public Quiz storeQuizQnA(Quiz quiz){
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.QUESTIONS_COLUMN_QUESTIONS, quiz.getQuestion());
        values.put(QuizDBHelper.QUESTIONS_COLUMN_ANSWERS, quiz.getAnswer());
        Log.d(DEBUG_TAG, String.valueOf(values));
        long id = db.insert(QuizDBHelper.TABLE_QUESTIONS, null, values);

        quiz.setId( id );

        Log.d( DEBUG_TAG, "Stored new q & a with id: " + String.valueOf( quiz.getId() ) );
        return quiz;
    }
}
