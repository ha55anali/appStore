package blInterface;

public interface userInterface
{
    public userDetails getUserDetails(int userID);

    public void addUser(userDetails user);

    public void removeUser(int userID);

    public void authenticateUser(int userID, String password);

    public void addCard(int cardNo, int ExpYear);

    public void setPaymentMethod(String method);

    public void changeCardDetails(int cardNo, int NewExpYear);

    public void removeCardDetails(int cardNo);
}
