package com.marco.finbill.sql.account;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface AccountBalanceCurrencyDao {
    @Query("SELECT A.accountBalance, C.* " +
            "FROM account_table A, currency_table C " +
            "WHERE A.accountBalanceCurrencyId = C.currencyId " +
            "AND A.accountId = :accountId")
    public AccountBalanceCurrency getAccountBalanceCurrencyById(int accountId);
}
