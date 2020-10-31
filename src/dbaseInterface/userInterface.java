package dbaseInterface;

public interface userInterface {
    // if user not found raise invalidArgumentException
    public userDetails getUserDetails(int userID);

    // if user exists raise invalidargumentexception
    public void addUser(userDetails user);

    // if user does not exist throw invalidargumentexception
    public void removeUser(int userID);

    // if user does not exist throw invalidargumentexception
    public void addCard(int cardNo, int ExpYear);

    // if user does not exist throw invalidargumentexception
    public void authenticateUser(int userID);

    // if user or app does not exists, throw invalidargumentexception
    public void addInstalledApp(int appID, int userID);

    // if user or app does not exists, throw invalidargumentexception
    public void removeInstalledApp(int appID, int userID);

    // if user or app does not exists, throw invalidargumentexception
    public void setPaymentMethod(String method);

    // if user or app does not exists, throw invalidargumentexception
    public void changeCardDetails(int cardNo, int NewExpYear);

    // if user or app does not exists, throw invalidargumentexception
    public void removeCardDetails(int cardNo);
}
