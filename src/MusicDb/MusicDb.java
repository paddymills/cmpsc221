package MusicDb;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 2
    Due date:           5/5/2021
    File:               MusicDb.java
    Purpose:            Music Database driver
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.11.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/)
                        sqlite-jdbc (https://github.com/xerial/sqlite-jdbc)
                        (https://codereview.stackexchange.com/questions/47986/printing-80-or-more-times-the-same-character)
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MusicDb {

    public static final String DATABASE_URL = "jdbc:sqlite:memory:";
    public static void main(String[] args) {
        StringBuilder init_script = new StringBuilder();
        Connection cnxn = null;
        Statement stmt = null;
        ResultSet res = null;

        // read sql query from file
        try {
            // try to open file
            Scanner inputFile = new Scanner( new FileInputStream("MusicDb/init.sql") );

            while ( inputFile.hasNext() ) {
                init_script.append( inputFile.nextLine() + "\n" );
            }

            inputFile.close();
        }
        
        catch (FileNotFoundException e) {   
            System.out.println("Failed to find sql file.");
            System.exit(1);
        }

        try {
            cnxn = DriverManager.getConnection(DATABASE_URL);
            stmt = cnxn.createStatement();

            // load data into database
            stmt.executeUpdate( init_script.toString() );

            // print musicTitles
            System.out.println(" Database Table: musicTitles");
            System.out.println("-----------------------------");
            System.out.printf( "%-5s%-30s%-30s%-30s%-50s%s%n", "ID", "Genre", "Artist", "Title", "Album", "Release Date" );
            System.out.println( new String(new char[160]).replace("\0", "=") );
            
            res = stmt.executeQuery("SELECT * FROM musicTitles ORDER BY artist");
            while ( res.next() ) {
                System.out.printf( "%-5s", res.getInt("musicID") );
                System.out.printf( "%-30s", res.getString("genre") );
                System.out.printf( "%-30s", res.getString("artist") );
                System.out.printf( "%-30s", res.getString("title") );
                System.out.printf( "%-50s", res.getString("album") );
                System.out.printf( "%s%n", res.getInt("releaseDate") );
            }
            System.out.println("\n\n");

            // print musicAwards
            System.out.println(" Database Table: musicAwards");
            System.out.println("-----------------------------");
            System.out.printf( "%-5s%-50s%s%n", "ID", "Award", "Award Year" );
            System.out.println( new String(new char[70]).replace("\0", "=") );

            res = stmt.executeQuery("SELECT * FROM musicAwards ORDER BY awardYear DESC");
            while ( res.next() ) {
                System.out.printf( "%-5s", res.getInt("musicID") );
                System.out.printf( "%-50s", res.getString("award") );
                System.out.printf( "%s%n", res.getString("awardYear") );
            }
            System.out.println("\n\n");

            // print songs with awards
            System.out.println(" Songs that have received awards");
            System.out.println("---------------------------------");
            System.out.printf( "%-30s%-30s%-50s%s%n", "Genre", "Artist", "Award", "Award Year" );
            System.out.println( new String(new char[130]).replace("\0", "=") );
            
            res = stmt.executeQuery("SELECT genre, artist, award, awardYear FROM musicTitles INNER JOIN musicAwards ON musicTitles.musicID=musicAwards.musicID");
            while ( res.next() ) {
                System.out.printf( "%-30s", res.getString("genre") );
                System.out.printf( "%-30s", res.getString("artist") );
                System.out.printf( "%-50s", res.getString("award") );
                System.out.printf( "%s%n", res.getString("awardYear") );
            }
            System.out.println("\n\n");

            // print Rock songs
            System.out.println(" Songs from the `Rock` Genre");
            System.out.println("-----------------------------");
            System.out.printf( "%-5s%-30s%-30s%-30s%-50s%s%n", "ID", "Genre", "Artist", "Title", "Album", "Release Date" );
            System.out.println( new String(new char[160]).replace("\0", "=") );
            
            res = stmt.executeQuery("SELECT * FROM musicTitles WHERE genre='Rock' ORDER BY artist");
            while ( res.next() ) {
                System.out.printf( "%-5s", res.getInt("musicID") );
                System.out.printf( "%-30s", res.getString("genre") );
                System.out.printf( "%-30s", res.getString("artist") );
                System.out.printf( "%-30s", res.getString("title") );
                System.out.printf( "%-50s", res.getString("album") );
                System.out.printf( "%s%n", res.getInt("releaseDate") );
            }
            System.out.println("\n\n");
        }

        catch ( SQLException ex ) {
            System.err.println( "Error accessing database: " + ex.getMessage() );
        }

        // close connection
        try {
            if ( cnxn != null )
                cnxn.close();
        }

        catch ( SQLException ex ) {
            System.err.println( "Error closing database connection: " + ex.getMessage() );
        }
    }
}