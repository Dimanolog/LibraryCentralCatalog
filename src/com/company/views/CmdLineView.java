package com.company.views;

import com.company.presenter.IModelPresenter;
import com.company.presenter.Presenter;

import java.util.Scanner;

/**
 * Created by Dimanolog on 25.01.2016.
 */
public class CmdLineView implements IPresentView
{
    private final String SYNTAX_ERROR = "SYNTAXERROR";
    private final String FOUND = "FOUND";
    private final String FOUND_MISSING = "FOUNDMISSING";
    private final String NOT_FOUND = "NOTFOUND";
    private final String OK = "OK";
    private final String RESERVED = "RESERVED";
    private final String ALREADY_RETURNED = "ALREADYRETURNED";
    private final String AUTHOR = "author";
    private final String TITLE = "name";
    private final String ID = "id";
    private final String DATE = "date";
    private final String ISSUED = "issued";
    private final String SUBSCRIBER = "abonent";
    private final String LIB = "lib";
    private final String DELIMITER = "=";
    private final String EXIT = "EXIT";

    private IModelPresenter presenter;


    public CmdLineView()
    {
        presenter = new Presenter(this);

    }

    public void startApplication()
    {
        System.out.println("Start program: \"Central library catalog\"");
        waitForInput();
    }

    public void waitForInput()
    {
        String cmd;
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("Сommand: ");
            cmd = in.nextLine();
            presenter.performCmd(cmd);

        } while (!cmd.equalsIgnoreCase(EXIT));
    }

    public void notFound()
    {
        System.out.println(NOT_FOUND);
    }

    public void syntaxError()
    {
        System.out.println(SYNTAX_ERROR);
    }


    public void alreadyReturned()
    {
        System.out.println(ALREADY_RETURNED);
    }


    public void orderOK(String subscriber, String date)
    {
        System.out.println(OK + " " + SUBSCRIBER + DELIMITER + subscriber + " " + DATE + DELIMITER + date);

    }

    public void orderReserved(String subscriber, String date)
    {
        System.out.println(RESERVED + " " + SUBSCRIBER + DELIMITER + subscriber + " " + DATE + DELIMITER + date);
    }

    public void returnOK(String subscriber)
    {
        System.out.println(OK + " " + SUBSCRIBER + DELIMITER + subscriber);
    }

    public void foundMissing(long index, String library, String date)
    {
        System.out.println(FOUND_MISSING + " " + ID + DELIMITER + index + " " + LIB + DELIMITER + library + " " + ISSUED
                + DELIMITER + date);
    }

    public void foundOK(long index, String library)
    {
        System.out.println(FOUND + " " + ID + DELIMITER + index + " " + LIB + DELIMITER + library);
    }

    public void error(String error)
    {
        System.out.println("ERROR: " + error);

    }

}

