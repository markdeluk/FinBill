package com.marco.finbill.sql.account;

import androidx.room.Embedded;

import com.marco.finbill.sql.currency.Currency;

public class AccountHasCurrencies {

    @Embedded
    private Account account;
    @Embedded(prefix = "balance_")
    private Currency balanceCurrency;
    @Embedded(prefix = "platfond_")
    private Currency platfondCurrency;

    public AccountHasCurrencies(Account account, Currency balanceCurrency, Currency platfondCurrency) {
        this.account = account;
        this.balanceCurrency = balanceCurrency;
        this.platfondCurrency = platfondCurrency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Currency getBalanceCurrency() {
        return balanceCurrency;
    }

    public void setBalanceCurrency(Currency balanceCurrency) {
        this.balanceCurrency = balanceCurrency;
    }

    public Currency getPlatfondCurrency() {
        return platfondCurrency;
    }

    public void setPlatfondCurrency(Currency platfondCurrency) {
        this.platfondCurrency = platfondCurrency;
    }

    public boolean equals(AccountHasCurrencies accountHasCurrencies) {
        return account.equals(accountHasCurrencies.getAccount()) && balanceCurrency.equals(accountHasCurrencies.getBalanceCurrency()) && platfondCurrency.equals(accountHasCurrencies.getPlatfondCurrency());
    }
}
