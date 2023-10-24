package com.rlssgroup;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class logging {

    public static void logEvent(String event){

        try {
            FileWriter myWriter = new FileWriter("T:\\development\\java\\shoppinglogs.txt", true);
            myWriter.write("\n");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
            myWriter.write(timeStamp+ " ");
            myWriter.write(event);
            myWriter.close();
        } catch (IOException ioe) {
            System.out.println("An error occurred.");
            ioe.printStackTrace();
        }
    }

}
    