package BinaryMath;

import java.util.Random;

/**
 * ProblemAndAnswer
 */
public class ProblemAndAnswer {
    
    private static final int NUMBER_LENGTH = 5;

    private BinaryNumber num1;
    private BinaryNumber num2;
    private char operator;


    public ProblemAndAnswer(char op) {

        // initialize binary number arrays
        num1 = new BinaryNumber(NUMBER_LENGTH);
        num2 = new BinaryNumber(NUMBER_LENGTH);

        // set operator
        operator = op;

    }

    public ProblemAndAnswer() {
        // call the base constructor
        // operator will be invalid but we will fix that later
        // has to be the first line, apparenlty
        this('_');

        Random randomOp = new Random();

        switch (randomOp.nextInt(3)) {
            case 0:
                operator = '+';
                break;
            case 1:
                operator = '-';
                break;
            case 2:
                operator = '*';
                break;
            default:
                operator = '/';
                break;
        }

    }

    public String getProblem() {
        String problem = new String();

        /*
            get problem display format
            example:
                   11011
                 + 10100
                ---------
        */
        problem += "   " + num1.toString() + "\n";
        problem += " " + operator + " " + num2.toString() + "\n";
        problem += "----";
        for (int i = 0; i < NUMBER_LENGTH; i++)
            problem += "-";

        return problem;
    
    }

    public boolean equals(ProblemAndAnswer other) {
        if (getProblem().equals(other.getProblem()))
            return true;

        return false;
    }

    public boolean checkAnswer(String answer) {
        if (solveProblem().equals(answer))
            return true;
        
        return false;
    }

    private String solveProblem() {
        String answer = new String();

        switch (operator) {
            case '+':       // addition
                
                break;
            case '-':     // subtraction
                
                break;
            case '*':     // multiplication
                
                break;
            default:      // division
                // not required for the assignment
                break;
        }

        return answer;
    }
}