package com.randomappsinc.mathrace.Utils;

import com.randomappsinc.mathrace.Models.Problem;

import java.util.Random;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class RaceUtils {
    public static final int OPERAND_CAP = 99;

    public static Problem generateProblem(String type) {
        Problem problem = new Problem();

        int firstNumber = generateRandomNumber(OPERAND_CAP);
        int secondNumber = generateRandomNumber(OPERAND_CAP);
        switch (type) {
            case Constants.ADDITION:
                problem.setProblemText(String.valueOf(firstNumber) + " + " + String.valueOf(secondNumber) + " = ?");
                problem.setAnswer(String.valueOf(firstNumber + secondNumber));
                break;
            case Constants.SUBTRACTION:
                problem.setProblemText(String.valueOf(firstNumber) + " - " + String.valueOf(secondNumber) + " = ?");
                problem.setAnswer(String.valueOf(firstNumber - secondNumber));
        }

        return problem;
    }

    public static int generateRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max) + 1;
    }
}
