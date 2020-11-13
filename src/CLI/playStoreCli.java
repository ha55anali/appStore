package CLI;

import java.util.Scanner;

class methods {
    // will add parameters according to functions
    // method to install an App
    boolean installApp() {
        boolean installSuccess = true;// set according to install success

        // add functionality through business layer

        System.out.println("App is Installed successfully");
        return installSuccess;
    }

    boolean unInstallApp() {
        boolean unInstallSuccess = true;// set according to uninstall success

        // add functionality through business layer

        System.out.println("App is uninstalled successfully");
        return unInstallSuccess;
    }

    boolean updateApp() {
        boolean update = true;// set according to update

        // add functionality through business layer

        System.out.println("App is successfully updated");
        return update;
    }

    boolean updateAllApps() {
        boolean update = true;// set according to update status
        System.out.println("Updating Apps.......");
        // add functionality through business layer

        System.out.println("App is successfully updated");
        return update;
    }

    void viewAllApps() {
        // viewing all apps
        System.out.println("view all app function");
    }

    void viewAppsByCategory() {
        // set category
        Scanner in=new Scanner (System.in);
        System.out.printf("Enter Category: ");
        String input=in.nextLine();
        System.out.println("viewing apps by "+ input+" Category");
        // view apps
    }

    void searchApp() {
        // seatch by nameScanner in=new Scanner (System.in);
        Scanner in=new Scanner (System.in);
        System.out.printf("Enter Search Category: ");
        String input=in.nextLine();
        System.out.println("Showing Results for "+ input);
    }

    void getAppDetails() {
        System.out.println("App details...");
    }

    void setPaymentMethod() {
        System.out.println("Select Payment Method...");
        System.out.println("Payment Method set...");
    }

    void buyApp() {
        setPaymentMethod();
        System.out.println("App bought");
    }

    void deleteReview() {
        System.out.println("review deleted");
    }

    void addRreview() {
        
        Scanner in=new Scanner (System.in);
        System.out.printf("Enter your review: ");
        String input=in.nextLine();
        System.out.println("Review added");
    }
    //clear screen code
    void clearScreen() {    
        System.out.flush();  
    }  
}

public class playStoreCli { 
     
    public static void main(String [] args) {
        methods m = new methods();

        System.out.println("---------------Welcome to Playstore---------------");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Press S to select App");
            System.out.println("Type Search to search an App");
            System.out.println("Press A to update all Apps");
            System.out.println("Press V to view all Apps");
            System.out.println("Press VC to view Apps by Category");

            String input = in.nextLine();
            if (input.equalsIgnoreCase("s")) {

                m.clearScreen();
                while (true) {
                    System.out.println("Press I to install App");
                    System.out.println("Press X to uninstall App");
                    System.out.println("Press U to update App");
                    System.out.println("Press R to add a Review");
                    System.out.println("Press DR to delete your Review");

                    System.out.println("Press B to Buy an App");
                    System.out.println("Press D to view details of the app");

                    input = in.nextLine();

                    if(input.equalsIgnoreCase("x"))
                    {
                        m.unInstallApp();
                        break;
                    }

                    if(input.equalsIgnoreCase("i"))
                    {
                        m.installApp();
                        break;
                    }
                    if(input.equalsIgnoreCase("u"))
                    {
                        m.updateApp();
                        break;
                    }
                    if(input.equalsIgnoreCase("r"))
                    {
                        m.addRreview();
                        break;
                    }

                    if(input.equalsIgnoreCase("dr"))
                    {
                        m.deleteReview();
                        break;
                    }

                    if(input.equalsIgnoreCase("b"))
                    {
                        m.buyApp();
                        break;
                    }
                    if(input.equalsIgnoreCase("d"))
                    {
                        m.getAppDetails();
                        break;
                    }
                    else{
                        System.out.println("Wrong Input. Try Again :)");
                    }
                }
                break;
            } 
            else if (input.equalsIgnoreCase("a")) {
                m.clearScreen();
                m.updateAllApps();
                break;
            } 
            else if (input.equalsIgnoreCase("v")) {
                m.clearScreen();
                m.viewAllApps();
                break;
            } 
            else if (input.equalsIgnoreCase("vc")) {
                m.clearScreen();
                m.viewAppsByCategory();
                break;
            } 
            else if (input.equalsIgnoreCase("search")) {
                m.clearScreen();
                m.searchApp();
                break;
            } 
            else {
                System.out.println("Wrong Input. Try Again :)");
            }
        }
    }
}

