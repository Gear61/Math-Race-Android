package com.randomappsinc.mathrace.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class LeadingRun {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("num_wrong")
    @Expose
    private int numWrong;

    @SerializedName("user_tag")
    @Expose
    private String userTag;

    @SerializedName("num_correct")
    @Expose
    private int numCorrect;

    public int getId() {
        return id;
    }

    public int getNumWrong() {
        return numWrong;
    }

    public String getUserTag() {
        return userTag;
    }

    public int getNumCorrect() {
        return numCorrect;
    }
}