package com.company.views;

/**
 * Created by Dimanolog on 02.02.2016.
 */
public interface IPresentView
{
    void notFound();
    void syntaxError();
    void alreadyReturned();
    void orderOK(String subscriber, String date);
    void orderReserved(String subscriber, String date);
    void returnOK(String subscriber);
    void foundMissing(long index, String library, String date);
    void foundOK(long index, String library);
    void error(String error);
}
