package com.company.data_formats;


import com.company.beans.book.AbstractBook;

/**
 * Created by Dimanolog on 09.12.2015.
 */
 public  interface ILiteraryData
{
    AbstractBook nextBook();//return beans from file
    boolean updateBook(AbstractBook book); //record changes to beans file
    boolean next();//moving to next file
    boolean hasNext();//check for last beans
    void resetCounter();// start over
}
