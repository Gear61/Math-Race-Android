package com.randomappsinc.mathrace.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/7/15.
 */
public class RunStory {
    @SerializedName("num_wrong")
    @Expose
    private Integer numWrong;

    @SerializedName("user_tag")
    @Expose
    private String userTag;

    @SerializedName("num_correct")
    @Expose
    private Integer numCorrect;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("run_type")
    @Expose
    private String runType;

    @SerializedName("time_occurred")
    @Expose
    private Long timeOccurred;

    public Integer getNumWrong() {
        return numWrong;
    }

    public String getUserTag() {
        return userTag;
    }

    public Integer getNumCorrect() {
        return numCorrect;
    }

    public Integer getId() {
        return id;
    }

    public String getRunType() {
        return runType;
    }

    public Long getTimeOccurred() {
        return timeOccurred;
    }
}
