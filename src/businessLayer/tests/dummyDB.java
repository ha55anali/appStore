package businessLayer.tests;

import dbaseInterface.*;
import java.util.*;

public class dummyDB implements appInterface {
    // if app does not exist throw invalidargumentexception
    public appDetails getAppDetails(int appID) {
        if (appID == 5)
            return new appDetails(1, 1, new ArrayList<Integer>(), 0, new ArrayList<String>());
        else if (appID == 10)
            return new appDetails(2, 2, new ArrayList<Integer>(), 0, new ArrayList<String>());
        else
            throw new IllegalArgumentException("hello");
    }

    // return appIds of all apps
    public List<Integer> getAllApps() {
        List<Integer> a = new ArrayList<Integer>();
        a.add(5);
        a.add(10);
        return a;
    }

    // returns all appIds in the category
    public List<Integer> getAppsInCategory(String Category) {
        return null;
    }

    // if app does not exist throw invalidargumentexception
    public String getAppContent(int appID) {
        return null;
    }

    // if app, user does not exist throw invalidargumentexception
    // rating passed should be between 0 and 5
    public void addRating(int appID, int userID, int rating) {

    }

    // if app, user does not exist throw invalidargumentexception
    public void addComment(int appID, int userID, String comment) {

    }

    // if app exists, throw invalidargumentexception
    public void addApp(appDetails app) {

    }

    // if add does not exist, throw invalidargumentexception
    public void updateApp(appDetails app) {

    }

    // if app does not exist, throw invalidargumentexception
    public void removeApp(int appID) {

    }

}
