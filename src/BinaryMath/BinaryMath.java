package BinaryMath;

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

    public BinaryMath() {
        // initialize problem array
        problems = new ProblemAndAnswer[NUMBER_OF_PROBLEMS];
    

        // generate math problems
        generateProblems();
    }
    
    public void generateProblems() {
        BinaryNumber num1;
        BinaryNumber num2;
        String problemString;

        char[] operators = {'+', '+', '+', '+', '+', '-', '-', '-', '-', '-', '*', '*', '*', '*', '*'};
        
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            problems[i] = new ProblemAndAnswer();
            
            while (true) {
                num1 = new BinaryNumber(NUMBER_LENGTH);
                num2 = new BinaryNumber(NUMBER_LENGTH);

                // get problem as string
                problemString = ProblemAndAnswer.getProblemFormat(num1, num2, operators[i]);

                // set problem
                problems[i].setProblem(problemString);
                
                // ensure problem is not already in list
                for (int j = 0; j < i; j++) {
                    if (problems[j].equals(problems[i])) {
                        // problem found in problems list: regenerate
                        continue;
                    }
                }
                
                // else: problem not found
                break;
            }

            // calculate and save answer
            problems[i].setAnswer(ProblemAndAnswer.solveProblem(num1, num2, operators[i]));
        }
        
    }

    public void programLoop() {
        int keepGoing;

        for (ProblemAndAnswer currentProblem : problems) {
            problemLoop(currentProblem);

            keepGoing = JOptionPane.showConfirmDialog(null, "Do you want another problem?", "Continue", JOptionPane.YES_NO_OPTION);

            if (keepGoing == 1) // No
                break;

        }
    }
    
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
                if (problem.getAnswer().equals(userInput)) {
                    JOptionPane.showMessageDialog(null, "Good Job! Correct answer!", "Correct", JOptionPane.PLAIN_MESSAGE);

                    quit = true;
                }

                else
                    JOptionPane.showMessageDialog(null, "Wrong answer. Try again.", "Wrong", JOptionPane.ERROR_MESSAGE);
            }

        } while (!quit);
    }

    public String displayProblem(ProblemAndAnswer problem) {

        return JOptionPane.showInputDialog(null, problem.getProblem(), "Solve this...", JOptionPane.QUESTION_MESSAGE);

    }

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

