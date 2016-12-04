package com.company.beans.book;

import com.company.beans.library.AbstractLibrary;
import com.company.beans.library.Library;

/**
 * Created by Dimanolog on 20.01.2016.
 */
class NullBook extends AbstractBook
{

    NullBook()
    {
    }

    @Override
    public boolean isNull()
    {
        return true;
    }

    @Override
    public long getIndex()
    {
        return -1;
    }


    @Override
    void setIndex(long index)
    {

    }

    @Override
    void setLibrary(AbstractLibrary library)
    {

    }

    @Override
    void setDate(String date)
    {

    }

    @Override
    void setTitle(String title)
    {

    }

    @Override
    void setAuthor(String author)
    {

    }

    @Override
    void setLibrary(Library library)
    {

    }

    @Override
    public String getAuthor()
    {
        return "unknown";
    }

    @Override
    public AbstractLibrary getLibrary()
    {
        return null;
    }

    @Override
    public String getTitle()
    {
        return null;
    }


    @Override
    public String getDate()
    {
        return "unknown";
    }

    @Override
    public boolean isAvailable()
    {
        return false;
    }

    @Override
    public boolean returnBook()
    {
        return false;
    }

    @Override
    public boolean orderBook(String subscriber)
    {
        return false;
    }

    @Override
    public String getSubscriber()
    {
        return "unknown";
    }

    @Override
    void setSubscriber(String subscriber)
    {

    }


}
