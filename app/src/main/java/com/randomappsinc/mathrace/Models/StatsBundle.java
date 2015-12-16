package com.randomappsinc.mathrace.Models;

import com.randomappsinc.mathrace.Persistence.Database.RunDO;

/**
 * Created by alexanderchiou on 12/15/15.
 */
public class StatsBundle {
    private int numTotalRuns;
    private double totalQuestionsCorrect;
    private double numTotalCorrect;
    private double numTotalWrong;
    private double overallPercentageCorrect;
    private double averageTimeTaken;
    private RunDO bestRun;
    private RunDO worstRun;

    public int getNumTotalRuns() {
        return numTotalRuns;
    }

    public void setNumTotalRuns(int numTotalRuns) {
        this.numTotalRuns = numTotalRuns;
    }

    public double getTotalQuestionsCorrect() {
        return totalQuestionsCorrect;
    }

    public void setTotalQuestionsCorrect(double totalQuestionsCorrect) {
        this.totalQuestionsCorrect = totalQuestionsCorrect;
    }

    public double getNumTotalCorrect() {
        return numTotalCorrect;
    }

    public void setNumTotalCorrect(double numTotalCorrect) {
        this.numTotalCorrect = numTotalCorrect;
    }

    public double getNumTotalWrong() {
        return numTotalWrong;
    }

    public void setNumTotalWrong(double numTotalWrong) {
        this.numTotalWrong = numTotalWrong;
    }

    public double getOverallPercentageCorrect() {
        return overallPercentageCorrect;
    }

    public void setOverallPercentageCorrect(double overallPercentageCorrect) {
        this.overallPercentageCorrect = overallPercentageCorrect;
    }

    public double getAverageTimeTaken() {
        return averageTimeTaken;
    }

    public void setAverageTimeTaken(double averageTimeTaken) {
        this.averageTimeTaken = averageTimeTaken;
    }

    public RunDO getBestRun() {
        return bestRun;
    }

    public void setBestRun(RunDO bestRun) {
        this.bestRun = bestRun;
    }

    public RunDO getWorstRun() {
        return worstRun;
    }

    public void setWorstRun(RunDO worstRun) {
        this.worstRun = worstRun;
    }
}
