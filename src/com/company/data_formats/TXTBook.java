package com.company.data_formats;

import com.company.beans.book.AbstractBook;
import com.company.beans.book.BookFactory;
import com.company.beans.library.AbstractLibrary;
import com.company.beans.library.LibraryFactory;
import com.company.exceptions.FileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dimanolog on 08.12.2015.
 */

public class TXTBook implements ILiteraryData
{
    private static Logger logger = Logger.getLogger(TXTBook.class.getName());

    private final String INDEX = "Index";
    private final String AUTHOR = "Author";
    private final String TITLE = "Name";
    private final String DATE = "Issued";
    private final String SUBSCRIBER = "Issuedto";
    private final String DELIMITER = "=";

    private List<File> txtFiles;
    private List<String> fileStrings;
    private File currentFile;
    private int count;


    public TXTBook(List<File> txtFiles)
    {
        this.txtFiles = txtFiles;
        resetCounter();
    }

    public AbstractBook nextBook()
    {
        long index = -1L;
        String author = "";
        String title = "";
        String date = "";
        String subscriber = "";
        AbstractLibrary library = null;
        if (next()) {
            try {
                try {
                    index = Long.parseLong(searchValue(INDEX));
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "wrong beans index in file: "
                            + currentFile.getPath());
                }
                author = searchValue(AUTHOR);
                title = searchValue(TITLE);
                date = searchValue(DATE);
                subscriber = searchValue(SUBSCRIBER);
                library = LibraryFactory.getLibrary(currentFile);
            } catch (FileFormatException e) {
            }

        }
        AbstractBook book = BookFactory.getAbstractBook(index, author, title, date, subscriber, library);

        return book;
    }

    public boolean next()
    {
        if (hasNext()) {
            currentFile = txtFiles.get(count);
            ++count;
            fileStrings = new ArrayList<String>(5);
            String line;
            BufferedReader bf = null;
            try {
                bf = new BufferedReader(new FileReader(currentFile));

                while ((line = bf.readLine()) != null) {
                    if (!line.equals("")) {
                        fileStrings.add(line);
                    }
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "cant read file: "
                        + currentFile.getPath(), e);
                next();

            } finally {
                try {
                    if (bf != null)
                        bf.close();
                } catch (IOException e) {

                }
            }
        } else return false;

        return true;
    }

    public boolean updateBook(AbstractBook book)
    {
        BufferedWriter bufWriter = null;
        String lineSeparator = System.getProperty("line.separator");
        try {
            bufWriter = new BufferedWriter(new PrintWriter(currentFile));

            bufWriter.write(INDEX + DELIMITER + book.getIndex() + lineSeparator);
            bufWriter.write(AUTHOR + DELIMITER + book.getAuthor() + lineSeparator);
            bufWriter.write(TITLE + DELIMITER + book.getTitle() + lineSeparator);
            bufWriter.write(DATE + DELIMITER + book.getDate() + lineSeparator);
            bufWriter.write(SUBSCRIBER + DELIMITER + book.getSubscriber() + lineSeparator);

            bufWriter.flush();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "cant write file: "
                    + currentFile.getPath(), e);
            return false;
        } finally {
            if (bufWriter != null) {
                try {
                    bufWriter.close();
                } catch (IOException e) {

                }
            }
        }

        return true;
    }

    public boolean hasNext()
    {
        return count < txtFiles.size();
    }

    public void resetCounter()
    {
        this.count = 0;
    }


    private String searchValue(String searchValue) throws FileFormatException
    {
        String values[];
        String splitter = searchValue + DELIMITER;
        for (String line : fileStrings) {
            if (line.contains(splitter)) {
                values = line.split(splitter);
                if (values.length > 1) {
                    return values[1];
                } else return "";
            }
        }
        throw new FileFormatException(currentFile);
    }
}


