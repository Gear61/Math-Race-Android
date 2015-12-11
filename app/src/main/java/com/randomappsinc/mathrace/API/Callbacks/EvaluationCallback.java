package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.Evaluation;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.Models.Events.EvaluationEvent;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class EvaluationCallback implements Callback<Evaluation> {
    private RunStory run;

    public EvaluationCallback(RunStory run) {
        this.run = run;
    }

    @Override
    public void onResponse(Response<Evaluation> response, Retrofit retrofit) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new EvaluationEvent(response.body()));
        }
        else {
            EventBus.getDefault().post(new EvaluationEvent(null));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        EventBus.getDefault().post(new EvaluationEvent(null));
    }
}