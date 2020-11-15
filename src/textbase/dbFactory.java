package textbase;

public class dbFactory {



    // public static appInterface getAppObject() {
    //     return new database.sqlAppDB();
    // }

    // public static devInterface getDevObject() {
    //     return new database.sqlDevDB();
    // }

    // public static userInterface getUserObject() {
    //     return new database.sqlUserDB();
    // }

   public static dbaseInterface.appInterface getAppObject() {
       return new textbase.App();
   }

   public static dbaseInterface.devInterface getDevObject() {
       return new textbase.Developer();
   }

   public static dbaseInterface.userInterface getUserObject() {
       return new textbase.User();
   }
}