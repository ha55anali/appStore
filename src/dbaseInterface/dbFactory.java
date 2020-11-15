package dbaseInterface;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;

public class dbFactory {

    private static int getDBMode() {
        try {
            File myObj = new File("dbMode.txt");
            Scanner myReader = new Scanner(myObj);
            String data="";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            return Integer.parseInt(data);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return -1;
    }

    public static dbaseInterface.appInterface getAppObject() {
        if (getDBMode() == 1)
            return database.dbFactory.getAppObject();
        else
            return textbase.dbFactory.getAppObject();
    }

    public static dbaseInterface.devInterface getDevObject() {
        if (getDBMode() == 1)
            return database.dbFactory.getDevObject();
        else
            return textbase.dbFactory.getDevObject();
    }

    public static dbaseInterface.userInterface getUserObject() {
        if (getDBMode() == 1)
            return database.dbFactory.getUserObject();
        else
            return textbase.dbFactory.getUserObject();
    }
}