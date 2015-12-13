package com.randomappsinc.mathrace.API.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class Evaluation {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("num_superior_runs")
    @Expose
    private int numSuperiorRuns;

    @SerializedName("num_total_runs")
    @Expose
    private int numTotalRuns;

    @SerializedName("time_occurred")
    @Expose
    private long timeOccured;

    public int getId() {
        return id;
    }

    public int getNumSuperiorRuns() {
        return numSuperiorRuns;
    }

    public int getNumTotalRuns() {
        return numTotalRuns;
    }

    public long getTimeOccured() {
        return timeOccured;
    }
}
