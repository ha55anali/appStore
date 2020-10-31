package businessLayer;

import java.time.LocalDate;

public class User implements blInterface.userInterface {
    dbaseInterface.userInterface dbUser;

    public User() {
        dbUser = dbaseInterface.dbFactory.getUserObject();
    }

    public void addUser(blInterface.userDetails user) {
        dbaseInterface.userDetails userdetails = new dbaseInterface.userDetails();
        userdetails.Name = user.Name;
        userdetails.DOB = user.DOB;
        userdetails.email = user.email;
        userdetails.password = user.password;
        userdetails.userID = user.userID;
        dbUser.addUser(userdetails);
        return;
    }

    public void removeUser(int userID) {

        return;
    }

    public void authenticateUser(int userID, String password) {

    }

    public void addCard(int cardNo, int ExpYear) {

    }

    public void setPaymentMethod(String method) {

    }

    public void changeCardDetails(int cardNo, int NewExpYear) {

    }

    public void removeCardDetails(int cardNo) {

    }
}
