package com.marco.finbill.sql.account;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.marco.finbill.enums.AccountType;
import com.marco.finbill.enums.PriorityType;

import java.util.Date;
import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insertAccount(Account account);
/*
    @Query("INSERT INTO account_table (" +
            "accountName, " +
            "accountDescription, " +
            "accountType, " +
            "accountBalance, " +
            "accountBalanceCurrencyId, " +
            "accountPlatfond, " +
            "accountPlatfondCurrencyId, " +
            "accountAdded, " +
            "accountCreated, " +
            "accountImage, " +
            "accountPriority) " +
            "VALUES (:accountName, " +
            ":accountDescription, " +
            ":accountType, " +
            ":accountBalance, " +
            "(SELECT currencyId FROM currency_table WHERE currencyString = :accountBalanceCurrencyString), " +
            ":accountPlatfond, " +
            "(SELECT currencyId FROM currency_table WHERE currencyString = :accountPlatfondCurrencyString), " +
            ":accountAdded, " +
            ":accountCreated, " +
            ":accountImage, " +
            ":accountPriority)")
    void insertAccountByCurrencyString(String accountName, String accountDescription, AccountType accountType, Double accountBalance, String accountBalanceCurrencyString, Double accountPlatfond, String accountPlatfondCurrencyString, Date accountAdded, Date accountCreated, Bitmap accountImage, PriorityType accountPriority);
*/
    @Update
    void updateAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("SELECT * FROM account_table WHERE accountId = :id")
    LiveData<Account> getAccountById(int id);

    @Query("SELECT * FROM account_table WHERE accountName = :name")
    LiveData<Account> getAccountByName(String name);

    @Query("SELECT * FROM account_table ORDER BY accountName ASC")
    LiveData<List<Account>> getAllAccounts();

    @Query("SELECT * FROM account_table WHERE accountName LIKE '%' || :query || '%'")
    LiveData<List<Account>> searchAccount(String query);

    @Query("DELETE FROM account_table")
    void deleteAllAccounts();
}
