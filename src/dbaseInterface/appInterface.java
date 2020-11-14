package dbaseInterface;

import java.util.*;

//assume all parameters passed are correct
public interface appInterface
{
    //assume appID is valid
    public appDetails getAppDetails(int appID);

    //return appIds of all apps
    public List<Integer> getAllApps();

    //returns all appIds in the category
    //if no apps in category return empty list
    public List<Integer> getAppsInCategory(String Category);

    //if rating present, update it
    public void addRating(int appID, int userID, int rating);

    //if comment present, update it
    public void addComment(int appID, int userID, String comment);

    //appID will be assigned by database. pass dummy appID
    // apps can have same names
    // return appID assigned
    public int addApp(appDetails app);

    //can change name
    //version
    //category
    //Description
    //asume appID is valid
    //ignore rating and comment in appDetails
    public void updateApp(appDetails app);

    //asume app exists
    public void removeApp(int appID);

    public boolean checkAppExists(int appID);

}