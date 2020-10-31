package businessLayer;

public class blFactory {
    public static blInterface.appInterface getAppObject() {
        return new App();
    }

    public static blInterface.devInterface getDevObject() {
        return null;
    }

    public static blInterface.userInterface getUserObject() {
        return new User();
    }
}
