package com.marco.finbill.sql.transaction.all;

import androidx.lifecycle.LiveData;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
public interface TransactionHasCurrencyDao {
    @Transaction
    @Query("SELECT * FROM transaction_table WHERE transactionId = :transactionId")
    LiveData<TransactionHasCurrency> getTransactionHasCurrencyById(int transactionId);

    @Transaction
    @Query("SELECT * FROM transaction_table")
    LiveData<List<TransactionHasCurrency>> getAllTransactionHaveCurrency();
}