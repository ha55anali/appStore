package dbaseInterface;

//assume all parameters passed are correct
public interface userInterface
{
    public userDetails getUserDetails(int userID);

    public boolean checkUserExists(int userID);

    public int addUser(userDetails user);

    public void removeUser(int userID);

    public void addInstalledApp(int appID, int userID, int ver);

    public int addCard(int userID, int cardNo, int ExpYear);

    public boolean authenticateUser(int userID, String password);

    public void removeInstalledApp(int appID, int userID);

    //return app version
    // -1 if not installed
    public int checkAppInstall(int appID, int userID);

    public void setPaymentMethod(int userID, String method);

    public boolean changeCardDetails(int userID, int cardNo, int NewExpYear);

    //return 1 if email is being used by some user
    public boolean checkEmailExists(String email);

    // if user or app does not exists, throw invalidargumentexception
    public boolean removeCardDetails(int userID, int cardNo);

    public boolean checkUserCard(int userID, int cardNo);
}
