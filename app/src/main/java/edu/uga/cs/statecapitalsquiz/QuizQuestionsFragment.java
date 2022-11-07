package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizQuestionsFragment extends Fragment {

    private static final String TAG = "questions";
    private static final int questions = 6;
    private int questionNum;
    QuizData quizData = null;
    List<Quiz> quiz = null;
    StartNewQuizFragment startNewQuizFragment;

    public static QuizQuestionsFragment newInstance(int questionNum ) {
        QuizQuestionsFragment fragment = new QuizQuestionsFragment();
        Bundle args = new Bundle();
        args.putInt( "questionNum", questionNum );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
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
        quizData = new QuizData(getActivity());
        quizData.open();
        List<Quiz> quiz = (List<Quiz>) StartNewQuizFragment.newInstance().getArguments().getSerializable("quizData");
        Log.d(TAG, String.valueOf(quiz));
        quizData.close();
        TextView titleView = view.findViewById( R.id.questionNumber );
        TextView question = view.findViewById(R.id.question);


        String title = getString(R.string.questionNumber) + " " + (questionNum+1);
        titleView.setText( title );
        question.setText(quiz.get(questionNum).getQuestion());

        int [] btns = {R.id.radioButton3, R.id.radioButton2, R.id.radioButton};
        Collections.shuffle(Arrays.asList(btns));
        RadioButton btn1 = view.findViewById(btns[0]);
        RadioButton btn2 = view.findViewById(btns[1]);
        RadioButton btn3 = view.findViewById(btns[2]);
        btn1.setText(quiz.get(questionNum).getXanswer1());
        btn2.setText(quiz.get(questionNum).getXanswer2());
        btn3.setText(quiz.get(questionNum).getAnswer());

        if(btn1.isChecked() && quiz.get(questionNum).getAnswer() == btn1.getText()){
//            save answer in db

        }
        else if(btn2.isChecked() && quiz.get(questionNum).getAnswer() == btn2.getText()) {

        }
        else if(btn3.isChecked() && quiz.get(questionNum).getAnswer() == btn3.getText()){

        }
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
//        Log.d( TAG, "on resume" );
        super.onResume();
        quizData.open();
        // open the database in onResume
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( R.string.app_name );
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
//        todo save quiz before closing
        quizData.close();
//        Log.d( TAG, "onPause()" );
        super.onPause();
    }

}
