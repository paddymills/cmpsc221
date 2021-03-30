
package FavoritesBar;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 3
    Due date:           3/30/2021
    File:               WebSite.java
    Purpose:            WebSite instance class
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

/**
 * WebSite object
 * 
 * holds an objects:
 *  - Name
 *  - URL
 *  - Rating (* through *****)
 *  - Topic/Category
 *  - Date last visited
 */
public class WebSite {

    private String name;
    private String url;
    private int rating;
    private String topic;
    private String dateLastVisited;

    /**
     * WebSite constructor
     * 
     * @param name
     * @param url
     * @param rating
     * @param topic
     * @param dateLastVisited
     */
    public WebSite(String siteName, String siteUrl, int siteRating, String siteTopic, String lastVisited) {
        setName(siteName);
        setUrl(siteUrl);
        setRating(siteRating);
        setTopic(siteTopic);
        setDate(lastVisited);
    }

    public WebSite(String siteName, String siteUrl, String siteRating, String siteTopic, String lastVisited) {
        setName(siteName);
        setUrl(siteUrl);
        setRating(siteRating);
        setTopic(siteTopic);
        setDate(lastVisited);
    }

    /**
     * sets the website name
     * 
     * @param n topic as a String
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * sets the website url
     * 
     * @param u url as a String
     */
    public void setUrl(String u) {
        url = u;
    }

    /**
     * sets the website rating
     * 
     * @param r rating as an integer (1-5)
     */
    public void setRating(int r) {
        if (r < 1 || r > 5)
            System.out.println("Invalid rating. Must be 1-5");

        else
            rating = r;
    }

    /**
     * sets the website rating
     * 
     * @param r rating as an integer (1-5)
     */
    public void setRating(String r) {
        int newRating = 0;

        for (char c : r.toCharArray()) {
            if (c == '*')
                newRating++;
            else {
                System.out.println("Unexpected character in rating (" + c + ")");
                return;
            }
        }

        rating = newRating;
    }

    /**
     * sets the website topic
     * 
     * @param t topic as a String
     */
    public void setTopic(String t) {
        topic = t;
    }

    /**
     * sets the website date last visited
     * 
     * @param d date as a String
     */
    public void setDate(String d) {
        dateLastVisited = d;
    }

    /**
     * gets the website name
     * 
     * @return the website name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the website url
     * 
     * @return the website url
     */
    public String getUrl() {
        return url;
    }

    /**
     * gets the website rating
     * 
     * @return the website rating as a number of stars
     */
    public String getRating() {
        String ratingString = new String();

        for (int i = 0; i < rating; i++)
            ratingString += "*";

        return ratingString;
    }

    /**
     * gets the website topic
     * 
     * @return the website topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * gets the website last visited date
     * 
     * @return the date the website was last visited
     */
    public String getDate() {
        return dateLastVisited;
    }

    /**
     * gets the object's representation
     * 
     * @return the website representation as a string
     */
    public String toString() {
        String stringRepr = new String();

        stringRepr += "Name:            " + getName() + "\n";
        stringRepr += "Address:         " + getUrl() + "\n";
        stringRepr += "Topic:           " + getTopic() + "\n";
        stringRepr += "Rating:          " + getRating() + "\n";
        stringRepr += "Last Visited:    " + getDate();
        
        return stringRepr;
    }

    /**
     * gets the object's representation (name and URL)
     * 
     * @return the website representation as a string in one line
     */
    public String toOneLineString() {
        return getName() + " (" + getUrl() + ")";
    }
    
}