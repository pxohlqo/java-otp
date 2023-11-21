package com.odde.securetoken;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BirthdayTest {

    @Test
    public void testIsBirthDay(){
        Birthday birthday = new Birthday();

        Birthday spy = Mockito.spy(birthday);
        Mockito.when(spy.now()).thenReturn(LocalDate.of(1970, 4, 9));
        assertTrue(spy.isBirthday());

        Mockito.when(spy.now()).thenReturn(LocalDate.of(1970, 4, 10));
        assertFalse(spy.isBirthday());

        Mockito.when(spy.now()).thenReturn(LocalDate.of(1970, 5, 9));
        assert !spy.isBirthday();
    }
}
