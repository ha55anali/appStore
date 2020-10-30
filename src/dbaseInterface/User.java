package dbaseInterface;

import java.io.*;
import java.io.IOException;

public class User {
    
    //made this main() to test the functions ill be making in this class
    public static void main(String[] args) {
        
        File test = new File("test123.txt");

        System.out.println(FileCheck.CheckFile(test));
    }
}
