package CLI;

import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.time.*;

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
    List<blInterface.App> appList;

    public userSession()
    {
        userID=-1;
        userObj = businessLayer.blFactory.getUserObject();
        appColObj = businessLayer.blFactory.getAppCollectionObject();
        indAppObj = businessLayer.blFactory.getIndividualAppObject();
        cin = new Scanner(System.in);

        System.out.println("enter 1 to login, enter 2 to signup, -1 to exit");
        int choice = getChoiceInput(2);
        if (choice == 1)
            loginUser();
        else if (choice ==2 )
        {
            signUp();
            loginUser();
        }
        else
            System.exit(1);

        while(true)
        {
            appList = appColObj.showAllApps();

            choice=showMainMenu();

            switch (choice)
            {
                case -1:
                    System.exit(1);
                    break;
                case 1:
                    AppListMenu();
                    break;
                case 2:
                    CatListMenu();
                    break;
                case 3:
                    showUserDetails();
                    break;
                case 4:
                    userObj.removeUser(userID);
                    System.exit(1);
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
        int choice= getChoiceInput(4);
        if (choice == -1)
            return;

        List<blInterface.App> appCatList= appColObj.showAppsinCategory(appColObj.getCategoryList().get(choice));
        for (int c=0; c< appCatList.size(); ++c)
        {
            System.out.println(c+ ". "+ appCatList.get(c).Name);
        }

        choice= getChoiceInput(appCatList.size()-1);
        if (choice != -1)
            showAppDetails(appCatList.get(choice).AppID);
    }

    private void AppListMenu()
    {
        showAppList();
        int choice = getChoiceInput(appList.size()-1);
        if (choice != -1)
            showAppDetails(appList.get(choice).AppID);
    }

    private int showMainMenu()
    {
        System.out.println("1. all apps");
        System.out.println("2. app categories");
        System.out.println("3. user details");
        System.out.println("4. delete user");
        System.out.println("-1. exit");

        return getChoiceInput(3);
    }

    private void loginUser()
    {
        System.out.println("login");
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

        for (int c=0; c< appList.size(); ++c)
        {
            System.out.println(c+ ". "+ appList.get(c).Name);
        }
        System.out.println("-1: exit");
    }

    private void showAppDetails(int appID)
    {
        blInterface.App app= indAppObj.showDetails(appID);

        System.out.println("App Name: " + app.Name);
        System.out.println("Description: " + app.Description);
        System.out.println("Avg. Rating: " + app.avgRatings);
        System.out.println("Comments: " );

        //print comments
        for ( int c=0; c<app.Reviews.size(); ++c)
        {
            System.out.println("1. "+app.Reviews.get(c));
        }

        System.out.println("1. install app");
        System.out.println("2. delete app");
        System.out.println("3. update app");
        System.out.println("-1. back");
        int choice= getChoiceInput(3);

        switch (choice)
        {
            case 1:
                try{
                    indAppObj.installApp(appID, userID);
                    System.out.println("installed");
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                if (indAppObj.removeApp(appID, userID)== -1)
                    System.out.println("app not installed");
                System.out.println("removed");
                break;
            case 3:
                if (indAppObj.updateApp(appID, userID) == -1)
                    System.out.println("already on latest version");
                System.out.println("updated");
                break;
            case -1:
                break;

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
    System.out.println("-1: back ");
    }

    private int getChoiceInput(int end)
    {
        int choice = Integer.parseInt(cin.nextLine());
        while (choice < 0 && choice > end  && choice != -1)
        {
            choice = Integer.parseInt(cin.nextLine());
        }

        return choice;
    }
    private LocalDate parseDate(String date) {
        String[] dateObj = date.split("-");
        LocalDate d = LocalDate.of(Integer.valueOf(dateObj[2]), Integer.valueOf(dateObj[1]),
                Integer.valueOf(dateObj[0]));
        return d;
    }

    private void signUp()
    {
        System.out.println("sign up");
        while (true) {
            System.out.flush();

            System.out.println("Enter your Name");
            String name = cin.nextLine();

            System.out.println("Enter your Date of Birth in format dd-MM-yyyy");
            String date = cin.nextLine();
            LocalDate dob = parseDate(date);
            System.out.println("Enter your email");
            String email = cin.nextLine();

            System.out.println("Enter your password");
            String password = cin.nextLine();

            blInterface.userDetails user = new blInterface.userDetails(name, 0, dob, email, password);

            int userId= userObj.addUser(user);
            if (userId != -1)
                return;
        }
    }
}
