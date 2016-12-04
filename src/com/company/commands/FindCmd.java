package com.company.commands;

import com.company.beans.book.AbstractBook;
import com.company.catalog.Catalog;
import com.company.presenter.IViewPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimanolog on 24.01.2016.
 */
public class FindCmd implements Commands
{
    private String author;
    private String title;
    private IViewPresenter pr;
    private Catalog catalog;

    FindCmd(String author, String title, IViewPresenter pr)
    {
        this.author = author;
        this.title = title;
        this.pr = pr;
        this.catalog = Catalog.getInstance();
    }

    public void execute()
    {
        Catalog catalog = Catalog.getInstance();
        boolean authorEmptyCheck = author.equals("") || author == null;
        boolean titleEmptyCheck = title.equals("") || title == null;

        if (authorEmptyCheck && titleEmptyCheck) {
            pr.displaySyntaxError();
            return;
        }

        List<AbstractBook> bookList;

        if (authorEmptyCheck || titleEmptyCheck) {
            if (titleEmptyCheck) {
                bookList = catalog.selectByAuthor(author);
            } else {
                bookList = catalog.selectByTittle(title);
            }

        } else {
            bookList = getByTitleAndAuthor();
        }

        pr.displayFoundResult(bookList);
    }

    private List<AbstractBook> getByTitleAndAuthor()
    {
        List<AbstractBook> bookList = catalog.selectByTittle(title);
        List<AbstractBook> newBookList = new ArrayList<AbstractBook>();
        for (AbstractBook book : bookList) {
            if (book.getAuthor().contains(author)) {
                newBookList.add(book);
            }
        }
        return newBookList;
    }
}
