package com.odde.budget;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;

public class Budget {

    Budget(YearMonth yearMonth, long amount) {
        mYearMonth = yearMonth;
        mAmount = amount;
    }

    private final YearMonth mYearMonth;
    private final long mAmount;

    long amountPerDay() {
        int days = getYearMonth().lengthOfMonth();
        return getAmount() / days;
    }

    LocalDate getBudgetEnd() {
        return getYearMonth().atEndOfMonth();
    }

    LocalDate getBudgetStart() {
        return getYearMonth().atDay(1);
    }

    public YearMonth getYearMonth() {
        return mYearMonth;
    }

    public long getAmount() {
        return mAmount;
    }

    long getOverlappingAmount(LocalDate startDate, LocalDate endDate) {
        LocalDate budgetStart = this.getBudgetStart();
        LocalDate budgetEnd = this.getBudgetEnd();
        if (endDate.isBefore(budgetStart) || startDate.isAfter(budgetEnd)) return 0;
        LocalDate start = startDate.isAfter(budgetStart) ? startDate : budgetStart;
        LocalDate end = endDate.isBefore(budgetEnd) ? endDate : budgetEnd;
        return this.amountPerDay() * (Period.between(start, end).getDays() + 1);
    }
}
