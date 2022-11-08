package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FullQuiz {

    public static final String TAG = "FullQuiz";
    private long id;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private double score;
    private int answered;
    private String date_time;

    public double getScore() {
        return score;
    }

    public int getAnswered() {
        return answered;
    }

    public String getDate_time() {
        return date_time;
    }

    public FullQuiz(){}

    public FullQuiz(double score, int answered, String time) {
        this.score = score;
        this.answered =answered;
        this.date_time = time;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setQuestion1(String question) {
        this.question1 = question;
    }
    public void setQuestion2(String question) {
        this.question2 = question;
    }
    public void setQuestion3(String question) {
        this.question3 = question;
    }
    public void setQuestion4(String question) {
        this.question4 = question;
    }
    public void setQuestion5(String question) {
        this.question5 = question;
    }
    public void setQuestion6(String question) {
        this.question6 = question;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setAnswered(int answered) {
        this.answered = answered;
    }
    public void setDate_time(String time) {
        this.date_time = time;
    }
    public ContentValues input() {
        ContentValues values = new ContentValues();
        //  values.put(QuizDBHelper.RESULTS_COLUMN_ID, this.id);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION1, this.question1);
        System.out.println(this.question1);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION2, this.question2);
        System.out.println(this.question2);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION3, this.question3);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION4, this.question4);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION5, this.question5);
        values.put(QuizDBHelper.RESULTS_COLUMN_QUESTION6, this.question6);
        values.put(QuizDBHelper.RESULTS_NUM_OF_ANSWERED, this.answered);
        values.put(QuizDBHelper.RESULTS_NUM_OF_CORRECT, this.score);
        values.put(QuizDBHelper.RESULTS_DATETIME, this.date_time);
        return values;
    }
}
