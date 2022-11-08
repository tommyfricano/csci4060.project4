package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
fragment for recyclerview for fullQuizzes
 */
public class ReviewQuizzesFragment extends Fragment {

    private static final String TAG = "ReviewQuizzesFragment";

    private QuizData quizData = null;
    private List<FullQuiz> quizList;

    private RecyclerView recyclerView;
    private QuizRecyclerAdapter recyclerAdapter;

    public ReviewQuizzesFragment() {
        // Required empty public constructor
    }

    public static ReviewQuizzesFragment newInstance() {
        ReviewQuizzesFragment fragment = new ReviewQuizzesFragment();
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // Enable the search menu population.
        // When the parameter of this method is true, Android will call onCreateOptionsMenu on
        // this fragment, when the options menu is being built for the hosting activity.
        setHasOptionsMenu( true );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_review_quizzes, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        recyclerView = getView().findViewById( R.id.recyclerView );

        // use a linear layout manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity() );

        recyclerView.setLayoutManager( layoutManager );

        quizList = new ArrayList<FullQuiz>();

        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        quizData = new QuizData( getActivity() );

        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        quizData.open();

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        new QuizDBReader().execute();
    }

    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class QuizDBReader extends AsyncTask<Void, List<FullQuiz>> {
        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<FullQuiz> doInBackground( Void... params ) {
            List<FullQuiz> quizList = quizData.retrieveAllQuizzes();
//            Log.d( TAG, "JobLeadDBReader: Job leads retrieved: " + jobLeadsList.size() );

            return quizList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( List<FullQuiz> quizList ) {
            Log.d( TAG, "QuizDBReader: quizList.size(): " + quizList.size() );

            // create the RecyclerAdapter and set it for the RecyclerView
            recyclerAdapter = new QuizRecyclerAdapter( getActivity(), quizList );
            Log.d(TAG, "Recycler Adapter added");
            recyclerView.setAdapter( recyclerAdapter );

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Open the database
        if( quizData != null && !quizData.isDBOpen() ) {
            quizData.open();
            Log.d( TAG, "ReviewQuizzesFragment.onResume(): opening DB" );
        }

        // Update the app name in the Action Bar to be the same as the app's name
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( getResources().getString( R.string.app_name ) );
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
        super.onPause();

        // close the database in onPause
        if( quizData != null ) {
            quizData.close();
            Log.d( TAG, "ReviewQuizzesFragment.onPause(): closing DB" );
        }
    }
}
