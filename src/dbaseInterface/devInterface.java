package dbaseInterface;

//assume all parameters passed are correct
public interface devInterface {
    public userDetails getDevDetails(int devID);

    public boolean checkDevExists(int devID);

    // appID will be assigned by database. pass dummy appID
    public int addUser(userDetails dev);

    public void removeUser(int devID);

    public int addApp(int devID, int appID, int ver);

    public void removeApp(int devID, int appID);

    // return 1 if dev has made appID
    public boolean checkAppDev(int devID, int appID);

    // return 1 if email is being used by some dev
    public boolean checkEmailExists(String email);
}
