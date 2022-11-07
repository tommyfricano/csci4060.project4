package edu.uga.cs.statecapitalsquiz;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuizSwipeAdapter  extends FragmentStateAdapter {


    public QuizSwipeAdapter(FragmentManager fragmentManager, Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }

    @Override
    public Fragment createFragment(int position){
        if( position == 6 ){
            QuizQuestionsFragment temp = QuizQuestionsFragment.newInstance(position);
            QuizCompleteFragment completeFragment = QuizCompleteFragment.newInstance(position);
            completeFragment.setPoints(temp.getPoints());
            return completeFragment;
        }
        QuizQuestionsFragment temp = QuizQuestionsFragment.newInstance(position);
        QuizQuestionsFragment newFrag = QuizQuestionsFragment.newInstance(position);
        newFrag.setPoints(temp.getPoints());
        return newFrag;
    }

    @Override
    public int getItemCount() {
        return QuizQuestionsFragment.getNumberOfQuestions();
    }

}
