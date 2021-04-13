package Exam2;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Operating system:   Linux pop-os 5.8.0
*/

public class MadeForTvMovie extends Movie {
    
    private String releaseDate, network;

    public MadeForTvMovie(String title, String rating, String genre, String director, String star, String rel, String net) {
        super(title, rating, genre, director, star);

        releaseDate = rel;
        network = net;
    }

    public void setReleaseDate(String rel) {
        releaseDate = rel;
    }

    public void setNetwork(String net) {
        network = net;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getNetwork() {
        return network;
    }

    public boolean equals(MadeForTvMovie other) {

        // title, rating, genre, director, star equality
        if ( super.equals(other) ) {
            
            // releaseDate equality
            if ( releaseDate.equals( other.getReleaseDate() )) {
                
                // network equality
                if ( network.equals( other.getNetwork() )) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return super.toString() +
            "\nRelease\nDate:    " + getReleaseDate() +
            "\nNetwork:  " + getNetwork();
    }

    public static void main(String[] args) {
        MadeForTvMovie tvMovie1 = new MadeForTvMovie( "An Adventure in Space and Time", "PG", "Drama", "Terry McDonough", "David Bradley", "11/22/2013", "BBC" );
        MadeForTvMovie tvMovie2 = new MadeForTvMovie( "Brian's Song", "G", "Biography", "Buzz Kulik", "James Caan", "11/30/1971", "ABC" );

        System.out.println("Selected Made-For-Television movies:\n");
        System.out.println( tvMovie1.toString() + "\n" );
        System.out.println( tvMovie2.toString() + "\n" );
        
        System.out.println("Testing the \"equals\" method ...");
        if ( tvMovie1.equals(tvMovie2) ) {
            System.out.println("**The two Made-For-TV movies are the same.**");
        } else {
            System.out.println("**The two Made-For-TV movies are NOT the same.**");
        }



    }
}
