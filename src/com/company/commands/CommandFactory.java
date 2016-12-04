package com.company.commands;

import com.company.presenter.IViewPresenter;
import com.company.presenter.Presenter;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dimanolog on 24.01.2016.
 */
public class CommandFactory
{
    private static final String AUTHOR = "author";
    private static final String TITLE = "name";
    private static final String ID = "id";
    private static final String SUBSCRIBER = "abonent";

    private IViewPresenter presenter;
    private MatchResult matchResult;

   private enum RegexPatterns
    {
        FIND("(?i)^FIND\\s(" + AUTHOR + "|" + TITLE + ")=(.+?)(?:\\s(" + AUTHOR + "|" + TITLE + ")=(.+?))?$"),
        ORDER("(?i)^ORDER\\s(" + ID + ")=(\\d{1,16})\\s(" + SUBSCRIBER + ")=(.+?)$"),
        RETURN("(?i)(?:^RETURN\\s(?:" + ID + ")=(\\d{1,16})$)");

        private final String regexPattern;

        RegexPatterns(String regexPattern)
        {
            this.regexPattern = regexPattern;
        }

        private String getRegexPattern()
        {
            return regexPattern;

        }
    }

    //String findCmdPattern = "(?i)^" + FIND + "\\s(" + AUTHOR + "|" + TITLE + ")=(.+?)(?:\\s(" + AUTHOR + "|" + TITLE + ")=(.+?))?$";
    //String orderCmdPattern = "(?i)^" + ORDER + "\\s(" + ID + ")=(\\d{1,16})\\s(" + SUBSCRIBER + ")=(.+?)$";
    //String returnCmdPattern = "(?i)(?:^" + RETURN + "\\s(?:" + ID + ")=(\\d{1,16})$)";

    public CommandFactory(IViewPresenter presenter)
    {
        this.presenter = presenter;
    }

    public Commands getCommand(String cmd, Presenter controller)
    {
        cmd = cmd.trim();

        for (RegexPatterns regex : RegexPatterns.values()) {
            if (validateCmd(cmd, regex.getRegexPattern())) {
                switch (regex) {
                    case FIND:
                        return createFindCmd();
                    case ORDER:
                        return createOrderCmd();
                    case RETURN:
                        return createReturnCmd();

                }
            }

        }

        return new WrongCmd(controller);
    }


    private Commands createFindCmd()
    {
        String author = "";
        String title = "";
        int size = matchResult.groupCount();
        for (int i = 1; i < size; i++) {
            if (matchResult.group(i) != null) {
                if (matchResult.group(i).equalsIgnoreCase(AUTHOR)) {
                    ++i;
                    author = (matchResult.group(i));
                    continue;
                }

                if (matchResult.group(i).equalsIgnoreCase(TITLE)) {
                    ++i;
                    title = (matchResult.group(i));
                }

            }else break;
        }

        return new FindCmd(author, title, presenter);

    }

    private Commands createOrderCmd()
    {
        String id = matchResult.group(2);
        String subscriber = matchResult.group(4);

        return new OrderCmd(id, subscriber, presenter);

    }

    private Commands createReturnCmd()
    {
        String id = matchResult.group(1);

        return new ReturnCmd(id, presenter);

    }


    private boolean validateCmd(String cmd, String regexPattern)
    {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(cmd);

        if (matcher.matches()) {
            matchResult = matcher.toMatchResult();
            return true;
        }
        return false;

    }

   /* private Commands cmdSelection(RegexPatterns regexPatterns)
    {
        switch (regexPatterns) {
            case FIND:
                return createFindCmd();
            case ORDER:
                return createOrderCmd();
            case RETURN:
                return createReturnCmd();

            default:
                return new WrongCmd();

        }

    }*/
}