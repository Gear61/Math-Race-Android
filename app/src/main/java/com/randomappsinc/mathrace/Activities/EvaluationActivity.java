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
    @Bind(R.id.evaluation_pre_rank) TextView preRank;
    @Bind(R.id.rank) TextView rank;
    @Bind(R.id.percentage) TextView percentage;

    private RunStory run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        run = getIntent().getParcelableExtra(Constants.RUN_KEY);
        EvaluationCallback callback = new EvaluationCallback(run);
        RestClient.getInstance().getMathRaceService().addScore(run).enqueue(callback);
    }

    public void onEvent(EvaluationEvent event) {
        loading.setVisibility(View.GONE);
        if (event.getEvaluation() != null) {
            String preRankText = getString(R.string.evaluation_part_1)
                    + String.valueOf(event.getEvaluation().getNumTotalRuns())
                    + getString(R.string.evaluation_part_2)
                    + run.getRunType()
                    + getString(R.string.evaluation_part_3);
            preRank.setText(preRankText);
            rank.setText(RaceUtils.generateRank(event.getEvaluation().getNumSuperiorRuns()));
            String percentageText = getString(R.string.evaluation_part_4)
                    + RaceUtils.generatePercentage(event.getEvaluation().getNumSuperiorRuns() + 1,
                    event.getEvaluation().getNumTotalRuns())
                    + getString(R.string.evaluation_part_5);
            percentage.setText(percentageText);
            summary.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
