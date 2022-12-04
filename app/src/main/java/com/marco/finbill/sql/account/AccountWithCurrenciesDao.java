package com.marco.finbill.sql.account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface AccountWithCurrenciesDao {
    @Transaction
    @Query("SELECT * FROM account_table WHERE accountId = :accountId")
    LiveData<AccountWithCurrencies> getAccountHasCurrenciesById(int accountId);

    @Transaction
    @Query("SELECT * FROM account_table")
    LiveData<List<AccountWithCurrencies>> getAllAccountsHaveCurrencies();

}