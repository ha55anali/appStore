package dbaseInterface;

import java.io.*;

public class FileControl {

    // this method mamkes sure if the given file i.e. obj exists.
    // if it doesn't it creates the file itself.
    public static boolean CheckFile(File obj) {

        if (!obj.exists()) {
            try {
                if (obj.createNewFile()) {
                    System.out.println("File Created");
                }
            } catch (IOException e) {
                System.out.println("An error occured\n");
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("File Exist");
        }

        return true;
    }

    // this method appends the passed String str in the given File obj
    public static boolean AppendInFile(File obj, String str) {

        try (FileWriter fw = new FileWriter("test123.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("the text");
            out.println("more text");
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
            System.out.println("An error occured\n");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
