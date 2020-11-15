package dbaseInterface;

import java.util.*;

//assume all parameters passed are correct
public interface devInterface {
    public userDetails getDevDetails(int devID);

    public boolean checkDevExists(int devID);

    // appID will be assigned by database. pass dummy appID
    public int addUser(userDetails dev);

    public void removeUser(int devID);

    // change to void
    public int addApp(int devID, int appID, int ver);

    public void removeApp(int devID, int appID);

    // return 1 if dev has made appID
    public boolean checkAppDev(int devID, int appID);

    // return 1 if email is being used by some dev
    public boolean checkEmailExists(String email);

    public int authenticateUser(String email, String password);

    public List<Integer> getDevApps(int devID);
}
