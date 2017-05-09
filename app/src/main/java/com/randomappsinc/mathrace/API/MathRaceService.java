package com.randomappsinc.mathrace.API;

import com.randomappsinc.mathrace.API.Models.Evaluation;
import com.randomappsinc.mathrace.API.Models.LeadingRun;
import com.randomappsinc.mathrace.API.Models.RunStory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public interface MathRaceService {
    @GET("/getStories/{mode}/{runId}")
    Call<List<RunStory>> getStories(@Path("mode") String mode, @Path("runId") String runId);

    @GET("/leaderboard/{runType}")
    Call<List<LeadingRun>> getLeaderboard(@Path("runType") String runType);

    @POST("/addScore")
    Call<Evaluation> addScore(@Body RunStory run);
}
