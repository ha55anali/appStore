package CLI;

import java.util.*;

public class userSession {

    public static void main(String args[])
    {
        new userSession();
    }

    int userID;
    Scanner cin;
    blInterface.userInterface userObj;
    blInterface.individualAppInterface indAppObj;
    blInterface.AppCollectionInterface appColObj;

    public userSession()
    {
        userID=-1;
        userObj = businessLayer.blFactory.getUserObject();
        appColObj = businessLayer.blFactory.getAppCollectionObject();
        indAppObj = businessLayer.blFactory.getIndividualAppObject();
        cin = new Scanner(System.in);

        loginUser();

        while(true)
        {
            int choice=showMainMenu();

            switch (choice)
            {
                case 1:
                    AppListMenu();
                    break;
                case 2:
                    CatListMenu();
                case 3:
                    showUserDetails();
                    break;
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }
    }

    private void CatListMenu()
    {
        showCategories();
        int choice = Integer.parseInt(cin.nextLine());

        List<blInterface.App> appList= appColObj.showAppsinCategory(appColObj.getCategoryList().get(choice));
        for (int c=0; c< appList.size(); ++c)
        {
            System.out.println(c+ ". "+ appList.get(c).Name);
        }

        choice = Integer.parseInt(cin.nextLine());

        showAppDetails(choice);
    }

    private void AppListMenu()
    {
        showAppList();
        int choice = Integer.parseInt(cin.nextLine());
        showAppDetails(choice);
    }

    private int showMainMenu()
    {
        System.out.println("1. all apps");
        System.out.println("2. app categories");
        System.out.println("3. user details");

        System.out.println("enter choice");
        int choice = Integer.parseInt(cin.nextLine());

        return choice;
    }

    private void loginUser()
    {
        while (userID== -1)
        {
            System.out.println("enter email");
            String email=cin.nextLine();

            System.out.println("enter passord");
            String password=cin.nextLine();

            userID=userObj.authenticateUser(email, password);

            if (userID==-1)
            {
                System.out.println("error, invalid login");
            }
        } 

    }

    private void showAppList()
    {
        System.out.println("list of apps");
        List<blInterface.App> appList=appColObj.showAllApps();

        for (int c=0; c< appList.size(); ++c)
        {
            System.out.println(c+ ". "+ appList.get(c).Name);
        }
    }

    private void showAppDetails(int appID)
    {
        List<blInterface.App> appList=appColObj.showAllApps();
        blInterface.App app= appList.get(appID);

        System.out.println("App Name: " + app.Name);
        System.out.println("Description: " + app.Description);
        System.out.println("Avg. Rating: " + app.avgRatings);
        System.out.println("Comments: " );

        //print comments
        for ( int c=0; c<app.Reviews.size(); ++c)
        {
            System.out.println("1. "+app.Reviews.get(c));
        }
    }

    private void showUserDetails()
    {
        blInterface.userDetails user= userObj.getUserDetails(userID);

        System.out.println("Name: "+user.Name);
        System.out.println("Email: "+user.email);
        System.out.println("Date of birth: "+user.DOB);
    }

    private void showCategories()
    {
        List<String> CatList = appColObj.getCategoryList();

        for (int i=0; i<CatList.size(); ++i)
        {
            System.out.println(i + ". " + CatList.get(i));
        }
    }

    private int getChoiceInput(int end)
    {
        int choice = Integer.parseInt(cin.nextLine());
        while (choice <= 0 && choice > end)
        {
            choice = Integer.parseInt(cin.nextLine());
        }

        return choice;
    }
}
