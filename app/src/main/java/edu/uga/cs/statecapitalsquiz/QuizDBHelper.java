package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 */
public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizDBHelper";

    private static final String DB_NAME = "quiz.db";
    private static final int DB_VERSION = 1;

    // Define all names (strings) for table and column names.
    // This will be useful if we want to change these names later.
    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "_id";
    public static final String QUIZ_COLUMN_QUESTION = "question";
    public static final String QUIZ_COLUMN_ANSWER = "answer";
    public static final String QUIZ_COLUMN_XANSWER1 = "xanswer1";
    public static final String QUIZ_COLUMN_XANSWER2 = "xanswer2";

    public static final String TABLE_RESULTS = "results";
    public static final String RESULTS_COLUMN_ID = "_rid";
    public static final String RESULTS_COLUMN_QUESTION1 = "question1";
 //   public static final String RESULTS_COLUMN_ANSWER1 = "answer1";
    public static final String RESULTS_COLUMN_QUESTION2 = "question2";
//    public static final String RESULTS_COLUMN_ANSWER2 = "answer2";
    public static final String RESULTS_COLUMN_QUESTION3 = "question3";
//    public static final String RESULTS_COLUMN_ANSWER3 = "answer3";
    public static final String RESULTS_COLUMN_QUESTION4 = "question4";
//    public static final String RESULTS_COLUMN_ANSWER4 = "answer4";
    public static final String RESULTS_COLUMN_QUESTION5 = "question5";
 //   public static final String RESULTS_COLUMN_ANSWER5 = "answer5";
    public static final String RESULTS_COLUMN_QUESTION6 = "question6";
//    public static final String RESULTS_COLUMN_ANSWER6 = "answer6";
    public static final String RESULTS_NUM_OF_CORRECT = "score";
    public static final String RESULTS_NUM_OF_ANSWERED = "answered";
    public static final String RESULTS_DATETIME = "date_time";

   
    private static final String CREATE_RESULTS =
            "create table " + TABLE_RESULTS + " ("
            + RESULTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RESULTS_COLUMN_QUESTION1 + " TEXT, "
            + RESULTS_COLUMN_QUESTION2 + " TEXT, "
            + RESULTS_COLUMN_QUESTION3 + " TEXT, "
            + RESULTS_COLUMN_QUESTION4 + " TEXT, "
            + RESULTS_COLUMN_QUESTION5 + " TEXT, "
            + RESULTS_COLUMN_QUESTION6 + " TEXT, "
            + RESULTS_NUM_OF_CORRECT + " INTEGER, "
            + RESULTS_NUM_OF_ANSWERED + " INTEGER, "
            + RESULTS_DATETIME + " TEXT"
            + ")";

    // This is a reference to the only instance for the helper.
    private static QuizDBHelper helperInstance;

    // A Create table SQL statement to create a table for job leads.
    // Note that _id is an auto increment primary key, i.e. the database will
    // automatically generate unique id values as keys.
    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ + " ("
                    + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZ_COLUMN_QUESTION + " TEXT, "
                    + QUIZ_COLUMN_ANSWER + " TEXT, "
                    + QUIZ_COLUMN_XANSWER1 + " TEXT, "
                    + QUIZ_COLUMN_XANSWER2 + " TEXT"
                    + ")";

    // Note that the constructor is private!
    // So, it can be called only from
    // this class, in the getInstance method.
    private QuizDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    // Access method to the single instance of the class.
    // It is synchronized, so that only one thread can executes this method, at a time.
    public static synchronized QuizDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUIZ );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZ + " created" );
        db.execSQL( CREATE_RESULTS );
        Log.d( DEBUG_TAG, "Table " + TABLE_RESULTS + " created" );

    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("drop table if exists " + TABLE_QUIZ);
        onCreate(db);
        Log.d(DEBUG_TAG, "Table " + TABLE_QUIZ + " upgraded");
    }
}