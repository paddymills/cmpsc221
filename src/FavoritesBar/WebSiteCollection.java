package FavoritesBar;

/**
    Author:             Patrick Miller
    E-mail:             pjm6196@psu.edu
    Course:             CMPSC 221
    Assignment:         Project 3
    Due date:           3/30/2021
    File:               WebSiteCollection.java
    Purpose:            A collection of WebSite instances
    Compiler/IDE:       openjdk-15/VisualStudioCode
    Operating system:   Linux pop-os 5.8.0
    Reference(s):       OpenJDK 15 (devdocs.io) (https://devdocs.io/openjdk~15/);
*/

import java.util.ArrayList;

public class WebSiteCollection {

    private ArrayList<WebSite> sites;

    /**
     * WebSiteCollection constructor
     * 
     * creates a new object to hold a list of WebSite objects
     */
    public WebSiteCollection() {
        sites = new ArrayList<WebSite>();
    }

    public boolean addWebSite(WebSite ws) {
        return sites.add(ws);
    }

    public boolean removeWebSite(WebSite ws) {
        return sites.remove(ws);
    }

    /**
     * gets all the available topics
     * 
     * @return ArrayList of the available topics as strings
     */
    public ArrayList<String> getTopics() {
        ArrayList<String> topics = new ArrayList<String>();

        for (WebSite site : sites) {
            if ( !topics.contains(site.getTopic()) )
                topics.add(site.getTopic());
        }

        return topics;
    }

    /**
     * returns the WebSites in a new ArrayList
     * 
     * @return websites as new ArrayList
     */
    public ArrayList<WebSite> getSites() {
        ArrayList<WebSite> returnSites = new ArrayList<WebSite>();

        for (WebSite site : sites) {
            returnSites.add(site);
        }

        return returnSites;
    }

    /**
     * gets all the available websites for a given topic
     * 
     * @param topic the topic to query websites for
     * @return an Arraylist of all the websites with the matching topic
     */
    public ArrayList<WebSite> getSitesWithTopic(String topic) {
        ArrayList<WebSite> returnSites = new ArrayList<WebSite>();

        for (WebSite site : sites) {
            if ( site.getTopic().equals(topic) )
                returnSites.add(site);
        }

        return returnSites;
    }
    
}
