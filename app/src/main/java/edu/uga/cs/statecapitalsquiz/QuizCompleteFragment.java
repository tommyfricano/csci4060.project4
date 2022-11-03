package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuizCompleteFragment extends Fragment {

    private int questionNum;

    public static QuizCompleteFragment newInstance(int questionNum ) {
        QuizCompleteFragment fragment = new QuizCompleteFragment();
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
        return inflater.inflate(R.layout.fragment_quiz_completion, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        // todo grab questions and answers from db

        TextView titleView = view.findViewById( R.id.quizGrade );
        String grade = "80";
        titleView.setText( grade );
    }

}
