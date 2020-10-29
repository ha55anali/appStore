package dbaseInterface;

import java.util.*;

public interface appInterface
{
    //if app does not exist throw invalidargumentexception
    public appDetails getAppDetails(int appID);

    //return appIds of all apps
    public List<Integer> getAllApps();

    //returns all appIds in the category
    public List<Integer> getAppsInCategory(String Category);

    //if app does not exist throw invalidargumentexception
    public String getAppContent(int appID);

    //if app, user does not exist throw invalidargumentexception
    //rating passed should be between 0 and 5
    public void addRating(int appID, int userID, int rating);

    //if app, user does not exist throw invalidargumentexception
    public void addComment(int appID, int userID, String comment);

    //if app exists, throw invalidargumentexception
    public void addApp(appDetails app);

    //if add does not exist, throw invalidargumentexception
    public void updateApp(appDetails app);

    //if app does not exist, throw invalidargumentexception
    public void removeApp(int appID);
}
