package TriviaGame;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 4
    Due date:           4/14/2021
    File:               TriviaGame.java
    Purpose:            Trivia game driver
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * TriviaGame
 */
public class TriviaGame {

    private static final String QUESTIONS_FILE = "TriviaGame/questions.txt";
    
    private ArrayList<Question> questions;
    private int pointsEarned;
    private Question currentQuestion;

    private TriviaGameGui gui;

    /**
     * No-arg constructor
     */
    public TriviaGame() {
        pointsEarned = 0;
        questions = new ArrayList<Question>();
        gui = new TriviaGameGui( this );

        getQuestions();
    }

    /**
     * method to read questions from a text file
     */
    public void getQuestions() {
        Scanner inputFile = null;
        String _question, _answer;
        int _points;

        try {
            // try to open file
            inputFile = new Scanner( new FileInputStream(QUESTIONS_FILE) );

            // read websites from file
            while ( inputFile.hasNextLine() ) {
                _question = inputFile.nextLine();
                _answer = inputFile.nextLine();
                _points = Integer.parseInt( inputFile.nextLine() );

                questions.add( new Question( _question, _answer, _points ) );
            }

            inputFile.close();
        }
        
        catch (FileNotFoundException e) {   
            System.out.println("Failed to find questions.txt");
            System.exit(1);
        }

        catch (NoSuchElementException e) {
            System.out.println("Error while parsing questions.txt");
            System.exit(1);
        }
    }

    /**
     * entrypoint to play game
     */
    public void playGame() {
        gui.displayQuestionPrompt( getNextQuestion() );
    }

    /**
     * Pops a random question from the list of questions.
     * Each call to this will reduce the number of questions.
     * Popping ensures questions are not duplicated.
     * 
     * @return the random question
     */
    private Question getNextQuestion() {
        Random rand = new Random();
        currentQuestion = questions.remove( rand.nextInt(questions.size()) );

        return currentQuestion;
    }
    
    /**
     * question submit handler
     */
    public void handleQuestionSubmit(String userAnswer) {
        String title = new String();
        String message = new String();

        if ( currentQuestion.checkAnswer(userAnswer) ) {
            pointsEarned += currentQuestion.getPoints();
            title = "Correct";
            message = "Correct. You earned " + pointsEarned + " points";
            
            gui.updatePointsEarned( pointsEarned );
        } else {
            title = "Incorrect";
            message = "Sorry, the correct answer is `" + currentQuestion.getAnswer() + "`";
        }
        
        JOptionPane.showMessageDialog(gui, message, title, JOptionPane.PLAIN_MESSAGE);
        
        // if questions exhausted -> quit
        if ( questions.size() == 0 ) {
            handleQuit();

            return;
        }

        gui.displayQuestionPrompt( getNextQuestion() );
    }
    
    /**
     * gui quit handler
     */
    public void handleQuit() {
        String title = new String();
        String message = new String();

        title = "Goodbye";
        message = "Thanks for playing. You earned " + pointsEarned + " points.";

        JOptionPane.showMessageDialog(gui, message, title, JOptionPane.PLAIN_MESSAGE);
        gui.dispose();
    }

    /**
     * main driver entrypoint
     * @param args cli args
     */
    public static void main(String[] args) {
        TriviaGame game = new TriviaGame();

        game.playGame();
    }
    
}