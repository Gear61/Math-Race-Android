package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.LeadingRun;
import com.randomappsinc.mathrace.Models.Events.LeaderboardEvent;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class GetLeaderboardCallback implements Callback<List<LeadingRun>> {
    private String runType;

    public GetLeaderboardCallback(String runType) {
        this.runType = runType;
    }

    @Override
    public void onResponse(Response<List<LeadingRun>> response, Retrofit retrofit) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new LeaderboardEvent(runType, response.body()));
        }
        else {
            EventBus.getDefault().post(new LeaderboardEvent(runType, null));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        EventBus.getDefault().post(new LeaderboardEvent(runType, null));
    }
}
