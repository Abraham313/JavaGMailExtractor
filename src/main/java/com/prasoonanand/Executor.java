package com.prasoonanand;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by prasoonanand on 14/02/17.
 */
public class Executor {
    private static final String YOUR_EMAIL_ADDRESS = "";
    private static final String PASSWORD = "";
    private static final int FROM_YEAR = 2018;
    private static final int TO_YEAR = 2018;
    private static final int FROM_DAY = 1;
    private static final int TO_DAY = 31;
    private static final int FROM_MONTH = Calendar.JANUARY;
    private static final int TO_MONTH = Calendar.JANUARY;

    public static void main(String[] args){
        final Calendar date = Calendar.getInstance();
        date.set(FROM_YEAR, FROM_MONTH, FROM_DAY);
        Date fromDate = date.getTime();
        date.set(TO_YEAR, TO_MONTH, TO_DAY);
        Date toDate = date.getTime();
        String folder = "INBOX";
        ReadMail.checkMail(YOUR_EMAIL_ADDRESS, PASSWORD, fromDate, toDate, folder);
    }


}
