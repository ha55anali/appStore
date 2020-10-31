import java.time.LocalDate;

class test {
    public static void main(String[] args) {
        blInterface.userInterface userInt = businessLayer.blFactory.getUserObject();
        try {
            blInterface.userDetails u = new blInterface.userDetails("nushiPishi", 1, LocalDate.now(), "nushi@pishi.com",
                    "yibz6969");
            userInt.addUser(u);
            userInt.removeUser(u.userID);
            System.out.println(u.Name);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}