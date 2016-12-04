package com.company;

import com.company.views.CmdLineView;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

public class Main
{
    public static void main(String[] args)
    {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }

        File logs=new File("./logs");
        if(!logs.exists()) {
            try {
                logs.mkdirs();
            } catch (SecurityException e) {
                System.err.println("cant create logs directory: " + e.toString());
            }
        }
        CmdLineView view= new CmdLineView();
        view.startApplication();

    }

}
