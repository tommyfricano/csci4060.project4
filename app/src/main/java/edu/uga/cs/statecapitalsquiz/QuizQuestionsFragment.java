package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class QuizQuestionsFragment extends Fragment {

    private static final String TAG = "questions";
    private static final int questions = 7;
    private int questionNum;
    QuizData quizData = null;
    List<Quiz> quiz = null;
    private static Double points = 0.0;
    public static QuizCompleteFragment completeFragment;
    ContentValues values;

    TextView titleView;
    TextView question;
    RadioGroup radioGroup;
    RadioButton btn1;
    RadioButton btn2;
    RadioButton btn3;


    public static QuizQuestionsFragment newInstance(int questionNum) {
        QuizQuestionsFragment fragment = new QuizQuestionsFragment();
        completeFragment = new QuizCompleteFragment();
        Bundle args = new Bundle();
        args.putInt( "questionNum", questionNum );
        args.putDouble("points", points);
//        Log.d(TAG, String.valueOf(points));
  //      completeFragment.setArguments(args);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
//            points = StartNewQuizFragment.newInstance().getArguments().getDouble("points");
//            points = getArguments().getDouble("points");
            quiz = (List<Quiz>) StartNewQuizFragment.newInstance().getArguments().getSerializable("quizData");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        Log.d(TAG, String.valueOf(points));
   //     Log.d(TAG, "onViewCreated");

        quizData = new QuizData(getActivity());
        quizData.open();
//        List<Quiz> quiz = (List<Quiz>) StartNewQuizFragment.newInstance().getArguments().getSerializable("quizData");
        quizData.close();
        titleView = view.findViewById( R.id.questionNumber );
        question = view.findViewById(R.id.question);
        radioGroup = view.findViewById(R.id.radioGroup);


        String title = getString(R.string.questionNumber) + " " + (questionNum+1);
        titleView.setText( title );
        question.setText("What is the capital of " + quiz.get(questionNum).getQuestion() + "?");

        List<Integer> btns = new ArrayList<>();
        btns.add(R.id.radioButton3);
        btns.add(R.id.radioButton2);
        btns.add(R.id.radioButton);
        Collections.shuffle(btns);

        btn1 = view.findViewById(btns.get(2));
        btn2 = view.findViewById(btns.get(0));
        btn3 = view.findViewById(btns.get(1));
        btn1.setText(quiz.get(questionNum).getXanswer1());
        btn2.setText(quiz.get(questionNum).getXanswer2());
        btn3.setText(quiz.get(questionNum).getAnswer());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            int checked = 0;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d("chk", "id" + checkedId);


                if (checkedId == btn1.getId() && quiz.get(questionNum).getAnswer().equals(btn1.getText())) {
                    //R.id.a = RadioButton ID in layout
                    if(checked < 1) {
                        points = points + 1.0;
                    }
                    checked++;
                }
                else if (checkedId == btn2.getId() && quiz.get(questionNum).getAnswer().equals(btn2.getText())) {
                    if(checked < 1) {
                        points = points + 1.0;
                    }
                    checked++;
                }
                else if (checkedId == btn3.getId() && quiz.get(questionNum).getAnswer().equals(btn3.getText())) {
                    if(checked < 1) {
                        points = points + 1.0;
                    }
                    checked++;
                }
                else{
                    if(checked > 1){
                        points --;
                    }
                }

            }
        });


    }

    public static int getNumberOfQuestions() {
        return questions;
    }

    public class QuizDBReader extends AsyncTask<Quiz, List<Quiz>> {

        @Override
        protected List<Quiz> doInBackground(Quiz... quizzes) {
            return quizData.getQuiz();
        }

        @Override
        protected void onPostExecute(List<Quiz> quiz ) {
            Toast.makeText( getContext(), "Quiz questions loaded",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        Log.d( TAG, "on resume" );
        super.onResume();
        quizData.open();
        // open the database in onResume
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( R.string.app_name );
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
//        todo save quiz before closing
        quizData.storeCompleteQuiz(quiz.get(questionNum), questionNum + 1);
        quizData.close();
        System.out.println(points);
        Log.d( TAG, "onPause()" );
        super.onPause();
    }
    public double getPoints() {
        return this.points;
    }
    public void setPoints(double score) {
        this.points = score;
    }
}
