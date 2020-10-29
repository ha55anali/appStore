package dbaseInterface;

public interface userInterface
{
    public userDetails getUserDetails(int userID);

    public void addUser(userDetails user);

    public void removeUser(int userID);

    public void addInstalledApp(int appID, int userID);

    public void removeInstalledApp(int appID, int userID);
}
