package businessLayer.tests;

import dbaseInterface.*;
import java.util.*;

public class dummyDB implements appInterface
{
    //if app does not exist throw invalidargumentexception
    public appDetails getAppDetails(int appID)
    {
        if (appID == 6)
            return new appDetails(6, 1, new ArrayList<Integer>(), 0, new ArrayList<String>() );
        else if (appID == 10)
            return new appDetails(2, 2, new ArrayList<Integer>(), 0, new ArrayList<String>() );
        else
            throw new IllegalArgumentException("hello");
    }

    //return appIds of all apps
    public List<Integer> getAllApps()
    {
        List<Integer> a= new ArrayList<Integer>();
        a.add(5);
        a.add(10);
        return a;
    }

    //returns all appIds in the category
    public List<Integer> getAppsInCategory(String Category)
    {
        if (Category == "a") {

            List<Integer> a = new ArrayList<Integer>();
            a.add(6);
            a.add(10);
            return a;

        }

        return null;
    }

    //if app does not exist throw invalidargumentexception
    public String getAppContent(int appID)
    {
        if (appID == 1)
            return "oeu";
        return "errer";
    }

    //if app, user does not exist throw invalidargumentexception
    //rating passed should be between 0 and 5
    public void addRating(int appID, int userID, int rating)
    {

    }

    //if app, user does not exist throw invalidargumentexception
    public void addComment(int appID, int userID, String comment)
    {

    }

    //if app exists, throw invalidargumentexception
    public void addApp(appDetails app)
    {

    }

    //if add does not exist, throw invalidargumentexception
    public void updateApp(appDetails app)
    {

    }

    //if app does not exist, throw invalidargumentexception
    public void removeApp(int appID)
    {

    }

}
