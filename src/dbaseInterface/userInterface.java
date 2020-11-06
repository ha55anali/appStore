package dbaseInterface;

//assume all parameters passed are correct
public interface userInterface {
    public userDetails getUserDetails(int userID);

    public boolean checkUserExists(int userID);

    public void addUser(userDetails user);

    public void removeUser(int userID);

    public void addInstalledApp(int appID, int userID, int ver);

    public void addCard(int userID, int cardNo, int ExpYear);

    public void authenticateUser(int userID, String password);

    public void removeInstalledApp(int appID, int userID);

    // return app version
    // -1 if not installed
    public int checkAppInstall(int appID, int userID);

    public void setPaymentMethod(int userID, String method);

    public void changeCardDetails(int userID, int cardNo, int NewExpYear);

    //return 1 if email is being used by some user
    public boolean checkEmailExists(String email);

    // if user or app does not exists, throw invalidargumentexception
    public void removeCardDetails(int userID, int cardNo);

    public boolean checkUserCard(int userID, int cardNo);
}
