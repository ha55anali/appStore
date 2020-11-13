package dbaseInterface;

import java.util.*;

//assume all parameters passed are correct
public interface devInterface
{
    //assume devID is valid
    public userDetails getDevDetails(int devID);

    //return 1 if exists
    public boolean checkDevExists(int devID);

    //appID will be assigned by database. pass dummy devID
    //will return devID assigned to new dev
    public int addUser(userDetails dev);

    //assume devID is valid
    public void removeUser(int devID);

    //assume devID and appID is valid
    public void addApp(int devID, int appID);

    //assume devID and appID is valid
    public void removeApp(int devID, int appID);

    //return 1 if dev has made appID
    public boolean checkAppDev(int devID, int appID);

    //return 1 if email is being used by some dev
    public boolean checkEmailExists(String email);

    //if valid login, return userID of dev
    //if invalid login, return -1
    public int authenticateUser(String email, String password);

    public List<Integer> getDevApps(int devID);
}
