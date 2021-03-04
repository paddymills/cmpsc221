package BinaryMath;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 2
    Due date:           3/4/2021
    File:               BinaryMath.java
    Purpose:            Binary math program to demostrate classes
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
                        BinaryMath (http://www.binarymath.info/)
*/

import javax.swing.JOptionPane;

/**
 * Driver program for Binary Math application
 */


public class BinaryMath {
    
    public static final int NUMBER_OF_PROBLEMS = 15;
    public static final int NUMBER_LENGTH = 5;

    private ProblemAndAnswer[] problems;

    public static void main(String[] args) {
        BinaryMath mathLab = new BinaryMath();

        // welcome message
        JOptionPane.showMessageDialog(null, "Welcome to Binary Math, where there are 10 kinds of people. Those who understand binary, and those who don't.", "Welcome", JOptionPane.PLAIN_MESSAGE);

        mathLab.programLoop();

        // closing message
        JOptionPane.showMessageDialog(null, "Thank you for playing Binary Math", "Goodbye", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Constructor
     */
    public BinaryMath() {
        // initialize problem array
        problems = new ProblemAndAnswer[NUMBER_OF_PROBLEMS];
    

        // generate math problems
        generateProblems();
    }
    
    /**
     * method to generate problems
     */
    public void generateProblems() {
        BinaryNumber num1;
        BinaryNumber num2;
        String problemString;

        boolean rerun = true;  // for controlling while loop

        // five of each: addition, subtraction, multiplication
        char[] operators = {'+', '+', '+', '+', '+', '-', '-', '-', '-', '-', '*', '*', '*', '*', '*'};
        
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            problems[i] = new ProblemAndAnswer();
            
            do {
                rerun = false;

                // generate numbers
                num1 = new BinaryNumber(NUMBER_LENGTH);
                num2 = new BinaryNumber(NUMBER_LENGTH);

                // subtraction: top number must be greater
                if (operators[i] == '-') {
                    while (num2.greaterThan(num1)) {
                        // could swap numbers, but its easier/cleaner to regenerate
                        num1 = new BinaryNumber(NUMBER_LENGTH);
                    }
                }
                
                // get problem as string
                problemString = ProblemAndAnswer.getProblemFormat(num1, num2, operators[i]);
                
                // ensure problem is not already in list
                for (int j = 0; j < i; j++) {
                    if (problems[j].getProblem().equals(problemString)) {
                        // problem found in problems list: regenerate
                        rerun = true;   // will rerun generate loop
                    }
                }
            } while (rerun);

            // set problem
            problems[i].setProblem(problemString);

            // calculate and save answer
            problems[i].setAnswer(ProblemAndAnswer.solveProblem(num1, num2, operators[i]));
        }
        
    }

    /**
     * main program loop
     * -----------------------
     * iterates through each problem
     * 
     * quits either by user request or when problem list is exhausted
     */
    public void programLoop() {
        int keepGoing;

        for (ProblemAndAnswer currentProblem : problems) {
            problemLoop(currentProblem);

            keepGoing = JOptionPane.showConfirmDialog(null, "Do you want another problem?", "Continue", JOptionPane.YES_NO_OPTION);

            if (keepGoing == 1) // No
                break;

        }
    }
    
    /**
     * problem loop
     * -----------------------
     * displays the problem and asks for the answer for the user
     * 
     * quits either by a correct answer or user clicks cancel
     */
    public void problemLoop(ProblemAndAnswer problem) {
        String userInput;
        boolean quit = false;
        
        do {
            userInput = displayProblem(problem);

            if (userInput == null)  // cancel button clicked
                break;
            
            // trim leading and trailing spaces
            userInput = userInput.trim();

            if (validateInput(userInput)) {
                if (problem.checkAnswer(userInput)) {
                    JOptionPane.showMessageDialog(null, "Good Job! Correct answer!", "Correct", JOptionPane.PLAIN_MESSAGE);

                    quit = true;
                }

                else
                    JOptionPane.showMessageDialog(null, "Wrong answer. Try again.", "Wrong", JOptionPane.ERROR_MESSAGE);
            }

        } while (!quit);
    }

    /**
     * displays problem as input dialog
     * 
     * @return user's input: either text as a String or null if cancel was clicked
     */
    public String displayProblem(ProblemAndAnswer problem) {
        
        return JOptionPane.showInputDialog(null, problem.getProblem(), "Solve this...", JOptionPane.QUESTION_MESSAGE);

    }

    /**
     * input validator
     * iterates through user's input and validates that each character
     * is a 1 or a 0
     * 
     * @param input user's input as a String
     * @return false if any character other than 1 or 0 is encountered. else, true.
     */
    public boolean validateInput(String input) {
        
        // assert number is binary
        for (char c : input.toCharArray()) {

            if ( !(c == '0' || c == '1') ) {
                JOptionPane.showMessageDialog(null, "Invalid character in input (must be 1 or 0)", "Invalid Input", JOptionPane.ERROR_MESSAGE);

                return false;
            }
        }

        return true;
    }

}

