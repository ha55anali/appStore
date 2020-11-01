package blInterface;

public interface userInterface {
    public boolean addUser(userDetails user);

    public boolean removeUser(int userID);

    public boolean authenticateUser(int userID, String password);

    public boolean addCard(int userID, int cardNo, int ExpYear);

    public boolean setPaymentMethod(int userID, String method);

    public boolean changeCardDetails(int userID, int cardNo, int NewExpYear);

    public boolean removeCardDetails(int userID, int cardNo);
}
