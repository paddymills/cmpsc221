package BinaryMath;

/**
 * Driver program for Binary Math application
 */


public class BinaryMath {
    
    public static final int NUMBER_OF_PROBLEMS = 15;
    public static void main(String[] args) {
        ProblemAndAnswer[] problems = new ProblemAndAnswer[NUMBER_OF_PROBLEMS];
        char[] operators = {'+', '+', '+', '+', '+', '-', '-', '-', '-', '-', '*', '*', '*', '*', '*'};
        
        for (int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            
            while (true) {
                problems[i] = new ProblemAndAnswer(operators[i]);
                
                for (int j = 0; j < i; j++) {
                    if (problems[j].equals(problems[i])) {
                        // problem found in problems list: regenerate
                        continue;
                    }
                }
                
                // else: problem not found
                break;
            }

        }
        
        for (ProblemAndAnswer prob : problems) {
            System.out.println(prob.getProblem());
        }
        
    }

}
