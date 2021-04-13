package TriviaGame;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 4
    Due date:           4/14/2021
    File:               Question.java
    Purpose:            Question object class
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

/**
 * Question instance class
 */
public class Question {

    private String question;
    private String answer;
    private int points;

    /**
     * Question constructor
     * @param q Question string
     * @param a Answer string
     * @param p number of point the question is worth
     */
    public Question(String q, String a, int p) {
        question = q;
        answer = a.toLowerCase();
        points = p;
    }

    /**
     * question setter
     * @param q question string
     */
    public void setQuestion(String q) {
        question = q;
    }

    /**
     * answer setter
     * @param a answer string
     */
    public void setAnswer(String a) {
        answer = a.toLowerCase();
    }

    /**
     * points setter
     * @param p number of points
     */
    public void setPoints(int p) {
        points = p;
    }

    /**
     * question getter
     * @return question string
     */
    public String getQuestion() {
        return question;
    }

    /**
     * answer getter
     * @return question's answer string
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * points getter
     * @return question'n number of points
     */
    public int getPoints() {
        return points;
    }

    /**
     * checks whether user's answer is correct
     * 
     * @param userAnswer user's answer as String
     * @return if user's answer is a case insensitive, whitespace ignored equivalent to the answer
     */
    public boolean checkAnswer(String userAnswer) {
        // strip leading/trailing whitespace
        // and convert to lower case
        String cleanedUserAnswer = userAnswer.strip().toLowerCase();

        if ( answer.equals(cleanedUserAnswer) )
            return true;

        return false;
    }

}