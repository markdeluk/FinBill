package com.marco.finbill.sql.account;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.marco.finbill.sql.currency.Currency;

public class AccountWithCurrencies {

    @Embedded
    private Account account;

    @Relation(
            parentColumn = "accountBalanceCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency balanceCurrency;

    @Relation(
            parentColumn = "accountPlatfondCurrencyId",
            entityColumn = "currencyId"
    )
    private Currency platfondCurrency;



    public AccountWithCurrencies(Account account, Currency balanceCurrency, Currency platfondCurrency) {
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

    public boolean equals(AccountWithCurrencies accountWithCurrencies) {
        return account.equals(accountWithCurrencies.getAccount()) && balanceCurrency.equals(accountWithCurrencies.getBalanceCurrency()) && platfondCurrency.equals(accountWithCurrencies.getPlatfondCurrency());
    }
}
