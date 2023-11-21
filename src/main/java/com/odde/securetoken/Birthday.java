package com.odde.securetoken;

import java.time.LocalDate;

import static java.time.Month.APRIL;

public class Birthday {

    public boolean isBirthday() {
        LocalDate today = now();
        return today.getDayOfMonth() == 9 && today.getMonth().equals(APRIL);
    }

    public LocalDate now(){
        return LocalDate.now();
    }
}