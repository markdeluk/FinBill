package com.marco.finbill.sql.transaction.income;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface IncomeFromCategoryDao {
    @Query("SELECT I.incomeId, C.* " +
            "FROM income_table I, category_table C " +
            "WHERE I.fromIncome = C.categoryId " +
            "AND I.incomeId = :incomeId")
    IncomeFromCategory getIncomeFromCategoryById(int incomeId);
}
