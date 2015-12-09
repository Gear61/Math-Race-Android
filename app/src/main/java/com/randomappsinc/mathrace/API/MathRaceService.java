package com.randomappsinc.mathrace.API;

import com.randomappsinc.mathrace.API.Models.LeadingRun;
import com.randomappsinc.mathrace.API.Models.RunStory;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public interface MathRaceService {
    @GET("/getStories/{mode}/{runId}")
    Call<List<RunStory>> getStories(@Path("mode") String mode, @Path("runId") String runId);

    @GET("/leaderboard/{runType}")
    Call<List<LeadingRun>> getLeaderboard(@Path("runType") String runType);
}
