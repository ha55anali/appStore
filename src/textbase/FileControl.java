package textbase;

import java.io.*;

public class FileControl {

    // this method mamkes sure if the given file i.e. obj exists.
    // if it doesn't it creates the file itself.
    //returns 1 if file is created, 0 if it already exists and -1 for error
    public static int CheckFile(File obj) {

        int val = 0;        //return value
        if (!obj.exists()) {
            try {
                if (obj.createNewFile()) {
                    //System.out.println("File Created");
                    val = 1;
                }
            } catch (IOException e) {
                System.out.println("An error occured in creating file\n");
                e.printStackTrace();
                val = -1;
            }
        } else {
            //System.out.println("File Exist");
            val = 0;
        }

        return val;
    }

    // this method appends the passed String str in the given File obj
    public static boolean AppendInFile(File obj, String str) {

        try (FileWriter fw = new FileWriter(obj, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(str);
            out.close();
            bw.close();
            fw.close();
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
            System.out.println("An error occured\n");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
