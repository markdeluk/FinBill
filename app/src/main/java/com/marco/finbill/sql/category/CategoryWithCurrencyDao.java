package com.marco.finbill.sql.category;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.marco.finbill.enums.CategoryType;

import java.util.List;
@Dao
public interface CategoryWithCurrencyDao {

    @Transaction
    @Query("SELECT * FROM category_table WHERE categoryId = :categoryId")
    LiveData<CategoryWithCurrency> getCategoryWithCurrencyById(int categoryId);

    @Transaction
    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrency();

    @Transaction
    @Query("SELECT * FROM category_table WHERE categoryType = :categoryType")
    LiveData<List<CategoryWithCurrency>> getAllCategoriesWithCurrencyByType(CategoryType categoryType);

}