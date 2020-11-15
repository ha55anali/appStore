package database;

import dbaseInterface.userDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Developer implements dbaseInterface.devInterface {

    public dbaseInterface.userDetails getDevDetails(int devID) {
        // initializing the returning object
        dbaseInterface.userDetails details = new dbaseInterface.userDetails();
        // first we check if users even exist or not
        File dev_count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(dev_count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return details;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File dev_file = new File("dev_record.txt");
        file_status = FileControl.CheckFile(dev_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return details;

        try {
            Scanner scanner = new Scanner(dev_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == devID) {

                        details.userID = devID; // ID found, hence stored in returning obj
                        details.Name = scanner.nextLine(); // next line has name, so stored
                        details.DOB = LocalDate.parse(scanner.nextLine()); // date is converted from String to LocalDate
                        // type to store back in returning obj
                        details.password = scanner.nextLine();
                        details.email = scanner.nextLine();

                        break;
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            return details;
        } catch (Exception e) {

            System.out.println("An error occured in writing user file\n");
            e.printStackTrace();
        }

        return details;

    }

    public boolean checkDevExists(int devID) {

        // first we check if users even exist or not
        File count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File dev_file = new File("dev_record.txt");
        file_status = FileControl.CheckFile(dev_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return false;

        try {
            Scanner scanner = new Scanner(dev_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == devID) { // user found

                        scanner.close();
                        return true;
                    } else {

                        // skipping the next 4 lines of name, DOB, password, email
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
            return false;
        } catch (Exception e) {

            System.out.println("An error occured in searching user\n");
            e.printStackTrace();
        }

        return false;

    }

    // appID will be assigned by database. pass dummy appID
    public int addUser(dbaseInterface.userDetails dev) {

        // first we see how many users are already there
        File dev_count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(dev_count_file);

        if (file_status == -1) // couldn't make file
            return -1;

        else { // file created or exists

            try {

                int total_users = 0;
                if (file_status == 0) { // file exists already and we can scan data from it

                    Scanner scanner = new Scanner(dev_count_file);
                    String data = "0";
                    if(scanner.hasNextLine())
                        data=scanner.nextLine();
                    scanner.close();
                    total_users = Integer.valueOf(data); // gives no. of users from first line of text file
                }

                // Till here, we know exactly how many users are already there;
                // updating the new number in count file;

                FileWriter writer = new FileWriter(dev_count_file);
                int _newID = total_users + 1;
                writer.write(_newID + "\n");

                // now, its time to append our new user in user_record;
                // for that, opening/creating new file i.e. app_file

                File dev_file = new File("dev_record.txt");
                file_status = FileControl.CheckFile(dev_file);
                writer.close();

                if (file_status == -1) // couldn't make file
                    return -1;

                else { // file opened or created;

                    String output; // this string will contain all the required user info to be printed
                    /*
                     * Syntax of filing: UserID Name DOB password email
                     */

                    output = String.valueOf(_newID); // userID has been assignedS
                    FileControl.AppendInFile(dev_file, output); // this call will append new user data into our file
                    output = dev.Name;
                    FileControl.AppendInFile(dev_file, output);
                    output = String.valueOf(dev.DOB);
                    FileControl.AppendInFile(dev_file, output);
                    output = dev.password;
                    FileControl.AppendInFile(dev_file, output);
                    output = dev.email;
                    FileControl.AppendInFile(dev_file, output);

                    // following is basically the original code which is called in AppendInFile()
                    // call
                    // FileWriter fw = new FileWriter(app_file, true);
                    // BufferedWriter bw = new BufferedWriter(fw);
                    // PrintWriter out = new PrintWriter(bw);
                    // out.println(String.valueOf(app.avgRatings));
                    // out.close();
                    // bw.close();
                    // fw.close();
                    return _newID;
                }

            } catch (IOException e) {

                System.out.println("An error occured in writing user file\n");
                e.printStackTrace();
            }

        }

        return -1;

    }

    public void removeUser(int devID) {
        // checking if ANY user exists or not
        File count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1) // file wasn't present earlier
            return;
        else if (file_status == 1) // a new file is made
        {
            count_file.delete();
            return;
        }

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File user_file = new File("dev_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1) // couldn't open file or file was't present in first place
            return;
        else if (file_status == 1) {
            user_file.delete();
            return;
        }

        // now we might find the user and have to re-write the file
        // for that, lets copy all the users in a list first

        List<dbaseInterface.userDetails> temp_list = new LinkedList<dbaseInterface.userDetails>();
        boolean userFound = false;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                userDetails temp_user = new userDetails();

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) != devID) {

                        temp_user.userID = Integer.valueOf(data); // its not the user which is to be deleted, hence
                        // storing
                        temp_user.Name = scanner.nextLine(); // next line has name, so stored
                        temp_user.DOB = LocalDate.parse(scanner.nextLine()); // date is converted from String to
                        // LocalDate type to store back in
                        // returning obj
                        temp_user.password = scanner.nextLine();
                        temp_user.email = scanner.nextLine();

                        temp_list.add(temp_user);
                    }

                    else { // user deleted to be. Hence, discarding

                        userFound = true;

                        scanner.nextLine(); // name discarded
                        scanner.nextLine(); // DOB discarded
                        scanner.nextLine(); // password discarded
                        scanner.nextLine(); // email discarded
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
        } catch (Exception e) {

            System.out.println("An error occured in writing user file\n");
            e.printStackTrace();
        }

        if (!userFound)
            return;

        // before writing back remaining users, lets delete user's apps in user-app
        // record
        removeApp(devID, -2); // this means that all the apps of this user will be deleted
        // time to write back in file

        try {
            FileWriter writer = new FileWriter(user_file);

            int total_users = temp_list.size();

            for (int i = 0; i < total_users; i++) {

                userDetails temp_user = new userDetails();
                temp_user = temp_list.remove(0);

                writer.write(String.valueOf(temp_user.userID) + "\n");
                writer.write(temp_user.Name + "\n");
                writer.write(String.valueOf(temp_user.DOB) + "\n");
                writer.write(temp_user.password + "\n");
                writer.write(temp_user.email + "\n");
            }

            writer.close();
        } catch (Exception e) {

            System.out.println("An error occured in writing user file in delete method\n");
            e.printStackTrace();
        }
    }

    public void addApp(int devID, int appID) {
        try {

            // check if the user even exists. if the user does not exist, we cant add the
            // card against that user. return 0

            if (!this.checkDevExists(devID)) {
                return;
            }
            // now, its time to append our new user in card_record

            File app_file = new File("dev_app_record.txt");
            int file_status = FileControl.CheckFile(app_file);

            if (file_status == -1) // couldn't make file
                return;

            else { // file opened or created;

                String output; // this string will contain all the required user info to be printed
                /*
                 * Syntax of filing: UserID\ncardNo\nExpYear
                 */

                output = String.valueOf(devID);
                FileControl.AppendInFile(app_file, output);
                output = String.valueOf(appID);
                FileControl.AppendInFile(app_file, output);

                return;
            }

        } catch (Exception e) {

            System.out.println("An error occured in writing the app file\n");
            e.printStackTrace();
        }

        return;
    }

    public void removeApp(int devID, int appID) {

        // if appID is passed as -2, it will remove all the apps of respective user
        // this feature is added for backend logic purpose and will not be used by user

        File record = new File("dev_app_record.txt");
        int file_status = FileControl.CheckFile(record);

        if (file_status == -1) // couldn't open file or file was't present in first place
            return;
        else if (file_status == 1) {
            record.delete();
            return;
        }

        // now we might find the user-app and have to re-write the file
        // for that, lets copy all the users-apps in a list first

        List<Integer> temp_list = new LinkedList<Integer>();
        boolean userFound = false;

        try {
            Scanner scanner = new Scanner(record);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine(); // if it reads one line, then it means it must have next 4 lines too
                try {
                    if (Integer.valueOf(data) != devID) {

                        // its not the user we want to delete, so we'll save its data to re-write later
                        temp_list.add(Integer.valueOf(data)); // userID
                        temp_list.add(Integer.valueOf(scanner.nextLine())); // appID
                        // temp_list.add(Integer.valueOf(scanner.nextLine())); // version
                        // temp_list.add(Integer.valueOf(scanner.nextLine())); // rating
                    }

                    else { // user deleted to be. Now checking for app

                        String data2 = scanner.nextLine();
                        if (appID == Integer.valueOf(data2) || appID == -2) { // app found. Not storing its data in list

                            userFound = true;

                        } else {

                            // not the app we are looking, save the data
                            temp_list.add(Integer.valueOf(data));
                            temp_list.add(Integer.valueOf(data2));

                        }
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
        } catch (Exception e) {

            System.out.println("An error occured in deleting app-user record\n");
            e.printStackTrace();
        }

        if (!userFound)
            return;

        // time to write back in file

        try {
            FileWriter writer = new FileWriter(record);

            // our data is already in sequence. We'll just write it down in order
            for (int i = 0; i < temp_list.size(); i++) {

                writer.write(String.valueOf(temp_list.remove(0)) + "\n"); // userID written
                writer.write(String.valueOf(temp_list.remove(0)) + "\n"); // appID

            }

            writer.close();
        } catch (Exception e) {
            System.out.println("An error occured in writing app-user file in delete method\n");
            e.printStackTrace();
        }

    }

    // return 1 if dev has made appID
    public boolean checkAppDev(int devID, int appID) {
        try {

            // check if the user even exists. if the user does not exist, we cant add the
            // card against that user. return 0

            File app_file = new File("dev_app_record.txt");
            int file_status = FileControl.CheckFile(app_file);

            if (file_status == -1) // couldn't make file
                return false;

            else { // file opened or created;
                Scanner line = new Scanner(app_file);
                while (line.hasNextLine()) {
                    int id = Integer.valueOf(line.nextLine());
                    int app = Integer.valueOf(line.nextLine());

                    if (id == devID && app == appID) {
                        line.close();
                        return true;
                    }
                }

                line.close();

            }

        } catch (Exception e) {

            System.out.println("An error occured in writing the card file\n");
            e.printStackTrace();
        }

        return false;
    }

    // return 1 if email is being used by some dev
    public boolean checkEmailExists(String email) {

        // first we check if users even exist or not
        File dev_count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(dev_count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File dev_file = new File("dev_record.txt");
        file_status = FileControl.CheckFile(dev_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return false;

        try {
            Scanner scanner = new Scanner(dev_file);
            while (scanner.hasNextLine()) { // will read the file till end

                // if file has this next line, it means file has a userID,
                // and if there's a userID, it means it is followed by 4 more lines having
                // information of user

                // skipping useless information
                scanner.nextLine(); // userID -> we don't need this
                scanner.nextLine(); // name -> don't need
                scanner.nextLine(); // DOB -> useless
                scanner.nextLine(); // password -> nahhh

                String data = scanner.nextLine(); // email, the thing we are concerned right now
                try {
                    if (data == email) { // user found

                        scanner.close();
                        return true;
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            return false;
        } catch (Exception e) {

            System.out.println("An error occured in checking for email\n");
            e.printStackTrace();
        }

        return false;

    }

    // made this main() to test the functions ill be making in this class
    public static void main(String[] args) {

    }

    public int authenticateUser(String email, String password) {
        // first we check if users even exist or not
        File count_file = new File("totalDevs.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return -1;

        // now we know that file exists
        // up to the real work now. Reading file to find the email ughh

        File user_file = new File("dev_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return -1;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                int userID = Integer.valueOf(scanner.nextLine()); // in case we'll have to return it

                // skipping the next 2 lines of name, DOB
                scanner.nextLine();
                scanner.nextLine();

                String pass = scanner.nextLine(); // password stored to compare later
                String data = scanner.nextLine();
                try {

                    if (data.equals(email)) { // user found

                        // now we have to see if the password matched the given password

                        if (pass.equals(password)) {

                            scanner.close();
                            return userID;
                        } else {

                            scanner.close();
                            return -1;
                        }
                    }
                } catch (Exception ex) {
                    continue;
                }
            }
            scanner.close();
            return -1;
        } catch (Exception e) {

            System.out.println("An error occured in authenticating user\n");
            e.printStackTrace();
        }

        return -1;
    }

    public List<Integer> getDevApps(int devID) {
        List<Integer> devApps = new ArrayList<Integer>();
        File app_file = new File("dev_app_record.txt");
        int file_status = FileControl.CheckFile(app_file);

        if (file_status == -1) // couldn't make file
            return devApps;

        else { // file opened or created;

            Scanner scanner;
            try {
                scanner = new Scanner(app_file);

                while (scanner.hasNextLine()) { // will read the file till end

                    String data = scanner.nextLine();
                    try {
                        if (Integer.valueOf(data) == devID) { // user found
                            String appID = scanner.nextLine();
                            devApps.add(Integer.valueOf(appID));

                        } else {
                            scanner.nextLine();
                        }
                    } catch (Exception ex) {
                        continue;
                    }
                }
                scanner.close();
                return devApps;
            } catch (FileNotFoundException e) {

            }
        }
        return devApps;
    }

}