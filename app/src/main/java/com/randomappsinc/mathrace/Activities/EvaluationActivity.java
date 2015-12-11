package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;

import com.randomappsinc.mathrace.API.Callbacks.EvaluationCallback;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.API.RestClient;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;

import butterknife.ButterKnife;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class EvaluationActivity extends StandardActivity {
    private RunStory run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        run = getIntent().getParcelableExtra(Constants.RUN_KEY);
        EvaluationCallback callback = new EvaluationCallback(run);
        RestClient.getInstance().getMathRaceService().addScore(run).enqueue(callback);
    }
}
