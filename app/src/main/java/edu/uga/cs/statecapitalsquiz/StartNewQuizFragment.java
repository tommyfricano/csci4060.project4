package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StartNewQuizFragment extends Fragment {

    public StartNewQuizFragment() {}

    public static StartNewQuizFragment newInstance() {
        StartNewQuizFragment fragment = new StartNewQuizFragment();
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

    }
}
