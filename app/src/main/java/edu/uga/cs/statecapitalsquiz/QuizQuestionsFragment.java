package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionsFragment extends Fragment {

    private static final String TAG = "questions";
    private static final int questions = 6;
    private int questionNum;
    QuizData quizData = null;


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
        // todo grab questions and answers from db
        quizData.open();
        List<Quiz> quiz = quizData.getQuiz();
        quizData.close();
        TextView titleView = view.findViewById( R.id.questionNumber );
        TextView question = view.findViewById(R.id.question);
        RadioButton btn1 = view.findViewById(R.id.radioButton);
        RadioButton btn2 = view.findViewById(R.id.radioButton2);
        RadioButton btn3 = view.findViewById(R.id.radioButton3);

        String title = getString(R.string.questionNumber) + " "  + (questionNum+1);
        titleView.setText( title );
//        Log.d(TAG, String.valueOf(quiz));
        question.setText(quiz.get(questionNum).getQuestion());
        btn1.setText(quiz.get(questionNum).getXanswer1());
        btn2.setText(quiz.get(questionNum).getXanswer2());
        btn3.setText(quiz.get(questionNum).getAnswer());
    }

    public static int getNumberOfQuestions() {
        return questions;
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
