package FavoritesBar;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 3
    Due date:           3/30/2021
    File:               FavoritesBar.java
    Purpose:            WebSite favorites driver program
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class FavoritesBar {

    private static final String UI_SEPARATOR = "\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private WebSiteCollection availableSites;
    private WebSiteCollection favorites;
    private Scanner kbd;

    /**
     * FavoritesBar constructor
     * 
     * initializes favorites collection to null
     * gets available sites from text file
     */
    public FavoritesBar() {
        favorites = null;
        availableSites = getAvailableSites();

        // initialize input stream
        kbd = new Scanner(System.in);
    }

    /**
     * initializes favorites collection
     */
    private void createFavoritesBar() {
        favorites = new WebSiteCollection();

        System.out.println("Favorites bar created");
    }

    /**
     * deletes favorites collection
     */
    private void deleteFavoritesBar() {
        if ( favorites == null ) {
            System.out.println("No favorites bar to delete");
            return;
        }

        favorites = null;

        System.out.println("Favorites bar deleted");
    }

    /**
     * main menu loop
     */
    public void mainLoop() {
        int index;

        while ( true ) {
            // print main menu
            System.out.println("\nChoose an option:");
            System.out.println("  1) Create favorites bar");
            System.out.println("  2) Delete favorites bar");
            System.out.println("  3) Add website to favorites bar");
            System.out.println("  4) Remove website to favorites bar");
            System.out.println("  5) View favorites bar");
            System.out.println("  6) Quit\n");

            index = getIntInputLoop(6);

            switch ( index ) {
                case 1:
                    createFavoritesBar();
                    break;

                case 2:
                    deleteFavoritesBar();
                    break;

                case 3:
                    addWebSiteLoop();
                    break;

                case 4:
                    removeWebSiteLoop();
                    break;
                
                case 5:
                    printMenuList( favorites.getSites() );
                    break;
                
                case 6:
                    doOutputFile();
                    return;
                
                default:    // unreachable
                    System.out.println("Invalid selection.");
            }

            System.out.println(UI_SEPARATOR);
            
        }
    }

    /**
     * loop to choose topic from available topics
     */
    private String chooseTopicLoop() {
        int index;
        
        ArrayList<String> topics = availableSites.getTopics();

        // print topics menu
        printMenuList("Choose a topic:", topics);

        // get selected index
        index = getIntInputLoop( topics.size() + 1  ) - 1;

        if ( index == topics.size() )
            return "";
        
        return topics.get( index );
    }

    /**
     * loop to add website to favorites collection
     * will run choose topic loop to get topic
     */
    private void addWebSiteLoop() {
        ArrayList<WebSite> sites = null;
        WebSite selectedWebSite;
        int index;

        // return if favorites not initialized
        if (favorites == null) {
            System.out.println("No favorites bar has been created to add to.");
            return;
        }

        // get topic
        String topic = chooseTopicLoop();

        // empty string -> return to main menu
        if ( topic.equals("") )
            return;

        sites = availableSites.getSitesWithTopic(topic);

        // print list of websites for selection
        printMenuList(sites);
        
        // get selected index
        index = getIntInputLoop( sites.size() + 1  ) - 1;

        if ( index < sites.size() ) {
            selectedWebSite = sites.get( index );

            favorites.addWebSite( selectedWebSite );
            System.out.println( "Added site to favorites: " + selectedWebSite.toOneLineString() );
        }
    }

    /**
     * loop to remove website from favorites collection
     */
    private void removeWebSiteLoop() {
        ArrayList<WebSite> sites;
        WebSite selectedWebSite;
        int index;

        // return if favorites not initialized
        if (favorites == null) {
            System.out.println("No favorites bar has been created to remove from.");
            return;
        }
        
        // get all websites
        sites = favorites.getSites();

        // print list of websites for selection
        printMenuList( sites );
        
        // get selected index
        index = getIntInputLoop( sites.size() + 1 ) - 1;


        if ( index < sites.size() ) {
            selectedWebSite = sites.get( index );

            favorites.removeWebSite( selectedWebSite );
            System.out.println( "Removed site from favorites: " + selectedWebSite.toOneLineString() );
        }
    }

    /**
     * Reads and parses visitedsites.txt
     * 
     * @return WebSiteCollection of the websites in visitedsites.txt
     */
    private WebSiteCollection getAvailableSites() {
        Scanner inputFile = null;
        String name, url, rating, topic, visited;
        WebSiteCollection sites = new WebSiteCollection();

        try {
            // try to open file
            inputFile = new Scanner( new FileInputStream("FavoritesBar/visitedsites.txt") );
            
            System.out.println("Initializing website list...\n");

            // read websites from file
            while ( inputFile.hasNextLine() ) {
                name = inputFile.nextLine();
                url = inputFile.nextLine();
                rating = inputFile.nextLine();
                topic = inputFile.nextLine();
                visited = inputFile.nextLine();
                
                sites.addWebSite( new WebSite(name, url, rating, topic, visited) );
            }

            inputFile.close();
        }
        
        catch (FileNotFoundException e) {   
            System.out.println("Failed to find website list file.");
            System.exit(1);
        }

        catch (NoSuchElementException e) {
            System.out.println("Website list file does not match expected format. (possible missing line)");
            System.exit(1);
        }

        return sites;
    }

    /**
     * Prints a menu
     * example  ----> Choose an option:
     *          ---->   1) Option 1
     *          ---->   2) Option 2
     *          ---->   3) Option 3
     *          ---->   4) Return to main menu
     * 
     * @param title Title to display (first line)
     * @param items ArrayList of String option values
     */
    private void printMenuList(String title, ArrayList<String> items) {
        // print title
        System.out.println(title);

        // print items
        int i = 1;
        for ( String item : items )
            System.out.printf("  %d) %s%n", i++, item);

        // print return to main menu item
        System.out.printf( "  %d) Return to main menu%n", i );
    }

    private void printMenuList(ArrayList<WebSite> sites) {
        // print title
        System.out.println("Choose a website:");

        // print items
        int i = 1;
        for ( WebSite site : sites ) {
            System.out.printf("Website #%d %n", i++);
            System.out.println("---------------------------------");
            System.out.println(site.toString());
            System.out.println("---------------------------------\n");
        }

        // print return to main menu item
        System.out.printf( "Option #%d: Return to main menu%n", i );
    }

    /**
     * loop to get user input of an index
     * validates if index is 1 to maxIndex (inclusive)
     * 
     * @param maxIndex maximum index that is valid
     * @return  selected index
     */
    private int getIntInputLoop(int maxIndex) {
        String input;
        int selection;
        
        while ( true ) {
            // reset input
            input = "";

            // prompt for input
            System.out.print("\n  > ");

            try {
                input = kbd.nextLine();
                selection = Integer.parseInt(input);

                // selection out of range
                if ( selection < 1 || selection > maxIndex )
                    throw new NoSuchElementException();

                // valid selection -> return index
                else
                    break;
            }

            // Exceptions: print and re-iterate
            // a) no numbers in input stream
            // b) input stream in exhausted
            // c) selection out of range
            catch ( NoSuchElementException | NumberFormatException e ) {
                System.out.println("Invalid selection: " + input);
            }
        }

        return selection;
    }

    /**
     * asks if user wants to output a favorites html file
     */
    private void doOutputFile() {
        String response;
        PrintWriter htmlOutput = null;
        
        System.out.println("Do you want to output your favorites as an HTML file?");
        System.out.print("([Y]es/[N]o) > ");

        response = kbd.next().toUpperCase();

        if ( response.equals("Y") || response.equals("YES") ) {
            try {
                htmlOutput = new PrintWriter( new FileOutputStream("favorites.html") );

                htmlOutput.println("<!DOCTYPE html>");
                htmlOutput.println("<html>");
                htmlOutput.println("    <head>");
                htmlOutput.println("        <title>My Favorites</title>");
                htmlOutput.println("    </head>");
                htmlOutput.println("    <body>");

                for ( WebSite site : favorites.getSites() )
                    htmlOutput.printf("        <a href=\"%s\">%s</a>%n", site.getUrl(), site.getName());
                
                htmlOutput.println("    </body>");
                htmlOutput.println("</html>");

                htmlOutput.close();

                System.out.println("favorites.html generated");
            }

            catch (FileNotFoundException ex) {
                System.out.println("Failed to create favorites.html. Sorry!");
            }
        }
    }
    
    /**
     * main driver method
     * 
     * @param args
     */
    public static void main(String[] args) {
        FavoritesBar bar;

        System.out.println("-----------------------------");
        System.out.println("    Welcome to FavGen 1.0");
        System.out.println("-----------------------------");
        System.out.println("\n");

        bar = new FavoritesBar();
        bar.mainLoop();

        System.out.println("\n");
        System.out.println("-----------------------------");
        System.out.println("    Thanks for using FavGen");
        System.out.println("-----------------------------");
    }
}
