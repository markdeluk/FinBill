package com.marco.finbill.sql.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.marco.finbill.enums.CategoryType;

import java.util.List;

@Dao
public interface CategoryWithCurrencyDao {

    @Query("SELECT CA.*, CU.* " +
            "FROM category_table CA, currency_table CU " +
            "WHERE CA.categoryBalanceCurrencyId = CU.currencyId " +
            "AND CA.categoryId = :categoryId")
    public LiveData<CategoryWithCurrency> getCategoryWithCurrencyById(int categoryId);

    @Query("SELECT CA.*, CU.* " +
            "FROM category_table CA, currency_table CU " +
            "WHERE CA.categoryBalanceCurrencyId = CU.currencyId")
    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency();

    @Query("SELECT CA.*, CU.* " +
            "FROM category_table CA, currency_table CU " +
            "WHERE CA.categoryBalanceCurrencyId = CU.currencyId " +
            "AND CA.categoryType = :categoryType")
    public LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType categoryType);

}
