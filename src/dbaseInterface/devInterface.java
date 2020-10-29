package dbaseInterface;

public interface devInterface
{
    public userDetails getDevDetails(int devID);

    public void addUser(userDetails dev);

    public void removeUser(int devID);

    public void addApp(int devID, int appID);

    public void removeApp(int devID, int appID);
}
