package com.randomappsinc.mathrace.Models.Events;

import com.randomappsinc.mathrace.API.Models.Evaluation;

/**
 * Created by alexanderchiou on 12/11/15.
 */
public class EvaluationEvent {
    private Evaluation evaluation;

    public EvaluationEvent(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }
}
