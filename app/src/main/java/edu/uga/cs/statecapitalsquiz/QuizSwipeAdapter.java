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
        QuizQuestionsFragment newFrag;
        if( position == 6 ){
            QuizQuestionsFragment temp = QuizQuestionsFragment.newInstance(position);
            QuizCompleteFragment completeFragment = QuizCompleteFragment.newInstance(position);
            completeFragment.setPoints(temp.getPoints());
            return completeFragment;
        }
        else if(position == 0){
            newFrag = QuizQuestionsFragment.newInstance(position);
            newFrag.setPoints(0);
        }
        else {
            QuizQuestionsFragment temp = QuizQuestionsFragment.newInstance(position);
            newFrag = QuizQuestionsFragment.newInstance(position);
            newFrag.setPoints(temp.getPoints());
        }
        return newFrag;
    }

    @Override
    public int getItemCount() {
        return QuizQuestionsFragment.getNumberOfQuestions();
    }

}
