package TriviaGame;

/**
 * Question
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

    public boolean checkAnswer(String userAnswer) {
        String cleanedUserAnswer = userAnswer.strip();

        if ( answer.equals(cleanedUserAnswer) )
            return true;

        return false;
    }

}