package com.odde.budget;

import java.time.LocalDate;

public class BudgetService {

    BudgetService(BudgetRepo budgetRepo) {
        mBudgetRepo = budgetRepo;
    }

    private final BudgetRepo mBudgetRepo;

    public long queryBudget(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) throw new IllegalArgumentException();
        long rlt = 0;
        for (Budget budget : mBudgetRepo.findAll()) {
            rlt += budget.getOverlappingAmount(start, end);
        }
        return rlt;
    }
}
