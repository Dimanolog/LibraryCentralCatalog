package com.company.presenter;

import com.company.beans.book.AbstractBook;

import java.util.List;

/**
 * Created by Dimanolog on 28.01.2016.
 */
public interface IViewPresenter
{
    void displaySyntaxError();
    void displayFoundResult(List<AbstractBook> bookList);
    void displayNotFound();
    void displayBookAlreadyReturned();
    void displayOrderOK(AbstractBook book);
    void displayBookReserved(AbstractBook book);
    void displayReturnOK(AbstractBook book);
    void displayError(String errorMessage);

}


