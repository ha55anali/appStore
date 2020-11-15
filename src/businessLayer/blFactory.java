package businessLayer;

public class blFactory {

    public static blInterface.AppCollectionInterface getAppCollectionObject() {
        return new App();
    }

    public static blInterface.individualAppInterface getIndividualAppObject() {
        return new App();
    }

    public static blInterface.devInterface getDevObject() {
        return new Dev();
    }

    public static blInterface.userInterface getUserObject() {
        return new User();
    }
}
