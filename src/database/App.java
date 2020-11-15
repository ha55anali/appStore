package database;

import dbaseInterface.appDetails;
import dbaseInterface.appInterface;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App implements appInterface {

    // assume appID is valid
    public appDetails getAppDetails(int appID) {

        // initializing the returning object
        appDetails details = new dbaseInterface.appDetails();
        // first we check if apps even exist or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return details;

        // now we know that file exists
        // up to the real work now. Reading file to find the appID ughh

        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return details;

        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data).equals(appID)) {

                        details.AppID = appID; // ID found, hence stored in returning obj
                        details.Name = scanner.nextLine(); // next line has name, so stored
                        details.Description = scanner.nextLine(); // next line has description
                        details.Version = Integer.valueOf(scanner.nextLine()); // version is converted from string to
                                                                               // int
                        details.avgRatings = Integer.valueOf(scanner.nextLine()); // avgRating is stored as integer
                        details.Category = scanner.nextLine(); // category stored
                        break;
                    }
                    else {
                        scanner.nextLine();
                        scanner.nextLine();
                        scanner.nextLine();
                        scanner.nextLine();
                        scanner.nextLine();
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
        }

        // now, checking ratings files to add list of ratings and reviews for app
        List<Integer> ratings = new LinkedList<Integer>();
        List<String> reviews = new LinkedList<String>();
        details.Ratings = ratings;
        details.Reviews = reviews;

        File record = new File("app_ratings.txt");
        file_status = FileControl.CheckFile(record);

        if (file_status == -1)
            return details;

        try {
            Scanner scanner = new Scanner(record);
            while (scanner.hasNextLine()) {

                Integer check = Integer.valueOf(scanner.nextLine()); // userID ignored
                
                if (check.equals(appID)) { // app found. store its ratings and reviews

                    scanner.nextLine();
                    String temp_rat = scanner.nextLine();
                    if (!temp_rat.equals("-1"))
                        ratings.add(Integer.valueOf(temp_rat));

                    String temp_rev = scanner.nextLine();
                    if (!temp_rev.equals("0"))
                        reviews.add(scanner.nextLine());
                    else
                        scanner.nextLine();
                }
                else {
                    scanner.nextLine();
                    scanner.nextLine();
                    scanner.nextLine();
                    scanner.nextLine();
                }
            }
            scanner.close();
            details.Reviews = reviews;
            details.Ratings = ratings;

        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
        }

        return details;
    }

    // return appIds of all apps
    public List<Integer> getAllApps() {

        List<Integer> app_list = new LinkedList<Integer>();
        // first we check if apps even exist or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return app_list;

        // now we know that file exists
        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return app_list;

        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                try {

                    app_list.add(Integer.valueOf(scanner.nextLine())); // ID found, and converted into integer to push
                                                                       // in list
                    scanner.nextLine(); // next line has name, so ignored
                    scanner.nextLine(); // next line has description
                    scanner.nextLine(); // version
                    scanner.nextLine(); // review
                    scanner.nextLine(); // category
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            return app_list;
        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
        }
        return app_list;
    }

    // returns all appIds in the category
    // if no apps in category return empty list
    public List<Integer> getAppsInCategory(String Category) {

        List<Integer> app_list = new LinkedList<Integer>();
        // first we check if apps even exist or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return app_list;

        // now we know that file exists
        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return app_list;

        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                try {

                    int temp_ID = Integer.valueOf(scanner.nextLine()); // storing ID in case it's category is matched by
                                                                       // required one
                    scanner.nextLine(); // next line has name, so ignored
                    scanner.nextLine(); // next line has description
                    scanner.nextLine(); // version
                    scanner.nextLine(); // review
                    String check = scanner.nextLine();
                    if (Category.equals(check)) // category
                        app_list.add(temp_ID);

                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            return app_list;
        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
        }
        return app_list;
    }

    // if rating present, update it
    public void addRating(int appID, int userID, int rating) {

        // opening the record file
        File record = new File("app_ratings.txt");
        int file_status = FileControl.CheckFile(record);

        if (file_status == -1) // couldn't make file
            return;

        else { // file created or exists

            // now file has opened. First we'll see if user-app exists already. If yes,
            // update. Else, append

            /*
             * Syntax of filing: userID appID rating 0/1 (for comment) comment/null
             */

            List<String> temp_list = new LinkedList<String>();
            boolean user_found = false;
            try {
                Scanner scanner = new Scanner(record);
                while (scanner.hasNextLine()) {

                    String temp_userID = scanner.nextLine();
                    temp_list.add(temp_userID); // userID stored
                    String temp_appID = scanner.nextLine();
                    temp_list.add(temp_appID); // appID stored

                    if (userID == Integer.valueOf(temp_userID)) { // user-found
                        if (appID == Integer.valueOf(temp_appID)) { // required user-app found

                            user_found = true;
                            scanner.nextLine(); // old rating discarded
                            temp_list.add(String.valueOf(rating)); // updated
                        }
                    } else {
                        temp_list.add(scanner.nextLine()); // storing the old value of rating
                    }

                    temp_list.add(scanner.nextLine()); // 0/1 stored
                    temp_list.add(scanner.nextLine()); // comment stored
                }
                scanner.close();

                if (!user_found) {

                    FileControl.AppendInFile(record, String.valueOf(userID));
                    FileControl.AppendInFile(record, String.valueOf(appID));
                    FileControl.AppendInFile(record, String.valueOf(rating));
                    FileControl.AppendInFile(record, "0");
                    FileControl.AppendInFile(record, "null");

                    return;
                }

                FileWriter writer = new FileWriter(record);
                for (int i = 0; i < temp_list.size();) {

                    writer.write(temp_list.get(0) + "\n");
                    temp_list.remove(0);
                }
                writer.close();

            } catch (Exception e) {
                System.out.println("An error occured in writing user file\n");
                e.printStackTrace();
            }
        }
    }

    // if comment present, update it
    public void addComment(int appID, int userID, String comment) {

        // opening the record file
        File record = new File("app_ratings.txt");
        int file_status = FileControl.CheckFile(record);

        if (file_status == -1) // couldn't make file
            return;

        else { // file created or exists

            // now file has opened. First we'll see if user-app exists already. If yes,
            // update. Else, append

            /*
             * Syntax of filing: userID appID rating 0/1 (for comment) comment/null
             */

            List<String> temp_list = new LinkedList<String>();
            boolean user_found = false;
            try {
                Scanner scanner = new Scanner(record);
                while (scanner.hasNextLine()) {

                    String temp_userID = scanner.nextLine();
                    temp_list.add(temp_userID); // userID stored
                    String temp_appID = scanner.nextLine();
                    temp_list.add(temp_appID); // appID stored
                    temp_list.add(scanner.nextLine()); // rating stored

                    if (userID == Integer.valueOf(temp_userID)) { // user-found
                        if (appID == Integer.valueOf(temp_appID)) { // required user-app found

                            user_found = true;
                            temp_list.add("1");
                            temp_list.add(comment); // updated
                            scanner.nextLine(); // discarding old valuse
                            scanner.nextLine();
                        }
                    } else {
                        temp_list.add(scanner.nextLine()); // storing the old value of comment
                        temp_list.add(scanner.nextLine());
                    }
                }
                scanner.close();

                if (!user_found) {

                    FileControl.AppendInFile(record, String.valueOf(userID));
                    FileControl.AppendInFile(record, String.valueOf(appID));
                    FileControl.AppendInFile(record, "-1");
                    FileControl.AppendInFile(record, "1");
                    FileControl.AppendInFile(record, comment);

                    return;
                }

                FileWriter writer = new FileWriter(record);
                for (int i = 0; i < temp_list.size();) {

                    writer.write(temp_list.get(0) + "\n");
                    temp_list.remove(0);
                }
                writer.close();

            } catch (Exception e) {
                System.out.println("An error occured in writing user file\n");
                e.printStackTrace();
            }
        }
    }

    // can change name
    // version
    // category
    // Description
    // asume appID is valid
    // ignore rating and comment in appDetails
    public void updateApp(appDetails app) {

        // first we check if apps even exist or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return;

        // now we know that file exists
        // up to the real work now. Reading file to find the appID ughh

        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return;

        List<String> temp_list = new LinkedList<String>();
        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == app.AppID) {

                        scanner.nextLine(); // old name discarded
                        scanner.nextLine(); // old description discarded
                        scanner.nextLine(); // old version discarded
                        scanner.nextLine(); // old avgRating discarded
                        scanner.nextLine(); // old category discarded

                        // updating contents
                        temp_list.add(String.valueOf(app.AppID));
                        temp_list.add(app.Name);
                        temp_list.add(app.Description);
                        temp_list.add(String.valueOf(app.Version));
                        temp_list.add(String.valueOf(app.avgRatings));
                        temp_list.add(app.Category);
                    } else {
                        temp_list.add(data);
                        temp_list.add(scanner.nextLine()); // name
                        temp_list.add(scanner.nextLine()); // description
                        temp_list.add(scanner.nextLine()); // version
                        temp_list.add(scanner.nextLine()); // avgRating
                        temp_list.add(scanner.nextLine()); // category
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            FileWriter writer = new FileWriter(app_file);
            for (int i = 0; i < temp_list.size();) {

                writer.write(temp_list.get(0) + "\n");
                temp_list.remove(0);
            }
            writer.close();

        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
        }
    }

    // asume app exists
    public void removeApp(int appID) {

        // checking if ANY app exists or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1) // file wasn't present earlier
            return;
        else if (file_status == 1) // a new file is made
        {
            count_file.delete();
            return;
        }

        // now we know that file exists
        // up to the real work now. Reading file to find the appID ughh

        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1) // couldn't open file or file was't present in first place
            return;
        else if (file_status == 1) {
            app_file.delete();
            return;
        }

        // now we might find the app and have to re-write the file
        // for that, lets copy all the apps in a list first

        List<appDetails> temp_list = new LinkedList<appDetails>();
        boolean userFound = false;

        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                appDetails temp_app = new appDetails();

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) != appID) {

                        temp_app.AppID = Integer.valueOf(data); // its not the app which is to be deleted, hence
                                                                // storing
                        temp_app.Name = scanner.nextLine(); // next line has name, so stored
                        temp_app.Description = scanner.nextLine(); // description stored
                        temp_app.Version = Integer.valueOf(scanner.nextLine());
                        temp_app.avgRatings = Integer.valueOf(scanner.nextLine());
                        temp_app.Category = scanner.nextLine();

                        temp_list.add(temp_app);
                    }

                    else { // user deleted to be. Hence, discarding

                        userFound = true;

                        scanner.nextLine(); // name discarded
                        scanner.nextLine(); // desc discarded
                        scanner.nextLine(); // ver discarded
                        scanner.nextLine(); // rating discarded
                        scanner.nextLine(); // category discarded
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
        } catch (Exception e) {

            System.out.println("An error occured in deleting from app file\n");
            e.printStackTrace();
        }

        if (!userFound)
            return;

        try {
            FileWriter writer = new FileWriter(app_file);

            int total_apps = temp_list.size();

            for (int i = 0; i < total_apps; i++) {

                appDetails temp_app = new appDetails();
                temp_app = temp_list.remove(0);

                writer.write(String.valueOf(temp_app.AppID) + "\n");
                writer.write(temp_app.Name + "\n");
                writer.write(temp_app.Description + "\n");
                writer.write(String.valueOf(temp_app.Version) + "\n");
                writer.write(String.valueOf(temp_app.avgRatings) + "\n");
                writer.write(temp_app.Category + "\n");
            }

            writer.close();
        } catch (Exception e) {

            System.out.println("An error occured in writing user file in delete method\n");
            e.printStackTrace();
        }
    }

    public boolean checkAppExists(int appID) {

        // first we check if apps even exist or not
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the appID ughh

        File app_file = new File("app_record.txt");
        file_status = FileControl.CheckFile(app_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return false;

        try {
            Scanner scanner = new Scanner(app_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();

                if (Integer.valueOf(data) == appID) {
                    scanner.close();
                    return true;
                }

                // disarding name, discription, version, avgRating, category
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
            }
            scanner.close();
            return false;

        } catch (Exception e) {

            System.out.println("An error occured in reading app file\n");
            e.printStackTrace();
            return false;
        }
    }

    public int addApp(appDetails app) {

        // first we see how many apps are already there
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1) // couldn't make file
            return -1;

        else { // file created or exists

            try {

                int total_apps = 0;
                if (file_status == 0) { // file exists already and we can scan data from it

                    Scanner scanner = new Scanner(count_file);
                    String data = "0";
                    if(scanner.hasNextLine())
                        data=scanner.nextLine();
                    scanner.close();
                    total_apps = Integer.valueOf(data); // gives no. of apps from first line of text file
                }

                // Till here, we know exactly how many apps are already there;
                // updating the new number in count file;

                FileWriter writer = new FileWriter(count_file);
                int _newID = total_apps + 1;
                writer.write(_newID + "\n");

                // now, its time to append our new app in app_record;
                // for that, opening/creating new file i.e. app_file

                File app_file = new File("app_record.txt");
                file_status = FileControl.CheckFile(app_file);
                writer.close();

                if (file_status == -1) // couldn't make file
                    return -1;

                else { // file opened or created;

                    String output; // this string will contain all the required app info to be printed
                    /*
                     * Syntax of filing: AppID Name Description Version AvgRating Category
                     */

                    output = String.valueOf(_newID); // appID has been assignedS
                    FileControl.AppendInFile(app_file, output); // this call will append new app data into our file
                    output = app.Name;
                    FileControl.AppendInFile(app_file, output);
                    output = app.Description;
                    FileControl.AppendInFile(app_file, output);
                    output = String.valueOf(app.Version);
                    FileControl.AppendInFile(app_file, output);

                    // following is basically the original code which is called in AppendInFile()
                    // call
                    FileWriter fw = new FileWriter(app_file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw);
                    out.println(String.valueOf(app.avgRatings));
                    out.close();
                    bw.close();
                    fw.close();
                    output = app.Category;
                    FileControl.AppendInFile(app_file, output);
                    return _newID;
                }

            } catch (IOException e) {

                System.out.println("An error occured in writing app file\n");
                e.printStackTrace();
            }

        }

        return -1;
    }

    // made this main() to test the functions ill be making in this class
    public static void main(String[] args) {

        // List<Integer> rats = new LinkedList<Integer>();
        // rats.add(1);
        // List<String> revs = new LinkedList<String>();
        // revs.add("checkkk");

        // App testing = new App();
        // appDetails obj = new appDetails(5, "PlanEveHAHA", "??????????", 222, "gomez", rats, 7, revs);
        // testing.addApp(obj);
        

        // List<Integer> temp = new LinkedList<Integer>();
        // temp = testing.getAllApps();
        // for(int i=0; i<5; ++i) {

        // System.out.println(temp.get(0) + "\n");
        // temp.remove(0);
        // }

        // appDetails obj = new appDetails();
        // obj = testing.getAppDetails(0);
        // System.out.println(obj.AppID + "\n" + obj.Name + "\n" + obj.Description +
        // "\n" + obj.Version + "\n" + obj.Category + "\n");
    }
}
