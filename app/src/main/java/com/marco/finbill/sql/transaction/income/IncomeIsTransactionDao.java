package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncomeIsTransactionDao {
    @Query("SELECT T.transactionId AS transactionId, I.incomeId AS incomeId " +
            "FROM transaction_table T, income_table I " +
            "WHERE T.transactionId = I.incomeId")
    public LiveData<List<IncomeIsTransaction>> getAllIncomeIsTransaction();
}
