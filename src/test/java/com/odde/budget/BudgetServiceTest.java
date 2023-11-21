package com.odde.budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetServiceTest {

    /**
     * b1
     * |-------|
     * ^    ^
     * s    e
     */
    @Test
    void case1() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2000, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2000, 1, 31);

        long amount = service.queryBudget(start, end);

        assertEquals(31, amount);
    }

    /**
     * ----b1------b2---
     * |-------|-------|
     * ^           ^
     * s           e
     */
    @Test
    void case2() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2001, 1), 31),
                new Budget(YearMonth.of(2001, 2), 28)
        ));
        LocalDate start = LocalDate.of(2001, 1, 15);
        LocalDate end = LocalDate.of(2001, 2, 15);

        long amount = service.queryBudget(start, end);

        assertEquals(32, amount);
    }

    @Test
    void case3() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2001, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 15);
        LocalDate end = LocalDate.of(2000, 2, 15);

        long amount = service.queryBudget(start, end);

        assertEquals(0, amount);
    }

    @Test
    void case4() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(1997, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 15);
        LocalDate end = LocalDate.of(2000, 2, 15);

        long amount = service.queryBudget(start, end);

        assertEquals(0, amount);
    }

    @Test
    void case5() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2000, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 1);
        LocalDate end = LocalDate.of(2001, 1, 31);

        long amount = service.queryBudget(start, end);

        assertEquals(31, amount);
    }

    @Test
    void case6() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2000, 1), 31),
                new Budget(YearMonth.of(2001, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 15);
        LocalDate end = LocalDate.of(2001, 1, 15);

        long amount = service.queryBudget(start, end);

        assertEquals(17 + 15, amount);
    }

    @Test
    void case7() {
        BudgetService service = new BudgetService(mockBudgetRepoFrom(
                new Budget(YearMonth.of(2000, 1), 31),
                new Budget(YearMonth.of(2000, 7), 31),
                new Budget(YearMonth.of(2001, 1), 31)
        ));
        LocalDate start = LocalDate.of(2000, 1, 15);
        LocalDate end = LocalDate.of(2001, 1, 15);

        long amount = service.queryBudget(start, end);

        assertEquals(17 + 31 + 15, amount);
    }

    private BudgetRepo mockBudgetRepoFrom(Budget... budgets) {
        return () -> new ArrayList<>(Arrays.asList(budgets));
    }

}