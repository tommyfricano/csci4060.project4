package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
This is the adapter used for viewing completed quizzes
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder>{
    public static final String DEBUG_TAG = "QuizRecyclerAdapter";

    private final Context context;

    private List<FullQuiz> values;
    private List<FullQuiz> originalValues;

    public QuizRecyclerAdapter(Context context, List<FullQuiz> quizList ) {
        this.context = context;
        this.values = quizList;
        Collections.reverse(this.values);
        this.originalValues = new ArrayList<FullQuiz>( quizList );
    }

    // reset the originalValues to the current contents of values
    public void sync()
    {
        originalValues = new ArrayList<FullQuiz>( values );
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    public static class QuizHolder extends RecyclerView.ViewHolder {

        TextView date_Time;
        TextView grade;
        TextView answered;

        public QuizHolder( View itemView ) {
            super( itemView );

            date_Time = itemView.findViewById( R.id.date_time);
            grade = itemView.findViewById( R.id.grade);
            answered = itemView.findViewById(R.id.answered);
        }
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz, parent, false );
        return new QuizHolder( view );
    }

    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {

        FullQuiz fullQuiz = values.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + fullQuiz );

        double grade = (fullQuiz.getScore() / 6) * 100.0;
        double gradeRound = (Math.round(grade * 100.0) / 100.0);
        holder.date_Time.setText("Date: " + fullQuiz.getDate_time());
        holder.grade.setText("Grade: " + String.valueOf(gradeRound));
        holder.answered.setText("Number of Answered: " + String.valueOf(fullQuiz.getAnswered()));
    }

    @Override
    public int getItemCount() {
        if( values != null )
            return values.size();
        else
            return 0;
    }
}
