package com.odde.budget;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.List;

public class BudgetService {

    BudgetService(BudgetRepo budgetRepo) {
        mBudgetRepo = budgetRepo;
    }

    private final BudgetRepo mBudgetRepo;

    public long queryBudget(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) throw new IllegalArgumentException();
        if (start.getYear() == end.getYear() && start.getMonth() == end.getMonth()) {
            Budget startBudget = find(mBudgetRepo.findAll(), start);
            long amountPerDay = amountPerDay(startBudget);
            return amountPerDay * (Period.between(start, end).getDays() + 1);
        } else {
            Period between = Period.between(start, end);
            long rlt = 0;
            Budget startBudget = find(mBudgetRepo.findAll(), start);
            LocalDate endDateOfStartMonth = YearMonth.of(start.getYear(), start.getMonth()).atEndOfMonth();
            int startMonthDays = Period.between(start, endDateOfStartMonth).getDays() + 1;
            rlt += amountPerDay(startBudget) * startMonthDays;

            Budget endBudget = find(mBudgetRepo.findAll(), end);
            LocalDate startDateOfEndMonth = YearMonth.of(end.getYear(), end.getMonth()).atDay(1);
            int endMonthDays = Period.between(startDateOfEndMonth, end).getDays() + 1;
            rlt += amountPerDay(endBudget) * endMonthDays;

            for (int i = 1; i < between.toTotalMonths() - 1; i++) {
                LocalDate ld = start.plusMonths(i);
                Budget ldBudget = find(mBudgetRepo.findAll(), ld);
                int days = YearMonth.of(ld.getYear(), ld.getMonth()).lengthOfMonth();
                rlt += amountPerDay(ldBudget) * days;
            }
            return rlt;
        }
    }

    private Budget find(List<Budget> list, LocalDate toFind) {
        for (Budget budget : list) {
            YearMonth yearMonth = budget.getYearMonth();
            if (yearMonth.getYear() == toFind.getYear() && yearMonth.getMonth() == toFind.getMonth()) {
                return budget;
            }
        }
        return new Budget(YearMonth.of(toFind.getYear(), toFind.getMonth()), 0);
    }

    private static long amountPerDay(Budget budget) {
        int days = budget.getYearMonth().lengthOfMonth();
        return budget.getAmount() / days;
    }
}
