package com.randomappsinc.mathrace.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class RunStory {
    @SerializedName("num_wrong")
    @Expose
    private int numWrong;

    @SerializedName("user_tag")
    @Expose
    private String userTag;

    @SerializedName("num_correct")
    @Expose
    private int numCorrect;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("run_type")
    @Expose
    private String runType;

    @SerializedName("time_occurred")
    @Expose
    private long timeOccurred;

    public int getNumWrong() {
        return numWrong;
    }

    public String getUserTag() {
        return userTag;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public int getId() {
        return id;
    }

    public String getRunType() {
        return runType;
    }

    public long getTimeOccurred() {
        return timeOccurred;
    }
}
