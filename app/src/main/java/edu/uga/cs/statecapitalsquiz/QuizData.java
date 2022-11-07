package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is facilitates storing and restoring job leads stored.
 */
public class QuizData {

    public static final String DEBUG_TAG = "QuizData";

    // this is a reference to our database; it is used later to run SQL commands
    private SQLiteDatabase db;
    public QuizDBHelper quizDbHelper;
    private List<Quiz> quiz;
    private static final String[] allColumns = {
            QuizDBHelper.QUIZ_COLUMN_ID,
            QuizDBHelper.QUIZ_COLUMN_QUESTION,
            QuizDBHelper.QUIZ_COLUMN_ANSWER,
            QuizDBHelper.QUIZ_COLUMN_XANSWER1,
            QuizDBHelper.QUIZ_COLUMN_XANSWER2
    };

    public QuizData(Context context) {
        this.quizDbHelper = QuizDBHelper.getInstance(context);
    }

    // Open the database
    public void open() {
        db = quizDbHelper.getWritableDatabase();
        Log.d(DEBUG_TAG, "QuizData: db open");
    }

    // Close the database
    public void close() {
        if (quizDbHelper != null) {
            quizDbHelper.close();
            Log.d(DEBUG_TAG, "QuizData: db closed");
        }
    }

    public boolean isDBOpen() {
        return db.isOpen();
    }

    public long numOfRows() {
        db = quizDbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "quiz");
        db.close();
        return count;
    }

    public Quiz storeQuiz(Quiz quiz) {

        // Prepare the values for all of the necessary columns in the table
        // and set their values to the variables of the JobLead argument.
        // This is how we are providing persistence to a JobLead (Java object) instance
        // by storing it as a new row in the database table representing job leads.
        ContentValues values = new ContentValues();
        values.put(QuizDBHelper.QUIZ_COLUMN_QUESTION, quiz.getQuestion());
        values.put(QuizDBHelper.QUIZ_COLUMN_ANSWER, quiz.getAnswer());
        values.put(QuizDBHelper.QUIZ_COLUMN_XANSWER1, quiz.getXanswer1());
        values.put(QuizDBHelper.QUIZ_COLUMN_XANSWER2, quiz.getXanswer2());

        // Insert the new row into the database table;
        // The id (primary key) is automatically generated by the database system
        // and returned as from the insert method call.
        this.open();
        long id = db.insert(QuizDBHelper.TABLE_QUIZ, null, values);

        // store the id (the primary key) in the JobLead instance, as it is now persistent
        quiz.setId(id);

        Log.d(DEBUG_TAG, "Stored new quiz with id: " + String.valueOf(quiz.getId()));

        return quiz;
    }

    public List<Quiz> getQuiz() {

        List<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;
        this.open();


        try {
            // Execute the select query and get the Cursor to iterate over the retrieved rows
            cursor = db.query(QuizDBHelper.TABLE_QUIZ, allColumns,
                    null, null, null, null, null);

            // collect all job leads into a List
            if (cursor != null && cursor.getCount() > 0) {

                Random r = new Random();
                int [] picked = new int[6];
                boolean distinct;
                for (int i = 0; i < 6; i++) {
                    distinct = false;
                    picked[i] = r.nextInt(50);
                    while (!distinct) {
                        distinct = true;
                        for (int j = 0; j < i; j++) {
                            if (picked[i] == picked[j]) {
                                distinct = false;
                                picked[i] = r.nextInt(50);
                            }
                        }
                    }
                }
                    for (int i = 0; i < 6; i++) {
                        Log.d(DEBUG_TAG, "" + picked[i]);
                        cursor.moveToPosition(picked[i]);
                        // get all attribute values of this job lead
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_ID);
                        long id = cursor.getLong(columnIndex);
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_QUESTION);
                        String question = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_ANSWER);
                        String answer = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_XANSWER1);
                        String xAnswer1 = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex(QuizDBHelper.QUIZ_COLUMN_XANSWER2);
                        String xAnswer2 = cursor.getString(columnIndex);

                        // create a new JobLead object and set its state to the retrieved values
                        Quiz q = new Quiz(question, answer, xAnswer1, xAnswer2);
                        q.setId(id); // set the id (the primary key) of this object
                        // add it to the list
                        quizzes.add(q);
                        Log.d(DEBUG_TAG, "Quiz loaded: " + q.getQuestion());
                    }
                }
            } catch (Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        } finally {
            // we should close the cursor
            if (cursor != null) {
                cursor.close();
            }
        }
//        Log.d(DEBUG_TAG, String.valueOf(quizzes));
        quiz = new ArrayList<>(quizzes);
        Log.d(DEBUG_TAG, String.valueOf(quiz));
        return quizzes;
    }

    public List<Quiz> getList() {
        Log.d(DEBUG_TAG, String.valueOf(quiz));
        return quiz;
    }
}