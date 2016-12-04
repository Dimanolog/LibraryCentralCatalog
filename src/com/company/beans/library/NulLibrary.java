package com.company.beans.library;

/**
 * Created by Dimanolog on 20.01.2016.
 */
public class NulLibrary extends AbstractLibrary
{
    NulLibrary()
    {}
    @Override
    public String getName()
    {
        return "unknown";
    }

    @Override
    public boolean isNull()
    {
        return true;
    }
}
