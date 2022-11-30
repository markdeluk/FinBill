package com.marco.finbill.sql.account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountHasCurrenciesDao {
    @Query("SELECT * " +
            "FROM account_table A, currency_table B, currency_table P " +
            "WHERE A.accountBalanceCurrencyId = B.currencyId AND A.accountPlatfondCurrencyId = P.currencyId " +
            "AND A.accountId = :accountId")
    LiveData<AccountHasCurrencies> getAccountHasCurrenciesById(int accountId);

    @Query("SELECT A.*, B.*, P.* " +
            "FROM account_table A, currency_table B, currency_table P " +
            "WHERE A.accountBalanceCurrencyId = B.currencyId AND A.accountPlatfondCurrencyId = P.currencyId")
    LiveData<List<AccountHasCurrencies>> getAllAccountsHaveCurrencies();

}
