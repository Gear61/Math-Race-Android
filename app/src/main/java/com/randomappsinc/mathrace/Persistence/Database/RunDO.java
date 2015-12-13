package com.randomappsinc.mathrace.Persistence.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by alexanderchiou on 12/13/15.
 */
public class RunDO extends RealmObject {
    @PrimaryKey
    private int id;

    private String runType;
    private int numCorrect;
    private int numWrong;
    private long timeOccurred;
    private int rank;
    private int numTotalRuns;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumWrong() {
        return numWrong;
    }

    public void setNumWrong(int numWrong) {
        this.numWrong = numWrong;
    }

    public long getTimeOccurred() {
        return timeOccurred;
    }

    public void setTimeOccurred(long timeOccurred) {
        this.timeOccurred = timeOccurred;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNumTotalRuns() {
        return numTotalRuns;
    }

    public void setNumTotalRuns(int numTotalRuns) {
        this.numTotalRuns = numTotalRuns;
    }
}
