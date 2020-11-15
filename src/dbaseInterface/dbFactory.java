package dbaseInterface;

public class dbFactory {



    public static appInterface getAppObject() {
        return new database.sqlAppDB();
    }

    public static devInterface getDevObject() {
        return new database.sqlDevDB();
    }

    public static userInterface getUserObject() {
        return new database.sqlUserDB();
    }

//    public static appInterface getAppObject() {
//        return new database.App();
//    }
//
//    public static devInterface getDevObject() {
//        return new database.Developer();
//    }
//
//    public static userInterface getUserObject() {
//        return new database.User();
//    }
}