package com.company.commands;

import com.company.beans.book.AbstractBook;
import com.company.catalog.Catalog;
import com.company.presenter.IViewPresenter;

/**
 * Created by Dimanolog on 24.01.2016.
 */
public class OrderCmd implements Commands
{
    private String strID;
    private String subscriber;
    private IViewPresenter presenter;

    OrderCmd(String strID, String subscriber, IViewPresenter presenter)
    {
        this.strID = strID;
        this.subscriber = subscriber;
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
        if (!(subscriber.equals("") || subscriber == null)) {
            AbstractBook book = catalog.selectByID(id);
            if (!book.isNull()) {
                if (book.isAvailable()) {
                    book.orderBook(subscriber);
                    if (catalog.updateBook(book)) {
                        presenter.displayOrderOK(book);
                        return;
                    } else {
                        presenter.displayError("cant update record");
                    }
                } else presenter.displayBookReserved(book);
            } else {
                presenter.displayNotFound();
            }
        } else {
            presenter.displaySyntaxError();
        }

    }

}



