package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartNewQuizFragment extends Fragment {

    static QuizData quizData;
    static List<Quiz> quiz;

    public static StartNewQuizFragment newInstance() {
        StartNewQuizFragment fragment = new StartNewQuizFragment();
        Bundle args = new Bundle();
        args.putSerializable( "quizData", (Serializable) quiz);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 pager = view.findViewById( R.id.viewpager2 );
        quizData = new QuizData(getActivity());
        quiz = quizData.getQuiz();

        QuizSwipeAdapter avpAdapter = new QuizSwipeAdapter(
                getActivity().getSupportFragmentManager(), getLifecycle() );

        pager.setOrientation( ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );
    }

    public List<Quiz> getData() {
        return quiz;
    }

}
