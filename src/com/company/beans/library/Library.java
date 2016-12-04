package com.company.beans.library;

/**
 * Created by Dimanolog on 18.01.2016.
 */
public class Library extends AbstractLibrary
{
    private String name;

    Library(String name)
    {
        this.name = name;
    }

    @Override
    public boolean isNull()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return name;
    }

}
