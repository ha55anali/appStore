package businessLayer.tests;

import java.time.LocalDate;

import dbaseInterface.*;

public class dummyDB2 implements dbaseInterface.userInterface {

    public void addUser(dbaseInterface.userDetails user) {
        if (user.Name == "nushiPishi")
            return;
        else
            throw new IllegalArgumentException("hello");
    }

    public userDetails getUserDetails(int userID) {
        if (userID < 100)
            return new userDetails("nushiPishi", 69, LocalDate.now(), "nushi@pishi.com", "yibz6969");
        else
            throw new IllegalArgumentException("alo jee error aya reee");
    }

    // if user does not exist throw invalidargumentexception
    public void removeUser(int userID) {
        if (userID == 69)
            return;
        else
            throw new IllegalArgumentException("alo jee ik hor error aya jay");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void addInstalledApp(int appID, int userID) {
        if (appID == 69)
            throw new IllegalArgumentException("app already installed dumbo");
        else if (userID != 69)
            throw new IllegalArgumentException("User dont exist dumbas");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void removeInstalledApp(int appID, int userID) {
        if (appID != 69)
            throw new IllegalArgumentException("app not installed dumbo");
    }

    // if user does not exist throw invalidargumentexception
    public void authenticateUser(int userID) {
        if (userID != 69)
            throw new IllegalArgumentException("ni krna stfu");
    }

    public void addCard(int cardNo, int ExpYear) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope cant add that card no");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void setPaymentMethod(String method) {
        if (method != "dhui")
            throw new IllegalArgumentException("dont accept this method");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void changeCardDetails(int cardNo, int NewExpYear) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope cant change that card's deets");
    }

    // if user or app does not exists, throw invalidargumentexception
    public void removeCardDetails(int cardNo) {
        if (cardNo != 69)
            throw new IllegalArgumentException("Nope nope nope");
    }

}
