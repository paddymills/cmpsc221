package BinaryMath;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 2
    Due date:           3/4/2021
    File:               ProblemAndAnswer.java
    Purpose:            ProblemAndAnswer class for driver program
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/


/**
 * ProblemAndAnswer
 */
public class ProblemAndAnswer {

    private String problem;
    private String answer;

    /**
     * Constructor
     * 
     * no argument constructor
     * initializes problem and answer to empty strings
     */
    public ProblemAndAnswer() {

        // initialize binary number arrays
        problem = "";
        answer = "";

    }

    /**
     * problem setter
     * 
     * @param p problem string
     */
    public void setProblem(String p) {
        problem = p;
    }

    /**
     * answer setter
     * 
     * @param a problems answer as string
     */
    public void setAnswer(String a) {
        answer = a;
    }

    /**
     * problem getter
     * 
     * @return problem string
     */
    public String getProblem() {
        return problem;
    }

    /**
     * answer getter
     * 
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * answer checker
     * 
     * @param userAnswer the answer the user entered to be checked
     * @return true or false, if user's answer matches problem's answer
     */
    public boolean checkAnswer(String userAnswer) {
        if (answer == userAnswer)
            return true;

        return false;
    }

    /**
     * equals checker
     * @param other another ProblemAndAnswer
     * @return true or false, if this and other are the same problem
     */
    public boolean equals(ProblemAndAnswer other) {
        if (problem.equals(other.getProblem()))
            return true;

        return false;
    }

    /**
     * problem format string formatter
     * version that can be called with types of BinaryNumber class
     * this is actually a wrapper that calls the String version of this method
     * 
     * @param num1 first number
     * @param num2 second number
     * @param operator mathematic operator
     * @return formatted problem string
     */
    public static String getProblemFormat(BinaryNumber num1, BinaryNumber num2, char operator) {
        return getProblemFormat(num1.toString(), num2.toString(), operator);
    }

    /**
     * problem format string formatter
     * returns the problem in the desired format
     * 
     * @param num1 first number
     * @param num2 second number
     * @param operator mathematic operator
     * @return formatted problem string
     */
    public static String getProblemFormat(String num1, String num2, char operator) {
        String problem;
        
        /*
            get problem display format
            example:
                   11011
                 + 10100
                ---------
        */

        problem =  "   " + num1 + "\n";
        problem += " " + operator + " " + num2 + "\n";
        problem += "----";
        for (int j = 0; j < num1.length(); j++)
            problem += "-";

        return problem;
    }

    /**
     * problem solver
     * calls the appropriate mathematical method, depending on the operator
     * 
     * @param num1 first number
     * @param num2 second number
     * @param operator mathematic operator
     * @return the String representation of the problem's answer
     */
    public static String solveProblem(BinaryNumber num1, BinaryNumber num2, char operator) {

        switch (operator) {
            case '+':       // addition
                return num1.add(num2).toString();
                
            case '-':     // subtraction
                return num1.subtract(num2).toString();    

            case '*':     // multiplication
                return num1.multiply(num2).toString();

            default:      // division
                // not required for the assignment

                // UNREACHABLE: compiler will fail without
                return "";
        }

    }
}