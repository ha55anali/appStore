
package businessLayer;

import blInterface.App;
import java.util.List;


public interface individualAppInterface
{
    public App showDetails(int AppID);

    public String installApp(int AppID, int userID, int ver);

    public String updateApp(int AppID, int userID, int ver);

    public void removeApp(int AppID, int userID);

    public void addReview(int AppID, int userID, String Review);

    public void addRating(int AppID, int userID, int rating);
}
