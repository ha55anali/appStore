package businessLayer;

import java.util.*;

public class Dev implements blInterface.devInterface {
    private dbaseInterface.devInterface devObj;
    private dbaseInterface.appInterface appObj;

    public Dev() {
        devObj = dbaseInterface.dbFactory.getDevObject();
        appObj = dbaseInterface.dbFactory.getAppObject();
    }

    public int addDev(blInterface.userDetails dev) {
        if (devObj.checkEmailExists(dev.email)) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // create bl App object
        dbaseInterface.userDetails dbDev = new dbaseInterface.userDetails(dev.Name, dev.userID, dev.DOB, dev.email,
                dev.password);
        return devObj.addUser(dbDev);
    }

    public void removeDev(int devID) {
        // check dev exists
        if (!devObj.checkDevExists(devID)) {
            throw new IllegalArgumentException("Dev does not exist");
        }
        devObj.removeUser(devID);

    }

    public void addApp(int devID, blInterface.App newApp) {
        // check dev exists
        if (!devObj.checkDevExists(devID)) {
            throw new IllegalArgumentException("Dev does not exist");
        }

        dbaseInterface.appDetails appDet = new dbaseInterface.appDetails(newApp.AppID, newApp.Name, newApp.Description,
                newApp.Version, newApp.Category, newApp.Ratings, newApp.avgRatings, newApp.Reviews);
        int appID = appObj.addApp(appDet);
        devObj.addApp(devID, appID);

    }

    public void removeApp(int devID, int appID) {
        // check dev exists
        if (!devObj.checkDevExists(devID)) {
            throw new IllegalArgumentException("Dev does not exist");
        }
        // check app exists
        if (!appObj.checkAppExists(appID)) {
            throw new IllegalArgumentException("App does not exist");
        }
        if (!devObj.checkAppDev(devID, appID)) {
            throw new IllegalArgumentException("Dev has no such app");
        }
        devObj.removeApp(devID, appID);
    }

    public void updateApp(int devID, blInterface.App appDetails) {
        // check dev exists
        if (!devObj.checkDevExists(devID)) {
            throw new IllegalArgumentException("Dev does not exist");
        }
        // check app exists
        if (!appObj.checkAppExists(appDetails.AppID)) {
            throw new IllegalArgumentException("App does not exist");
        }
        if (!devObj.checkAppDev(devID, appDetails.AppID)) {
            throw new IllegalArgumentException("Dev has no such app");
        }
        dbaseInterface.appDetails appDet = new dbaseInterface.appDetails(appDetails.AppID, appDetails.Name,
                appDetails.Description, appDetails.Version, appDetails.Category, appDetails.Ratings,
                appDetails.avgRatings, appDetails.Reviews);
        appObj.updateApp(appDet);
    }

    public int authenticateUser(String email, String password) {
        return devObj.authenticateUser(email, password);
    }

    public List<blInterface.App> getDevApps(int devID) {
        // get list of AppIDs
        List<Integer> appList = devObj.getDevApps(devID);

        List<blInterface.App> apps = new ArrayList<blInterface.App>();
        for (int i : appList)
            apps.add(returnblApp(i));

        return apps;
    }

    public blInterface.userDetails getDevDetails(int devID) {
        // check dev exists
        if (!devObj.checkDevExists(devID)) {
            throw new IllegalArgumentException("Dev does not exist");
        }

        dbaseInterface.userDetails dev = devObj.getDevDetails(devID);

        blInterface.userDetails dbDev = new blInterface.userDetails(dev.Name, dev.userID, dev.DOB, dev.email,
                dev.password);
        return dbDev;
    }

    private blInterface.App returnblApp(int AppID) {

        dbaseInterface.appDetails app = appObj.getAppDetails(AppID);

        blInterface.App appDet = new blInterface.App(app.AppID, app.Name, app.Description, app.Version, app.Category,
                app.Ratings, app.avgRatings, app.Reviews);

        return appDet;
    }
}
