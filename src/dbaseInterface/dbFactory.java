package dbaseInterface;

public class dbFactory {
    public static appInterface getAppObject() {
        return null;
    }

    public static devInterface getDevObject() {
        return null;
    }

    public static userInterface getUserObject() {
        return new businessLayer.tests.dummyDB2();
    }

}
