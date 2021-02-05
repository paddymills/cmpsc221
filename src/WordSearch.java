
/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 1
    Due date:           1/5/2021
    File:               WordSearch.java
    Purpose:            Word search game to demonstrate string methods
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io)
                        (https://devdocs.io/openjdk~15/);
*/

import java.util.Scanner;

public class WordSearch {

    private String puzzle;
    private int row_length;
    private int rows;

    public static void main(String[] args) {
        // create user input object
        Scanner userInput = new Scanner(System.in);

        // create puzzle object
        WordSearch puzzle = new WordSearch();

        System.out.println("Enter word search puzzle");
        System.out.println("(separate rows with `$`)");
        System.out.print("\n>> ");

        // read puzzle from input string
        String input = userInput.nextLine();
        puzzle.setPuzzle(input);

        boolean solving = true;
        do {
            // print puzzle
            puzzle.printPuzzle();

            // ask user for word to find
            System.out.print("Please enter a word to search for: ");
            input = userInput.nextLine();

            // check if word is in puzzle
            puzzle.findWord(input);
            
            // ask to continue solving
            System.out.print("Search for another word ([Y]es/[N]o)? ");
            input = userInput.nextLine();

            // if user gave No or N, stop loop
            if (input.toUpperCase().startsWith("N")) {
                solving = false;
            }

        } while (solving);
        puzzle.printPuzzle();
        
        // close user input stream
        userInput.close();
    }

    public void setPuzzle(String rawPuzzle) {
        puzzle = rawPuzzle;
    }

    public void printPuzzle() {
        System.out.print("Puzzle :\n\n\t");
        System.out.println(puzzle.replace("$", "\n\t"));
        System.out.print("\n");
    }

    public findWord(String word) {
        

    }

}