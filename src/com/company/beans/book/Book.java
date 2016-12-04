package com.company.beans.book;

import com.company.beans.library.AbstractLibrary;
import com.company.beans.library.Library;
import com.company.date.Date;

/**
 * Created by Dimanolog on 20.01.2016.
 */
class Book extends AbstractBook
{

    Book(long index, String author, String title, String date, String subscriber, AbstractLibrary library)
    {
        setIndex(index);
        setAuthor(author);
        setTitle(title);
        setDate(date);
        setSubscriber(subscriber);
        setLibrary(library);

        available=this.subscriber.equals("");
    }

     Book(Book book)
    {
        setIndex(book.getIndex());
        setAuthor(book.getAuthor());
        setTitle(book.getTitle());
        setDate(book.getDate());
        setSubscriber(book.getSubscriber());
        setLibrary(book.getLibrary());

        available=this.subscriber.equals("");
    }

    @Override
    protected void setIndex(long index)
    {
        this.index=index;
    }

    @Override
    void setSubscriber(String subscriber)
    {
      if(subscriber!=null){this.subscriber=subscriber;}
        else{this.subscriber="";}
    }

    @Override
     void setLibrary(AbstractLibrary library)
    {
        this.library=library;
    }

    @Override
    void setDate(String date)
    {
        if(date!=null){this.date=date;}
        else{this.date="";}
    }

    @Override
     void setAuthor(String author)
    {
        this.author=author;
    }

    @Override
    void setTitle(String title)
    {
        this.title=title;
    }

    @Override
    void setLibrary(Library library)
    {
        this.library=library;
    }

    @Override
    public long getIndex()
    {
        return index;
    }

    @Override
    public String getAuthor()
    {
        return author;
    }

   @Override
    public AbstractLibrary getLibrary()
    {
        return library;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public String getDate()
    {
        return date;
    }

    @Override
    public boolean isAvailable()
    {
        return available;
    }

    @Override
    public boolean returnBook()
    {
        if(!available)
        {
            subscriber="";
            date="";
            available=true;
            return true;
        }
        return false;

    }

    @Override
    public boolean  orderBook(String subscriber)
    {
        if(available)
        {
            if(!(subscriber.equals("") && subscriber!=null))
            {
                this.subscriber=subscriber;
                this.date= Date.getCurrentDate();
                this.available=true;

                return true;
            }

        }

        return false;
    }

    @Override
    public boolean isNull()
    {
        return false;
    }

    @Override
    public String getSubscriber()
    {
        return subscriber;
    }
}
