package com.company.data_formats;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.company.beans.book.AbstractBook;
import com.company.beans.book.BookFactory;
import com.company.beans.library.AbstractLibrary;
import com.company.beans.library.LibraryFactory;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dimanolog on 09.12.2015.
 */
public class CSVBook implements ILiteraryData
{
    private final char SEPARATOR = ',';

    private static Logger logger = Logger.getLogger(CSVBook.class.getName());

    private List<File> csvFiles;
    private int fileCount;
    private int lineCount;
    private AbstractLibrary library;
    private File currentFile;
    private String[] currentLine;
    private CSVReader csvReader;


    public CSVBook(List<File> csvFiles)
    {
        this.csvFiles = csvFiles;
        resetCounter();
    }

    public AbstractBook nextBook()
    {
        long index = -1L;
        String author = "";
        String title = "";
        String date = "";
        String subscriber = "";

        if (currentLine != null) {
            if (currentLine.length == 5) {
                try {
                    index = Long.parseLong(currentLine[0]);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "wrong book index in file: "
                            + currentFile.getPath() + " in line: " + lineCount);
                }
                author = currentLine[1];
                title = currentLine[2];
                date = currentLine[3];
                subscriber = currentLine[4];
            } else {
                logger.log(Level.WARNING, "wrong line format in file: "
                        + currentFile.getPath() + " in line: " + lineCount);
            }
        }
        AbstractBook book = BookFactory.getAbstractBook(index, author, title, date, subscriber, library);

        next();

        return book;
    }

    public boolean updateBook(AbstractBook book)
    {
        long index;

        String[] nextLine;
        long bookID = book.getIndex();

        CSVWriter writer = null;

        File tempFile = new File(currentFile.getParent(), currentFile.getName() + ".temp");

        try {
            csvReader.close();
            csvReader = new CSVReader(new FileReader(currentFile), SEPARATOR, '\0');
            writer = new CSVWriter(new FileWriter(tempFile), SEPARATOR, '\0');

            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length == 5) {
                    try {
                        index = Long.parseLong(nextLine[0]);
                    } catch (NumberFormatException e) {
                        index = -1L;
                    }
                    if (bookID == index) {
                        nextLine[1] = book.getAuthor();
                        nextLine[2] = book.getTitle();
                        nextLine[3] = book.getDate();
                        nextLine[4] = book.getSubscriber();
                    }
                }
                writer.writeNext(nextLine);
            }
            writer.flush();
        } catch (IOException e) {
            return false;
        } finally {
            if (writer != null)
                try {
                    writer.close();
                    csvReader.close();
                } catch (IOException e) {
                    return false;
                }


            if (!currentFile.delete()) {
                logger.log(Level.WARNING, "cant delete file: " + currentFile.getPath());
                return false;
            } else if (!tempFile.renameTo(currentFile)) {
                logger.log(Level.WARNING, "cant rename file : " + tempFile.getPath());
                return false;
            }

            try {
                csvReader = new CSVReader(new FileReader(currentFile), SEPARATOR, '\0');
            } catch (FileNotFoundException e) {
                logger.log(Level.SEVERE, "cant find file:" + currentFile.getPath(), e);
                nextFile();
            }
        }

        return true;
    }

    public boolean hasNext()
    {
        return currentLine != null;
    }

    public boolean next()
    {
        try {
            if (csvReader != null) {
                if ((currentLine = csvReader.readNext()) != null) {
                    ++lineCount;
                    return true;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "cant read next line: " + currentFile.getPath()
                    + " line: " + lineCount, e);
        }
        if (nextFile()) {
            next();
        }

        return false;
    }

    public void resetCounter()
    {
        fileCount = 0;
        nextFile();
        next();
    }

    private boolean hasNextFile()
    {
        return csvFiles.size() > fileCount;

    }

    private boolean nextFile()
    {
        if (hasNextFile()) {
            try {
                if (csvReader != null)
                    csvReader.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "cant close csvReader", e);
            }
            currentFile = csvFiles.get(fileCount);
            library = LibraryFactory.getLibrary(currentFile);
            ++fileCount;
            lineCount = 1;
            try {
                csvReader = new CSVReader(new FileReader(currentFile), SEPARATOR, '\0');

            } catch (FileNotFoundException e) {
                logger.log(Level.SEVERE, "cant find file:" + currentFile.getPath(), e);
                nextFile();// go to next file
            }

        } else return false;

        return true;
    }
}
