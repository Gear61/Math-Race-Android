package com.randomappsinc.mathrace.Models;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class Problem {
    private String problemText;
    private int answer;

    public String getProblemText() {
        return problemText;
    }

    public int getAnswer() {
        return answer;
    }

    public void setProblemText(String problemText) {
        this.problemText = problemText;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
