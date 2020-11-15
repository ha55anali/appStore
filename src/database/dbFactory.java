package database;

public class dbFactory {



    public static dbaseInterface.appInterface getAppObject() {
        return new database.sqlAppDB();
    }

    public static dbaseInterface.devInterface getDevObject() {
        return new database.sqlDevDB();
    }

    public static dbaseInterface.userInterface getUserObject() {
        return new database.sqlUserDB();
    }


}