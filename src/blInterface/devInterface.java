package blInterface;

public interface devInterface
{
    public void addDev(userDetails dev);

    public void removeDev(int devID);

    public void addApp(int devID, App newApp);

    public void removeApp( int devID, int appID);

    public void updateApp(int devID, int appID, int version, String content);
}
