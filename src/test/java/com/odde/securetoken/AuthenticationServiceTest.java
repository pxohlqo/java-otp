package com.odde.securetoken;

import com.odde.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationServiceTest {

    @Test
    public void is_valid_test() {

        ProfileDao mockProfileDao = Mockito.mock(ProfileDao.class);
        RsaTokenDao mockRsaTokenDao = Mockito.mock(RsaTokenDao.class);
        Logger mockLogger = Mockito.mock(Logger.class);

        AuthenticationService target = new AuthenticationService(mockProfileDao, mockRsaTokenDao, mockLogger);

        String mockAccount = "joey";
        String mockPassword = "91000000";

        Mockito.when(mockProfileDao.getPassword(mockAccount)).thenReturn("91");
        Mockito.when(mockRsaTokenDao.getRandom(mockAccount)).thenReturn("000000");

        boolean actual = target.isValid(mockAccount, mockPassword);

        assertTrue(actual);
    }

    @Test
    public void is_invalid_test() {

        ProfileDao mockProfileDao = Mockito.mock(ProfileDao.class);
        RsaTokenDao mockRsaTokenDao = Mockito.mock(RsaTokenDao.class);
        Logger mockLogger = Mockito.mock(Logger.class);

        AuthenticationService target = new AuthenticationService(mockProfileDao, mockRsaTokenDao, mockLogger);

        String mockAccount = "joey";
        String mockPassword = "91000000";

        Mockito.when(mockProfileDao.getPassword(mockAccount)).thenReturn("000000");
        Mockito.when(mockRsaTokenDao.getRandom(mockAccount)).thenReturn("91");

        boolean actual = target.isValid(mockAccount, mockPassword);

        Mockito.verify(mockLogger).log("valid failed");
        assertFalse(actual);
    }

    @Test
    public void is_invalid_test_with_log() {

        ProfileDao mockProfileDao = Mockito.mock(ProfileDao.class);
        RsaTokenDao mockRsaTokenDao = Mockito.mock(RsaTokenDao.class);
        Logger mockLogger = Mockito.mock(Logger.class);

        AuthenticationService target = new AuthenticationService(mockProfileDao, mockRsaTokenDao, mockLogger);

        String mockAccount = "joey";
        String mockPassword = "91000000";

        Mockito.when(mockProfileDao.getPassword(mockAccount)).thenReturn("000000");
        Mockito.when(mockRsaTokenDao.getRandom(mockAccount)).thenReturn("91");

        boolean actual = target.isValid(mockAccount, mockPassword);

        Mockito.verify(mockLogger).log("valid failed");
        assertFalse(actual);
    }
}
