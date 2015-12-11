package com.randomappsinc.mathrace.Models;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class Problem {
    private String problemText;
    private String answer;

    public String getProblemText() {
        return problemText;
    }

    public String getAnswer() {
        return answer;
    }

    public void setProblemText(String problemText) {
        this.problemText = problemText;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
