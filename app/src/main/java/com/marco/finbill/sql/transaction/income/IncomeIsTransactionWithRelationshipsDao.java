package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncomeIsTransactionWithRelationshipsDao {
    @Query("SELECT * " +
            "FROM income_table I, transaction_table T, category_table C, account_table A " +
            "WHERE I.incomeId = T.transactionId AND I.fromIncome = C.categoryId AND I.toIncome = A.accountId")
    LiveData<List<IncomeIsTransactionWithRelationships>> getAllIncomeIsTransactionWithRelationships();

    @Query("SELECT * " +
            "FROM income_table I, transaction_table T, category_table C, account_table A " +
            "WHERE I.incomeId = T.transactionId AND I.fromIncome = C.categoryId AND I.toIncome = A.accountId " +
            "AND I.incomeId = :incomeId")
    IncomeIsTransactionWithRelationships getIncomeIsTransactionWithRelationshipsById(int incomeId);
}
