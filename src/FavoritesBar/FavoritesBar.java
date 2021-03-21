package FavoritesBar;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 3
    Due date:           3/31/2021
    File:               FavoritesBar.java
    Purpose:            WebSite favorites driver program
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

import java.util.ArrayList;

public class FavoritesBar {

    private static ArrayList<WebSite> availableSites;
    private static WebSiteCollection favorites;
    
    public static void main(String[] args) {
        favorites = null;
        availableSites = getAvailableSites();


    }

    private static ArrayList<WebSite> getAvailableSites() {
        ArrayList<WebSite> sites = new ArrayList<WebSite>();

        // read websites from file

        return sites;
    }

    private static void createFavoritesBar() {
        favorites = new WebSiteCollection();
    }

    private static void deleteFavoritesBar() {
        favorites = null;
    }

    private static void addWebSite(WebSite ws) {
        if (favorites == null) {
            System.out.println("No favorites bar has been created.");
            return;
        }

        favorites.addWebSite(ws);
    }

    private static void removeWebSite(WebSite ws) {
        if (favorites == null) {
            System.out.println("No favorites bar has been created.");
            return;
        }

        favorites.removeWebSite(ws);
    }

    private void mainLoop() {
        boolean quit = false;

        do {
            // print main menu
            System.out.println("Welcome to FavGen 1.0\n");
            System.out.println("Choose an option:");
            System.out.println("  1) Create favorites bar");
            System.out.println("  2) Delete favorites bar");
            System.out.println("  3) Add website to favorites bar");
            System.out.println("  4) Remove website to favorites bar");
            System.out.println("  5) Quit\n");
            System.out.print("\tYour choice: ");
            
        } while ( !quit );
    }

    private void addWebSiteLoop() {
        boolean quit = false;

        do {
            
        } while ( !quit );
    }
}
