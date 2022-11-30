package com.marco.finbill.sql.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CategoryCurrencyDao {
    @Query("SELECT CA.categoryBalanceCurrencyId, CU.* " +
            "FROM category_table CA, currency_table CU " +
            "WHERE CA.categoryBalanceCurrencyId = CU.currencyId " +
            "AND CA.categoryId = :categoryId")
    public LiveData<CategoryCurrency> getCategoryBalanceCurrencyById(int categoryId);
}
