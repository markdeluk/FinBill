package com.marco.finbill.sql.transaction.all;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

public interface TransactionCurrencyDao {
    @Query("SELECT T.transactionCurrencyId, C.* " +
            "FROM transaction_table T, currency_table C " +
            "WHERE T.transactionCurrencyId = C.currencyId " +
            "AND T.transactionId = :transactionId")
    LiveData<TransactionCurrency> getTransactionCurrencyById(int transactionId);
}
