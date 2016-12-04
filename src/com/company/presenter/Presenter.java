package com.company.presenter;


import com.company.beans.book.AbstractBook;
import com.company.commands.CommandFactory;
import com.company.commands.Commands;
import com.company.views.IPresentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimanolog on 25.01.2016.
 */
public class Presenter implements IViewPresenter, IModelPresenter
{
    private IPresentView cmdLineView;
    private CommandFactory cmdFactory;

   public  Presenter(IPresentView view)
    {
        this.cmdFactory = new CommandFactory(this);
        this.cmdLineView=view;
    }

    public void performCmd(String strCmd)
    {
        Commands cmd = cmdFactory.getCommand(strCmd, this);
        cmd.execute();
    }

    public void displaySyntaxError()
    {
        cmdLineView.syntaxError();
    }

    public void displayFoundResult(List<AbstractBook> bookList)
    {
        if (bookList.isEmpty()) {
            cmdLineView.notFound();
            return;
        }

        List<AbstractBook> freeList = new ArrayList<AbstractBook>();
        List<AbstractBook> takenList = new ArrayList<AbstractBook>();

        for (AbstractBook book : bookList) {
            if (book.isAvailable()) {
                freeList.add(book);
            } else {
                if (!book.isNull()) {
                    takenList.add(book);
                }
            }
        }
        if (freeList.isEmpty()) {
            for (AbstractBook book : takenList) {
                cmdLineView.foundMissing(book.getIndex(), book.getLibrary().getName(),book.getDate());
            }

        } else {
            for (AbstractBook book : freeList) {
                cmdLineView.foundOK(book.getIndex(), book.getLibrary().getName());

            }

        }
    }
    public void displayNotFound()
    {
        cmdLineView.notFound();
    }

    public void displayBookAlreadyReturned()
    {
        cmdLineView.alreadyReturned();
    }

    public void displayOrderOK(AbstractBook book)
    {
        cmdLineView.orderOK(book.getSubscriber(), book.getDate());
    }

    public void displayBookReserved(AbstractBook book)
    {
        cmdLineView.orderReserved(book.getSubscriber(), book.getDate());
    }

    public void displayReturnOK(AbstractBook book)
    {
        cmdLineView.returnOK(book.getSubscriber());
    }

    public void displayError(String errorMessage)
    {
        cmdLineView.error(errorMessage);
    }

}












