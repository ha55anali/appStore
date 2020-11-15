package businessLayer;

// import blInterface.*;
// import dbaseInterface.*;
import java.util.*;
import java.util.Locale.Category;

public class App implements blInterface.individualAppInterface, blInterface.AppCollectionInterface {
    dbaseInterface.appInterface dbApp;
    dbaseInterface.userInterface dbUser;
    List<String> CategoryList;

    public App() {
        dbApp = dbaseInterface.dbFactory.getAppObject();
        dbUser = dbaseInterface.dbFactory.getUserObject();

        CategoryList = new ArrayList<String>();
        CategoryList.add("Games");
        CategoryList.add("Productivity");
        CategoryList.add("Education");
        CategoryList.add("Communication");
    }

    public blInterface.App showDetails(int AppID) {
        // check if valid app
        if (dbApp.checkAppExists(AppID)) {
            return returnblApp(AppID);
        } else {
            throw new IllegalArgumentException("App does not exist");
        }
    }

    public List<blInterface.App> showAllApps() {
        // get list of AppIDs
        List<Integer> appList = dbApp.getAllApps();

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i : appList)
            apps.add(returnblApp(i));

        return apps;
        // List<int> apps=new ArrayList<Integer>();
        // for(int i : appList)
        // Ratings.add(i);
        // return apps;
    }

    public List<blInterface.App> showAppsinCategory(String Category) {
        int validCat = 0;
        for (int i = 0; i < CategoryList.size(); ++i) {
            if (CategoryList.get(i).equals(Category)) {
                validCat = 1;
                break;
            }
        }
        if (validCat == 0)
            throw new IllegalArgumentException("Invalid category");
        // get list of AppIDs
        List<Integer> appList = dbApp.getAppsInCategory(Category);

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i : appList)
            apps.add(returnblApp(i));

        return apps;
    }

    public List<String> getCategoryList() {
        return CategoryList;
    }

    public void installApp(int AppID, int userID) {
        if (!dbApp.checkAppExists(AppID)) // valid app
            throw new IllegalArgumentException("Invalid app");
        else if (!dbUser.checkUserExists(userID)) // valid user
            throw new IllegalArgumentException("Invalid user");
        else {
            dbaseInterface.appDetails app = dbApp.getAppDetails(AppID);

            // add app to user data
            dbUser.addInstalledApp(AppID, userID, app.Version);
        }
    }

    public int updateApp(int AppID, int userID) {
        if (!dbApp.checkAppExists(AppID)) // check valid app
            return -1;
        else if (!dbUser.checkUserExists(userID)) // valid user
            throw new IllegalArgumentException("Invalid user");
        else {
            // get version of app installed
            int installedVer = dbUser.checkAppInstall(AppID, userID);
            // get latest version
            blInterface.App app = returnblApp(AppID);

            if (installedVer == app.Version)
                return -1;

            dbUser.addInstalledApp(AppID, userID, app.Version);
            return 1;
        }
    }

    public int removeApp(int AppID, int userID) {
        if (!dbApp.checkAppExists(AppID)) // check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (!dbUser.checkUserExists(userID)) // valid user
            throw new IllegalArgumentException("Invalid user");
        else {
            // check if user has app installed
            if (dbUser.checkAppInstall(AppID, userID) == -1)
                return -1;
            dbUser.removeInstalledApp(AppID, userID);
            return 1;
        }
    }

    public void addReview(int AppID, int userID, String Review) {
        if (dbApp.checkAppExists(AppID)) // check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (dbUser.checkUserExists(userID)) // valid user
            throw new IllegalArgumentException("Invalid user");
        else {
            // check if user has app installed
            if (dbUser.checkAppInstall(AppID, userID) == -1)
                throw new IllegalArgumentException("User does not have this app installed");
            dbApp.addComment(AppID, userID, Review);
        }
    }

    public void addRating(int AppID, int userID, int rating) {
        if (dbApp.checkAppExists(AppID)) // check valid app
            throw new IllegalArgumentException("Invalid app");
        else if (dbUser.checkUserExists(userID)) // valid user
            throw new IllegalArgumentException("Invalid user");
        else {
            // check if user has app installed
            if (dbUser.checkAppInstall(AppID, userID) == -1)
                throw new IllegalArgumentException("User does not have this app installed");
            dbApp.addRating(AppID, userID, rating);
        }
    }

    private blInterface.App returnblApp(int AppID) {

        dbaseInterface.appDetails app = dbApp.getAppDetails(AppID);

        blInterface.App appDet = new blInterface.App(app.AppID, app.Name, app.Description, app.Version, app.Category,
                app.Ratings, app.avgRatings, app.Reviews);

        return appDet;
    }

    // return app version
    // -1 if not installed
    public int checkAppInstalled(int appID, int userID)
    {
        return dbUser.checkAppInstall(appID, userID);
    }
}
