package dbaseInterface;

import java.util.*;

public interface appInterface
{
    public appDetails getAppDetails(int appID);

    //return appIds of all apps
    public List<Integer> getAllApps();

    //returns all appIds in the category
    public List<Integer> getAppsInCategory(String Category);

    public String getAppContent(int appID);

    public void addRating(int appID, int userID, int rating);

    public void addComment(int appID, int userID, int rating);

    public void addApp(appDetails app);

    public void updateApp(appDetails app);

    public void removeApp(int appID);
}
