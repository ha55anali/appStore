package blInterface;

import java.util.*;

public interface devInterface {
    // if invalid userID, exception is thrown
    public userDetails getDevDetails(int devID);

    // userID is assigned automatically
    // userID of the new user is returned
    // -1 is returned if email is not unique
    public int addDev(userDetails dev);

    // throws exception if devID is invalid
    public void removeDev(int devID) throws IllegalArgumentException;

    // pass dummy value to appID
    // dummy values to rating and comments
    // if devID is invalid throw exception
    public void addApp(int devID, App newApp);

    // remove app
    // if appID is invalid throw exception
    public void removeApp(int devID, int appID) throws IllegalArgumentException;

    // if invalid appID throw exception
    // if new version is not greater than current, throw exception
    public void updateApp(int devID, blInterface.App appDetails);

    // return userID if valid login
    // returns -1 if invalid
    public int authenticateUser(String email, String password);

    public List<App> getDevApps(int devID);
}
