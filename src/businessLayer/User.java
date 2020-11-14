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

    public blInterface.userDetails getUserDetails(int userID)
    {
        dbaseInterface.userDetails dbus=dbUser.getUserDetails(userID);
        blInterface.userDetails us = new blInterface.userDetails(dbus.Name, dbus.userID, dbus.DOB, dbus.email, dbus.password);
        return us;
    }

    public int addUser(blInterface.userDetails user) {
        // // user must be atleast 13 years of age
        // if (stringIsNullOrEmpty(user.Name) || stringIsNullOrEmpty(user.email) || stringIsNullOrEmpty(user.password)
        //         || user.userID < 0 || user.DOB == null || LocalDate.now().getYear() - user.DOB.getYear() < 13
        //         || dbUser.checkUserExists(user.userID) || dbUser.checkEmailExists(user.userID, user.email)) {
        //     return false;
        // }

        //check if email is unique
        if (dbUser.checkEmailExists(user.email))
            return -1;

        dbaseInterface.userDetails userdetails = new dbaseInterface.userDetails(user.Name, user.userID, user.DOB,
                user.email, user.password);
        dbUser.addUser(userdetails);
        return 1;
    }

    public void removeUser(int userID) {
        if (userID < 0 || dbUser.checkUserExists(userID) == false)
            throw new IllegalArgumentException("user does not exist");
        dbUser.removeUser(userID);
    }

    public int authenticateUser(String email,String password) {
        return dbUser.authenticateUser(email, password);
    }

}
