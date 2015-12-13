package com.randomappsinc.mathrace.Utils;

import com.randomappsinc.mathrace.Models.Problem;

import java.util.Random;

/**
 * Created by alexanderchiou on 12/10/15.
 */
public class RaceUtils {
    // Cap is non-inclusive
    public static final int OPERAND_CAP = 100;

    public static Problem generateProblem(String type) {
        Problem problem = new Problem();

        int firstNumber = generateRandomNumber(OPERAND_CAP);
        int secondNumber = type.equals(Constants.ADDITION)
                ? generateRandomNumber(OPERAND_CAP)
                : generateRandomNumber(firstNumber + 1);
        switch (type) {
            case Constants.ADDITION:
                problem.setProblemText(String.valueOf(firstNumber) + " + " + String.valueOf(secondNumber) + " = ?");
                problem.setAnswer(firstNumber + secondNumber);
                break;
            case Constants.SUBTRACTION:
                problem.setProblemText(String.valueOf(firstNumber) + " - " + String.valueOf(secondNumber) + " = ?");
                problem.setAnswer(firstNumber - secondNumber);
        }

        return problem;
    }

    public static int generateRandomNumber(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public static String generateRank(int numSuperiorRuns) {
        int position = numSuperiorRuns + 1;
        String rank = String.valueOf(position);
        int lastTwoDigits = position % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) {
            rank += "th";
        }
        else {
            switch (position % 10) {
                case 0:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    rank += "th";
                    break;
                case 1:
                    rank += "st";
                    break;
                case 2:
                    rank += "nd";
                    break;
                case 3:
                    rank += "rd";
            }
        }
        return rank;
    }

    public static String generatePercentile(int rank, int numTotalRuns) {
        double percentile = 100 - ((double) rank / numTotalRuns) * 100;
        return String.format("%.2f", percentile);
    }
}
