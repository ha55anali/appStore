package businessLayer.tests;

public class sqlFactory {
    public static dbaseInterface.appInterface getAppObject() {
        return new businessLayer.tests.dummyDB();
    }

    public static dbaseInterface.devInterface getDevObject() {
        return null;
    }

    public static dbaseInterface.userInterface getUserObject() {
        return new businessLayer.tests.dummyDB2();
    }

}

