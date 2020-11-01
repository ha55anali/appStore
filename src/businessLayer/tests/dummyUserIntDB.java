package businessLayer.tests;

public class dummyUserIntDB implements dbaseInterface.userInterface
{
        // if user not found raise invalidArgumentException
    public dbaseInterface.userDetails getUserDetails(int userID)
        {
            return null;
        }

    //if user exists raise invalidargumentexception
    public void addUser(dbaseInterface.userDetails user)
        {

        }

    //if user does not exist throw invalidargumentexception
    public void removeUser(int userID)
        {

        }

    //if user or app does not exists, throw invalidargumentexception
    public void addInstalledApp(int appID, int userID)
        {
            System.out.println("App added");

        }

    //if user or app does not exists, throw invalidargumentexception
    public void removeInstalledApp(int appID, int userID)
        {

        }

}
