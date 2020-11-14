// package dbaseInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class User implements userInterface {

    public boolean checkUserExists(int userID) {

        // first we check if users even exist or not
        File count_file = new File("totalUsers.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File user_file = new File("user_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return false;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == userID) { // user found

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

    public void addInstalledApp(int appID, int userID, int ver) {
    }

    public boolean authenticateUser(int userID, String password) {

        // first we check if users even exist or not
        File count_file = new File("totalUsers.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File user_file = new File("user_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return false;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == userID) { // user found

                        // now we have to see if the password matched the given password

                        scanner.nextLine(); // name of user
                        scanner.nextLine(); // DOB of user

                        if (scanner.nextLine() == password) {

                            scanner.close();
                            return true;
                        } else {

                            scanner.close();
                            return false;
                        }
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

            System.out.println("An error occured in authenticating user\n");
            e.printStackTrace();
        }

        return false;
    }

    public void removeInstalledApp(int appID, int userID) {
    }

    public int checkAppInstall(int appID, int userID) {

        return 0;
    }

    // NOTE: NUSHIPISHI CODING OVER HERE HEHE
    public int addCard(int userID, int cardNo, int ExpYear) {
        try {

            // check if the user even exists. if the user does not exist, we cant add the
            // card against that user. return 0
            if (!this.checkUserExists(userID)) {
                return 0;
            }
            // now, its time to append our new user in card_record

            File card_file = new File("card_record.txt");
            int file_status = FileControl.CheckFile(card_file);

            if (file_status == -1) // couldn't make file
                return -1;

            else { // file opened or created;

                String output; // this string will contain all the required user info to be printed
                /*
                 * Syntax of filing: UserID\ncardNo\nExpYear
                 */

                output = String.valueOf(userID);
                FileControl.AppendInFile(card_file, output);
                output = String.valueOf(cardNo);
                FileControl.AppendInFile(card_file, output);
                output = String.valueOf(ExpYear);
                FileControl.AppendInFile(card_file, output);

                return 1;// card successfully added against user
            }

        } catch (Exception e) {

            System.out.println("An error occured in writing the card file\n");
            e.printStackTrace();
        }

        return -1;

    }

    // NOTE: YEAH IDER BHI
    // Done :p
    public boolean changeCardDetails(int userID, int cardNo, int NewExpYear) {
        if (checkUserCard(userID, cardNo)) {
            return false;
        }

        List<HashMap<String, String>> cardDetailsList = new ArrayList<HashMap<String, String>>();
        try {
            File card_file = new File("card_record.txt");
            Scanner line = new Scanner(card_file);

            // creating a hashmap list for all card detail type pseudo objects
            while (line.hasNextLine()) {

                HashMap<String, String> tempObj = new HashMap<String, String>();
                tempObj.put("id", line.nextLine());
                tempObj.put("cardNum", line.nextLine());
                tempObj.put("expYear", line.nextLine());

                cardDetailsList.add(tempObj);
            }

            for (HashMap<String, String> cardDetailObj : cardDetailsList) {

                String id = Integer.toString(userID);
                String card = Integer.toString(cardNo);
                String exp = Integer.toString(NewExpYear);
                String getVal = cardDetailObj.get("id");
                String temp1 = id;
                String temp2 = getVal;
                if (temp1.equals(temp2)) {
                    cardDetailObj.replace("cardNum", card);
                    cardDetailObj.replace("expYear", exp);
                }
            }

            // now to write back the whole fricking file
            line.close();
            Writer writer = new FileWriter(card_file);
            // for(int i=0; i<cardDetailsList.size(); ++i){
            // cardDetailsList[i].
            // }
            try {
                writer.write("");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // FileControl fc = new FileControl();
            String output = "";
            cardDetailsList.forEach((cardDetailObj) -> {
                String id = cardDetailObj.get("id");
                String card = cardDetailObj.get("cardNum");
                String exp = cardDetailObj.get("expYear");
                try {
                    FileControl.AppendInFile(card_file, id);
                    FileControl.AppendInFile(card_file, card);
                    FileControl.AppendInFile(card_file, exp);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });

            writer.close();

        } catch (

        Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // NOTE: NICE THIS TOO
    public boolean removeCardDetails(int userID, int cardNo) {
        if (!checkUserCard(userID, cardNo)) {
            return false;
        }

        List<HashMap<String, String>> cardDetailsList = new ArrayList<HashMap<String, String>>();
        try {
            File card_file = new File("card_record.txt");
            Scanner line = new Scanner(card_file);

            // creating a hashmap list for all card detail type pseudo objects
            while (line.hasNextLine()) {

                HashMap<String, String> tempObj = new HashMap<String, String>();
                tempObj.put("id", line.nextLine());
                tempObj.put("cardNum", line.nextLine());
                tempObj.put("expYear", line.nextLine());

                cardDetailsList.add(tempObj);
            }

            // iterating over the list to see if we find our userID. if found, we update
            // details
            cardDetailsList.forEach((cardDetailObj) -> {
                if (cardDetailObj.get("id").equals(Integer.toString(userID))) {
                    cardDetailObj.remove("id");
                    cardDetailObj.remove("cardNum");
                    cardDetailObj.remove("expTear");
                }
            });

            // now to write back the whole fricking file
            line.close();

            Writer writer = new FileWriter(card_file);
            // for(int i=0; i<cardDetailsList.size(); ++i){
            // cardDetailsList[i].
            // }
            try {
                writer.write("");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            // FileControl fc = new FileControl();
            String output = "";
            cardDetailsList.forEach((cardDetailObj) -> {
                String id = cardDetailObj.get("id");
                if (id != null) {
                    String card = cardDetailObj.get("cardNum");
                    String exp = cardDetailObj.get("expYear");
                    try {
                        FileControl.AppendInFile(card_file, id);
                        FileControl.AppendInFile(card_file, card);
                        FileControl.AppendInFile(card_file, exp);

                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }

            });

            // writer.close();

            // Writer writer = new FileWriter(card_file);
            // cardDetailsList.forEach((cardDetailObj) -> {
            // String id = cardDetailObj.get("id");
            // String card = cardDetailObj.get("id");
            // String exp = cardDetailObj.get("id");
            // try {
            // writer.write(id);
            // writer.write(card);
            // writer.write(exp);
            // } catch (IOException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // }
            // });

            // writer.close();

        } catch (

        Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // NOTE: TUNAY KIYA KYA HAI BAY? >:(
    // done :p
    public boolean checkUserCard(int userID, int cardNo) {
        try {

            // check if the user even exists. if the user does not exist, we cant add the
            // card against that user. return 0

            File card_file = new File("card_record.txt");
            int file_status = FileControl.CheckFile(card_file);

            if (file_status == -1) // couldn't make file
                return false;

            else { // file opened or created;
                Scanner line = new Scanner(card_file);
                while (line.hasNextLine()) {
                    int id = Integer.valueOf(line.nextLine());
                    int card = Integer.valueOf(line.nextLine());
                    int exp = Integer.valueOf(line.nextLine());

                    if (card == cardNo && id == userID) {
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

    public boolean checkEmailExists(String email) {

        // first we check if users even exist or not
        File count_file = new File("totalUsers.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return false;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File user_file = new File("user_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1 || file_status == 1) // couldn't open file or file wasn't present
            return false;

        try {
            Scanner scanner = new Scanner(user_file);
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

    public int addUser(userDetails user) {

        // first we see how many users are already there
        File count_file = new File("totalUsers.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1) // couldn't make file
            return -1;

        else { // file created or exists

            try {

                int total_users = 0;
                if (file_status == 0) { // file exists already and we can scan data from it

                    Scanner scanner = new Scanner(count_file);
                    String data = scanner.nextLine();
                    scanner.close();
                    total_users = Integer.valueOf(data); // gives no. of users from first line of text file
                }

                // Till here, we know exactly how many users are already there;
                // updating the new number in count file;

                FileWriter writer = new FileWriter(count_file);
                int _newID = total_users + 1;
                writer.write(_newID + "\n");

                // now, its time to append our new user in user_record;
                // for that, opening/creating new file i.e. app_file

                File user_file = new File("user_record.txt");
                file_status = FileControl.CheckFile(user_file);
                writer.close();

                if (file_status == -1) // couldn't make file
                    return -1;

                else { // file opened or created;

                    String output; // this string will contain all the required user info to be printed
                    /*
                     * Syntax of filing: UserID Name DOB password email
                     */

                    output = String.valueOf(_newID); // userID has been assignedS
                    FileControl.AppendInFile(user_file, output); // this call will append new user data into our file
                    output = user.Name;
                    FileControl.AppendInFile(user_file, output);
                    output = String.valueOf(user.DOB);
                    FileControl.AppendInFile(user_file, output);
                    output = user.password;
                    FileControl.AppendInFile(user_file, output);
                    output = user.email;
                    FileControl.AppendInFile(user_file, output);

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

    public userDetails getUserDetails(int userID) {

        // initializing the returning object
        userDetails details = new userDetails();
        // first we check if users even exist or not
        File count_file = new File("totalUsers.txt");
        int file_status = FileControl.CheckFile(count_file);

        if (file_status == -1 || file_status == 1) // file wasn't present earlier
            return details;

        // now we know that file exists
        // up to the real work now. Reading file to find the userID ughh

        File user_file = new File("user_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1 || file_status == 1) // couldn't open file
            return details;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) == userID) {

                        details.userID = userID; // ID found, hence stored in returning obj
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

    public void removeUser(int userID) {

        // checking if ANY user exists or not
        File count_file = new File("totalUsers.txt");
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

        File user_file = new File("user_record.txt");
        file_status = FileControl.CheckFile(user_file);

        if (file_status == -1) // couldn't open file or file was't present in first place
            return;
        else if (file_status == 1) {
            user_file.delete();
            return;
        }

        // now we might find the user and have to re-write the file
        // for that, lets opy all the users in a list first

        List<userDetails> temp_list = new LinkedList<userDetails>();
        boolean userFound = false;

        try {
            Scanner scanner = new Scanner(user_file);
            while (scanner.hasNextLine()) { // will read the file till end

                userDetails temp_user = new userDetails();

                String data = scanner.nextLine();
                try {
                    if (Integer.valueOf(data) != userID) {

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

    // made this main() to test the functions ill be making in this class
    public static void main(String[] args) {

        LocalDate dobby = LocalDate.now();
        User testing = new User();
        userDetails ob = new userDetails("nushipishi", 13233, dobby, "nushi@pishi.pp", "omgitshappening");
        int userID = testing.addUser(ob);

        // System.out.println(testing.addCard(userID, 7837823, LocalDate.now().getYear()
        // + 10));
        // System.out.println(testing.changeCardDetails(30, 12345667, 2035));
        System.out.println(testing.removeCardDetails(56, 7837823));

        // for (int i = 1; i < 9; i++) {
        // userDetails obj = testing.getUserDetails(i);
        // if (obj.userID != -1)
        // System.out.println("\n" + obj.userID + "\n" + obj.Name + "\n" + obj.DOB +
        // "\n" + obj.password + "\n"
        // + obj.email + "\n");

        // else
        // System.out.println("\nUSER NOT FOUND\n");
        // }

        // testing.removeUser(3);
    }
}