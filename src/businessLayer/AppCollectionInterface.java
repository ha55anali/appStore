
package businessLayer;

import blInterface.App;
import java.util.List;


public interface AppCollectionInterface
{
    public List<App> showAllApps();

    public List<App> showAppsinCategory(String Category);
}
