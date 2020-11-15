
package blInterface;

import java.util.List;


public interface individualAppInterface
{
    public App showDetails(int AppID);

    //install latest available version
    //if already installed, throw exception
    public void installApp(int AppID, int userID) throws IllegalArgumentException;

    //if not on latest app, install latest app
    //if update successful, return 1
    //if latest or other error, return -1
    public int updateApp(int AppID, int userID);

    //if invalid appID, return -1
    public int removeApp(int AppID, int userID);

    //if invalid appID, throw exception
    public void addReview(int AppID, int userID, String Review) throws IllegalArgumentException;

    //if invalid appID, or rating greater than 5, throw exception
    public void addRating(int AppID, int userID, int rating) throws IllegalArgumentException;

    // return app version
    // -1 if not installed
    public int checkAppInstalled(int appID, int userID);
}
