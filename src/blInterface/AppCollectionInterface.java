
package blInterface;

import java.util.List;


public interface AppCollectionInterface
{
    public List<App> showAllApps();

    //if invalid category, throw exception
    public List<App> showAppsinCategory(String Category) throws IllegalArgumentException;

    public List<String> getCategoryList();
}
