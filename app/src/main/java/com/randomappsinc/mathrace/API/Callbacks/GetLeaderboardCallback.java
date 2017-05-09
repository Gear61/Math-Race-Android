package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.LeadingRun;
import com.randomappsinc.mathrace.Models.Events.LeaderboardEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class GetLeaderboardCallback implements Callback<List<LeadingRun>> {
    private String runType;

    public GetLeaderboardCallback(String runType) {
        this.runType = runType;
    }

    @Override
    public void onResponse(Call<List<LeadingRun>> call, Response<List<LeadingRun>> response) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new LeaderboardEvent(runType, response.body()));
        } else {
            EventBus.getDefault().post(new LeaderboardEvent(runType, null));
        }
    }

    @Override
    public void onFailure(Call<List<LeadingRun>> call, Throwable t) {
        EventBus.getDefault().post(new LeaderboardEvent(runType, null));
    }
}
