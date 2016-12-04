package com.company.commands;

import com.company.beans.book.AbstractBook;
import com.company.beans.book.BookFactory;
import com.company.catalog.Catalog;
import com.company.presenter.IViewPresenter;

/**
 * Created by Dimanolog on 22.01.2016.
 */
public class ReturnCmd implements Commands
{
    private String strID;
    private IViewPresenter presenter;

    ReturnCmd(String strID, IViewPresenter presenter)
    {
        this.strID = strID;
        this.presenter = presenter;
    }

    public void execute()
    {
        long id;
        try {
            id = Long.parseLong(strID);
        } catch (NumberFormatException e) {
            presenter.displayError("wrong book id");
            return;
        }
        Catalog catalog = Catalog.getInstance();
        AbstractBook book = catalog.selectByID(id);
        if (!book.isNull()) {
            if (!book.isAvailable()) {
                AbstractBook returnedBook= BookFactory.getAbstractBook(book);
                returnedBook.returnBook();
                if (catalog.updateBook(returnedBook)) {
                    presenter.displayReturnOK(book);
                    return;
                } else {
                    presenter.displayError("cant update record");
                }

            } else {
                presenter.displayBookAlreadyReturned();
            }
        } else {
            presenter.displayNotFound();
        }
    }
}
