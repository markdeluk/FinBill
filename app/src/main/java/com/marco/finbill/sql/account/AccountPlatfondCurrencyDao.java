package com.marco.finbill.sql.account;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface AccountPlatfondCurrencyDao {
    @Query("SELECT A.accountPlatfondCurrencyId, C.* " +
            "FROM account_table A, currency_table C " +
            "WHERE A.accountPlatfondCurrencyId = C.currencyId " +
            "AND A.accountId = :accountId")
    public AccountPlatfondCurrency getAccountPlatfondCurrencyById(int accountId);
}
