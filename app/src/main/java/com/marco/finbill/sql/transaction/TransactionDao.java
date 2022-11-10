package com.marco.finbill.sql.transaction;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.marco.finbill.sql.account.Account;
import com.marco.finbill.sql.category.Category;

import java.util.List;
import java.util.Map;

@Dao
public interface TransactionDao {

    @Insert
    void insertTransaction(Transaction transaction);

    @Update
    void updateTransaction(Transaction transaction);

    @Delete
    void deleteTransaction(Transaction transaction);

    @Query("SELECT type FROM transaction_table WHERE transactionId = :transactionId")
    Transaction getTransactionById(int transactionId);

    @Query("SELECT type FROM transaction_table WHERE transactionId = :transactionId")
    Transaction getTypeById(int transactionId);

    @Query("SELECT * FROM transaction_table")
    LiveData<List<Transaction>> getAllTransactions();

    @Query("SELECT * FROM transaction_table WHERE name LIKE '%' || :query || '%'")
    LiveData<List<Transaction>> searchTransaction(String query);

    @Query("DELETE FROM transaction_table")
    void deleteAllTransactions();
}
