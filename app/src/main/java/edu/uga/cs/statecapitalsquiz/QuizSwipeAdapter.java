package edu.uga.cs.statecapitalsquiz;

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
            return QuizCompleteFragment.newInstance( position );
        }
        return QuizQuestionsFragment.newInstance( position );
    }

    @Override
    public int getItemCount() {
        return QuizQuestionsFragment.getNumberOfQuestions();
    }
}
