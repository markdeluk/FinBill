package com.marco.finbill.sql.account;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void insertAccount(Account account);

    @Update
    void updateAccount(Account account);

    @Delete
    void deleteAccount(Account account);

    @Query("SELECT * FROM account_table WHERE accountId = :id")
    Account getAccountById(int id);

    @Query("SELECT * FROM account_table")
    LiveData<List<Account>> getAllAccounts();

    @Query("SELECT * FROM account_table WHERE accountName LIKE '%' || :query || '%'")
    LiveData<List<Account>> searchAccount(String query);

    @Query("DELETE FROM account_table")
    void deleteAllAccounts();
}
