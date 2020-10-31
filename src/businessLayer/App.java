package businessLayer;

// import blInterface.*;
// import dbaseInterface.*;
import java.util.*;

class App implements blInterface.appInterface {
    dbaseInterface.appInterface dbApp;

    public App() {
        dbApp = dbaseInterface.dbFactory.getAppObject();
    }

    public blInterface.App showDetails(int AppID) {
        return returnblApp(AppID);
    }

    public List<blInterface.App> showAllApps() {
        //get list of AppIDs
        List<Integer> appList = dbApp.getAllApps();

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i: appList)
            apps.add(returnblApp(i));

        return apps;
        // List<int> apps=new ArrayList<Integer>();
        // for(int i : appList)
        //     Ratings.add(i);
        // return apps;
    }

    public List<blInterface.App> showAppsinCategory(String Category) {
        return null;
    }

    public String installApp(int AppID, int userID) {
        return null;
    }

    public String updateApp(int AppID, int userID) {
        return null;
    }

    public void removeApp(int AppID, int userID) {
    }

    public void addReview(int AppID, int userID, String Review) {
    }

    public void addRating(int AppID, int userID, int rating) {
    }

    private blInterface.App returnblApp(int AppID) {
        dbaseInterface.appDetails app = dbApp.getAppDetails(AppID);

        blInterface.App appDet = new blInterface.App(app.AppID, app.Version, app.Ratings, app.avgRatings, app.Reviews);

        return appDet;
    }
}
