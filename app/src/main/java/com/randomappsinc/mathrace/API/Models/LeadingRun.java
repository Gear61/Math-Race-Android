package com.randomappsinc.mathrace.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class LeadingRun {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("num_wrong")
    @Expose
    private Integer numWrong;

    @SerializedName("user_tag")
    @Expose
    private String userTag;

    @SerializedName("num_correct")
    @Expose
    private Integer numCorrect;

    public Integer getId() {
        return id;
    }

    public Integer getNumWrong() {
        return numWrong;
    }

    public String getUserTag() {
        return userTag;
    }

    public Integer getNumCorrect() {
        return numCorrect;
    }
}