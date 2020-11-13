package blInterface;

public interface userInterface
{
    //if invalid userID, exception is thrown
    public userDetails getUserDetails(int userID);

    //userID is assigned automatically
    //userID of the new user is returned
    //-1 is returned if email is not unique
    public int addUser(userDetails user);

    //exception is thrown if userID is not invalid
    public void removeUser(int userID) throws IllegalArgumentException;

    //return userID if valid login    
    //returns -1 if invalid
    public int authenticateUser(String email, String password);
}
