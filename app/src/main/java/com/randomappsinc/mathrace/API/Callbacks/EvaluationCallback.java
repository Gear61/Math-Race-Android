package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.Evaluation;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.Models.Events.EvaluationEvent;
import com.randomappsinc.mathrace.Persistence.Database.DatabaseManager;
import com.randomappsinc.mathrace.Persistence.Database.RunDO;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class EvaluationCallback implements Callback<Evaluation> {
    private RunStory run;

    public EvaluationCallback(RunStory run) {
        this.run = run;
    }

    @Override
    public void onResponse(Call<Evaluation> call, Response<Evaluation> response) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new EvaluationEvent(response.body()));
            RunDO runDO = new RunDO();
            runDO.setId(response.body().getId());
            runDO.setRunType(run.getRunType());
            runDO.setNumCorrect(run.getNumCorrect());
            runDO.setNumWrong(run.getNumWrong());
            runDO.setTimeOccurred(response.body().getTimeOccured());
            runDO.setRank(response.body().getNumSuperiorRuns() + 1);
            runDO.setNumTotalRuns(response.body().getNumTotalRuns());
            DatabaseManager.get().addRun(runDO);
        } else {
            EventBus.getDefault().post(new EvaluationEvent(null));
        }
    }

    @Override
    public void onFailure(Call<Evaluation> call, Throwable t) {
        EventBus.getDefault().post(new EvaluationEvent(null));
    }
}