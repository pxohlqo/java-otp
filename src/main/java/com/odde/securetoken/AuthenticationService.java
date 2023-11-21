package com.odde.securetoken;

import com.odde.Logger;

public class AuthenticationService {
    public AuthenticationService(ProfileDao profileDao, RsaTokenDao rsaTokenDao, Logger logger){
        this.profileDao = profileDao;
        this.rsaTokenDao = rsaTokenDao;
        this.logger = logger;
    }

    public final ProfileDao profileDao;
    public final RsaTokenDao rsaTokenDao;

    public final Logger logger;

    public boolean isValid(String account, String password) {
        // 根據 account 取得自訂密碼
//        ProfileDao profileDao = new ProfileDao();
        String passwordFromDao = profileDao.getPassword(account);

        // 根據 account 取得 RSA token 目前的亂數
//        RsaTokenDao rsaToken = new RsaTokenDao();
        String randomCode = rsaTokenDao.getRandom(account);

        // 驗證傳入的 password 是否等於自訂密碼 + RSA token亂數
        String validPassword = passwordFromDao + randomCode;
        boolean isValid = password.equals(validPassword);

        if (isValid) {
            return true;
        } else {
            logger.log("valid failed");
            return false;
        }
    }
}
