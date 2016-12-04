package com.company.exceptions;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dimanolog on 09.01.2016.
 */
public class FileFormatException extends Exception
{
    private Logger logger = Logger.getLogger(FileFormatException.class.getName());

    public FileFormatException(File wrongFormatFile)
    {
        super();
        logger.log(Level.WARNING,"wrong file format: "+ wrongFormatFile.getPath());

    }

}
