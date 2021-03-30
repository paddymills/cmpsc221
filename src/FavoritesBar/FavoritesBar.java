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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class FavoritesBar {

    private static final String UI_SEPARATOR = "\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    private WebSiteCollection availableSites;
    private WebSiteCollection favorites;

    /**
     * FavoritesBar constructor
     * 
     * initializes favorites collection to null
     * gets available sites from text file
     */
    public FavoritesBar() {
        favorites = null;
        availableSites = getAvailableSites();
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
        favorites = null;

        System.out.println("Favorites bar deleted");
    }

    /**
     * adds website to favorites collection
     * 
     * @param ws Website to add to favorites collection
     */
    private void addWebSite(WebSite ws) {
        if (favorites == null) {
            System.out.println("No favorites bar has been created to add to.");
            return;
        }

        favorites.addWebSite(ws);
    }

    /**
     * remove website from favorites collection
     * 
     * @param ws Website to remove from favorites collection
     */
    private void removeWebSite(WebSite ws) {
        if (favorites == null) {
            System.out.println("No favorites bar has been created to remove from.");
            return;
        }

        favorites.removeWebSite(ws);
    }

    /**
     * main menu loop
     */
    public void mainLoop() {
        Scanner kbd = new Scanner(System.in);
        boolean quit = false;

        do {
            // print main menu
            System.out.println("\nChoose an option:");
            System.out.println("  1) Create favorites bar");
            System.out.println("  2) Delete favorites bar");
            System.out.println("  3) Add website to favorites bar");
            System.out.println("  4) Remove website to favorites bar");
            System.out.println("  5) Quit\n");
            System.out.print("  >> ");

            switch (kbd.nextLine().strip()) {
                case "1":
                    createFavoritesBar();
                    break;

                case "2":
                    deleteFavoritesBar();
                    break;

                case "3":
                    addWebSiteLoop();
                    break;

                case "4":
                    removeWebSiteLoop();
                    break;
                    
                case "5":
                    quit = true;
                    break;
                
                default:
                    System.out.println("Invalid selection.");
            }

            System.out.println(UI_SEPARATOR);
            
        } while ( !quit );

        kbd.close();
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
        index = getIntInputLoop(topics.size());

        if ( index == topics.size() )
            return "";
        
        return topics.get(index - 1);
    }

    /**
     * loop to add website to favorites collection
     * will run choose topic loop to get topic
     */
    private void addWebSiteLoop() {
        ArrayList<WebSite> sites = null;
        WebSite selectedWebSite;
        int index;

        // get topic
        String topic = chooseTopicLoop();

        // empty string -> return to main menu
        if ( topic == "" )
            return;

        sites = availableSites.getSitesWithTopic(topic);

        // print list of websites for selection
        printMenuList(sites);
        
        // get selected index
        index = getIntInputLoop(sites.size());

        if ( index < sites.size() ) {
            selectedWebSite = sites.get(index - 1);
            addWebSite( selectedWebSite );
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
        
        // get all websites
        sites = favorites.getSites();

        // print list of websites for selection
        printMenuList( sites );
        
        // get selected index
        index = getIntInputLoop( sites.size() );


        if ( index < sites.size() ) {
            selectedWebSite = sites.get(index - 1);

            removeWebSite( selectedWebSite );
            System.out.println( "Removed site from favorites: " + selectedWebSite.toOneLineString() );
        }
    }
    
    /**
     * main driver method
     * 
     * @param args
     */
    public static void main(String[] args) {
        FavoritesBar bar = new FavoritesBar();

        System.out.println("-----------------------------");
        System.out.println("    Welcome to FavGen 1.0");
        System.out.println("-----------------------------");

        // bar.mainLoop();
        bar.createFavoritesBar();
        bar.addWebSiteLoop();

        System.out.println("-----------------------------");
        System.out.println("    Thanks for using FavGen");
        System.out.println("-----------------------------");
    }

    /**
     * Reads and parses visitedsites.txt
     * 
     * @return WebSiteCollection of the websites in visitedsites.txt
     */
    private static WebSiteCollection getAvailableSites() {
        Scanner inputFile = null;
        String name, url, rating, topic, visited;
        WebSiteCollection sites = new WebSiteCollection();

        try {
            // try to open file
            inputFile = new Scanner( new FileInputStream("FavoritesBar/visitedsites.txt") );
            
            System.out.println("Initializing website list...");

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
    private static void printMenuList(String title, ArrayList<String> items) {
        // print title
        System.out.println(title);

        // print items
        for ( int i = 0; i < items.size(); i++ )
            System.out.printf("  %d) %s%n", i+1, items.get(i));

        // print return to main menu item
        System.out.printf("  %d) Return to main menu%n", items.size());
    }

    private static void printMenuList(ArrayList<WebSite> sites) {
        // print title
        System.out.println("Choose a website:");

        // print items
        for ( int i = 0; i < sites.size(); i++ ) {
            System.out.printf("Website #%d %n", i);
            System.out.println("--------------");
            System.out.println(sites.get(i).toString());
            System.out.println("--------------\n");
        }

        // print return to main menu item
        System.out.printf("Option #%d: Return to main menu%n", sites.size());
    }

    /**
     * loop to get user input of an index
     * validates if index is 1 to maxIndex (inclusive)
     * 
     * @param maxIndex maximum index that is valid
     * @return  selected index
     */
    private static int getIntInputLoop(int maxIndex) {
        Scanner kbd = new Scanner(System.in);
        int selection;
        
        while ( true ) {
            // prompt for input
            System.out.print("\n  >> ");

            try {
                selection = kbd.nextInt();

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
            catch ( NoSuchElementException e ) {
                System.out.println("Invalid selection.");
            }

            // clear the input buffer
            if ( kbd.hasNextLine() )
                kbd.nextLine();
        }

        kbd.close();

        return selection;
    }
}
