package com.randomappsinc.mathrace.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.randomappsinc.mathrace.Models.StatsBundle;
import com.randomappsinc.mathrace.Persistence.Database.DatabaseManager;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/15/15.
 */
public class MyStatsFragment extends Fragment {
    @Bind(R.id.total_runs) TextView totalRuns;
    @Bind(R.id.total_questions_attempted) TextView totalQuestions;
    @Bind(R.id.total_correct) TextView totalCorrect;
    @Bind(R.id.total_wrong) TextView totalWrong;
    @Bind(R.id.overall_percentage_correct) TextView overallPercentageCorrect;
    @Bind(R.id.average_time_taken) TextView averageTimeTaken;
    @Bind(R.id.best_correct) TextView bestCorrect;
    @Bind(R.id.best_wrong) TextView bestWrong;
    @Bind(R.id.worst_correct) TextView worstCorrect;
    @Bind(R.id.worst_wrong) TextView worstWrong;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.my_stats, container, false);
        ButterKnife.bind(this, rootView);

        String runType = getArguments().getString(Constants.RUN_TYPE_KEY);
        StatsBundle statsBundle = DatabaseManager.get().getStatsBundle(runType);
        totalRuns.setText(String.valueOf(statsBundle.getNumTotalRuns()));
        totalQuestions.setText(String.valueOf(statsBundle.getTotalQuestionsAnswered()));
        totalCorrect.setText(String.valueOf(statsBundle.getNumTotalCorrect()));
        totalWrong.setText(String.valueOf(statsBundle.getNumTotalWrong()));
        overallPercentageCorrect.setText(statsBundle.getOverallPercentageCorrect());
        averageTimeTaken.setText(statsBundle.getAverageTimeTaken());
        bestCorrect.setText(String.valueOf(statsBundle.getBestRun().getNumCorrect()));
        bestWrong.setText(String.valueOf(statsBundle.getBestRun().getNumWrong()));
        worstCorrect.setText(String.valueOf(statsBundle.getWorstRun().getNumCorrect()));
        worstWrong.setText(String.valueOf(statsBundle.getWorstRun().getNumWrong()));

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
