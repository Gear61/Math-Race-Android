package com.randomappsinc.mathrace.Models;

import com.randomappsinc.mathrace.Persistence.Database.RunDO;

/**
 * Created by alexanderchiou on 12/15/15.
 */
public class StatsBundle {
    private int numTotalRuns;
    private int totalQuestionsAnswered;
    private int numTotalCorrect;
    private int numTotalWrong;
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

    public int getTotalQuestionsAnswered() {
        return totalQuestionsAnswered;
    }

    public void setTotalQuestionsAnswered(double totalQuestionsAnswered) {
        this.totalQuestionsAnswered = (int) totalQuestionsAnswered;
    }

    public int getNumTotalCorrect() {
        return numTotalCorrect;
    }

    public void setNumTotalCorrect(double numTotalCorrect) {
        this.numTotalCorrect = (int) numTotalCorrect;
    }

    public int getNumTotalWrong() {
        return numTotalWrong;
    }

    public void setNumTotalWrong(double numTotalWrong) {
        this.numTotalWrong =  (int) numTotalWrong;
    }

    public String getOverallPercentageCorrect() {
        String percent = String.format("%.2f", overallPercentageCorrect);
        return percent + "%";
    }

    public void setOverallPercentageCorrect(double overallPercentageCorrect) {
        this.overallPercentageCorrect = overallPercentageCorrect * 100;
    }

    public String getAverageTimeTaken() {
        String averageSeconds = String.format("%.2f", averageTimeTaken);
        return averageSeconds + " seconds";
    }

    public void setAverageTimeTaken(double averageTimeTaken) {
        this.averageTimeTaken = averageTimeTaken;
    }

    public RunDO getBestRun() {
        return bestRun != null ? bestRun : new RunDO();
    }

    public void setBestRun(RunDO bestRun) {
        this.bestRun = bestRun;
    }

    public RunDO getWorstRun() {
        return worstRun != null ? worstRun : new RunDO();
    }

    public void setWorstRun(RunDO worstRun) {
        this.worstRun = worstRun;
    }
}
