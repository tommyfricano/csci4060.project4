package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.io.Serializable;
import java.util.List;

public class StartNewQuizFragment extends Fragment {

    private static final String TAG = "start";

    ViewPager2 viewPager2;
    static QuizData quizData;
    static List<Quiz> quiz;
    static Double points;
    private QuizCompleteFragment fin;

    public static StartNewQuizFragment newInstance() {
        StartNewQuizFragment fragment = new StartNewQuizFragment();
        points = 0.0;
        Bundle args = new Bundle();
        args.putSerializable( "quizData", (Serializable) quiz);
        args.putDouble("points", points);
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        points = 0.0;
        if( getArguments() != null ) {
        }
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
        points = 0.0;

        QuizSwipeAdapter avpAdapter = new QuizSwipeAdapter(
                getActivity().getSupportFragmentManager(), getLifecycle() );

        viewPager2 = view.findViewById(R.id.viewpager2);

        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            int previousPage = 0;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(position < previousPage){
                    pager.setCurrentItem(previousPage, false);
                } else {
                    previousPage = position;
                }
            }
        });

        pager.setOrientation( ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( avpAdapter );

    }

    @Override
    public void onResume() {
//        Log.d( TAG, "on resume" );
        super.onResume();
        points = 0.0;
    }

}
