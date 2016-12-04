package com.company.beans.book;

import com.company.beans.library.AbstractLibrary;
import com.company.beans.library.Library;

/**
 * Created by Dimanolog on 08.12.2015.
 */
abstract public class AbstractBook
{
    long index;
    AbstractLibrary library;
    String author;
    String title;
    String date;
    String subscriber;
    boolean available;

    abstract void setIndex(long index);

    abstract void setLibrary(AbstractLibrary library);

    abstract void setDate(String date);

    abstract void setTitle(String title);

    abstract void setAuthor(String author);

    abstract void setLibrary(Library library);

    abstract void setSubscriber(String subscriber);

    abstract public boolean isNull();

    abstract public long getIndex();

    abstract public String getAuthor();

    abstract public AbstractLibrary getLibrary();

    abstract public String getTitle();

    abstract public String getSubscriber();

    abstract public String getDate();

    abstract public boolean isAvailable();

    abstract public boolean returnBook();

    abstract public boolean orderBook( String subscriber);

}
