package BinaryMath;

/**
 * ProblemAndAnswer
 */
public class ProblemAndAnswer {

    private String problem;
    private String answer;


    public ProblemAndAnswer() {

        // initialize binary number arrays
        problem = "";
        answer = "";

    }

    public void setProblem(String p) {
        problem = p;
    }

    public void setAnswer(String a) {
        answer = a;
    }

    public String getProblem() {
        return problem;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String userAnswer) {
        if (answer == userAnswer)
            return true;

        return false;
    }

    public boolean equals(ProblemAndAnswer other) {
        if (problem.equals(other.getProblem()))
            return true;

        return false;
    }

    public static String getProblemFormat(BinaryNumber num1, BinaryNumber num2, char operator) {
        return getProblemFormat(num1.toString(), num2.toString(), operator);
    }

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