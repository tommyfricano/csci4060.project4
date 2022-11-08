package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainScreen extends Fragment {
    private static final String TAG = "MainScreen";
    QuizData quizData;

    public MainScreen() {
        // Required empty public constructor
    }


    public static MainScreen newInstance() {
        MainScreen fragment = new MainScreen();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
      //  Log.d(TAG, "on create view");
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_main_screen, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );
     //   Log.d(TAG, "on view created");

        quizData = new QuizData(getActivity());

        // add if statement

        if (quizData.numOfRows() < 50) {
            quizData.open();
            try {
                InputStream in_s = getResources().getAssets().open("state_capitals.csv");
                CSVReader reader = new CSVReader(new InputStreamReader(in_s));
                String[] nextRow;

                while ((nextRow = reader.readNext()) != null) {
                    Quiz quiz = new Quiz(nextRow[0], nextRow[1], nextRow[2], nextRow[3]);
//                Log.d(TAG, nextRow[0]);
//                Log.d(TAG, nextRow[1]);
//                Log.d(TAG, nextRow[2]);
//                Log.d(TAG, nextRow[3]);
                    new QuizDBWriter().execute(quiz);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
            }
        }
    }

    public class QuizDBWriter extends AsyncTask<Quiz, Quiz> {
        @Override
        protected Quiz doInBackground( Quiz... quizzes ) {
            quizData.storeQuiz( quizzes[0] );
            return quizzes[0];
        }

        @Override
        protected void onPostExecute( Quiz quiz ) {
            // probably wrong message being shown
          /*  Toast.makeText( getContext(), "Quiz questions loaded",
                    Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public void onResume() {
        Log.d( TAG, "on resume" );
        super.onResume();
        // open the database in onResume
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( R.string.app_name );
    }

    // We need to save job leads into a file as the activity stops being a foreground activity
    @Override
    public void onPause() {
        Log.d( TAG, "onPause()" );
        super.onPause();
    }
}