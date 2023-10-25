package com.rlssgroup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

public class Logging {

    public static void logEvent(String event) throws IOException{
        Config configs = new Config();
        String logLoc = configs.getLogLoc();
        FileWriter myWriter = new FileWriter(logLoc, true);
        myWriter.write("\n");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
        myWriter.write(timeStamp+ " ");
        myWriter.write(event);
        myWriter.close();
    }

    public static void logException(Exception e) throws IOException{
        Config configs = new Config();
        String logLoc = configs.getLogLoc();
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        FileWriter myWriter = new FileWriter(logLoc, true);
        myWriter.write("\n");
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
        myWriter.write(timeStamp+ " ");
        myWriter.write(exceptionAsString);
        myWriter.close();
    }
}
    