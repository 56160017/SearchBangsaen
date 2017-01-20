package com.buu.se.searchbangsaen.utils;

public class SearchBangsaenSingleton {
    private static SearchBangsaenSingleton ourInstance;
    private String withDrawMoney;
    private String accountRegistation;
    private int typeRegister;
  //  private AccountDao accountDao;
    private String sessionId;

    public static SearchBangsaenSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new SearchBangsaenSingleton();
        }
        return ourInstance;
    }

    public String getWithDrawMoney() {
        return withDrawMoney;
    }

    public void setWithDrawMoney(String withDrawMoney) {
        this.withDrawMoney = withDrawMoney;
    }

    public String getAccountRegistation() {
        return accountRegistation;
    }

    public void setAccountRegistation(String accountRegistation) {
        this.accountRegistation = accountRegistation;
    }

    /*public AccountDao getAccountDaoRegistation() {
        return accountDao;
    }

    public void setAccountDaoRegistation(AccountDao accountDao) {
        this.accountDao = accountDao;
    }*/

    public int getTypeRegister() {
        return typeRegister;
    }

    public void setTypeRegister(int typeRegister) {
        this.typeRegister = typeRegister;
    }

    public String getSessionId() {
        return sessionId;
    }

    public synchronized void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    private SearchBangsaenSingleton() {
    }
}
