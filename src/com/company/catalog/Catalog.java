package com.company.catalog;

import com.company.beans.book.AbstractBook;
import com.company.beans.book.BookFactory;
import com.company.data_formats.CSVBook;
import com.company.data_formats.TXTBook;
import com.company.data_formats.ILiteraryData;
import com.company.file_system.ExtensionFileFilter;
import com.company.file_system.FileSearcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimanolog on 13.12.2015.
 */
public class Catalog
{
    static private final String PATH = "./Libraries";
    static private final Catalog INSTANCE = new Catalog(PATH);

    private final String TXT_EXTENSION = "properties";
    private final String CSV_EXTENSION = "csv";

    private List<ILiteraryData> bookFiles;

    private Catalog(String librariesPath)
    {
        setLibrariesPath(librariesPath);
    }

    static public Catalog getInstance()
    {
        return INSTANCE;
    }

    private void setLibrariesPath(String librariesPath)
    {
        List<File> txtFiles = FileSearcher.recursiveFileSearch(new File(librariesPath), new ExtensionFileFilter(TXT_EXTENSION));
        List<File> csvFiles = FileSearcher.recursiveFileSearch(new File(librariesPath), new ExtensionFileFilter(CSV_EXTENSION));
        bookFiles = new ArrayList<ILiteraryData>();
        bookFiles.add(new CSVBook(csvFiles));
        bookFiles.add(new TXTBook(txtFiles));

    }

    public AbstractBook selectByID(long id)
    {
        AbstractBook book;

        for (ILiteraryData bookFile : bookFiles) {
            bookFile.resetCounter();
            while (bookFile.hasNext()) {
                book = bookFile.nextBook();
                if (!book.isNull()) {
                    if (book.getIndex() == id) {
                        return book;
                    }
                }
            }
        }
        return BookFactory.getNullBook();
    }


    public List<AbstractBook> selectByAuthor(String author)
    {
        AbstractBook absBook;
        List<AbstractBook> bookList = new ArrayList<AbstractBook>();
        for (ILiteraryData bookFile : bookFiles) {
            bookFile.resetCounter();
            while (bookFile.hasNext()) {

                absBook = bookFile.nextBook();
                if (!absBook.isNull()) {
                    if (absBook.getAuthor().contains(author)) {
                        bookList.add(absBook);
                    }
                }

            }
        }

        return bookList;

    }

    public List<AbstractBook> selectByTittle(String title)
    {
        AbstractBook book;
        List<AbstractBook> bookList = new ArrayList<AbstractBook>();
        for (ILiteraryData bookFile : bookFiles) {
            bookFile.resetCounter();
            while (bookFile.hasNext()) {
                book = bookFile.nextBook();
                if (!book.isNull()) {
                    if (book.getTitle().contains(title)) {
                        bookList.add(book);
                    }
                }
            }
        }

        return bookList;

    }

    public boolean updateBook(AbstractBook book)
    {
        long bookID = book.getIndex();
        AbstractBook oldBook;
        for (ILiteraryData bookFile : bookFiles) {
            bookFile.resetCounter();
            while (bookFile.hasNext()) {
                oldBook = bookFile.nextBook();
                if (!oldBook.isNull()) {
                    if (oldBook.getIndex() == bookID) {
                        return bookFile.updateBook(book);
                    }
                }
            }
        }
        return false;
    }

}


