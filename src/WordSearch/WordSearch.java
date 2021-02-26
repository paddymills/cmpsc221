
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

    private StringBuilder puzzle;
    private int rowLength;

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in); // create user input object
        String input;                               // for collecting input

        // create puzzle object
        WordSearch puzzle = new WordSearch();

        System.out.println("Enter word search puzzle");
        System.out.println("(separate rows with `$`)");
        System.out.print("\n>> ");

        // read puzzle from input string
        input = userInput.nextLine();
        puzzle.setPuzzle(input);

        boolean solving = true;
        do {
            // print puzzle
            puzzle.printPuzzle();

            // ask user for word to find
            System.out.print("Please enter a word to search for: ");
            input = userInput.nextLine();

            // validate input
            if (input.isEmpty()) {
                System.out.println("Please enter a value next time");
            } else {
                System.out.println(puzzle.findWord(input));
            }
            
            // ask to continue solving
            System.out.print("Search for another word ([Y]es/[N]o)? ");
            input = userInput.nextLine();

            // if user gave No or N, stop loop
            if (input.toUpperCase().startsWith("N")) {
                solving = false;
            } else if (!input.toUpperCase().startsWith("Y")) {
                System.out.println("Invalid option. Quitting.");
                solving = false;
            }

        } while (solving);
        
        // close user input stream
        userInput.close();
    }

    /** This method sets the puzzle.  
     * @param rawPuzzle      puzzle string.
     */
    public void setPuzzle(String rawPuzzle) {
        puzzle = new StringBuilder(rawPuzzle.toUpperCase().replace(" ", ""));

        // calculate row length
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == '$') {
                rowLength = i + 1;

                break;
            }
        }
    }

    /*
     * This method prints the puzzle
     */
    public void printPuzzle() {
        System.out.print("\nPuzzle :\n\n\t");
        
        // print puzzle with spaces between characters
        for (int i = 0; i < puzzle.length(); i++) {
            if (puzzle.charAt(i) == '$') {
                System.out.print("\n\t");
            } else {
                System.out.print(puzzle.charAt(i) + " ");
            }
        }
        
        System.out.println("\n");
    }

    /** This method tries to find the word using the methods.  
     * @param wordToFind    word to find.
     * @return              The result string of how the word was found, or not at all
     */
    public StringBuilder findWord(String wordToFind) {
        // need to convert to StringBuilder so that we can reverse
        StringBuilder word = new StringBuilder(wordToFind.toUpperCase());
        StringBuilder result = new StringBuilder();

        // start building result
        result.append("\nSearch word \"");
        result.append(word);
        result.append("\" ");

        // ~~~~~~~~~~~~~~ FIND FORWARD ~~~~~~~~~~~~~~
        if (findHorizontal(word)) {         // find forwards
            return result.append("found forward.");
        } else if (findVertical(word)) {    // find down
            return result.append("found down.");
        } else if (findDiagonal(word)) {    // find diagonally
            return result.append("found diagonal.");
        }

        // ~~~~~~~~~~~~~~ FIND REVERSED ~~~~~~~~~~~~~~
        word.reverse();

        // find reversed
        if (findHorizontal(word)) {         // find right-to-left
            return result.append("found backwards.");
        } else if (findVertical(word)) {    // find up
            return result.append("found up.");
        } else if (findDiagonal(word)) {    // find diagonal (reversed)
            return result.append("found diagonal reversed.");
        }
        
        // all else, not found
        return result.append("not found.");
    }

    /** This method tries to find the word horizontally.  
     * @param word      word to find.
     * @return          True if the wod is found, otherwise False
     */
    public boolean findHorizontal(StringBuilder word) {
        int foundAt;
        
        // find beginning index of word, or -1
        foundAt = puzzle.indexOf(word.toString());

        if (foundAt > -1) {
            // replace word with `*`
            puzzle.replace(foundAt, foundAt + word.length(), "*".repeat(word.length()));

            return true;
        }

        return false;
    }

    /** This method tries to find the word vertically.  
     * @param word      word to find.
     * @return          True if the wod is found, otherwise False
     */
    public boolean findVertical(StringBuilder word) {
        boolean found = false;      // for verifying word is found
        char puzzleChar, wordChar;  // for comparing characters
        
        // iterate over puzzle
        for (int i = 0; i < puzzle.length(); i++) {
            // find if character matches first character of word
            if (puzzle.charAt(i) == word.charAt(0)) {
                
                /*
                 *  Iterate over word, checking that word is in puzzle
                 *  add row length to index to move vertically
                 */
                found = true;
                for (int j = 1; j < word.length(); j++) {
                    // stop if next find location passed end of puzzle
                    // no need to continue comparing
                    if (i + j * rowLength >= puzzle.length()) {
                        return false;
                    }
                    
                    // set compare characters
                    wordChar = word.charAt(j);
                    puzzleChar = puzzle.charAt(i + j * rowLength);

                    // stop if character does not match
                    if (puzzleChar != wordChar) {
                        found = false;

                        break;
                    }
                }

                if (found) {
                    // set found characters to `*`
                    for (int j = 0; j < word.length(); j++) {
                        puzzle.setCharAt(i + j * rowLength, '*');
                    }

                    return true;
                }
            }
        }

        return false;
    }

    /** This method tries to find the word diagonally.  
     * @param word      word to find.
     * @return          True if the wod is found, otherwise False
     */
    public boolean findDiagonal(StringBuilder word) {
        boolean foundLeft = false;                       // for verifying word is found (/ direction)
        boolean foundRight = false;                      // for verifying word is found (\ direction)
        char puzzleCharLeft, puzzleCharRight, wordChar;  // for comparing characters
        
        // iterate over puzzle
        for (int i = 0; i < puzzle.length(); i++) {
            // find if character matches first character of word
            if (puzzle.charAt(i) == word.charAt(0)) {
                
                /*
                 *  Iterate over word, checking that word is in puzzle
                 *  add row length to index to move vertically
                 */
                foundLeft = true;
                foundRight = true;
                for (int j = 1; j < word.length(); j++) {
                    // stop if next find location passed end of puzzle
                    // no need to continue comparing
                    if (i + j * rowLength >= puzzle.length()) {
                        return false;
                    }
                    
                    // stop left/right searches if they have reached the end of their line
                    if (i - j < 0) {
                        foundLeft = false;
                    } else if (i + j > rowLength - 1) {
                        foundRight = false;
                    }
                    // set compare word character
                    wordChar = word.charAt(j);
                    
                    // stop if character does not match
                    if (foundLeft) {
                        puzzleCharLeft = puzzle.charAt(i + j * rowLength - j);
                        
                        if (puzzleCharLeft != wordChar) {
                            foundLeft = false;
                        }
                    }
                    if (foundRight) {
                        puzzleCharRight = puzzle.charAt(i + j * rowLength + j);

                        if (puzzleCharRight != wordChar) {
                            foundRight = false;
                        }
                    }


                    // stop if both left and right searches failed
                    if (!foundLeft && !foundRight) {
                        return false;
                    }
                }

                if (foundLeft) {
                    // set found characters to `*`
                    for (int j = 0; j < word.length(); j++) {
                        puzzle.setCharAt(i + j * rowLength - j, '*');
                    }

                    return true;
                } else if (foundRight) {
                    // set found characters to `*`
                    for (int j = 0; j < word.length(); j++) {
                        puzzle.setCharAt(i + j * rowLength + j, '*');
                    }

                    return true;
                }
            }
        }

        return false;
    }

}