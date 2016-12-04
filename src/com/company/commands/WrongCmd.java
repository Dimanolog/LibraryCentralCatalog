package com.company.commands;

import com.company.presenter.IViewPresenter;

/**
 * Created by Dimanolog on 24.01.2016.
 */
public class WrongCmd implements Commands
{
    private IViewPresenter pr;
    public WrongCmd(IViewPresenter pr)
    {
        this.pr=pr;
    }

    public void execute()
    {
        pr.displaySyntaxError();
    }
}
