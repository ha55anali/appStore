package dbaseInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Developer implements devInterface {

    public userDetails getDevDetails(int devID) {
        // initializing the returning object
        userDetails details = new userDetails();
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
    public int addUser(userDetails dev) {

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
                    String data = scanner.nextLine();
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

        return;
    }

    public int addApp(int devID, int appID) {
        try {

            // check if the user even exists. if the user does not exist, we cant add the
            // card against that user. return 0
            if (!this.checkDevExists(devID)) {
                return 0;
            }
            // now, its time to append our new user in card_record

            File app_file = new File("dev_app_record.txt");
            int file_status = FileControl.CheckFile(app_file);

            if (file_status == -1) // couldn't make file
                return -1;

            else { // file opened or created;

                String output; // this string will contain all the required user info to be printed
                /*
                 * Syntax of filing: UserID\ncardNo\nExpYear
                 */

                output = String.valueOf(devID);
                FileControl.AppendInFile(app_file, output);
                output = String.valueOf(appID);
                FileControl.AppendInFile(app_file, output);

                return 1;// card successfully added against user
            }

        } catch (Exception e) {

            System.out.println("An error occured in writing the card file\n");
            e.printStackTrace();
        }

        return -1;
    }

    public void removeApp(int devID, int appID) {

        return;

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
}
