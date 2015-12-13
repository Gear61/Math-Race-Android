package com.randomappsinc.mathrace.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.randomappsinc.mathrace.API.Callbacks.EvaluationCallback;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.API.RestClient;
import com.randomappsinc.mathrace.Models.Events.EvaluationEvent;
import com.randomappsinc.mathrace.R;
import com.randomappsinc.mathrace.Utils.Constants;
import com.randomappsinc.mathrace.Utils.RaceUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class EvaluationActivity extends StandardActivity {
    @Bind(R.id.loading) View loading;
    @Bind(R.id.evaluation_summary) View summary;
    @Bind(R.id.rank) TextView rank;
    @Bind(R.id.percentile) TextView percentile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RunStory run = getIntent().getParcelableExtra(Constants.RUN_KEY);
        EvaluationCallback callback = new EvaluationCallback(run);
        RestClient.getInstance().getMathRaceService().addScore(run).enqueue(callback);
    }

    public void onEvent(EvaluationEvent event) {
        loading.setVisibility(View.GONE);
        if (event.getEvaluation() != null) {
            String rankText = RaceUtils.generateRank(event.getEvaluation().getNumSuperiorRuns())
                    + getString(R.string.out_of)
                    + String.valueOf(event.getEvaluation().getNumTotalRuns());
            rank.setText(rankText);
            percentile.setText(RaceUtils.generatePercentile(event.getEvaluation().getNumSuperiorRuns() + 1,
                    event.getEvaluation().getNumTotalRuns()));
            summary.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
