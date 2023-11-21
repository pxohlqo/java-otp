package com.odde.budget;

import java.time.YearMonth;

public class Budget {

    Budget(YearMonth yearMonth, long amount){
        mYearMonth = yearMonth;
        mAmount = amount;
    }

    private final YearMonth mYearMonth;
    private final long mAmount;

    public YearMonth getYearMonth(){
        return mYearMonth;
    }

    public long getAmount(){
        return mAmount;
    }
}
