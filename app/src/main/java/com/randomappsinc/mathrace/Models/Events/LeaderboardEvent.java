package com.randomappsinc.mathrace.Models.Events;

import com.randomappsinc.mathrace.API.Models.LeadingRun;

import java.util.List;

/**
 * Created by alexanderchiou on 12/8/15.
 */
public class LeaderboardEvent {
    private String runType;
    private List<LeadingRun> leadingRuns;

    public LeaderboardEvent(String runType, List<LeadingRun> leadingRuns) {
        this.runType = runType;
        this.leadingRuns = leadingRuns;
    }

    public String getRunType() {
        return runType;
    }

    public List<LeadingRun> getLeadingRuns() {
        return leadingRuns;
    }
}
