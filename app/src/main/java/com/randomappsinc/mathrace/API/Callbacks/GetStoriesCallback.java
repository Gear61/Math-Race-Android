package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.Models.Events.StoriesEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class GetStoriesCallback implements Callback<List<RunStory>> {
    private String mode;

    public GetStoriesCallback(String mode) {
        this.mode = mode;
    }

    @Override
    public void onResponse(Call<List<RunStory>> call, Response<List<RunStory>> response) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new StoriesEvent(mode, response.body()));
        } else {
            EventBus.getDefault().post(new StoriesEvent(mode, null));
        }
    }

    @Override
    public void onFailure(Call<List<RunStory>> call, Throwable t) {
        EventBus.getDefault().post(new StoriesEvent(mode, null));
    }
}
