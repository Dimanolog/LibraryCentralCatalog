package com.company.beans.book;

import com.company.beans.library.AbstractLibrary;

/**
 * Created by Dimanolog on 21.01.2016.
 */
public class BookFactory
{
    public static AbstractBook getAbstractBook(long index, String author, String title, String date, String subscriber, AbstractLibrary library)
    {
        if (index > 0)
            if (!(author == null || author.equals("")))
                if (!(title == null || title.equals("")))
                    if (library != null) {
                        return new Book(index, author, title, date, subscriber, library);
                    }

        return new NullBook();

    }

    public static AbstractBook getAbstractBook(AbstractBook book)
    {
       return book.isNull()? new NullBook(): new Book((Book)book);
    }

    public static NullBook getNullBook()
    {
        return new NullBook();
    }
}
