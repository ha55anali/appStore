package CLI;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.*;

public class devMethods {

    public static void main(String[] args) {
        new devMethods();
    }

    int devID;
    blInterface.devInterface dev = businessLayer.blFactory.getDevObject();

    public devMethods() {
    }

    private LocalDate parseDate(String date) {
        String[] dateObj = date.split("-");
        LocalDate d = LocalDate.of(Integer.valueOf(dateObj[2]), Integer.valueOf(dateObj[1]),
                Integer.valueOf(dateObj[0]));
        return d;
    }

    public void signInPage() {

        System.out.println("---------------Welcome to Play Store---------------");
        Scanner in = new Scanner(System.in);

        while (true) {

            System.out.println("Press S to sign in");
            System.out.println("Press U to sign up if you don't have an account");

            String input = in.nextLine();

            if (input.equalsIgnoreCase("S")) {
                while (true) {
                    System.out.flush();
                    Scanner signIn = new Scanner(System.in);
                    System.out.println("Enter your email");
                    String e = signIn.nextLine();

                    System.out.println("Enter your password");
                    String p = signIn.nextLine();

                    devID = dev.authenticateUser(e, p);
                    if (devID != -1)
                        return;
                }

            } else if (input.equalsIgnoreCase("U")) {

                while (true) {
                    System.out.flush();

                    System.out.println("Enter your Name");
                    String name = in.nextLine();

                    System.out.println("Enter your Date of Birth in format dd-MM-yyyy");
                    String date = in.nextLine();
                    LocalDate dob = parseDate(date);
                    System.out.println("Enter your email");
                    String email = in.nextLine();

                    System.out.println("Enter your password");
                    String password = in.nextLine();

                    blInterface.userDetails developer = new blInterface.userDetails(name, 0, dob, email, password);

                    devID = dev.addDev(developer);
                    if (devID != -1)
                        return;
                }

            } else {
                System.out.println("Wrong Input. Try Again :)");
            }

        }

    }

    public void categoryPage() {

        while (true) {
            System.out.flush();
            System.out.println("Press P to publish new App");
            System.out.println("U to Update an existing App");
            System.out.println("R to Remove an App");
            System.out.println("I to Show your Information");
            System.out.println("S to Show all developer Apps ");
            System.out.println("E to Logout");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            
            if(input.equalsIgnoreCase("E")){
                System.out.println("Logged out Successfully");
                break;
            }

            else if(input.equalsIgnoreCase("S")){
                System.out.println("List of Apps that you created");
                List<blInterface.App> devApps = dev.getDevApps(devID);
                for (int i = 0; i < devApps.size(); i++) {

                    System.out.println(devApps.get(i).Name + " (" + devApps.get(i).AppID + ")");
                }
            }
            else if (input.equalsIgnoreCase("P")) {

                System.out.flush();
                System.out.println("Enter the name of the App");
                String name = in.nextLine();

                System.out.println("Enter the version of the App");
                int version = in.nextInt();
                in.nextLine();

                String categ;
                while (true) {
                    System.out.println("Select the Category of the App from");
                    System.out.println("G for Games");
                    System.out.println("E for Education");
                    System.out.println("P for Productivity");
                    System.out.println("C for Communication");
                    categ = in.nextLine();

                    if (categ.equalsIgnoreCase("g")) {
                        categ = "Games";
                        break;
                    } else if (categ.equalsIgnoreCase("e")) {
                        categ = "Education";
                        break;
                    } else if (categ.equalsIgnoreCase("P")) {
                        categ = "Productivity";
                        break;
                    } else if (categ.equalsIgnoreCase("C")) {
                        categ = "Communication";
                        break;
                    } else {
                        System.out.println("Wrong Input. Try Again :)");
                    }
                }

                System.out.println("Enter description of the App");
                String desc = in.nextLine();
                List<Integer> UserRatings = new ArrayList<Integer>();

                blInterface.App newApp = new blInterface.App(0, name, desc, version, categ, UserRatings, 0,
                        new ArrayList<String>());
                dev.addApp(devID, newApp);
                System.out.println("New App Added Successfully");
            }

            else if (input.equalsIgnoreCase("U")) {
                System.out.println("List of Apps that you created");
                List<blInterface.App> devApps = dev.getDevApps(devID);
                for (int i = 0; i < devApps.size(); i++) {

                    System.out.println(devApps.get(i).Name + " (" + devApps.get(i).AppID + ")");
                }
                System.out.println("Enter the appID of the app you want to Update");
                int id = in.nextInt();
                in.nextLine();
                int Appno = 0;
                for (Appno = 0; Appno < devApps.size(); Appno++) {
                    if (id == devApps.get(Appno).AppID) {
                        System.out.flush();
                        System.out.println("Name: " + devApps.get(Appno).Name);
                        System.out.println("Category: " + devApps.get(Appno).Category);
                        System.out.println("Version: " + devApps.get(Appno).Version);
                        System.out.println("Description: " + devApps.get(Appno).Description);
                        break;
                    }
                }
                blInterface.App editedApp = new blInterface.App(devApps.get(Appno).AppID, devApps.get(Appno).Name,
                        devApps.get(Appno).Description, devApps.get(Appno).Version, devApps.get(Appno).Category,
                        devApps.get(Appno).Ratings, devApps.get(Appno).avgRatings, devApps.get(Appno).Reviews);
                while (true) {

                    System.out.println("Press N to edit Name");
                    System.out.println("Press C to edit Category");
                    System.out.println("Press V to edit Version");
                    System.out.println("Press D to edit Description");

                    input = in.nextLine();
                    if (input.equalsIgnoreCase("N")) {
                        System.out.println("Enter new Name");
                        editedApp.Name = in.nextLine();
                        break;
                    } else if (input.equalsIgnoreCase("C")) {

                        while (true) {
                            System.out.println("Select the Category of the App from");
                            System.out.println("G for Games");
                            System.out.println("E for Education");
                            System.out.println("P for Productivity");
                            System.out.println("C for Communication");
                            input = in.nextLine();

                            if (input.equalsIgnoreCase("g")) {
                                editedApp.Category = "Games";
                                break;
                            } else if (input.equalsIgnoreCase("e")) {
                                editedApp.Category = "Education";
                                break;
                            } else if (input.equalsIgnoreCase("P")) {
                                editedApp.Category = "Productivity";
                                break;
                            } else if (input.equalsIgnoreCase("C")) {
                                editedApp.Category = "Communication";
                                break;
                            } else {
                                System.out.println("Wrong Input. Try Again :)");
                            }
                        }
                        break;
                    } else if (input.equalsIgnoreCase("V")) {
                        System.out.println("Enter the version of the App");
                        editedApp.Version = in.nextInt();
                        in.nextLine();
                        break;
                    } else if (input.equalsIgnoreCase("D")) {
                        System.out.println("Enter the edited description");
                        editedApp.Description = in.nextLine();
                        break;
                    } else {
                        System.out.println("Wrong Input. Try Again");
                    }

                }
                dev.updateApp(devID, editedApp);

            }

            else if (input.equalsIgnoreCase("R")) {
                System.out.println("List of Apps that you created");
                List<blInterface.App> devApps = dev.getDevApps(devID);
                for (int i = 0; i < devApps.size(); i++) {

                    System.out.println(devApps.get(i).Name + " (" + devApps.get(i).AppID + ")");
                }
                System.out.println("Enter the appID of the app you want to Remove");
                int id = in.nextInt();
                dev.removeApp(devID, id);
                System.out.println("App has been removed Successfully");

            } else if (input.equalsIgnoreCase("I")) {
                System.out.println("---------------Dev Details-------------");
                blInterface.userDetails developer = dev.getDevDetails(devID);
                System.out.flush();
                System.out.println("Name: " + developer.Name);
                System.out.println("Password: " + developer.password);
                System.out.println("Email: " + developer.email);
                System.out.println("Press 1 to delete Profile");
                input = in.nextLine();
                if(input.equalsIgnoreCase("1")){
                    dev.removeDev(devID);
                    System.out.println("Developer Deleted");
                    break;
                }
                System.out.println("Press any key to get back");
                input = in.nextLine();
            }

            else {
                System.out.println("Wrong Input. Try Again :)");
            }

        }

    }

}
