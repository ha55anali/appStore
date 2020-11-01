package businessLayer;

import java.time.LocalDate;

public class User implements blInterface.userInterface {
    dbaseInterface.userInterface dbUser;

    private boolean stringIsNullOrEmpty(String str) {
        return (str == null || str.isBlank()) ? true : false;
    }

    public User() {
        dbUser = dbaseInterface.dbFactory.getUserObject();
    }

    public boolean addUser(blInterface.userDetails user) {
        // user must be atleast 13 years of age
        if (stringIsNullOrEmpty(user.Name) || stringIsNullOrEmpty(user.email) || stringIsNullOrEmpty(user.password)
                || user.userID < 0 || user.DOB == null || LocalDate.now().getYear() - user.DOB.getYear() < 13
                || dbUser.checkUserExists(user.userID) || dbUser.checkEmailExists(user.userID, user.email)) {
            return false;
        }

        dbaseInterface.userDetails userdetails = new dbaseInterface.userDetails(user.Name, user.userID, user.DOB,
                user.email, user.password);
        dbUser.addUser(userdetails);
        return true;
    }

    public boolean removeUser(int userID) {
        if (userID < 0 || dbUser.checkUserExists(userID) == false)
            return false;
        dbUser.removeUser(userID);
        return true;
    }

    public boolean authenticateUser(int userID, String password) {
        if (userID < 0 || stringIsNullOrEmpty(password) || dbUser.checkUserExists(userID) == false)
            return false;
        dbUser.authenticateUser(userID, password);
        return true;
    }

    // this kinda incomplete lmao
    // TODO: add some way to find out that another user does not have the same card
    // no
    public boolean addCard(int userID, int cardNo, int ExpYear) {
        if (userID < 0 || cardNo < 0 || ExpYear < LocalDate.now().getYear() || dbUser.checkUserExists(userID) == false
                || dbUser.checkUserCard(userID, cardNo))
            return false;
        dbUser.addCard(userID, cardNo, ExpYear);
        return true;
    }

    public boolean setPaymentMethod(int userID, String method) {
        if (userID < 0 || stringIsNullOrEmpty(method) || dbUser.checkUserExists(userID) == false || method != "Cash"
                || method != "Card")
            return false;
        dbUser.setPaymentMethod(userID, method);
        return true;
    }

    public boolean changeCardDetails(int userID, int cardNo, int NewExpYear) {
        if (userID < 0 || dbUser.checkUserExists(userID) == false || cardNo < 0
                || dbUser.checkUserCard(userID, cardNo) == false || NewExpYear < LocalDate.now().getYear()) {
            return false;
        }
        dbUser.changeCardDetails(userID, cardNo, NewExpYear);
        return true;
    }

    public boolean removeCardDetails(int userID, int cardNo) {
        if (userID < 0 || dbUser.checkUserExists(userID) == false || cardNo < 0
                || dbUser.checkUserCard(userID, cardNo) == false) {
            return false;
        }

        dbUser.removeCardDetails(userID, cardNo);
        return true;
    }
}
