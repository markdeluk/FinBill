package com.marco.finbill.sql.transaction.income;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface IncomeToAccountDao {
    @Query("SELECT I.incomeId, A.* " +
            "FROM income_table I, account_table A " +
            "WHERE I.toIncome = A.accountId " +
            "AND I.incomeId = :incomeId")
    IncomeToAccount getIncomeToAccountById(int incomeId);
}
