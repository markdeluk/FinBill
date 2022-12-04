package com.marco.finbill.sql.transaction.income;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncomeRelationshipsDao {
    @Query("SELECT * " +
            "FROM income_table I, transaction_table T, currency_table CU, category_table CA, account_table A " +
            "WHERE I.incomeId = T.transactionId AND T.transactionCurrencyId = CU.currencyId AND I.fromIncome = CA.categoryId AND I.toIncome = A.accountId")
    LiveData<List<IncomeRelationships>> getAllIncomeIsTransactionWithRelationships();
}
