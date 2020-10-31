package businessLayer;

import java.time.LocalDate;

public class User implements blInterface.userInterface {
    dbaseInterface.userInterface dbUser;

    public User() {
        dbUser = dbaseInterface.dbFactory.getUserObject();
    }

    public void addUser(blInterface.userDetails user) {
        dbaseInterface.userDetails userdetails = new dbaseInterface.userDetails(user.Name, user.userID, user.DOB,
                user.email, user.password);
        dbUser.addUser(userdetails);
    }

    public void removeUser(int userID) {
        dbUser.removeUser(userID);
    }

    public void authenticateUser(int userID, String password) {
        dbUser.authenticateUser(userID);
    }

    public void addCard(int cardNo, int ExpYear) {
        dbUser.addCard(cardNo, ExpYear);
    }

    public void setPaymentMethod(String method) {
        dbUser.setPaymentMethod(method);
    }

    public void changeCardDetails(int cardNo, int NewExpYear) {
        dbUser.changeCardDetails(cardNo, NewExpYear);
    }

    public void removeCardDetails(int cardNo) {
        dbUser.removeCardDetails(cardNo);
    }
}
