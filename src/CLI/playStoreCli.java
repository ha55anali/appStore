package CLI;

import java.util.Scanner;

public class playStoreCli{

    public static void main(String [] args ){

        System.out.println("1. user page");
        System.out.println("2. dev page");
        System.out.println("-1. exit");
        int choice = getChoiceInput(2);

        switch (choice)
        {
            case 1:
                new userSession();
            case 2:
                devMethods obj = new devMethods();
                obj.signInPage();
                obj.categoryPage();
                break;
            case -1:
                System.exit(1);
        }

    }

    private static int getChoiceInput(int end)
    {
        Scanner cin= new Scanner(System.in);
        int choice = Integer.parseInt(cin.nextLine());
        while (choice < 0 && choice > end  && choice != -1)
        {
            choice = Integer.parseInt(cin.nextLine());
        }

        return choice;
    }
}