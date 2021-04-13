package Exam2;

// Movie.java

// package com.mycompany.movieandmadefortvmovie;

/*
Author:			Wanda Kunkle
E-mail:			wmk12@psu.edu
Course:			CMPSC 221
Assignment:		Exam 2
Due date:		4/13/2021
File:			Movie.java
Purpose:		Class that represents a movie and its attributes
Compiler/IDE:	Java SE Development Kit 8u261/NetBeans IDE 11.3
Operating
system:			MS Windows 10 Home
Reference(s):	Java 8 API - Oracle Documentation
				(http://docs.oracle.com/javase/8/docs/api/);
*/

public class Movie {

    private String movieTitle;
    private String movieRating;
    private String movieGenre;
    private String movieDirector;
    private String movieStar;

    /**
     * No-argument constructor that sets each of the instance
     * variables to the empty string
     */
    public Movie() {
        movieTitle = "";
        movieRating = "";
        movieGenre = "";
        movieDirector = "";
        movieStar = "";
    }

    /**
     * Five-argument constructor that sets each of the instance
     * variables to the corresponding parameter provided
     * @param title the movie title
     * @param rating the movie rating
     * @param genre the movie genre
     * @param director the movie director
     * @param star the movie star
     */
    public Movie(String title, String rating, String genre, String director, String star) {
        movieTitle = title;
        movieRating = rating;
        movieGenre = genre;
        movieDirector = director;
        movieStar = star;
    }

    /**
     * Set movieTitle to title
     * @param title the movie title
     */
    public void setMovieTitle(String title) {
        movieTitle = title;
    }

    /**
     * Get the movieTitle
     * @return a String representing the movie title
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Set movieRating to rating
     * @param rating the movie rating
     */
    public void setMovieRating(String rating) {
        movieRating = rating;
    }

    /**
     * Get the movieRating
     * @return a String representing the movie rating
     */
    public String getMovieRating() {
        return movieRating;
    }

    /**
     * Set movieGenre to genre
     * @param genre the movie genre
     */
    public void setMovieGenre(String genre) {
        movieGenre = genre;
    }

    /**
     * Get the movieGenre
     * @return a String representing the movie genre
     */
    public String getMovieGenre() {
        return movieGenre;
    }

    /**
     * Set movieDirector to director
     * @param director the movie director
     */
    public void setMovieDirector(String director) {
        movieDirector = director;
    }

    /**
     * Get the movieDirector
     * @return a String representing the movie director
     */
    public String getMovieDirector() {
        return movieDirector;
    }

    /**
     * Set movieStar to star
     * @param star the movie star
     */
    public void setMovieStar(String star) {
        movieStar = star;
    }

    /**
     * Get the movieStar
     * @return a String representing the movie star
     */
    public String getMovieStar() {
        return movieStar;
    }

    /**
     * Represent all movie information as a single String
     * @return a String representation of all movie information
     */
    @Override
    public String toString() {
        return "\nTitle:    " + getMovieTitle()+
            "\nRating:   " + getMovieRating() +
            "\nGenre:    " + getMovieGenre() +
            "\nDirector: " + getMovieDirector() +
            "\nStar:     " + getMovieStar();
    }

    /**
     * Test two movie objects for equality
     * @param otherMovie the movie to be tested
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object otherMovie)
    {
        if (otherMovie == null)
            return false;
        else if (getClass() != otherMovie.getClass())
            return false;
        else {
            Movie legitMovie = (Movie)otherMovie;
            return (getMovieTitle().equalsIgnoreCase(legitMovie.getMovieTitle())
                && getMovieRating().equalsIgnoreCase(legitMovie.getMovieRating())
                && getMovieGenre().equalsIgnoreCase(legitMovie.getMovieGenre())
                && getMovieDirector().equalsIgnoreCase(legitMovie.getMovieDirector())
                && getMovieStar().equalsIgnoreCase(legitMovie.getMovieStar()));
        }
    }

}
