package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class QuizCompleteFragment extends Fragment {

    private static final String TAG = "complete";

    TextView titleView;
    private int questionNum;
    private static Double points;


    public static QuizCompleteFragment newInstance(int questionNum) {
        QuizCompleteFragment fragment = new QuizCompleteFragment();
        Bundle args = new Bundle();
//        args.putInt( "questionNum", questionNum );
//        args.putDouble("points", points);
 //       fragment.setArguments( args );

        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
            points = this.getArguments().getDouble("points");
    //        points = QuizSwipeAdapter.getFrag().points;
            Log.d(TAG, "" + this.getArguments().getDouble("points"));
            Log.d(TAG, String.valueOf(points));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
    //    points = savedInstanceState.getDouble("points");
        return inflater.inflate(R.layout.fragment_quiz_completion, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
        // todo calculate grade and save
        QuizQuestionsFragment temp = QuizQuestionsFragment.newInstance(6);
        this.points = temp.getPoints();
        Log.d(TAG, "onViewCreated()");
        TextView titleView = view.findViewById( R.id.quizGrade );
        Double gradeCalc = this.points/6.0;
        gradeCalc = gradeCalc * 100;
        double gradeRound = Math.round(gradeCalc * 100.0) / 100.0;
        String grade = String.valueOf(gradeRound);
        grade += "%";
        titleView.setText( grade );
    }

    public void setPoints(double score) {
        this.points = score;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    public double getPoints() {
        return this.points;
    }
}
