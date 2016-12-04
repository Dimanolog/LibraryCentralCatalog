package com.company.beans.library;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dimanolog on 28.12.2015.
 */
public class LibraryFactory
{   private static Logger logger = Logger.getLogger(LibraryFactory.class.getName());
    public static AbstractLibrary getLibrary(File bookFile)
    {
        String directoryName = bookFile.getParent();
        Pattern pattern = Pattern.compile("^(?:.+?)_(.+)$");
        Matcher matcher = pattern.matcher(directoryName);
        if (matcher.matches()) {
            return new Library(matcher.group(1));
        } else {
            logger.log(Level.WARNING,"wrong folder name:"+directoryName);
            return new NulLibrary();

        }
    }

}
