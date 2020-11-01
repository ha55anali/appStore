package dbaseInterface;

import java.util.*;

//assume all parameters passed are correct
public interface appInterface
{
    public appDetails getAppDetails(int appID);

    //return appIds of all apps
    public List<Integer> getAllApps();

    //returns all appIds in the category
    public List<Integer> getAppsInCategory(String Category);

    public String getAppContent(int appID);

    //if rating present, update it
    public void addRating(int appID, int userID, int rating);

    //if comment present, update it
    public void addComment(int appID, int userID, String comment);

    //appID will be assigned by database. pass dummy appID
    // apps can have same names
    public boolean addApp(appDetails app);

    public void updateApp(appDetails app, String content);

    public void removeApp(int appID);

    public boolean checkAppExists(int appID);

}
