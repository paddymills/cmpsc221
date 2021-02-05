
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

    public static void main(String[] args) {
        // create user input object
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter word search puzzle");
        System.out.println("(separate rows with `$`)");
        System.out.print("\n>> ");

        // read puzzle from input string
        String puzzle = userInput.nextLine();

        System.out.print("Puzzle :\n\n\t");
        System.out.println(puzzle.replace("$", "\n\t"));
        System.out.print("\n");
        
        // close user input stream
        userInput.close();
    }

}