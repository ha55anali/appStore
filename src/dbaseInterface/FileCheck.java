package dbaseInterface;

import java.io.*;

public class FileCheck {
    
    //this function mamkes sure if the given file i.e. obj exists.
    //if it doesn't it creates the file itself.
    public static boolean CheckFile(File obj) {
        
        if(!obj.exists()){
            try {
                if(obj.createNewFile()){
                    System.out.println("File Created");
                }
            }
            catch (IOException e) {
                System.out.println("An error occured\n");
                e.printStackTrace();
                return false;
            }
        }
        else{            
            System.out.println("File Exist");
        }

        return true;
    }
}
