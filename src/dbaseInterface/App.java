package dbaseInterface;

import java.io.*;
import java.util.*;

public class App {

    
    public int addApp(appDetails app) {

        //first we see how many apps are already there
        File count_file = new File("totalApplications.txt");
        int file_status = FileControl.CheckFile(count_file);

        if(file_status == -1)   //couldn't make file
           return -1;

        else {              //file created or exists

            try {

                int total_apps = 0;
                if(file_status == 0) {      //file exists already and we can scan data from it

                    Scanner scanner = new Scanner(count_file);
                    String data = scanner.nextLine();
                    scanner.close();
                    total_apps = Integer.valueOf(data); // gives no. of apps from first line of text file
                }
                
                //Till here, we know exactly how many apps are already there;
                //updating the new number in count file;

                FileWriter writer = new FileWriter(count_file);
                int _newID = total_apps+1;
                writer.write(_newID + "\n");

                //now, its time to append our new app in app_record;
                //for that, opening/creating new file i.e. app_file
                
                File app_file = new File("app_record.txt");
                file_status = FileControl.CheckFile(app_file);
                writer.close();

                if(file_status == -1)   //couldn't make file
                    return -1;

                else {          //file opened or created;

                    String output;  //this string will contain all the required app info to be printed
                    /*
                        Syntax of filing:
                        AppID
                        Name
                        Description
                        Version
                        AvgRating
                    */

                    output = String.valueOf(_newID);      //appID has been assignedS
                    FileControl.AppendInFile(app_file, output);     //this call will append new app data into our file
                    output = app.Name;
                    FileControl.AppendInFile(app_file, output);
                    output = app.Description;
                    FileControl.AppendInFile(app_file, output);
                    output = String.valueOf(app.Version);
                    FileControl.AppendInFile(app_file, output);

                    //following is basically the original code which is called in AppendInFile() call
                    FileWriter fw = new FileWriter(app_file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw);
                    out.println(String.valueOf(app.avgRatings));
                    out.close();
                    bw.close();
                    fw.close();
                    return _newID;
                }
                
            } catch (IOException e) {

                System.out.println("An error occured in writing app file\n");
                e.printStackTrace();
            }

        }

        return -1;
    }
    //made this main() to test the functions ill be making in this class
    public static void main(String[] args) {
        
        List<Integer> rats = new LinkedList<Integer>();
        rats.add(1);
        List<String> revs = new LinkedList<String>();
        revs.add("checkkk");

        App testing = new App();
        appDetails obj = new appDetails(1, "PlanEve", "It's all coming togethor", 1, rats, 5, revs);
        testing.addApp(obj);
    }
}
