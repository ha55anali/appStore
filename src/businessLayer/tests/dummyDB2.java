package businessLayer.tests;

import dbaseInterface.*;

import dbaseInterface.*;
import java.util.*;

public class dummyDB2 implements dbaseInterface.userInterface {

    public void addUser(dbaseInterface.userDetails user) {
        if (user.Name == "nushiPishi")
            return;
        else
            throw new IllegalArgumentException("hello");
    }

    public userDetails getUserDetails(int userID) {
        return null;
    }

    // if user does not exist throw invalidargumentexception
    public void removeUser(int userID) {
    }

    // if user or app does not exists, throw invalidargumentexception
    public void addInstalledApp(int appID, int userID) {
    }

    // if user or app does not exists, throw invalidargumentexception
    public void removeInstalledApp(int appID, int userID) {
    }

}
