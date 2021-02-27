package BinaryMath;

import java.util.Random;


/**
 * ProblemAndAnswer
 */
public class ProblemAndAnswer {
    
    private static final int NUMBER_LENGTH = 5;

    private int[] num1;
    private int[] num2;
    private char operator;


    public ProblemAndAnswer(char op) {

        // initialize binary number arrays
        num1 = new int[NUMBER_LENGTH];
        num2 = new int[NUMBER_LENGTH];

        // set operator
        operator = op;

        // initialize random number generator
        Random randomNumber = new Random();
        
        for (int i=0; i<NUMBER_LENGTH; i++) {
            num1[i] = randomNumber.nextInt(1000) % 2;
            num2[i] = randomNumber.nextInt(1000) % 2;
        }
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
        String num1String = new String();
        String num2String = new String();

        for (int i = 0; i < NUMBER_LENGTH; i++) {
            num1String += num1[i];
            num2String += num2[i];
        }

        return num1String + " " + operator + " " + num2String;
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