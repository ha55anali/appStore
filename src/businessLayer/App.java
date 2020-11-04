package businessLayer;

// import blInterface.*;
// import dbaseInterface.*;
import java.util.*;

class App implements blInterface.appInterface {
    dbaseInterface.appInterface dbApp;
    dbaseInterface.userInterface dbUser;

    public App() {
        dbApp = dbaseInterface.dbFactory.getAppObject();
        dbUser = dbaseInterface.dbFactory.getUserObject();
    }

    public blInterface.App showDetails(int AppID) {
        //check if valid app
        if (Boolean.compare(dbApp.checkAppExists(AppID), true) == 0) {
            return returnblApp(AppID);
        } else {
            throw new IllegalArgumentException("App does not exist");
        }
    }

    public List<blInterface.App> showAllApps() {
        //get list of AppIDs
        List<Integer> appList = dbApp.getAllApps();

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i : appList)
            apps.add(returnblApp(i));

        return apps;
        // List<int> apps=new ArrayList<Integer>();
        // for(int i : appList)
        //     Ratings.add(i);
        // return apps;
    }

    public List<blInterface.App> showAppsinCategory(String Category) {
        //get list of AppIDs
        List<Integer> appList = dbApp.getAppsInCategory(Category);

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i : appList)
            apps.add(returnblApp(i));

        return apps;
    }

    public String installApp(int AppID, int userID, int ver) {
        if (Boolean.compare(dbApp.checkAppExists(AppID), true) == 0) //valid app
            throw new IllegalArgumentException("Invalid app");
        else if (Boolean.compare(dbUser.checkUserExists(userID), true)==0 ) //valid user
            throw new IllegalArgumentException("Invalid user");
        else
        {
            //add app to user data
            dbUser.addInstalledApp(AppID, userID, ver);
            return dbApp.getAppContent(AppID);
        }
    }

    public String updateApp(int AppID, int userID) {
        if (Boolean.compare(dbApp.checkAppExists(AppID), true) == 0) //check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (Boolean.compare(dbUser.checkUserExists(userID), true)==0 ) //valid user
            throw new IllegalArgumentException("Invalid user");
        else
        {
            //get version of app installed
            int installedVer = dbUser.checkAppInstall(AppID, userID);
            //get latest version
            blInterface.App app = returnblApp(AppID);

            if (installedVer == app.Version)
                throw new IllegalArgumentException("latest app already installed");

            //return latest app content
            return dbApp.getAppContent(AppID);

        }
    }

    public void removeApp(int AppID, int userID) {
        if (Boolean.compare(dbApp.checkAppExists(AppID), true) == 0) //check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (Boolean.compare(dbUser.checkUserExists(userID), true)==0 ) //valid user
            throw new IllegalArgumentException("Invalid user");
        else
        {
            //check if user has app installed
            if (dbUser.checkAppInstall(AppID, userID) == -1)
                throw new IllegalArgumentException("User does not have this app installed");
            dbUser.removeInstalledApp(AppID, userID);
        }
    }

    public void addReview(int AppID, int userID, String Review) {
        if (Boolean.compare(dbApp.checkAppExists(AppID), true) == 0) //check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (Boolean.compare(dbUser.checkUserExists(userID), true)==0 ) //valid user
            throw new IllegalArgumentException("Invalid user");
        else
        {
            //check if user has app installed
            if (dbUser.checkAppInstall(AppID, userID) == -1)
                throw new IllegalArgumentException("User does not have this app installed");
            dbApp.addComment(AppID, userID, Review);
        }
    }

    public void addRating(int AppID, int userID, int rating) {
    }

    private blInterface.App returnblApp(int AppID) {

        dbaseInterface.appDetails app = dbApp.getAppDetails(AppID);

        blInterface.App appDet = new blInterface.App(app.AppID, app.Name, app.Description, app.Version, app.Ratings, app.avgRatings, app.Reviews);

        return appDet;
    }
}
