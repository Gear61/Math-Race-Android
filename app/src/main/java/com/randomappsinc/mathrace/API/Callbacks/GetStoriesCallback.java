package com.randomappsinc.mathrace.API.Callbacks;

import com.randomappsinc.mathrace.API.ApiConstants;
import com.randomappsinc.mathrace.API.Models.RunStory;
import com.randomappsinc.mathrace.Models.Events.StoriesEvent;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class GetStoriesCallback implements Callback<List<RunStory>> {
    private String mode;

    public GetStoriesCallback(String mode) {
        this.mode = mode;
    }

    @Override
    public void onResponse(Response<List<RunStory>> response, Retrofit retrofit) {
        if (response.code() == ApiConstants.HTTP_STATUS_OK) {
            EventBus.getDefault().post(new StoriesEvent(mode, null));
        }
        else {
            EventBus.getDefault().post(new StoriesEvent(mode, null));
        }
    }

    @Override
    public void onFailure(Throwable t) {
        EventBus.getDefault().post(new StoriesEvent(mode, null));
    }
}
