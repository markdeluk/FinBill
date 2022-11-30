package com.marco.finbill.sql.transaction.all;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public interface TransactionHasCurrencyDao {
    @Query("SELECT T.*, C.* " +
            "FROM transaction_table T, currency_table C " +
            "WHERE T.transactionCurrencyId = C.currencyId " +
            "AND T.transactionId = :transactionId")
    LiveData<TransactionHasCurrency> getTransactionHasCurrencyById(int transactionId);

    @Query("SELECT T.*, C.* " +
            "FROM transaction_table T, currency_table C " +
            "WHERE T.transactionCurrencyId = C.currencyId")
    LiveData<List<TransactionHasCurrency>> getAllTransactionHaveCurrency();
}
