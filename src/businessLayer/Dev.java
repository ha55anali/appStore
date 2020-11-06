package businessLayer;

public class Dev implements blInterface.devInterface
{
    private dbaseInterface.devInterface devObj;
    private dbaseInterface.appInterface appObj;

    public Dev()
    {
        devObj= dbaseInterface.dbFactory.getDevObject();
        appObj = dbaseInterface.dbFactory.getAppObject();
    }
    public void addDev(blInterface.userDetails dev)
    {
        if (Boolean.compare(devObj.checkEmailExists(dev.email), true) ==1)
        {
            throw new IllegalArgumentException("Email is already in use");
        }

        //create bl  App object
        dbaseInterface.userDetails dbDev = new dbaseInterface.userDetails(dev.Name, dev.userID, dev.DOB, dev.email, dev.password);
        devObj.addUser(dbDev);
    }

    public void removeDev(int devID)
    {
        //check dev exists
        if (Boolean.compare(devObj.checkDevExists(devID), false) ==1)
            {
                throw new IllegalArgumentException("Dev does not exist");
            }
        devObj.removeUser(devID);

    }

    public void addApp(int devID, blInterface.App newApp)
    {
        //check dev exists
        if (Boolean.compare(devObj.checkDevExists(devID), false) ==1)
            {
                throw new IllegalArgumentException("Dev does not exist");
            }

        dbaseInterface.appDetails appDet = new dbaseInterface.appDetails(newApp.AppID, newApp.Name, newApp.Description, newApp.Version, newApp.Ratings, newApp.avgRatings, newApp.Reviews);
        int appID = appObj.addApp(appDet);
        devObj.addApp(devID, appID);

    }

    public void removeApp( int devID, int appID)
    {
        //check dev exists
        if (Boolean.compare(devObj.checkDevExists(devID), false) ==1)
            {
                throw new IllegalArgumentException("Dev does not exist");
            }
        //check app exists
        if (Boolean.compare(appObj.checkAppExists(appID), false) ==1)
            {
                throw new IllegalArgumentException("App does not exist");
            }
        if (Boolean.compare(devObj.checkAppDev(devID, appID), false) ==1)
            {
                throw new IllegalArgumentException("Dev has no such app");
            }
        devObj.removeApp(devID, appID);
    }

    public void updateApp(int devID, int appID, int version, String content)
    {
        //check dev exists
        if (Boolean.compare(devObj.checkDevExists(devID), false) ==1)
            {
                throw new IllegalArgumentException("Dev does not exist");
            }
        //check app exists
        if (Boolean.compare(appObj.checkAppExists(appID), false) ==1)
            {
                throw new IllegalArgumentException("App does not exist");
            }
        if (Boolean.compare(devObj.checkAppDev(devID, appID), false) ==1)
            {
                throw new IllegalArgumentException("Dev has no such app");
            }
        dbaseInterface.appDetails appDet = appObj.getAppDetails(appID);
        appDet.Version = version;
        appObj.updateApp(appDet, content);
    }

}
